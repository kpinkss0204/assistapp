package dev.chrisbanes.snapper;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyList.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aa\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000328\b\u0002\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u00052\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0081\u0001\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000328\b\u0002\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0097\u0001\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000328\b\u0002\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00172\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00150\u001bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a¿\u0001\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000328\b\u0002\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00172<\u0010\u001e\u001a8\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\f0\u001fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\"\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"rememberLazyListSnapperLayoutInfo", "Ldev/chrisbanes/snapper/LazyListSnapperLayoutInfo;", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "snapOffsetForItem", "Lkotlin/Function2;", "Ldev/chrisbanes/snapper/SnapperLayoutInfo;", "Lkotlin/ParameterName;", "name", "layoutInfo", "Ldev/chrisbanes/snapper/SnapperLayoutItemInfo;", "item", "", "endContentPadding", "Landroidx/compose/ui/unit/Dp;", "rememberLazyListSnapperLayoutInfo-6a0pyJM", "(Landroidx/compose/foundation/lazy/LazyListState;Lkotlin/jvm/functions/Function2;FLandroidx/compose/runtime/Composer;II)Ldev/chrisbanes/snapper/LazyListSnapperLayoutInfo;", "rememberSnapperFlingBehavior", "Ldev/chrisbanes/snapper/SnapperFlingBehavior;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "springAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "rememberSnapperFlingBehavior-TN_CM5M", "(Landroidx/compose/foundation/lazy/LazyListState;Lkotlin/jvm/functions/Function2;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Ldev/chrisbanes/snapper/SnapperFlingBehavior;", "maximumFlingDistance", "Lkotlin/Function1;", "rememberSnapperFlingBehavior-osbwsH8", "(Landroidx/compose/foundation/lazy/LazyListState;Lkotlin/jvm/functions/Function2;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Ldev/chrisbanes/snapper/SnapperFlingBehavior;", "snapIndex", "Lkotlin/Function3;", "startIndex", "targetIndex", "(Landroidx/compose/foundation/lazy/LazyListState;Lkotlin/jvm/functions/Function2;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Ldev/chrisbanes/snapper/SnapperFlingBehavior;", "lib_release"}, k = 2, mv = {1, 6, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LazyListKt {
    /* JADX INFO: renamed from: rememberSnapperFlingBehavior-osbwsH8, reason: not valid java name */
    public static final SnapperFlingBehavior m7862rememberSnapperFlingBehaviorosbwsH8(LazyListState lazyListState, Function2<? super SnapperLayoutInfo, ? super SnapperLayoutItemInfo, Integer> function2, float f, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Function3<? super SnapperLayoutInfo, ? super Integer, ? super Integer, Integer> snapIndex, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        Intrinsics.checkNotNullParameter(snapIndex, "snapIndex");
        composer.startReplaceableGroup(-632875458);
        ComposerKt.sourceInformation(composer, "C(rememberSnapperFlingBehavior)P(2,4,1:c#ui.unit.Dp!1,5)");
        if ((i2 & 2) != 0) {
            function2 = SnapOffsets.INSTANCE.getCenter();
        }
        Function2<? super SnapperLayoutInfo, ? super SnapperLayoutItemInfo, Integer> function22 = function2;
        if ((i2 & 4) != 0) {
            f = Dp.m7027constructorimpl(0);
        }
        float f2 = f;
        if ((i2 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        }
        if ((i2 & 16) != 0) {
            animationSpec = SnapperFlingBehaviorDefaults.INSTANCE.getSpringAnimationSpec();
        }
        SnapperFlingBehavior snapperFlingBehaviorRememberSnapperFlingBehavior = SnapperFlingBehaviorKt.rememberSnapperFlingBehavior(m7859rememberLazyListSnapperLayoutInfo6a0pyJM(lazyListState, function22, f2, composer, i & 1022, 0), decayAnimationSpec, animationSpec, snapIndex, composer, ((i >> 6) & 7168) | 576, 0);
        composer.endReplaceableGroup();
        return snapperFlingBehaviorRememberSnapperFlingBehavior;
    }

    /* JADX INFO: renamed from: rememberSnapperFlingBehavior-TN_CM5M, reason: not valid java name */
    public static final SnapperFlingBehavior m7860rememberSnapperFlingBehaviorTN_CM5M(LazyListState lazyListState, Function2<? super SnapperLayoutInfo, ? super SnapperLayoutItemInfo, Integer> function2, float f, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        composer.startReplaceableGroup(-632873779);
        ComposerKt.sourceInformation(composer, "C(rememberSnapperFlingBehavior)P(2,3,1:c#ui.unit.Dp)");
        if ((i2 & 2) != 0) {
            function2 = SnapOffsets.INSTANCE.getCenter();
        }
        Function2<? super SnapperLayoutInfo, ? super SnapperLayoutItemInfo, Integer> function22 = function2;
        if ((i2 & 4) != 0) {
            f = Dp.m7027constructorimpl(0);
        }
        if ((i2 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        }
        DecayAnimationSpec<Float> decayAnimationSpec2 = decayAnimationSpec;
        if ((i2 & 16) != 0) {
            animationSpec = SnapperFlingBehaviorDefaults.INSTANCE.getSpringAnimationSpec();
        }
        SnapperFlingBehavior snapperFlingBehaviorM7862rememberSnapperFlingBehaviorosbwsH8 = m7862rememberSnapperFlingBehaviorosbwsH8(lazyListState, function22, f, decayAnimationSpec2, animationSpec, SnapperFlingBehaviorDefaults.INSTANCE.getSnapIndex(), composer, (i & 14) | 36864 | (i & 112) | (i & 896), 0);
        composer.endReplaceableGroup();
        return snapperFlingBehaviorM7862rememberSnapperFlingBehaviorosbwsH8;
    }

    @Deprecated(message = "The maximumFlingDistance parameter has been replaced with snapIndex")
    /* JADX INFO: renamed from: rememberSnapperFlingBehavior-osbwsH8, reason: not valid java name */
    public static final SnapperFlingBehavior m7861rememberSnapperFlingBehaviorosbwsH8(LazyListState lazyListState, Function2<? super SnapperLayoutInfo, ? super SnapperLayoutItemInfo, Integer> function2, float f, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Function1<? super SnapperLayoutInfo, Float> function1, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        composer.startReplaceableGroup(-632871639);
        ComposerKt.sourceInformation(composer, "C(rememberSnapperFlingBehavior)P(2,4,1:c#ui.unit.Dp!1,5)");
        if ((i2 & 2) != 0) {
            function2 = SnapOffsets.INSTANCE.getCenter();
        }
        Function2<? super SnapperLayoutInfo, ? super SnapperLayoutItemInfo, Integer> function22 = function2;
        if ((i2 & 4) != 0) {
            f = Dp.m7027constructorimpl(0);
        }
        float f2 = f;
        if ((i2 & 8) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        }
        if ((i2 & 16) != 0) {
            animationSpec = SnapperFlingBehaviorDefaults.INSTANCE.getSpringAnimationSpec();
        }
        if ((i2 & 32) != 0) {
            function1 = SnapperFlingBehaviorDefaults.INSTANCE.getMaximumFlingDistance();
        }
        SnapperFlingBehavior snapperFlingBehaviorRememberSnapperFlingBehavior = SnapperFlingBehaviorKt.rememberSnapperFlingBehavior(m7859rememberLazyListSnapperLayoutInfo6a0pyJM(lazyListState, function22, f2, composer, i & 1022, 0), decayAnimationSpec, animationSpec, function1, composer, ((i >> 6) & 7168) | 576, 0);
        composer.endReplaceableGroup();
        return snapperFlingBehaviorRememberSnapperFlingBehavior;
    }

    /* JADX INFO: renamed from: rememberLazyListSnapperLayoutInfo-6a0pyJM, reason: not valid java name */
    public static final LazyListSnapperLayoutInfo m7859rememberLazyListSnapperLayoutInfo6a0pyJM(LazyListState lazyListState, Function2<? super SnapperLayoutInfo, ? super SnapperLayoutItemInfo, Integer> function2, float f, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        composer.startReplaceableGroup(-1050829263);
        ComposerKt.sourceInformation(composer, "C(rememberLazyListSnapperLayoutInfo)P(1,2,0:c#ui.unit.Dp)");
        if ((i2 & 2) != 0) {
            function2 = SnapOffsets.INSTANCE.getCenter();
        }
        Function2<? super SnapperLayoutInfo, ? super SnapperLayoutItemInfo, Integer> function22 = function2;
        if ((i2 & 4) != 0) {
            f = Dp.m7027constructorimpl(0);
        }
        composer.startReplaceableGroup(-3686552);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(lazyListState) | composer.changed(function22);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            LazyListSnapperLayoutInfo lazyListSnapperLayoutInfo = new LazyListSnapperLayoutInfo(lazyListState, function22, 0, 4, null);
            composer.updateRememberedValue(lazyListSnapperLayoutInfo);
            objRememberedValue = lazyListSnapperLayoutInfo;
        }
        composer.endReplaceableGroup();
        LazyListSnapperLayoutInfo lazyListSnapperLayoutInfo2 = (LazyListSnapperLayoutInfo) objRememberedValue;
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        lazyListSnapperLayoutInfo2.setEndContentPadding$lib_release(((Density) objConsume).mo651roundToPx0680j_4(f));
        composer.endReplaceableGroup();
        return lazyListSnapperLayoutInfo2;
    }
}
