package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* JADX INFO: compiled from: Button.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'J0\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.JD\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u00042\b\b\u0002\u0010/\u001a\u00020\u00042\b\b\u0002\u00100\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102J0\u00103\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u00105J0\u00106\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b7\u00105R\u0016\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\u0010\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0011\u0010\rR\u0019\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0013\u0010\rR\u000e\u0010\u0014\u001a\u00020\u0015X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0017\u0010\rR\u0011\u0010\u0018\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\nR\u0016\u0010\u001a\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u001b\u001a\u00020\u001c8G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00068"}, d2 = {"Landroidx/compose/material/ButtonDefaults;", "", "()V", "ButtonHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ButtonVerticalPadding", "ContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "IconSize", "getIconSize-D9Ej5fM", "()F", "IconSpacing", "getIconSpacing-D9Ej5fM", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "OutlinedBorderOpacity", "", "OutlinedBorderSize", "getOutlinedBorderSize-D9Ej5fM", "TextButtonContentPadding", "getTextButtonContentPadding", "TextButtonHorizontalPadding", "outlinedBorder", "Landroidx/compose/foundation/BorderStroke;", "getOutlinedBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "buttonColors", "Landroidx/compose/material/ButtonColors;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledBackgroundColor", "disabledContentColor", "buttonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonColors;", "elevation", "Landroidx/compose/material/ButtonElevation;", "defaultElevation", "pressedElevation", "disabledElevation", "elevation-yajeYGU", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonElevation;", "hoveredElevation", "focusedElevation", "elevation-R_JCAzs", "(FFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonElevation;", "outlinedButtonColors", "outlinedButtonColors-RGew2ao", "(JJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonColors;", "textButtonColors", "textButtonColors-RGew2ao", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ButtonDefaults {
    public static final int $stable = 0;
    private static final float ButtonHorizontalPadding;
    private static final float ButtonVerticalPadding;
    private static final PaddingValues ContentPadding;
    public static final ButtonDefaults INSTANCE = new ButtonDefaults();
    private static final float IconSize;
    private static final float IconSpacing;
    private static final float MinHeight;
    private static final float MinWidth;
    public static final float OutlinedBorderOpacity = 0.12f;
    private static final float OutlinedBorderSize;
    private static final PaddingValues TextButtonContentPadding;
    private static final float TextButtonHorizontalPadding;

    private ButtonDefaults() {
    }

    public final PaddingValues getContentPadding() {
        return ContentPadding;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1749getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1748getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1746getIconSizeD9Ej5fM() {
        return IconSize;
    }

    /* JADX INFO: renamed from: getIconSpacing-D9Ej5fM, reason: not valid java name */
    public final float m1747getIconSpacingD9Ej5fM() {
        return IconSpacing;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use another overload of elevation")
    /* JADX INFO: renamed from: elevation-yajeYGU, reason: not valid java name */
    public final /* synthetic */ ButtonElevation m1745elevationyajeYGU(float f, float f2, float f3, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1428576874, "C(elevation)P(0:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp)350@14616L161:Button.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            f = Dp.m7027constructorimpl(2);
        }
        float f4 = f;
        if ((i2 & 2) != 0) {
            f2 = Dp.m7027constructorimpl(8);
        }
        float f5 = f2;
        if ((i2 & 4) != 0) {
            f3 = Dp.m7027constructorimpl(0);
        }
        float f6 = f3;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1428576874, i, -1, "androidx.compose.material.ButtonDefaults.elevation (Button.kt:350)");
        }
        float f7 = 4;
        ButtonElevation buttonElevationM1744elevationR_JCAzs = m1744elevationR_JCAzs(f4, f5, f6, Dp.m7027constructorimpl(f7), Dp.m7027constructorimpl(f7), composer, (i & 14) | 27648 | (i & 112) | (i & 896) | ((i << 6) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return buttonElevationM1744elevationR_JCAzs;
    }

    /* JADX INFO: renamed from: elevation-R_JCAzs, reason: not valid java name */
    public final ButtonElevation m1744elevationR_JCAzs(float f, float f2, float f3, float f4, float f5, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -737170518, "C(elevation)P(0:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,3:c#ui.unit.Dp,2:c#ui.unit.Dp)379@15748L497:Button.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            f = Dp.m7027constructorimpl(2);
        }
        float f6 = f;
        if ((i2 & 2) != 0) {
            f2 = Dp.m7027constructorimpl(8);
        }
        float f7 = f2;
        if ((i2 & 4) != 0) {
            f3 = Dp.m7027constructorimpl(0);
        }
        float f8 = f3;
        float fM7027constructorimpl = (i2 & 8) != 0 ? Dp.m7027constructorimpl(4) : f4;
        float fM7027constructorimpl2 = (i2 & 16) != 0 ? Dp.m7027constructorimpl(4) : f5;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-737170518, i, -1, "androidx.compose.material.ButtonDefaults.elevation (Button.kt:378)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -511819094, "CC(remember):Button.kt#9igjgp");
        boolean z = ((((i & 14) ^ 6) > 4 && composer.changed(f6)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer.changed(f7)) || (i & 48) == 32) | ((((i & 896) ^ 384) > 256 && composer.changed(f8)) || (i & 384) == 256) | ((((i & 7168) ^ 3072) > 2048 && composer.changed(fM7027constructorimpl)) || (i & 3072) == 2048) | ((((57344 & i) ^ 24576) > 16384 && composer.changed(fM7027constructorimpl2)) || (i & 24576) == 16384);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            DefaultButtonElevation defaultButtonElevation = new DefaultButtonElevation(f6, f7, f8, fM7027constructorimpl, fM7027constructorimpl2, null);
            composer.updateRememberedValue(defaultButtonElevation);
            objRememberedValue = defaultButtonElevation;
        }
        DefaultButtonElevation defaultButtonElevation2 = (DefaultButtonElevation) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultButtonElevation2;
    }

    /* JADX INFO: renamed from: buttonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1743buttonColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1870371134, "C(buttonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)407@16814L6,408@16860L32,409@16949L6,410@17027L6,411@17096L6,412@17152L8:Button.kt#jmzs0o");
        long jM1777getPrimary0d7_KjU = (i2 & 1) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU() : j;
        long jM1795contentColorForek8zF_U = (i2 & 2) != 0 ? ColorsKt.m1795contentColorForek8zF_U(jM1777getPrimary0d7_KjU, composer, i & 14) : j2;
        long jM4613compositeOverOWjLjI = (i2 & 4) != 0 ? ColorKt.m4613compositeOverOWjLjI(Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColors(composer, 6).m1781getSurface0d7_KjU()) : j3;
        long jM4567copywmQWz5c$default = (i2 & 8) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j4;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1870371134, i, -1, "androidx.compose.material.ButtonDefaults.buttonColors (Button.kt:413)");
        }
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(jM1777getPrimary0d7_KjU, jM1795contentColorForek8zF_U, jM4613compositeOverOWjLjI, jM4567copywmQWz5c$default, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultButtonColors;
    }

    /* JADX INFO: renamed from: outlinedButtonColors-RGew2ao, reason: not valid java name */
    public final ButtonColors m1751outlinedButtonColorsRGew2ao(long j, long j2, long j3, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -2124406093, "C(outlinedButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color)430@17899L6,431@17959L6,432@18027L6,433@18083L8:Button.kt#jmzs0o");
        long jM1781getSurface0d7_KjU = (i2 & 1) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1781getSurface0d7_KjU() : j;
        long jM1777getPrimary0d7_KjU = (i2 & 2) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU() : j2;
        long jM4567copywmQWz5c$default = (i2 & 4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j3;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2124406093, i, -1, "androidx.compose.material.ButtonDefaults.outlinedButtonColors (Button.kt:434)");
        }
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(jM1781getSurface0d7_KjU, jM1777getPrimary0d7_KjU, jM1781getSurface0d7_KjU, jM4567copywmQWz5c$default, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultButtonColors;
    }

    /* JADX INFO: renamed from: textButtonColors-RGew2ao, reason: not valid java name */
    public final ButtonColors m1752textButtonColorsRGew2ao(long j, long j2, long j3, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 182742216, "C(textButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color)452@18850L6,453@18918L6,454@18974L8:Button.kt#jmzs0o");
        long jM4603getTransparent0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m4603getTransparent0d7_KjU() : j;
        long jM1777getPrimary0d7_KjU = (i2 & 2) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU() : j2;
        long jM4567copywmQWz5c$default = (i2 & 4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j3;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(182742216, i, -1, "androidx.compose.material.ButtonDefaults.textButtonColors (Button.kt:455)");
        }
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(jM4603getTransparent0d7_KjU, jM1777getPrimary0d7_KjU, jM4603getTransparent0d7_KjU, jM4567copywmQWz5c$default, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultButtonColors;
    }

    /* JADX INFO: renamed from: getOutlinedBorderSize-D9Ej5fM, reason: not valid java name */
    public final float m1750getOutlinedBorderSizeD9Ej5fM() {
        return OutlinedBorderSize;
    }

    public final BorderStroke getOutlinedBorder(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -2091313033, "C478@19678L6:Button.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2091313033, i, -1, "androidx.compose.material.ButtonDefaults.<get-outlinedBorder> (Button.kt:477)");
        }
        BorderStroke borderStrokeM547BorderStrokecXLIe8U = BorderStrokeKt.m547BorderStrokecXLIe8U(OutlinedBorderSize, Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return borderStrokeM547BorderStrokecXLIe8U;
    }

    public final PaddingValues getTextButtonContentPadding() {
        return TextButtonContentPadding;
    }

    static {
        float fM7027constructorimpl = Dp.m7027constructorimpl(16);
        ButtonHorizontalPadding = fM7027constructorimpl;
        float f = 8;
        float fM7027constructorimpl2 = Dp.m7027constructorimpl(f);
        ButtonVerticalPadding = fM7027constructorimpl2;
        PaddingValues paddingValuesM961PaddingValuesa9UjIt4 = PaddingKt.m961PaddingValuesa9UjIt4(fM7027constructorimpl, fM7027constructorimpl2, fM7027constructorimpl, fM7027constructorimpl2);
        ContentPadding = paddingValuesM961PaddingValuesa9UjIt4;
        MinWidth = Dp.m7027constructorimpl(64);
        MinHeight = Dp.m7027constructorimpl(36);
        IconSize = Dp.m7027constructorimpl(18);
        IconSpacing = Dp.m7027constructorimpl(f);
        OutlinedBorderSize = Dp.m7027constructorimpl(1);
        float fM7027constructorimpl3 = Dp.m7027constructorimpl(f);
        TextButtonHorizontalPadding = fM7027constructorimpl3;
        TextButtonContentPadding = PaddingKt.m961PaddingValuesa9UjIt4(fM7027constructorimpl3, paddingValuesM961PaddingValuesa9UjIt4.getTop(), fM7027constructorimpl3, paddingValuesM961PaddingValuesa9UjIt4.getBottom());
    }
}
