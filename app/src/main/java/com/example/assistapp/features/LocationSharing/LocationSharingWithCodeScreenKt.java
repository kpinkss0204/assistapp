package com.example.assistapp.features.LocationSharing;

import android.content.Context;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.LocalLifecycleOwnerKt;
import com.example.assistapp.ui.components.KakaoMapViewComposeKt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LocationSharingWithCodeScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\r\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\u0005¨\u0006\u0006²\u0006\n\u0010\u0007\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\f\u0010\b\u001a\u0004\u0018\u00010\u0001X\u008a\u008e\u0002²\u0006\u0018\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u008e\u0002"}, d2 = {"toSafeDbKey", "", "input", "LocationSharingWithCodeScreen", "", "(Landroidx/compose/runtime/Composer;I)V", "app_release", "inputKey", "partnerKey", "partnerLocation", "Lkotlin/Pair;", "", "isTracking", ""}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LocationSharingWithCodeScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LocationSharingWithCodeScreen$lambda$27(int i, Composer composer, int i2) {
        LocationSharingWithCodeScreen(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final String toSafeDbKey(String input) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(input, "input");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNull(bArrDigest);
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LocationSharingWithCodeScreenKt.toSafeDbKey$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence toSafeDbKey$lambda$0(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public static final void LocationSharingWithCodeScreen(Composer composer, final int i) {
        int i2;
        final MutableState mutableState;
        Composer composerStartRestartGroup = composer.startRestartGroup(-765990525);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LocationSharingWithCodeScreen)29@1091L7,32@1206L31,33@1260L42,34@1330L56,35@1409L34,37@1490L7,41@1630L3,39@1528L105,43@1660L430,43@1639L451,59@2141L678,59@2112L707,82@2890L561,82@2857L594,104@3580L2292,101@3457L2415:LocationSharingWithCodeScreen.kt#l7p0sd");
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-765990525, i, -1, "com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreen (LocationSharingWithCodeScreen.kt:27)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            final DatabaseReference databaseReferenceChild = FirebaseDatabase.getInstance().getReference().child("shared_locations");
            Intrinsics.checkNotNullExpressionValue(databaseReferenceChild, "child(...)");
            composerStartRestartGroup.startReplaceGroup(720900084);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(720901823);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue2;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(720904077);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue3;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(720906583);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue4;
            composerStartRestartGroup.endReplaceGroup();
            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final LifecycleOwner lifecycleOwner = (LifecycleOwner) objConsume2;
            ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissions = new ActivityResultContracts.RequestMultiplePermissions();
            composerStartRestartGroup.startReplaceGroup(720913624);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$14$lambda$13((Map) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            composerStartRestartGroup.endReplaceGroup();
            ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions, (Function1) objRememberedValue5, composerStartRestartGroup, 48);
            Unit unit = Unit.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(720915011);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult);
            LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1 locationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || locationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                locationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1RememberedValue = new LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1(context, managedActivityResultLauncherRememberLauncherForActivityResult, null);
                composerStartRestartGroup.updateRememberedValue(locationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1RememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) locationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1RememberedValue, composerStartRestartGroup, 6);
            String strLocationSharingWithCodeScreen$lambda$5 = LocationSharingWithCodeScreen$lambda$5(mutableState3);
            composerStartRestartGroup.startReplaceGroup(720930651);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(databaseReferenceChild);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$19$lambda$18(databaseReferenceChild, mutableState3, mutableState4, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.DisposableEffect(strLocationSharingWithCodeScreen$lambda$5, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue6, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceGroup(720954502);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(databaseReferenceChild) | composerStartRestartGroup.changedInstance(lifecycleOwner);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                i2 = 0;
                mutableState = mutableState4;
                Function1 function1 = new Function1() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$24$lambda$23(lifecycleOwner, mutableState3, databaseReferenceChild, mutableState5, mutableState, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function1);
                objRememberedValue7 = function1;
            } else {
                mutableState = mutableState4;
                i2 = 0;
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.DisposableEffect(lifecycleOwner, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue7, composerStartRestartGroup, i2);
            Modifier modifierM965padding3ABfNKs = PaddingKt.m965padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7027constructorimpl(16));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM845spacedBy0680j_4 = Arrangement.INSTANCE.m845spacedBy0680j_4(Dp.m7027constructorimpl(12));
            composerStartRestartGroup.startReplaceGroup(720978313);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):LocationSharingWithCodeScreen.kt#9igjgp");
            boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(databaseReferenceChild) | composerStartRestartGroup.changedInstance(context);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance4 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                final MutableState mutableState6 = mutableState;
                objRememberedValue8 = new Function1() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$26$lambda$25(mutableState2, mutableState5, databaseReferenceChild, context, mutableState3, mutableState6, (LazyListScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            composerStartRestartGroup.endReplaceGroup();
            LazyDslKt.LazyColumn(modifierM965padding3ABfNKs, null, null, false, horizontalOrVerticalM845spacedBy0680j_4, null, null, false, (Function1) objRememberedValue8, composerStartRestartGroup, 24582, 238);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$27(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LocationSharingWithCodeScreen$lambda$2(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LocationSharingWithCodeScreen$lambda$5(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Double, Double> LocationSharingWithCodeScreen$lambda$8(MutableState<Pair<Double, Double>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LocationSharingWithCodeScreen$lambda$11(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LocationSharingWithCodeScreen$lambda$12(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LocationSharingWithCodeScreen$lambda$14$lambda$13(Map it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$2$1$listener$1] */
    public static final DisposableEffectResult LocationSharingWithCodeScreen$lambda$19$lambda$18(final DatabaseReference databaseReference, MutableState mutableState, final MutableState mutableState2, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final String strLocationSharingWithCodeScreen$lambda$5 = LocationSharingWithCodeScreen$lambda$5(mutableState);
        if (strLocationSharingWithCodeScreen$lambda$5 != null) {
            final ?? r4 = new ValueEventListener() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$2$1$listener$1
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot snapshot) {
                    Intrinsics.checkNotNullParameter(snapshot, "snapshot");
                    Double d = (Double) snapshot.child("lat").getValue(Double.TYPE);
                    Double d2 = (Double) snapshot.child("lon").getValue(Double.TYPE);
                    mutableState2.setValue((d == null || d2 == null) ? null : TuplesKt.to(d, d2));
                }
            };
            databaseReference.child(strLocationSharingWithCodeScreen$lambda$5).addValueEventListener((ValueEventListener) r4);
            return new DisposableEffectResult() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$lambda$19$lambda$18$$inlined$onDispose$2
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    databaseReference.child(strLocationSharingWithCodeScreen$lambda$5).removeEventListener(r4);
                }
            };
        }
        return new DisposableEffectResult() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$lambda$19$lambda$18$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LocationSharingWithCodeScreen$lambda$24$lambda$23(final LifecycleOwner lifecycleOwner, final MutableState mutableState, final DatabaseReference databaseReference, final MutableState mutableState2, final MutableState mutableState3, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$24$lambda$23$lambda$21(mutableState, databaseReference, mutableState2, mutableState3, lifecycleOwner2, event);
            }
        };
        lifecycleOwner.getLifecycle().addObserver(lifecycleEventObserver);
        return new DisposableEffectResult() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$lambda$24$lambda$23$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                lifecycleOwner.getLifecycle().removeObserver(lifecycleEventObserver);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LocationSharingWithCodeScreen$lambda$24$lambda$23$lambda$21(MutableState mutableState, DatabaseReference databaseReference, MutableState mutableState2, MutableState mutableState3, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_STOP || event == Lifecycle.Event.ON_PAUSE) {
            String strLocationSharingWithCodeScreen$lambda$5 = LocationSharingWithCodeScreen$lambda$5(mutableState);
            if (strLocationSharingWithCodeScreen$lambda$5 != null) {
                databaseReference.child(strLocationSharingWithCodeScreen$lambda$5).child("watcher").setValue(false);
            }
            LocationSharingWithCodeScreen$lambda$12(mutableState2, false);
            mutableState.setValue(null);
            mutableState3.setValue(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LocationSharingWithCodeScreen$lambda$26$lambda$25(MutableState mutableState, MutableState mutableState2, DatabaseReference databaseReference, Context context, MutableState mutableState3, final MutableState mutableState4, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(379363951, true, new LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$1(mutableState, mutableState2)), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1931458344, true, new LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$2(databaseReference, context, mutableState, mutableState3, mutableState2)), 3, null);
        if (LocationSharingWithCodeScreen$lambda$11(mutableState2)) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(614185012, true, new LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$3(databaseReference, context, mutableState3, mutableState2, mutableState4)), 3, null);
            if (LocationSharingWithCodeScreen$lambda$8(mutableState4) != null) {
                LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1451367353, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$4
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                        invoke(lazyItemScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LazyItemScope item, Composer composer, int i) {
                        Intrinsics.checkNotNullParameter(item, "$this$item");
                        ComposerKt.sourceInformation(composer, "C159@5201L38,160@5260L39:LocationSharingWithCodeScreen.kt#l7p0sd");
                        if ((i & 17) != 16 || !composer.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1451367353, i, -1, "com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreen.<anonymous>.<anonymous>.<anonymous> (LocationSharingWithCodeScreen.kt:159)");
                            }
                            Pair pairLocationSharingWithCodeScreen$lambda$8 = LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$8(mutableState4);
                            Intrinsics.checkNotNull(pairLocationSharingWithCodeScreen$lambda$8);
                            TextKt.m2996Text4IGK_g("위도: " + pairLocationSharingWithCodeScreen$lambda$8.getFirst(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
                            Pair pairLocationSharingWithCodeScreen$lambda$82 = LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$8(mutableState4);
                            Intrinsics.checkNotNull(pairLocationSharingWithCodeScreen$lambda$82);
                            TextKt.m2996Text4IGK_g("경도: " + pairLocationSharingWithCodeScreen$lambda$82.getSecond(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer.skipToGroupEnd();
                    }
                }), 3, null);
                LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1731425186, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$4$1$5
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                        invoke(lazyItemScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LazyItemScope item, Composer composer, int i) {
                        Intrinsics.checkNotNullParameter(item, "$this$item");
                        ComposerKt.sourceInformation(composer, "C164@5362L320:LocationSharingWithCodeScreen.kt#l7p0sd");
                        if ((i & 17) != 16 || !composer.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1731425186, i, -1, "com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreen.<anonymous>.<anonymous>.<anonymous> (LocationSharingWithCodeScreen.kt:164)");
                            }
                            Pair pairLocationSharingWithCodeScreen$lambda$8 = LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$8(mutableState4);
                            Intrinsics.checkNotNull(pairLocationSharingWithCodeScreen$lambda$8);
                            double dDoubleValue = ((Number) pairLocationSharingWithCodeScreen$lambda$8.getFirst()).doubleValue();
                            Pair pairLocationSharingWithCodeScreen$lambda$82 = LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen$lambda$8(mutableState4);
                            Intrinsics.checkNotNull(pairLocationSharingWithCodeScreen$lambda$82);
                            KakaoMapViewComposeKt.KakaoMapViewCompose(dDoubleValue, ((Number) pairLocationSharingWithCodeScreen$lambda$82.getSecond()).doubleValue(), 15, SizeKt.m996height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7027constructorimpl(300)), composer, 3456, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer.skipToGroupEnd();
                    }
                }), 3, null);
            } else {
                LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$LocationSharingWithCodeScreenKt.INSTANCE.m7508getLambda4$app_release(), 3, null);
            }
        }
        return Unit.INSTANCE;
    }
}
