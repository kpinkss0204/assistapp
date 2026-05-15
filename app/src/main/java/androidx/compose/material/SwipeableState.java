package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: Swipeable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0017\b\u0017\u0018\u0000 v*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001vBB\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012#\b\u0002\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\b¢\u0006\u0002\u0010\rJ$\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020\u00062\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@¢\u0006\u0002\u0010cJ&\u0010d\u001a\u00020`2\u0006\u0010P\u001a\u00028\u00002\u000e\b\u0002\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087@¢\u0006\u0002\u0010fJ!\u0010g\u001a\u00020`2\u0012\u0010h\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0011H\u0000¢\u0006\u0002\biJ\u000e\u0010j\u001a\u00020\u00062\u0006\u0010k\u001a\u00020\u0006J\u0016\u0010l\u001a\u00020`2\u0006\u0010m\u001a\u00020\u0006H\u0086@¢\u0006\u0002\u0010nJ8\u0010o\u001a\u00020`2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u00112\u0012\u0010h\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0011H\u0080@¢\u0006\u0004\bq\u0010rJ\u0016\u0010s\u001a\u00020`2\u0006\u0010a\u001a\u00020\u0006H\u0082@¢\u0006\u0002\u0010nJ\u0016\u0010t\u001a\u00020`2\u0006\u0010P\u001a\u00028\u0000H\u0087@¢\u0006\u0002\u0010uR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000RC\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u00112\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u00118@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR+\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u0018\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010%\u001a\u00020\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020+X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R+\u0010.\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u0018\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R \u00103\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u001104X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010)\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010)\"\u0004\b;\u00108R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00060=8F¢\u0006\u0006\u001a\u0004\b>\u0010?R\u000e\u0010@\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00060=8F¢\u0006\u0006\u001a\u0004\bB\u0010?R\u000e\u0010C\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000E8FX\u0087\u0004¢\u0006\f\u0012\u0004\bF\u0010'\u001a\u0004\bG\u0010HR/\u0010J\u001a\u0004\u0018\u00010I2\b\u0010\u0010\u001a\u0004\u0018\u00010I8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bO\u0010\u0018\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001a\u0010P\u001a\u00028\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\bQ\u0010'\u001a\u0004\bR\u0010!RO\u0010T\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060S2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060S8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bY\u0010\u0018\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR+\u0010Z\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00068@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b]\u0010^\u001a\u0004\b[\u0010)\"\u0004\b\\\u00108¨\u0006w"}, d2 = {"Landroidx/compose/material/SwipeableState;", ExifInterface.GPS_DIRECTION_TRUE, "", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "newValue", "", "(Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;)V", "absoluteOffset", "Landroidx/compose/runtime/MutableFloatState;", "<set-?>", "", "anchors", "getAnchors$material_release", "()Ljava/util/Map;", "setAnchors$material_release", "(Ljava/util/Map;)V", "anchors$delegate", "Landroidx/compose/runtime/MutableState;", "getAnimationSpec$material_release", "()Landroidx/compose/animation/core/AnimationSpec;", "animationTarget", "Landroidx/compose/runtime/MutableState;", "getConfirmStateChange$material_release", "()Lkotlin/jvm/functions/Function1;", "currentValue", "getCurrentValue", "()Ljava/lang/Object;", "setCurrentValue", "(Ljava/lang/Object;)V", "currentValue$delegate", "direction", "getDirection$annotations", "()V", "getDirection", "()F", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "getDraggableState$material_release", "()Landroidx/compose/foundation/gestures/DraggableState;", "isAnimationRunning", "()Z", "setAnimationRunning", "(Z)V", "isAnimationRunning$delegate", "latestNonEmptyAnchorsFlow", "Lkotlinx/coroutines/flow/Flow;", "maxBound", "getMaxBound$material_release", "setMaxBound$material_release", "(F)V", "minBound", "getMinBound$material_release", "setMinBound$material_release", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/runtime/State;", "getOffset", "()Landroidx/compose/runtime/State;", "offsetState", "overflow", "getOverflow", "overflowState", "progress", "Landroidx/compose/material/SwipeProgress;", "getProgress$annotations", "getProgress", "()Landroidx/compose/material/SwipeProgress;", "Landroidx/compose/material/ResistanceConfig;", "resistance", "getResistance$material_release", "()Landroidx/compose/material/ResistanceConfig;", "setResistance$material_release", "(Landroidx/compose/material/ResistanceConfig;)V", "resistance$delegate", "targetValue", "getTargetValue$annotations", "getTargetValue", "Lkotlin/Function2;", "thresholds", "getThresholds$material_release", "()Lkotlin/jvm/functions/Function2;", "setThresholds$material_release", "(Lkotlin/jvm/functions/Function2;)V", "thresholds$delegate", "velocityThreshold", "getVelocityThreshold$material_release", "setVelocityThreshold$material_release", "velocityThreshold$delegate", "Landroidx/compose/runtime/MutableFloatState;", "animateInternalToOffset", "", TypedValues.AttributesType.S_TARGET, "spec", "(FLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateTo", "anim", "(Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureInit", "newAnchors", "ensureInit$material_release", "performDrag", "delta", "performFling", "velocity", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processNewAnchors", "oldAnchors", "processNewAnchors$material_release", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "snapInternalToOffset", "snapTo", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@Deprecated(message = "Material's Swipeable has been replaced by Foundation's AnchoredDraggable APIs. Please see developer.android.com for an overview of the changes and a migration guide.")
public class SwipeableState<T> {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final MutableFloatState absoluteOffset;

