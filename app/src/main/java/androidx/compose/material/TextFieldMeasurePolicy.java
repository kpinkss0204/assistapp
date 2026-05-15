package androidx.compose.material;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ8\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J<\u0010\u0011\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J\"\u0010\u0014\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u0015\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016J,\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\"\u0010\u001e\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u001f\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"Landroidx/compose/material/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicWidth", "", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    public TextFieldMeasurePolicy(boolean z, float f, PaddingValues paddingValues) {
        this.singleLine = z;
        this.animationProgress = f;
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo322measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        Measurable measurable;
        Measurable measurable2;
        Measurable measurable3;
        int height;
        Measurable measurable4;
        final TextFieldMeasurePolicy textFieldMeasurePolicy = this;
        final MeasureScope measureScope2 = measureScope;
        List<? extends Measurable> list2 = list;
        final int i = measureScope2.mo651roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getTop());
        int i2 = measureScope2.mo651roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getBottom());
        final int i3 = measureScope2.mo651roundToPx0680j_4(TextFieldKt.getTextFieldTopPadding());
        long jM6971copyZbe2FdA$default = Constraints.m6971copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        int size = list2.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                measurable = null;
                break;
            }
            measurable = list2.get(i4);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "Leading")) {
                break;
            }
            i4++;
        }
        Measurable measurable5 = measurable;
        Placeable placeableMo5903measureBRTryo0 = measurable5 != null ? measurable5.mo5903measureBRTryo0(jM6971copyZbe2FdA$default) : null;
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo0);
        int size2 = list2.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size2) {
                measurable2 = null;
                break;
            }
            measurable2 = list2.get(i5);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "Trailing")) {
                break;
            }
            i5++;
        }
        Measurable measurable6 = measurable2;
        Placeable placeableMo5903measureBRTryo02 = measurable6 != null ? measurable6.mo5903measureBRTryo0(ConstraintsKt.m7000offsetNN6EwU$default(jM6971copyZbe2FdA$default, -iWidthOrZero, 0, 2, null)) : null;
        int i6 = -i2;
        int i7 = -(iWidthOrZero + TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo02));
        long jM6999offsetNN6EwU = ConstraintsKt.m6999offsetNN6EwU(jM6971copyZbe2FdA$default, i7, i6);
        int size3 = list2.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size3) {
                measurable3 = null;
                break;
            }
            measurable3 = list2.get(i8);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "Label")) {
                break;
            }
            i8++;
        }
        Measurable measurable7 = measurable3;
        Placeable placeableMo5903measureBRTryo03 = measurable7 != null ? measurable7.mo5903measureBRTryo0(jM6999offsetNN6EwU) : null;
        if (placeableMo5903measureBRTryo03 != null) {
            height = placeableMo5903measureBRTryo03.get(AlignmentLineKt.getLastBaseline());
            if (height == Integer.MIN_VALUE) {
                height = placeableMo5903measureBRTryo03.getHeight();
            }
        } else {
            height = 0;
        }
        final int iMax = Math.max(height, i);
        long jM6999offsetNN6EwU2 = ConstraintsKt.m6999offsetNN6EwU(Constraints.m6971copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null), i7, placeableMo5903measureBRTryo03 != null ? (i6 - i3) - iMax : (-i) - i2);
        int size4 = list2.size();
        int i9 = 0;
        while (i9 < size4) {
            Measurable measurable8 = list2.get(i9);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable8), "TextField")) {
                final Placeable placeableMo5903measureBRTryo04 = measurable8.mo5903measureBRTryo0(jM6999offsetNN6EwU2);
                long jM6971copyZbe2FdA$default2 = Constraints.m6971copyZbe2FdA$default(jM6999offsetNN6EwU2, 0, 0, 0, 0, 14, null);
                int size5 = list2.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size5) {
                        measurable4 = null;
                        break;
                    }
                    measurable4 = list2.get(i10);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), "Hint")) {
                        break;
                    }
                    i10++;
                    list2 = list;
                }
                Measurable measurable9 = measurable4;
                final Placeable placeableMo5903measureBRTryo05 = measurable9 != null ? measurable9.mo5903measureBRTryo0(jM6971copyZbe2FdA$default2) : null;
                final int iM2017calculateWidthVsPV1Ek = TextFieldKt.m2017calculateWidthVsPV1Ek(TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo0), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo02), placeableMo5903measureBRTryo04.getWidth(), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo03), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo05), j);
                final int iM2016calculateHeightO3s9Psw = TextFieldKt.m2016calculateHeightO3s9Psw(placeableMo5903measureBRTryo04.getHeight(), placeableMo5903measureBRTryo03 != null, iMax, TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo0), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo02), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo05), j, measureScope2.getDensity(), textFieldMeasurePolicy.paddingValues);
                final Placeable placeable = placeableMo5903measureBRTryo02;
                final int i11 = height;
                final Placeable placeable2 = placeableMo5903measureBRTryo03;
                final Placeable placeable3 = placeableMo5903measureBRTryo0;
                return MeasureScope.layout$default(measureScope, iM2017calculateWidthVsPV1Ek, iM2016calculateHeightO3s9Psw, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$measure$1
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
                        if (placeable2 == null) {
                            TextFieldKt.placeWithoutLabel(placementScope, iM2017calculateWidthVsPV1Ek, iM2016calculateHeightO3s9Psw, placeableMo5903measureBRTryo04, placeableMo5903measureBRTryo05, placeable3, placeable, textFieldMeasurePolicy.singleLine, measureScope2.getDensity(), textFieldMeasurePolicy.paddingValues);
                        } else {
                            TextFieldKt.placeWithLabel(placementScope, iM2017calculateWidthVsPV1Ek, iM2016calculateHeightO3s9Psw, placeableMo5903measureBRTryo04, placeable2, placeableMo5903measureBRTryo05, placeable3, placeable, textFieldMeasurePolicy.singleLine, RangesKt.coerceAtLeast(i - i11, 0), iMax + i3, textFieldMeasurePolicy.animationProgress, measureScope2.getDensity());
                        }
                    }
                }, 4, null);
            }
            i9++;
            textFieldMeasurePolicy = this;
            measureScope2 = measureScope;
            list2 = list;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy.maxIntrinsicHeight.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicHeight(i2));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy.minIntrinsicHeight.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicHeight(i2));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicWidth(list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy.maxIntrinsicWidth.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicWidth(i2));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicWidth(list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy.minIntrinsicWidth.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicWidth(i2));
            }
        });
    }

    private final int intrinsicWidth(List<? extends IntrinsicMeasurable> measurables, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> intrinsicMeasurer) {
        IntrinsicMeasurable intrinsicMeasurable;
        IntrinsicMeasurable intrinsicMeasurable2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        int size = measurables.size();
        for (int i = 0; i < size; i++) {
            IntrinsicMeasurable intrinsicMeasurable5 = measurables.get(i);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), "TextField")) {
                int iIntValue = intrinsicMeasurer.invoke(intrinsicMeasurable5, Integer.valueOf(height)).intValue();
                int size2 = measurables.size();
                int i2 = 0;
                while (true) {
                    intrinsicMeasurable = null;
                    if (i2 >= size2) {
                        intrinsicMeasurable2 = null;
                        break;
                    }
                    intrinsicMeasurable2 = measurables.get(i2);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), "Label")) {
                        break;
                    }
                    i2++;
                }
                IntrinsicMeasurable intrinsicMeasurable6 = intrinsicMeasurable2;
                int iIntValue2 = intrinsicMeasurable6 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable6, Integer.valueOf(height)).intValue() : 0;
                int size3 = measurables.size();
                int i3 = 0;
                while (true) {
                    if (i3 >= size3) {
                        intrinsicMeasurable3 = null;
                        break;
                    }
                    intrinsicMeasurable3 = measurables.get(i3);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), "Trailing")) {
                        break;
                    }
                    i3++;
                }
                IntrinsicMeasurable intrinsicMeasurable7 = intrinsicMeasurable3;
                int iIntValue3 = intrinsicMeasurable7 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable7, Integer.valueOf(height)).intValue() : 0;
                int size4 = measurables.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size4) {
                        intrinsicMeasurable4 = null;
                        break;
                    }
                    intrinsicMeasurable4 = measurables.get(i4);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), "Leading")) {
                        break;
                    }
                    i4++;
                }
                IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable4;
                int iIntValue4 = intrinsicMeasurable8 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable8, Integer.valueOf(height)).intValue() : 0;
                int size5 = measurables.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size5) {
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable9 = measurables.get(i5);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable9), "Hint")) {
                        intrinsicMeasurable = intrinsicMeasurable9;
                        break;
                    }
                    i5++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable;
                return TextFieldKt.m2017calculateWidthVsPV1Ek(iIntValue4, iIntValue3, iIntValue, iIntValue2, intrinsicMeasurable10 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable10, Integer.valueOf(height)).intValue() : 0, TextFieldImplKt.getZeroConstraints());
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        IntrinsicMeasurable intrinsicMeasurable;
        IntrinsicMeasurable intrinsicMeasurable2;
        int i2;
        int iIntValue;
        int iSubstractConstraintSafely;
        IntrinsicMeasurable intrinsicMeasurable3;
        int iIntValue2;
        IntrinsicMeasurable intrinsicMeasurable4;
        int size = list.size();
        int i3 = 0;
        while (true) {
            intrinsicMeasurable = null;
            if (i3 >= size) {
                intrinsicMeasurable2 = null;
                break;
            }
            intrinsicMeasurable2 = list.get(i3);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), "Leading")) {
                break;
            }
            i3++;
        }
        IntrinsicMeasurable intrinsicMeasurable5 = intrinsicMeasurable2;
        if (intrinsicMeasurable5 != null) {
            i2 = i;
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(i2, intrinsicMeasurable5.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = function2.invoke(intrinsicMeasurable5, Integer.valueOf(i2)).intValue();
        } else {
            i2 = i;
            iIntValue = 0;
            iSubstractConstraintSafely = i2;
        }
        int size2 = list.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size2) {
                intrinsicMeasurable3 = null;
                break;
            }
            intrinsicMeasurable3 = list.get(i4);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), "Trailing")) {
                break;
            }
            i4++;
        }
        IntrinsicMeasurable intrinsicMeasurable6 = intrinsicMeasurable3;
        if (intrinsicMeasurable6 != null) {
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable6.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue2 = function2.invoke(intrinsicMeasurable6, Integer.valueOf(i2)).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size3) {
                intrinsicMeasurable4 = null;
                break;
            }
            intrinsicMeasurable4 = list.get(i5);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), "Label")) {
                break;
            }
            i5++;
        }
        IntrinsicMeasurable intrinsicMeasurable7 = intrinsicMeasurable4;
        int iIntValue3 = intrinsicMeasurable7 != null ? function2.invoke(intrinsicMeasurable7, Integer.valueOf(iSubstractConstraintSafely)).intValue() : 0;
        int size4 = list.size();
        for (int i6 = 0; i6 < size4; i6++) {
            IntrinsicMeasurable intrinsicMeasurable8 = list.get(i6);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable8), "TextField")) {
                int iIntValue4 = function2.invoke(intrinsicMeasurable8, Integer.valueOf(iSubstractConstraintSafely)).intValue();
                int size5 = list.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size5) {
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable9 = list.get(i7);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable9), "Hint")) {
                        intrinsicMeasurable = intrinsicMeasurable9;
                        break;
                    }
                    i7++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable;
                return TextFieldKt.m2016calculateHeightO3s9Psw(iIntValue4, iIntValue3 > 0, iIntValue3, iIntValue, iIntValue2, intrinsicMeasurable10 != null ? function2.invoke(intrinsicMeasurable10, Integer.valueOf(iSubstractConstraintSafely)).intValue() : 0, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
