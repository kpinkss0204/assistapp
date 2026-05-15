package androidx.compose.material;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowDropDownKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ExposedDropdownMenu.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0007¢\u0006\u0002\u0010\tJî\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\r2\b\b\u0002\u0010 \u001a\u00020\r2\b\b\u0002\u0010!\u001a\u00020\r2\b\b\u0002\u0010\"\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$Jî\u0001\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010&\u001a\u00020\r2\b\b\u0002\u0010'\u001a\u00020\r2\b\b\u0002\u0010(\u001a\u00020\r2\b\b\u0002\u0010)\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\r2\b\b\u0002\u0010 \u001a\u00020\r2\b\b\u0002\u0010!\u001a\u00020\r2\b\b\u0002\u0010\"\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010$\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/compose/material/ExposedDropdownMenuDefaults;", "", "()V", "TrailingIcon", "", "expanded", "", "onIconClick", "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "outlinedTextFieldColors", "Landroidx/compose/material/TextFieldColors;", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", "backgroundColor", "cursorColor", "errorCursorColor", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "leadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "trailingIconColor", "focusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "outlinedTextFieldColors-DlUQjxs", "(JJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material/TextFieldColors;", "textFieldColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "textFieldColors-DlUQjxs", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ExposedDropdownMenuDefaults {
    public static final int $stable = 0;
    public static final ExposedDropdownMenuDefaults INSTANCE = new ExposedDropdownMenuDefaults();

    private ExposedDropdownMenuDefaults() {
    }

    public final void TrailingIcon(final boolean z, Function0<Unit> function0, Composer composer, final int i, final int i2) {
        int i3;
        final AnonymousClass1 anonymousClass1;
        Composer composerStartRestartGroup = composer.startRestartGroup(1752693020);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(TrailingIcon)298@11657L314,298@11577L394:ExposedDropdownMenu.android.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i3 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            anonymousClass1 = i4 != 0 ? new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            } : function0;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1752693020, i3, -1, "androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon (ExposedDropdownMenu.android.kt:293)");
            }
            IconButtonKt.IconButton(anonymousClass1, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }
            }), false, null, ComposableLambdaKt.rememberComposableLambda(-689144648, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    ComposerKt.sourceInformation(composer2, "C299@11671L290:ExposedDropdownMenu.android.kt#jmzs0o");
                    if ((i5 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-689144648, i5, -1, "androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon.<anonymous> (ExposedDropdownMenu.android.kt:299)");
                        }
                        IconKt.m1870Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), "Trailing icon for exposed dropdown menu", RotateKt.rotate(Modifier.INSTANCE, z ? 180.0f : 360.0f), 0L, composer2, 48, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 >> 3) & 14) | 24576, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            anonymousClass1 = function0;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    ExposedDropdownMenuDefaults.this.TrailingIcon(z, anonymousClass1, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: textFieldColors-DlUQjxs, reason: not valid java name */
    public final TextFieldColors m1848textFieldColorsDlUQjxs(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, Composer composer, int i, int i2, int i3, int i4) {
        long jM4567copywmQWz5c$default;
        long j23;
        long jM4567copywmQWz5c$default2;
        long j24;
        long jM4567copywmQWz5c$default3;
        long j25;
        long jM4567copywmQWz5c$default4;
        long j26;
        long jM4567copywmQWz5c$default5;
        ComposerKt.sourceInformationMarkerStart(composer, 969536191, "C(textFieldColors)P(18:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,13:c#ui.graphics.Color,20:c#ui.graphics.Color,2:c#ui.graphics.Color,9:c#ui.graphics.Color,16:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,19:c#ui.graphics.Color,15:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,21:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,17:c#ui.graphics.Color,5:c#ui.graphics.Color)357@14650L7,357@14681L7,358@14754L8,360@14824L6,361@14935L6,362@14999L6,364@15078L6,364@15119L4,366@15193L6,369@15393L8,370@15455L6,372@15529L6,373@15676L8,376@15805L6,378@15935L6,378@15976L4,379@16070L8,380@16135L6,382@16210L6,382@16251L4,383@16309L6,383@16344L6,384@16427L8,385@16485L6,386@16547L6,386@16582L6,387@16668L8:ExposedDropdownMenu.android.kt#jmzs0o");
        if ((i4 & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composer);
            long jM4578unboximpl = ((Color) objConsume).m4578unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd(composer);
            jM4567copywmQWz5c$default = Color.m4567copywmQWz5c$default(jM4578unboximpl, ((Number) objConsume2).floatValue(), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM4567copywmQWz5c$default = j;
        }
        long jM4567copywmQWz5c$default6 = (i4 & 2) != 0 ? Color.m4567copywmQWz5c$default(jM4567copywmQWz5c$default, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j2;
        long jM4567copywmQWz5c$default7 = (i4 & 4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j3;
        long jM1777getPrimary0d7_KjU = (i4 & 8) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU() : j4;
        long jM1771getError0d7_KjU = (i4 & 16) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1771getError0d7_KjU() : j5;
        long jM4567copywmQWz5c$default8 = (i4 & 32) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j6;
        long jM4567copywmQWz5c$default9 = (i4 & 64) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.42f, 0.0f, 0.0f, 0.0f, 14, null) : j7;
        if ((i4 & 128) != 0) {
            long j27 = jM4567copywmQWz5c$default9;
            j23 = j27;
            jM4567copywmQWz5c$default2 = Color.m4567copywmQWz5c$default(j27, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            j23 = jM4567copywmQWz5c$default9;
            jM4567copywmQWz5c$default2 = j8;
        }
        long jM1771getError0d7_KjU2 = (i4 & 256) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1771getError0d7_KjU() : j9;
        long jM4567copywmQWz5c$default10 = (i4 & 512) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j10;
        if ((i4 & 1024) != 0) {
            long j28 = jM4567copywmQWz5c$default10;
            jM4567copywmQWz5c$default3 = Color.m4567copywmQWz5c$default(j28, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
            j24 = j28;
        } else {
            j24 = jM4567copywmQWz5c$default10;
            jM4567copywmQWz5c$default3 = j11;
        }
        long j29 = (i4 & 2048) != 0 ? j24 : j12;
        long jM4567copywmQWz5c$default11 = (i4 & 4096) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        long jM4567copywmQWz5c$default12 = (i4 & 8192) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j14;
        if ((i4 & 16384) != 0) {
            long j30 = jM4567copywmQWz5c$default11;
            jM4567copywmQWz5c$default4 = Color.m4567copywmQWz5c$default(j30, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
            j25 = j30;
        } else {
            j25 = jM4567copywmQWz5c$default11;
            jM4567copywmQWz5c$default4 = j15;
        }
        long jM1771getError0d7_KjU3 = (32768 & i4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1771getError0d7_KjU() : j16;
        long jM4567copywmQWz5c$default13 = (65536 & i4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j17;
        long jM4567copywmQWz5c$default14 = (131072 & i4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getMedium(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j18;
        if ((262144 & i4) != 0) {
            long j31 = jM4567copywmQWz5c$default14;
            jM4567copywmQWz5c$default5 = Color.m4567copywmQWz5c$default(j31, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
            j26 = j31;
        } else {
            j26 = jM4567copywmQWz5c$default14;
            jM4567copywmQWz5c$default5 = j19;
        }
        long jM1771getError0d7_KjU4 = (524288 & i4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1771getError0d7_KjU() : j20;
        long jM4567copywmQWz5c$default15 = (1048576 & i4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getMedium(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j21;
        long jM4567copywmQWz5c$default16 = (i4 & 2097152) != 0 ? Color.m4567copywmQWz5c$default(jM4567copywmQWz5c$default15, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j22;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(969536191, i, i2, "androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors (ExposedDropdownMenu.android.kt:389)");
        }
        DefaultTextFieldForExposedDropdownMenusColors defaultTextFieldForExposedDropdownMenusColors = new DefaultTextFieldForExposedDropdownMenusColors(jM4567copywmQWz5c$default, jM4567copywmQWz5c$default6, jM1777getPrimary0d7_KjU, jM1771getError0d7_KjU, jM4567copywmQWz5c$default8, j23, jM1771getError0d7_KjU2, jM4567copywmQWz5c$default2, j24, jM4567copywmQWz5c$default3, j29, j25, jM4567copywmQWz5c$default12, jM4567copywmQWz5c$default4, jM1771getError0d7_KjU3, jM4567copywmQWz5c$default7, jM4567copywmQWz5c$default13, j26, jM4567copywmQWz5c$default5, jM1771getError0d7_KjU4, jM4567copywmQWz5c$default15, jM4567copywmQWz5c$default16, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultTextFieldForExposedDropdownMenusColors;
    }

    /* JADX INFO: renamed from: outlinedTextFieldColors-DlUQjxs, reason: not valid java name */
    public final TextFieldColors m1847outlinedTextFieldColorsDlUQjxs(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, Composer composer, int i, int i2, int i3, int i4) {
        long jM4567copywmQWz5c$default;
        long j23;
        long jM4567copywmQWz5c$default2;
        long j24;
        long jM4567copywmQWz5c$default3;
        long j25;
        long jM4567copywmQWz5c$default4;
        long j26;
        long jM4567copywmQWz5c$default5;
        ComposerKt.sourceInformationMarkerStart(composer, 1841636861, "C(outlinedTextFieldColors)P(18:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,9:c#ui.graphics.Color,13:c#ui.graphics.Color,20:c#ui.graphics.Color,2:c#ui.graphics.Color,8:c#ui.graphics.Color,16:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,19:c#ui.graphics.Color,15:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,21:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,17:c#ui.graphics.Color,5:c#ui.graphics.Color)460@20635L7,460@20666L7,461@20739L8,463@20845L6,464@20909L6,466@20985L6,466@21026L4,468@21097L6,468@21140L8,469@21235L8,470@21294L6,472@21368L6,473@21515L8,476@21644L6,478@21774L6,478@21815L4,479@21909L8,480@21974L6,482@22049L6,482@22090L4,483@22148L6,483@22183L6,484@22266L8,485@22324L6,486@22386L6,486@22421L6,487@22507L8:ExposedDropdownMenu.android.kt#jmzs0o");
        if ((i4 & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composer);
            long jM4578unboximpl = ((Color) objConsume).m4578unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd(composer);
            jM4567copywmQWz5c$default = Color.m4567copywmQWz5c$default(jM4578unboximpl, ((Number) objConsume2).floatValue(), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM4567copywmQWz5c$default = j;
        }
        long jM4567copywmQWz5c$default6 = (i4 & 2) != 0 ? Color.m4567copywmQWz5c$default(jM4567copywmQWz5c$default, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j2;
        long jM4603getTransparent0d7_KjU = (i4 & 4) != 0 ? Color.INSTANCE.m4603getTransparent0d7_KjU() : j3;
        long jM1777getPrimary0d7_KjU = (i4 & 8) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU() : j4;
        long jM1771getError0d7_KjU = (i4 & 16) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1771getError0d7_KjU() : j5;
        long jM4567copywmQWz5c$default7 = (i4 & 32) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j6;
        long jM4567copywmQWz5c$default8 = (i4 & 64) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j7;
        if ((i4 & 128) != 0) {
            long j27 = jM4567copywmQWz5c$default8;
            j23 = j27;
            jM4567copywmQWz5c$default2 = Color.m4567copywmQWz5c$default(j27, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            j23 = jM4567copywmQWz5c$default8;
            jM4567copywmQWz5c$default2 = j8;
        }
        long jM1771getError0d7_KjU2 = (i4 & 256) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1771getError0d7_KjU() : j9;
        long jM4567copywmQWz5c$default9 = (i4 & 512) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j10;
        if ((i4 & 1024) != 0) {
            long j28 = jM4567copywmQWz5c$default9;
            jM4567copywmQWz5c$default3 = Color.m4567copywmQWz5c$default(j28, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
            j24 = j28;
        } else {
            j24 = jM4567copywmQWz5c$default9;
            jM4567copywmQWz5c$default3 = j11;
        }
        long j29 = (i4 & 2048) != 0 ? j24 : j12;
        long jM4567copywmQWz5c$default10 = (i4 & 4096) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j13;
        long jM4567copywmQWz5c$default11 = (i4 & 8192) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j14;
        if ((i4 & 16384) != 0) {
            long j30 = jM4567copywmQWz5c$default10;
            jM4567copywmQWz5c$default4 = Color.m4567copywmQWz5c$default(j30, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
            j25 = j30;
        } else {
            j25 = jM4567copywmQWz5c$default10;
            jM4567copywmQWz5c$default4 = j15;
        }
        long jM1771getError0d7_KjU3 = (32768 & i4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1771getError0d7_KjU() : j16;
        long jM4567copywmQWz5c$default12 = (65536 & i4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1777getPrimary0d7_KjU(), ContentAlpha.INSTANCE.getHigh(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j17;
        long jM4567copywmQWz5c$default13 = (131072 & i4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getMedium(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j18;
        if ((262144 & i4) != 0) {
            long j31 = jM4567copywmQWz5c$default13;
            jM4567copywmQWz5c$default5 = Color.m4567copywmQWz5c$default(j31, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null);
            j26 = j31;
        } else {
            j26 = jM4567copywmQWz5c$default13;
            jM4567copywmQWz5c$default5 = j19;
        }
        long jM1771getError0d7_KjU4 = (524288 & i4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 6).m1771getError0d7_KjU() : j20;
        long jM4567copywmQWz5c$default14 = (1048576 & i4) != 0 ? Color.m4567copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 6).m1776getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getMedium(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j21;
        long jM4567copywmQWz5c$default15 = (i4 & 2097152) != 0 ? Color.m4567copywmQWz5c$default(jM4567copywmQWz5c$default14, ContentAlpha.INSTANCE.getDisabled(composer, 6), 0.0f, 0.0f, 0.0f, 14, null) : j22;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1841636861, i, i2, "androidx.compose.material.ExposedDropdownMenuDefaults.outlinedTextFieldColors (ExposedDropdownMenu.android.kt:489)");
        }
        DefaultTextFieldForExposedDropdownMenusColors defaultTextFieldForExposedDropdownMenusColors = new DefaultTextFieldForExposedDropdownMenusColors(jM4567copywmQWz5c$default, jM4567copywmQWz5c$default6, jM1777getPrimary0d7_KjU, jM1771getError0d7_KjU, jM4567copywmQWz5c$default7, j23, jM1771getError0d7_KjU2, jM4567copywmQWz5c$default2, j24, jM4567copywmQWz5c$default3, j29, j25, jM4567copywmQWz5c$default11, jM4567copywmQWz5c$default4, jM1771getError0d7_KjU3, jM4603getTransparent0d7_KjU, jM4567copywmQWz5c$default12, j26, jM4567copywmQWz5c$default5, jM1771getError0d7_KjU4, jM4567copywmQWz5c$default14, jM4567copywmQWz5c$default15, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultTextFieldForExposedDropdownMenusColors;
    }
}
