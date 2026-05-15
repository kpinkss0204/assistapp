package com.example.assistapp.features.ScheduleSharing;

import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.DatePickerState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ScheduleSendScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class ScheduleSendScreenKt$CalendarDatePicker$1$4 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ DatePickerState $datePickerState;
    final /* synthetic */ Function1<Long, Unit> $onDateSelected;
    final /* synthetic */ MutableState<Boolean> $showDialog$delegate;

    /* JADX WARN: Multi-variable type inference failed */
    ScheduleSendScreenKt$CalendarDatePicker$1$4(DatePickerState datePickerState, Function1<? super Long, Unit> function1, MutableState<Boolean> mutableState) {
        this.$datePickerState = datePickerState;
        this.$onDateSelected = function1;
        this.$showDialog$delegate = mutableState;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C228@7959L205,228@7938L242:ScheduleSendScreen.kt#l4w89x");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-946043488, i, -1, "com.example.assistapp.features.ScheduleSharing.CalendarDatePicker.<anonymous>.<anonymous> (ScheduleSendScreen.kt:228)");
        }
        composer.startReplaceGroup(147159416);
        ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreen.kt#9igjgp");
        boolean zChanged = composer.changed(this.$datePickerState) | composer.changed(this.$onDateSelected);
        final DatePickerState datePickerState = this.$datePickerState;
        final Function1<Long, Unit> function1 = this.$onDateSelected;
        final MutableState<Boolean> mutableState = this.$showDialog$delegate;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$CalendarDatePicker$1$4$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ScheduleSendScreenKt$CalendarDatePicker$1$4.invoke$lambda$2$lambda$1(datePickerState, function1, mutableState);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceGroup();
        ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ScheduleSendScreenKt.INSTANCE.m7523getLambda11$app_release(), composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$2$lambda$1(DatePickerState datePickerState, Function1 function1, MutableState mutableState) {
        Long selectedDateMillis = datePickerState.getSelectedDateMillis();
        if (selectedDateMillis != null) {
            function1.invoke(Long.valueOf(selectedDateMillis.longValue()));
        }
        ScheduleSendScreenKt.CalendarDatePicker$lambda$21(mutableState, false);
        return Unit.INSTANCE;
    }
}
