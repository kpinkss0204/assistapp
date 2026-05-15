package com.example.assistapp.features.Schedule;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowBackKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialThemeKt;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.assistapp.features.ScheduleSharing.ScheduleSendScreenKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScheduleSendScreenActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ComposableSingletons$ScheduleSendScreenActivityKt {
    public static final ComposableSingletons$ScheduleSendScreenActivityKt INSTANCE = new ComposableSingletons$ScheduleSendScreenActivityKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f88lambda1 = ComposableLambdaKt.composableLambdaInstance(1913858751, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C39@1669L27:ScheduleSendScreenActivity.kt#vnfy9");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1913858751, i, -1, "com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt.lambda-1.<anonymous> (ScheduleSendScreenActivity.kt:39)");
            }
            ScheduleSendScreenActivityKt.ScheduleSendScreenContent(composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f89lambda2 = ComposableLambdaKt.composableLambdaInstance(-818523117, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C38@1637L73:ScheduleSendScreenActivity.kt#vnfy9");
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-818523117, i, -1, "com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt.lambda-2.<anonymous> (ScheduleSendScreenActivity.kt:38)");
                }
                MaterialThemeKt.MaterialTheme(null, null, null, ComposableSingletons$ScheduleSendScreenActivityKt.INSTANCE.m7511getLambda1$app_release(), composer, 3072, 7);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f90lambda3 = ComposableLambdaKt.composableLambdaInstance(-719968635, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C54@1958L124:ScheduleSendScreenActivity.kt#vnfy9");
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-719968635, i, -1, "com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt.lambda-3.<anonymous> (ScheduleSendScreenActivity.kt:54)");
                }
                TextKt.m2996Text4IGK_g("일정 전송", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 196614, 0, 131038);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f91lambda4 = ComposableLambdaKt.composableLambdaInstance(960500260, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C63@2295L154:ScheduleSendScreenActivity.kt#vnfy9");
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(960500260, i, -1, "com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt.lambda-4.<anonymous> (ScheduleSendScreenActivity.kt:63)");
                }
                IconKt.m2453Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.INSTANCE.getDefault()), "뒤로가기", (Modifier) null, 0L, composer, 48, 12);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-5, reason: not valid java name */
    public static Function3<PaddingValues, Composer, Integer, Unit> f92lambda5 = ComposableLambdaKt.composableLambdaInstance(1714739542, false, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer, Integer num) {
            invoke(paddingValues, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(PaddingValues paddingValues, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
            ComposerKt.sourceInformation(composer, "C99@3711L202:ScheduleSendScreenActivity.kt#vnfy9");
            if ((i & 6) == 0) {
                i |= composer.changed(paddingValues) ? 4 : 2;
            }
            if ((i & 19) != 18 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1714739542, i, -1, "com.example.assistapp.features.Schedule.ComposableSingletons$ScheduleSendScreenActivityKt.lambda-5.<anonymous> (ScheduleSendScreenActivity.kt:99)");
                }
                Modifier modifierPadding = PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), paddingValues);
                ComposerKt.sourceInformationMarkerStart(composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierPadding);
                Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
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
                Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.Companion.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, 1529563769, "C105@3883L20:ScheduleSendScreenActivity.kt#vnfy9");
                ScheduleSendScreenKt.ScheduleSendScreen(composer, 0);
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

    /* JADX INFO: renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7511getLambda1$app_release() {
        return f88lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7512getLambda2$app_release() {
        return f89lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7513getLambda3$app_release() {
        return f90lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7514getLambda4$app_release() {
        return f91lambda4;
    }

    /* JADX INFO: renamed from: getLambda-5$app_release, reason: not valid java name */
    public final Function3<PaddingValues, Composer, Integer, Unit> m7515getLambda5$app_release() {
        return f92lambda5;
    }
}
