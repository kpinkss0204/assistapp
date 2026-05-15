package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: Checkbox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JD\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/compose/material/CheckboxDefaults;", "", "()V", "colors", "Landroidx/compose/material/CheckboxColors;", "checkedColor", "Landroidx/compose/ui/graphics/Color;", "uncheckedColor", "checkmarkColor", "disabledColor", "disabledIndeterminateColor", "colors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/CheckboxColors;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CheckboxDefaults {
    public static final int $stable = 0;
    public static final CheckboxDefaults INSTANCE = new CheckboxDefaults();

    private CheckboxDefaults() {
    }

    /* JADX INFO: renamed from: colors-zjMxDiM, reason: not valid java name */
    public final CheckboxColors m1755colorszjMxDiM(long j, long j2, long j3, long j4, long j5, Composer composer, int i, int i2) {
        long j6;
        long jM4567copywmQWz5c$default;
        ComposerKt.sourceInformationMarkerStart(composer, 469524104, "C(colors)P(0:c#ui.graphics.Color,4:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)226@9439L6,227@9503L6,228@9586L6,229@9647L6,229@9690L8,230@9784L8,232@9833L922:Checkbox.kt#jmzs0o");
        long jM1779getSecondary0d7_KjU = (i2 & 1) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1779getSecondary0d7_KjU() : j;
        long jM4567copywmQWz5c$default2 = (i2 & 2) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null) : j2;
        long jM1781getSurface0d7_KjU = (i2 & 4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1781getSurface0d7_KjU() : j3;
        long jM4567copywmQWz5c$default3 = (i2 & 8) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j4;
        if ((i2 & 16) != 0) {
            long j7 = jM1779getSecondary0d7_KjU;
            jM4567copywmQWz5c$default = Color.m4567copywmQWz5c$default(j7, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
            j6 = j7;
        } else {
            j6 = jM1779getSecondary0d7_KjU;
            jM4567copywmQWz5c$default = j5;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(469524104, i, -1, "androidx.compose.material.CheckboxDefaults.colors (Checkbox.kt:231)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 654676893, "CC(remember):Checkbox.kt#9igjgp");
        boolean z = ((((i & 14) ^ 6) > 4 && composer.changed(j6)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer.changed(jM4567copywmQWz5c$default2)) || (i & 48) == 32) | ((((i & 896) ^ 384) > 256 && composer.changed(jM1781getSurface0d7_KjU)) || (i & 384) == 256) | ((((i & 7168) ^ 3072) > 2048 && composer.changed(jM4567copywmQWz5c$default3)) || (i & 3072) == 2048) | ((((57344 & i) ^ 24576) > 16384 && composer.changed(jM4567copywmQWz5c$default)) || (i & 24576) == 16384);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            long jM4567copywmQWz5c$default4 = Color.m4567copywmQWz5c$default(jM1781getSurface0d7_KjU, 0.0f, 0.0f, 0.0f, 0.0f, 14, null);
            long j8 = j6;
            long jM4567copywmQWz5c$default5 = Color.m4567copywmQWz5c$default(j8, 0.0f, 0.0f, 0.0f, 0.0f, 14, null);
            long j9 = jM4567copywmQWz5c$default3;
            DefaultCheckboxColors defaultCheckboxColors = new DefaultCheckboxColors(jM1781getSurface0d7_KjU, jM4567copywmQWz5c$default4, j8, jM4567copywmQWz5c$default5, j9, Color.m4567copywmQWz5c$default(j9, 0.0f, 0.0f, 0.0f, 0.0f, 14, null), jM4567copywmQWz5c$default, j8, jM4567copywmQWz5c$default2, j9, jM4567copywmQWz5c$default, null);
            composer.updateRememberedValue(defaultCheckboxColors);
            objRememberedValue = defaultCheckboxColors;
        }
        DefaultCheckboxColors defaultCheckboxColors2 = (DefaultCheckboxColors) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultCheckboxColors2;
    }
}
