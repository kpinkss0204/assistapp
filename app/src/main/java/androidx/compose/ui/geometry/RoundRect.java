package androidx.compose.ui.geometry;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RoundRect.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 >2\u00020\u0001:\u0001>BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u0016\u0010!\u001a\u00020\bHÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u0011J\u0016\u0010#\u001a\u00020\bHÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b$\u0010\u0011J\u0016\u0010%\u001a\u00020\bHÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010\u0011J\u0016\u0010'\u001a\u00020\bHÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010\u0011J\u001b\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010.Jc\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bHÆ\u0001ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u0013\u00102\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J(\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00032\u0006\u00109\u001a\u00020\u00032\u0006\u0010:\u001a\u00020\u0003H\u0002J\b\u0010;\u001a\u00020\u0000H\u0002J\b\u0010<\u001a\u00020=H\u0016R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u000b\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\n\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0019\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0019\u0010\u0011R\u0019\u0010\t\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\u001b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006?"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect;", "", "left", "", "top", "right", "bottom", "topLeftCornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "topRightCornerRadius", "bottomRightCornerRadius", "bottomLeftCornerRadius", "(FFFFJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "_scaledRadiiRect", "getBottom", "()F", "getBottomLeftCornerRadius-kKHJgLs", "()J", "J", "getBottomRightCornerRadius-kKHJgLs", "height", "getHeight", "getLeft", "getRight", "getTop", "getTopLeftCornerRadius-kKHJgLs", "getTopRightCornerRadius-kKHJgLs", "width", "getWidth", "component1", "component2", "component3", "component4", "component5", "component5-kKHJgLs", "component6", "component6-kKHJgLs", "component7", "component7-kKHJgLs", "component8", "component8-kKHJgLs", "contains", "", "point", "Landroidx/compose/ui/geometry/Offset;", "contains-k-4lQ0M", "(J)Z", "copy", "copy-MDFrsts", "(FFFFJJJJ)Landroidx/compose/ui/geometry/RoundRect;", "equals", "other", "hashCode", "", "minRadius", "min", "radius1", "radius2", "limit", "scaledRadiiRect", "toString", "", "Companion", "ui-geometry_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class RoundRect {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RoundRect Zero = RoundRectKt.m4381RoundRectgG7oq9Y(0.0f, 0.0f, 0.0f, 0.0f, CornerRadius.INSTANCE.m4312getZerokKHJgLs());
    private RoundRect _scaledRadiiRect;
    private final float bottom;
    private final long bottomLeftCornerRadius;
    private final long bottomRightCornerRadius;
    private final float left;
    private final float right;
    private final float top;
    private final long topLeftCornerRadius;
    private final long topRightCornerRadius;

    public /* synthetic */ RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, j, j2, j3, j4);
    }

    /* JADX INFO: renamed from: copy-MDFrsts$default, reason: not valid java name */
    public static /* synthetic */ RoundRect m4368copyMDFrsts$default(RoundRect roundRect, float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = roundRect.left;
        }
        if ((i & 2) != 0) {
            f2 = roundRect.top;
        }
        if ((i & 4) != 0) {
            f3 = roundRect.right;
        }
        if ((i & 8) != 0) {
            f4 = roundRect.bottom;
        }
        if ((i & 16) != 0) {
            j = roundRect.topLeftCornerRadius;
        }
        if ((i & 32) != 0) {
            j2 = roundRect.topRightCornerRadius;
        }
        if ((i & 64) != 0) {
            j3 = roundRect.bottomRightCornerRadius;
        }
        if ((i & 128) != 0) {
            j4 = roundRect.bottomLeftCornerRadius;
        }
        long j5 = j4;
        long j6 = j3;
        long j7 = j2;
        long j8 = j;
        return roundRect.m4374copyMDFrsts(f, f2, f3, f4, j8, j7, j6, j5);
    }

    public static final RoundRect getZero() {
        return INSTANCE.getZero();
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getLeft() {
        return this.left;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getTop() {
        return this.top;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getRight() {
        return this.right;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: component5-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopLeftCornerRadius() {
        return this.topLeftCornerRadius;
    }

    /* JADX INFO: renamed from: component6-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopRightCornerRadius() {
        return this.topRightCornerRadius;
    }

    /* JADX INFO: renamed from: component7-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomRightCornerRadius() {
        return this.bottomRightCornerRadius;
    }

    /* JADX INFO: renamed from: component8-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomLeftCornerRadius() {
        return this.bottomLeftCornerRadius;
    }

    /* JADX INFO: renamed from: copy-MDFrsts, reason: not valid java name */
    public final RoundRect m4374copyMDFrsts(float left, float top, float right, float bottom, long topLeftCornerRadius, long topRightCornerRadius, long bottomRightCornerRadius, long bottomLeftCornerRadius) {
        return new RoundRect(left, top, right, bottom, topLeftCornerRadius, topRightCornerRadius, bottomRightCornerRadius, bottomLeftCornerRadius, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoundRect)) {
            return false;
        }
        RoundRect roundRect = (RoundRect) other;
        return Float.compare(this.left, roundRect.left) == 0 && Float.compare(this.top, roundRect.top) == 0 && Float.compare(this.right, roundRect.right) == 0 && Float.compare(this.bottom, roundRect.bottom) == 0 && CornerRadius.m4301equalsimpl0(this.topLeftCornerRadius, roundRect.topLeftCornerRadius) && CornerRadius.m4301equalsimpl0(this.topRightCornerRadius, roundRect.topRightCornerRadius) && CornerRadius.m4301equalsimpl0(this.bottomRightCornerRadius, roundRect.bottomRightCornerRadius) && CornerRadius.m4301equalsimpl0(this.bottomLeftCornerRadius, roundRect.bottomLeftCornerRadius);
    }

    public int hashCode() {
        return (((((((((((((Float.hashCode(this.left) * 31) + Float.hashCode(this.top)) * 31) + Float.hashCode(this.right)) * 31) + Float.hashCode(this.bottom)) * 31) + CornerRadius.m4304hashCodeimpl(this.topLeftCornerRadius)) * 31) + CornerRadius.m4304hashCodeimpl(this.topRightCornerRadius)) * 31) + CornerRadius.m4304hashCodeimpl(this.bottomRightCornerRadius)) * 31) + CornerRadius.m4304hashCodeimpl(this.bottomLeftCornerRadius);
    }

    private RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
        this.topLeftCornerRadius = j;
        this.topRightCornerRadius = j2;
        this.bottomRightCornerRadius = j3;
        this.bottomLeftCornerRadius = j4;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getTop() {
        return this.top;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getBottom() {
        return this.bottom;
    }

    public /* synthetic */ RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, (i & 16) != 0 ? CornerRadius.INSTANCE.m4312getZerokKHJgLs() : j, (i & 32) != 0 ? CornerRadius.INSTANCE.m4312getZerokKHJgLs() : j2, (i & 64) != 0 ? CornerRadius.INSTANCE.m4312getZerokKHJgLs() : j3, (i & 128) != 0 ? CornerRadius.INSTANCE.m4312getZerokKHJgLs() : j4, null);
    }

    /* JADX INFO: renamed from: getTopLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m4377getTopLeftCornerRadiuskKHJgLs() {
        return this.topLeftCornerRadius;
    }

    /* JADX INFO: renamed from: getTopRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m4378getTopRightCornerRadiuskKHJgLs() {
        return this.topRightCornerRadius;
    }

    /* JADX INFO: renamed from: getBottomRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m4376getBottomRightCornerRadiuskKHJgLs() {
        return this.bottomRightCornerRadius;
    }

    /* JADX INFO: renamed from: getBottomLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m4375getBottomLeftCornerRadiuskKHJgLs() {
        return this.bottomLeftCornerRadius;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    private final RoundRect scaledRadiiRect() {
        RoundRect roundRect = this._scaledRadiiRect;
        if (roundRect != null) {
            return roundRect;
        }
        float fMinRadius = minRadius(minRadius(minRadius(minRadius(1.0f, CornerRadius.m4303getYimpl(this.bottomLeftCornerRadius), CornerRadius.m4303getYimpl(this.topLeftCornerRadius), getHeight()), CornerRadius.m4302getXimpl(this.topLeftCornerRadius), CornerRadius.m4302getXimpl(this.topRightCornerRadius), getWidth()), CornerRadius.m4303getYimpl(this.topRightCornerRadius), CornerRadius.m4303getYimpl(this.bottomRightCornerRadius), getHeight()), CornerRadius.m4302getXimpl(this.bottomRightCornerRadius), CornerRadius.m4302getXimpl(this.bottomLeftCornerRadius), getWidth());
        RoundRect roundRect2 = new RoundRect(this.left * fMinRadius, this.top * fMinRadius, this.right * fMinRadius, this.bottom * fMinRadius, CornerRadiusKt.CornerRadius(CornerRadius.m4302getXimpl(this.topLeftCornerRadius) * fMinRadius, CornerRadius.m4303getYimpl(this.topLeftCornerRadius) * fMinRadius), CornerRadiusKt.CornerRadius(CornerRadius.m4302getXimpl(this.topRightCornerRadius) * fMinRadius, CornerRadius.m4303getYimpl(this.topRightCornerRadius) * fMinRadius), CornerRadiusKt.CornerRadius(CornerRadius.m4302getXimpl(this.bottomRightCornerRadius) * fMinRadius, CornerRadius.m4303getYimpl(this.bottomRightCornerRadius) * fMinRadius), CornerRadiusKt.CornerRadius(CornerRadius.m4302getXimpl(this.bottomLeftCornerRadius) * fMinRadius, CornerRadius.m4303getYimpl(this.bottomLeftCornerRadius) * fMinRadius), null);
        this._scaledRadiiRect = roundRect2;
        return roundRect2;
    }

    private final float minRadius(float min, float radius1, float radius2, float limit) {
        float f = radius1 + radius2;
        return (f <= limit || f == 0.0f) ? min : Math.min(min, limit / f);
    }

    /* JADX INFO: renamed from: contains-k-4lQ0M, reason: not valid java name */
    public final boolean m4373containsk4lQ0M(long point) {
        float fM4327getXimpl;
        float fM4328getYimpl;
        float fM4302getXimpl;
        float fM4303getYimpl;
        if (Offset.m4327getXimpl(point) < this.left || Offset.m4327getXimpl(point) >= this.right || Offset.m4328getYimpl(point) < this.top || Offset.m4328getYimpl(point) >= this.bottom) {
            return false;
        }
        RoundRect roundRectScaledRadiiRect = scaledRadiiRect();
        if (Offset.m4327getXimpl(point) < this.left + CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.topLeftCornerRadius) && Offset.m4328getYimpl(point) < this.top + CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.topLeftCornerRadius)) {
            fM4327getXimpl = (Offset.m4327getXimpl(point) - this.left) - CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.topLeftCornerRadius);
            fM4328getYimpl = (Offset.m4328getYimpl(point) - this.top) - CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.topLeftCornerRadius);
            fM4302getXimpl = CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.topLeftCornerRadius);
            fM4303getYimpl = CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.topLeftCornerRadius);
        } else if (Offset.m4327getXimpl(point) > this.right - CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.topRightCornerRadius) && Offset.m4328getYimpl(point) < this.top + CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.topRightCornerRadius)) {
            fM4327getXimpl = (Offset.m4327getXimpl(point) - this.right) + CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.topRightCornerRadius);
            fM4328getYimpl = (Offset.m4328getYimpl(point) - this.top) - CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.topRightCornerRadius);
            fM4302getXimpl = CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.topRightCornerRadius);
            fM4303getYimpl = CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.topRightCornerRadius);
        } else if (Offset.m4327getXimpl(point) > this.right - CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.bottomRightCornerRadius) && Offset.m4328getYimpl(point) > this.bottom - CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.bottomRightCornerRadius)) {
            fM4327getXimpl = (Offset.m4327getXimpl(point) - this.right) + CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.bottomRightCornerRadius);
            fM4328getYimpl = (Offset.m4328getYimpl(point) - this.bottom) + CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.bottomRightCornerRadius);
            fM4302getXimpl = CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.bottomRightCornerRadius);
            fM4303getYimpl = CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.bottomRightCornerRadius);
        } else {
            if (Offset.m4327getXimpl(point) >= this.left + CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius) || Offset.m4328getYimpl(point) <= this.bottom - CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius)) {
                return true;
            }
            fM4327getXimpl = (Offset.m4327getXimpl(point) - this.left) - CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius);
            fM4328getYimpl = (Offset.m4328getYimpl(point) - this.bottom) + CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius);
            fM4302getXimpl = CornerRadius.m4302getXimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius);
            fM4303getYimpl = CornerRadius.m4303getYimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius);
        }
        float f = fM4327getXimpl / fM4302getXimpl;
        float f2 = fM4328getYimpl / fM4303getYimpl;
        return (f * f) + (f2 * f2) <= 1.0f;
    }

    public String toString() {
        long j = this.topLeftCornerRadius;
        long j2 = this.topRightCornerRadius;
        long j3 = this.bottomRightCornerRadius;
        long j4 = this.bottomLeftCornerRadius;
        String str = GeometryUtilsKt.toStringAsFixed(this.left, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.top, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.right, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom, 1);
        return (CornerRadius.m4301equalsimpl0(j, j2) && CornerRadius.m4301equalsimpl0(j2, j3) && CornerRadius.m4301equalsimpl0(j3, j4)) ? CornerRadius.m4302getXimpl(j) == CornerRadius.m4303getYimpl(j) ? "RoundRect(rect=" + str + ", radius=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m4302getXimpl(j), 1) + ')' : "RoundRect(rect=" + str + ", x=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m4302getXimpl(j), 1) + ", y=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m4303getYimpl(j), 1) + ')' : "RoundRect(rect=" + str + ", topLeft=" + ((Object) CornerRadius.m4308toStringimpl(j)) + ", topRight=" + ((Object) CornerRadius.m4308toStringimpl(j2)) + ", bottomRight=" + ((Object) CornerRadius.m4308toStringimpl(j3)) + ", bottomLeft=" + ((Object) CornerRadius.m4308toStringimpl(j4)) + ')';
    }

    /* JADX INFO: compiled from: RoundRect.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect$Companion;", "", "()V", "Zero", "Landroidx/compose/ui/geometry/RoundRect;", "getZero$annotations", "getZero", "()Landroidx/compose/ui/geometry/RoundRect;", "ui-geometry_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getZero$annotations() {
        }

        private Companion() {
        }

        public final RoundRect getZero() {
            return RoundRect.Zero;
        }
    }
}
