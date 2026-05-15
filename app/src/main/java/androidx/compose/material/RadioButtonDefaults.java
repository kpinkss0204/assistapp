package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: RadioButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"Landroidx/compose/material/RadioButtonDefaults;", "", "()V", "colors", "Landroidx/compose/material/RadioButtonColors;", "selectedColor", "Landroidx/compose/ui/graphics/Color;", "unselectedColor", "disabledColor", "colors-RGew2ao", "(JJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/RadioButtonColors;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class RadioButtonDefaults {
    public static final int $stable = 0;
    public static final RadioButtonDefaults INSTANCE = new RadioButtonDefaults();

    private RadioButtonDefaults() {
    }

    /* JADX INFO: renamed from: colors-RGew2ao, reason: not valid java name */
    public final RadioButtonColors m1921colorsRGew2ao(long j, long j2, long j3, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1370708026, "C(colors)P(1:c#ui.graphics.Color,2:c#ui.graphics.Color,0:c#ui.graphics.Color)162@6523L6,163@6588L6,164@6670L6,164@6713L8,166@6765L197:RadioButton.kt#jmzs0o");
        long jM1779getSecondary0d7_KjU = (i2 & 1) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1779getSecondary0d7_KjU() : j;
        long jM4567copywmQWz5c$default = (i2 & 2) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null) : j2;
        long jM4567copywmQWz5c$default2 = (i2 & 4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j3;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1370708026, i, -1, "androidx.compose.material.RadioButtonDefaults.colors (RadioButton.kt:165)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1778188470, "CC(remember):RadioButton.kt#9igjgp");
        boolean z = ((((i & 14) ^ 6) > 4 && composer.changed(jM1779getSecondary0d7_KjU)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer.changed(jM4567copywmQWz5c$default)) || (i & 48) == 32) | ((((i & 896) ^ 384) > 256 && composer.changed(jM4567copywmQWz5c$default2)) || (i & 384) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            DefaultRadioButtonColors defaultRadioButtonColors = new DefaultRadioButtonColors(jM1779getSecondary0d7_KjU, jM4567copywmQWz5c$default, jM4567copywmQWz5c$default2, null);
            composer.updateRememberedValue(defaultRadioButtonColors);
            objRememberedValue = defaultRadioButtonColors;
        }
        DefaultRadioButtonColors defaultRadioButtonColors2 = (DefaultRadioButtonColors) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultRadioButtonColors2;
    }
}
