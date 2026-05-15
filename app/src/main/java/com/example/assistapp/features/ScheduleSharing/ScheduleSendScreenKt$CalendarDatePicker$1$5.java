package com.example.assistapp.features.ScheduleSharing;

import androidx.compose.material3.ButtonKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ScheduleSendScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class ScheduleSendScreenKt$CalendarDatePicker$1$5 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ MutableState<Boolean> $showDialog$delegate;

    ScheduleSendScreenKt$CalendarDatePicker$1$5(MutableState<Boolean> mutableState) {
        this.$showDialog$delegate = mutableState;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(MutableState mutableState) {
        ScheduleSendScreenKt.CalendarDatePicker$lambda$21(mutableState, false);
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C236@8275L22,236@8254L59:ScheduleSendScreen.kt#l4w89x");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1800582946, i, -1, "com.example.assistapp.features.ScheduleSharing.CalendarDatePicker.<anonymous>.<anonymous> (ScheduleSendScreen.kt:236)");
        }
        composer.startReplaceGroup(147169345);
        ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreen.kt#9igjgp");
        final MutableState<Boolean> mutableState = this.$showDialog$delegate;
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$CalendarDatePicker$1$5$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ScheduleSendScreenKt$CalendarDatePicker$1$5.invoke$lambda$1$lambda$0(mutableState);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceGroup();
        ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ScheduleSendScreenKt.INSTANCE.m7524getLambda12$app_release(), composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
