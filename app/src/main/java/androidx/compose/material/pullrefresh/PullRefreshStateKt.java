package androidx.compose.material.pullrefresh;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PullRefreshState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a<\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"DragMultiplier", "", "rememberPullRefreshState", "Landroidx/compose/material/pullrefresh/PullRefreshState;", "refreshing", "", "onRefresh", "Lkotlin/Function0;", "", "refreshThreshold", "Landroidx/compose/ui/unit/Dp;", "refreshingOffset", "rememberPullRefreshState-UuyPYSY", "(ZLkotlin/jvm/functions/Function0;FFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/pullrefresh/PullRefreshState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PullRefreshStateKt {
    private static final float DragMultiplier = 0.5f;

    /* JADX INFO: renamed from: rememberPullRefreshState-UuyPYSY, reason: not valid java name */
    public static final PullRefreshState m2037rememberPullRefreshStateUuyPYSY(final boolean z, Function0<Unit> function0, float f, float f2, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -174977512, "C(rememberPullRefreshState)P(2!1,1:c#ui.unit.Dp,3:c#ui.unit.Dp)65@2636L24,66@2686L31,*70@2802L7,75@2935L104,79@3056L141,79@3045L152:PullRefreshState.kt#t44y28");
        if ((i2 & 4) != 0) {
            f = PullRefreshDefaults.INSTANCE.m2030getRefreshThresholdD9Ej5fM();
        }
        if ((i2 & 8) != 0) {
            f2 = PullRefreshDefaults.INSTANCE.m2031getRefreshingOffsetD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-174977512, i, -1, "androidx.compose.material.pullrefresh.rememberPullRefreshState (PullRefreshState.kt:62)");
        }
        if (Dp.m7026compareTo0680j_4(f, Dp.m7027constructorimpl(0)) <= 0) {
            throw new IllegalArgumentException("The refresh trigger must be greater than zero!".toString());
        }
        ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart(composer, -954363344, "CC(remember):Effects.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
            composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
            objRememberedValue = compositionScopedCoroutineScopeCanceller;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
        ComposerKt.sourceInformationMarkerEnd(composer);
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function0, composer, (i >> 3) & 14);
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        final Ref.FloatRef floatRef2 = new Ref.FloatRef();
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Density density = (Density) objConsume;
        floatRef.element = density.mo657toPx0680j_4(f);
        floatRef2.element = density.mo657toPx0680j_4(f2);
        ComposerKt.sourceInformationMarkerStart(composer, 975868558, "CC(remember):PullRefreshState.kt#9igjgp");
        boolean zChanged = composer.changed(coroutineScope);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new PullRefreshState(coroutineScope, stateRememberUpdatedState, floatRef2.element, floatRef.element);
            composer.updateRememberedValue(objRememberedValue2);
        }
        final PullRefreshState pullRefreshState = (PullRefreshState) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 975872467, "CC(remember):PullRefreshState.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(pullRefreshState) | ((((i & 14) ^ 6) > 4 && composer.changed(z)) || (i & 6) == 4) | composer.changed(floatRef.element) | composer.changed(floatRef2.element);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshStateKt$rememberPullRefreshState$3$1
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
                    pullRefreshState.setRefreshing$material_release(z);
                    pullRefreshState.setThreshold$material_release(floatRef.element);
                    pullRefreshState.setRefreshingOffset$material_release(floatRef2.element);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.SideEffect((Function0) objRememberedValue3, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return pullRefreshState;
    }
}
