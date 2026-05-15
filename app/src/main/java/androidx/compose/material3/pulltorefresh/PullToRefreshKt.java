package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PullToRefresh.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0002\u001a(\u0010\u0017\u001a\u00020\u00182\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0003ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u007f\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\u00192\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020'2\u001e\b\u0002\u0010(\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00180)¢\u0006\u0002\b+¢\u0006\u0002\b,2\u001c\u0010-\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00180)¢\u0006\u0002\b+¢\u0006\u0002\b,H\u0007¢\u0006\u0002\u0010.\u001a\b\u0010/\u001a\u00020%H\u0007\u001a\r\u00100\u001a\u00020%H\u0007¢\u0006\u0002\u00101\u001aF\u00102\u001a\u00020\u0018*\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a>\u0010=\u001a\u00020\u0018*\u0002032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u00152\u0006\u0010>\u001a\u0002072\u0006\u0010:\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010@\u001aH\u0010A\u001a\u00020#*\u00020#2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010B\u001a\u00020 2\b\b\u0002\u0010C\u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\bD\u0010E\u001aN\u0010F\u001a\u00020#*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010C\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020H2\b\b\u0002\u0010I\u001a\u00020\u001b2\b\b\u0002\u0010J\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\bK\u0010L\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u000e\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000f\u0010\u0010\"\u0016\u0010\u0011\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0012\u0010\u0010\"\u0010\u0010\u0013\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006M²\u0006\n\u0010N\u001a\u00020\u0002X\u008a\u0084\u0002"}, d2 = {"AlphaTween", "Landroidx/compose/animation/core/TweenSpec;", "", "ArcRadius", "Landroidx/compose/ui/unit/Dp;", "F", "ArrowHeight", "ArrowWidth", "CrossfadeDurationMs", "", "DragMultiplier", "MaxAlpha", "MaxProgressArc", "MinAlpha", "SpinnerContainerSize", "getSpinnerContainerSize", "()F", "SpinnerSize", "getSpinnerSize", "StrokeWidth", "ArrowValues", "Landroidx/compose/material3/pulltorefresh/ArrowValues;", "progress", "CircularArrowProgressIndicator", "", "Lkotlin/Function0;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "CircularArrowProgressIndicator-RPmYEkk", "(Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "PullToRefreshBox", "isRefreshing", "", "onRefresh", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "indicator", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", FirebaseAnalytics.Param.CONTENT, "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/pulltorefresh/PullToRefreshState;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "PullToRefreshState", "rememberPullToRefreshState", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "drawArrow", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "arrow", "Landroidx/compose/ui/graphics/Path;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "alpha", "values", "strokeWidth", "drawArrow-uDrxG_w", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/geometry/Rect;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;F)V", "drawCircularIndicator", "arcBounds", "drawCircularIndicator-KzyDr3Q", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;Landroidx/compose/ui/geometry/Rect;F)V", "pullToRefresh", "enabled", "threshold", "pullToRefresh-Z4HSEVQ", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/pulltorefresh/PullToRefreshState;ZFLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/Modifier;", "pullToRefreshIndicator", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "elevation", "pullToRefreshIndicator-wUdLESc", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/pulltorefresh/PullToRefreshState;ZFLandroidx/compose/ui/graphics/Shape;JF)Landroidx/compose/ui/Modifier;", "material3_release", "targetAlpha"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PullToRefreshKt {
    private static final int CrossfadeDurationMs = 100;
    private static final float DragMultiplier = 0.5f;
    private static final float MaxAlpha = 1.0f;
    private static final float MaxProgressArc = 0.8f;
    private static final float MinAlpha = 0.3f;
    private static final float StrokeWidth = Dp.m7027constructorimpl((float) 2.5d);
    private static final float ArcRadius = Dp.m7027constructorimpl((float) 5.5d);
    private static final float SpinnerSize = Dp.m7027constructorimpl(16);
    private static final float SpinnerContainerSize = Dp.m7027constructorimpl(40);
    private static final float ArrowWidth = Dp.m7027constructorimpl(10);
    private static final float ArrowHeight = Dp.m7027constructorimpl(5);
    private static final TweenSpec<Float> AlphaTween = AnimationSpecKt.tween$default(300, 0, EasingKt.getLinearEasing(), 2, null);

    /* JADX WARN: Removed duplicated region for block: B:101:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void PullToRefreshBox(final boolean r16, final kotlin.jvm.functions.Function0<kotlin.Unit> r17, androidx.compose.ui.Modifier r18, androidx.compose.material3.pulltorefresh.PullToRefreshState r19, androidx.compose.ui.Alignment r20, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r21, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r22, androidx.compose.runtime.Composer r23, final int r24, final int r25) {
        /*
            Method dump skipped, instruction units count: 580
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox(boolean, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.material3.pulltorefresh.PullToRefreshState, androidx.compose.ui.Alignment, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: pullToRefreshIndicator-wUdLESc$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3303pullToRefreshIndicatorwUdLESc$default(Modifier modifier, PullToRefreshState pullToRefreshState, boolean z, float f, Shape shape, long j, float f2, int i, Object obj) {
        if ((i & 4) != 0) {
            f = PullToRefreshDefaults.INSTANCE.m3289getPositionalThresholdD9Ej5fM();
        }
        float f3 = f;
        if ((i & 8) != 0) {
            shape = PullToRefreshDefaults.INSTANCE.getShape();
        }
        Shape shape2 = shape;
        if ((i & 16) != 0) {
            j = Color.INSTANCE.m4604getUnspecified0d7_KjU();
        }
        return m3302pullToRefreshIndicatorwUdLESc(modifier, pullToRefreshState, z, f3, shape2, j, (i & 32) != 0 ? PullToRefreshDefaults.INSTANCE.m3288getElevationD9Ej5fM() : f2);
    }

    /* JADX INFO: renamed from: pullToRefreshIndicator-wUdLESc, reason: not valid java name */
    public static final Modifier m3302pullToRefreshIndicatorwUdLESc(Modifier modifier, final PullToRefreshState pullToRefreshState, final boolean z, final float f, final Shape shape, long j, final float f2) {
        return BackgroundKt.m519backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(DrawModifierKt.drawWithContent(SizeKt.m1010size3ABfNKs(modifier, SpinnerContainerSize), new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$pullToRefreshIndicator$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope contentDrawScope) {
                int iM4557getIntersectrtfAjoo = ClipOp.INSTANCE.m4557getIntersectrtfAjoo();
                DrawContext drawContext = contentDrawScope.getDrawContext();
                long jMo5045getSizeNHjbRc = drawContext.mo5045getSizeNHjbRc();
                drawContext.getCanvas().save();
                try {
                    drawContext.getTransform().mo5048clipRectN_I0leg(-3.4028235E38f, 0.0f, Float.MAX_VALUE, Float.MAX_VALUE, iM4557getIntersectrtfAjoo);
                    contentDrawScope.drawContent();
                } finally {
                    drawContext.getCanvas().restore();
                    drawContext.mo5046setSizeuvyYCjk(jMo5045getSizeNHjbRc);
                }
            }
        }), new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$pullToRefreshIndicator$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                invoke2(graphicsLayerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                boolean z2 = pullToRefreshState.getDistanceFraction() > 0.0f || z;
                graphicsLayerScope.setTranslationY((pullToRefreshState.getDistanceFraction() * graphicsLayerScope.mo651roundToPx0680j_4(f)) - Size.m4393getHeightimpl(graphicsLayerScope.getSize()));
                graphicsLayerScope.setShadowElevation(z2 ? graphicsLayerScope.mo657toPx0680j_4(f2) : 0.0f);
                graphicsLayerScope.setShape(shape);
                graphicsLayerScope.setClip(true);
            }
        }), j, shape);
    }

    /* JADX INFO: renamed from: pullToRefresh-Z4HSEVQ$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3301pullToRefreshZ4HSEVQ$default(Modifier modifier, boolean z, PullToRefreshState pullToRefreshState, boolean z2, float f, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        boolean z3 = z2;
        if ((i & 8) != 0) {
            f = PullToRefreshDefaults.INSTANCE.m3289getPositionalThresholdD9Ej5fM();
        }
        return m3300pullToRefreshZ4HSEVQ(modifier, z, pullToRefreshState, z3, f, function0);
    }

    /* JADX INFO: renamed from: pullToRefresh-Z4HSEVQ, reason: not valid java name */
    public static final Modifier m3300pullToRefreshZ4HSEVQ(Modifier modifier, boolean z, PullToRefreshState pullToRefreshState, boolean z2, float f, Function0<Unit> function0) {
        return modifier.then(new PullToRefreshElement(z, function0, z2, pullToRefreshState, f, null));
    }

    public static final PullToRefreshState rememberPullToRefreshState(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 318623070, "C(rememberPullToRefreshState)513@19156L83:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(318623070, i, -1, "androidx.compose.material3.pulltorefresh.rememberPullToRefreshState (PullToRefresh.kt:512)");
        }
        PullToRefreshStateImpl pullToRefreshStateImpl = (PullToRefreshStateImpl) RememberSaveableKt.m4139rememberSaveable(new Object[0], (Saver) PullToRefreshStateImpl.INSTANCE.getSaver(), (String) null, (Function0) new Function0<PullToRefreshStateImpl>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.rememberPullToRefreshState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PullToRefreshStateImpl invoke() {
                return new PullToRefreshStateImpl();
            }
        }, composer, 3072, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return pullToRefreshStateImpl;
    }

    public static final PullToRefreshState PullToRefreshState() {
        return new PullToRefreshStateImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CircularArrowProgressIndicator-RPmYEkk, reason: not valid java name */
    public static final void m3294CircularArrowProgressIndicatorRPmYEkk(final Function0<Float> function0, final long j, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-569718810);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CircularArrowProgressIndicator)P(1,0:c#ui.graphics.Color)562@20583L61,564@20745L76,565@20843L74,567@20982L98,571@21118L443,566@20922L639:PullToRefresh.kt#djiw08");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-569718810, i2, -1, "androidx.compose.material3.pulltorefresh.CircularArrowProgressIndicator (PullToRefresh.kt:561)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1136642763, "CC(remember):PullToRefresh.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Object obj = objRememberedValue;
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Path Path = AndroidPath_androidKt.Path();
                Path.mo4461setFillTypeoQ8Xj4U(PathFillType.INSTANCE.m4850getEvenOddRgk1Os());
                composerStartRestartGroup.updateRememberedValue(Path);
                obj = Path;
            }
            final Path path = (Path) obj;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1136647962, "CC(remember):PullToRefresh.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt.derivedStateOf(new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$targetAlpha$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(function0.invoke().floatValue() < 1.0f ? 0.3f : 1.0f);
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CircularArrowProgressIndicator_RPmYEkk$lambda$4((State) objRememberedValue2), AlphaTween, 0.0f, null, null, composerStartRestartGroup, 48, 28);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1136655568, "CC(remember):PullToRefresh.kt#9igjgp");
            int i3 = i2 & 14;
            boolean z = i3 == 4;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function0.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM1010size3ABfNKs = SizeKt.m1010size3ABfNKs(SemanticsModifierKt.semantics(companion, true, (Function1) objRememberedValue3), SpinnerSize);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1136660265, "CC(remember):PullToRefresh.kt#9igjgp");
            boolean zChanged = (i3 == 4) | composerStartRestartGroup.changed(stateAnimateFloatAsState) | ((i2 & 112) == 32) | composerStartRestartGroup.changedInstance(path);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope drawScope) {
                        ArrowValues ArrowValues = PullToRefreshKt.ArrowValues(function0.invoke().floatValue());
                        float fFloatValue = stateAnimateFloatAsState.getValue().floatValue();
                        float rotation = ArrowValues.getRotation();
                        long j2 = j;
                        Path path2 = path;
                        long jMo5123getCenterF1C5BW0 = drawScope.mo5123getCenterF1C5BW0();
                        DrawContext drawContext = drawScope.getDrawContext();
                        long jMo5045getSizeNHjbRc = drawContext.mo5045getSizeNHjbRc();
                        drawContext.getCanvas().save();
                        try {
                            drawContext.getTransform().mo5051rotateUv8p0NA(rotation, jMo5123getCenterF1C5BW0);
                            Rect rectM4366Rect3MmeM6k = RectKt.m4366Rect3MmeM6k(androidx.compose.ui.geometry.SizeKt.m4406getCenteruvyYCjk(drawScope.mo5124getSizeNHjbRc()), drawScope.mo657toPx0680j_4(PullToRefreshKt.ArcRadius) + (drawScope.mo657toPx0680j_4(PullToRefreshKt.StrokeWidth) / 2.0f));
                            PullToRefreshKt.m3299drawCircularIndicatorKzyDr3Q(drawScope, j2, fFloatValue, ArrowValues, rectM4366Rect3MmeM6k, PullToRefreshKt.StrokeWidth);
                            PullToRefreshKt.m3298drawArrowuDrxG_w(drawScope, path2, rectM4366Rect3MmeM6k, j2, fFloatValue, ArrowValues, PullToRefreshKt.StrokeWidth);
                        } finally {
                            drawContext.getCanvas().restore();
                            drawContext.mo5046setSizeuvyYCjk(jMo5045getSizeNHjbRc);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifierM1010size3ABfNKs, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    PullToRefreshKt.m3294CircularArrowProgressIndicatorRPmYEkk(function0, j, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCircularIndicator-KzyDr3Q, reason: not valid java name */
    public static final void m3299drawCircularIndicatorKzyDr3Q(DrawScope drawScope, long j, float f, ArrowValues arrowValues, Rect rect, float f2) {
        DrawScope.m5103drawArcyD3GUKo$default(drawScope, j, arrowValues.getStartAngle(), arrowValues.getEndAngle() - arrowValues.getStartAngle(), false, rect.m4362getTopLeftF1C5BW0(), rect.m4360getSizeNHjbRc(), f, new Stroke(drawScope.mo657toPx0680j_4(f2), 0.0f, StrokeCap.INSTANCE.m4921getButtKaPHkGw(), 0, null, 26, null), null, 0, 768, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrowValues ArrowValues(float f) {
        float fMax = (Math.max(Math.min(1.0f, f) - 0.4f, 0.0f) * 5) / 3;
        float fCoerceIn = RangesKt.coerceIn(Math.abs(f) - 1.0f, 0.0f, 2.0f);
        float fPow = (((0.4f * fMax) - 0.25f) + (fCoerceIn - (((float) Math.pow(fCoerceIn, 2)) / 4))) * 0.5f;
        float f2 = 360;
        return new ArrowValues(fPow, fPow * f2, ((0.8f * fMax) + fPow) * f2, Math.min(1.0f, fMax));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawArrow-uDrxG_w, reason: not valid java name */
    public static final void m3298drawArrowuDrxG_w(DrawScope drawScope, Path path, Rect rect, long j, float f, ArrowValues arrowValues, float f2) {
        path.reset();
        path.moveTo(0.0f, 0.0f);
        float f3 = ArrowWidth;
        path.lineTo((drawScope.mo657toPx0680j_4(f3) * arrowValues.getScale()) / 2, drawScope.mo657toPx0680j_4(ArrowHeight) * arrowValues.getScale());
        path.lineTo(drawScope.mo657toPx0680j_4(f3) * arrowValues.getScale(), 0.0f);
        path.mo4463translatek4lQ0M(OffsetKt.Offset(((Math.min(rect.getWidth(), rect.getHeight()) / 2.0f) + Offset.m4327getXimpl(rect.m4357getCenterF1C5BW0())) - ((drawScope.mo657toPx0680j_4(f3) * arrowValues.getScale()) / 2.0f), Offset.m4328getYimpl(rect.m4357getCenterF1C5BW0()) - drawScope.mo657toPx0680j_4(f2)));
        float endAngle = arrowValues.getEndAngle() - drawScope.mo657toPx0680j_4(f2);
        long jMo5123getCenterF1C5BW0 = drawScope.mo5123getCenterF1C5BW0();
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo5045getSizeNHjbRc = drawContext.mo5045getSizeNHjbRc();
        drawContext.getCanvas().save();
        try {
            drawContext.getTransform().mo5051rotateUv8p0NA(endAngle, jMo5123getCenterF1C5BW0);
            DrawScope.m5114drawPathLG529CI$default(drawScope, path, j, f, new Stroke(drawScope.mo657toPx0680j_4(f2), 0.0f, 0, 0, null, 30, null), null, 0, 48, null);
        } finally {
            drawContext.getCanvas().restore();
            drawContext.mo5046setSizeuvyYCjk(jMo5045getSizeNHjbRc);
        }
    }

    public static final float getSpinnerSize() {
        return SpinnerSize;
    }

    public static final float getSpinnerContainerSize() {
        return SpinnerContainerSize;
    }

    private static final float CircularArrowProgressIndicator_RPmYEkk$lambda$4(State<Float> state) {
        return state.getValue().floatValue();
    }
}
