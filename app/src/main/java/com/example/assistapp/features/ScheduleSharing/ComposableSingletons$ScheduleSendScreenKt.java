package com.example.assistapp.features.ScheduleSharing;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScheduleSendScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ComposableSingletons$ScheduleSendScreenKt {
    public static final ComposableSingletons$ScheduleSendScreenKt INSTANCE = new ComposableSingletons$ScheduleSendScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f94lambda1 = ComposableLambdaKt.composableLambdaInstance(-1278878103, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation(composer, "C43@1561L10,43@1521L62:ScheduleSendScreen.kt#l4w89x");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1278878103, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-1.<anonymous> (ScheduleSendScreen.kt:43)");
            }
            TextKt.m2996Text4IGK_g("📤 일정 보내기", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleLarge(), composer, 6, 0, 65534);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f98lambda2 = ComposableLambdaKt.composableLambdaInstance(1486764434, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation(composer, "C46@1610L19:ScheduleSendScreen.kt#l4w89x");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1486764434, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-2.<anonymous> (ScheduleSendScreen.kt:46)");
            }
            DividerKt.m2375HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer, 0, 7);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f99lambda3 = ComposableLambdaKt.composableLambdaInstance(1741153841, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation(composer, "C49@1699L10,49@1660L62:ScheduleSendScreen.kt#l4w89x");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1741153841, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-3.<anonymous> (ScheduleSendScreen.kt:49)");
            }
            TextKt.m2996Text4IGK_g("일정 정보 입력", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleMedium(), composer, 6, 0, 65534);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f100lambda4 = ComposableLambdaKt.composableLambdaInstance(330578934, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C56@1891L16:ScheduleSendScreen.kt#l4w89x");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(330578934, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-4.<anonymous> (ScheduleSendScreen.kt:56)");
            }
            TextKt.m2996Text4IGK_g("상대방 암호코드", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-5, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f101lambda5 = ComposableLambdaKt.composableLambdaInstance(584968341, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C67@2211L13:ScheduleSendScreen.kt#l4w89x");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(584968341, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-5.<anonymous> (ScheduleSendScreen.kt:67)");
            }
            TextKt.m2996Text4IGK_g("일정 제목", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-6, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f102lambda6 = ComposableLambdaKt.composableLambdaInstance(-251826602, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C71@2385L16:ScheduleSendScreen.kt#l4w89x");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-251826602, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-6.<anonymous> (ScheduleSendScreen.kt:71)");
            }
            TextKt.m2996Text4IGK_g("예: 저녁 약속", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-7, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f103lambda7 = ComposableLambdaKt.composableLambdaInstance(1093747155, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-7$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C87@2791L25:ScheduleSendScreen.kt#l4w89x");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1093747155, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-7.<anonymous> (ScheduleSendScreen.kt:87)");
            }
            TextKt.m2996Text4IGK_g("시간 (HH:MM) - 선택사항", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-8, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f104lambda8 = ComposableLambdaKt.composableLambdaInstance(256952212, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-8$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C91@2977L16:ScheduleSendScreen.kt#l4w89x");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(256952212, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-8.<anonymous> (ScheduleSendScreen.kt:91)");
            }
            TextKt.m2996Text4IGK_g("예: 18:30", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-9, reason: not valid java name */
    public static Function3<ColumnScope, Composer, Integer, Unit> f105lambda9 = ComposableLambdaKt.composableLambdaInstance(1012739257, false, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-9$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
            invoke(columnScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(ColumnScope Card, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation(composer, "C178@6369L610:ScheduleSendScreen.kt#l4w89x");
            if ((i & 17) != 16 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1012739257, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-9.<anonymous> (ScheduleSendScreen.kt:178)");
                }
                Modifier modifierM965padding3ABfNKs = PaddingKt.m965padding3ABfNKs(Modifier.INSTANCE, Dp.m7027constructorimpl(16));
                ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
                ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM965padding3ABfNKs);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    composer.createNode(constructor);
                } else {
                    composer.useNode();
                }
                Composer composerM4013constructorimpl = Updater.m4013constructorimpl(composer);
                Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, 238601381, "C181@6522L10,179@6434L131,183@6586L40,189@6919L10,184@6647L314:ScheduleSendScreen.kt#l4w89x");
                TextKt.m2996Text4IGK_g("💡 사용 방법", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleSmall(), composer, 6, 0, 65534);
                SpacerKt.Spacer(SizeKt.m996height3ABfNKs(Modifier.INSTANCE, Dp.m7027constructorimpl(8)), composer, 6);
                TextKt.m2996Text4IGK_g("1. 상대방의 암호코드를 입력하세요\n2. 일정 제목과 날짜를 선택하세요\n3. 시간은 선택사항입니다\n4. 과거 날짜/시간은 등록할 수 없습니다", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 6, 0, 65534);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-10, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f95lambda10 = ComposableLambdaKt.composableLambdaInstance(-1027477013, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-10$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation(composer, "C175@6292L11,174@6229L107,173@6185L808:ScheduleSendScreen.kt#l4w89x");
            if ((i & 17) != 16 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1027477013, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-10.<anonymous> (ScheduleSendScreen.kt:173)");
                }
                CardKt.Card(null, null, CardDefaults.INSTANCE.m2132cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).m2228getSurfaceVariant0d7_KjU(), 0L, 0L, 0L, composer, CardDefaults.$stable << 12, 14), null, null, ComposableSingletons$ScheduleSendScreenKt.INSTANCE.m7532getLambda9$app_release(), composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 27);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-11, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f96lambda11 = ComposableLambdaKt.composableLambdaInstance(-1499824829, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-11$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation(composer, "C233@8168L10:ScheduleSendScreen.kt#l4w89x");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1499824829, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-11.<anonymous> (ScheduleSendScreen.kt:233)");
            }
            TextKt.m2996Text4IGK_g("확인", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-12, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f97lambda12 = ComposableLambdaKt.composableLambdaInstance(1940603009, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt$lambda-12$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation(composer, "C236@8301L10:ScheduleSendScreen.kt#l4w89x");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1940603009, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendScreenKt.lambda-12.<anonymous> (ScheduleSendScreen.kt:236)");
            }
            TextKt.m2996Text4IGK_g("취소", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7521getLambda1$app_release() {
        return f94lambda1;
    }

    /* JADX INFO: renamed from: getLambda-10$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7522getLambda10$app_release() {
        return f95lambda10;
    }

    /* JADX INFO: renamed from: getLambda-11$app_release, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7523getLambda11$app_release() {
        return f96lambda11;
    }

    /* JADX INFO: renamed from: getLambda-12$app_release, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7524getLambda12$app_release() {
        return f97lambda12;
    }

    /* JADX INFO: renamed from: getLambda-2$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7525getLambda2$app_release() {
        return f98lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7526getLambda3$app_release() {
        return f99lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7527getLambda4$app_release() {
        return f100lambda4;
    }

    /* JADX INFO: renamed from: getLambda-5$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7528getLambda5$app_release() {
        return f101lambda5;
    }

    /* JADX INFO: renamed from: getLambda-6$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7529getLambda6$app_release() {
        return f102lambda6;
    }

    /* JADX INFO: renamed from: getLambda-7$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7530getLambda7$app_release() {
        return f103lambda7;
    }

    /* JADX INFO: renamed from: getLambda-8$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7531getLambda8$app_release() {
        return f104lambda8;
    }

    /* JADX INFO: renamed from: getLambda-9$app_release, reason: not valid java name */
    public final Function3<ColumnScope, Composer, Integer, Unit> m7532getLambda9$app_release() {
        return f105lambda9;
    }
}
