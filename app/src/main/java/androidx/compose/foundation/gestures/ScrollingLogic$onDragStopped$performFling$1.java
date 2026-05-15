package androidx.compose.foundation.gestures;

import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/Velocity;", "velocity"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$performFling$1", f = "Scrollable.kt", i = {0, 1, 1, 2, 2}, l = {745, 748, 751}, m = "invokeSuspend", n = {"velocity", "velocity", "available", "velocity", "velocityLeft"}, s = {"J$0", "J$0", "J$1", "J$0", "J$1"})
final class ScrollingLogic$onDragStopped$performFling$1 extends SuspendLambda implements Function2<Velocity, Continuation<? super Velocity>, Object> {
    /* synthetic */ long J$0;
    long J$1;
    int label;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScrollingLogic$onDragStopped$performFling$1(ScrollingLogic scrollingLogic, Continuation<? super ScrollingLogic$onDragStopped$performFling$1> continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScrollingLogic$onDragStopped$performFling$1 scrollingLogic$onDragStopped$performFling$1 = new ScrollingLogic$onDragStopped$performFling$1(this.this$0, continuation);
        scrollingLogic$onDragStopped$performFling$1.J$0 = ((Velocity) obj).getPackedValue();
        return scrollingLogic$onDragStopped$performFling$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Velocity velocity, Continuation<? super Velocity> continuation) {
        return m784invokesFctU(velocity.getPackedValue(), continuation);
    }

    /* JADX INFO: renamed from: invoke-sF-c-tU, reason: not valid java name */
    public final Object m784invokesFctU(long j, Continuation<? super Velocity> continuation) {
        return ((ScrollingLogic$onDragStopped$performFling$1) create(Velocity.m7255boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0092  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L3c
            if (r2 == r5) goto L34
            if (r2 == r4) goto L27
            if (r2 != r3) goto L1f
            long r1 = r0.J$1
            long r3 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r18)
            r12 = r1
            r2 = r18
            goto L93
        L1f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L27:
            long r4 = r0.J$1
            long r6 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r18)
            r15 = r6
            r7 = r4
            r5 = r15
            r2 = r18
            goto L72
        L34:
            long r5 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r18)
            r2 = r18
            goto L56
        L3c:
            kotlin.ResultKt.throwOnFailure(r18)
            long r6 = r0.J$0
            androidx.compose.foundation.gestures.ScrollingLogic r2 = r0.this$0
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r2 = androidx.compose.foundation.gestures.ScrollingLogic.access$getNestedScrollDispatcher$p(r2)
            r8 = r0
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r0.J$0 = r6
            r0.label = r5
            java.lang.Object r2 = r2.m5652dispatchPreFlingQWom1Mo(r6, r8)
            if (r2 != r1) goto L55
            goto L91
        L55:
            r5 = r6
        L56:
            androidx.compose.ui.unit.Velocity r2 = (androidx.compose.ui.unit.Velocity) r2
            long r7 = r2.getPackedValue()
            long r7 = androidx.compose.ui.unit.Velocity.m7267minusAH228Gc(r5, r7)
            androidx.compose.foundation.gestures.ScrollingLogic r2 = r0.this$0
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r0.J$0 = r5
            r0.J$1 = r7
            r0.label = r4
            java.lang.Object r2 = r2.m777doFlingAnimationQWom1Mo(r7, r9)
            if (r2 != r1) goto L72
            goto L91
        L72:
            androidx.compose.ui.unit.Velocity r2 = (androidx.compose.ui.unit.Velocity) r2
            long r12 = r2.getPackedValue()
            androidx.compose.foundation.gestures.ScrollingLogic r2 = r0.this$0
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r9 = androidx.compose.foundation.gestures.ScrollingLogic.access$getNestedScrollDispatcher$p(r2)
            long r10 = androidx.compose.ui.unit.Velocity.m7267minusAH228Gc(r7, r12)
            r14 = r0
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            r0.J$0 = r5
            r0.J$1 = r12
            r0.label = r3
            java.lang.Object r2 = r9.m5650dispatchPostFlingRZ2iAVY(r10, r12, r14)
            if (r2 != r1) goto L92
        L91:
            return r1
        L92:
            r3 = r5
        L93:
            androidx.compose.ui.unit.Velocity r2 = (androidx.compose.ui.unit.Velocity) r2
            long r1 = r2.getPackedValue()
            long r1 = androidx.compose.ui.unit.Velocity.m7267minusAH228Gc(r12, r1)
            long r1 = androidx.compose.ui.unit.Velocity.m7267minusAH228Gc(r3, r1)
            androidx.compose.ui.unit.Velocity r1 = androidx.compose.ui.unit.Velocity.m7255boximpl(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$performFling$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
