package androidx.compose.material3;

import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: MaterialTheme.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aD\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\b0\u0010¢\u0006\u0002\b\u0011H\u0001¢\u0006\u0002\u0010\u0012\u001a>\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\b0\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001a\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\u0010\u0016\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"LocalUsingExpressiveTheme", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalUsingExpressiveTheme", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "TextSelectionBackgroundOpacity", "", "MaterialExpressiveTheme", "", "colorScheme", "Landroidx/compose/material3/ColorScheme;", "shapes", "Landroidx/compose/material3/Shapes;", "typography", "Landroidx/compose/material3/Typography;", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/material3/Shapes;Landroidx/compose/material3/Typography;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "MaterialTheme", "rememberTextSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MaterialThemeKt {
    private static final ProvidableCompositionLocal<Boolean> LocalUsingExpressiveTheme = CompositionLocalKt.staticCompositionLocalOf(new Function0<Boolean>() { // from class: androidx.compose.material3.MaterialThemeKt$LocalUsingExpressiveTheme$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return false;
        }
    });
    public static final float TextSelectionBackgroundOpacity = 0.4f;

    /* JADX WARN: Removed duplicated region for block: B:77:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0159  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void MaterialTheme(androidx.compose.material3.ColorScheme r16, androidx.compose.material3.Shapes r17, androidx.compose.material3.Typography r18, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r19, androidx.compose.runtime.Composer r20, final int r21, final int r22) {
        /*
            Method dump skipped, instruction units count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.MaterialThemeKt.MaterialTheme(androidx.compose.material3.ColorScheme, androidx.compose.material3.Shapes, androidx.compose.material3.Typography, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void MaterialExpressiveTheme(final ColorScheme colorScheme, final Shapes shapes, Typography typography, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        final Typography typography2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1399457222);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MaterialExpressiveTheme)P(!1,2,3)134@5791L7:MaterialTheme.kt#uh7d8r");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(colorScheme) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(shapes) ? 32 : 16;
        }
        int i6 = i2 & 4;
        if (i6 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(typography) ? 256 : 128;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i3 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (i4 != 0) {
                colorScheme = null;
            }
            if (i5 != 0) {
                shapes = null;
            }
            final Typography typography3 = i6 == 0 ? typography : null;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1399457222, i3, -1, "androidx.compose.material3.MaterialExpressiveTheme (MaterialTheme.kt:133)");
            }
            ProvidableCompositionLocal<Boolean> providableCompositionLocal = LocalUsingExpressiveTheme;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (((Boolean) objConsume).booleanValue()) {
                composerStartRestartGroup.startReplaceGroup(547059915);
                ComposerKt.sourceInformation(composerStartRestartGroup, "135@5810L240");
                composerStartRestartGroup.startReplaceGroup(1126027167);
                ComposerKt.sourceInformation(composerStartRestartGroup, "136@5880L11");
                ColorScheme colorScheme2 = colorScheme == null ? MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6) : colorScheme;
                composerStartRestartGroup.endReplaceGroup();
                composerStartRestartGroup.startReplaceGroup(1126029309);
                ComposerKt.sourceInformation(composerStartRestartGroup, "137@5946L10");
                Typography typography4 = typography3 == null ? MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6) : typography3;
                composerStartRestartGroup.endReplaceGroup();
                composerStartRestartGroup.startReplaceGroup(1126031253);
                ComposerKt.sourceInformation(composerStartRestartGroup, "138@6003L6");
                Shapes shapes2 = shapes == null ? MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 6) : shapes;
                composerStartRestartGroup.endReplaceGroup();
                MaterialTheme(colorScheme2, shapes2, typography4, function2, composerStartRestartGroup, i3 & 7168, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(547327197);
                ComposerKt.sourceInformation(composerStartRestartGroup, "142@6138L412,142@6072L478");
                CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(true), ComposableLambdaKt.rememberComposableLambda(2050809758, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i7) {
                        ComposerKt.sourceInformation(composer2, "C143@6152L388:MaterialTheme.kt#uh7d8r");
                        if ((i7 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2050809758, i7, -1, "androidx.compose.material3.MaterialExpressiveTheme.<anonymous> (MaterialTheme.kt:143)");
                            }
                            ColorScheme colorSchemeExpressiveLightColorScheme = colorScheme;
                            if (colorSchemeExpressiveLightColorScheme == null) {
                                colorSchemeExpressiveLightColorScheme = ColorSchemeKt.expressiveLightColorScheme();
                            }
                            ColorScheme colorScheme3 = colorSchemeExpressiveLightColorScheme;
                            Shapes shapes3 = shapes;
                            Shapes shapes4 = shapes3 == null ? new Shapes(null, null, null, null, null, 31, null) : shapes3;
                            Typography typography5 = typography3;
                            MaterialThemeKt.MaterialTheme(colorScheme3, shapes4, typography5 == null ? new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, LayoutKt.LargeDimension, null) : typography5, function2, composer2, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            typography2 = typography3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            typography2 = typography;
        }
        final ColorScheme colorScheme3 = colorScheme;
        final Shapes shapes3 = shapes;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialExpressiveTheme.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i7) {
                    MaterialThemeKt.MaterialExpressiveTheme(colorScheme3, shapes3, typography2, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalUsingExpressiveTheme() {
        return LocalUsingExpressiveTheme;
    }

    public static final TextSelectionColors rememberTextSelectionColors(ColorScheme colorScheme, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1866455512, "C(rememberTextSelectionColors)161@6816L198:MaterialTheme.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1866455512, i, -1, "androidx.compose.material3.rememberTextSelectionColors (MaterialTheme.kt:159)");
        }
        long primary = colorScheme.getPrimary();
        ComposerKt.sourceInformationMarkerStart(composer, -1160063291, "CC(remember):MaterialTheme.kt#9igjgp");
        boolean zChanged = composer.changed(primary);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            TextSelectionColors textSelectionColors = new TextSelectionColors(primary, Color.m4567copywmQWz5c$default(primary, 0.4f, 0.0f, 0.0f, 0.0f, 14, null), null);
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
}
