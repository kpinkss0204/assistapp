package dev.chrisbanes.snapper;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnapperFlingBehavior.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001Bm\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u0012>\b\u0002\u0010\t\u001a8\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\u0010BC\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u0012\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0012¢\u0006\u0002\u0010\u0013B}\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u0012<\u0010\t\u001a8\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0012¢\u0006\u0002\u0010\u0014J \u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002J\u0010\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0006H\u0002J\"\u0010#\u001a\u00020$*\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0002J%\u0010%\u001a\u00020\u0006*\u00020&2\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J7\u0010)\u001a\u00020\u0006*\u00020&2\u0006\u0010*\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u00062\b\b\u0002\u0010+\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010,J\u001d\u0010-\u001a\u00020\u0006*\u00020&2\u0006\u0010\u001e\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010.JK\u0010/\u001a\u00020$*\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000201002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u000b2!\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u00060\u0012H\u0002J/\u00104\u001a\u00020\u0006*\u00020&2\u0006\u0010*\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u00105R/\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0012X\u0082\u0004¢\u0006\u0002\n\u0000RD\u0010\t\u001a8\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00066"}, d2 = {"Ldev/chrisbanes/snapper/SnapperFlingBehavior;", "Landroidx/compose/foundation/gestures/FlingBehavior;", "layoutInfo", "Ldev/chrisbanes/snapper/SnapperLayoutInfo;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "springAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "snapIndex", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "startIndex", "targetIndex", "(Ldev/chrisbanes/snapper/SnapperLayoutInfo;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function3;)V", "maximumFlingDistance", "Lkotlin/Function1;", "(Ldev/chrisbanes/snapper/SnapperLayoutInfo;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;)V", "(Ldev/chrisbanes/snapper/SnapperLayoutInfo;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;)V", "<set-?>", "animationTarget", "getAnimationTarget", "()Ljava/lang/Integer;", "setAnimationTarget", "(Ljava/lang/Integer;)V", "animationTarget$delegate", "Landroidx/compose/runtime/MutableState;", "calculateSnapBack", "initialVelocity", "currentItem", "Ldev/chrisbanes/snapper/SnapperLayoutItemInfo;", "consumeVelocityIfNotAtScrollEdge", "velocity", "canDecayBeyondCurrentItem", "", "flingToIndex", "Landroidx/compose/foundation/gestures/ScrollScope;", FirebaseAnalytics.Param.INDEX, "(Landroidx/compose/foundation/gestures/ScrollScope;IFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performDecayFling", "initialItem", "flingThenSpring", "(Landroidx/compose/foundation/gestures/ScrollScope;Ldev/chrisbanes/snapper/SnapperLayoutItemInfo;IFZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performFling", "(Landroidx/compose/foundation/gestures/ScrollScope;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performSnapBackIfNeeded", "Landroidx/compose/animation/core/AnimationScope;", "Landroidx/compose/animation/core/AnimationVector1D;", "scrollBy", "pixels", "performSpringFling", "(Landroidx/compose/foundation/gestures/ScrollScope;Ldev/chrisbanes/snapper/SnapperLayoutItemInfo;IFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lib_release"}, k = 1, mv = {1, 6, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SnapperFlingBehavior implements FlingBehavior {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: animationTarget$delegate, reason: from kotlin metadata */
    private final MutableState animationTarget;
    private final DecayAnimationSpec<Float> decayAnimationSpec;
    private final SnapperLayoutInfo layoutInfo;
    private final Function1<SnapperLayoutInfo, Float> maximumFlingDistance;
    private final Function3<SnapperLayoutInfo, Integer, Integer, Integer> snapIndex;
    private final AnimationSpec<Float> springAnimationSpec;

    /* JADX INFO: renamed from: dev.chrisbanes.snapper.SnapperFlingBehavior$flingToIndex$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapperFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "dev.chrisbanes.snapper.SnapperFlingBehavior", f = "SnapperFlingBehavior.kt", i = {0, 0, 0, 0, 1}, l = {406, TypedValues.CycleType.TYPE_PATH_ROTATE}, m = "flingToIndex", n = {"this", "$this$flingToIndex", FirebaseAnalytics.Param.INDEX, "initialVelocity", "this"}, s = {"L$0", "L$1", "I$0", "F$0", "L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapperFlingBehavior.this.flingToIndex(null, 0, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: dev.chrisbanes.snapper.SnapperFlingBehavior$performDecayFling$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapperFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "dev.chrisbanes.snapper.SnapperFlingBehavior", f = "SnapperFlingBehavior.kt", i = {0, 0}, l = {477}, m = "performDecayFling", n = {"this", "velocityLeft"}, s = {"L$0", "L$1"})
    static final class C06931 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C06931(Continuation<? super C06931> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapperFlingBehavior.this.performDecayFling(null, null, 0, 0.0f, false, this);
        }
    }

    /* JADX INFO: renamed from: dev.chrisbanes.snapper.SnapperFlingBehavior$performSpringFling$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapperFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "dev.chrisbanes.snapper.SnapperFlingBehavior", f = "SnapperFlingBehavior.kt", i = {0, 0}, l = {551}, m = "performSpringFling", n = {"this", "velocityLeft"}, s = {"L$0", "L$1"})
    static final class C06941 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C06941(Continuation<? super C06941> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapperFlingBehavior.this.performSpringFling(null, null, 0, 0.0f, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SnapperFlingBehavior(SnapperLayoutInfo snapperLayoutInfo, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Function3<? super SnapperLayoutInfo, ? super Integer, ? super Integer, Integer> function3, Function1<? super SnapperLayoutInfo, Float> function1) {
        this.layoutInfo = snapperLayoutInfo;
        this.decayAnimationSpec = decayAnimationSpec;
        this.springAnimationSpec = animationSpec;
        this.snapIndex = function3;
        this.maximumFlingDistance = function1;
        this.animationTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    public /* synthetic */ SnapperFlingBehavior(SnapperLayoutInfo snapperLayoutInfo, DecayAnimationSpec decayAnimationSpec, AnimationSpec animationSpec, Function3 function3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(snapperLayoutInfo, (DecayAnimationSpec<Float>) decayAnimationSpec, (AnimationSpec<Float>) ((i & 4) != 0 ? SnapperFlingBehaviorDefaults.INSTANCE.getSpringAnimationSpec() : animationSpec), (Function3<? super SnapperLayoutInfo, ? super Integer, ? super Integer, Integer>) ((i & 8) != 0 ? SnapperFlingBehaviorDefaults.INSTANCE.getSnapIndex() : function3));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SnapperFlingBehavior(SnapperLayoutInfo layoutInfo, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> springAnimationSpec, Function3<? super SnapperLayoutInfo, ? super Integer, ? super Integer, Integer> snapIndex) {
        this(layoutInfo, decayAnimationSpec, springAnimationSpec, snapIndex, SnapperFlingBehaviorDefaults.INSTANCE.getMaximumFlingDistance());
        Intrinsics.checkNotNullParameter(layoutInfo, "layoutInfo");
        Intrinsics.checkNotNullParameter(decayAnimationSpec, "decayAnimationSpec");
        Intrinsics.checkNotNullParameter(springAnimationSpec, "springAnimationSpec");
        Intrinsics.checkNotNullParameter(snapIndex, "snapIndex");
    }

    public /* synthetic */ SnapperFlingBehavior(SnapperLayoutInfo snapperLayoutInfo, DecayAnimationSpec decayAnimationSpec, AnimationSpec animationSpec, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(snapperLayoutInfo, (DecayAnimationSpec<Float>) decayAnimationSpec, (AnimationSpec<Float>) ((i & 4) != 0 ? SnapperFlingBehaviorDefaults.INSTANCE.getSpringAnimationSpec() : animationSpec), (Function1<? super SnapperLayoutInfo, Float>) ((i & 8) != 0 ? SnapperFlingBehaviorDefaults.INSTANCE.getMaximumFlingDistance() : function1));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "The maximumFlingDistance parameter has been replaced with snapIndex")
    public SnapperFlingBehavior(SnapperLayoutInfo layoutInfo, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> springAnimationSpec, Function1<? super SnapperLayoutInfo, Float> maximumFlingDistance) {
        this(layoutInfo, decayAnimationSpec, springAnimationSpec, SnapperFlingBehaviorDefaults.INSTANCE.getSnapIndex(), maximumFlingDistance);
        Intrinsics.checkNotNullParameter(layoutInfo, "layoutInfo");
        Intrinsics.checkNotNullParameter(decayAnimationSpec, "decayAnimationSpec");
        Intrinsics.checkNotNullParameter(springAnimationSpec, "springAnimationSpec");
        Intrinsics.checkNotNullParameter(maximumFlingDistance, "maximumFlingDistance");
    }

    private final void setAnimationTarget(Integer num) {
        this.animationTarget.setValue(num);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Integer getAnimationTarget() {
        return (Integer) this.animationTarget.getValue();
    }

    @Override // androidx.compose.foundation.gestures.FlingBehavior
    public Object performFling(ScrollScope scrollScope, float f, Continuation<? super Float> continuation) {
        if (!this.layoutInfo.canScrollTowardsStart() || !this.layoutInfo.canScrollTowardsEnd()) {
            return Boxing.boxFloat(f);
        }
        SnapperLog snapperLog = SnapperLog.INSTANCE;
        float fFloatValue = this.maximumFlingDistance.invoke(this.layoutInfo).floatValue();
        if (fFloatValue <= 0.0f) {
            throw new IllegalArgumentException("Distance returned by maximumFlingDistance should be greater than 0".toString());
        }
        SnapperLayoutItemInfo currentItem = this.layoutInfo.getCurrentItem();
        if (currentItem == null) {
            return Boxing.boxFloat(f);
        }
        int iIntValue = this.snapIndex.invoke(this.layoutInfo, Boxing.boxInt(f < 0.0f ? currentItem.getIndex() + 1 : currentItem.getIndex()), Boxing.boxInt(this.layoutInfo.determineTargetIndex(f, this.decayAnimationSpec, fFloatValue))).intValue();
        if (iIntValue < 0 || iIntValue >= this.layoutInfo.getTotalItemsCount()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return flingToIndex(scrollScope, iIntValue, f, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object flingToIndex(androidx.compose.foundation.gestures.ScrollScope r12, int r13, float r14, kotlin.coroutines.Continuation<? super java.lang.Float> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: dev.chrisbanes.snapper.SnapperFlingBehavior.flingToIndex(androidx.compose.foundation.gestures.ScrollScope, int, float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object performDecayFling(final androidx.compose.foundation.gestures.ScrollScope r19, dev.chrisbanes.snapper.SnapperLayoutItemInfo r20, final int r21, float r22, boolean r23, kotlin.coroutines.Continuation<? super java.lang.Float> r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: dev.chrisbanes.snapper.SnapperFlingBehavior.performDecayFling(androidx.compose.foundation.gestures.ScrollScope, dev.chrisbanes.snapper.SnapperLayoutItemInfo, int, float, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object performDecayFling$default(SnapperFlingBehavior snapperFlingBehavior, ScrollScope scrollScope, SnapperLayoutItemInfo snapperLayoutItemInfo, int i, float f, boolean z, Continuation continuation, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = true;
        }
        return snapperFlingBehavior.performDecayFling(scrollScope, snapperLayoutItemInfo, i, f, z, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object performSpringFling(final androidx.compose.foundation.gestures.ScrollScope r22, dev.chrisbanes.snapper.SnapperLayoutItemInfo r23, final int r24, float r25, kotlin.coroutines.Continuation<? super java.lang.Float> r26) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: dev.chrisbanes.snapper.SnapperFlingBehavior.performSpringFling(androidx.compose.foundation.gestures.ScrollScope, dev.chrisbanes.snapper.SnapperLayoutItemInfo, int, float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object performSpringFling$default(SnapperFlingBehavior snapperFlingBehavior, ScrollScope scrollScope, SnapperLayoutItemInfo snapperLayoutItemInfo, int i, float f, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            f = 0.0f;
        }
        return snapperFlingBehavior.performSpringFling(scrollScope, snapperLayoutItemInfo, i, f, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean performSnapBackIfNeeded(AnimationScope<Float, AnimationVector1D> animationScope, SnapperLayoutItemInfo snapperLayoutItemInfo, int i, Function1<? super Float, Float> function1) {
        SnapperLog snapperLog = SnapperLog.INSTANCE;
        int iCalculateSnapBack = calculateSnapBack(animationScope.getVelocity().floatValue(), snapperLayoutItemInfo, i);
        if (iCalculateSnapBack == 0) {
            return false;
        }
        SnapperLog snapperLog2 = SnapperLog.INSTANCE;
        function1.invoke(Float.valueOf(iCalculateSnapBack));
        return true;
    }

    private final boolean canDecayBeyondCurrentItem(DecayAnimationSpec<Float> decayAnimationSpec, float f, SnapperLayoutItemInfo snapperLayoutItemInfo) {
        if (Math.abs(f) < 0.5f) {
            return false;
        }
        float fCalculateTargetValue = DecayAnimationSpecKt.calculateTargetValue(decayAnimationSpec, 0.0f, f);
        SnapperLog snapperLog = SnapperLog.INSTANCE;
        return f < 0.0f ? fCalculateTargetValue <= ((float) this.layoutInfo.distanceToIndexSnap(snapperLayoutItemInfo.getIndex())) : fCalculateTargetValue >= ((float) this.layoutInfo.distanceToIndexSnap(snapperLayoutItemInfo.getIndex() + 1));
    }

    private final int calculateSnapBack(float initialVelocity, SnapperLayoutItemInfo currentItem, int targetIndex) {
        if (initialVelocity > 0.0f && currentItem.getIndex() >= targetIndex) {
            return this.layoutInfo.distanceToIndexSnap(currentItem.getIndex());
        }
        if (initialVelocity >= 0.0f || currentItem.getIndex() > targetIndex - 1) {
            return 0;
        }
        return this.layoutInfo.distanceToIndexSnap(currentItem.getIndex() + 1);
    }

    private final float consumeVelocityIfNotAtScrollEdge(float velocity) {
        if ((velocity >= 0.0f || this.layoutInfo.canScrollTowardsStart()) && (velocity <= 0.0f || this.layoutInfo.canScrollTowardsEnd())) {
            return 0.0f;
        }
        return velocity;
    }
}
