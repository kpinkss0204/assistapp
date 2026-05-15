package androidx.compose.material3;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"androidx/compose/material3/ExitAlwaysScrollBehavior$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", FirebaseAnalytics.Param.SOURCE, "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ExitAlwaysScrollBehavior$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ ExitAlwaysScrollBehavior this$0;

    ExitAlwaysScrollBehavior$nestedScrollConnection$1(ExitAlwaysScrollBehavior exitAlwaysScrollBehavior) {
        this.this$0 = exitAlwaysScrollBehavior;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo767onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.this$0.getCanScroll().invoke().booleanValue()) {
            return Offset.INSTANCE.m4343getZeroF1C5BW0();
        }
        BottomAppBarState state = this.this$0.getState();
        state.setContentOffset(state.getContentOffset() + Offset.m4328getYimpl(consumed));
        if ((this.this$0.getState().getHeightOffset() == 0.0f || this.this$0.getState().getHeightOffset() == this.this$0.getState().getHeightOffsetLimit()) && Offset.m4328getYimpl(consumed) == 0.0f && Offset.m4328getYimpl(available) > 0.0f) {
            this.this$0.getState().setContentOffset(0.0f);
        }
        this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m4328getYimpl(consumed));
        return Offset.INSTANCE.m4343getZeroF1C5BW0();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object mo766onPostFlingRZ2iAVY(long r9, long r11, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r13) {
        /*
            r8 = this;
            boolean r0 = r13 instanceof androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1
            if (r0 == 0) goto L14
            r0 = r13
            androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 r0 = (androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 r0 = new androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1
            r0.<init>(r8, r13)
        L19:
            r6 = r0
            java.lang.Object r13 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r7 = 2
            r2 = 1
            if (r1 == 0) goto L42
            if (r1 == r2) goto L38
            if (r1 != r7) goto L30
            long r9 = r6.J$0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L82
        L30:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L38:
            long r11 = r6.J$0
            java.lang.Object r9 = r6.L$0
            androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1 r9 = (androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1) r9
            kotlin.ResultKt.throwOnFailure(r13)
            goto L57
        L42:
            kotlin.ResultKt.throwOnFailure(r13)
            r6.L$0 = r8
            r6.J$0 = r11
            r6.label = r2
            r1 = r8
            r2 = r9
            r4 = r11
            java.lang.Object r13 = super.mo766onPostFlingRZ2iAVY(r2, r4, r6)
            if (r13 != r0) goto L55
            goto L80
        L55:
            r9 = r8
            r11 = r4
        L57:
            androidx.compose.ui.unit.Velocity r13 = (androidx.compose.ui.unit.Velocity) r13
            long r1 = r13.getPackedValue()
            androidx.compose.material3.ExitAlwaysScrollBehavior r10 = r9.this$0
            androidx.compose.material3.BottomAppBarState r10 = r10.getState()
            float r11 = androidx.compose.ui.unit.Velocity.m7265getYimpl(r11)
            androidx.compose.material3.ExitAlwaysScrollBehavior r12 = r9.this$0
            androidx.compose.animation.core.DecayAnimationSpec r12 = r12.getFlingAnimationSpec()
            androidx.compose.material3.ExitAlwaysScrollBehavior r9 = r9.this$0
            androidx.compose.animation.core.AnimationSpec r9 = r9.getSnapAnimationSpec()
            r13 = 0
            r6.L$0 = r13
            r6.J$0 = r1
            r6.label = r7
            java.lang.Object r13 = androidx.compose.material3.AppBarKt.access$settleAppBarBottom(r10, r11, r12, r9, r6)
            if (r13 != r0) goto L81
        L80:
            return r0
        L81:
            r9 = r1
        L82:
            androidx.compose.ui.unit.Velocity r13 = (androidx.compose.ui.unit.Velocity) r13
            long r11 = r13.getPackedValue()
            long r9 = androidx.compose.ui.unit.Velocity.m7268plusAH228Gc(r9, r11)
            androidx.compose.ui.unit.Velocity r9 = androidx.compose.ui.unit.Velocity.m7255boximpl(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1.mo766onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