    /* JADX INFO: renamed from: anchors$delegate, reason: from kotlin metadata */
    private final MutableState anchors;
    private final AnimationSpec<Float> animationSpec;
    private final MutableState<Float> animationTarget;
    private final Function1<T, Boolean> confirmStateChange;

    /* JADX INFO: renamed from: currentValue$delegate, reason: from kotlin metadata */
    private final MutableState currentValue;
    private final DraggableState draggableState;

    /* JADX INFO: renamed from: isAnimationRunning$delegate, reason: from kotlin metadata */
    private final MutableState isAnimationRunning;
    private final Flow<Map<Float, T>> latestNonEmptyAnchorsFlow;
    private float maxBound;
    private float minBound;
    private final MutableFloatState offsetState;
    private final MutableFloatState overflowState;

    /* JADX INFO: renamed from: resistance$delegate, reason: from kotlin metadata */
    private final MutableState resistance;

    /* JADX INFO: renamed from: thresholds$delegate, reason: from kotlin metadata */
    private final MutableState thresholds;

    /* JADX INFO: renamed from: velocityThreshold$delegate, reason: from kotlin metadata */
    private final MutableFloatState velocityThreshold;

    public static /* synthetic */ void getDirection$annotations() {
    }

    public static /* synthetic */ void getProgress$annotations() {
    }

