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
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocationSharingWithCodeScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$3 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ Context $context;
    final /* synthetic */ DatabaseReference $database;
    final /* synthetic */ MutableState<Boolean> $isTracking$delegate;
    final /* synthetic */ MutableState<String> $partnerKey$delegate;
    final /* synthetic */ MutableState<Pair<Double, Double>> $partnerLocation$delegate;

    LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$3(DatabaseReference databaseReference, Context context, MutableState<String> mutableState, MutableState<Boolean> mutableState2, MutableState<Pair<Double, Double>> mutableState3) {
        this.$database = databaseReference;
        this.$context = context;
        this.$partnerKey$delegate = mutableState;
        this.$isTracking$delegate = mutableState2;
        this.$partnerLocation$delegate = mutableState3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C140@4582L389,139@4536L563:LocationSharingWithCodeScreen.kt#l7p0sd");
        if ((i & 17) != 16 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(614185012, i, -1, "com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreen.<anonymous>.<anonymous>.<anonymous> (LocationSharingWithCodeScreen.kt:139)");
            }
            composer.startReplaceGroup(-1522203817);
            ComposerKt.sourceInformation(composer, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(this.$database) | composer.changedInstance(this.$context);
            final Context context = this.$context;
            final MutableState<String> mutableState = this.$partnerKey$delegate;
            final DatabaseReference databaseReference = this.$database;
            final MutableState<Boolean> mutableState2 = this.$isTracking$delegate;
            final MutableState<Pair<Double, Double>> mutableState3 = this.$partnerLocation$delegate;
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$3$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$3.invoke$lambda$2$lambda$1(context, mutableState, databaseReference, mutableState2, mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceGroup();
            ButtonKt.OutlinedButton((Function0) objRememberedValue, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$LocationSharingWithCodeScreenKt.INSTANCE.m7507getLambda3$app_release(), composer, 805306416, TypedValues.PositionType.TYPE_CURVE_FIT);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$2$lambda$1(Context context, MutableState mutableState, DatabaseReference databaseReference, MutableState mutableState2, MutableState mutableState3) {
        String strLocationSharingWithCodeScreen$lambda$5 = LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$5(mutableState);
        if (strLocationSharingWithCodeScreen$lambda$5 != null) {
            databaseReference.child(strLocationSharingWithCodeScreen$lambda$5).child("watcher").setValue(false);
        }
        LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$12(mutableState2, false);
        mutableState.setValue(null);
        mutableState3.setValue(null);
        Toast.makeText(context, "추적 중단", 0).show();
        return Unit.INSTANCE;
    }
}
