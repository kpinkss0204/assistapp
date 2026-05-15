package com.google.accompanist.pager;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import dev.chrisbanes.snapper.LazyListKt;
import dev.chrisbanes.snapper.SnapOffsets;
import dev.chrisbanes.snapper.SnapperFlingBehavior;
import dev.chrisbanes.snapper.SnapperFlingBehaviorDefaults;
import dev.chrisbanes.snapper.SnapperLayoutInfo;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Pager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated(message = "\naccompanist/pager is deprecated.\nThe androidx.compose equivalent of Pager is androidx.compose.foundation.pager.Pager.\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n")
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J]\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00192\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 JG\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00192\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\"J\u0085\u0001\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00192\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2<\u0010#\u001a8\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\f0\u000bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%R(\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\tRR\u0010\n\u001a8\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\f0\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lcom/google/accompanist/pager/PagerDefaults;", "", "()V", "singlePageFlingDistance", "Lkotlin/Function1;", "Ldev/chrisbanes/snapper/SnapperLayoutInfo;", "", "getSinglePageFlingDistance$annotations", "getSinglePageFlingDistance", "()Lkotlin/jvm/functions/Function1;", "singlePageSnapIndex", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "startIndex", "targetIndex", "getSinglePageSnapIndex$annotations", "getSinglePageSnapIndex", "()Lkotlin/jvm/functions/Function3;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "state", "Lcom/google/accompanist/pager/PagerState;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "maximumFlingDistance", "endContentPadding", "Landroidx/compose/ui/unit/Dp;", "flingBehavior-FJfuzF0", "(Lcom/google/accompanist/pager/PagerState;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;FLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/FlingBehavior;", "flingBehavior--jt2gSs", "(Lcom/google/accompanist/pager/PagerState;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;FLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/FlingBehavior;", "snapIndex", "flingBehavior-hGBTI10", "(Lcom/google/accompanist/pager/PagerState;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;FLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/gestures/FlingBehavior;", "pager_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PagerDefaults {
    public static final int $stable = 0;
    public static final PagerDefaults INSTANCE = new PagerDefaults();
    private static final Function1<SnapperLayoutInfo, Float> singlePageFlingDistance = new Function1<SnapperLayoutInfo, Float>() { // from class: com.google.accompanist.pager.PagerDefaults$singlePageFlingDistance$1
        @Override // kotlin.jvm.functions.Function1
        public final Float invoke(SnapperLayoutInfo layoutInfo) {
            Intrinsics.checkNotNullParameter(layoutInfo, "layoutInfo");
            return Float.valueOf(layoutInfo.getEndScrollOffset() - layoutInfo.getStartScrollOffset());
        }
    };
    private static final Function3<SnapperLayoutInfo, Integer, Integer, Integer> singlePageSnapIndex = new Function3<SnapperLayoutInfo, Integer, Integer, Integer>() { // from class: com.google.accompanist.pager.PagerDefaults$singlePageSnapIndex$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Integer invoke(SnapperLayoutInfo snapperLayoutInfo, Integer num, Integer num2) {
            return invoke(snapperLayoutInfo, num.intValue(), num2.intValue());
        }

        public final Integer invoke(SnapperLayoutInfo layoutInfo, int i, int i2) {
            Intrinsics.checkNotNullParameter(layoutInfo, "layoutInfo");
            return Integer.valueOf(RangesKt.coerceIn(RangesKt.coerceIn(i2, i - 1, i + 1), 0, layoutInfo.getTotalItemsCount() - 1));
        }
    };

    @Deprecated(message = "MaximumFlingDistance has been deprecated in Snapper")
    public static /* synthetic */ void getSinglePageFlingDistance$annotations() {
    }

    public static /* synthetic */ void getSinglePageSnapIndex$annotations() {
    }

    private PagerDefaults() {
    }

    public final Function1<SnapperLayoutInfo, Float> getSinglePageFlingDistance() {
        return singlePageFlingDistance;
    }

    public final Function3<SnapperLayoutInfo, Integer, Integer, Integer> getSinglePageSnapIndex() {
        return singlePageSnapIndex;
    }

    @Deprecated(message = "\n            accompanist/pager is deprecated.\n            The androidx.compose equivalent of Pager is androidx.compose.foundation.pager.Pager.\n            For more migration information, please visit https://google.github.io/accompanist/pager/#migration\n    ")
    /* JADX INFO: renamed from: flingBehavior-FJfuzF0, reason: not valid java name */
    public final FlingBehavior m7551flingBehaviorFJfuzF0(PagerState state, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Function1<? super SnapperLayoutInfo, Float> function1, float f, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(state, "state");
        composer.startReplaceableGroup(1345971532);
        ComposerKt.sourceInformation(composer, "C(flingBehavior)P(4!1,3,2,1:c#ui.unit.Dp)");
        if ((i2 & 2) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        }
        if ((i2 & 4) != 0) {
            animationSpec = SnapperFlingBehaviorDefaults.INSTANCE.getSpringAnimationSpec();
        }
        AnimationSpec<Float> animationSpec2 = animationSpec;
        if ((i2 & 8) != 0) {
            function1 = singlePageFlingDistance;
        }
        Function1<? super SnapperLayoutInfo, Float> function12 = function1;
        float fM7027constructorimpl = (i2 & 16) != 0 ? Dp.m7027constructorimpl(0) : f;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1345971532, i, -1, "com.google.accompanist.pager.PagerDefaults.flingBehavior (Pager.kt:133)");
        }
        SnapperFlingBehavior snapperFlingBehaviorM7861rememberSnapperFlingBehaviorosbwsH8 = LazyListKt.m7861rememberSnapperFlingBehaviorosbwsH8(state.getLazyListState(), SnapOffsets.INSTANCE.getStart(), fM7027constructorimpl, decayAnimationSpec, animationSpec2, function12, composer, ((i >> 6) & 896) | 36864 | (458752 & (i << 6)), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return snapperFlingBehaviorM7861rememberSnapperFlingBehaviorosbwsH8;
    }

    @Deprecated(message = "\naccompanist/pager is deprecated.\nThe androidx.compose equivalent of Pager is androidx.compose.foundation.pager.Pager\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n")
    /* JADX INFO: renamed from: flingBehavior-hGBTI10, reason: not valid java name */
    public final FlingBehavior m7552flingBehaviorhGBTI10(PagerState state, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, float f, Function3<? super SnapperLayoutInfo, ? super Integer, ? super Integer, Integer> snapIndex, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapIndex, "snapIndex");
        composer.startReplaceableGroup(-776119664);
        ComposerKt.sourceInformation(composer, "C(flingBehavior)P(4!1,2,1:c#ui.unit.Dp)");
        if ((i2 & 2) != 0) {
            decayAnimationSpec = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        }
        if ((i2 & 4) != 0) {
            animationSpec = SnapperFlingBehaviorDefaults.INSTANCE.getSpringAnimationSpec();
        }
        AnimationSpec<Float> animationSpec2 = animationSpec;
        if ((i2 & 8) != 0) {
            f = Dp.m7027constructorimpl(0);
        }
        float f2 = f;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-776119664, i, -1, "com.google.accompanist.pager.PagerDefaults.flingBehavior (Pager.kt:175)");
        }
        SnapperFlingBehavior snapperFlingBehaviorM7862rememberSnapperFlingBehaviorosbwsH8 = LazyListKt.m7862rememberSnapperFlingBehaviorosbwsH8(state.getLazyListState(), SnapOffsets.INSTANCE.getStart(), f2, decayAnimationSpec, animationSpec2, snapIndex, composer, ((i >> 3) & 896) | 36864 | (458752 & (i << 3)), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return snapperFlingBehaviorM7862rememberSnapperFlingBehaviorosbwsH8;
    }

    @Deprecated(message = "\naccompanist/pager is deprecated.\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n", replaceWith = @ReplaceWith(expression = "androidx.compose.foundation.pager.PagerDefaults.flingBehavior(state = state)", imports = {"androidx.compose.foundation.pager.PagerDefaults"}))
    /* JADX INFO: renamed from: flingBehavior--jt2gSs, reason: not valid java name */
    public final FlingBehavior m7550flingBehaviorjt2gSs(PagerState state, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, float f, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(state, "state");
        composer.startReplaceableGroup(132228799);
        ComposerKt.sourceInformation(composer, "C(flingBehavior)P(3!1,2,1:c#ui.unit.Dp)");
        DecayAnimationSpec<Float> decayAnimationSpecRememberSplineBasedDecay = (i2 & 2) != 0 ? SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0) : decayAnimationSpec;
        AnimationSpec<Float> springAnimationSpec = (i2 & 4) != 0 ? SnapperFlingBehaviorDefaults.INSTANCE.getSpringAnimationSpec() : animationSpec;
        float fM7027constructorimpl = (i2 & 8) != 0 ? Dp.m7027constructorimpl(0) : f;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(132228799, i, -1, "com.google.accompanist.pager.PagerDefaults.flingBehavior (Pager.kt:215)");
        }
        FlingBehavior flingBehaviorM7552flingBehaviorhGBTI10 = m7552flingBehaviorhGBTI10(state, decayAnimationSpecRememberSplineBasedDecay, springAnimationSpec, fM7027constructorimpl, singlePageSnapIndex, composer, (i & 14) | 576 | (i & 7168) | ((i << 3) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return flingBehaviorM7552flingBehaviorhGBTI10;
    }
}
