package com.example.assistapp;

import android.content.Context;
import android.content.Intent;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.DateRangeKt;
import androidx.compose.material.icons.filled.HomeKt;
import androidx.compose.material.icons.filled.LocationOnKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SurfaceKt;
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
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.vector.ImageVector;
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
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.example.assistapp.MainActivityKt;
import com.example.assistapp.features.Schedule.ScheduleSendScreenActivity;
import com.example.assistapp.ui.WebViewScreenActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002\u001a)\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a7\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0007¢\u0006\u0002\u0010\u0013¨\u0006\u0014²\u0006\n\u0010\u0004\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0017X\u008a\u0084\u0002"}, d2 = {"MainScreen", "", "(Landroidx/compose/runtime/Composer;I)V", "BottomNavigationBar", "selectedIndex", "", "onTabSelected", "Lkotlin/Function1;", "(ILkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "BottomNavItem", "Landroidx/compose/foundation/layout/RowScope;", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "selected", "", "onClick", "Lkotlin/Function0;", "(Landroidx/compose/foundation/layout/RowScope;Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_release", "previousIndex", "scale", ""}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MainActivityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomNavItem$lambda$11(RowScope rowScope, ImageVector imageVector, String str, boolean z, Function0 function0, int i, Composer composer, int i2) {
        BottomNavItem(rowScope, imageVector, str, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomNavigationBar$lambda$7(int i, Function1 function1, int i2, Composer composer, int i3) {
        BottomNavigationBar(i, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$6(int i, Composer composer, int i2) {
        MainScreen(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void MainScreen(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1229595554);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MainScreen)46@1674L7,47@1707L30,48@1763L30,51@1829L987,76@2823L1996,50@1799L3020:MainActivity.kt#wr12u1");
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1229595554, i, -1, "com.example.assistapp.MainScreen (MainActivity.kt:45)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Context context = (Context) objConsume;
            composerStartRestartGroup.startReplaceGroup(1991813642);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(1991815434);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceGroup();
            ScaffoldKt.m2711ScaffoldTvnljyQ(null, null, ComposableLambdaKt.rememberComposableLambda(-509998621, true, new C06871(context, mutableState, (MutableState) objRememberedValue2), composerStartRestartGroup, 54), null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(-1447927763, true, new AnonymousClass2(mutableState), composerStartRestartGroup, 54), composerStartRestartGroup, 805306752, TypedValues.PositionType.TYPE_PERCENT_Y);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.MainActivityKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.MainScreen$lambda$6(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int MainScreen$lambda$1(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MainScreen$lambda$2(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MainScreen$lambda$5(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    /* JADX INFO: renamed from: com.example.assistapp.MainActivityKt$MainScreen$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class C06871 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ Context $context;
        final /* synthetic */ MutableState<Integer> $previousIndex$delegate;
        final /* synthetic */ MutableState<Integer> $selectedIndex$delegate;

        C06871(Context context, MutableState<Integer> mutableState, MutableState<Integer> mutableState2) {
            this.$context = context;
            this.$selectedIndex$delegate = mutableState;
            this.$previousIndex$delegate = mutableState2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C54@1943L849,52@1843L963:MainActivity.kt#wr12u1");
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-509998621, i, -1, "com.example.assistapp.MainScreen.<anonymous> (MainActivity.kt:52)");
                }
                int iMainScreen$lambda$1 = MainActivityKt.MainScreen$lambda$1(this.$selectedIndex$delegate);
                composer.startReplaceGroup(1932237500);
                ComposerKt.sourceInformation(composer, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance = composer.changedInstance(this.$context);
                final Context context = this.$context;
                final MutableState<Integer> mutableState = this.$selectedIndex$delegate;
                final MutableState<Integer> mutableState2 = this.$previousIndex$delegate;
                Object objRememberedValue = composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.example.assistapp.MainActivityKt$MainScreen$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivityKt.C06871.invoke$lambda$1$lambda$0(context, mutableState, mutableState2, ((Integer) obj).intValue());
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                MainActivityKt.BottomNavigationBar(iMainScreen$lambda$1, (Function1) objRememberedValue, composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$1$lambda$0(Context context, MutableState mutableState, MutableState mutableState2, int i) {
            if (i == 0) {
                MainActivityKt.MainScreen$lambda$5(mutableState2, MainActivityKt.MainScreen$lambda$1(mutableState));
                MainActivityKt.MainScreen$lambda$2(mutableState, 0);
            } else if (i == 1) {
                context.startActivity(new Intent(context, (Class<?>) ScheduleSendScreenActivity.class).addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
            } else if (i == 2) {
                context.startActivity(new Intent(context, (Class<?>) WebViewScreenActivity.class).addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.example.assistapp.MainActivityKt$MainScreen$2, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass2 implements Function3<PaddingValues, Composer, Integer, Unit> {
        final /* synthetic */ MutableState<Integer> $selectedIndex$delegate;

        AnonymousClass2(MutableState<Integer> mutableState) {
            this.$selectedIndex$delegate = mutableState;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int invoke$lambda$6$lambda$5$lambda$4$lambda$0(int i) {
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int invoke$lambda$6$lambda$5$lambda$4$lambda$1(int i) {
            return -i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int invoke$lambda$6$lambda$5$lambda$4$lambda$2(int i) {
            return -i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int invoke$lambda$6$lambda$5$lambda$4$lambda$3(int i) {
            return i;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer, Integer num) {
            invoke(paddingValues, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(PaddingValues paddingValues, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
            ComposerKt.sourceInformation(composer, "C77@2850L1963:MainActivity.kt#wr12u1");
            if ((i & 6) == 0) {
                i |= composer.changed(paddingValues) ? 4 : 2;
            }
            if ((i & 19) != 18 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1447927763, i, -1, "com.example.assistapp.MainScreen.<anonymous> (MainActivity.kt:77)");
                }
                Modifier modifierNavigationBarsPadding = WindowInsetsPadding_androidKt.navigationBarsPadding(BackgroundKt.m520backgroundbw27NRU$default(PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), paddingValues), Color.INSTANCE.m4605getWhite0d7_KjU(), null, 2, null));
                MutableState<Integer> mutableState = this.$selectedIndex$delegate;
                ComposerKt.sourceInformationMarkerStart(composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierNavigationBarsPadding);
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
                Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, -1860505622, "C87@3187L1368,85@3092L1711:MainActivity.kt#wr12u1");
                Integer numValueOf = Integer.valueOf(MainActivityKt.MainScreen$lambda$1(mutableState));
                composer.startReplaceGroup(-614202942);
                ComposerKt.sourceInformation(composer, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.example.assistapp.MainActivityKt$MainScreen$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivityKt.AnonymousClass2.invoke$lambda$6$lambda$5$lambda$4((AnimatedContentTransitionScope) obj);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                AnimatedContentKt.AnimatedContent(numValueOf, null, (Function1) objRememberedValue, null, "page_transition", null, ComposableSingletons$MainActivityKt.INSTANCE.m7500getLambda3$app_release(), composer, 1597824, 42);
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

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final ContentTransform invoke$lambda$6$lambda$5$lambda$4(AnimatedContentTransitionScope AnimatedContent) {
            Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
            if (((Number) AnimatedContent.getTargetState()).intValue() > ((Number) AnimatedContent.getInitialState()).intValue()) {
                return AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInHorizontally(AnimationSpecKt.tween$default(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null), new Function1() { // from class: com.example.assistapp.MainActivityKt$MainScreen$2$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MainActivityKt.AnonymousClass2.invoke$lambda$6$lambda$5$lambda$4$lambda$0(((Integer) obj).intValue()));
                    }
                }).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), 0.0f, 2, null)), EnterExitTransitionKt.slideOutHorizontally(AnimationSpecKt.tween$default(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null), new Function1() { // from class: com.example.assistapp.MainActivityKt$MainScreen$2$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(MainActivityKt.AnonymousClass2.invoke$lambda$6$lambda$5$lambda$4$lambda$1(((Integer) obj).intValue()));
                    }
                }).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), 0.0f, 2, null)));
            }
            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInHorizontally(AnimationSpecKt.tween$default(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null), new Function1() { // from class: com.example.assistapp.MainActivityKt$MainScreen$2$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(MainActivityKt.AnonymousClass2.invoke$lambda$6$lambda$5$lambda$4$lambda$2(((Integer) obj).intValue()));
                }
            }).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), 0.0f, 2, null)), EnterExitTransitionKt.slideOutHorizontally(AnimationSpecKt.tween$default(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null), new Function1() { // from class: com.example.assistapp.MainActivityKt$MainScreen$2$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(MainActivityKt.AnonymousClass2.invoke$lambda$6$lambda$5$lambda$4$lambda$3(((Integer) obj).intValue()));
                }
            }).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(300, 0, null, 6, null), 0.0f, 2, null)));
        }
    }

    public static final void BottomNavigationBar(final int i, final Function1<? super Integer, Unit> onTabSelected, Composer composer, final int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(onTabSelected, "onTabSelected");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1189394045);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BottomNavigationBar)P(1)133@5131L892,126@4925L1098:MainActivity.kt#wr12u1");
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onTabSelected) ? 32 : 16;
        }
        if ((i3 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1189394045, i3, -1, "com.example.assistapp.BottomNavigationBar (MainActivity.kt:125)");
            }
            SurfaceKt.m2846SurfaceT9BRK9s(WindowInsetsPadding_androidKt.navigationBarsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null)), null, ColorKt.Color(4294572537L), 0L, Dp.m7027constructorimpl(3), Dp.m7027constructorimpl(8), null, ComposableLambdaKt.rememberComposableLambda(1949504158, true, new AnonymousClass1(i, onTabSelected), composerStartRestartGroup, 54), composerStartRestartGroup, 12804480, 74);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.MainActivityKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.BottomNavigationBar$lambda$7(i, onTabSelected, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.example.assistapp.MainActivityKt$BottomNavigationBar$1, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ Function1<Integer, Unit> $onTabSelected;
        final /* synthetic */ int $selectedIndex;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(int i, Function1<? super Integer, Unit> function1) {
            this.$selectedIndex = i;
            this.$onTabSelected = function1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C134@5141L876:MainActivity.kt#wr12u1");
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1949504158, i, -1, "com.example.assistapp.BottomNavigationBar.<anonymous> (MainActivity.kt:134)");
                }
                Modifier modifierM996height3ABfNKs = SizeKt.m996height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7027constructorimpl(60));
                Arrangement.HorizontalOrVertical spaceEvenly = Arrangement.INSTANCE.getSpaceEvenly();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                int i2 = this.$selectedIndex;
                final Function1<Integer, Unit> function1 = this.$onTabSelected;
                ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceEvenly, centerVertically, composer, 54);
                ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM996height3ABfNKs);
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
                Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, 127570763, "C145@5553L20,141@5386L201,151@5766L20,147@5600L200,157@5973L20,153@5813L194:MainActivity.kt#wr12u1");
                ImageVector locationOn = LocationOnKt.getLocationOn(Icons.INSTANCE.getDefault());
                boolean z = i2 == 0;
                composer.startReplaceGroup(-1935542721);
                ComposerKt.sourceInformation(composer, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged = composer.changed(function1);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.example.assistapp.MainActivityKt$BottomNavigationBar$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AnonymousClass1.invoke$lambda$6$lambda$1$lambda$0(function1);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                MainActivityKt.BottomNavItem(rowScopeInstance, locationOn, "위치", z, (Function0) objRememberedValue, composer, 390);
                ImageVector dateRange = DateRangeKt.getDateRange(Icons.INSTANCE.getDefault());
                boolean z2 = i2 == 1;
                composer.startReplaceGroup(-1935535905);
                ComposerKt.sourceInformation(composer, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged2 = composer.changed(function1);
                Object objRememberedValue2 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.example.assistapp.MainActivityKt$BottomNavigationBar$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AnonymousClass1.invoke$lambda$6$lambda$3$lambda$2(function1);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue2);
                }
                composer.endReplaceGroup();
                MainActivityKt.BottomNavItem(rowScopeInstance, dateRange, "일정", z2, (Function0) objRememberedValue2, composer, 390);
                ImageVector home = HomeKt.getHome(Icons.INSTANCE.getDefault());
                boolean z3 = i2 == 2;
                composer.startReplaceGroup(-1935529281);
                ComposerKt.sourceInformation(composer, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChanged3 = composer.changed(function1);
                Object objRememberedValue3 = composer.rememberedValue();
                if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.example.assistapp.MainActivityKt$BottomNavigationBar$1$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.AnonymousClass1.invoke$lambda$6$lambda$5$lambda$4(function1);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue3);
                }
                composer.endReplaceGroup();
                MainActivityKt.BottomNavItem(rowScopeInstance, home, "웹", z3, (Function0) objRememberedValue3, composer, 390);
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

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$6$lambda$1$lambda$0(Function1 function1) {
            function1.invoke(0);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$6$lambda$3$lambda$2(Function1 function1) {
            function1.invoke(1);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$6$lambda$5$lambda$4(Function1 function1) {
            function1.invoke(2);
            return Unit.INSTANCE;
        }
    }

    public static final void BottomNavItem(final RowScope rowScope, final ImageVector icon, final String label, final boolean z, final Function0<Unit> onClick, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(rowScope, "<this>");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1480863021);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BottomNavItem)P(!2,3)174@6285L252,190@6762L79,183@6543L951:MainActivity.kt#wr12u1");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(rowScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(icon) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(label) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onClick) ? 16384 : 8192;
        }
        int i3 = i2;
        if ((i3 & 9363) != 9362 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1480863021, i3, -1, "com.example.assistapp.BottomNavItem (MainActivity.kt:169)");
            }
            long jColor = ColorKt.Color(4278190080L);
            long jColor2 = ColorKt.Color(4287137928L);
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.1f : 1.0f, AnimationSpecKt.spring$default(0.5f, 200.0f, null, 4, null), 0.0f, "icon_scale", null, composerStartRestartGroup, 3120, 20);
            Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 1, null);
            composerStartRestartGroup.startReplaceGroup(-343954093);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            Modifier modifierM551clickableO2vRcR0$default = ClickableKt.m551clickableO2vRcR0$default(modifierFillMaxHeight$default, (MutableInteractionSource) objRememberedValue, null, false, null, null, onClick, 28, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composerStartRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM551clickableO2vRcR0$default);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 827148582, "C195@6982L227,202@7218L40,203@7267L221:MainActivity.kt#wr12u1");
            IconKt.m2453Iconww6aTOc(icon, label, SizeKt.m1010size3ABfNKs(Modifier.INSTANCE, Dp.m7027constructorimpl(26 * BottomNavItem$lambda$8(stateAnimateFloatAsState))), z ? jColor : jColor2, composerStartRestartGroup, (i3 >> 3) & WebSocketProtocol.PAYLOAD_SHORT, 0);
            SpacerKt.Spacer(SizeKt.m996height3ABfNKs(Modifier.INSTANCE, Dp.m7027constructorimpl(2)), composerStartRestartGroup, 6);
            long sp = TextUnitKt.getSp(10);
            FontWeight.Companion companion = FontWeight.INSTANCE;
            FontWeight medium = z ? companion.getMedium() : companion.getNormal();
            if (!z) {
                jColor = jColor2;
            }
            composer2 = composerStartRestartGroup;
            TextKt.m2996Text4IGK_g(label, (Modifier) null, jColor, sp, (FontStyle) null, medium, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, ((i3 >> 6) & 14) | 3072, 0, 131026);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.MainActivityKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.BottomNavItem$lambda$11(rowScope, icon, label, z, onClick, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float BottomNavItem$lambda$8(State<Float> state) {
        return state.getValue().floatValue();
    }
}
