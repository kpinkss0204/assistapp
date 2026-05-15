package androidx.compose.ui.platform;

import android.graphics.Outline;
import android.os.Build;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OutlineResolver.android.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\u0018\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001dø\u0001\u0000¢\u0006\u0004\b,\u0010-J:\u0010.\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010/\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\b2\u0006\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001aø\u0001\u0000¢\u0006\u0004\b2\u00103J\b\u00104\u001a\u00020'H\u0002J\u0010\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020\u000eH\u0002J\u0010\u00107\u001a\u00020'2\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u00020'2\u0006\u0010;\u001a\u00020#H\u0002J0\u0010<\u001a\u00020\b*\u0004\u0018\u00010#2\u0006\u0010=\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u001fH\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010@R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000bR\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\u00020\u001aX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u001dX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u001bR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006A"}, d2 = {"Landroidx/compose/ui/platform/OutlineResolver;", "", "()V", "androidOutline", "Landroid/graphics/Outline;", "getAndroidOutline", "()Landroid/graphics/Outline;", "<set-?>", "", "cacheIsDirty", "getCacheIsDirty$ui_release", "()Z", "cachedOutline", "cachedRrectPath", "Landroidx/compose/ui/graphics/Path;", "clipPath", "getClipPath", "()Landroidx/compose/ui/graphics/Path;", "isSupportedOutline", "outline", "Landroidx/compose/ui/graphics/Outline;", "outlineClipSupported", "getOutlineClipSupported", "outlineNeeded", "outlinePath", "rectSize", "Landroidx/compose/ui/geometry/Size;", "J", "rectTopLeft", "Landroidx/compose/ui/geometry/Offset;", "roundedCornerRadius", "", "tmpOpPath", "tmpPath", "tmpRoundRect", "Landroidx/compose/ui/geometry/RoundRect;", "tmpTouchPointPath", "usePathForClip", "clipToOutline", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "isInOutline", "position", "isInOutline-k-4lQ0M", "(J)Z", "update", "alpha", "elevation", "size", "update-S_szKao", "(Landroidx/compose/ui/graphics/Outline;FZFJ)Z", "updateCache", "updateCacheWithPath", "composePath", "updateCacheWithRect", "rect", "Landroidx/compose/ui/geometry/Rect;", "updateCacheWithRoundRect", "roundRect", "isSameBounds", TypedValues.CycleType.S_WAVE_OFFSET, "radius", "isSameBounds-4L21HEs", "(Landroidx/compose/ui/geometry/RoundRect;JJF)Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class OutlineResolver {
    public static final int $stable = 8;
    private boolean cacheIsDirty;
    private final Outline cachedOutline;
    private Path cachedRrectPath;
    private boolean isSupportedOutline = true;
    private androidx.compose.ui.graphics.Outline outline;
    private boolean outlineNeeded;
    private Path outlinePath;
    private long rectSize;
    private long rectTopLeft;
    private float roundedCornerRadius;
    private Path tmpOpPath;
    private Path tmpPath;
    private RoundRect tmpRoundRect;
    private Path tmpTouchPointPath;
    private boolean usePathForClip;

    public OutlineResolver() {
        Outline outline = new Outline();
        outline.setAlpha(1.0f);
        this.cachedOutline = outline;
        this.rectTopLeft = Offset.INSTANCE.m4343getZeroF1C5BW0();
        this.rectSize = Size.INSTANCE.m4405getZeroNHjbRc();
    }

    /* JADX INFO: renamed from: getCacheIsDirty$ui_release, reason: from getter */
    public final boolean getCacheIsDirty() {
        return this.cacheIsDirty;
    }

    public final Outline getAndroidOutline() {
        updateCache();
        if (this.outlineNeeded && this.isSupportedOutline) {
            return this.cachedOutline;
        }
        return null;
    }

    public final boolean getOutlineClipSupported() {
        return !this.usePathForClip;
    }

    public final Path getClipPath() {
        updateCache();
        return this.outlinePath;
    }

    /* JADX INFO: renamed from: update-S_szKao, reason: not valid java name */
    public final boolean m6294updateS_szKao(androidx.compose.ui.graphics.Outline outline, float alpha, boolean clipToOutline, float elevation, long size) {
        this.cachedOutline.setAlpha(alpha);
        boolean zAreEqual = Intrinsics.areEqual(this.outline, outline);
        boolean z = !zAreEqual;
        if (!zAreEqual) {
            this.outline = outline;
            this.cacheIsDirty = true;
        }
        this.rectSize = size;
        boolean z2 = outline != null && (clipToOutline || elevation > 0.0f);
        if (this.outlineNeeded != z2) {
            this.outlineNeeded = z2;
            this.cacheIsDirty = true;
        }
        return z;
    }

    /* JADX INFO: renamed from: isInOutline-k-4lQ0M, reason: not valid java name */
    public final boolean m6293isInOutlinek4lQ0M(long position) {
        androidx.compose.ui.graphics.Outline outline;
        if (this.outlineNeeded && (outline = this.outline) != null) {
            return ShapeContainingUtilKt.isInOutline(outline, Offset.m4327getXimpl(position), Offset.m4328getYimpl(position), this.tmpTouchPointPath, this.tmpOpPath);
        }
        return true;
    }

    public final void clipToOutline(Canvas canvas) {
        OutlineResolver outlineResolver;
        Path clipPath = getClipPath();
        if (clipPath != null) {
            Canvas.m4539clipPathmtrdDE$default(canvas, clipPath, 0, 2, null);
            return;
        }
        float f = this.roundedCornerRadius;
        if (f > 0.0f) {
            Path Path = this.tmpPath;
            RoundRect roundRect = this.tmpRoundRect;
            if (Path != null) {
                outlineResolver = this;
                if (!m6292isSameBounds4L21HEs(roundRect, this.rectTopLeft, this.rectSize, f)) {
                }
                Canvas.m4539clipPathmtrdDE$default(canvas, Path, 0, 2, null);
                return;
            }
            outlineResolver = this;
            RoundRect roundRectM4381RoundRectgG7oq9Y = RoundRectKt.m4381RoundRectgG7oq9Y(Offset.m4327getXimpl(outlineResolver.rectTopLeft), Offset.m4328getYimpl(outlineResolver.rectTopLeft), Offset.m4327getXimpl(outlineResolver.rectTopLeft) + Size.m4396getWidthimpl(outlineResolver.rectSize), Offset.m4328getYimpl(outlineResolver.rectTopLeft) + Size.m4393getHeightimpl(outlineResolver.rectSize), CornerRadiusKt.CornerRadius$default(outlineResolver.roundedCornerRadius, 0.0f, 2, null));
            if (Path == null) {
                Path = AndroidPath_androidKt.Path();
            } else {
                Path.reset();
            }
            Path.addRoundRect$default(Path, roundRectM4381RoundRectgG7oq9Y, null, 2, null);
            outlineResolver.tmpRoundRect = roundRectM4381RoundRectgG7oq9Y;
            outlineResolver.tmpPath = Path;
            Canvas.m4539clipPathmtrdDE$default(canvas, Path, 0, 2, null);
            return;
        }
        Canvas.m4540clipRectN_I0leg$default(canvas, Offset.m4327getXimpl(this.rectTopLeft), Offset.m4328getYimpl(this.rectTopLeft), Size.m4396getWidthimpl(this.rectSize) + Offset.m4327getXimpl(this.rectTopLeft), Size.m4393getHeightimpl(this.rectSize) + Offset.m4328getYimpl(this.rectTopLeft), 0, 16, null);
    }

    private final void updateCache() {
        if (this.cacheIsDirty) {
            this.rectTopLeft = Offset.INSTANCE.m4343getZeroF1C5BW0();
            this.roundedCornerRadius = 0.0f;
            this.outlinePath = null;
            this.cacheIsDirty = false;
            this.usePathForClip = false;
            androidx.compose.ui.graphics.Outline outline = this.outline;
            if (outline != null && this.outlineNeeded && Size.m4396getWidthimpl(this.rectSize) > 0.0f && Size.m4393getHeightimpl(this.rectSize) > 0.0f) {
                this.isSupportedOutline = true;
                if (outline instanceof Outline.Rectangle) {
                    updateCacheWithRect(((Outline.Rectangle) outline).getRect());
                    return;
                } else if (outline instanceof Outline.Rounded) {
                    updateCacheWithRoundRect(((Outline.Rounded) outline).getRoundRect());
                    return;
                } else {
                    if (outline instanceof Outline.Generic) {
                        updateCacheWithPath(((Outline.Generic) outline).getPath());
                        return;
                    }
                    return;
                }
            }
            this.cachedOutline.setEmpty();
        }
    }

    private final void updateCacheWithRect(Rect rect) {
        this.rectTopLeft = OffsetKt.Offset(rect.getLeft(), rect.getTop());
        this.rectSize = SizeKt.Size(rect.getWidth(), rect.getHeight());
        this.cachedOutline.setRect(Math.round(rect.getLeft()), Math.round(rect.getTop()), Math.round(rect.getRight()), Math.round(rect.getBottom()));
    }

    private final void updateCacheWithRoundRect(RoundRect roundRect) {
        float fM4302getXimpl = CornerRadius.m4302getXimpl(roundRect.m4377getTopLeftCornerRadiuskKHJgLs());
        this.rectTopLeft = OffsetKt.Offset(roundRect.getLeft(), roundRect.getTop());
        this.rectSize = SizeKt.Size(roundRect.getWidth(), roundRect.getHeight());
        if (RoundRectKt.isSimple(roundRect)) {
            this.cachedOutline.setRoundRect(Math.round(roundRect.getLeft()), Math.round(roundRect.getTop()), Math.round(roundRect.getRight()), Math.round(roundRect.getBottom()), fM4302getXimpl);
            this.roundedCornerRadius = fM4302getXimpl;
            return;
        }
        Path Path = this.cachedRrectPath;
        if (Path == null) {
            Path = AndroidPath_androidKt.Path();
            this.cachedRrectPath = Path;
        }
        Path.reset();
        Path.addRoundRect$default(Path, roundRect, null, 2, null);
        updateCacheWithPath(Path);
    }

    private final void updateCacheWithPath(Path composePath) {
        if (Build.VERSION.SDK_INT > 28 || composePath.isConvex()) {
            android.graphics.Outline outline = this.cachedOutline;
            if (composePath instanceof AndroidPath) {
                outline.setConvexPath(((AndroidPath) composePath).getInternalPath());
                this.usePathForClip = !this.cachedOutline.canClip();
            } else {
                throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
            }
        } else {
            this.isSupportedOutline = false;
            this.cachedOutline.setEmpty();
            this.usePathForClip = true;
        }
        this.outlinePath = composePath;
    }

    /* JADX INFO: renamed from: isSameBounds-4L21HEs, reason: not valid java name */
    private final boolean m6292isSameBounds4L21HEs(RoundRect roundRect, long j, long j2, float f) {
        return roundRect != null && RoundRectKt.isSimple(roundRect) && roundRect.getLeft() == Offset.m4327getXimpl(j) && roundRect.getTop() == Offset.m4328getYimpl(j) && roundRect.getRight() == Offset.m4327getXimpl(j) + Size.m4396getWidthimpl(j2) && roundRect.getBottom() == Offset.m4328getYimpl(j) + Size.m4393getHeightimpl(j2) && CornerRadius.m4302getXimpl(roundRect.m4377getTopLeftCornerRadiuskKHJgLs()) == f;
    }
}
