package androidx.compose.foundation.gestures.snapping;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.pager.PageInfo;
import androidx.compose.foundation.pager.PagerLayoutInfo;
import androidx.compose.foundation.pager.PagerLayoutInfoKt;
import androidx.compose.foundation.pager.PagerSnapDistance;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.ui.geometry.Offset;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PagerSnapLayoutInfoProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001e\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H\u0000\u001a8\u0010\u0006\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0000\u001a\u0017\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0082\b\u001a\f\u0010\u0014\u001a\u00020\b*\u00020\u0003H\u0002\u001a\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\u0002\u001a\f\u0010\u0017\u001a\u00020\u0016*\u00020\u0003H\u0002¨\u0006\u0018"}, d2 = {"SnapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "pagerState", "Landroidx/compose/foundation/pager/PagerState;", "pagerSnapDistance", "Landroidx/compose/foundation/pager/PagerSnapDistance;", "calculateFinalSnappingBound", "Lkotlin/Function3;", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "snapPositionalThreshold", "flingVelocity", "lowerBoundOffset", "upperBoundOffset", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "dragGestureDelta", "isLtrDragging", "", "isScrollingForward", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PagerSnapLayoutInfoProviderKt {
    private static final void debugLog(Function0<String> function0) {
    }

    public static final SnapLayoutInfoProvider SnapLayoutInfoProvider(final PagerState pagerState, final PagerSnapDistance pagerSnapDistance, final Function3<? super Float, ? super Float, ? super Float, Float> function3) {
        return new SnapLayoutInfoProvider() { // from class: androidx.compose.foundation.gestures.snapping.PagerSnapLayoutInfoProviderKt.SnapLayoutInfoProvider.1
            public final boolean isValidDistance(float f) {
                return (f == Float.POSITIVE_INFINITY || f == Float.NEGATIVE_INFINITY) ? false : true;
            }

            public final PagerLayoutInfo getLayoutInfo() {
                return pagerState.getLayoutInfo();
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateSnapOffset(float velocity) {
                Pair<Float, Float> pairSearchForSnappingBounds = searchForSnappingBounds(pagerState.getLayoutInfo().getSnapPosition());
                float fFloatValue = pairSearchForSnappingBounds.component1().floatValue();
                float fFloatValue2 = pairSearchForSnappingBounds.component2().floatValue();
                float fFloatValue3 = function3.invoke(Float.valueOf(velocity), Float.valueOf(fFloatValue), Float.valueOf(fFloatValue2)).floatValue();
                if (fFloatValue3 != fFloatValue && fFloatValue3 != fFloatValue2 && fFloatValue3 != 0.0f) {
                    throw new IllegalStateException(("Final Snapping Offset Should Be one of " + fFloatValue + ", " + fFloatValue2 + " or 0.0").toString());
                }
                if (isValidDistance(fFloatValue3)) {
                    return fFloatValue3;
                }
                return 0.0f;
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateApproachOffset(float velocity, float decayOffset) {
                int firstVisiblePage;
                int pageSize$foundation_release = pagerState.getPageSize$foundation_release() + pagerState.getPageSpacing$foundation_release();
                if (pageSize$foundation_release == 0) {
                    return 0.0f;
                }
                if (velocity < 0.0f) {
                    firstVisiblePage = pagerState.getFirstVisiblePage() + 1;
                } else {
                    firstVisiblePage = pagerState.getFirstVisiblePage();
                }
                int i = firstVisiblePage;
                int iCoerceAtLeast = RangesKt.coerceAtLeast(Math.abs((RangesKt.coerceIn(pagerSnapDistance.calculateTargetPage(i, RangesKt.coerceIn(((int) (decayOffset / pageSize$foundation_release)) + i, 0, pagerState.getPageCount()), velocity, pagerState.getPageSize$foundation_release(), pagerState.getPageSpacing$foundation_release()), 0, pagerState.getPageCount()) - i) * pageSize$foundation_release) - pageSize$foundation_release, 0);
                return iCoerceAtLeast == 0 ? iCoerceAtLeast : iCoerceAtLeast * Math.signum(velocity);
            }

            private final Pair<Float, Float> searchForSnappingBounds(SnapPosition snapPosition) {
                float f;
                List<PageInfo> visiblePagesInfo = getLayoutInfo().getVisiblePagesInfo();
                PagerState pagerState2 = pagerState;
                int size = visiblePagesInfo.size();
                float f2 = Float.NEGATIVE_INFINITY;
                float f3 = Float.POSITIVE_INFINITY;
                int i = 0;
                while (true) {
                    f = 0.0f;
                    if (i >= size) {
                        break;
                    }
                    PageInfo pageInfo = visiblePagesInfo.get(i);
                    float fCalculateDistanceToDesiredSnapPosition = SnapPositionKt.calculateDistanceToDesiredSnapPosition(PagerLayoutInfoKt.getMainAxisViewportSize(getLayoutInfo()), getLayoutInfo().getBeforeContentPadding(), getLayoutInfo().getAfterContentPadding(), getLayoutInfo().getPageSize(), pageInfo.getOffset(), pageInfo.getIndex(), snapPosition, pagerState2.getPageCount());
                    if (fCalculateDistanceToDesiredSnapPosition <= 0.0f && fCalculateDistanceToDesiredSnapPosition > f2) {
                        f2 = fCalculateDistanceToDesiredSnapPosition;
                    }
                    if (fCalculateDistanceToDesiredSnapPosition >= 0.0f && fCalculateDistanceToDesiredSnapPosition < f3) {
                        f3 = fCalculateDistanceToDesiredSnapPosition;
                    }
                    i++;
                }
                if (f2 == Float.NEGATIVE_INFINITY) {
                    f2 = f3;
                }
                if (f3 == Float.POSITIVE_INFINITY) {
                    f3 = f2;
                }
                boolean z = PagerSnapLayoutInfoProviderKt.dragGestureDelta(pagerState) == 0.0f;
                if (!pagerState.getCanScrollForward()) {
                    if (z || !PagerSnapLayoutInfoProviderKt.isScrollingForward(pagerState)) {
                        f3 = 0.0f;
                    } else {
                        f2 = 0.0f;
                        f3 = 0.0f;
                    }
                }
                if (pagerState.getCanScrollBackward()) {
                    f = f2;
                } else if (!z && !PagerSnapLayoutInfoProviderKt.isScrollingForward(pagerState)) {
                    f3 = 0.0f;
                }
                return TuplesKt.to(Float.valueOf(f), Float.valueOf(f3));
            }
        };
    }

    private static final boolean isLtrDragging(PagerState pagerState) {
        return dragGestureDelta(pagerState) > 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isScrollingForward(PagerState pagerState) {
        boolean reverseLayout = pagerState.getLayoutInfo().getReverseLayout();
        if (isLtrDragging(pagerState) && reverseLayout) {
            return true;
        }
        return (isLtrDragging(pagerState) || reverseLayout) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float dragGestureDelta(PagerState pagerState) {
        if (pagerState.getLayoutInfo().getOrientation() == Orientation.Horizontal) {
            return Offset.m4327getXimpl(pagerState.m1213getUpDownDifferenceF1C5BW0$foundation_release());
        }
        return Offset.m4328getYimpl(pagerState.m1213getUpDownDifferenceF1C5BW0$foundation_release());
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0092 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final float calculateFinalSnappingBound(androidx.compose.foundation.pager.PagerState r4, androidx.compose.ui.unit.LayoutDirection r5, float r6, float r7, float r8, float r9) {
        /*
            androidx.compose.foundation.pager.PagerLayoutInfo r0 = r4.getLayoutInfo()
            androidx.compose.foundation.gestures.Orientation r0 = r0.getOrientation()
            androidx.compose.foundation.gestures.Orientation r1 = androidx.compose.foundation.gestures.Orientation.Vertical
            if (r0 != r1) goto L11
            boolean r5 = isScrollingForward(r4)
            goto L23
        L11:
            androidx.compose.ui.unit.LayoutDirection r0 = androidx.compose.ui.unit.LayoutDirection.Ltr
            if (r5 != r0) goto L1a
            boolean r5 = isScrollingForward(r4)
            goto L23
        L1a:
            boolean r5 = isScrollingForward(r4)
            if (r5 != 0) goto L22
            r5 = 1
            goto L23
        L22:
            r5 = 0
        L23:
            androidx.compose.foundation.pager.PagerLayoutInfo r0 = r4.getLayoutInfo()
            int r0 = r0.getPageSize()
            r1 = 0
            if (r0 != 0) goto L30
            r2 = r1
            goto L36
        L30:
            float r2 = dragGestureDelta(r4)
            float r0 = (float) r0
            float r2 = r2 / r0
        L36:
            int r0 = (int) r2
            float r0 = (float) r0
            float r0 = r2 - r0
            androidx.compose.ui.unit.Density r3 = r4.getDensity()
            int r7 = androidx.compose.foundation.gestures.snapping.LazyListSnapLayoutInfoProviderKt.calculateFinalSnappingItem(r3, r7)
            androidx.compose.foundation.gestures.snapping.FinalSnappingItem$Companion r3 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.INSTANCE
            int r3 = r3.m808getClosestItembbeMdSM()
            boolean r3 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.m804equalsimpl0(r7, r3)
            if (r3 == 0) goto L79
            float r7 = java.lang.Math.abs(r0)
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 <= 0) goto L59
            if (r5 == 0) goto L92
            goto L85
        L59:
            float r6 = java.lang.Math.abs(r2)
            float r4 = r4.getPositionThresholdFraction$foundation_release()
            float r4 = java.lang.Math.abs(r4)
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 < 0) goto L6c
            if (r5 == 0) goto L85
            goto L92
        L6c:
            float r4 = java.lang.Math.abs(r8)
            float r5 = java.lang.Math.abs(r9)
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 >= 0) goto L85
            goto L92
        L79:
            androidx.compose.foundation.gestures.snapping.FinalSnappingItem$Companion r4 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.INSTANCE
            int r4 = r4.m809getNextItembbeMdSM()
            boolean r4 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.m804equalsimpl0(r7, r4)
            if (r4 == 0) goto L86
        L85:
            return r9
        L86:
            androidx.compose.foundation.gestures.snapping.FinalSnappingItem$Companion r4 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.INSTANCE
            int r4 = r4.m810getPreviousItembbeMdSM()
            boolean r4 = androidx.compose.foundation.gestures.snapping.FinalSnappingItem.m804equalsimpl0(r7, r4)
            if (r4 == 0) goto L93
        L92:
            return r8
        L93:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.PagerSnapLayoutInfoProviderKt.calculateFinalSnappingBound(androidx.compose.foundation.pager.PagerState, androidx.compose.ui.unit.LayoutDirection, float, float, float, float):float");
    }
}
