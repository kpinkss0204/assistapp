package com.example.assistapp.features.ScheduleSharing;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.DateRangeKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.DatePickerDialog_androidKt;
import androidx.compose.material3.DatePickerKt;
import androidx.compose.material3.DatePickerState;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: ScheduleSendScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002\u001a+\u0010\u0003\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005\u001a\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\n\u001a\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\n¨\u0006\u0011²\u0006\n\u0010\u0012\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\nX\u008a\u008e\u0002²\u0006\f\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\rX\u008a\u008e\u0002"}, d2 = {"ScheduleSendScreen", "", "(Landroidx/compose/runtime/Composer;I)V", "CalendarDatePicker", "selectedDate", "", "onDateSelected", "Lkotlin/Function1;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "formatDate", "", "timeMillis", "isValidTimeFormat", "", "time", "isPastDateTime", "dateTimeString", "app_release", "inputKey", "title", "selectedDateMillis", "isSending", "showDialog"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ScheduleSendScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CalendarDatePicker$lambda$27(Long l, Function1 function1, int i, Composer composer, int i2) {
        CalendarDatePicker(l, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleSendScreen$lambda$18(int i, Composer composer, int i2) {
        ScheduleSendScreen(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ScheduleSendScreen(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1192209533);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScheduleSendScreen)21@757L7,25@859L101,30@1060L31,31@1109L31,32@1171L40,33@1239L31,34@1292L34,41@1492L5517,36@1332L5677:ScheduleSendScreen.kt#l4w89x");
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1192209533, i, -1, "com.example.assistapp.features.ScheduleSharing.ScheduleSendScreen (ScheduleSendScreen.kt:20)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance(...)");
            composerStartRestartGroup.startReplaceGroup(-599370973);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = context.getSharedPreferences("location_sharing_prefs", 0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            String string = ((SharedPreferences) objRememberedValue).getString("generated_key", "");
            final String str = string == null ? "" : string;
            composerStartRestartGroup.startReplaceGroup(-599364611);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState = (MutableState) objRememberedValue2;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(-599363043);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue3;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(-599361050);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue4;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(-599358883);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue5;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(-599357184);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue6;
            composerStartRestartGroup.endReplaceGroup();
            Modifier modifierM965padding3ABfNKs = PaddingKt.m965padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7027constructorimpl(16));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM845spacedBy0680j_4 = Arrangement.INSTANCE.m845spacedBy0680j_4(Dp.m7027constructorimpl(12));
            composerStartRestartGroup.startReplaceGroup(-599345301);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changed(str) | composerStartRestartGroup.changedInstance(firebaseFirestore);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Function1 function1 = new Function1() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ScheduleSendScreenKt.ScheduleSendScreen$lambda$17$lambda$16(mutableState, mutableState5, mutableState2, mutableState3, mutableState4, context, str, firebaseFirestore, (LazyListScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function1);
                objRememberedValue7 = function1;
            }
            composerStartRestartGroup.endReplaceGroup();
            LazyDslKt.LazyColumn(modifierM965padding3ABfNKs, null, null, false, horizontalOrVerticalM845spacedBy0680j_4, null, null, false, (Function1) objRememberedValue7, composerStartRestartGroup, 24582, 238);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScheduleSendScreenKt.ScheduleSendScreen$lambda$18(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ScheduleSendScreen$lambda$2(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ScheduleSendScreen$lambda$5(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long ScheduleSendScreen$lambda$8(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ScheduleSendScreen$lambda$11(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ScheduleSendScreen$lambda$14(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ScheduleSendScreen$lambda$15(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleSendScreen$lambda$17$lambda$16(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, Context context, String str, FirebaseFirestore firebaseFirestore, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$ScheduleSendScreenKt.INSTANCE.m7521getLambda1$app_release(), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$ScheduleSendScreenKt.INSTANCE.m7525getLambda2$app_release(), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$ScheduleSendScreenKt.INSTANCE.m7526getLambda3$app_release(), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1995543248, true, new ScheduleSendScreenKt$ScheduleSendScreen$1$1$1(mutableState, mutableState2)), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-2045034641, true, new ScheduleSendScreenKt$ScheduleSendScreen$1$1$2(mutableState3, mutableState2)), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1790645234, true, new ScheduleSendScreenKt$ScheduleSendScreen$1$1$3(mutableState4)), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1536255827, true, new ScheduleSendScreenKt$ScheduleSendScreen$1$1$4(mutableState5, mutableState2)), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1281866420, true, new ScheduleSendScreenKt$ScheduleSendScreen$1$1$5(context, str, firebaseFirestore, mutableState, mutableState3, mutableState4, mutableState5, mutableState2)), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$ScheduleSendScreenKt.INSTANCE.m7522getLambda10$app_release(), 3, null);
        return Unit.INSTANCE;
    }

    public static final void CalendarDatePicker(final Long l, final Function1<? super Long, Unit> onDateSelected, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(onDateSelected, "onDateSelected");
        Composer composerStartRestartGroup = composer.startRestartGroup(1089407971);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CalendarDatePicker)P(1)203@7175L34,205@7237L79,209@7322L1107:ScheduleSendScreen.kt#l4w89x");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onDateSelected) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1089407971, i2, -1, "com.example.assistapp.features.ScheduleSharing.CalendarDatePicker (ScheduleSendScreen.kt:202)");
            }
            composerStartRestartGroup.startReplaceGroup(-1931579313);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            final DatePickerState datePickerStateM2336rememberDatePickerStateEU0dCGE = DatePickerKt.m2336rememberDatePickerStateEU0dCGE(l, null, null, 0, null, composerStartRestartGroup, i2 & 14, 30);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4013constructorimpl = Updater.m4013constructorimpl(composerStartRestartGroup);
            Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -66736741, "C211@7377L21,213@7457L310,210@7339L428:ScheduleSendScreen.kt#l4w89x");
            composerStartRestartGroup.startReplaceGroup(-1941815295);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ScheduleSendScreenKt.CalendarDatePicker$lambda$26$lambda$23$lambda$22(mutableState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceGroup();
            ButtonKt.OutlinedButton((Function0) objRememberedValue2, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-936844549, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$CalendarDatePicker$1$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer2, Integer num) {
                    invoke(rowScope, composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RowScope OutlinedButton, Composer composer2, int i3) {
                    String date;
                    Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
                    ComposerKt.sourceInformation(composer2, "C214@7471L56,215@7540L39,216@7592L165:ScheduleSendScreen.kt#l4w89x");
                    if ((i3 & 17) != 16 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-936844549, i3, -1, "com.example.assistapp.features.ScheduleSharing.CalendarDatePicker.<anonymous>.<anonymous> (ScheduleSendScreen.kt:214)");
                        }
                        IconKt.m2453Iconww6aTOc(DateRangeKt.getDateRange(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, composer2, 48, 12);
                        SpacerKt.Spacer(SizeKt.m1015width3ABfNKs(Modifier.INSTANCE, Dp.m7027constructorimpl(8)), composer2, 6);
                        Long l2 = l;
                        if (l2 == null) {
                            date = "📅 날짜 선택";
                        } else {
                            date = ScheduleSendScreenKt.formatDate(l2.longValue());
                        }
                        TextKt.m2996Text4IGK_g(date, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 805306422, TypedValues.PositionType.TYPE_CURVE_FIT);
            composerStartRestartGroup.startReplaceGroup(-1941801870);
            ComposerKt.sourceInformation(composerStartRestartGroup, "226@7860L22,227@7916L282,235@8232L99,238@8346L67,225@7807L606");
            if (CalendarDatePicker$lambda$20(mutableState)) {
                composerStartRestartGroup.startReplaceGroup(-1941799838);
                ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreen.kt#9igjgp");
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ScheduleSendScreenKt.CalendarDatePicker$lambda$26$lambda$25$lambda$24(mutableState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                composerStartRestartGroup.endReplaceGroup();
                DatePickerDialog_androidKt.m2328DatePickerDialogGmEhDVc((Function0) objRememberedValue3, ComposableLambdaKt.rememberComposableLambda(-946043488, true, new ScheduleSendScreenKt$CalendarDatePicker$1$4(datePickerStateM2336rememberDatePickerStateEU0dCGE, onDateSelected, mutableState), composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(-1800582946, true, new ScheduleSendScreenKt$CalendarDatePicker$1$5(mutableState), composerStartRestartGroup, 54), null, 0.0f, null, null, ComposableLambdaKt.rememberComposableLambda(1441012585, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$CalendarDatePicker$1$6
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer2, Integer num) {
                        invoke(columnScope, composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(ColumnScope DatePickerDialog, Composer composer2, int i3) {
                        Intrinsics.checkNotNullParameter(DatePickerDialog, "$this$DatePickerDialog");
                        ComposerKt.sourceInformation(composer2, "C239@8364L35:ScheduleSendScreen.kt#l4w89x");
                        if ((i3 & 17) == 16 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1441012585, i3, -1, "com.example.assistapp.features.ScheduleSharing.CalendarDatePicker.<anonymous>.<anonymous> (ScheduleSendScreen.kt:239)");
                        }
                        DatePickerKt.DatePicker(datePickerStateM2336rememberDatePickerStateEU0dCGE, null, null, null, null, false, null, composer2, 0, WebSocketProtocol.PAYLOAD_SHORT);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 100666422, 244);
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScheduleSendScreenKt.CalendarDatePicker$lambda$27(l, onDateSelected, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean CalendarDatePicker$lambda$20(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CalendarDatePicker$lambda$21(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CalendarDatePicker$lambda$26$lambda$23$lambda$22(MutableState mutableState) {
        CalendarDatePicker$lambda$21(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CalendarDatePicker$lambda$26$lambda$25$lambda$24(MutableState mutableState) {
        CalendarDatePicker$lambda$21(mutableState, false);
        return Unit.INSTANCE;
    }

    public static final String formatDate(long j) {
        String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(j));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public static final boolean isValidTimeFormat(String time) {
        Intrinsics.checkNotNullParameter(time, "time");
        return new Regex("^([0-1][0-9]|2[0-3]):[0-5][0-9]$").matches(time);
    }

    public static final boolean isPastDateTime(String dateTimeString) {
        Intrinsics.checkNotNullParameter(dateTimeString, "dateTimeString");
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).parse(dateTimeString);
            if (date == null) {
                return false;
            }
            return date.before(new Date());
        } catch (Exception unused) {
            return false;
        }
    }
}
