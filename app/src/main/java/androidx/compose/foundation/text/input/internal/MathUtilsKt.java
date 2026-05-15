package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: MathUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0080\b\u001a\u001e\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a&\u0010\f\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0000ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a#\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0080\b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"addExactOrElse", "", "right", "defaultValue", "Lkotlin/Function0;", "distanceSquaredToClosestCornerFromOutside", "", "Landroidx/compose/ui/geometry/Offset;", "rect", "Landroidx/compose/ui/geometry/Rect;", "distanceSquaredToClosestCornerFromOutside-3MmeM6k", "(JLandroidx/compose/ui/geometry/Rect;)F", "findClosestRect", "rect1", "rect2", "findClosestRect-9KIMszo", "(JLandroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;)I", "subtractExactOrElse", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MathUtilsKt {
    public static final int addExactOrElse(int i, int i2, Function0<Integer> function0) {
        int i3 = i + i2;
        return ((i ^ i3) & (i2 ^ i3)) < 0 ? function0.invoke().intValue() : i3;
    }

    public static final int subtractExactOrElse(int i, int i2, Function0<Integer> function0) {
        int i3 = i - i2;
        return ((i ^ i3) & (i2 ^ i)) < 0 ? function0.invoke().intValue() : i3;
    }

    /* JADX INFO: renamed from: findClosestRect-9KIMszo, reason: not valid java name */
    public static final int m1460findClosestRect9KIMszo(long j, Rect rect, Rect rect2) {
        float fM1459distanceSquaredToClosestCornerFromOutside3MmeM6k = m1459distanceSquaredToClosestCornerFromOutside3MmeM6k(j, rect);
        float fM1459distanceSquaredToClosestCornerFromOutside3MmeM6k2 = m1459distanceSquaredToClosestCornerFromOutside3MmeM6k(j, rect2);
        if (fM1459distanceSquaredToClosestCornerFromOutside3MmeM6k == fM1459distanceSquaredToClosestCornerFromOutside3MmeM6k2) {
            return 0;
        }
        return fM1459distanceSquaredToClosestCornerFromOutside3MmeM6k < fM1459distanceSquaredToClosestCornerFromOutside3MmeM6k2 ? -1 : 1;
    }

    /* JADX INFO: renamed from: distanceSquaredToClosestCornerFromOutside-3MmeM6k, reason: not valid java name */
    private static final float m1459distanceSquaredToClosestCornerFromOutside3MmeM6k(long j, Rect rect) {
        if (SelectionManagerKt.m1664containsInclusiveUv8p0NA(rect, j)) {
            return 0.0f;
        }
        float fM4326getDistanceSquaredimpl = Offset.m4326getDistanceSquaredimpl(Offset.m4331minusMKHz9U(rect.m4362getTopLeftF1C5BW0(), j));
        if (fM4326getDistanceSquaredimpl >= Float.MAX_VALUE) {
            fM4326getDistanceSquaredimpl = Float.MAX_VALUE;
        }
        float fM4326getDistanceSquaredimpl2 = Offset.m4326getDistanceSquaredimpl(Offset.m4331minusMKHz9U(rect.m4363getTopRightF1C5BW0(), j));
        if (fM4326getDistanceSquaredimpl2 < fM4326getDistanceSquaredimpl) {
            fM4326getDistanceSquaredimpl = fM4326getDistanceSquaredimpl2;
        }
        float fM4326getDistanceSquaredimpl3 = Offset.m4326getDistanceSquaredimpl(Offset.m4331minusMKHz9U(rect.m4355getBottomLeftF1C5BW0(), j));
        if (fM4326getDistanceSquaredimpl3 < fM4326getDistanceSquaredimpl) {
            fM4326getDistanceSquaredimpl = fM4326getDistanceSquaredimpl3;
        }
        float fM4326getDistanceSquaredimpl4 = Offset.m4326getDistanceSquaredimpl(Offset.m4331minusMKHz9U(rect.m4356getBottomRightF1C5BW0(), j));
        return fM4326getDistanceSquaredimpl4 < fM4326getDistanceSquaredimpl ? fM4326getDistanceSquaredimpl4 : fM4326getDistanceSquaredimpl;
    }
}
