package com.example.assistapp.ui.theme;

import android.content.Context;
import android.os.Build;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.DynamicTonalPaletteKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Theme.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00040\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"DarkColorScheme", "Landroidx/compose/material3/ColorScheme;", "LightColorScheme", "AssistappTheme", "", "darkTheme", "", "dynamicColor", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(ZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app_release"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ThemeKt {
    private static final ColorScheme DarkColorScheme = ColorSchemeKt.m2235darkColorSchemeCXl9yA$default(ColorKt.getPurple80(), 0, 0, 0, 0, ColorKt.getPurpleGrey80(), 0, 0, 0, ColorKt.getPink80(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -546, 15, null);
    private static final ColorScheme LightColorScheme = ColorSchemeKt.m2239lightColorSchemeCXl9yA$default(ColorKt.getPurple40(), 0, 0, 0, 0, ColorKt.getPurpleGrey40(), 0, 0, 0, ColorKt.getPink40(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -546, 15, null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistappTheme$lambda$0(boolean z, boolean z2, Function2 function2, int i, int i2, Composer composer, int i3) {
        AssistappTheme(z, z2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void AssistappTheme(boolean z, boolean z2, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i, final int i2) {
        int i3;
        boolean zIsSystemInDarkTheme;
        boolean z3;
        boolean z4;
        ColorScheme colorSchemeDynamicDarkColorScheme;
        final boolean z5;
        final boolean z6;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1666762205);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AssistappTheme)P(1,2)52@1582L114:Theme.kt#dmjkh6");
        if ((i & 6) == 0) {
            i3 = (((i2 & 1) == 0 && composerStartRestartGroup.changed(z)) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z2) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(content) ? 256 : 128;
        }
        if ((i3 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "37@1095L21");
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
                    zIsSystemInDarkTheme = DarkThemeKt.isSystemInDarkTheme(composerStartRestartGroup, 0);
                    i3 &= -15;
                } else {
                    zIsSystemInDarkTheme = z;
                }
                if (i4 != 0) {
                    z4 = zIsSystemInDarkTheme;
                    z3 = true;
                } else {
                    z3 = z2;
                    z4 = zIsSystemInDarkTheme;
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                z4 = z;
                z3 = z2;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1666762205, i3, -1, "com.example.assistapp.ui.theme.AssistappTheme (Theme.kt:41)");
            }
            composerStartRestartGroup.startReplaceGroup(-1879220254);
            ComposerKt.sourceInformation(composerStartRestartGroup, "44@1385L7");
            if (z3 && Build.VERSION.SDK_INT >= 31) {
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = composerStartRestartGroup.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Context context = (Context) objConsume;
                colorSchemeDynamicDarkColorScheme = z4 ? DynamicTonalPaletteKt.dynamicDarkColorScheme(context) : DynamicTonalPaletteKt.dynamicLightColorScheme(context);
            } else if (z4) {
                colorSchemeDynamicDarkColorScheme = DarkColorScheme;
            } else {
                colorSchemeDynamicDarkColorScheme = LightColorScheme;
            }
            composerStartRestartGroup.endReplaceGroup();
            MaterialThemeKt.MaterialTheme(colorSchemeDynamicDarkColorScheme, null, TypeKt.getTypography(), content, composerStartRestartGroup, ((i3 << 3) & 7168) | 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z5 = z4;
            z6 = z3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            z5 = z;
            z6 = z2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.ui.theme.ThemeKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ThemeKt.AssistappTheme$lambda$0(z5, z6, content, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
