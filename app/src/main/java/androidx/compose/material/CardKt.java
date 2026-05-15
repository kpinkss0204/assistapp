package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0087\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001ac\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"Card", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "elevation", "Landroidx/compose/ui/unit/Dp;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", FirebaseAnalytics.Param.CONTENT, "Landroidx/compose/runtime/Composable;", "Card-LPr_se0", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Card-F-jzlyU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CardKt {
    /* JADX INFO: renamed from: Card-F-jzlyU, reason: not valid java name */
    public static final void m1753CardFjzlyU(Modifier modifier, Shape shape, long j, long j2, BorderStroke borderStroke, float f, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1956755640, "C(Card)P(5,6,0:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp)56@2436L6,57@2494L6,58@2536L32,63@2674L218:Card.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            modifier = Modifier.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            shape = MaterialTheme.INSTANCE.getShapes(composer, 6).getMedium();
        }
        Shape shape2 = shape;
        long jM1781getSurface0d7_KjU = (i2 & 4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1781getSurface0d7_KjU() : j;
        long jM1795contentColorForek8zF_U = (i2 & 8) != 0 ? ColorsKt.m1795contentColorForek8zF_U(jM1781getSurface0d7_KjU, composer, (i >> 6) & 14) : j2;
        BorderStroke borderStroke2 = (i2 & 16) != 0 ? null : borderStroke;
        float fM7027constructorimpl = (i2 & 32) != 0 ? Dp.m7027constructorimpl(1) : f;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1956755640, i, -1, "androidx.compose.material.Card (Card.kt:62)");
        }
        SurfaceKt.m1959SurfaceFjzlyU(modifier, shape2, jM1781getSurface0d7_KjU, jM1795contentColorForek8zF_U, borderStroke2, fM7027constructorimpl, function2, composer, i & 4194302, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
    }

    /* JADX INFO: renamed from: Card-LPr_se0, reason: not valid java name */
    public static final void m1754CardLPr_se0(Function0<Unit> function0, Modifier modifier, boolean z, Shape shape, long j, long j2, BorderStroke borderStroke, float f, MutableInteractionSource mutableInteractionSource, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 778538979, "C(Card)P(8,7,5,9,0:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp,6)107@4567L6,108@4625L6,109@4667L32,115@4862L319:Card.kt#jmzs0o");
        Modifier.Companion companion = (i2 & 2) != 0 ? Modifier.INSTANCE : modifier;
        boolean z2 = (i2 & 4) != 0 ? true : z;
        CornerBasedShape medium = (i2 & 8) != 0 ? MaterialTheme.INSTANCE.getShapes(composer, 6).getMedium() : shape;
        long jM1781getSurface0d7_KjU = (i2 & 16) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1781getSurface0d7_KjU() : j;
        long jM1795contentColorForek8zF_U = (i2 & 32) != 0 ? ColorsKt.m1795contentColorForek8zF_U(jM1781getSurface0d7_KjU, composer, (i >> 12) & 14) : j2;
        BorderStroke borderStroke2 = (i2 & 64) != 0 ? null : borderStroke;
        float fM7027constructorimpl = (i2 & 128) != 0 ? Dp.m7027constructorimpl(1) : f;
        MutableInteractionSource mutableInteractionSource2 = (i2 & 256) == 0 ? mutableInteractionSource : null;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(778538979, i, -1, "androidx.compose.material.Card (Card.kt:114)");
        }
        SurfaceKt.m1960SurfaceLPr_se0(function0, companion, z2, medium, jM1781getSurface0d7_KjU, jM1795contentColorForek8zF_U, borderStroke2, fM7027constructorimpl, mutableInteractionSource2, function2, composer, i & 2147483646, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
    }
}
