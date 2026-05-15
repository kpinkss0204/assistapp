package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Pager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J#\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\"\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u001c\u0010\u001a\u001a\u00020\f*\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u001e*\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Landroidx/compose/foundation/pager/DefaultPagerNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "state", "Landroidx/compose/foundation/pager/PagerState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/gestures/Orientation;)V", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "getState", "()Landroidx/compose/foundation/pager/PagerState;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", FirebaseAnalytics.Param.SOURCE, "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "consumeOnOrientation", "consumeOnOrientation-QWom1Mo", "(JLandroidx/compose/foundation/gestures/Orientation;)J", "mainAxis", "", "mainAxis-k-4lQ0M", "(J)F", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class DefaultPagerNestedScrollConnection implements NestedScrollConnection {
    private final Orientation orientation;
    private final PagerState state;

    public DefaultPagerNestedScrollConnection(PagerState pagerState, Orientation orientation) {
        this.state = pagerState;
        this.orientation = orientation;
    }

    public final PagerState getState() {
        return this.state;
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    /* JADX INFO: renamed from: consumeOnOrientation-QWom1Mo, reason: not valid java name */
    public final long m1197consumeOnOrientationQWom1Mo(long j, Orientation orientation) {
        if (orientation == Orientation.Vertical) {
            return Velocity.m7260copyOhffZ5M$default(j, 0.0f, 0.0f, 2, null);
        }
        return Velocity.m7260copyOhffZ5M$default(j, 0.0f, 0.0f, 1, null);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo1041onPreScrollOzD1aCk(long available, int source) {
        if (NestedScrollSource.m5657equalsimpl0(source, NestedScrollSource.INSTANCE.m5669getUserInputWNlRxjI()) && Math.abs(this.state.getCurrentPageOffsetFraction()) > 1.0E-6d) {
            float currentPageOffsetFraction = this.state.getCurrentPageOffsetFraction() * this.state.getPageSize$foundation_release();
            float pageSize = ((this.state.getLayoutInfo().getPageSize() + this.state.getLayoutInfo().getPageSpacing()) * (-Math.signum(this.state.getCurrentPageOffsetFraction()))) + currentPageOffsetFraction;
            if (this.state.getCurrentPageOffsetFraction() > 0.0f) {
                pageSize = currentPageOffsetFraction;
                currentPageOffsetFraction = pageSize;
            }
            float fM4328getYimpl = -this.state.dispatchRawDelta(-RangesKt.coerceIn(this.orientation == Orientation.Horizontal ? Offset.m4327getXimpl(available) : Offset.m4328getYimpl(available), currentPageOffsetFraction, pageSize));
            float fM4327getXimpl = this.orientation == Orientation.Horizontal ? fM4328getYimpl : Offset.m4327getXimpl(available);
            if (this.orientation != Orientation.Vertical) {
                fM4328getYimpl = Offset.m4328getYimpl(available);
            }
            return Offset.m4320copydBAh8RU(available, fM4327getXimpl, fM4328getYimpl);
        }
        return Offset.INSTANCE.m4343getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo767onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (NestedScrollSource.m5657equalsimpl0(source, NestedScrollSource.INSTANCE.m5668getSideEffectWNlRxjI()) && m1196mainAxisk4lQ0M(available) != 0.0f) {
            throw new CancellationException();
        }
        return Offset.INSTANCE.m4343getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo766onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
        return Velocity.m7255boximpl(m1197consumeOnOrientationQWom1Mo(j2, this.orientation));
    }

    /* JADX INFO: renamed from: mainAxis-k-4lQ0M, reason: not valid java name */
    private final float m1196mainAxisk4lQ0M(long j) {
        return this.orientation == Orientation.Horizontal ? Offset.m4327getXimpl(j) : Offset.m4328getYimpl(j);
    }
}
