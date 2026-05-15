package com.example.assistapp.features.ScheduleSharing;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScheduleSendScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class ScheduleSendScreenKt$ScheduleSendScreen$1$1$4 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<Boolean> $isSending$delegate;
    final /* synthetic */ MutableState<String> $time$delegate;

    ScheduleSendScreenKt$ScheduleSendScreen$1$1$4(MutableState<String> mutableState, MutableState<Boolean> mutableState2) {
        this.$time$delegate = mutableState;
        this.$isSending$delegate = mutableState2;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C86@2750L13,84@2669L340:ScheduleSendScreen.kt#l4w89x");
        if ((i & 17) != 16 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1536255827, i, -1, "com.example.assistapp.features.ScheduleSharing.ScheduleSendScreen.<anonymous>.<anonymous>.<anonymous> (ScheduleSendScreen.kt:84)");
            }
            String strScheduleSendScreen$lambda$11 = ScheduleSendScreenKt.ScheduleSendScreen$lambda$11(this.$time$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            boolean z = !ScheduleSendScreenKt.ScheduleSendScreen$lambda$14(this.$isSending$delegate);
            composer.startReplaceGroup(-11609432);
            ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            final MutableState<String> mutableState = this.$time$delegate;
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$ScheduleSendScreen$1$1$4$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ScheduleSendScreenKt$ScheduleSendScreen$1$1$4.invoke$lambda$1$lambda$0(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceGroup();
            OutlinedTextFieldKt.OutlinedTextField(strScheduleSendScreen$lambda$11, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, z, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ScheduleSendScreenKt.INSTANCE.m7530getLambda7$app_release(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ScheduleSendScreenKt.INSTANCE.m7531getLambda8$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 14156208, 12582912, 0, 8257328);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }
}
