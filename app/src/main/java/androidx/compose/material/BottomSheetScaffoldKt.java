package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ar\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0080\u0002\u0010\u001b\u001a\u00020\u00062\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0015\b\u0002\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010 ¢\u0006\u0002\b\u00172\u0019\b\u0002\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010 ¢\u0006\u0002\b\u00172\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010&\u001a\u00020\u000f2\b\b\u0002\u0010'\u001a\u00020\u000f2\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\u0002\b\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u009b\u0001\u0010+\u001a\u00020\u00062\u0013\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010 ¢\u0006\u0002\b\u00172\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00060 ¢\u0006\u0002\b\u00172\u0011\u0010-\u001a\r\u0012\u0004\u0012\u00020\u00060 ¢\u0006\u0002\b\u00172\u0013\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010 ¢\u0006\u0002\b\u00172\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00060 ¢\u0006\u0002\b\u00172\u0006\u0010\u0011\u001a\u00020\u00012\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0 2\u0006\u0010$\u001a\u00020%2\u0006\u00100\u001a\u00020\bH\u0003ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001a\u001c\u00103\u001a\u0002042\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u0003052\u0006\u00106\u001a\u000207H\u0002\u001a!\u00108\u001a\u00020\u001e2\b\b\u0002\u00109\u001a\u00020\b2\b\b\u0002\u0010:\u001a\u00020\"H\u0007¢\u0006\u0002\u0010;\u001a;\u0010<\u001a\u00020\b2\u0006\u0010=\u001a\u00020>2\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020/0@2\u0014\b\u0002\u0010A\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020\n0\u0015H\u0007¢\u0006\u0002\u0010B\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"BottomSheetScaffoldPositionalThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "BottomSheetScaffoldVelocityThreshold", "FabSpacing", "BottomSheet", "", "state", "Landroidx/compose/material/BottomSheetState;", "sheetGesturesEnabled", "", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetPeekHeight", "modifier", "Landroidx/compose/ui/Modifier;", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "BottomSheet-dAqlCkY", "(Landroidx/compose/material/BottomSheetState;ZLandroidx/compose/ui/graphics/Shape;FJJFLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomSheetScaffold", "sheetContent", "scaffoldState", "Landroidx/compose/material/BottomSheetScaffoldState;", "topBar", "Lkotlin/Function0;", "snackbarHost", "Landroidx/compose/material/SnackbarHostState;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "backgroundColor", "contentColor", "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-HnlDQGw", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BottomSheetScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLandroidx/compose/ui/graphics/Shape;FJJFJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "BottomSheetScaffoldLayout", "body", "bottomSheet", "sheetOffset", "", "sheetState", "BottomSheetScaffoldLayout-HJHHjMs", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;FLkotlin/jvm/functions/Function0;ILandroidx/compose/material/BottomSheetState;Landroidx/compose/runtime/Composer;I)V", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/material/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "rememberBottomSheetScaffoldState", "bottomSheetState", "snackbarHostState", "(Landroidx/compose/material/BottomSheetState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetScaffoldState;", "rememberBottomSheetState", "initialValue", "Landroidx/compose/material/BottomSheetValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmStateChange", "(Landroidx/compose/material/BottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BottomSheetScaffoldKt {
    private static final float FabSpacing = Dp.m7027constructorimpl(16);
    private static final float BottomSheetScaffoldPositionalThreshold = Dp.m7027constructorimpl(56);
    private static final float BottomSheetScaffoldVelocityThreshold = Dp.m7027constructorimpl(125);

    public static final BottomSheetState rememberBottomSheetState(final BottomSheetValue bottomSheetValue, final AnimationSpec<Float> animationSpec, final Function1<? super BottomSheetValue, Boolean> function1, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1808153344, "C(rememberBottomSheetState)P(2)236@8776L7,244@9018L210,237@8795L433:BottomSheetScaffold.kt#jmzs0o");
        if ((i2 & 2) != 0) {
            animationSpec = BottomSheetScaffoldDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i2 & 4) != 0) {
            function1 = new Function1<BottomSheetValue, Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt.rememberBottomSheetState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(BottomSheetValue bottomSheetValue2) {
                    return true;
                }
            };
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1808153344, i, -1, "androidx.compose.material.rememberBottomSheetState (BottomSheetScaffold.kt:235)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        final Density density = (Density) objConsume;
        Object[] objArr = {animationSpec};
        Saver<BottomSheetState, ?> Saver = BottomSheetState.INSTANCE.Saver(animationSpec, function1, density);
        ComposerKt.sourceInformationMarkerStart(composer, -1916212862, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean zChanged = ((((i & 14) ^ 6) > 4 && composer.changed(bottomSheetValue)) || (i & 6) == 4) | composer.changed(density) | composer.changedInstance(animationSpec) | ((((i & 896) ^ 384) > 256 && composer.changed(function1)) || (i & 384) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function0) new Function0<BottomSheetState>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$rememberBottomSheetState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final BottomSheetState invoke() {
                    return new BottomSheetState(bottomSheetValue, density, animationSpec, function1);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        BottomSheetState bottomSheetState = (BottomSheetState) RememberSaveableKt.m4139rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return bottomSheetState;
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(BottomSheetState bottomSheetState, SnackbarHostState snackbarHostState, Composer composer, int i, int i2) {
        Composer composer2;
        ComposerKt.sourceInformationMarkerStart(composer, -1022285988, "C(rememberBottomSheetScaffoldState)274@9912L35,275@9992L32,277@10066L196:BottomSheetScaffold.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            composer2 = composer;
            bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed, null, null, composer2, 6, 6);
        } else {
            composer2 = composer;
        }
        if ((i2 & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart(composer2, 200088250, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SnackbarHostState();
                composer2.updateRememberedValue(objRememberedValue);
            }
            snackbarHostState = (SnackbarHostState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1022285988, i, -1, "androidx.compose.material.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:276)");
        }
        ComposerKt.sourceInformationMarkerStart(composer2, 200090782, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean z = ((((i & 14) ^ 6) > 4 && composer2.changed(bottomSheetState)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer2.changed(snackbarHostState)) || (i & 48) == 32);
        Object objRememberedValue2 = composer2.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new BottomSheetScaffoldState(bottomSheetState, snackbarHostState);
            composer2.updateRememberedValue(objRememberedValue2);
        }
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer2);
        return bottomSheetScaffoldState;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:267:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0122  */
    /* JADX INFO: renamed from: BottomSheetScaffold-HnlDQGw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1738BottomSheetScaffoldHnlDQGw(final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, androidx.compose.ui.Modifier r37, androidx.compose.material.BottomSheetScaffoldState r38, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, kotlin.jvm.functions.Function3<? super androidx.compose.material.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r40, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, int r42, boolean r43, androidx.compose.ui.graphics.Shape r44, float r45, long r46, long r48, float r50, long r51, long r53, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, androidx.compose.runtime.Composer r56, final int r57, final int r58, final int r59) {
        /*
            Method dump skipped, instruction units count: 1027
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomSheetScaffoldKt.m1738BottomSheetScaffoldHnlDQGw(kotlin.jvm.functions.Function3, androidx.compose.ui.Modifier, androidx.compose.material.BottomSheetScaffoldState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, int, boolean, androidx.compose.ui.graphics.Shape, float, long, long, float, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0116  */
    /* JADX INFO: renamed from: BottomSheet-dAqlCkY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1737BottomSheetdAqlCkY(final androidx.compose.material.BottomSheetState r26, final boolean r27, final androidx.compose.ui.graphics.Shape r28, final float r29, final long r30, final long r32, final float r34, androidx.compose.ui.Modifier r35, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, androidx.compose.runtime.Composer r37, final int r38, final int r39) {
        /*
            Method dump skipped, instruction units count: 636
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomSheetScaffoldKt.m1737BottomSheetdAqlCkY(androidx.compose.material.BottomSheetState, boolean, androidx.compose.ui.graphics.Shape, float, long, long, float, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: BottomSheetScaffoldLayout-HJHHjMs, reason: not valid java name */
    public static final void m1739BottomSheetScaffoldLayoutHJHHjMs(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Function2<? super Composer, ? super Integer, Unit> function25, final float f, final Function0<Float> function0, final int i, final BottomSheetState bottomSheetState, Composer composer, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1411837005);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BottomSheetScaffoldLayout)P(8!3,7,5:c#ui.unit.Dp,4,3:c#material.FabPosition)509@20078L2668,501@19867L2879:BottomSheetScaffold.kt#jmzs0o");
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function22) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function23) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function24) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function25) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 131072 : 65536;
        }
        if ((1572864 & i2) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 1048576 : 524288;
        }
        if ((12582912 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 8388608 : 4194304;
        }
        if ((100663296 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(bottomSheetState) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & i3) != 38347922 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1411837005, i3, -1, "androidx.compose.material.BottomSheetScaffoldLayout (BottomSheetScaffold.kt:500)");
            }
            Function2[] function2Arr = new Function2[5];
            function2Arr[0] = function2 == null ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1805getLambda2$material_release() : function2;
            function2Arr[1] = function22;
            function2Arr[2] = function23;
            function2Arr[3] = function24 == null ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1806getLambda3$material_release() : function24;
            function2Arr[4] = function25;
            List listListOf = CollectionsKt.listOf((Object[]) function2Arr);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 539800922, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean z = ((i3 & 234881024) == 67108864) | ((3670016 & i3) == 1048576) | ((29360128 & i3) == 8388608) | ((458752 & i3) == 131072);
            MultiContentMeasurePolicy multiContentMeasurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || multiContentMeasurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
                multiContentMeasurePolicyRememberedValue = new MultiContentMeasurePolicy() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1
                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo913measure3p2s80s(final MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j) {
                        Object obj;
                        Object obj2;
                        Object obj3;
                        Object obj4;
                        Object obj5;
                        List<? extends Measurable> list2 = list.get(0);
                        List<? extends Measurable> list3 = list.get(1);
                        List<? extends Measurable> list4 = list.get(2);
                        List<? extends Measurable> list5 = list.get(3);
                        List<? extends Measurable> list6 = list.get(4);
                        final int iM6980getMaxWidthimpl = Constraints.m6980getMaxWidthimpl(j);
                        final int iM6979getMaxHeightimpl = Constraints.m6979getMaxHeightimpl(j);
                        long jM6971copyZbe2FdA$default = Constraints.m6971copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        ArrayList arrayList = new ArrayList(list4.size());
                        int size = list4.size();
                        for (int i4 = 0; i4 < size; i4++) {
                            arrayList.add(list4.get(i4).mo5903measureBRTryo0(jM6971copyZbe2FdA$default));
                        }
                        final ArrayList arrayList2 = arrayList;
                        ArrayList arrayList3 = new ArrayList(list2.size());
                        int size2 = list2.size();
                        for (int i5 = 0; i5 < size2; i5++) {
                            arrayList3.add(list2.get(i5).mo5903measureBRTryo0(jM6971copyZbe2FdA$default));
                        }
                        final ArrayList arrayList4 = arrayList3;
                        if (arrayList4.isEmpty()) {
                            obj = null;
                        } else {
                            obj = arrayList4.get(0);
                            int height = ((Placeable) obj).getHeight();
                            int lastIndex = CollectionsKt.getLastIndex(arrayList4);
                            if (1 <= lastIndex) {
                                int i6 = 1;
                                while (true) {
                                    Object obj6 = arrayList4.get(i6);
                                    int height2 = ((Placeable) obj6).getHeight();
                                    if (height < height2) {
                                        height = height2;
                                        obj = obj6;
                                    }
                                    if (i6 == lastIndex) {
                                        break;
                                    }
                                    i6++;
                                }
                            }
                        }
                        Placeable placeable = (Placeable) obj;
                        final int height3 = placeable != null ? placeable.getHeight() : 0;
                        long jM6971copyZbe2FdA$default2 = Constraints.m6971copyZbe2FdA$default(jM6971copyZbe2FdA$default, 0, 0, 0, iM6979getMaxHeightimpl - height3, 7, null);
                        ArrayList arrayList5 = new ArrayList(list3.size());
                        int size3 = list3.size();
                        for (int i7 = 0; i7 < size3; i7++) {
                            arrayList5.add(list3.get(i7).mo5903measureBRTryo0(jM6971copyZbe2FdA$default2));
                        }
                        final ArrayList arrayList6 = arrayList5;
                        ArrayList arrayList7 = new ArrayList(list5.size());
                        int size4 = list5.size();
                        for (int i8 = 0; i8 < size4; i8++) {
                            arrayList7.add(list5.get(i8).mo5903measureBRTryo0(jM6971copyZbe2FdA$default));
                        }
                        final ArrayList arrayList8 = arrayList7;
                        if (arrayList8.isEmpty()) {
                            obj2 = null;
                        } else {
                            obj2 = arrayList8.get(0);
                            int width = ((Placeable) obj2).getWidth();
                            int lastIndex2 = CollectionsKt.getLastIndex(arrayList8);
                            if (1 <= lastIndex2) {
                                int i9 = 1;
                                while (true) {
                                    Object obj7 = arrayList8.get(i9);
                                    int width2 = ((Placeable) obj7).getWidth();
                                    if (width < width2) {
                                        obj2 = obj7;
                                        width = width2;
                                    }
                                    if (i9 == lastIndex2) {
                                        break;
                                    }
                                    i9++;
                                }
                            }
                        }
                        Placeable placeable2 = (Placeable) obj2;
                        final int width3 = placeable2 != null ? placeable2.getWidth() : 0;
                        if (arrayList8.isEmpty()) {
                            obj3 = null;
                        } else {
                            obj3 = arrayList8.get(0);
                            int height4 = ((Placeable) obj3).getHeight();
                            int lastIndex3 = CollectionsKt.getLastIndex(arrayList8);
                            if (1 <= lastIndex3) {
                                int i10 = 1;
                                while (true) {
                                    Object obj8 = arrayList8.get(i10);
                                    int height5 = ((Placeable) obj8).getHeight();
                                    if (height4 < height5) {
                                        obj3 = obj8;
                                        height4 = height5;
                                    }
                                    if (i10 == lastIndex3) {
                                        break;
                                    }
                                    i10++;
                                }
                            }
                        }
                        Placeable placeable3 = (Placeable) obj3;
                        final int height6 = placeable3 != null ? placeable3.getHeight() : 0;
                        ArrayList arrayList9 = new ArrayList(list6.size());
                        int size5 = list6.size();
                        for (int i11 = 0; i11 < size5; i11++) {
                            arrayList9.add(list6.get(i11).mo5903measureBRTryo0(jM6971copyZbe2FdA$default));
                        }
                        final ArrayList arrayList10 = arrayList9;
                        if (arrayList10.isEmpty()) {
                            obj4 = null;
                        } else {
                            obj4 = arrayList10.get(0);
                            int width4 = ((Placeable) obj4).getWidth();
                            int lastIndex4 = CollectionsKt.getLastIndex(arrayList10);
                            if (1 <= lastIndex4) {
                                int i12 = 1;
                                while (true) {
                                    Object obj9 = arrayList10.get(i12);
                                    int width5 = ((Placeable) obj9).getWidth();
                                    if (width4 < width5) {
                                        obj4 = obj9;
                                        width4 = width5;
                                    }
                                    if (i12 == lastIndex4) {
                                        break;
                                    }
                                    i12++;
                                }
                            }
                        }
                        Placeable placeable4 = (Placeable) obj4;
                        int width6 = placeable4 != null ? placeable4.getWidth() : 0;
                        if (arrayList10.isEmpty()) {
                            obj5 = null;
                        } else {
                            Object obj10 = arrayList10.get(0);
                            int height7 = ((Placeable) obj10).getHeight();
                            int lastIndex5 = CollectionsKt.getLastIndex(arrayList10);
                            int i13 = 1;
                            if (1 <= lastIndex5) {
                                while (true) {
                                    Object obj11 = arrayList10.get(i13);
                                    int height8 = ((Placeable) obj11).getHeight();
                                    if (height7 < height8) {
                                        obj10 = obj11;
                                        height7 = height8;
                                    }
                                    if (i13 == lastIndex5) {
                                        break;
                                    }
                                    i13++;
                                }
                            }
                            obj5 = obj10;
                        }
                        Placeable placeable5 = (Placeable) obj5;
                        final int height9 = placeable5 != null ? placeable5.getHeight() : 0;
                        final Function0<Float> function02 = function0;
                        final int i14 = i;
                        final float f2 = f;
                        final BottomSheetState bottomSheetState2 = bottomSheetState;
                        final int i15 = width6;
                        return MeasureScope.layout$default(measureScope, iM6980getMaxWidthimpl, iM6979getMaxHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1.1

                            /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1$1$WhenMappings */
                            /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                            public /* synthetic */ class WhenMappings {
                                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                                static {
                                    int[] iArr = new int[BottomSheetValue.values().length];
                                    try {
                                        iArr[BottomSheetValue.Collapsed.ordinal()] = 1;
                                    } catch (NoSuchFieldError unused) {
                                    }
                                    try {
                                        iArr[BottomSheetValue.Expanded.ordinal()] = 2;
                                    } catch (NoSuchFieldError unused2) {
                                    }
                                    $EnumSwitchMapping$0 = iArr;
                                }
                            }

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
                                int i16;
                                int i17;
                                int i18;
                                int iRoundToInt = MathKt.roundToInt(function02.invoke().floatValue());
                                int i19 = i14;
                                if (FabPosition.m1852equalsimpl0(i19, FabPosition.INSTANCE.m1858getStart5ygKITE())) {
                                    i16 = measureScope.mo651roundToPx0680j_4(BottomSheetScaffoldKt.FabSpacing);
                                } else {
                                    i16 = FabPosition.m1852equalsimpl0(i19, FabPosition.INSTANCE.m1856getCenter5ygKITE()) ? (iM6980getMaxWidthimpl - width3) / 2 : (iM6980getMaxWidthimpl - width3) - measureScope.mo651roundToPx0680j_4(BottomSheetScaffoldKt.FabSpacing);
                                }
                                int i20 = i16;
                                float f3 = measureScope.mo657toPx0680j_4(f2);
                                int i21 = height6;
                                if (f3 < i21 / 2) {
                                    i17 = (iRoundToInt - i21) - measureScope.mo651roundToPx0680j_4(BottomSheetScaffoldKt.FabSpacing);
                                } else {
                                    i17 = iRoundToInt - (i21 / 2);
                                }
                                int i22 = i17;
                                int i23 = (iM6980getMaxWidthimpl - i15) / 2;
                                int i24 = WhenMappings.$EnumSwitchMapping$0[bottomSheetState2.getCurrentValue().ordinal()];
                                if (i24 == 1) {
                                    i18 = i22 - height9;
                                } else {
                                    if (i24 != 2) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    i18 = iM6979getMaxHeightimpl - height9;
                                }
                                int i25 = i18;
                                List<Placeable> list7 = arrayList6;
                                int i26 = height3;
                                int size6 = list7.size();
                                for (int i27 = 0; i27 < size6; i27++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, list7.get(i27), 0, i26, 0.0f, 4, null);
                                }
                                List<Placeable> list8 = arrayList4;
                                int size7 = list8.size();
                                for (int i28 = 0; i28 < size7; i28++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, list8.get(i28), 0, 0, 0.0f, 4, null);
                                }
                                List<Placeable> list9 = arrayList2;
                                int size8 = list9.size();
                                for (int i29 = 0; i29 < size8; i29++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, list9.get(i29), 0, 0, 0.0f, 4, null);
                                }
                                List<Placeable> list10 = arrayList8;
                                int size9 = list10.size();
                                for (int i30 = 0; i30 < size9; i30++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, list10.get(i30), i20, i22, 0.0f, 4, null);
                                }
                                List<Placeable> list11 = arrayList10;
                                int size10 = list11.size();
                                for (int i31 = 0; i31 < size10; i31++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, list11.get(i31), i23, i25, 0.0f, 4, null);
                                }
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(multiContentMeasurePolicyRememberedValue);
            }
            MultiContentMeasurePolicy multiContentMeasurePolicy = (MultiContentMeasurePolicy) multiContentMeasurePolicyRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399185516, "CC(Layout)P(!1,2)173@6976L62,170@6862L182:Layout.kt#80mrfh");
            Modifier.Companion companion = Modifier.INSTANCE;
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listListOf);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -290761997, "CC(remember):Layout.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicy);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicy);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
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
            function2CombineAsVirtualLayouts.invoke(composerStartRestartGroup, 0);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2
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

                public final void invoke(Composer composer2, int i4) {
                    BottomSheetScaffoldKt.m1739BottomSheetScaffoldLayoutHJHHjMs(function2, function22, function23, function24, function25, f, function0, i, bottomSheetState, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1, reason: invalid class name */
    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\"\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u0015*\u00020\tH\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0014\u001a\u00020\u0015*\u00020\u0003H\u0003¢\u0006\u0004\b\u0018\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\t*\u00020\u0015H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001b"}, d2 = {"androidx/compose/material/BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", FirebaseAnalytics.Param.SOURCE, "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "toFloat", "", "offsetToFloat", "(J)F", "velocityToFloat", "toOffset", "(F)J", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AnonymousClass1 implements NestedScrollConnection {
        final /* synthetic */ Orientation $orientation;
        final /* synthetic */ AnchoredDraggableState<?> $state;

        AnonymousClass1(AnchoredDraggableState<?> anchoredDraggableState, Orientation orientation) {
            this.$state = anchoredDraggableState;
            this.$orientation = orientation;
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
        public long mo1041onPreScrollOzD1aCk(long available, int source) {
            float fOffsetToFloat = offsetToFloat(available);
            if (fOffsetToFloat < 0.0f && NestedScrollSource.m5657equalsimpl0(source, NestedScrollSource.INSTANCE.m5669getUserInputWNlRxjI())) {
                return toOffset(this.$state.dispatchRawDelta(fOffsetToFloat));
            }
            return Offset.INSTANCE.m4343getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
        public long mo767onPostScrollDzOQY0M(long consumed, long available, int source) {
            if (NestedScrollSource.m5657equalsimpl0(source, NestedScrollSource.INSTANCE.m5669getUserInputWNlRxjI())) {
                return toOffset(this.$state.dispatchRawDelta(offsetToFloat(available)));
            }
            return Offset.INSTANCE.m4343getZeroF1C5BW0();
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreFling-QWom1Mo */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object mo1040onPreFlingQWom1Mo(long r6, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r8) {
            /*
                r5 = this;
                boolean r0 = r8 instanceof androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1
                if (r0 == 0) goto L14
                r0 = r8
                androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 r0 = (androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r8 = r0.label
                int r8 = r8 - r2
                r0.label = r8
                goto L19
            L14:
                androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 r0 = new androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1
                r0.<init>(r5, r8)
            L19:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L34
                if (r2 != r3) goto L2c
                long r6 = r0.J$0
                kotlin.ResultKt.throwOnFailure(r8)
                goto L67
            L2c:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L34:
                kotlin.ResultKt.throwOnFailure(r8)
                float r8 = r5.velocityToFloat(r6)
                androidx.compose.material.AnchoredDraggableState<?> r2 = r5.$state
                float r2 = r2.requireOffset()
                r4 = 0
                int r4 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
                if (r4 >= 0) goto L61
                androidx.compose.material.AnchoredDraggableState<?> r4 = r5.$state
                androidx.compose.material.DraggableAnchors r4 = r4.getAnchors()
                float r4 = r4.minAnchor()
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 <= 0) goto L61
                androidx.compose.material.AnchoredDraggableState<?> r2 = r5.$state
                r0.J$0 = r6
                r0.label = r3
                java.lang.Object r8 = r2.settle(r8, r0)
                if (r8 != r1) goto L67
                return r1
            L61:
                androidx.compose.ui.unit.Velocity$Companion r6 = androidx.compose.ui.unit.Velocity.INSTANCE
                long r6 = r6.m7275getZero9UxMQ8M()
            L67:
                androidx.compose.ui.unit.Velocity r6 = androidx.compose.ui.unit.Velocity.m7255boximpl(r6)
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomSheetScaffoldKt.AnonymousClass1.mo1040onPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object mo766onPostFlingRZ2iAVY(long r3, long r5, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r7) {
            /*
                r2 = this;
                boolean r3 = r7 instanceof androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1
                if (r3 == 0) goto L14
                r3 = r7
                androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 r3 = (androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) r3
                int r4 = r3.label
                r0 = -2147483648(0xffffffff80000000, float:-0.0)
                r4 = r4 & r0
                if (r4 == 0) goto L14
                int r4 = r3.label
                int r4 = r4 - r0
                r3.label = r4
                goto L19
            L14:
                androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 r3 = new androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1
                r3.<init>(r2, r7)
            L19:
                java.lang.Object r4 = r3.result
                java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r0 = r3.label
                r1 = 1
                if (r0 == 0) goto L34
                if (r0 != r1) goto L2c
                long r5 = r3.J$0
                kotlin.ResultKt.throwOnFailure(r4)
                goto L48
            L2c:
                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
                r3.<init>(r4)
                throw r3
            L34:
                kotlin.ResultKt.throwOnFailure(r4)
                androidx.compose.material.AnchoredDraggableState<?> r4 = r2.$state
                float r0 = r2.velocityToFloat(r5)
                r3.J$0 = r5
                r3.label = r1
                java.lang.Object r3 = r4.settle(r0, r3)
                if (r3 != r7) goto L48
                return r7
            L48:
                androidx.compose.ui.unit.Velocity r3 = androidx.compose.ui.unit.Velocity.m7255boximpl(r5)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomSheetScaffoldKt.AnonymousClass1.mo766onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
        }

        private final long toOffset(float f) {
            float f2 = this.$orientation == Orientation.Horizontal ? f : 0.0f;
            if (this.$orientation != Orientation.Vertical) {
                f = 0.0f;
            }
            return OffsetKt.Offset(f2, f);
        }

        private final float velocityToFloat(long j) {
            return this.$orientation == Orientation.Horizontal ? Velocity.m7264getXimpl(j) : Velocity.m7265getYimpl(j);
        }

        private final float offsetToFloat(long j) {
            return this.$orientation == Orientation.Horizontal ? Offset.m4327getXimpl(j) : Offset.m4328getYimpl(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(AnchoredDraggableState<?> anchoredDraggableState, Orientation orientation) {
        return new AnonymousClass1(anchoredDraggableState, orientation);
    }
}
