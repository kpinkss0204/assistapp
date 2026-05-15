package com.example.assistapp.features.LocationSharing;

import android.content.Context;
import android.widget.Toast;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.ButtonKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.database.DatabaseReference;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocationSharingWithCodeScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$2 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ Context $context;
    final /* synthetic */ DatabaseReference $database;
    final /* synthetic */ MutableState<String> $inputKey$delegate;
    final /* synthetic */ MutableState<Boolean> $isTracking$delegate;
    final /* synthetic */ MutableState<String> $partnerKey$delegate;

    LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$2(DatabaseReference databaseReference, Context context, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3) {
        this.$database = databaseReference;
        this.$context = context;
        this.$inputKey$delegate = mutableState;
        this.$partnerKey$delegate = mutableState2;
        this.$isTracking$delegate = mutableState3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C118@3937L349,117@3903L559:LocationSharingWithCodeScreen.kt#l7p0sd");
        if ((i & 17) != 16 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1931458344, i, -1, "com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreen.<anonymous>.<anonymous>.<anonymous> (LocationSharingWithCodeScreen.kt:117)");
            }
            composer.startReplaceGroup(-1522224497);
            ComposerKt.sourceInformation(composer, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(this.$database) | composer.changedInstance(this.$context);
            final DatabaseReference databaseReference = this.$database;
            final Context context = this.$context;
            final MutableState<String> mutableState = this.$inputKey$delegate;
            final MutableState<String> mutableState2 = this.$partnerKey$delegate;
            final MutableState<Boolean> mutableState3 = this.$isTracking$delegate;
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$2.invoke$lambda$1$lambda$0(databaseReference, context, mutableState, mutableState2, mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function0 function0 = (Function0) objRememberedValue;
            composer.endReplaceGroup();
            ButtonKt.Button(function0, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), !LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$11(this.$isTracking$delegate) && LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$2(this.$inputKey$delegate).length() > 0, null, null, null, null, null, null, ComposableSingletons$LocationSharingWithCodeScreenKt.INSTANCE.m7506getLambda2$app_release(), composer, 805306416, TypedValues.PositionType.TYPE_PERCENT_HEIGHT);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(DatabaseReference databaseReference, Context context, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) throws NoSuchAlgorithmException {
        String safeDbKey = LocationSharingWithCodeScreenKt.toSafeDbKey(LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$2(mutableState));
        mutableState2.setValue(safeDbKey);
        LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$12(mutableState3, true);
        databaseReference.child(safeDbKey).child("watcher").setValue(true);
        Toast.makeText(context, "추적 시작", 0).show();
        return Unit.INSTANCE;
    }
}
