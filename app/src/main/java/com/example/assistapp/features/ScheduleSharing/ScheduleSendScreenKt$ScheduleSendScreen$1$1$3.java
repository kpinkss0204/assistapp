package com.example.assistapp.features.ScheduleSharing;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScheduleSendScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class ScheduleSendScreenKt$ScheduleSendScreen$1$1$3 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<Long> $selectedDateMillis$delegate;

    ScheduleSendScreenKt$ScheduleSendScreen$1$1$3(MutableState<Long> mutableState) {
        this.$selectedDateMillis$delegate = mutableState;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C79@2589L27,77@2485L145:ScheduleSendScreen.kt#l4w89x");
        if ((i & 17) != 16 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1790645234, i, -1, "com.example.assistapp.features.ScheduleSharing.ScheduleSendScreen.<anonymous>.<anonymous>.<anonymous> (ScheduleSendScreen.kt:77)");
            }
            Long lScheduleSendScreen$lambda$8 = ScheduleSendScreenKt.ScheduleSendScreen$lambda$8(this.$selectedDateMillis$delegate);
            composer.startReplaceGroup(-11614570);
            ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            final MutableState<Long> mutableState = this.$selectedDateMillis$delegate;
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$ScheduleSendScreen$1$1$3$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ScheduleSendScreenKt$ScheduleSendScreen$1$1$3.invoke$lambda$1$lambda$0(mutableState, ((Long) obj).longValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceGroup();
            ScheduleSendScreenKt.CalendarDatePicker(lScheduleSendScreen$lambda$8, (Function1) objRememberedValue, composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(MutableState mutableState, long j) {
        mutableState.setValue(Long.valueOf(j));
        return Unit.INSTANCE;
    }
}
