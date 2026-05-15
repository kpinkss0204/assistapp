package androidx.compose.material.internal;

import android.view.View;
import androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ExposedDropdownMenuPopup.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a:\u0010\u0005\u001a\u00020\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\b\fH\u0001¢\u0006\u0002\u0010\r\u001a+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0013\b\b\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\b\fH\u0083\b¢\u0006\u0002\u0010\u0011\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0012²\u0006\u0015\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\b\fX\u008a\u0084\u0002"}, d2 = {"LocalPopupTestTag", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalPopupTestTag", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "ExposedDropdownMenuPopup", "", "onDismissRequest", "Lkotlin/Function0;", "popupPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", FirebaseAnalytics.Param.CONTENT, "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "SimpleStack", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material_release", "currentContent"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ExposedDropdownMenuPopup_androidKt {
    private static final ProvidableCompositionLocal<String> LocalPopupTestTag = CompositionLocalKt.compositionLocalOf$default(null, new Function0<String>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$LocalPopupTestTag$1
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return "DEFAULT_TEST_TAG";
        }
    }, 1, null);

    public static final void ExposedDropdownMenuPopup(Function0<Unit> function0, PopupPositionProvider popupPositionProvider, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        final Function0<Unit> function02;
        int i3;
        int i4;
        final LayoutDirection layoutDirection;
        int i5;
        int i6;
        final String str;
        final PopupPositionProvider popupPositionProvider2 = popupPositionProvider;
        Composer composerStartRestartGroup = composer.startRestartGroup(-707851182);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ExposedDropdownMenuPopup)P(1,2)84@3429L7,85@3468L7,86@3512L7,87@3567L7,88@3603L28,89@3658L29,90@3706L38,91@3767L907,118@4710L350,118@4680L380,132@5077L181,132@5066L192,140@5304L126,140@5264L166,151@5687L462,162@6156L99,149@5608L647:ExposedDropdownMenuPopup.android.kt#mnwmf7");
        int i7 = i2 & 1;
        if (i7 != 0) {
            i3 = i | 6;
            function02 = function0;
        } else if ((i & 6) == 0) {
            function02 = function0;
            i3 = (composerStartRestartGroup.changedInstance(function02) ? 4 : 2) | i;
        } else {
            function02 = function0;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(popupPositionProvider2) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i3 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            Function0<Unit> function03 = i7 != 0 ? null : function02;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-707851182, i3, -1, "androidx.compose.material.internal.ExposedDropdownMenuPopup (ExposedDropdownMenuPopup.android.kt:83)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localView);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            View view = (View) objConsume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume2;
            ProvidableCompositionLocal<String> providableCompositionLocal = LocalPopupTestTag;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String str2 = (String) objConsume3;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume4 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection2 = (LayoutDirection) objConsume4;
            CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i3 >> 6) & 14);
            UUID uuid = (UUID) RememberSaveableKt.m4139rememberSaveable(new Object[0], (Saver) null, (String) null, (Function0) new Function0<UUID>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$popupId$1
                @Override // kotlin.jvm.functions.Function0
                public final UUID invoke() {
                    return UUID.randomUUID();
                }
            }, composerStartRestartGroup, 3072, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1502143506, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                i6 = 0;
                layoutDirection = layoutDirection2;
                i5 = i3;
                final PopupLayout popupLayout = new PopupLayout(function03, str2, view, density, popupPositionProvider2, uuid);
                function02 = function03;
                str = str2;
                popupPositionProvider2 = popupPositionProvider2;
                popupLayout.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(580081703, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$popupLayout$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i8) {
                        ComposerKt.sourceInformation(composer2, "C105@4299L122,111@4588L56,101@4111L533:ExposedDropdownMenuPopup.android.kt#mnwmf7");
                        if ((i8 & 3) != 2 || !composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(580081703, i8, -1, "androidx.compose.material.internal.ExposedDropdownMenuPopup.<anonymous>.<anonymous>.<anonymous> (ExposedDropdownMenuPopup.android.kt:101)");
                            }
                            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$popupLayout$1$1$1.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    SemanticsPropertiesKt.popup(semanticsPropertyReceiver);
                                }
                            }, 1, null);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1794961794, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
                            boolean zChangedInstance = composer2.changedInstance(popupLayout);
                            final PopupLayout popupLayout2 = popupLayout;
                            Object objRememberedValue2 = composer2.rememberedValue();
                            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$popupLayout$1$1$1$2$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                        m2026invokeozmzZPI(intSize.getPackedValue());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                    public final void m2026invokeozmzZPI(long j) {
                                        popupLayout2.m2028setPopupContentSizefhxjrPA(IntSize.m7189boximpl(j));
                                        popupLayout2.updatePosition();
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue2);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Modifier modifierAlpha = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) objRememberedValue2), popupLayout.getCanCalculatePosition() ? 1.0f : 0.0f);
                            final State<Function2<Composer, Integer, Unit>> state = stateRememberUpdatedState;
                            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1347607057, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$popupLayout$1$1$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i9) {
                                    ComposerKt.sourceInformation(composer3, "C112@4610L16:ExposedDropdownMenuPopup.android.kt#mnwmf7");
                                    if ((i9 & 3) == 2 && composer3.getSkipping()) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1347607057, i9, -1, "androidx.compose.material.internal.ExposedDropdownMenuPopup.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ExposedDropdownMenuPopup.android.kt:112)");
                                    }
                                    ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup$lambda$0(state).invoke(composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer2, 54);
                            ComposerKt.sourceInformationMarkerStart(composer2, -1085885553, "CC(SimpleStack)P(1)178@6799L979:ExposedDropdownMenuPopup.android.kt#mnwmf7");
                            ExposedDropdownMenuPopup_androidKt.AnonymousClass1 anonymousClass1 = ExposedDropdownMenuPopup_androidKt.AnonymousClass1.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierAlpha);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM4013constructorimpl = Updater.m4013constructorimpl(composer2);
                            Updater.m4020setimpl(composerM4013constructorimpl, anonymousClass1, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            composableLambdaRememberComposableLambda.invoke(composer2, 6);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }));
                composerStartRestartGroup.updateRememberedValue(popupLayout);
                objRememberedValue = popupLayout;
                i4 = 4;
            } else {
                i4 = 4;
                layoutDirection = layoutDirection2;
                i5 = i3;
                i6 = 0;
                function02 = function03;
                str = str2;
            }
            final PopupLayout popupLayout2 = (PopupLayout) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1502173125, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            int i8 = i5 & 14;
            int i9 = (composerStartRestartGroup.changedInstance(popupLayout2) ? 1 : 0) | (i8 == i4 ? 1 : i6) | (composerStartRestartGroup.changed(str) ? 1 : 0) | (composerStartRestartGroup.changed(layoutDirection) ? 1 : 0);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (i9 != 0 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout2.show();
                        popupLayout2.updateParameters(function02, str, layoutDirection);
                        final PopupLayout popupLayout3 = popupLayout2;
                        return new DisposableEffectResult() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                popupLayout3.disposeComposition();
                                popupLayout3.dismiss();
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.DisposableEffect(popupLayout2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, i6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1502184700, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            int i10 = (composerStartRestartGroup.changedInstance(popupLayout2) ? 1 : 0) | (i8 == i4 ? 1 : i6) | (composerStartRestartGroup.changed(str) ? 1 : 0) | (composerStartRestartGroup.changed(layoutDirection) ? 1 : 0);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (i10 != 0 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        popupLayout2.updateParameters(function02, str, layoutDirection);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.SideEffect((Function0) objRememberedValue3, composerStartRestartGroup, i6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1502191909, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            int i11 = (composerStartRestartGroup.changedInstance(popupLayout2) ? 1 : 0) | ((i5 & 112) == 32 ? 1 : i6);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (i11 != 0 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        popupLayout2.setPositionProvider(popupPositionProvider2);
                        popupLayout2.updatePosition();
                        return new DisposableEffectResult() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$3$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue4, composerStartRestartGroup, (i5 >> 3) & 14);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1502204501, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout2);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$5$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates layoutCoordinates) {
                        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
                        Intrinsics.checkNotNull(parentLayoutCoordinates);
                        long jMo5911getSizeYbymL2g = parentLayoutCoordinates.mo5911getSizeYbymL2g();
                        long jPositionInWindow = LayoutCoordinatesKt.positionInWindow(parentLayoutCoordinates);
                        popupLayout2.setParentBounds(IntRectKt.m7187IntRectVbeCjmY(IntOffsetKt.IntOffset(MathKt.roundToInt(Offset.m4327getXimpl(jPositionInWindow)), MathKt.roundToInt(Offset.m4328getYimpl(jPositionInWindow))), jMo5911getSizeYbymL2g));
                        popupLayout2.updatePosition();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(companion, (Function1) objRememberedValue5);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1502219146, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout2) | composerStartRestartGroup.changed(layoutDirection);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = (MeasurePolicy) new MeasurePolicy() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$6$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo322measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        popupLayout2.setParentLayoutDirection(layoutDirection);
                        return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$6$1.1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope placementScope) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, i6);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned);
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
            Updater.m4020setimpl(composerM4013constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 355624765, "C:ExposedDropdownMenuPopup.android.kt#mnwmf7");
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        final Function0<Unit> function04 = function02;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup.7
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i12) {
                    ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup(function04, popupPositionProvider2, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    public static final ProvidableCompositionLocal<String> getLocalPopupTestTag() {
        return LocalPopupTestTag;
    }

    private static final void SimpleStack(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1085885553, "CC(SimpleStack)P(1)178@6799L979:ExposedDropdownMenuPopup.android.kt#mnwmf7");
        AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
        int i2 = ((i << 3) & 112) | ((i >> 3) & 14) | 384;
        ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        int i3 = ((i2 << 6) & 896) | 6;
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
        Updater.m4020setimpl(composerM4013constructorimpl, anonymousClass1, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
        }
        Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
        function2.invoke(composer, Integer.valueOf((i3 >> 6) & 14));
        composer.endNode();
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
    }

    /* JADX INFO: renamed from: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$SimpleStack$1, reason: invalid class name */
    /* JADX INFO: compiled from: ExposedDropdownMenuPopup.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AnonymousClass1 implements MeasurePolicy {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // androidx.compose.ui.layout.MeasurePolicy
        /* JADX INFO: renamed from: measure-3p2s80s */
        public final MeasureResult mo322measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
            int i;
            int i2;
            int size = list.size();
            if (size == 0) {
                return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt.SimpleStack.1.1
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope placementScope) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }
                }, 4, null);
            }
            int i3 = 0;
            if (size == 1) {
                final Placeable placeableMo5903measureBRTryo0 = list.get(0).mo5903measureBRTryo0(j);
                return MeasureScope.layout$default(measureScope, placeableMo5903measureBRTryo0.getWidth(), placeableMo5903measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt.SimpleStack.1.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope placementScope) {
                        Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5903measureBRTryo0, 0, 0, 0.0f, 4, null);
                    }
                }, 4, null);
            }
            ArrayList arrayList = new ArrayList(list.size());
            int size2 = list.size();
            for (int i4 = 0; i4 < size2; i4++) {
                arrayList.add(list.get(i4).mo5903measureBRTryo0(j));
            }
            final ArrayList arrayList2 = arrayList;
            int lastIndex = CollectionsKt.getLastIndex(arrayList2);
            if (lastIndex >= 0) {
                int iMax = 0;
                int iMax2 = 0;
                while (true) {
                    Placeable placeable = (Placeable) arrayList2.get(i3);
                    iMax = Math.max(iMax, placeable.getWidth());
                    iMax2 = Math.max(iMax2, placeable.getHeight());
                    if (i3 == lastIndex) {
                        break;
                    }
                    i3++;
                }
                i = iMax;
                i2 = iMax2;
            } else {
                i = 0;
                i2 = 0;
            }
            return MeasureScope.layout$default(measureScope, i, i2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt.SimpleStack.1.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                    int lastIndex2 = CollectionsKt.getLastIndex(arrayList2);
                    if (lastIndex2 < 0) {
                        return;
                    }
                    int i5 = 0;
                    while (true) {
                        Placeable.PlacementScope placementScope2 = placementScope;
                        Placeable.PlacementScope.placeRelative$default(placementScope2, arrayList2.get(i5), 0, 0, 0.0f, 4, null);
                        if (i5 == lastIndex2) {
                            return;
                        }
                        i5++;
                        placementScope = placementScope2;
                    }
                }
            }, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<Composer, Integer, Unit> ExposedDropdownMenuPopup$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        return (Function2) state.getValue();
    }
}