    public static /* synthetic */ void getTargetValue$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SwipeableState(T t, AnimationSpec<Float> animationSpec, Function1<? super T, Boolean> function1) {
        this.animationSpec = animationSpec;
        this.confirmStateChange = function1;
        this.currentValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
        this.isAnimationRunning = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.offsetState = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.overflowState = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.absoluteOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.animationTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.anchors = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MapsKt.emptyMap(), null, 2, null);
        final Flow flowSnapshotFlow = SnapshotStateKt.snapshotFlow(new Function0<Map<Float, ? extends T>>(this) { // from class: androidx.compose.material.SwipeableState$latestNonEmptyAnchorsFlow$1
            final /* synthetic */ SwipeableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<Float, T> invoke() {
                return this.this$0.getAnchors$material_release();
            }
        });
        this.latestNonEmptyAnchorsFlow = FlowKt.take(new Flow<Map<Float, ? extends T>>() { // from class: androidx.compose.material.SwipeableState$special$$inlined$filter$1

            /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$special$$inlined$filter$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$special$$inlined$filter$1$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                @DebugMetadata(c = "androidx.compose.material.SwipeableState$special$$inlined$filter$1$2", f = "Swipeable.kt", i = {}, l = {223}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof androidx.compose.material.SwipeableState$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r6
                        androidx.compose.material.SwipeableState$special$$inlined$filter$1$2$1 r0 = (androidx.compose.material.SwipeableState$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r6 = r0.label
                        int r6 = r6 - r2
                        r0.label = r6
                        goto L19
                    L14:
                        androidx.compose.material.SwipeableState$special$$inlined$filter$1$2$1 r0 = new androidx.compose.material.SwipeableState$special$$inlined$filter$1$2$1
                        r0.<init>(r6)
                    L19:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L32
                        if (r2 != r3) goto L2a
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4c
                    L2a:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L32:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlinx.coroutines.flow.FlowCollector r6 = r4.$this_unsafeFlow
                        r2 = r0
                        kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                        r2 = r5
                        java.util.Map r2 = (java.util.Map) r2
                        boolean r2 = r2.isEmpty()
                        if (r2 != 0) goto L4c
                        r0.label = r3
                        java.lang.Object r5 = r6.emit(r5, r0)
                        if (r5 != r1) goto L4c
                        return r1
                    L4c:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SwipeableState$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flowSnapshotFlow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, 1);
        this.minBound = Float.NEGATIVE_INFINITY;
        this.maxBound = Float.POSITIVE_INFINITY;
        this.thresholds = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new Function2<Float, Float, Float>() { // from class: androidx.compose.material.SwipeableState$thresholds$2
            public final Float invoke(float f, float f2) {
                return Float.valueOf(0.0f);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Float invoke(Float f, Float f2) {
                return invoke(f.floatValue(), f2.floatValue());
            }
        }, null, 2, null);
        this.velocityThreshold = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.resistance = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.draggableState = DraggableKt.DraggableState(new Function1<Float, Unit>(this) { // from class: androidx.compose.material.SwipeableState$draggableState$1
            final /* synthetic */ SwipeableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                invoke(f.floatValue());
                return Unit.INSTANCE;
            }

            public final void invoke(float f) {
                float floatValue = ((SwipeableState) this.this$0).absoluteOffset.getFloatValue() + f;
                float fCoerceIn = RangesKt.coerceIn(floatValue, this.this$0.getMinBound(), this.this$0.getMaxBound());
                float f2 = floatValue - fCoerceIn;
                ResistanceConfig resistance$material_release = this.this$0.getResistance$material_release();
                ((SwipeableState) this.this$0).offsetState.setFloatValue(fCoerceIn + (resistance$material_release != null ? resistance$material_release.computeResistance(f2) : 0.0f));
                ((SwipeableState) this.this$0).overflowState.setFloatValue(f2);
                ((SwipeableState) this.this$0).absoluteOffset.setFloatValue(floatValue);
            }
        });
    }

    public /* synthetic */ SwipeableState(Object obj, SpringSpec<Float> springSpec, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? SwipeableDefaults.INSTANCE.getAnimationSpec() : springSpec, (i & 4) != 0 ? new Function1<T, Boolean>() { // from class: androidx.compose.material.SwipeableState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(T t) {
                return true;
            }
        } : anonymousClass1);
    }

    public final AnimationSpec<Float> getAnimationSpec$material_release() {
        return this.animationSpec;
    }

    public final Function1<T, Boolean> getConfirmStateChange$material_release() {
        return this.confirmStateChange;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentValue(T t) {
        this.currentValue.setValue(t);
    }

    public final T getCurrentValue() {
        return this.currentValue.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAnimationRunning(boolean z) {
        this.isAnimationRunning.setValue(Boolean.valueOf(z));
    }

    public final boolean isAnimationRunning() {
        return ((Boolean) this.isAnimationRunning.getValue()).booleanValue();
    }

    public final State<Float> getOffset() {
        return this.offsetState;
    }

    public final State<Float> getOverflow() {
        return this.overflowState;
    }

    public final Map<Float, T> getAnchors$material_release() {
        return (Map) this.anchors.getValue();
    }

    public final void setAnchors$material_release(Map<Float, ? extends T> map) {
        this.anchors.setValue(map);
    }

    /* JADX INFO: renamed from: getMinBound$material_release, reason: from getter */
    public final float getMinBound() {
        return this.minBound;
    }

    public final void setMinBound$material_release(float f) {
        this.minBound = f;
    }

    /* JADX INFO: renamed from: getMaxBound$material_release, reason: from getter */
    public final float getMaxBound() {
        return this.maxBound;
    }

    public final void setMaxBound$material_release(float f) {
        this.maxBound = f;
    }

    public final void ensureInit$material_release(Map<Float, ? extends T> newAnchors) {
        if (getAnchors$material_release().isEmpty()) {
            Float offset = SwipeableKt.getOffset(newAnchors, getCurrentValue());
            if (offset == null) {
                throw new IllegalArgumentException("The initial value must have an associated anchor.".toString());
            }
            this.offsetState.setFloatValue(offset.floatValue());
            this.absoluteOffset.setFloatValue(offset.floatValue());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a0, code lost:
    
        if (snapInternalToOffset(r10, r0) == r1) goto L86;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x021d  */
    /* JADX WARN: Type inference failed for: r10v21, types: [float] */
    /* JADX WARN: Type inference failed for: r10v76, types: [float] */
    /* JADX WARN: Type inference failed for: r10v78, types: [float] */
    /* JADX WARN: Type inference failed for: r10v89 */
    /* JADX WARN: Type inference failed for: r10v90 */
    /* JADX WARN: Type inference failed for: r10v91 */
    /* JADX WARN: Type inference failed for: r10v92 */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processNewAnchors$material_release(java.util.Map<java.lang.Float, ? extends T> r10, java.util.Map<java.lang.Float, ? extends T> r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 645
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SwipeableState.processNewAnchors$material_release(java.util.Map, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Function2<Float, Float, Float> getThresholds$material_release() {
        return (Function2) this.thresholds.getValue();
    }

    public final void setThresholds$material_release(Function2<? super Float, ? super Float, Float> function2) {
        this.thresholds.setValue(function2);
    }

    public final float getVelocityThreshold$material_release() {
        return this.velocityThreshold.getFloatValue();
    }

    public final void setVelocityThreshold$material_release(float f) {
        this.velocityThreshold.setFloatValue(f);
    }

    public final ResistanceConfig getResistance$material_release() {
        return (ResistanceConfig) this.resistance.getValue();
    }

    public final void setResistance$material_release(ResistanceConfig resistanceConfig) {
        this.resistance.setValue(resistanceConfig);
    }

    /* JADX INFO: renamed from: getDraggableState$material_release, reason: from getter */
    public final DraggableState getDraggableState() {
        return this.draggableState;
    }

    /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$snapInternalToOffset$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.material.SwipeableState$snapInternalToOffset$2", f = "Swipeable.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04592 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ float $target;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SwipeableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04592(float f, SwipeableState<T> swipeableState, Continuation<? super C04592> continuation) {
            super(2, continuation);
            this.$target = f;
            this.this$0 = swipeableState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04592 c04592 = new C04592(this.$target, this.this$0, continuation);
            c04592.L$0 = obj;
            return c04592;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
            return ((C04592) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((DragScope) this.L$0).dragBy(this.$target - ((SwipeableState) this.this$0).absoluteOffset.getFloatValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object snapInternalToOffset(float f, Continuation<? super Unit> continuation) {
        Object objDrag$default = DraggableState.drag$default(this.draggableState, null, new C04592(f, this, null), continuation, 1, null);
        return objDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDrag$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$animateInternalToOffset$2, reason: invalid class name */
    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.material.SwipeableState$animateInternalToOffset$2", f = "Swipeable.kt", i = {}, l = {229}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $spec;
        final /* synthetic */ float $target;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SwipeableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(SwipeableState<T> swipeableState, float f, AnimationSpec<Float> animationSpec, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = swipeableState;
            this.$target = f;
            this.$spec = animationSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$target, this.$spec, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final DragScope dragScope = (DragScope) this.L$0;
                    final Ref.FloatRef floatRef = new Ref.FloatRef();
                    floatRef.element = ((SwipeableState) this.this$0).absoluteOffset.getFloatValue();
                    ((SwipeableState) this.this$0).animationTarget.setValue(Boxing.boxFloat(this.$target));
                    this.this$0.setAnimationRunning(true);
                    this.label = 1;
                    if (Animatable.animateTo$default(AnimatableKt.Animatable$default(floatRef.element, 0.0f, 2, null), Boxing.boxFloat(this.$target), this.$spec, null, new Function1<Animatable<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material.SwipeableState.animateInternalToOffset.2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Animatable<Float, AnimationVector1D> animatable) {
                            invoke2(animatable);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Animatable<Float, AnimationVector1D> animatable) {
                            dragScope.dragBy(animatable.getValue().floatValue() - floatRef.element);
                            floatRef.element = animatable.getValue().floatValue();
                        }
                    }, this, 4, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                ((SwipeableState) this.this$0).animationTarget.setValue(null);
                this.this$0.setAnimationRunning(false);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                ((SwipeableState) this.this$0).animationTarget.setValue(null);
                this.this$0.setAnimationRunning(false);
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateInternalToOffset(float f, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        Object objDrag$default = DraggableState.drag$default(this.draggableState, null, new AnonymousClass2(this, f, animationSpec, null), continuation, 1, null);
        return objDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDrag$default : Unit.INSTANCE;
    }

    public final T getTargetValue() {
        float fComputeTarget;
        Float value = this.animationTarget.getValue();
        if (value != null) {
            fComputeTarget = value.floatValue();
        } else {
            float fFloatValue = getOffset().getValue().floatValue();
            Float offset = SwipeableKt.getOffset(getAnchors$material_release(), getCurrentValue());
            fComputeTarget = SwipeableKt.computeTarget(fFloatValue, offset != null ? offset.floatValue() : getOffset().getValue().floatValue(), getAnchors$material_release().keySet(), getThresholds$material_release(), 0.0f, Float.POSITIVE_INFINITY);
        }
        T t = getAnchors$material_release().get(Float.valueOf(fComputeTarget));
        return t == null ? getCurrentValue() : t;
    }

    public final SwipeProgress<T> getProgress() {
        Object currentValue;
        Object value;
        float fFloatValue;
        Pair pair;
        List listFindBounds = SwipeableKt.findBounds(getOffset().getValue().floatValue(), getAnchors$material_release().keySet());
        int size = listFindBounds.size();
        if (size == 0) {
            T currentValue2 = getCurrentValue();
            currentValue = getCurrentValue();
            value = currentValue2;
            fFloatValue = 1.0f;
        } else if (size == 1) {
            Object value2 = MapsKt.getValue(getAnchors$material_release(), listFindBounds.get(0));
            currentValue = MapsKt.getValue(getAnchors$material_release(), listFindBounds.get(0));
            fFloatValue = 1.0f;
            value = value2;
        } else {
            if (getDirection() > 0.0f) {
                pair = TuplesKt.to(listFindBounds.get(0), listFindBounds.get(1));
            } else {
                pair = TuplesKt.to(listFindBounds.get(1), listFindBounds.get(0));
            }
            float fFloatValue2 = ((Number) pair.component1()).floatValue();
            float fFloatValue3 = ((Number) pair.component2()).floatValue();
            value = MapsKt.getValue(getAnchors$material_release(), Float.valueOf(fFloatValue2));
            currentValue = MapsKt.getValue(getAnchors$material_release(), Float.valueOf(fFloatValue3));
            fFloatValue = (getOffset().getValue().floatValue() - fFloatValue2) / (fFloatValue3 - fFloatValue2);
        }
        return new SwipeProgress<>(value, currentValue, fFloatValue);
    }

    public final float getDirection() {
        Float offset = SwipeableKt.getOffset(getAnchors$material_release(), getCurrentValue());
        if (offset == null) {
            return 0.0f;
        }
        return Math.signum(getOffset().getValue().floatValue() - offset.floatValue());
    }

    /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$snapTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "anchors", "", "", "emit", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class C04602<T> implements FlowCollector {
        final /* synthetic */ T $targetValue;
        final /* synthetic */ SwipeableState<T> this$0;

        C04602(T t, SwipeableState<T> swipeableState) {
            this.$targetValue = t;
            this.this$0 = swipeableState;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
            return emit((Map) obj, (Continuation<? super Unit>) continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(java.util.Map<java.lang.Float, ? extends T> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof androidx.compose.material.SwipeableState$snapTo$2$emit$1
                if (r0 == 0) goto L14
                r0 = r6
                androidx.compose.material.SwipeableState$snapTo$2$emit$1 r0 = (androidx.compose.material.SwipeableState$snapTo$2$emit$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r6 = r0.label
                int r6 = r6 - r2
                r0.label = r6
                goto L19
            L14:
                androidx.compose.material.SwipeableState$snapTo$2$emit$1 r0 = new androidx.compose.material.SwipeableState$snapTo$2$emit$1
                r0.<init>(r4, r6)
            L19:
                java.lang.Object r6 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L36
                if (r2 != r3) goto L2e
                java.lang.Object r5 = r0.L$0
                androidx.compose.material.SwipeableState$snapTo$2 r5 = (androidx.compose.material.SwipeableState.C04602) r5
                kotlin.ResultKt.throwOnFailure(r6)
                goto L53
            L2e:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L36:
                kotlin.ResultKt.throwOnFailure(r6)
                T r6 = r4.$targetValue
                java.lang.Float r5 = androidx.compose.material.SwipeableKt.access$getOffset(r5, r6)
                if (r5 == 0) goto L5d
                androidx.compose.material.SwipeableState<T> r6 = r4.this$0
                float r5 = r5.floatValue()
                r0.L$0 = r4
                r0.label = r3
                java.lang.Object r5 = androidx.compose.material.SwipeableState.access$snapInternalToOffset(r6, r5, r0)
                if (r5 != r1) goto L52
                return r1
            L52:
                r5 = r4
            L53:
                androidx.compose.material.SwipeableState<T> r6 = r5.this$0
                T r5 = r5.$targetValue
                androidx.compose.material.SwipeableState.access$setCurrentValue(r6, r5)
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            L5d:
                java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
                java.lang.String r6 = "The target value must have an associated anchor."
                java.lang.String r6 = r6.toString()
                r5.<init>(r6)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SwipeableState.C04602.emit(java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public final Object snapTo(T t, Continuation<? super Unit> continuation) {
        Object objCollect = this.latestNonEmptyAnchorsFlow.collect(new C04602(t, this), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$animateTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "anchors", "", "", "emit", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class C04572<T> implements FlowCollector {
        final /* synthetic */ AnimationSpec<Float> $anim;
        final /* synthetic */ T $targetValue;
        final /* synthetic */ SwipeableState<T> this$0;

        C04572(T t, SwipeableState<T> swipeableState, AnimationSpec<Float> animationSpec) {
            this.$targetValue = t;
            this.this$0 = swipeableState;
            this.$anim = animationSpec;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
            return emit((Map) obj, (Continuation<? super Unit>) continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00b0  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00e9  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x011c  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(java.util.Map<java.lang.Float, ? extends T> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 296
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SwipeableState.C04572.emit(java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateTo$default(SwipeableState swipeableState, Object obj, AnimationSpec animationSpec, Continuation continuation, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: animateTo");
        }
        if ((i & 2) != 0) {
            animationSpec = swipeableState.animationSpec;
        }
        return swipeableState.animateTo(obj, animationSpec, continuation);
    }

    public final Object animateTo(T t, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        Object objCollect = this.latestNonEmptyAnchorsFlow.collect(new C04572(t, this, animationSpec), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public final Object performFling(final float f, Continuation<? super Unit> continuation) {
        Object objCollect = this.latestNonEmptyAnchorsFlow.collect(new FlowCollector(this) { // from class: androidx.compose.material.SwipeableState.performFling.2
            final /* synthetic */ SwipeableState<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Map) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(Map<Float, ? extends T> map, Continuation<? super Unit> continuation2) {
                Float offset = SwipeableKt.getOffset(map, this.this$0.getCurrentValue());
                Intrinsics.checkNotNull(offset);
                float fFloatValue = offset.floatValue();
                T t = map.get(Boxing.boxFloat(SwipeableKt.computeTarget(this.this$0.getOffset().getValue().floatValue(), fFloatValue, map.keySet(), this.this$0.getThresholds$material_release(), f, this.this$0.getVelocityThreshold$material_release())));
                if (t != null && this.this$0.getConfirmStateChange$material_release().invoke(t).booleanValue()) {
                    Object objAnimateTo$default = SwipeableState.animateTo$default(this.this$0, t, null, continuation2, 2, null);
                    return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
                }
                SwipeableState<T> swipeableState = this.this$0;
                Object objAnimateInternalToOffset = swipeableState.animateInternalToOffset(fFloatValue, swipeableState.getAnimationSpec$material_release(), continuation2);
                return objAnimateInternalToOffset == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateInternalToOffset : Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public final float performDrag(float delta) {
        float fCoerceIn = RangesKt.coerceIn(this.absoluteOffset.getFloatValue() + delta, this.minBound, this.maxBound) - this.absoluteOffset.getFloatValue();
        if (Math.abs(fCoerceIn) > 0.0f) {
            this.draggableState.dispatchRawDelta(fCoerceIn);
        }
        return fCoerceIn;
    }

    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JD\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\f0\u000b¨\u0006\r"}, d2 = {"Landroidx/compose/material/SwipeableState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material/SwipeableState;", ExifInterface.GPS_DIRECTION_TRUE, "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> Saver<SwipeableState<T>, T> Saver(final AnimationSpec<Float> animationSpec, final Function1<? super T, Boolean> confirmStateChange) {
            return SaverKt.Saver(new Function2<SaverScope, SwipeableState<T>, T>() { // from class: androidx.compose.material.SwipeableState$Companion$Saver$1
                @Override // kotlin.jvm.functions.Function2
                public final T invoke(SaverScope saverScope, SwipeableState<T> swipeableState) {
                    return swipeableState.getCurrentValue();
                }
            }, new Function1<T, SwipeableState<T>>() { // from class: androidx.compose.material.SwipeableState$Companion$Saver$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final SwipeableState<T> invoke(T t) {
                    return new SwipeableState<>(t, animationSpec, confirmStateChange);
                }
            });
        }
    }
}
