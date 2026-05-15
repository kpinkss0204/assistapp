package androidx.compose.material;

import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0001HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J*\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\u001c\u0010\u001f\u001a\u00020 *\u00020!2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J4\u0010\"\u001a\u00020 *\u00020!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020$H\u0002R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006)"}, d2 = {"Landroidx/compose/material/BottomAppBarCutoutShape;", "Landroidx/compose/ui/graphics/Shape;", "cutoutShape", "fabPlacement", "Landroidx/compose/material/FabPlacement;", "(Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/FabPlacement;)V", "getCutoutShape", "()Landroidx/compose/ui/graphics/Shape;", "getFabPlacement", "()Landroidx/compose/material/FabPlacement;", "component1", "component2", "copy", "createOutline", "Landroidx/compose/ui/graphics/Outline;", "size", "Landroidx/compose/ui/geometry/Size;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "createOutline-Pq9zytI", "(JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)Landroidx/compose/ui/graphics/Outline;", "equals", "", "other", "", "hashCode", "", "toString", "", "addCutoutShape", "", "Landroidx/compose/ui/graphics/Path;", "addRoundedEdges", "cutoutStartPosition", "", "cutoutEndPosition", "cutoutRadius", "roundedEdgeRadius", "verticalOffset", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final /* data */ class BottomAppBarCutoutShape implements Shape {
    private final Shape cutoutShape;
    private final FabPlacement fabPlacement;

    public static /* synthetic */ BottomAppBarCutoutShape copy$default(BottomAppBarCutoutShape bottomAppBarCutoutShape, Shape shape, FabPlacement fabPlacement, int i, Object obj) {
        if ((i & 1) != 0) {
            shape = bottomAppBarCutoutShape.cutoutShape;
        }
        if ((i & 2) != 0) {
            fabPlacement = bottomAppBarCutoutShape.fabPlacement;
        }
        return bottomAppBarCutoutShape.copy(shape, fabPlacement);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Shape getCutoutShape() {
        return this.cutoutShape;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FabPlacement getFabPlacement() {
        return this.fabPlacement;
    }

    public final BottomAppBarCutoutShape copy(Shape cutoutShape, FabPlacement fabPlacement) {
        return new BottomAppBarCutoutShape(cutoutShape, fabPlacement);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BottomAppBarCutoutShape)) {
            return false;
        }
        BottomAppBarCutoutShape bottomAppBarCutoutShape = (BottomAppBarCutoutShape) other;
        return Intrinsics.areEqual(this.cutoutShape, bottomAppBarCutoutShape.cutoutShape) && Intrinsics.areEqual(this.fabPlacement, bottomAppBarCutoutShape.fabPlacement);
    }

    public int hashCode() {
        return (this.cutoutShape.hashCode() * 31) + this.fabPlacement.hashCode();
    }

    public String toString() {
        return "BottomAppBarCutoutShape(cutoutShape=" + this.cutoutShape + ", fabPlacement=" + this.fabPlacement + ')';
    }

    public BottomAppBarCutoutShape(Shape shape, FabPlacement fabPlacement) {
        this.cutoutShape = shape;
        this.fabPlacement = fabPlacement;
    }

    public final Shape getCutoutShape() {
        return this.cutoutShape;
    }

    public final FabPlacement getFabPlacement() {
        return this.fabPlacement;
    }

    @Override // androidx.compose.ui.graphics.Shape
    /* JADX INFO: renamed from: createOutline-Pq9zytI */
    public Outline mo568createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
        Path Path = AndroidPath_androidKt.Path();
        Path.addRect$default(Path, new Rect(0.0f, 0.0f, Size.m4396getWidthimpl(size), Size.m4393getHeightimpl(size)), null, 2, null);
        Path Path2 = AndroidPath_androidKt.Path();
        addCutoutShape(Path2, layoutDirection, density);
        Path2.mo4460opN5in7k0(Path, Path2, PathOperation.INSTANCE.m4860getDifferenceb3I0S0c());
        return new Outline.Generic(Path2);
    }

    private final void addCutoutShape(Path path, LayoutDirection layoutDirection, Density density) {
        float fMo657toPx0680j_4 = density.mo657toPx0680j_4(AppBarKt.BottomAppBarCutoutOffset);
        float f = 2 * fMo657toPx0680j_4;
        long jSize = SizeKt.Size(this.fabPlacement.getWidth() + f, this.fabPlacement.getHeight() + f);
        float left = this.fabPlacement.getLeft() - fMo657toPx0680j_4;
        float fM4396getWidthimpl = left + Size.m4396getWidthimpl(jSize);
        float fM4393getHeightimpl = Size.m4393getHeightimpl(jSize) / 2.0f;
        OutlineKt.addOutline(path, this.cutoutShape.mo568createOutlinePq9zytI(jSize, layoutDirection, density));
        path.mo4463translatek4lQ0M(OffsetKt.Offset(left, -fM4393getHeightimpl));
        if (Intrinsics.areEqual(this.cutoutShape, RoundedCornerShapeKt.getCircleShape())) {
            addRoundedEdges(path, left, fM4396getWidthimpl, fM4393getHeightimpl, density.mo657toPx0680j_4(AppBarKt.BottomAppBarRoundedEdgeRadius), 0.0f);
        }
    }

    private final void addRoundedEdges(Path path, float f, float f2, float f3, float f4, float f5) {
        float f6 = -((float) Math.sqrt((f3 * f3) - (f5 * f5)));
        float f7 = f3 + f6;
        float f8 = f + f7;
        float f9 = f2 - f7;
        Pair<Float, Float> pairCalculateRoundedEdgeIntercept = AppBarKt.calculateRoundedEdgeIntercept(f6 - 1.0f, f5, f3);
        float fFloatValue = pairCalculateRoundedEdgeIntercept.component1().floatValue() + f3;
        float fFloatValue2 = pairCalculateRoundedEdgeIntercept.component2().floatValue() - f5;
        path.moveTo(f8 - f4, 0.0f);
        path.quadraticTo(f8 - 1.0f, 0.0f, f + fFloatValue, fFloatValue2);
        path.lineTo(f2 - fFloatValue, fFloatValue2);
        path.quadraticTo(f9 + 1.0f, 0.0f, f4 + f9, 0.0f);
        path.close();
    }
}
