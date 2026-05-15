package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: BackdropScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\r\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0019\u0010\u000f\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0017\u0010\u0011\u001a\u00020\u00128Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168G¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0019"}, d2 = {"Landroidx/compose/material/BackdropScaffoldDefaults;", "", "()V", "AnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "FrontLayerElevation", "Landroidx/compose/ui/unit/Dp;", "getFrontLayerElevation-D9Ej5fM", "()F", "F", "HeaderHeight", "getHeaderHeight-D9Ej5fM", "PeekHeight", "getPeekHeight-D9Ej5fM", "frontLayerScrimColor", "Landroidx/compose/ui/graphics/Color;", "getFrontLayerScrimColor", "(Landroidx/compose/runtime/Composer;I)J", "frontLayerShape", "Landroidx/compose/ui/graphics/Shape;", "getFrontLayerShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BackdropScaffoldDefaults {
    public static final BackdropScaffoldDefaults INSTANCE = new BackdropScaffoldDefaults();
    private static final float PeekHeight = Dp.m7027constructorimpl(56);
    private static final float HeaderHeight = Dp.m7027constructorimpl(48);
    private static final float FrontLayerElevation = Dp.m7027constructorimpl(1);
    private static final AnimationSpec<Float> AnimationSpec = AnimationSpecKt.tween$default(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
    public static final int $stable = 8;

    private BackdropScaffoldDefaults() {
    }

    /* JADX INFO: renamed from: getPeekHeight-D9Ej5fM, reason: not valid java name */
    public final float m1713getPeekHeightD9Ej5fM() {
        return PeekHeight;
    }

    /* JADX INFO: renamed from: getHeaderHeight-D9Ej5fM, reason: not valid java name */
    public final float m1712getHeaderHeightD9Ej5fM() {
        return HeaderHeight;
    }

    public final Shape getFrontLayerShape(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1580588700, "C650@26326L6:BackdropScaffold.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1580588700, i, -1, "androidx.compose.material.BackdropScaffoldDefaults.<get-frontLayerShape> (BackdropScaffold.kt:650)");
        }
        float f = 16;
        CornerBasedShape cornerBasedShapeCopy$default = CornerBasedShape.copy$default(MaterialTheme.INSTANCE.getShapes(composer, 6).getLarge(), CornerSizeKt.m1240CornerSize0680j_4(Dp.m7027constructorimpl(f)), CornerSizeKt.m1240CornerSize0680j_4(Dp.m7027constructorimpl(f)), null, null, 12, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return cornerBasedShapeCopy$default;
    }

    /* JADX INFO: renamed from: getFrontLayerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1711getFrontLayerElevationD9Ej5fM() {
        return FrontLayerElevation;
    }

    public final long getFrontLayerScrimColor(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1806270648, "C662@26677L6:BackdropScaffold.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1806270648, i, -1, "androidx.compose.material.BackdropScaffoldDefaults.<get-frontLayerScrimColor> (BackdropScaffold.kt:662)");
        }
        long jM4567copywmQWz5c$default = Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1781getSurface0d7_KjU(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return jM4567copywmQWz5c$default;
    }

    public final AnimationSpec<Float> getAnimationSpec() {
        return AnimationSpec;
    }
}
