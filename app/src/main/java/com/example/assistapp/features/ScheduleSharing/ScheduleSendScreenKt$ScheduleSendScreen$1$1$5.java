package com.example.assistapp.features.ScheduleSharing;

import android.content.Context;
import android.widget.Toast;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScheduleSendScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class ScheduleSendScreenKt$ScheduleSendScreen$1$1$5 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ Context $context;
    final /* synthetic */ FirebaseFirestore $firestore;
    final /* synthetic */ String $generatedKey;
    final /* synthetic */ MutableState<String> $inputKey$delegate;
    final /* synthetic */ MutableState<Boolean> $isSending$delegate;
    final /* synthetic */ MutableState<Long> $selectedDateMillis$delegate;
    final /* synthetic */ MutableState<String> $time$delegate;
    final /* synthetic */ MutableState<String> $title$delegate;

    ScheduleSendScreenKt$ScheduleSendScreen$1$1$5(Context context, String str, FirebaseFirestore firebaseFirestore, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Long> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5) {
        this.$context = context;
        this.$generatedKey = str;
        this.$firestore = firebaseFirestore;
        this.$inputKey$delegate = mutableState;
        this.$title$delegate = mutableState2;
        this.$selectedDateMillis$delegate = mutableState3;
        this.$time$delegate = mutableState4;
        this.$isSending$delegate = mutableState5;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C97@3082L2575,160@5762L384,96@3048L3098:ScheduleSendScreen.kt#l4w89x");
        if ((i & 17) != 16 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1281866420, i, -1, "com.example.assistapp.features.ScheduleSharing.ScheduleSendScreen.<anonymous>.<anonymous>.<anonymous> (ScheduleSendScreen.kt:96)");
            }
            composer.startReplaceGroup(-11596246);
            ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(this.$context) | composer.changed(this.$generatedKey) | composer.changedInstance(this.$firestore);
            final Context context = this.$context;
            final String str = this.$generatedKey;
            final FirebaseFirestore firebaseFirestore = this.$firestore;
            final MutableState<String> mutableState = this.$inputKey$delegate;
            final MutableState<String> mutableState2 = this.$title$delegate;
            final MutableState<Long> mutableState3 = this.$selectedDateMillis$delegate;
            final MutableState<String> mutableState4 = this.$time$delegate;
            final MutableState<Boolean> mutableState5 = this.$isSending$delegate;
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$ScheduleSendScreen$1$1$5$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ScheduleSendScreenKt$ScheduleSendScreen$1$1$5.invoke$lambda$4$lambda$3(context, str, firebaseFirestore, mutableState, mutableState2, mutableState3, mutableState4, mutableState5);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function0 function0 = (Function0) objRememberedValue;
            composer.endReplaceGroup();
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            boolean z = !ScheduleSendScreenKt.ScheduleSendScreen$lambda$14(this.$isSending$delegate);
            final MutableState<Boolean> mutableState6 = this.$isSending$delegate;
            ButtonKt.Button(function0, modifierFillMaxWidth$default, z, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-898909348, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$ScheduleSendScreen$1$1$5.2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer2, Integer num) {
                    invoke(rowScope, composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RowScope Button, Composer composer2, int i2) {
                    Intrinsics.checkNotNullParameter(Button, "$this$Button");
                    ComposerKt.sourceInformation(composer2, "C168@6085L47:ScheduleSendScreen.kt#l4w89x");
                    if ((i2 & 17) != 16 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-898909348, i2, -1, "com.example.assistapp.features.ScheduleSharing.ScheduleSendScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ScheduleSendScreen.kt:161)");
                        }
                        composer2.startReplaceGroup(1830627322);
                        ComposerKt.sourceInformation(composer2, "164@5947L11,162@5817L173,166@6011L39");
                        if (ScheduleSendScreenKt.ScheduleSendScreen$lambda$14(mutableState6)) {
                            ProgressIndicatorKt.m2670CircularProgressIndicatorLxG7B9w(SizeKt.m1010size3ABfNKs(Modifier.INSTANCE, Dp.m7027constructorimpl(20)), MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getOnPrimary(), 0.0f, 0L, 0, composer2, 6, 28);
                            SpacerKt.Spacer(SizeKt.m1015width3ABfNKs(Modifier.INSTANCE, Dp.m7027constructorimpl(8)), composer2, 6);
                        }
                        composer2.endReplaceGroup();
                        TextKt.m2996Text4IGK_g(ScheduleSendScreenKt.ScheduleSendScreen$lambda$14(mutableState6) ? "전송 중..." : "📤 일정 보내기", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composer, 54), composer, 805306416, TypedValues.PositionType.TYPE_PERCENT_HEIGHT);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$4$lambda$3(final Context context, String str, FirebaseFirestore firebaseFirestore, MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5) {
        if (ScheduleSendScreenKt.ScheduleSendScreen$lambda$2(mutableState).length() != 0) {
            if (ScheduleSendScreenKt.ScheduleSendScreen$lambda$5(mutableState2).length() != 0) {
                if (ScheduleSendScreenKt.ScheduleSendScreen$lambda$8(mutableState3) != null) {
                    if (ScheduleSendScreenKt.ScheduleSendScreen$lambda$11(mutableState4).length() <= 0 || ScheduleSendScreenKt.isValidTimeFormat(ScheduleSendScreenKt.ScheduleSendScreen$lambda$11(mutableState4))) {
                        Long lScheduleSendScreen$lambda$8 = ScheduleSendScreenKt.ScheduleSendScreen$lambda$8(mutableState3);
                        Intrinsics.checkNotNull(lScheduleSendScreen$lambda$8);
                        String date = ScheduleSendScreenKt.formatDate(lScheduleSendScreen$lambda$8.longValue());
                        if (!ScheduleSendScreenKt.isPastDateTime((ScheduleSendScreenKt.ScheduleSendScreen$lambda$11(mutableState4).length() > 0 ? new StringBuilder().append(date).append(" ").append(ScheduleSendScreenKt.ScheduleSendScreen$lambda$11(mutableState4)) : new StringBuilder().append(date).append(" 00:00")).toString())) {
                            ScheduleSendScreenKt.ScheduleSendScreen$lambda$15(mutableState5, true);
                            Task<DocumentReference> taskAdd = firebaseFirestore.collection("shared_schedules").document(ScheduleSendScreenKt.ScheduleSendScreen$lambda$2(mutableState)).collection(FirebaseAnalytics.Param.ITEMS).add(MapsKt.mapOf(TuplesKt.to("title", ScheduleSendScreenKt.ScheduleSendScreen$lambda$5(mutableState2)), TuplesKt.to("date", date), TuplesKt.to("time", ScheduleSendScreenKt.ScheduleSendScreen$lambda$11(mutableState4)), TuplesKt.to("createdAt", Timestamp.INSTANCE.now()), TuplesKt.to("senderKey", str)));
                            final Function1 function1 = new Function1() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$ScheduleSendScreen$1$1$5$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return ScheduleSendScreenKt$ScheduleSendScreen$1$1$5.invoke$lambda$4$lambda$3$lambda$0(context, mutableState5, mutableState2, mutableState3, mutableState4, (DocumentReference) obj);
                                }
                            };
                            taskAdd.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$ScheduleSendScreen$1$1$5$$ExternalSyntheticLambda2
                                @Override // com.google.android.gms.tasks.OnSuccessListener
                                public final void onSuccess(Object obj) {
                                    function1.invoke(obj);
                                }
                            }).addOnFailureListener(new OnFailureListener() { // from class: com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt$ScheduleSendScreen$1$1$5$$ExternalSyntheticLambda3
                                @Override // com.google.android.gms.tasks.OnFailureListener
                                public final void onFailure(Exception exc) {
                                    ScheduleSendScreenKt$ScheduleSendScreen$1$1$5.invoke$lambda$4$lambda$3$lambda$2(context, mutableState5, exc);
                                }
                            });
                            return Unit.INSTANCE;
                        }
                        Toast.makeText(context, "⚠️ 과거 날짜/시간은 등록할 수 없습니다", 1).show();
                        return Unit.INSTANCE;
                    }
                    Toast.makeText(context, "시간 형식이 올바르지 않습니다 (HH:MM)", 0).show();
                    return Unit.INSTANCE;
                }
                Toast.makeText(context, "날짜를 선택하세요", 0).show();
                return Unit.INSTANCE;
            }
            Toast.makeText(context, "일정 제목을 입력하세요", 0).show();
            return Unit.INSTANCE;
        }
        Toast.makeText(context, "상대방 암호코드를 입력하세요", 0).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$4$lambda$3$lambda$0(Context context, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, DocumentReference documentReference) {
        ScheduleSendScreenKt.ScheduleSendScreen$lambda$15(mutableState, false);
        Toast.makeText(context, "✅ 일정이 공유되었습니다!", 0).show();
        mutableState2.setValue("");
        mutableState3.setValue(null);
        mutableState4.setValue("");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$4$lambda$3$lambda$2(Context context, MutableState mutableState, Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        ScheduleSendScreenKt.ScheduleSendScreen$lambda$15(mutableState, false);
        Toast.makeText(context, "❌ 전송 실패: " + exception.getMessage(), 1).show();
    }
}
