package androidx.compose.material.pullrefresh;

import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: PullRefreshState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B3\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\tH\u0002J\b\u00108\u001a\u00020\tH\u0002J\u0015\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020\tH\u0000¢\u0006\u0002\b;J\u0015\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\tH\u0000¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u0014H\u0000¢\u0006\u0002\b@J\u0015\u0010A\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\bBJ\u0015\u0010C\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0002\bDR+\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R+\u0010\u0015\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u00148B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R+\u0010\u001c\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0013\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0011R+\u0010 \u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u0013\u001a\u0004\b!\u0010\u000f\"\u0004\b\"\u0010\u0011R\u001b\u0010$\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b%\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010(\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010\u0013\u001a\u0004\b)\u0010\u000f\"\u0004\b*\u0010\u0011R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u000fR\u0011\u00100\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b1\u0010\u000fR\u0014\u00102\u001a\u00020\u00148@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\u0017R\u0014\u0010\n\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b4\u0010\u000f¨\u0006E"}, d2 = {"Landroidx/compose/material/pullrefresh/PullRefreshState;", "", "animationScope", "Lkotlinx/coroutines/CoroutineScope;", "onRefreshState", "Landroidx/compose/runtime/State;", "Lkotlin/Function0;", "", "refreshingOffset", "", "threshold", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/State;FF)V", "<set-?>", "_position", "get_position", "()F", "set_position", "(F)V", "_position$delegate", "Landroidx/compose/runtime/MutableFloatState;", "", "_refreshing", "get_refreshing", "()Z", "set_refreshing", "(Z)V", "_refreshing$delegate", "Landroidx/compose/runtime/MutableState;", "_refreshingOffset", "get_refreshingOffset", "set_refreshingOffset", "_refreshingOffset$delegate", "_threshold", "get_threshold", "set_threshold", "_threshold$delegate", "adjustedDistancePulled", "getAdjustedDistancePulled", "adjustedDistancePulled$delegate", "Landroidx/compose/runtime/State;", "distancePulled", "getDistancePulled", "setDistancePulled", "distancePulled$delegate", "mutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "position", "getPosition$material_release", "progress", "getProgress", "refreshing", "getRefreshing$material_release", "getThreshold$material_release", "animateIndicatorTo", "Lkotlinx/coroutines/Job;", TypedValues.CycleType.S_WAVE_OFFSET, "calculateIndicatorPosition", "onPull", "pullDelta", "onPull$material_release", "onRelease", "velocity", "onRelease$material_release", "setRefreshing", "setRefreshing$material_release", "setRefreshingOffset", "setRefreshingOffset$material_release", "setThreshold", "setThreshold$material_release", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PullRefreshState {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: _refreshingOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState _refreshingOffset;

    /* JADX INFO: renamed from: _threshold$delegate, reason: from kotlin metadata */
    private final MutableFloatState _threshold;
    private final CoroutineScope animationScope;
    private final State<Function0<Unit>> onRefreshState;

    /* JADX INFO: renamed from: adjustedDistancePulled$delegate, reason: from kotlin metadata */
    private final State adjustedDistancePulled = SnapshotStateKt.derivedStateOf(new Function0<Float>() { // from class: androidx.compose.material.pullrefresh.PullRefreshState$adjustedDistancePulled$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Float invoke() {
            return Float.valueOf(this.this$0.getDistancePulled() * 0.5f);
        }
    });

    /* JADX INFO: renamed from: _refreshing$delegate, reason: from kotlin metadata */
    private final MutableState _refreshing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: _position$delegate, reason: from kotlin metadata */
    private final MutableFloatState _position = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    /* JADX INFO: renamed from: distancePulled$delegate, reason: from kotlin metadata */
    private final MutableFloatState distancePulled = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
    private final MutatorMutex mutatorMutex = new MutatorMutex();

    /* JADX WARN: Multi-variable type inference failed */
    public PullRefreshState(CoroutineScope coroutineScope, State<? extends Function0<Unit>> state, float f, float f2) {
        this.animationScope = coroutineScope;
        this.onRefreshState = state;
        this._threshold = PrimitiveSnapshotStateKt.mutableFloatStateOf(f2);
        this._refreshingOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
    }

    public final float getProgress() {
        return getAdjustedDistancePulled() / getThreshold$material_release();
    }

    public final boolean getRefreshing$material_release() {
        return get_refreshing();
    }

    public final float getPosition$material_release() {
        return get_position();
    }

    public final float getThreshold$material_release() {
        return get_threshold();
    }

    private final float getAdjustedDistancePulled() {
        return ((Number) this.adjustedDistancePulled.getValue()).floatValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean get_refreshing() {
        return ((Boolean) this._refreshing.getValue()).booleanValue();
    }

    private final void set_refreshing(boolean z) {
        this._refreshing.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float get_position() {
        return this._position.getFloatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void set_position(float f) {
        this._position.setFloatValue(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getDistancePulled() {
        return this.distancePulled.getFloatValue();
    }

    private final void setDistancePulled(float f) {
        this.distancePulled.setFloatValue(f);
    }

    private final float get_threshold() {
        return this._threshold.getFloatValue();
    }

    private final void set_threshold(float f) {
        this._threshold.setFloatValue(f);
    }

    private final float get_refreshingOffset() {
        return this._refreshingOffset.getFloatValue();
    }

    private final void set_refreshingOffset(float f) {
        this._refreshingOffset.setFloatValue(f);
    }

    public final float onPull$material_release(float pullDelta) {
        if (get_refreshing()) {
            return 0.0f;
        }
        float fCoerceAtLeast = RangesKt.coerceAtLeast(getDistancePulled() + pullDelta, 0.0f);
        float distancePulled = fCoerceAtLeast - getDistancePulled();
        setDistancePulled(fCoerceAtLeast);
        set_position(calculateIndicatorPosition());
        return distancePulled;
    }

    public final float onRelease$material_release(float velocity) {
        if (getRefreshing$material_release()) {
            return 0.0f;
        }
        if (getAdjustedDistancePulled() > getThreshold$material_release()) {
            this.onRefreshState.getValue().invoke();
        }
        animateIndicatorTo(0.0f);
        if (getDistancePulled() == 0.0f || velocity < 0.0f) {
            velocity = 0.0f;
        }
        setDistancePulled(0.0f);
        return velocity;
    }

    public final void setRefreshing$material_release(boolean refreshing) {
        if (get_refreshing() != refreshing) {
            set_refreshing(refreshing);
            setDistancePulled(0.0f);
            animateIndicatorTo(refreshing ? get_refreshingOffset() : 0.0f);
        }
    }

    public final void setThreshold$material_release(float threshold) {
        set_threshold(threshold);
    }

    public final void setRefreshingOffset$material_release(float refreshingOffset) {
        if (get_refreshingOffset() == refreshingOffset) {
            return;
        }
        set_refreshingOffset(refreshingOffset);
        if (getRefreshing$material_release()) {
            animateIndicatorTo(refreshingOffset);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material.pullrefresh.PullRefreshState$animateIndicatorTo$1, reason: invalid class name */
    /* JADX INFO: compiled from: PullRefreshState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.material.pullrefresh.PullRefreshState$animateIndicatorTo$1", f = "PullRefreshState.kt", i = {}, l = {187}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ float $offset;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(float f, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$offset = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PullRefreshState.this.new AnonymousClass1(this.$offset, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.material.pullrefresh.PullRefreshState$animateIndicatorTo$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: PullRefreshState.kt */
        @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "androidx.compose.material.pullrefresh.PullRefreshState$animateIndicatorTo$1$1", f = "PullRefreshState.kt", i = {}, l = {188}, m = "invokeSuspend", n = {}, s = {})
        static final class C00941 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
            final /* synthetic */ float $offset;
            int label;
            final /* synthetic */ PullRefreshState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00941(PullRefreshState pullRefreshState, float f, Continuation<? super C00941> continuation) {
                super(1, continuation);
                this.this$0 = pullRefreshState;
                this.$offset = f;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new C00941(this.this$0, this.$offset, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation<? super Unit> continuation) {
                return ((C00941) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    float f = this.this$0.get_position();
                    float f2 = this.$offset;
                    final PullRefreshState pullRefreshState = this.this$0;
                    this.label = 1;
                    if (SuspendAnimationKt.animate$default(f, f2, 0.0f, null, new Function2<Float, Float, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshState.animateIndicatorTo.1.1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Float f3, Float f4) {
                            invoke(f3.floatValue(), f4.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(float f3, float f4) {
                            pullRefreshState.set_position(f3);
                        }
                    }, this, 12, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MutatorMutex.mutate$default(PullRefreshState.this.mutatorMutex, null, new C00941(PullRefreshState.this, this.$offset, null), this, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final Job animateIndicatorTo(float offset) {
        return BuildersKt__Builders_commonKt.launch$default(this.animationScope, null, null, new AnonymousClass1(offset, null), 3, null);
    }

    private final float calculateIndicatorPosition() {
        if (getAdjustedDistancePulled() <= getThreshold$material_release()) {
            return getAdjustedDistancePulled();
        }
        float fAbs = Math.abs(getProgress()) - 1.0f;
        if (fAbs < 0.0f) {
            fAbs = 0.0f;
        }
        if (fAbs > 2.0f) {
            fAbs = 2.0f;
        }
        return getThreshold$material_release() + (getThreshold$material_release() * (fAbs - (((float) Math.pow(fAbs, 2)) / 4)));
    }
}
