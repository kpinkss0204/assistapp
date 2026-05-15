package com.example.assistapp.features.Schedule;

import android.content.Context;
import android.content.Intent;
import androidx.activity.ComponentActivity;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
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
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
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
import androidx.profileinstaller.ProfileVerifier;
import com.example.assistapp.MainActivity;
import com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt;
import com.example.assistapp.ui.WebViewScreenActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: ScheduleSendScreenActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002\u001a)\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a7\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0007¢\u0006\u0002\u0010\u0013¨\u0006\u0014²\u0006\n\u0010\u0015\u001a\u00020\u0016X\u008a\u0084\u0002"}, d2 = {"ScheduleSendScreenContent", "", "(Landroidx/compose/runtime/Composer;I)V", "BottomNavigationBar", "selectedIndex", "", "onTabSelected", "Lkotlin/Function1;", "(ILkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "BottomNavItem", "Landroidx/compose/foundation/layout/RowScope;", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "selected", "", "onClick", "Lkotlin/Function0;", "(Landroidx/compose/foundation/layout/RowScope;Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_release", "scale", ""}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ScheduleSendScreenActivityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomNavItem$lambda$5(RowScope rowScope, ImageVector imageVector, String str, boolean z, Function0 function0, int i, Composer composer, int i2) {
        BottomNavItem(rowScope, imageVector, str, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomNavigationBar$lambda$1(int i, Function1 function1, int i2, Composer composer, int i3) {
        BottomNavigationBar(i, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleSendScreenContent$lambda$0(int i, Composer composer, int i2) {
        ScheduleSendScreenContent(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ScheduleSendScreenContent(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-721233659);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScheduleSendScreenContent)48@1847L7,51@1887L754,74@2663L1014,50@1860L2059:ScheduleSendScreenActivity.kt#vnfy9");
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-721233659, i, -1, "com.example.assistapp.features.Schedule.ScheduleSendScreenContent (ScheduleSendScreenActivity.kt:47)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            ScaffoldKt.m2711ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(-1506143039, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt.ScheduleSendScreenContent.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i2) {
                    ComposerKt.sourceInformation(composer2, "C59@2135L354,69@2534L83,52@1901L730:ScheduleSendScreenActivity.kt#vnfy9");
                    if ((i2 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1506143039, i2, -1, "com.example.assistapp.features.Schedule.ScheduleSendScreenContent.<anonymous> (ScheduleSendScreenActivity.kt:52)");
                        }
                        AppBarKt.m2076TopAppBarGHTll3U(ComposableSingletons$ScheduleSendScreenActivityKt.INSTANCE.m7513getLambda3$app_release(), null, ComposableLambdaKt.rememberComposableLambda(-192574585, true, new C01631(context), composer2, 54), null, 0.0f, null, TopAppBarDefaults.INSTANCE.m3167topAppBarColorszjMxDiM(Color.INSTANCE.m4605getWhite0d7_KjU(), 0L, 0L, 0L, 0L, composer2, (TopAppBarDefaults.$stable << 15) | 6, 30), null, composer2, 390, 186);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }

                /* JADX INFO: renamed from: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$ScheduleSendScreenContent$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: ScheduleSendScreenActivity.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                static final class C01631 implements Function2<Composer, Integer, Unit> {
                    final /* synthetic */ Context $context;

                    C01631(Context context) {
                        this.$context = context;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer, int i) {
                        ComposerKt.sourceInformation(composer, "C60@2178L89,60@2157L314:ScheduleSendScreenActivity.kt#vnfy9");
                        if ((i & 3) == 2 && composer.getSkipping()) {
                            composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-192574585, i, -1, "com.example.assistapp.features.Schedule.ScheduleSendScreenContent.<anonymous>.<anonymous> (ScheduleSendScreenActivity.kt:60)");
                        }
                        composer.startReplaceGroup(1337601144);
                        ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreenActivity.kt#9igjgp");
                        boolean zChangedInstance = composer.changedInstance(this.$context);
                        final Context context = this.$context;
                        Object objRememberedValue = composer.rememberedValue();
                        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function0() { // from class: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$ScheduleSendScreenContent$1$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return ScheduleSendScreenActivityKt.C06881.C01631.invoke$lambda$1$lambda$0(context);
                                }
                            };
                            composer.updateRememberedValue(objRememberedValue);
                        }
                        composer.endReplaceGroup();
                        IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, ComposableSingletons$ScheduleSendScreenActivityKt.INSTANCE.m7514getLambda4$app_release(), composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final Unit invoke$lambda$1$lambda$0(Context context) {
                        ComponentActivity componentActivity = context instanceof ComponentActivity ? (ComponentActivity) context : null;
                        if (componentActivity != null) {
                            componentActivity.finish();
                        }
                        return Unit.INSTANCE;
                    }
                }
            }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(978920672, true, new AnonymousClass2(context), composerStartRestartGroup, 54), null, null, 0, 0L, 0L, null, ComposableSingletons$ScheduleSendScreenActivityKt.INSTANCE.m7515getLambda5$app_release(), composerStartRestartGroup, 805306800, TypedValues.PositionType.TYPE_SIZE_PERCENT);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScheduleSendScreenActivityKt.ScheduleSendScreenContent$lambda$0(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$ScheduleSendScreenContent$2, reason: invalid class name */
    /* JADX INFO: compiled from: ScheduleSendScreenActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ Context $context;

        AnonymousClass2(Context context) {
            this.$context = context;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C77@2765L888,75@2677L990:ScheduleSendScreenActivity.kt#vnfy9");
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(978920672, i, -1, "com.example.assistapp.features.Schedule.ScheduleSendScreenContent.<anonymous> (ScheduleSendScreenActivity.kt:75)");
                }
                composer.startReplaceGroup(1314981624);
                ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreenActivity.kt#9igjgp");
                boolean zChangedInstance = composer.changedInstance(this.$context);
                final Context context = this.$context;
                Object objRememberedValue = composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$ScheduleSendScreenContent$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ScheduleSendScreenActivityKt.AnonymousClass2.invoke$lambda$3$lambda$2(context, ((Integer) obj).intValue());
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                ScheduleSendScreenActivityKt.BottomNavigationBar(1, (Function1) objRememberedValue, composer, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$3$lambda$2(Context context, int i) {
            if (i == 0) {
                Intent intent = new Intent(context, (Class<?>) MainActivity.class);
                intent.setFlags(603979776);
                context.startActivity(intent);
            } else if (i == 2) {
                Intent intent2 = new Intent(context, (Class<?>) WebViewScreenActivity.class);
                intent2.setFlags(603979776);
                context.startActivity(intent2);
            }
            return Unit.INSTANCE;
        }
    }

    public static final void BottomNavigationBar(final int i, final Function1<? super Integer, Unit> onTabSelected, Composer composer, final int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(onTabSelected, "onTabSelected");
        Composer composerStartRestartGroup = composer.startRestartGroup(-642711977);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BottomNavigationBar)P(1)122@4231L892,115@4025L1098:ScheduleSendScreenActivity.kt#vnfy9");
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
                ComposerKt.traceEventStart(-642711977, i3, -1, "com.example.assistapp.features.Schedule.BottomNavigationBar (ScheduleSendScreenActivity.kt:114)");
            }
            SurfaceKt.m2846SurfaceT9BRK9s(WindowInsetsPadding_androidKt.navigationBarsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null)), null, ColorKt.Color(4294572537L), 0L, Dp.m7027constructorimpl(3), Dp.m7027constructorimpl(8), null, ComposableLambdaKt.rememberComposableLambda(556112754, true, new AnonymousClass1(i, onTabSelected), composerStartRestartGroup, 54), composerStartRestartGroup, 12804480, 74);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScheduleSendScreenActivityKt.BottomNavigationBar$lambda$1(i, onTabSelected, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$BottomNavigationBar$1, reason: invalid class name */
    /* JADX INFO: compiled from: ScheduleSendScreenActivity.kt */
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
            ComposerKt.sourceInformation(composer, "C123@4241L876:ScheduleSendScreenActivity.kt#vnfy9");
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(556112754, i, -1, "com.example.assistapp.features.Schedule.BottomNavigationBar.<anonymous> (ScheduleSendScreenActivity.kt:123)");
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
                ComposerKt.sourceInformationMarkerStart(composer, 178066801, "C134@4653L20,130@4486L201,140@4866L20,136@4700L200,146@5073L20,142@4913L194:ScheduleSendScreenActivity.kt#vnfy9");
                ImageVector locationOn = LocationOnKt.getLocationOn(Icons.INSTANCE.getDefault());
                boolean z = i2 == 0;
                composer.startReplaceGroup(-271345831);
                ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreenActivity.kt#9igjgp");
                boolean zChanged = composer.changed(function1);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$BottomNavigationBar$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ScheduleSendScreenActivityKt.AnonymousClass1.invoke$lambda$6$lambda$1$lambda$0(function1);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                ScheduleSendScreenActivityKt.BottomNavItem(rowScopeInstance, locationOn, "위치", z, (Function0) objRememberedValue, composer, 390);
                ImageVector dateRange = DateRangeKt.getDateRange(Icons.INSTANCE.getDefault());
                boolean z2 = i2 == 1;
                composer.startReplaceGroup(-271339015);
                ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreenActivity.kt#9igjgp");
                boolean zChanged2 = composer.changed(function1);
                Object objRememberedValue2 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$BottomNavigationBar$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ScheduleSendScreenActivityKt.AnonymousClass1.invoke$lambda$6$lambda$3$lambda$2(function1);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue2);
                }
                composer.endReplaceGroup();
                ScheduleSendScreenActivityKt.BottomNavItem(rowScopeInstance, dateRange, "일정", z2, (Function0) objRememberedValue2, composer, 390);
                ImageVector home = HomeKt.getHome(Icons.INSTANCE.getDefault());
                boolean z3 = i2 == 2;
                composer.startReplaceGroup(-271332391);
                ComposerKt.sourceInformation(composer, "CC(remember):ScheduleSendScreenActivity.kt#9igjgp");
                boolean zChanged3 = composer.changed(function1);
                Object objRememberedValue3 = composer.rememberedValue();
                if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$BottomNavigationBar$1$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ScheduleSendScreenActivityKt.AnonymousClass1.invoke$lambda$6$lambda$5$lambda$4(function1);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue3);
                }
                composer.endReplaceGroup();
                ScheduleSendScreenActivityKt.BottomNavItem(rowScopeInstance, home, "웹", z3, (Function0) objRememberedValue3, composer, 390);
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
        Composer composerStartRestartGroup = composer.startRestartGroup(257443751);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BottomNavItem)P(!2,3)162@5364L252,178@5841L39,171@5622L894:ScheduleSendScreenActivity.kt#vnfy9");
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
                ComposerKt.traceEventStart(257443751, i3, -1, "com.example.assistapp.features.Schedule.BottomNavItem (ScheduleSendScreenActivity.kt:158)");
            }
            long jColor = ColorKt.Color(4278190080L);
            long jColor2 = ColorKt.Color(4287137928L);
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.1f : 1.0f, AnimationSpecKt.spring$default(0.5f, 200.0f, null, 4, null), 0.0f, "icon_scale", null, composerStartRestartGroup, 3120, 20);
            Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 1, null);
            composerStartRestartGroup.startReplaceGroup(1429615909);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ScheduleSendScreenActivity.kt#9igjgp");
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 612244061, "C183@6021L210,189@6240L40,190@6289L221:ScheduleSendScreenActivity.kt#vnfy9");
            IconKt.m2453Iconww6aTOc(icon, label, SizeKt.m1010size3ABfNKs(Modifier.INSTANCE, Dp.m7027constructorimpl(26 * BottomNavItem$lambda$2(stateAnimateFloatAsState))), z ? jColor : jColor2, composerStartRestartGroup, (i3 >> 3) & WebSocketProtocol.PAYLOAD_SHORT, 0);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.assistapp.features.Schedule.ScheduleSendScreenActivityKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScheduleSendScreenActivityKt.BottomNavItem$lambda$5(rowScope, icon, label, z, onClick, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float BottomNavItem$lambda$2(State<Float> state) {
        return state.getValue().floatValue();
    }
}
