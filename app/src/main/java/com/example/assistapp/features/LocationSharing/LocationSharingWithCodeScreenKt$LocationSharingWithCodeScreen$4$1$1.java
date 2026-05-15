package com.example.assistapp.features.LocationSharing;

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

/* JADX INFO: compiled from: LocationSharingWithCodeScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$1 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<String> $inputKey$delegate;
    final /* synthetic */ MutableState<Boolean> $isTracking$delegate;

    LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$1(MutableState<String> mutableState, MutableState<Boolean> mutableState2) {
        this.$inputKey$delegate = mutableState;
        this.$isTracking$delegate = mutableState2;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C109@3695L17,107@3610L254:LocationSharingWithCodeScreen.kt#l7p0sd");
        if ((i & 17) != 16 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(379363951, i, -1, "com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreen.<anonymous>.<anonymous>.<anonymous> (LocationSharingWithCodeScreen.kt:107)");
            }
            String strLocationSharingWithCodeScreen$lambda$2 = LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$2(this.$inputKey$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            boolean z = !LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$11(this.$isTracking$delegate);
            composer.startReplaceGroup(-1522232573);
            ComposerKt.sourceInformation(composer, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            final MutableState<String> mutableState = this.$inputKey$delegate;
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$1.invoke$lambda$1$lambda$0(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceGroup();
            OutlinedTextFieldKt.OutlinedTextField(strLocationSharingWithCodeScreen$lambda$2, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, z, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$LocationSharingWithCodeScreenKt.INSTANCE.m7505getLambda1$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1573296, 0, 0, 8388528);
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
