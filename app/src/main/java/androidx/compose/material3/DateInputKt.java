package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DateInput.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u001ad\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2#\u0010\u000b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\u0010\u001a\u001a\u0098\u0001\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0013\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010 ¢\u0006\u0002\b!2\u0013\u0010\"\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010 ¢\u0006\u0002\b!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\n\u0010)\u001a\u00060*j\u0002`+2\u0006\u0010\u0018\u001a\u00020\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010-\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006.²\u0006\n\u0010/\u001a\u000200X\u008a\u008e\u0002"}, d2 = {"InputTextFieldPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getInputTextFieldPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "InputTextNonErroneousBottomPadding", "Landroidx/compose/ui/unit/Dp;", "F", "DateInputContent", "", "selectedDateMillis", "", "onDateSelectionChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "dateInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "colors", "Landroidx/compose/material3/DatePickerColors;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "DateInputTextField", "modifier", "Landroidx/compose/ui/Modifier;", "initialDateMillis", "label", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "inputIdentifier", "Landroidx/compose/material3/InputIdentifier;", "dateInputValidator", "Landroidx/compose/material3/DateInputValidator;", "dateInputFormat", "Landroidx/compose/material3/internal/DateInputFormat;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DateInputTextField-tQNruF0", "(Landroidx/compose/ui/Modifier;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ILandroidx/compose/material3/DateInputValidator;Landroidx/compose/material3/internal/DateInputFormat;Ljava/util/Locale;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "material3_release", "text", "Landroidx/compose/ui/text/input/TextFieldValue;"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class DateInputKt {
    private static final PaddingValues InputTextFieldPadding;
    private static final float InputTextNonErroneousBottomPadding = Dp.m7027constructorimpl(16);

    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static final void DateInputContent(final Long l, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        IntRange intRange2;
        Composer composer2;
        Locale locale;
        int i3;
        DateInputFormat dateInputFormat;
        ?? r0;
        Composer composer3;
        Composer composerStartRestartGroup = composer.startRestartGroup(643325609);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DateInputContent)P(5,3!1,6,2,4)62@2605L15,64@2655L75,65@2758L45,66@2838L44,67@2916L45,69@2999L551,82@3639L42,86@3836L164,92@4024L62,83@3686L901:DateInput.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            intRange2 = intRange;
            i2 |= composerStartRestartGroup.changedInstance(intRange2) ? 2048 : 1024;
        } else {
            intRange2 = intRange;
        }
        if ((i & 24576) == 0) {
            i2 |= (i & 32768) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(selectableDates) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? 1048576 : 524288;
        }
        if ((599187 & i2) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(643325609, i2, -1, "androidx.compose.material3.DateInputContent (DateInput.kt:60)");
            }
            Locale localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1825155115, "CC(remember):DateInput.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(localeDefaultLocale);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = calendarModel.getDateInputFormat(localeDefaultLocale);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            DateInputFormat dateInputFormat2 = (DateInputFormat) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Strings.Companion companion = Strings.INSTANCE;
            String strM3278getString2EP1pXo = Strings_androidKt.m3278getString2EP1pXo(Strings.m3208constructorimpl(R.string.m3c_date_input_invalid_for_pattern), composerStartRestartGroup, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            String strM3278getString2EP1pXo2 = Strings_androidKt.m3278getString2EP1pXo(Strings.m3208constructorimpl(R.string.m3c_date_input_invalid_year_range), composerStartRestartGroup, 0);
            Strings.Companion companion3 = Strings.INSTANCE;
            String strM3278getString2EP1pXo3 = Strings_androidKt.m3278getString2EP1pXo(Strings.m3208constructorimpl(R.string.m3c_date_input_invalid_not_allowed), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1825166599, "CC(remember):DateInput.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(dateInputFormat2) | ((57344 & i2) == 16384 || ((i2 & 32768) != 0 && composerStartRestartGroup.changed(datePickerFormatter)));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                composer2 = composerStartRestartGroup;
                locale = localeDefaultLocale;
                i3 = i2;
                IntRange intRange3 = intRange2;
                dateInputFormat = dateInputFormat2;
                r0 = 1;
                DateInputValidator dateInputValidator = new DateInputValidator(intRange3, selectableDates, dateInputFormat, datePickerFormatter, strM3278getString2EP1pXo, strM3278getString2EP1pXo2, strM3278getString2EP1pXo3, "", null, null, 768, null);
                composer2.updateRememberedValue(dateInputValidator);
                objRememberedValue2 = dateInputValidator;
            } else {
                composer2 = composerStartRestartGroup;
                i3 = i2;
                dateInputFormat = dateInputFormat2;
                locale = localeDefaultLocale;
                r0 = 1;
            }
            DateInputValidator dateInputValidator2 = (DateInputValidator) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            final String upperCase = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            Strings.Companion companion4 = Strings.INSTANCE;
            final String strM3278getString2EP1pXo4 = Strings_androidKt.m3278getString2EP1pXo(Strings.m3208constructorimpl(R.string.m3c_date_input_label), composer2, 0);
            Modifier modifierPadding = PaddingKt.padding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, r0, null), InputTextFieldPadding);
            int iM2478getSingleDateInputJ2x2o4M = InputIdentifier.INSTANCE.m2478getSingleDateInputJ2x2o4M();
            dateInputValidator2.setCurrentStartDateMillis$material3_release(l);
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1819015125, r0, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt.DateInputContent.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                    invoke(composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer4, int i4) {
                    ComposerKt.sourceInformation(composer4, "C89@3929L47,87@3850L140:DateInput.kt#uh7d8r");
                    if ((i4 & 3) != 2 || !composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1819015125, i4, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:87)");
                        }
                        String str = strM3278getString2EP1pXo4;
                        Modifier.Companion companion5 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer4, -49706674, "CC(remember):DateInput.kt#9igjgp");
                        boolean zChanged3 = composer4.changed(strM3278getString2EP1pXo4) | composer4.changed(upperCase);
                        final String str2 = strM3278getString2EP1pXo4;
                        final String str3 = upperCase;
                        Object objRememberedValue3 = composer4.rememberedValue();
                        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$2$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str2 + ", " + str3);
                                }
                            };
                            composer4.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        TextKt.m2996Text4IGK_g(str, SemanticsModifierKt.semantics$default(companion5, false, (Function1) objRememberedValue3, 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131068);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer4.skipToGroupEnd();
                }
            }, composer2, 54);
            ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-564233108, r0, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt.DateInputContent.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                    invoke(composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer4, int i4) {
                    ComposerKt.sourceInformation(composer4, "C92@4026L58:DateInput.kt#uh7d8r");
                    if ((i4 & 3) == 2 && composer4.getSkipping()) {
                        composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-564233108, i4, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:92)");
                    }
                    TextKt.m2996Text4IGK_g(upperCase, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateInputKt.DateInputContent.3.1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }
                    }), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer4, 0, 0, 131068);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composer2, 54);
            int i4 = i3 << 3;
            Composer composer4 = composer2;
            m2296DateInputTextFieldtQNruF0(modifierPadding, l, function1, calendarModel, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, iM2478getSingleDateInputJ2x2o4M, dateInputValidator2, dateInputFormat, locale, datePickerColors, composer4, (i4 & 112) | 1794054 | (i4 & 896) | (i4 & 7168), (i3 >> 18) & 14);
            composer3 = composer4;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer3 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt.DateInputContent.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                    invoke(composer5, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer5, int i5) {
                    DateInputKt.DateInputContent(l, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer5, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: renamed from: DateInputTextField-tQNruF0, reason: not valid java name */
    public static final void m2296DateInputTextFieldtQNruF0(final Modifier modifier, final Long l, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final int i, final DateInputValidator dateInputValidator, DateInputFormat dateInputFormat, final Locale locale, final DatePickerColors datePickerColors, Composer composer, final int i2, final int i3) {
        int i4;
        Function1<? super Long, Unit> function12;
        int i5;
        final MutableState mutableState;
        int i6;
        float fM7027constructorimpl;
        Composer composer2;
        final DateInputFormat dateInputFormat2 = dateInputFormat;
        Composer composerStartRestartGroup = composer.startRestartGroup(-857008589);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DateInputTextField)P(8,4,9!1,6,10,5:c#material3.InputIdentifier,3,2,7)122@5091L39,124@5207L488,124@5155L540,142@5766L1458,188@7730L60,191@7875L59,140@5701L2642:DateInput.kt#uh7d8r");
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(l) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            function12 = function1;
            i4 |= composerStartRestartGroup.changedInstance(function12) ? 256 : 128;
        } else {
            function12 = function1;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(calendarModel) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function22) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 1048576 : 524288;
        }
        if ((i2 & 12582912) == 0) {
            i4 |= composerStartRestartGroup.changed(dateInputValidator) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            i4 |= composerStartRestartGroup.changed(dateInputFormat2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i2 & 805306368) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(locale) ? 536870912 : 268435456;
        }
        if ((i3 & 6) == 0) {
            i5 = i3 | (composerStartRestartGroup.changed(datePickerColors) ? 4 : 2);
        } else {
            i5 = i3;
        }
        if ((i4 & 306783379) != 306783378 || (i5 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-857008589, i4, i5, "androidx.compose.material3.DateInputTextField (DateInput.kt:121)");
            }
            MutableState mutableState2 = (MutableState) RememberSaveableKt.m4139rememberSaveable(new Object[0], (Saver) null, (String) null, (Function0) new Function0<MutableState<String>>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$errorText$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final MutableState<String> invoke() {
                    return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                }
            }, composerStartRestartGroup, 3072, 6);
            Object[] objArr = new Object[0];
            Saver<TextFieldValue, Object> saver = TextFieldValue.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 236419420, "CC(remember):DateInput.kt#9igjgp");
            int i7 = 234881024 & i4;
            boolean zChangedInstance = ((i4 & 112) == 32) | composerStartRestartGroup.changedInstance(calendarModel) | (i7 == 67108864) | composerStartRestartGroup.changedInstance(locale);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function0) new Function0<MutableState<TextFieldValue>>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$text$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    /* JADX WARN: Removed duplicated region for block: B:6:0x001a  */
                    @Override // kotlin.jvm.functions.Function0
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final androidx.compose.runtime.MutableState<androidx.compose.ui.text.input.TextFieldValue> invoke() {
                        /*
                            r8 = this;
                            java.lang.Long r0 = r1
                            if (r0 == 0) goto L1a
                            androidx.compose.material3.internal.CalendarModel r1 = r2
                            androidx.compose.material3.internal.DateInputFormat r2 = r3
                            java.util.Locale r3 = r4
                            java.lang.Number r0 = (java.lang.Number) r0
                            long r4 = r0.longValue()
                            java.lang.String r0 = r2.getPatternWithoutDelimiters()
                            java.lang.String r0 = r1.formatWithPattern(r4, r0, r3)
                            if (r0 != 0) goto L1c
                        L1a:
                            java.lang.String r0 = ""
                        L1c:
                            r2 = r0
                            r0 = 0
                            long r3 = androidx.compose.ui.text.TextRangeKt.TextRange(r0, r0)
                            androidx.compose.ui.text.input.TextFieldValue r1 = new androidx.compose.ui.text.input.TextFieldValue
                            r5 = 0
                            r6 = 4
                            r7 = 0
                            r1.<init>(r2, r3, r5, r6, r7)
                            r0 = 2
                            r2 = 0
                            androidx.compose.runtime.MutableState r0 = androidx.compose.runtime.SnapshotStateKt.mutableStateOf$default(r1, r2, r0, r2)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateInputKt$DateInputTextField$text$2$1.invoke():androidx.compose.runtime.MutableState");
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableStateRememberSaveable = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) objRememberedValue, composerStartRestartGroup, 0, 4);
            TextFieldValue textFieldValueDateInputTextField_tQNruF0$lambda$4 = DateInputTextField_tQNruF0$lambda$4(mutableStateRememberSaveable);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 236438278, "CC(remember):DateInput.kt#9igjgp");
            boolean zChanged = ((i4 & 896) == 256) | (i7 == 67108864) | composerStartRestartGroup.changed(mutableStateRememberSaveable) | composerStartRestartGroup.changed(mutableState2) | composerStartRestartGroup.changedInstance(calendarModel) | ((29360128 & i4) == 8388608) | ((3670016 & i4) == 1048576) | composerStartRestartGroup.changedInstance(locale);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState2;
                i6 = i4;
                final Function1<? super Long, Unit> function13 = function12;
                Object obj = new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                        invoke2(textFieldValue);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextFieldValue textFieldValue) {
                        if (textFieldValue.getText().length() <= dateInputFormat2.getPatternWithoutDelimiters().length()) {
                            String text = textFieldValue.getText();
                            for (int i8 = 0; i8 < text.length(); i8++) {
                                if (!Character.isDigit(text.charAt(i8))) {
                                    return;
                                }
                            }
                            mutableStateRememberSaveable.setValue(textFieldValue);
                            String string = StringsKt.trim((CharSequence) textFieldValue.getText()).toString();
                            Long lValueOf = null;
                            if (string.length() == 0 || string.length() < dateInputFormat2.getPatternWithoutDelimiters().length()) {
                                mutableState.setValue("");
                                function13.invoke(null);
                                return;
                            }
                            CalendarDate calendarDate = calendarModel.parse(string, dateInputFormat2.getPatternWithoutDelimiters());
                            mutableState.setValue(dateInputValidator.m2297validateXivgLIo(calendarDate, i, locale));
                            Function1<Long, Unit> function14 = function13;
                            if (mutableState.getValue().length() == 0 && calendarDate != null) {
                                lValueOf = Long.valueOf(calendarDate.getUtcTimeMillis());
                            }
                            function14.invoke(lValueOf);
                        }
                    }
                };
                dateInputFormat2 = dateInputFormat2;
                objRememberedValue2 = (Function1) obj;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                mutableState = mutableState2;
                i6 = i4;
            }
            Function1 function14 = (Function1) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (StringsKt.isBlank((CharSequence) mutableState.getValue())) {
                fM7027constructorimpl = InputTextNonErroneousBottomPadding;
            } else {
                fM7027constructorimpl = Dp.m7027constructorimpl(0);
            }
            Modifier modifierM969paddingqDBjuR0$default = PaddingKt.m969paddingqDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, fM7027constructorimpl, 7, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 236499728, "CC(remember):DateInput.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(mutableState);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        if (StringsKt.isBlank(mutableState.getValue())) {
                            return;
                        }
                        SemanticsPropertiesKt.error(semanticsPropertyReceiver, mutableState.getValue());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            OutlinedTextFieldKt.OutlinedTextField(textFieldValueDateInputTextField_tQNruF0$lambda$4, (Function1<? super TextFieldValue, Unit>) function14, SemanticsModifierKt.semantics$default(modifierM969paddingqDBjuR0$default, false, (Function1) objRememberedValue3, 1, null), false, false, (TextStyle) null, function2, function22, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-591991974, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i8) {
                    ComposerKt.sourceInformation(composer3, "C191@7911L21:DateInput.kt#uh7d8r");
                    if ((i8 & 3) == 2 && composer3.getSkipping()) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-591991974, i8, -1, "androidx.compose.material3.DateInputTextField.<anonymous> (DateInput.kt:191)");
                    }
                    if (!StringsKt.isBlank(mutableState.getValue())) {
                        TextKt.m2996Text4IGK_g(mutableState.getValue(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131070);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), !StringsKt.isBlank((CharSequence) mutableState.getValue()), (VisualTransformation) new DateVisualTransformation(dateInputFormat2), new KeyboardOptions(0, (Boolean) false, KeyboardType.INSTANCE.m6747getNumberPjHm6EE(), ImeAction.INSTANCE.m6691getDoneeUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 113, (DefaultConstructorMarker) null), (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, datePickerColors.getDateTextFieldColors(), composer2, (i6 << 6) & 33030144, 12779904, 0, 4001592);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i8) {
                    DateInputKt.m2296DateInputTextFieldtQNruF0(modifier, l, function1, calendarModel, function2, function22, i, dateInputValidator, dateInputFormat2, locale, datePickerColors, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
                }
            });
        }
    }

    private static final TextFieldValue DateInputTextField_tQNruF0$lambda$4(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    public static final PaddingValues getInputTextFieldPadding() {
        return InputTextFieldPadding;
    }

    static {
        float f = 24;
        InputTextFieldPadding = PaddingKt.m962PaddingValuesa9UjIt4$default(Dp.m7027constructorimpl(f), Dp.m7027constructorimpl(10), Dp.m7027constructorimpl(f), 0.0f, 8, null);
    }
}
