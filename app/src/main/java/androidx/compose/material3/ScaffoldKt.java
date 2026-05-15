package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Scaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a±\u0001\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0013\b\u0002\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0013\b\u0002\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0013\b\u0002\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00040\u0015¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0087\u0001\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u000e2\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00040\u0015¢\u0006\u0002\b\t2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0011\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\tH\u0003ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"FabSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "Scaffold", "", "modifier", "Landroidx/compose/ui/Modifier;", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "bottomBar", "snackbarHost", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material3/FabPosition;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/PaddingValues;", "Scaffold-TvnljyQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;IJJLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ScaffoldLayout", "fabPosition", "snackbar", "fab", "ScaffoldLayout-FMILGgc", "(ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ScaffoldKt {
    private static final float FabSpacing = Dp.m7027constructorimpl(16);

    /* JADX WARN: Removed duplicated region for block: B:100:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:199:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0117  */
    /* JADX INFO: renamed from: Scaffold-TvnljyQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2711ScaffoldTvnljyQ(androidx.compose.ui.Modifier r28, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, int r33, long r34, long r36, androidx.compose.foundation.layout.WindowInsets r38, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, androidx.compose.runtime.Composer r40, final int r41, final int r42) {
        /*
            Method dump skipped, instruction units count: 780
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt.m2711ScaffoldTvnljyQ(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, int, long, long, androidx.compose.foundation.layout.WindowInsets, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ScaffoldLayout-FMILGgc, reason: not valid java name */
    public static final void m2712ScaffoldLayoutFMILGgc(final int i, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final WindowInsets windowInsets, final Function2<? super Composer, ? super Integer, Unit> function24, Composer composer, final int i2) {
        int i3;
        Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function32;
        Function2<? super Composer, ? super Integer, Unit> function25;
        final WindowInsets windowInsets2;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-975511942);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScaffoldLayout)P(4:c#material3.FabPosition,6,1,5,3,2)139@6582L6951,139@6565L6968:Scaffold.kt#uh7d8r");
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            function32 = function3;
            i3 |= composerStartRestartGroup.changedInstance(function32) ? 256 : 128;
        } else {
            function32 = function3;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function22) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            function25 = function23;
            i3 |= composerStartRestartGroup.changedInstance(function25) ? 16384 : 8192;
        } else {
            function25 = function23;
        }
        if ((196608 & i2) == 0) {
            windowInsets2 = windowInsets;
            i3 |= composerStartRestartGroup.changed(windowInsets2) ? 131072 : 65536;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i2 & 1572864) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function24) ? 1048576 : 524288;
        }
        if ((i3 & 599187) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-975511942, i3, -1, "androidx.compose.material3.ScaffoldLayout (Scaffold.kt:138)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1690368138, "CC(remember):Scaffold.kt#9igjgp");
            boolean z = ((i3 & 112) == 32) | ((i3 & 7168) == 2048) | ((458752 & i3) == 131072) | ((57344 & i3) == 16384) | ((i3 & 14) == 4) | ((3670016 & i3) == 1048576) | ((i3 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function33 = function32;
                i4 = 0;
                final Function2<? super Composer, ? super Integer, Unit> function26 = function25;
                objRememberedValue = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m2714invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m2714invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, long j) {
                        Object obj;
                        Object obj2;
                        Object obj3;
                        ArrayList arrayList;
                        FabPlacement fabPlacement;
                        Object obj4;
                        Integer numValueOf;
                        int i5;
                        int iIntValue;
                        int height;
                        int bottom;
                        Object obj5;
                        Object obj6;
                        int i6;
                        int i7;
                        final int iM6980getMaxWidthimpl = Constraints.m6980getMaxWidthimpl(j);
                        final int iM6979getMaxHeightimpl = Constraints.m6979getMaxHeightimpl(j);
                        long jM6971copyZbe2FdA$default = Constraints.m6971copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.TopBar, function2);
                        ArrayList arrayList2 = new ArrayList(listSubcompose.size());
                        int size = listSubcompose.size();
                        for (int i8 = 0; i8 < size; i8++) {
                            arrayList2.add(listSubcompose.get(i8).mo5903measureBRTryo0(jM6971copyZbe2FdA$default));
                        }
                        ArrayList arrayList3 = arrayList2;
                        if (arrayList3.isEmpty()) {
                            obj = null;
                        } else {
                            obj = arrayList3.get(0);
                            int height2 = ((Placeable) obj).getHeight();
                            int lastIndex = CollectionsKt.getLastIndex(arrayList3);
                            if (1 <= lastIndex) {
                                int i9 = 1;
                                while (true) {
                                    Object obj7 = arrayList3.get(i9);
                                    int height3 = ((Placeable) obj7).getHeight();
                                    if (height2 < height3) {
                                        obj = obj7;
                                        height2 = height3;
                                    }
                                    if (i9 == lastIndex) {
                                        break;
                                    }
                                    i9++;
                                }
                            }
                        }
                        Placeable placeable = (Placeable) obj;
                        int height4 = placeable != null ? placeable.getHeight() : 0;
                        List<Measurable> listSubcompose2 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Snackbar, function22);
                        WindowInsets windowInsets3 = windowInsets2;
                        ArrayList arrayList4 = new ArrayList(listSubcompose2.size());
                        int size2 = listSubcompose2.size();
                        int i10 = 0;
                        while (i10 < size2) {
                            SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                            arrayList4.add(listSubcompose2.get(i10).mo5903measureBRTryo0(ConstraintsKt.m6999offsetNN6EwU(jM6971copyZbe2FdA$default, (-windowInsets3.getLeft(subcomposeMeasureScope2, subcomposeMeasureScope.getLayoutDirection())) - windowInsets3.getRight(subcomposeMeasureScope2, subcomposeMeasureScope.getLayoutDirection()), -windowInsets3.getBottom(subcomposeMeasureScope2))));
                            i10++;
                            height4 = height4;
                            listSubcompose2 = listSubcompose2;
                            arrayList3 = arrayList3;
                        }
                        final ArrayList arrayList5 = arrayList3;
                        final int i11 = height4;
                        final ArrayList arrayList6 = arrayList4;
                        if (arrayList6.isEmpty()) {
                            obj2 = null;
                        } else {
                            obj2 = arrayList6.get(0);
                            int height5 = ((Placeable) obj2).getHeight();
                            int lastIndex2 = CollectionsKt.getLastIndex(arrayList6);
                            if (1 <= lastIndex2) {
                                Object obj8 = obj2;
                                int i12 = height5;
                                int i13 = 1;
                                while (true) {
                                    Object obj9 = arrayList6.get(i13);
                                    int height6 = ((Placeable) obj9).getHeight();
                                    if (i12 < height6) {
                                        obj8 = obj9;
                                        i12 = height6;
                                    }
                                    if (i13 == lastIndex2) {
                                        break;
                                    }
                                    i13++;
                                }
                                obj2 = obj8;
                            }
                        }
                        Placeable placeable2 = (Placeable) obj2;
                        int height7 = placeable2 != null ? placeable2.getHeight() : 0;
                        if (arrayList6.isEmpty()) {
                            obj3 = null;
                        } else {
                            obj3 = arrayList6.get(0);
                            int width = ((Placeable) obj3).getWidth();
                            int lastIndex3 = CollectionsKt.getLastIndex(arrayList6);
                            if (1 <= lastIndex3) {
                                Object obj10 = obj3;
                                int i14 = width;
                                int i15 = 1;
                                while (true) {
                                    Object obj11 = arrayList6.get(i15);
                                    int width2 = ((Placeable) obj11).getWidth();
                                    if (i14 < width2) {
                                        obj10 = obj11;
                                        i14 = width2;
                                    }
                                    if (i15 == lastIndex3) {
                                        break;
                                    }
                                    i15++;
                                }
                                obj3 = obj10;
                            }
                        }
                        Placeable placeable3 = (Placeable) obj3;
                        int width3 = placeable3 != null ? placeable3.getWidth() : 0;
                        List<Measurable> listSubcompose3 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Fab, function26);
                        WindowInsets windowInsets4 = windowInsets2;
                        ArrayList arrayList7 = new ArrayList(listSubcompose3.size());
                        int size3 = listSubcompose3.size();
                        int i16 = 0;
                        while (i16 < size3) {
                            int i17 = height7;
                            SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope;
                            List<Measurable> list = listSubcompose3;
                            ArrayList arrayList8 = arrayList7;
                            Placeable placeableMo5903measureBRTryo0 = listSubcompose3.get(i16).mo5903measureBRTryo0(ConstraintsKt.m6999offsetNN6EwU(jM6971copyZbe2FdA$default, (-windowInsets4.getLeft(subcomposeMeasureScope3, subcomposeMeasureScope.getLayoutDirection())) - windowInsets4.getRight(subcomposeMeasureScope3, subcomposeMeasureScope.getLayoutDirection()), -windowInsets4.getBottom(subcomposeMeasureScope3)));
                            if (placeableMo5903measureBRTryo0.getHeight() == 0 || placeableMo5903measureBRTryo0.getWidth() == 0) {
                                placeableMo5903measureBRTryo0 = null;
                            }
                            if (placeableMo5903measureBRTryo0 != null) {
                                arrayList8.add(placeableMo5903measureBRTryo0);
                            }
                            i16++;
                            height7 = i17;
                            listSubcompose3 = list;
                            arrayList7 = arrayList8;
                        }
                        int i18 = height7;
                        ArrayList arrayList9 = arrayList7;
                        if (arrayList9.isEmpty()) {
                            arrayList = arrayList9;
                            fabPlacement = null;
                        } else {
                            if (arrayList9.isEmpty()) {
                                obj5 = null;
                            } else {
                                obj5 = arrayList9.get(0);
                                int width4 = ((Placeable) obj5).getWidth();
                                int lastIndex4 = CollectionsKt.getLastIndex(arrayList9);
                                if (1 <= lastIndex4) {
                                    Object obj12 = obj5;
                                    int i19 = width4;
                                    int i20 = 1;
                                    while (true) {
                                        Object obj13 = arrayList9.get(i20);
                                        int width5 = ((Placeable) obj13).getWidth();
                                        if (i19 < width5) {
                                            obj12 = obj13;
                                            i19 = width5;
                                        }
                                        if (i20 == lastIndex4) {
                                            break;
                                        }
                                        i20++;
                                    }
                                    obj5 = obj12;
                                }
                            }
                            Intrinsics.checkNotNull(obj5);
                            int width6 = ((Placeable) obj5).getWidth();
                            if (arrayList9.isEmpty()) {
                                arrayList = arrayList9;
                                obj6 = null;
                            } else {
                                obj6 = arrayList9.get(0);
                                int height8 = ((Placeable) obj6).getHeight();
                                int lastIndex5 = CollectionsKt.getLastIndex(arrayList9);
                                if (1 <= lastIndex5) {
                                    Object obj14 = obj6;
                                    int i21 = height8;
                                    int i22 = 1;
                                    while (true) {
                                        Object obj15 = arrayList9.get(i22);
                                        arrayList = arrayList9;
                                        int height9 = ((Placeable) obj15).getHeight();
                                        if (i21 < height9) {
                                            i21 = height9;
                                            obj14 = obj15;
                                        }
                                        if (i22 == lastIndex5) {
                                            break;
                                        }
                                        i22++;
                                        arrayList9 = arrayList;
                                    }
                                    obj6 = obj14;
                                } else {
                                    arrayList = arrayList9;
                                }
                            }
                            Intrinsics.checkNotNull(obj6);
                            int height10 = ((Placeable) obj6).getHeight();
                            int i23 = i;
                            if (FabPosition.m2403equalsimpl0(i23, FabPosition.INSTANCE.m2410getStartERTFSPs())) {
                                if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                                    i6 = subcomposeMeasureScope.mo651roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    fabPlacement = new FabPlacement(i6, width6, height10);
                                } else {
                                    i7 = subcomposeMeasureScope.mo651roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    i6 = (iM6980getMaxWidthimpl - i7) - width6;
                                    fabPlacement = new FabPlacement(i6, width6, height10);
                                }
                            } else {
                                if (FabPosition.m2403equalsimpl0(i23, FabPosition.INSTANCE.m2408getEndERTFSPs()) ? true : FabPosition.m2403equalsimpl0(i23, FabPosition.INSTANCE.m2409getEndOverlayERTFSPs())) {
                                    if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                                        i7 = subcomposeMeasureScope.mo651roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                        i6 = (iM6980getMaxWidthimpl - i7) - width6;
                                    } else {
                                        i6 = subcomposeMeasureScope.mo651roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    }
                                } else {
                                    i6 = (iM6980getMaxWidthimpl - width6) / 2;
                                }
                                fabPlacement = new FabPlacement(i6, width6, height10);
                            }
                        }
                        ScaffoldLayoutContent scaffoldLayoutContent = ScaffoldLayoutContent.BottomBar;
                        final Function2<Composer, Integer, Unit> function27 = function24;
                        List<Measurable> listSubcompose4 = subcomposeMeasureScope.subcompose(scaffoldLayoutContent, ComposableLambdaKt.composableLambdaInstance(-2146438447, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1$bottomBarPlaceables$1
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

                            public final void invoke(Composer composer2, int i24) {
                                ComposerKt.sourceInformation(composer2, "C209@10015L11:Scaffold.kt#uh7d8r");
                                if ((i24 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2146438447, i24, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:209)");
                                }
                                function27.invoke(composer2, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        ArrayList arrayList10 = new ArrayList(listSubcompose4.size());
                        int size4 = listSubcompose4.size();
                        for (int i24 = 0; i24 < size4; i24++) {
                            arrayList10.add(listSubcompose4.get(i24).mo5903measureBRTryo0(jM6971copyZbe2FdA$default));
                        }
                        final ArrayList arrayList11 = arrayList10;
                        if (arrayList11.isEmpty()) {
                            obj4 = null;
                        } else {
                            obj4 = arrayList11.get(0);
                            int height11 = ((Placeable) obj4).getHeight();
                            int lastIndex6 = CollectionsKt.getLastIndex(arrayList11);
                            if (1 <= lastIndex6) {
                                int i25 = 1;
                                while (true) {
                                    Object obj16 = arrayList11.get(i25);
                                    int height12 = ((Placeable) obj16).getHeight();
                                    if (height11 < height12) {
                                        height11 = height12;
                                        obj4 = obj16;
                                    }
                                    if (i25 == lastIndex6) {
                                        break;
                                    }
                                    i25++;
                                }
                            }
                        }
                        Placeable placeable4 = (Placeable) obj4;
                        final Integer numValueOf2 = placeable4 != null ? Integer.valueOf(placeable4.getHeight()) : null;
                        if (fabPlacement != null) {
                            int i26 = i;
                            WindowInsets windowInsets5 = windowInsets2;
                            if (numValueOf2 == null || FabPosition.m2403equalsimpl0(i26, FabPosition.INSTANCE.m2409getEndOverlayERTFSPs())) {
                                height = fabPlacement.getHeight() + subcomposeMeasureScope.mo651roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                bottom = windowInsets5.getBottom(subcomposeMeasureScope);
                            } else {
                                height = numValueOf2.intValue() + fabPlacement.getHeight();
                                bottom = subcomposeMeasureScope.mo651roundToPx0680j_4(ScaffoldKt.FabSpacing);
                            }
                            numValueOf = Integer.valueOf(height + bottom);
                        } else {
                            numValueOf = null;
                        }
                        if (i18 != 0) {
                            if (numValueOf != null) {
                                iIntValue = numValueOf.intValue();
                            } else {
                                iIntValue = numValueOf2 != null ? numValueOf2.intValue() : windowInsets2.getBottom(subcomposeMeasureScope);
                            }
                            i5 = i18 + iIntValue;
                        } else {
                            i5 = 0;
                        }
                        ScaffoldLayoutContent scaffoldLayoutContent2 = ScaffoldLayoutContent.MainContent;
                        final WindowInsets windowInsets6 = windowInsets2;
                        final Function3<PaddingValues, Composer, Integer, Unit> function34 = function33;
                        List<Measurable> listSubcompose5 = subcomposeMeasureScope.subcompose(scaffoldLayoutContent2, ComposableLambdaKt.composableLambdaInstance(-1213360416, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1$bodyContentPlaceables$1
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

                            public final void invoke(Composer composer2, int i27) {
                                float top;
                                float bottom2;
                                Integer num;
                                ComposerKt.sourceInformation(composer2, "C260@12377L21:Scaffold.kt#uh7d8r");
                                if ((i27 & 3) != 2 || !composer2.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1213360416, i27, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:238)");
                                    }
                                    PaddingValues paddingValuesAsPaddingValues = WindowInsetsKt.asPaddingValues(windowInsets6, subcomposeMeasureScope);
                                    if (arrayList5.isEmpty()) {
                                        top = paddingValuesAsPaddingValues.getTop();
                                    } else {
                                        top = subcomposeMeasureScope.mo654toDpu2uoSUM(i11);
                                    }
                                    if (arrayList11.isEmpty() || (num = numValueOf2) == null) {
                                        bottom2 = paddingValuesAsPaddingValues.getBottom();
                                    } else {
                                        bottom2 = subcomposeMeasureScope.mo654toDpu2uoSUM(num.intValue());
                                    }
                                    function34.invoke(PaddingKt.m961PaddingValuesa9UjIt4(PaddingKt.calculateStartPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope.getLayoutDirection()), top, PaddingKt.calculateEndPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope.getLayoutDirection()), bottom2), composer2, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer2.skipToGroupEnd();
                            }
                        }));
                        ArrayList arrayList12 = new ArrayList(listSubcompose5.size());
                        int size5 = listSubcompose5.size();
                        for (int i27 = 0; i27 < size5; i27++) {
                            arrayList12.add(listSubcompose5.get(i27).mo5903measureBRTryo0(jM6971copyZbe2FdA$default));
                        }
                        final ArrayList arrayList13 = arrayList12;
                        final Integer num = numValueOf2;
                        final WindowInsets windowInsets7 = windowInsets2;
                        final int i28 = width3;
                        final Integer num2 = numValueOf;
                        final int i29 = i5;
                        final FabPlacement fabPlacement2 = fabPlacement;
                        final ArrayList arrayList14 = arrayList;
                        return MeasureScope.layout$default(subcomposeMeasureScope, iM6980getMaxWidthimpl, iM6979getMaxHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1.1
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
                                List<Placeable> list2 = arrayList13;
                                int size6 = list2.size();
                                for (int i30 = 0; i30 < size6; i30++) {
                                    Placeable.PlacementScope.place$default(placementScope, list2.get(i30), 0, 0, 0.0f, 4, null);
                                }
                                List<Placeable> list3 = arrayList5;
                                int size7 = list3.size();
                                for (int i31 = 0; i31 < size7; i31++) {
                                    Placeable.PlacementScope.place$default(placementScope, list3.get(i31), 0, 0, 0.0f, 4, null);
                                }
                                List<Placeable> list4 = arrayList6;
                                int i32 = iM6980getMaxWidthimpl;
                                int i33 = i28;
                                WindowInsets windowInsets8 = windowInsets7;
                                SubcomposeMeasureScope subcomposeMeasureScope4 = subcomposeMeasureScope;
                                int i34 = iM6979getMaxHeightimpl;
                                int i35 = i29;
                                int size8 = list4.size();
                                for (int i36 = 0; i36 < size8; i36++) {
                                    Placeable.PlacementScope.place$default(placementScope, list4.get(i36), ((i32 - i33) / 2) + windowInsets8.getLeft(subcomposeMeasureScope4, subcomposeMeasureScope4.getLayoutDirection()), i34 - i35, 0.0f, 4, null);
                                }
                                List<Placeable> list5 = arrayList11;
                                int i37 = iM6979getMaxHeightimpl;
                                Integer num3 = num;
                                int size9 = list5.size();
                                for (int i38 = 0; i38 < size9; i38++) {
                                    Placeable.PlacementScope.place$default(placementScope, list5.get(i38), 0, i37 - (num3 != null ? num3.intValue() : 0), 0.0f, 4, null);
                                }
                                FabPlacement fabPlacement3 = fabPlacement2;
                                if (fabPlacement3 != null) {
                                    List<Placeable> list6 = arrayList14;
                                    int i39 = iM6979getMaxHeightimpl;
                                    Integer num4 = num2;
                                    int size10 = list6.size();
                                    for (int i40 = 0; i40 < size10; i40++) {
                                        Placeable placeable5 = list6.get(i40);
                                        int left = fabPlacement3.getLeft();
                                        Intrinsics.checkNotNull(num4);
                                        Placeable.PlacementScope.place$default(placementScope, placeable5, left, i39 - num4.intValue(), 0.0f, 4, null);
                                    }
                                }
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                i4 = 0;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) objRememberedValue, composerStartRestartGroup, i4, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$2
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

                public final void invoke(Composer composer2, int i5) {
                    ScaffoldKt.m2712ScaffoldLayoutFMILGgc(i, function2, function3, function22, function23, windowInsets, function24, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                }
            });
        }
    }
}
