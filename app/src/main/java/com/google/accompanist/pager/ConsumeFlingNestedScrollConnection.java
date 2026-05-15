package com.google.accompanist.pager;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J)\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ-\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/google/accompanist/pager/ConsumeFlingNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "consumeHorizontal", "", "consumeVertical", "pagerState", "Lcom/google/accompanist/pager/PagerState;", "(ZZLcom/google/accompanist/pager/PagerState;)V", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", FirebaseAnalytics.Param.SOURCE, "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "pager_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class ConsumeFlingNestedScrollConnection implements NestedScrollConnection {
    private final boolean consumeHorizontal;
    private final boolean consumeVertical;
    private final PagerState pagerState;

    public ConsumeFlingNestedScrollConnection(boolean z, boolean z2, PagerState pagerState) {
        Intrinsics.checkNotNullParameter(pagerState, "pagerState");
        this.consumeHorizontal = z;
        this.consumeVertical = z2;
        this.pagerState = pagerState;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo767onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (NestedScrollSource.m5657equalsimpl0(source, NestedScrollSource.INSTANCE.m5666getFlingWNlRxjI())) {
            return Pager.m7548consume9KIMszo(available, this.consumeHorizontal, this.consumeVertical);
        }
        return Offset.INSTANCE.m4343getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo766onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
        long jM7275getZero9UxMQ8M;
        if (this.pagerState.getCurrentPageOffset() == 0.0f) {
            jM7275getZero9UxMQ8M = Pager.m7549consumeBMRW4eQ(j2, this.consumeHorizontal, this.consumeVertical);
        } else {
            jM7275getZero9UxMQ8M = Velocity.INSTANCE.m7275getZero9UxMQ8M();
        }
        return Velocity.m7255boximpl(jM7275getZero9UxMQ8M);
    }
}
