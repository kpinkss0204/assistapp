package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\u001aA\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\nH\u0083\b\u001a\u008c\u0001\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001a¸\u0002\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00020-2\u0006\u0010.\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020423\u00105\u001a/\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020+090\u00010\n2/\u0010:\u001a+\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020=0\n¢\u0006\u0002\b>\u0012\u0004\u0012\u00020?0;H\u0000ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a+\u0010B\u001a\u00020=\"\u0004\b\u0000\u0010C*\b\u0012\u0004\u0012\u0002HC0\r2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002HC0EH\u0002¢\u0006\u0002\u0010F\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006G"}, d2 = {"calculateExtraItems", "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "pinnedItems", "", "measuredItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "filter", "Lkotlin/Function1;", "", "calculateItemsOffsets", "", "lines", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "isVertical", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "measureLazyGrid", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "itemsCount", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "firstVisibleLineScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "slotsPerLine", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "prefetchInfoRetriever", "Lkotlin/ParameterName;", "name", "line", "Lkotlin/Pair;", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyGrid-OZKpZRA", "(ILandroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "addAllFromArray", ExifInterface.GPS_DIRECTION_TRUE, "arr", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LazyGridMeasureKt {
    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    /* JADX INFO: renamed from: measureLazyGrid-OZKpZRA, reason: not valid java name */
    public static final LazyGridMeasureResult m1106measureLazyGridOZKpZRA(int i, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyLayoutItemAnimator<LazyGridMeasuredItem> lazyLayoutItemAnimator, int i8, List<Integer> list, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, Function1<? super Integer, ? extends List<Pair<Integer, Constraints>>> function1, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int i9;
        int iM6997constrainWidthK40F9xA;
        int iM6979getMaxHeightimpl;
        int iM6997constrainWidthK40F9xA2;
        List<LazyGridMeasuredItem> list2;
        int i10;
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        int i11;
        int i12;
        int i13 = i;
        List<Integer> list3 = list;
        if (i3 < 0) {
            throw new IllegalArgumentException("negative beforeContentPadding".toString());
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("negative afterContentPadding".toString());
        }
        if (i13 <= 0) {
            int iM6982getMinWidthimpl = Constraints.m6982getMinWidthimpl(j);
            int iM6981getMinHeightimpl = Constraints.m6981getMinHeightimpl(j);
            lazyLayoutItemAnimator.onMeasured(0, iM6982getMinWidthimpl, iM6981getMinHeightimpl, new ArrayList(), lazyGridMeasuredItemProvider.getKeyIndexMap(), lazyGridMeasuredItemProvider, z, false, i8, false, 0, 0, coroutineScope, graphicsContext);
            long jM1130getMinSizeToFitDisappearingItemsYbymL2g = lazyLayoutItemAnimator.m1130getMinSizeToFitDisappearingItemsYbymL2g();
            if (!IntSize.m7195equalsimpl0(jM1130getMinSizeToFitDisappearingItemsYbymL2g, IntSize.INSTANCE.m7202getZeroYbymL2g())) {
                iM6982getMinWidthimpl = ConstraintsKt.m6997constrainWidthK40F9xA(j, IntSize.m7197getWidthimpl(jM1130getMinSizeToFitDisappearingItemsYbymL2g));
                iM6981getMinHeightimpl = ConstraintsKt.m6996constrainHeightK40F9xA(j, IntSize.m7196getHeightimpl(jM1130getMinSizeToFitDisappearingItemsYbymL2g));
            }
            return new LazyGridMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(iM6982getMinWidthimpl), Integer.valueOf(iM6981getMinHeightimpl), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }), false, coroutineScope, density, i8, function1, CollectionsKt.emptyList(), -i3, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
        }
        int iRound = Math.round(f);
        int i14 = i7 - iRound;
        if (i6 == 0 && i14 < 0) {
            iRound += i14;
            i14 = 0;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i15 = -i3;
        int i16 = (i5 < 0 ? i5 : 0) + i15;
        int mainAxisSizeWithSpacings = i14 + i16;
        int i17 = i6;
        while (mainAxisSizeWithSpacings < 0 && i17 > 0) {
            i17--;
            LazyGridMeasuredLine andMeasure = lazyGridMeasuredLineProvider.getAndMeasure(i17);
            arrayDeque.add(0, andMeasure);
            mainAxisSizeWithSpacings += andMeasure.getMainAxisSizeWithSpacings();
        }
        if (mainAxisSizeWithSpacings < i16) {
            iRound += mainAxisSizeWithSpacings;
            mainAxisSizeWithSpacings = i16;
        }
        int mainAxisSizeWithSpacings2 = mainAxisSizeWithSpacings - i16;
        int i18 = i2 + i4;
        int i19 = iRound;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i18, 0);
        int i20 = i17;
        int i21 = i20;
        int mainAxisSizeWithSpacings3 = -mainAxisSizeWithSpacings2;
        int i22 = 0;
        boolean z3 = false;
        while (i22 < arrayDeque.size()) {
            if (mainAxisSizeWithSpacings3 >= iCoerceAtLeast) {
                arrayDeque.remove(i22);
                z3 = true;
            } else {
                i21++;
                mainAxisSizeWithSpacings3 += ((LazyGridMeasuredLine) arrayDeque.get(i22)).getMainAxisSizeWithSpacings();
                i22++;
            }
        }
        int i23 = i21;
        while (i23 < i13 && (mainAxisSizeWithSpacings3 < iCoerceAtLeast || mainAxisSizeWithSpacings3 <= 0 || arrayDeque.isEmpty())) {
            LazyGridMeasuredLine andMeasure2 = lazyGridMeasuredLineProvider.getAndMeasure(i23);
            if (andMeasure2.isEmpty()) {
                break;
            }
            int mainAxisSizeWithSpacings4 = mainAxisSizeWithSpacings3 + andMeasure2.getMainAxisSizeWithSpacings();
            if (mainAxisSizeWithSpacings4 <= i16) {
                i11 = iCoerceAtLeast;
                i12 = mainAxisSizeWithSpacings4;
                if (((LazyGridMeasuredItem) ArraysKt.last(andMeasure2.getItems())).getIndex() != i13 - 1) {
                    mainAxisSizeWithSpacings2 -= andMeasure2.getMainAxisSizeWithSpacings();
                    i20 = i23 + 1;
                    z3 = true;
                }
                i23++;
                mainAxisSizeWithSpacings3 = i12;
                iCoerceAtLeast = i11;
            } else {
                i11 = iCoerceAtLeast;
                i12 = mainAxisSizeWithSpacings4;
            }
            arrayDeque.add(andMeasure2);
            i23++;
            mainAxisSizeWithSpacings3 = i12;
            iCoerceAtLeast = i11;
        }
        if (mainAxisSizeWithSpacings3 < i2) {
            int i24 = i2 - mainAxisSizeWithSpacings3;
            mainAxisSizeWithSpacings2 -= i24;
            mainAxisSizeWithSpacings3 += i24;
            while (mainAxisSizeWithSpacings2 < i3 && i20 > 0) {
                int i25 = i20 - 1;
                LazyGridMeasuredLine andMeasure3 = lazyGridMeasuredLineProvider.getAndMeasure(i25);
                arrayDeque.add(0, andMeasure3);
                mainAxisSizeWithSpacings2 += andMeasure3.getMainAxisSizeWithSpacings();
                i20 = i25;
            }
            i9 = i19 + i24;
            if (mainAxisSizeWithSpacings2 < 0) {
                i9 += mainAxisSizeWithSpacings2;
                mainAxisSizeWithSpacings3 += mainAxisSizeWithSpacings2;
                mainAxisSizeWithSpacings2 = 0;
            }
        } else {
            i9 = i19;
        }
        float f2 = (MathKt.getSign(Math.round(f)) != MathKt.getSign(i9) || Math.abs(Math.round(f)) < Math.abs(i9)) ? f : i9;
        if (mainAxisSizeWithSpacings2 < 0) {
            throw new IllegalArgumentException("negative initial offset".toString());
        }
        int i26 = -mainAxisSizeWithSpacings2;
        LazyGridMeasuredLine lazyGridMeasuredLine = (LazyGridMeasuredLine) arrayDeque.first();
        LazyGridMeasuredItem lazyGridMeasuredItem2 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(lazyGridMeasuredLine.getItems());
        int index = lazyGridMeasuredItem2 != null ? lazyGridMeasuredItem2.getIndex() : 0;
        LazyGridMeasuredLine lazyGridMeasuredLine2 = (LazyGridMeasuredLine) arrayDeque.lastOrNull();
        int index2 = (lazyGridMeasuredLine2 == null || (items = lazyGridMeasuredLine2.getItems()) == null || (lazyGridMeasuredItem = (LazyGridMeasuredItem) ArraysKt.lastOrNull(items)) == null) ? 0 : lazyGridMeasuredItem.getIndex();
        int size = list3.size();
        ArrayList arrayListEmptyList = null;
        List listEmptyList = null;
        int i27 = 0;
        while (i27 < size) {
            int i28 = size;
            int iIntValue = list3.get(i27).intValue();
            if (iIntValue < 0 || iIntValue >= index) {
                i10 = i27;
            } else {
                i10 = i27;
                int iSpanOf = lazyGridMeasuredLineProvider.spanOf(iIntValue);
                LazyGridMeasuredItem lazyGridMeasuredItemMo1079getAndMeasurehBUhpc = lazyGridMeasuredItemProvider.mo1079getAndMeasurehBUhpc(iIntValue, 0, iSpanOf, lazyGridMeasuredLineProvider.m1110childConstraintsJhjzzOo$foundation_release(0, iSpanOf));
                ArrayList arrayList = listEmptyList == null ? new ArrayList() : listEmptyList;
                arrayList.add(lazyGridMeasuredItemMo1079getAndMeasurehBUhpc);
                listEmptyList = arrayList;
            }
            i27 = i10 + 1;
            size = i28;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list4 = listEmptyList;
        int size2 = list3.size();
        int i29 = 0;
        while (i29 < size2) {
            int i30 = i29;
            int iIntValue2 = list3.get(i29).intValue();
            if (index2 + 1 <= iIntValue2 && iIntValue2 < i13) {
                int iSpanOf2 = lazyGridMeasuredLineProvider.spanOf(iIntValue2);
                LazyGridMeasuredItem lazyGridMeasuredItemMo1079getAndMeasurehBUhpc2 = lazyGridMeasuredItemProvider.mo1079getAndMeasurehBUhpc(iIntValue2, 0, iSpanOf2, lazyGridMeasuredLineProvider.m1110childConstraintsJhjzzOo$foundation_release(0, iSpanOf2));
                if (arrayListEmptyList == null) {
                    arrayListEmptyList = new ArrayList();
                }
                List list5 = arrayListEmptyList;
                list5.add(lazyGridMeasuredItemMo1079getAndMeasurehBUhpc2);
                arrayListEmptyList = list5;
            }
            i29 = i30 + 1;
            i13 = i;
            list3 = list;
        }
        if (arrayListEmptyList == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        }
        if (i3 > 0 || i5 < 0) {
            int size3 = arrayDeque.size();
            int i31 = 0;
            while (i31 < size3) {
                int mainAxisSizeWithSpacings5 = ((LazyGridMeasuredLine) arrayDeque.get(i31)).getMainAxisSizeWithSpacings();
                if (mainAxisSizeWithSpacings2 == 0 || mainAxisSizeWithSpacings5 > mainAxisSizeWithSpacings2 || i31 == CollectionsKt.getLastIndex(arrayDeque)) {
                    break;
                }
                mainAxisSizeWithSpacings2 -= mainAxisSizeWithSpacings5;
                i31++;
                lazyGridMeasuredLine = (LazyGridMeasuredLine) arrayDeque.get(i31);
            }
        }
        LazyGridMeasuredLine lazyGridMeasuredLine3 = lazyGridMeasuredLine;
        int i32 = mainAxisSizeWithSpacings2;
        if (z) {
            iM6997constrainWidthK40F9xA = Constraints.m6980getMaxWidthimpl(j);
        } else {
            iM6997constrainWidthK40F9xA = ConstraintsKt.m6997constrainWidthK40F9xA(j, mainAxisSizeWithSpacings3);
        }
        int i33 = iM6997constrainWidthK40F9xA;
        if (z) {
            iM6979getMaxHeightimpl = ConstraintsKt.m6996constrainHeightK40F9xA(j, mainAxisSizeWithSpacings3);
        } else {
            iM6979getMaxHeightimpl = Constraints.m6979getMaxHeightimpl(j);
        }
        int iM6996constrainHeightK40F9xA = iM6979getMaxHeightimpl;
        int i34 = mainAxisSizeWithSpacings3;
        int i35 = index;
        int i36 = index2;
        List list6 = arrayListEmptyList;
        final List<LazyGridMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(arrayDeque, list4, list6, i33, iM6996constrainHeightK40F9xA, i34, i2, i26, z, vertical, horizontal, z2, density);
        lazyLayoutItemAnimator.onMeasured((int) f2, i33, iM6996constrainHeightK40F9xA, listCalculateItemsOffsets, lazyGridMeasuredItemProvider.getKeyIndexMap(), lazyGridMeasuredItemProvider, z, false, i8, false, i32, i34, coroutineScope, graphicsContext);
        long jM1130getMinSizeToFitDisappearingItemsYbymL2g2 = lazyLayoutItemAnimator.m1130getMinSizeToFitDisappearingItemsYbymL2g();
        if (IntSize.m7195equalsimpl0(jM1130getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.INSTANCE.m7202getZeroYbymL2g())) {
            iM6997constrainWidthK40F9xA2 = i33;
        } else {
            int i37 = z ? iM6996constrainHeightK40F9xA : i33;
            iM6997constrainWidthK40F9xA2 = ConstraintsKt.m6997constrainWidthK40F9xA(j, Math.max(i33, IntSize.m7197getWidthimpl(jM1130getMinSizeToFitDisappearingItemsYbymL2g2)));
            iM6996constrainHeightK40F9xA = ConstraintsKt.m6996constrainHeightK40F9xA(j, Math.max(iM6996constrainHeightK40F9xA, IntSize.m7196getHeightimpl(jM1130getMinSizeToFitDisappearingItemsYbymL2g2)));
            int i38 = z ? iM6996constrainHeightK40F9xA : iM6997constrainWidthK40F9xA2;
            if (i38 != i37) {
                int size4 = listCalculateItemsOffsets.size();
                for (int i39 = 0; i39 < size4; i39++) {
                    listCalculateItemsOffsets.get(i39).updateMainAxisLayoutSize(i38);
                }
            }
        }
        boolean z4 = i36 != i + (-1) || i34 > i2;
        MeasureResult measureResultInvoke = function3.invoke(Integer.valueOf(iM6997constrainWidthK40F9xA2), Integer.valueOf(iM6996constrainHeightK40F9xA), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                List<LazyGridMeasuredItem> list7 = listCalculateItemsOffsets;
                int size5 = list7.size();
                for (int i40 = 0; i40 < size5; i40++) {
                    list7.get(i40).place(placementScope);
                }
                ObservableScopeInvalidator.m1139attachToScopeimpl(mutableState);
            }
        });
        if (list4.isEmpty() && list6.isEmpty()) {
            list2 = listCalculateItemsOffsets;
        } else {
            ArrayList arrayList2 = new ArrayList(listCalculateItemsOffsets.size());
            int size5 = listCalculateItemsOffsets.size();
            int i40 = 0;
            while (i40 < size5) {
                LazyGridMeasuredItem lazyGridMeasuredItem3 = listCalculateItemsOffsets.get(i40);
                int index3 = lazyGridMeasuredItem3.getIndex();
                int i41 = i35;
                if (i41 <= index3 && index3 <= i36) {
                    arrayList2.add(lazyGridMeasuredItem3);
                }
                i40++;
                i35 = i41;
            }
            list2 = arrayList2;
        }
        return new LazyGridMeasureResult(lazyGridMeasuredLine3, i32, z4, f2, measureResultInvoke, z3, coroutineScope, density, i8, function1, list2, i15, i18, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
    }

    private static final List<LazyGridMeasuredItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3 && i5 != 0) {
            throw new IllegalStateException("non-zero firstLineScrollOffset".toString());
        }
        int size = list.size();
        int length = 0;
        for (int i7 = 0; i7 < size; i7++) {
            length += list.get(i7).getItems().length;
        }
        ArrayList arrayList = new ArrayList(length);
        if (z3) {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("no items".toString());
            }
            int size2 = list.size();
            int[] iArr = new int[size2];
            for (int i8 = 0; i8 < size2; i8++) {
                iArr[i8] = list.get(calculateItemsOffsets$reverseAware(i8, z2, size2)).getMainAxisSize();
            }
            int[] iArr2 = new int[size2];
            for (int i9 = 0; i9 < size2; i9++) {
                iArr2[i9] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalArrangement".toString());
                }
                vertical.arrange(density, i6, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalArrangement".toString());
                }
                horizontal.arrange(density, i6, iArr, LayoutDirection.Ltr, iArr2);
            }
            IntRange indices = ArraysKt.getIndices(iArr2);
            if (z2) {
                indices = RangesKt.reversed(indices);
            }
            int first = indices.getFirst();
            int last = indices.getLast();
            int step = indices.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (true) {
                    int mainAxisSize = iArr2[first];
                    LazyGridMeasuredLine lazyGridMeasuredLine = list.get(calculateItemsOffsets$reverseAware(first, z2, size2));
                    if (z2) {
                        mainAxisSize = (i6 - mainAxisSize) - lazyGridMeasuredLine.getMainAxisSize();
                    }
                    addAllFromArray(arrayList, lazyGridMeasuredLine.position(mainAxisSize, i, i2));
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size() - 1;
            if (size3 >= 0) {
                int mainAxisSizeWithSpacings = i5;
                while (true) {
                    int i10 = size3 - 1;
                    LazyGridMeasuredItem lazyGridMeasuredItem = list2.get(size3);
                    mainAxisSizeWithSpacings -= lazyGridMeasuredItem.getMainAxisSizeWithSpacings();
                    lazyGridMeasuredItem.position(mainAxisSizeWithSpacings, 0, i, i2);
                    arrayList.add(lazyGridMeasuredItem);
                    if (i10 < 0) {
                        break;
                    }
                    size3 = i10;
                }
            }
            int size4 = list.size();
            int mainAxisSizeWithSpacings2 = i5;
            for (int i11 = 0; i11 < size4; i11++) {
                LazyGridMeasuredLine lazyGridMeasuredLine2 = list.get(i11);
                addAllFromArray(arrayList, lazyGridMeasuredLine2.position(mainAxisSizeWithSpacings2, i, i2));
                mainAxisSizeWithSpacings2 += lazyGridMeasuredLine2.getMainAxisSizeWithSpacings();
            }
            int size5 = list3.size();
            for (int i12 = 0; i12 < size5; i12++) {
                LazyGridMeasuredItem lazyGridMeasuredItem2 = list3.get(i12);
                lazyGridMeasuredItem2.position(mainAxisSizeWithSpacings2, 0, i, i2);
                arrayList.add(lazyGridMeasuredItem2);
                mainAxisSizeWithSpacings2 += lazyGridMeasuredItem2.getMainAxisSizeWithSpacings();
            }
        }
        return arrayList;
    }

    private static final <T> void addAllFromArray(List<T> list, T[] tArr) {
        for (T t : tArr) {
            list.add(t);
        }
    }

    private static final List<LazyGridMeasuredItem> calculateExtraItems(List<Integer> list, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, Function1<? super Integer, Boolean> function1) {
        LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider2;
        int size = list.size();
        ArrayList arrayList = null;
        int i = 0;
        while (i < size) {
            int iIntValue = list.get(i).intValue();
            if (function1.invoke(Integer.valueOf(iIntValue)).booleanValue()) {
                int iSpanOf = lazyGridMeasuredLineProvider.spanOf(iIntValue);
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
                LazyGridMeasuredItem lazyGridMeasuredItemMo1079getAndMeasurehBUhpc = lazyGridMeasuredItemProvider2.mo1079getAndMeasurehBUhpc(iIntValue, 0, iSpanOf, lazyGridMeasuredLineProvider.m1110childConstraintsJhjzzOo$foundation_release(0, iSpanOf));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyGridMeasuredItemMo1079getAndMeasurehBUhpc);
            } else {
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
            }
            i++;
            lazyGridMeasuredItemProvider = lazyGridMeasuredItemProvider2;
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }
}
