package androidx.compose.material3;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.material3.internal.DraggableAnchors;
import androidx.compose.material3.internal.DraggableAnchorsConfig;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
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
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u008a\u0002\u0010\u0000\u001a\u00020\u00012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u0019\b\u0002\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00112\b\b\u0002\u0010\u001d\u001a\u00020\u00112\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u008e\u0001\u0010\"\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u0010#\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00162\u0006\u0010'\u001a\u00020(2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011H\u0003ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u008d\u0001\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020(2\u0006\u0010-\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\f2\u0013\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u001c\u0010\u001e\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0006H\u0003ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a!\u00104\u001a\u00020\n2\b\b\u0002\u00105\u001a\u00020(2\b\b\u0002\u00106\u001a\u00020\u001bH\u0007¢\u0006\u0002\u00107\u001a7\u00108\u001a\u00020(2\b\b\u0002\u00109\u001a\u00020:2\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0002\u0010<\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010=\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006>"}, d2 = {"BottomSheetScaffold", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material3/BottomSheetScaffoldState;", "sheetPeekHeight", "Landroidx/compose/ui/unit/Dp;", "sheetMaxWidth", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetContainerColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetTonalElevation", "sheetShadowElevation", "sheetDragHandle", "Lkotlin/Function0;", "sheetSwipeEnabled", "", "topBar", "snackbarHost", "Landroidx/compose/material3/SnackbarHostState;", "containerColor", "contentColor", FirebaseAnalytics.Param.CONTENT, "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-sdMYb0k", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/BottomSheetScaffoldState;FFLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;JJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "BottomSheetScaffoldLayout", "body", "bottomSheet", "sheetOffset", "", "sheetState", "Landroidx/compose/material3/SheetState;", "BottomSheetScaffoldLayout-2E65NiM", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SheetState;JJLandroidx/compose/runtime/Composer;I)V", "StandardBottomSheet", "state", "peekHeight", "shape", "tonalElevation", "shadowElevation", "dragHandle", "StandardBottomSheet-w7I5h1o", "(Landroidx/compose/material3/SheetState;FFZLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "rememberBottomSheetScaffoldState", "bottomSheetState", "snackbarHostState", "(Landroidx/compose/material3/SheetState;Landroidx/compose/material3/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomSheetScaffoldState;", "rememberStandardBottomSheetState", "initialValue", "Landroidx/compose/material3/SheetValue;", "confirmValueChange", "skipHiddenState", "(Landroidx/compose/material3/SheetValue;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BottomSheetScaffoldKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:289:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010f  */
    /* JADX INFO: renamed from: BottomSheetScaffold-sdMYb0k, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2098BottomSheetScaffoldsdMYb0k(final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r34, androidx.compose.ui.Modifier r35, androidx.compose.material3.BottomSheetScaffoldState r36, float r37, float r38, androidx.compose.ui.graphics.Shape r39, long r40, long r42, float r44, float r45, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r46, boolean r47, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r48, kotlin.jvm.functions.Function3<? super androidx.compose.material3.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r49, long r50, long r52, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, androidx.compose.runtime.Composer r55, final int r56, final int r57, final int r58) {
        /*
            Method dump skipped, instruction units count: 1161
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.BottomSheetScaffoldKt.m2098BottomSheetScaffoldsdMYb0k(kotlin.jvm.functions.Function3, androidx.compose.ui.Modifier, androidx.compose.material3.BottomSheetScaffoldState, float, float, androidx.compose.ui.graphics.Shape, long, long, float, float, kotlin.jvm.functions.Function2, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(SheetState sheetState, SnackbarHostState snackbarHostState, Composer composer, int i, int i2) {
        Composer composer2;
        ComposerKt.sourceInformationMarkerStart(composer, -1474606134, "C(rememberBottomSheetScaffoldState)178@8695L34,179@8774L32,181@8848L196:BottomSheetScaffold.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            composer2 = composer;
            sheetState = rememberStandardBottomSheetState(null, null, false, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i2 & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart(composer2, -787714761, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SnackbarHostState();
                composer2.updateRememberedValue(objRememberedValue);
            }
            snackbarHostState = (SnackbarHostState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1474606134, i, -1, "androidx.compose.material3.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:180)");
        }
        ComposerKt.sourceInformationMarkerStart(composer2, -787712229, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean z = ((((i & 14) ^ 6) > 4 && composer2.changed(sheetState)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer2.changed(snackbarHostState)) || (i & 48) == 32);
        Object objRememberedValue2 = composer2.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new BottomSheetScaffoldState(sheetState, snackbarHostState);
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

    public static final SheetState rememberStandardBottomSheetState(SheetValue sheetValue, Function1<? super SheetValue, Boolean> function1, boolean z, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 678511581, "C(rememberStandardBottomSheetState)P(1)204@9680L154:BottomSheetScaffold.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            sheetValue = SheetValue.PartiallyExpanded;
        }
        SheetValue sheetValue2 = sheetValue;
        if ((i2 & 2) != 0) {
            function1 = new Function1<SheetValue, Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt.rememberStandardBottomSheetState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(SheetValue sheetValue3) {
                    return true;
                }
            };
        }
        Function1<? super SheetValue, Boolean> function12 = function1;
        if ((i2 & 4) != 0) {
            z = true;
        }
        boolean z2 = z;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(678511581, i, -1, "androidx.compose.material3.rememberStandardBottomSheetState (BottomSheetScaffold.kt:204)");
        }
        SheetState sheetStateRememberSheetState = SheetDefaultsKt.rememberSheetState(false, function12, sheetValue2, z2, composer, (i & 112) | ((i << 6) & 896) | ((i << 3) & 7168), 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return sheetStateRememberSheetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: StandardBottomSheet-w7I5h1o, reason: not valid java name */
    public static final void m2100StandardBottomSheetw7I5h1o(final SheetState sheetState, final float f, final float f2, final boolean z, final Shape shape, final long j, final long j2, final float f3, final float f4, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        CoroutineScope coroutineScope;
        int i5;
        Object obj;
        Modifier.Companion companionNestedScroll$default;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(721467526);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(StandardBottomSheet)P(9,4:c#ui.unit.Dp,7:c#ui.unit.Dp,8,6,0:c#ui.graphics.Color,2:c#ui.graphics.Color,10:c#ui.unit.Dp,5:c#ui.unit.Dp,3)225@10249L24,*227@10358L7,248@11164L1938,294@13493L2297,242@10889L4901:BottomSheetScaffold.kt#uh7d8r");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(sheetState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(f2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changed(shape) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(j) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(j2) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i3 |= composerStartRestartGroup.changed(f3) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i3 |= composerStartRestartGroup.changed(f4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 536870912 : 268435456;
        }
        if ((i2 & 6) == 0) {
            i4 = i2 | (composerStartRestartGroup.changedInstance(function3) ? 4 : 2);
        } else {
            i4 = i2;
        }
        if ((i3 & 306783379) != 306783378 || (i4 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(721467526, i3, i4, "androidx.compose.material3.StandardBottomSheet (BottomSheetScaffold.kt:224)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954363344, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                objRememberedValue = compositionScopedCoroutineScopeCanceller;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final CoroutineScope coroutineScope2 = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Orientation orientation = Orientation.Vertical;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final float fMo657toPx0680j_4 = ((Density) objConsume).mo657toPx0680j_4(f);
            composerStartRestartGroup.startReplaceGroup(-1831611516);
            ComposerKt.sourceInformation(composerStartRestartGroup, "231@10496L326");
            if (z) {
                Modifier.Companion companion = Modifier.INSTANCE;
                AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release = sheetState.getAnchoredDraggableState$material3_release();
                i5 = i3;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1831609222, "CC(remember):BottomSheetScaffold.kt#9igjgp");
                boolean zChanged = composerStartRestartGroup.changed(anchoredDraggableState$material3_release);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState, orientation, new Function1<Float, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$nestedScroll$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Float f5) {
                            invoke(f5.floatValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$nestedScroll$1$1$1, reason: invalid class name */
                        /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                        @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$nestedScroll$1$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {236}, m = "invokeSuspend", n = {}, s = {})
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ float $it;
                            final /* synthetic */ SheetState $state;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(SheetState sheetState, float f, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$state = sheetState;
                                this.$it = f;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$state, this.$it, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    this.label = 1;
                                    if (this.$state.settle$material3_release(this.$it, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } else {
                                    if (i != 1) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        public final void invoke(float f5) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(sheetState, f5, null), 3, null);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                coroutineScope = coroutineScope2;
                obj = null;
                companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) objRememberedValue2, null, 2, null);
            } else {
                coroutineScope = coroutineScope2;
                i5 = i3;
                obj = null;
                companionNestedScroll$default = Modifier.INSTANCE;
            }
            composerStartRestartGroup.endReplaceGroup();
            Modifier modifierThen = SizeKt.m1001requiredHeightInVpY3zN4$default(SizeKt.fillMaxWidth$default(SizeKt.m1017widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, f2, 1, obj), 0.0f, 1, obj), f, 0.0f, 2, obj).then(companionNestedScroll$default);
            AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release2 = sheetState.getAnchoredDraggableState$material3_release();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1831586234, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean zChanged2 = ((i5 & 14) == 4) | composerStartRestartGroup.changed(fMo657toPx0680j_4);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$1$1

                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[SheetValue.values().length];
                            try {
                                iArr[SheetValue.Hidden.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            try {
                                iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                            } catch (NoSuchFieldError unused2) {
                            }
                            try {
                                iArr[SheetValue.Expanded.ordinal()] = 3;
                            } catch (NoSuchFieldError unused3) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                        return m2103invokeGpV2Q24(intSize.getPackedValue(), constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-GpV2Q24, reason: not valid java name */
                    public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2103invokeGpV2Q24(long j3, long j4) {
                        final float fM6979getMaxHeightimpl = Constraints.m6979getMaxHeightimpl(j4);
                        final float fM7196getHeightimpl = IntSize.m7196getHeightimpl(j3);
                        final SheetState sheetState2 = sheetState;
                        final float f5 = fMo657toPx0680j_4;
                        DraggableAnchors DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$1$1$newAnchors$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                invoke2(draggableAnchorsConfig);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                if (!sheetState2.getSkipPartiallyExpanded()) {
                                    draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, fM6979getMaxHeightimpl - f5);
                                }
                                if (fM7196getHeightimpl != f5) {
                                    draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(fM6979getMaxHeightimpl - fM7196getHeightimpl, 0.0f));
                                }
                                if (sheetState2.getSkipHiddenState()) {
                                    return;
                                }
                                draggableAnchorsConfig.at(SheetValue.Hidden, fM6979getMaxHeightimpl);
                            }
                        });
                        SheetValue targetValue = sheetState.getAnchoredDraggableState$material3_release().getTargetValue();
                        int i6 = WhenMappings.$EnumSwitchMapping$0[targetValue.ordinal()];
                        if (i6 != 1) {
                            if (i6 != 2) {
                                if (i6 == 3) {
                                    if (DraggableAnchors.hasAnchorFor(SheetValue.Expanded)) {
                                        targetValue = SheetValue.Expanded;
                                    } else if (DraggableAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                                        targetValue = SheetValue.PartiallyExpanded;
                                    } else if (DraggableAnchors.hasAnchorFor(SheetValue.Hidden)) {
                                        targetValue = SheetValue.Hidden;
                                    }
                                } else {
                                    throw new NoWhenBranchMatchedException();
                                }
                            } else if (DraggableAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                                targetValue = SheetValue.PartiallyExpanded;
                            } else if (DraggableAnchors.hasAnchorFor(SheetValue.Expanded)) {
                                targetValue = SheetValue.Expanded;
                            } else if (DraggableAnchors.hasAnchorFor(SheetValue.Hidden)) {
                                targetValue = SheetValue.Hidden;
                            }
                        } else if (DraggableAnchors.hasAnchorFor(SheetValue.Hidden)) {
                            targetValue = SheetValue.Hidden;
                        }
                        return TuplesKt.to(DraggableAnchors, targetValue);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final CoroutineScope coroutineScope3 = coroutineScope;
            int i6 = i5 >> 9;
            composer2 = composerStartRestartGroup;
            SurfaceKt.m2846SurfaceT9BRK9s(AnchoredDraggableKt.anchoredDraggable$default(AnchoredDraggableKt.draggableAnchors(modifierThen, anchoredDraggableState$material3_release2, orientation, (Function2) objRememberedValue3), sheetState.getAnchoredDraggableState$material3_release(), orientation, z, false, null, 24, null), shape, j, j2, f3, f4, null, ComposableLambdaKt.rememberComposableLambda(390720907, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2
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

                public final void invoke(Composer composer3, int i7) {
                    ComposerKt.sourceInformation(composer3, "C295@13503L2281:BottomSheetScaffold.kt#uh7d8r");
                    if ((i7 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(390720907, i7, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous> (BottomSheetScaffold.kt:295)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Function2<Composer, Integer, Unit> function22 = function2;
                        final SheetState sheetState2 = sheetState;
                        final boolean z2 = z;
                        final CoroutineScope coroutineScope4 = coroutineScope3;
                        Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor);
                        } else {
                            composer3.useNode();
                        }
                        Composer composerM4013constructorimpl = Updater.m4013constructorimpl(composer3);
                        Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, -1850745504, "C337@15765L9:BottomSheetScaffold.kt#uh7d8r");
                        composer3.startReplaceGroup(-1168080147);
                        ComposerKt.sourceInformation(composer3, "298@13642L54,299@13738L48,300@13827L47,302@13986L1680,301@13891L1847");
                        if (function22 != null) {
                            Strings.Companion companion2 = Strings.INSTANCE;
                            final String strM3278getString2EP1pXo = Strings_androidKt.m3278getString2EP1pXo(Strings.m3208constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer3, 0);
                            Strings.Companion companion3 = Strings.INSTANCE;
                            final String strM3278getString2EP1pXo2 = Strings_androidKt.m3278getString2EP1pXo(Strings.m3208constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer3, 0);
                            Strings.Companion companion4 = Strings.INSTANCE;
                            final String strM3278getString2EP1pXo3 = Strings_androidKt.m3278getString2EP1pXo(Strings.m3208constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer3, 0);
                            Modifier modifierAlign = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                            ComposerKt.sourceInformationMarkerStart(composer3, -1168066686, "CC(remember):BottomSheetScaffold.kt#9igjgp");
                            boolean zChanged3 = composer3.changed(sheetState2) | composer3.changed(z2) | composer3.changed(strM3278getString2EP1pXo3) | composer3.changedInstance(coroutineScope4) | composer3.changed(strM3278getString2EP1pXo) | composer3.changed(strM3278getString2EP1pXo2);
                            Object objRememberedValue4 = composer3.rememberedValue();
                            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1
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
                                        final SheetState sheetState3 = sheetState2;
                                        boolean z3 = z2;
                                        String str = strM3278getString2EP1pXo3;
                                        String str2 = strM3278getString2EP1pXo;
                                        String str3 = strM3278getString2EP1pXo2;
                                        final CoroutineScope coroutineScope5 = coroutineScope4;
                                        if (sheetState3.getAnchoredDraggableState$material3_release().getAnchors().getSize() <= 1 || !z3) {
                                            return;
                                        }
                                        if (sheetState3.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                            if (sheetState3.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str, new Function0<Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1$1$1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1$1$1$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                                    @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1$1$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {311}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ SheetState $this_with;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$this_with = sheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$this_with, continuation);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Object invokeSuspend(Object obj) {
                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                            int i = this.label;
                                                            if (i == 0) {
                                                                ResultKt.throwOnFailure(obj);
                                                                this.label = 1;
                                                                if (this.$this_with.expand(this) == coroutine_suspended) {
                                                                    return coroutine_suspended;
                                                                }
                                                            } else {
                                                                if (i != 1) {
                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                }
                                                                ResultKt.throwOnFailure(obj);
                                                            }
                                                            return Unit.INSTANCE;
                                                        }
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope5, null, null, new AnonymousClass1(sheetState3, null), 3, null);
                                                        return true;
                                                    }
                                                });
                                            }
                                        } else if (sheetState3.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                            SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1$1$2
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1$1$2$1, reason: invalid class name */
                                                /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                                @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1$1$2$1", f = "BottomSheetScaffold.kt", i = {}, l = {320}, m = "invokeSuspend", n = {}, s = {})
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ SheetState $this_with;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$this_with = sheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$this_with, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object obj) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        int i = this.label;
                                                        if (i == 0) {
                                                            ResultKt.throwOnFailure(obj);
                                                            this.label = 1;
                                                            if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                return coroutine_suspended;
                                                            }
                                                        } else {
                                                            if (i != 1) {
                                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                            }
                                                            ResultKt.throwOnFailure(obj);
                                                        }
                                                        return Unit.INSTANCE;
                                                    }
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope5, null, null, new AnonymousClass1(sheetState3, null), 3, null);
                                                    return true;
                                                }
                                            });
                                        }
                                        if (sheetState3.getSkipHiddenState()) {
                                            return;
                                        }
                                        SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1$1$3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1$1$3$1, reason: invalid class name */
                                            /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                            @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1$1$1$1$3$1", f = "BottomSheetScaffold.kt", i = {}, l = {327}, m = "invokeSuspend", n = {}, s = {})
                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ SheetState $this_with;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                    super(2, continuation);
                                                    this.$this_with = sheetState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new AnonymousClass1(this.$this_with, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object obj) {
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    int i = this.label;
                                                    if (i == 0) {
                                                        ResultKt.throwOnFailure(obj);
                                                        this.label = 1;
                                                        if (this.$this_with.hide(this) == coroutine_suspended) {
                                                            return coroutine_suspended;
                                                        }
                                                    } else {
                                                        if (i != 1) {
                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                        }
                                                        ResultKt.throwOnFailure(obj);
                                                    }
                                                    return Unit.INSTANCE;
                                                }
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope5, null, null, new AnonymousClass1(sheetState3, null), 3, null);
                                                return true;
                                            }
                                        });
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifierAlign, true, (Function1) objRememberedValue4);
                            ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierSemantics);
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor2);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM4013constructorimpl2 = Updater.m4013constructorimpl(composer3);
                            Updater.m4020setimpl(composerM4013constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4020setimpl(composerM4013constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM4013constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                composerM4013constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                composerM4013constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                            }
                            Updater.m4020setimpl(composerM4013constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer3, -1678862234, "C334@15708L12:BottomSheetScaffold.kt#uh7d8r");
                            function22.invoke(composer3, 0);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                        }
                        composer3.endReplaceGroup();
                        function32.invoke(columnScopeInstance, composer3, 6);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composer2, (i6 & 112) | 12582912 | (i6 & 896) | (i6 & 7168) | (57344 & i6) | (i6 & 458752), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3
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

                public final void invoke(Composer composer3, int i7) {
                    BottomSheetScaffoldKt.m2100StandardBottomSheetw7I5h1o(sheetState, f, f2, z, shape, j, j2, f3, f4, function2, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: BottomSheetScaffoldLayout-2E65NiM, reason: not valid java name */
    public static final void m2099BottomSheetScaffoldLayout2E65NiM(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Function0<Float> function0, final SheetState sheetState, final long j, final long j2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1651214892);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BottomSheetScaffoldLayout)P(4,8!2,7,5,6,2:c#ui.graphics.Color,3:c#ui.graphics.Color)359@16300L255,370@16635L1950,355@16183L2402:BottomSheetScaffold.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function22) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function23) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function24) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(sheetState) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(j2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i2 & 38347923) != 38347922 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1651214892, i2, -1, "androidx.compose.material3.BottomSheetScaffoldLayout (BottomSheetScaffold.kt:354)");
            }
            Function2[] function2Arr = new Function2[4];
            function2Arr[0] = function2 == null ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m2263getLambda3$material3_release() : function2;
            function2Arr[1] = ComposableLambdaKt.rememberComposableLambda(398963586, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1
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

                public final void invoke(Composer composer2, int i3) {
                    ComposerKt.sourceInformation(composer2, "C360@16322L215:BottomSheetScaffold.kt#uh7d8r");
                    if ((i3 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(398963586, i3, -1, "androidx.compose.material3.BottomSheetScaffoldLayout.<anonymous> (BottomSheetScaffold.kt:360)");
                        }
                        SurfaceKt.m2846SurfaceT9BRK9s(modifier, null, j, j2, 0.0f, 0.0f, null, function22, composer2, 0, 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54);
            function2Arr[2] = function23;
            function2Arr[3] = function24;
            List listListOf = CollectionsKt.listOf((Object[]) function2Arr);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1221133327, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean z = ((3670016 & i2) == 1048576) | ((i2 & 458752) == 131072);
            MultiContentMeasurePolicy multiContentMeasurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || multiContentMeasurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
                multiContentMeasurePolicyRememberedValue = new MultiContentMeasurePolicy() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1
                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo913measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j3) {
                        Integer numValueOf;
                        List<? extends Measurable> list2 = list.get(0);
                        int i3 = 1;
                        List<? extends Measurable> list3 = list.get(1);
                        List<? extends Measurable> list4 = list.get(2);
                        List<? extends Measurable> list5 = list.get(3);
                        final int iM6980getMaxWidthimpl = Constraints.m6980getMaxWidthimpl(j3);
                        final int iM6979getMaxHeightimpl = Constraints.m6979getMaxHeightimpl(j3);
                        long jM6971copyZbe2FdA$default = Constraints.m6971copyZbe2FdA$default(j3, 0, 0, 0, 0, 10, null);
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
                            numValueOf = null;
                        } else {
                            numValueOf = Integer.valueOf(((Placeable) arrayList4.get(0)).getHeight());
                            int lastIndex = CollectionsKt.getLastIndex(arrayList4);
                            if (1 <= lastIndex) {
                                while (true) {
                                    Integer numValueOf2 = Integer.valueOf(((Placeable) arrayList4.get(i3)).getHeight());
                                    if (numValueOf2.compareTo(numValueOf) > 0) {
                                        numValueOf = numValueOf2;
                                    }
                                    if (i3 == lastIndex) {
                                        break;
                                    }
                                    i3++;
                                }
                            }
                        }
                        Integer num = numValueOf;
                        final int iIntValue = num != null ? num.intValue() : 0;
                        long jM6971copyZbe2FdA$default2 = Constraints.m6971copyZbe2FdA$default(jM6971copyZbe2FdA$default, 0, 0, 0, iM6979getMaxHeightimpl - iIntValue, 7, null);
                        ArrayList arrayList5 = new ArrayList(list3.size());
                        int size3 = list3.size();
                        for (int i6 = 0; i6 < size3; i6++) {
                            arrayList5.add(list3.get(i6).mo5903measureBRTryo0(jM6971copyZbe2FdA$default2));
                        }
                        final ArrayList arrayList6 = arrayList5;
                        ArrayList arrayList7 = new ArrayList(list5.size());
                        int size4 = list5.size();
                        for (int i7 = 0; i7 < size4; i7++) {
                            arrayList7.add(list5.get(i7).mo5903measureBRTryo0(jM6971copyZbe2FdA$default));
                        }
                        final ArrayList arrayList8 = arrayList7;
                        final SheetState sheetState2 = sheetState;
                        final Function0<Float> function02 = function0;
                        return MeasureScope.layout$default(measureScope, iM6980getMaxWidthimpl, iM6979getMaxHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1.1

                            /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1$1$WhenMappings */
                            /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                            public /* synthetic */ class WhenMappings {
                                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                                static {
                                    int[] iArr = new int[SheetValue.values().length];
                                    try {
                                        iArr[SheetValue.PartiallyExpanded.ordinal()] = 1;
                                    } catch (NoSuchFieldError unused) {
                                    }
                                    try {
                                        iArr[SheetValue.Expanded.ordinal()] = 2;
                                    } catch (NoSuchFieldError unused2) {
                                    }
                                    try {
                                        iArr[SheetValue.Hidden.ordinal()] = 3;
                                    } catch (NoSuchFieldError unused3) {
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
                                Integer numValueOf3;
                                Integer numValueOf4;
                                int iRoundToInt;
                                List<Placeable> list6 = arrayList2;
                                Integer numValueOf5 = null;
                                if (list6.isEmpty()) {
                                    numValueOf3 = null;
                                } else {
                                    numValueOf3 = Integer.valueOf(list6.get(0).getWidth());
                                    int lastIndex2 = CollectionsKt.getLastIndex(list6);
                                    if (1 <= lastIndex2) {
                                        int i8 = 1;
                                        while (true) {
                                            Integer numValueOf6 = Integer.valueOf(list6.get(i8).getWidth());
                                            if (numValueOf6.compareTo(numValueOf3) > 0) {
                                                numValueOf3 = numValueOf6;
                                            }
                                            if (i8 == lastIndex2) {
                                                break;
                                            } else {
                                                i8++;
                                            }
                                        }
                                    }
                                }
                                Integer num2 = numValueOf3;
                                int iMax = Integer.max(0, (iM6980getMaxWidthimpl - (num2 != null ? num2.intValue() : 0)) / 2);
                                List<Placeable> list7 = arrayList8;
                                if (list7.isEmpty()) {
                                    numValueOf4 = null;
                                } else {
                                    numValueOf4 = Integer.valueOf(list7.get(0).getWidth());
                                    int lastIndex3 = CollectionsKt.getLastIndex(list7);
                                    if (1 <= lastIndex3) {
                                        int i9 = 1;
                                        while (true) {
                                            Integer numValueOf7 = Integer.valueOf(list7.get(i9).getWidth());
                                            if (numValueOf7.compareTo(numValueOf4) > 0) {
                                                numValueOf4 = numValueOf7;
                                            }
                                            if (i9 == lastIndex3) {
                                                break;
                                            } else {
                                                i9++;
                                            }
                                        }
                                    }
                                }
                                Integer num3 = numValueOf4;
                                int iIntValue2 = num3 != null ? num3.intValue() : 0;
                                List<Placeable> list8 = arrayList8;
                                if (!list8.isEmpty()) {
                                    numValueOf5 = Integer.valueOf(list8.get(0).getHeight());
                                    int lastIndex4 = CollectionsKt.getLastIndex(list8);
                                    if (1 <= lastIndex4) {
                                        int i10 = 1;
                                        while (true) {
                                            Integer numValueOf8 = Integer.valueOf(list8.get(i10).getHeight());
                                            if (numValueOf8.compareTo(numValueOf5) > 0) {
                                                numValueOf5 = numValueOf8;
                                            }
                                            if (i10 == lastIndex4) {
                                                break;
                                            } else {
                                                i10++;
                                            }
                                        }
                                    }
                                }
                                Integer num4 = numValueOf5;
                                int iIntValue3 = num4 != null ? num4.intValue() : 0;
                                int i11 = (iM6980getMaxWidthimpl - iIntValue2) / 2;
                                int i12 = WhenMappings.$EnumSwitchMapping$0[sheetState2.getCurrentValue().ordinal()];
                                if (i12 == 1) {
                                    iRoundToInt = MathKt.roundToInt(function02.invoke().floatValue());
                                } else {
                                    if (i12 != 2 && i12 != 3) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    iRoundToInt = iM6979getMaxHeightimpl;
                                }
                                int i13 = iRoundToInt - iIntValue3;
                                List<Placeable> list9 = arrayList6;
                                int i14 = iIntValue;
                                int size5 = list9.size();
                                for (int i15 = 0; i15 < size5; i15++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, list9.get(i15), 0, i14, 0.0f, 4, null);
                                }
                                List<Placeable> list10 = arrayList4;
                                int size6 = list10.size();
                                for (int i16 = 0; i16 < size6; i16++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, list10.get(i16), 0, 0, 0.0f, 4, null);
                                }
                                List<Placeable> list11 = arrayList2;
                                int size7 = list11.size();
                                for (int i17 = 0; i17 < size7; i17++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, list11.get(i17), iMax, 0, 0.0f, 4, null);
                                }
                                List<Placeable> list12 = arrayList8;
                                int size8 = list12.size();
                                for (int i18 = 0; i18 < size8; i18++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, list12.get(i18), i11, i13, 0.0f, 4, null);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$3
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

                public final void invoke(Composer composer2, int i3) {
                    BottomSheetScaffoldKt.m2099BottomSheetScaffoldLayout2E65NiM(modifier, function2, function22, function23, function24, function0, sheetState, j, j2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }
}
