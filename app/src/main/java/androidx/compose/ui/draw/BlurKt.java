package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.RenderEffectKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Blur.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"blur", "Landroidx/compose/ui/Modifier;", "radius", "Landroidx/compose/ui/unit/Dp;", "edgeTreatment", "Landroidx/compose/ui/draw/BlurredEdgeTreatment;", "blur-F8QBwvs", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/Modifier;", "radiusX", "radiusY", "blur-1fqS-gw", "(Landroidx/compose/ui/Modifier;FFLandroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/Modifier;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BlurKt {
    /* JADX INFO: renamed from: blur-1fqS-gw$default, reason: not valid java name */
    public static /* synthetic */ Modifier m4203blur1fqSgw$default(Modifier modifier, float f, float f2, BlurredEdgeTreatment blurredEdgeTreatment, int i, Object obj) {
        if ((i & 4) != 0) {
            blurredEdgeTreatment = BlurredEdgeTreatment.m4206boximpl(BlurredEdgeTreatment.INSTANCE.m4213getRectangleGoahg());
        }
        return m4202blur1fqSgw(modifier, f, f2, blurredEdgeTreatment.m4212unboximpl());
    }

    /* JADX INFO: renamed from: blur-1fqS-gw, reason: not valid java name */
    public static final Modifier m4202blur1fqSgw(Modifier modifier, final float f, final float f2, final Shape shape) {
        int iM4942getDecal3opZhB0;
        final boolean z;
        if (shape != null) {
            iM4942getDecal3opZhB0 = TileMode.INSTANCE.m4941getClamp3opZhB0();
            z = true;
        } else {
            iM4942getDecal3opZhB0 = TileMode.INSTANCE.m4942getDecal3opZhB0();
            z = false;
        }
        final int i = iM4942getDecal3opZhB0;
        float f3 = 0;
        return ((Dp.m7026compareTo0680j_4(f, Dp.m7027constructorimpl(f3)) <= 0 || Dp.m7026compareTo0680j_4(f2, Dp.m7027constructorimpl(f3)) <= 0) && !z) ? modifier : GraphicsLayerModifierKt.graphicsLayer(modifier, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.ui.draw.BlurKt$blur$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                invoke2(graphicsLayerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                float f4 = graphicsLayerScope.mo657toPx0680j_4(f);
                float f5 = graphicsLayerScope.mo657toPx0680j_4(f2);
                graphicsLayerScope.setRenderEffect((f4 <= 0.0f || f5 <= 0.0f) ? null : RenderEffectKt.m4876BlurEffect3YTHUZs(f4, f5, i));
                Shape rectangleShape = shape;
                if (rectangleShape == null) {
                    rectangleShape = RectangleShapeKt.getRectangleShape();
                }
                graphicsLayerScope.setShape(rectangleShape);
                graphicsLayerScope.setClip(z);
            }
        });
    }

    /* JADX INFO: renamed from: blur-F8QBwvs$default, reason: not valid java name */
    public static /* synthetic */ Modifier m4205blurF8QBwvs$default(Modifier modifier, float f, BlurredEdgeTreatment blurredEdgeTreatment, int i, Object obj) {
        if ((i & 2) != 0) {
            blurredEdgeTreatment = BlurredEdgeTreatment.m4206boximpl(BlurredEdgeTreatment.INSTANCE.m4213getRectangleGoahg());
        }
        return m4204blurF8QBwvs(modifier, f, blurredEdgeTreatment.m4212unboximpl());
    }

    /* JADX INFO: renamed from: blur-F8QBwvs, reason: not valid java name */
    public static final Modifier m4204blurF8QBwvs(Modifier modifier, float f, Shape shape) {
        return m4202blur1fqSgw(modifier, f, f, shape);
    }
}
