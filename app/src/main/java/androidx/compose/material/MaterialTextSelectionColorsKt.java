package androidx.compose.material;

import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: MaterialTextSelectionColors.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\"\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a2\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a*\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\u0010\u001a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"DefaultSelectionBackgroundAlpha", "", "DesiredContrastRatio", "MinimumSelectionBackgroundAlpha", "binarySearchForAccessibleSelectionColorAlpha", "selectionColor", "Landroidx/compose/ui/graphics/Color;", "textColor", "backgroundColor", "binarySearchForAccessibleSelectionColorAlpha-ysEtTa8", "(JJJ)F", "calculateContrastRatio", "foreground", "background", "calculateContrastRatio--OWjLjI", "(JJ)F", "selectionColorAlpha", "calculateContrastRatio-nb2GgbA", "(JFJJ)F", "calculateSelectionBackgroundColor", "calculateSelectionBackgroundColor-ysEtTa8", "(JJJ)J", "rememberTextSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "colors", "Landroidx/compose/material/Colors;", "(Landroidx/compose/material/Colors;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MaterialTextSelectionColorsKt {
    private static final float DefaultSelectionBackgroundAlpha = 0.4f;
    private static final float DesiredContrastRatio = 4.5f;
    private static final float MinimumSelectionBackgroundAlpha = 0.2f;

    public static final TextSelectionColors rememberTextSelectionColors(Colors colors, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -721696685, "C(rememberTextSelectionColors)45@1902L6,47@1930L384:MaterialTextSelectionColors.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-721696685, i, -1, "androidx.compose.material.rememberTextSelectionColors (MaterialTextSelectionColors.kt:35)");
        }
        long jM1777getPrimary0d7_KjU = colors.m1777getPrimary0d7_KjU();
        long jM1770getBackground0d7_KjU = colors.m1770getBackground0d7_KjU();
        composer.startReplaceGroup(1102762072);
        ComposerKt.sourceInformation(composer, "*43@1845L7");
        long jM1794contentColorFor4WTKRHQ = ColorsKt.m1794contentColorFor4WTKRHQ(colors, jM1770getBackground0d7_KjU);
        if (jM1794contentColorFor4WTKRHQ == 16) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composer);
            jM1794contentColorFor4WTKRHQ = ((Color) objConsume).m4578unboximpl();
        }
        composer.endReplaceGroup();
        long jM4567copywmQWz5c$default = Color.m4567copywmQWz5c$default(jM1794contentColorFor4WTKRHQ, ContentAlpha.INSTANCE.getMedium(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
        ComposerKt.sourceInformationMarkerStart(composer, 1102766492, "CC(remember):MaterialTextSelectionColors.kt#9igjgp");
        boolean zChanged = composer.changed(jM1777getPrimary0d7_KjU) | composer.changed(jM1770getBackground0d7_KjU) | composer.changed(jM4567copywmQWz5c$default);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            TextSelectionColors textSelectionColors = new TextSelectionColors(colors.m1777getPrimary0d7_KjU(), m1877calculateSelectionBackgroundColorysEtTa8(jM1777getPrimary0d7_KjU, jM4567copywmQWz5c$default, jM1770getBackground0d7_KjU), null);
            composer.updateRememberedValue(textSelectionColors);
            objRememberedValue = textSelectionColors;
        }
        TextSelectionColors textSelectionColors2 = (TextSelectionColors) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return textSelectionColors2;
    }

    /* JADX INFO: renamed from: calculateSelectionBackgroundColor-ysEtTa8, reason: not valid java name */
    public static final long m1877calculateSelectionBackgroundColorysEtTa8(long j, long j2, long j3) {
        float fM1874binarySearchForAccessibleSelectionColorAlphaysEtTa8;
        float fM1876calculateContrastRationb2GgbA = m1876calculateContrastRationb2GgbA(j, 0.4f, j2, j3);
        float fM1876calculateContrastRationb2GgbA2 = m1876calculateContrastRationb2GgbA(j, 0.2f, j2, j3);
        if (fM1876calculateContrastRationb2GgbA >= DesiredContrastRatio) {
            fM1874binarySearchForAccessibleSelectionColorAlphaysEtTa8 = 0.4f;
        } else {
            fM1874binarySearchForAccessibleSelectionColorAlphaysEtTa8 = fM1876calculateContrastRationb2GgbA2 < DesiredContrastRatio ? 0.2f : m1874binarySearchForAccessibleSelectionColorAlphaysEtTa8(j, j2, j3);
        }
        return Color.m4567copywmQWz5c$default(j, fM1874binarySearchForAccessibleSelectionColorAlphaysEtTa8, 0.0f, 0.0f, 0.0f, 14, null);
    }

    /* JADX INFO: renamed from: binarySearchForAccessibleSelectionColorAlpha-ysEtTa8, reason: not valid java name */
    private static final float m1874binarySearchForAccessibleSelectionColorAlphaysEtTa8(long j, long j2, long j3) {
        int i = 0;
        float f = 0.2f;
        float f2 = 0.4f;
        float f3 = 0.4f;
        while (i < 7) {
            long j4 = j;
            long j5 = j2;
            long j6 = j3;
            float fM1876calculateContrastRationb2GgbA = (m1876calculateContrastRationb2GgbA(j4, f2, j5, j6) / DesiredContrastRatio) - 1.0f;
            if (0.0f <= fM1876calculateContrastRationb2GgbA && fM1876calculateContrastRationb2GgbA <= 0.01f) {
                return f2;
            }
            if (fM1876calculateContrastRationb2GgbA < 0.0f) {
                f3 = f2;
            } else {
                f = f2;
            }
            f2 = (f3 + f) / 2.0f;
            i++;
            j = j4;
            j2 = j5;
            j3 = j6;
        }
        return f2;
    }

    /* JADX INFO: renamed from: calculateContrastRatio-nb2GgbA, reason: not valid java name */
    private static final float m1876calculateContrastRationb2GgbA(long j, float f, long j2, long j3) {
        long jM4613compositeOverOWjLjI = ColorKt.m4613compositeOverOWjLjI(Color.m4567copywmQWz5c$default(j, f, 0.0f, 0.0f, 0.0f, 14, null), j3);
        return m1875calculateContrastRatioOWjLjI(ColorKt.m4613compositeOverOWjLjI(j2, jM4613compositeOverOWjLjI), jM4613compositeOverOWjLjI);
    }

    /* JADX INFO: renamed from: calculateContrastRatio--OWjLjI, reason: not valid java name */
    public static final float m1875calculateContrastRatioOWjLjI(long j, long j2) {
        float fM4620luminance8_81llA = ColorKt.m4620luminance8_81llA(j) + 0.05f;
        float fM4620luminance8_81llA2 = ColorKt.m4620luminance8_81llA(j2) + 0.05f;
        return Math.max(fM4620luminance8_81llA, fM4620luminance8_81llA2) / Math.min(fM4620luminance8_81llA, fM4620luminance8_81llA2);
    }
}
