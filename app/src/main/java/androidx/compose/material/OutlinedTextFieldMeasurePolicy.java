package androidx.compose.material;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
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
import androidx.compose.ui.util.MathHelpersKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OutlinedTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B1\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ<\u0010\r\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000e2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u0015H\u0002J<\u0010\u0016\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000e2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u0015H\u0002J\"\u0010\u0018\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\"\u0010\u0019\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J,\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0016ø\u0001\u0000¢\u0006\u0004\b \u0010!J\"\u0010\"\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\"\u0010#\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000eH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/material/OutlinedTextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "onLabelMeasured", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Size;", "", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Lkotlin/jvm/functions/Function1;ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicWidth", "height", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class OutlinedTextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final Function1<Size, Unit> onLabelMeasured;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    /* JADX WARN: Multi-variable type inference failed */
    public OutlinedTextFieldMeasurePolicy(Function1<? super Size, Unit> function1, boolean z, float f, PaddingValues paddingValues) {
        this.onLabelMeasured = function1;
        this.singleLine = z;
        this.animationProgress = f;
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo322measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, long j) {
        Measurable measurable;
        Measurable measurable2;
        Measurable measurable3;
        long jM4405getZeroNHjbRc;
        Measurable measurable4;
        final OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy = this;
        MeasureScope measureScope2 = measureScope;
        int i = measureScope2.mo651roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.getBottom());
        long jM6971copyZbe2FdA$default = Constraints.m6971copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        int size = list.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                measurable = null;
                break;
            }
            measurable = list.get(i2);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "Leading")) {
                break;
            }
            i2++;
        }
        Measurable measurable5 = measurable;
        Placeable placeableMo5903measureBRTryo0 = measurable5 != null ? measurable5.mo5903measureBRTryo0(jM6971copyZbe2FdA$default) : null;
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo0);
        int size2 = list.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size2) {
                measurable2 = null;
                break;
            }
            measurable2 = list.get(i3);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "Trailing")) {
                break;
            }
            i3++;
        }
        Measurable measurable6 = measurable2;
        Placeable placeableMo5903measureBRTryo02 = measurable6 != null ? measurable6.mo5903measureBRTryo0(ConstraintsKt.m7000offsetNN6EwU$default(jM6971copyZbe2FdA$default, -iWidthOrZero, 0, 2, null)) : null;
        int iWidthOrZero2 = iWidthOrZero + TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo02);
        int i4 = measureScope2.mo651roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.mo916calculateLeftPaddingu2uoSUM(measureScope2.getLayoutDirection())) + measureScope2.mo651roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.mo917calculateRightPaddingu2uoSUM(measureScope2.getLayoutDirection()));
        int i5 = -iWidthOrZero2;
        int i6 = -i;
        long jM6999offsetNN6EwU = ConstraintsKt.m6999offsetNN6EwU(jM6971copyZbe2FdA$default, MathHelpersKt.lerp(i5 - i4, -i4, outlinedTextFieldMeasurePolicy.animationProgress), i6);
        int size3 = list.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size3) {
                measurable3 = null;
                break;
            }
            measurable3 = list.get(i7);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "Label")) {
                break;
            }
            i7++;
        }
        Measurable measurable7 = measurable3;
        final Placeable placeableMo5903measureBRTryo03 = measurable7 != null ? measurable7.mo5903measureBRTryo0(jM6999offsetNN6EwU) : null;
        if (placeableMo5903measureBRTryo03 != null) {
            jM4405getZeroNHjbRc = SizeKt.Size(placeableMo5903measureBRTryo03.getWidth(), placeableMo5903measureBRTryo03.getHeight());
        } else {
            jM4405getZeroNHjbRc = Size.INSTANCE.m4405getZeroNHjbRc();
        }
        outlinedTextFieldMeasurePolicy.onLabelMeasured.invoke(Size.m4384boximpl(jM4405getZeroNHjbRc));
        long j2 = j;
        long jM6971copyZbe2FdA$default2 = Constraints.m6971copyZbe2FdA$default(ConstraintsKt.m6999offsetNN6EwU(j2, i5, i6 - Math.max(TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo03) / 2, measureScope2.mo651roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.getTop()))), 0, 0, 0, 0, 11, null);
        int size4 = list.size();
        int i8 = 0;
        while (i8 < size4) {
            Measurable measurable8 = list.get(i8);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable8), "TextField")) {
                Placeable placeableMo5903measureBRTryo04 = measurable8.mo5903measureBRTryo0(jM6971copyZbe2FdA$default2);
                long jM6971copyZbe2FdA$default3 = Constraints.m6971copyZbe2FdA$default(jM6971copyZbe2FdA$default2, 0, 0, 0, 0, 14, null);
                int size5 = list.size();
                int i9 = 0;
                while (true) {
                    if (i9 >= size5) {
                        measurable4 = null;
                        break;
                    }
                    measurable4 = list.get(i9);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), "Hint")) {
                        break;
                    }
                    i9++;
                }
                Measurable measurable9 = measurable4;
                final Placeable placeableMo5903measureBRTryo05 = measurable9 != null ? measurable9.mo5903measureBRTryo0(jM6971copyZbe2FdA$default3) : null;
                int iM1898calculateWidthO3s9Psw = OutlinedTextFieldKt.m1898calculateWidthO3s9Psw(TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo0), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo02), placeableMo5903measureBRTryo04.getWidth(), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo03), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo05), outlinedTextFieldMeasurePolicy.animationProgress, j2, measureScope2.getDensity(), outlinedTextFieldMeasurePolicy.paddingValues);
                int iM1897calculateHeightO3s9Psw = OutlinedTextFieldKt.m1897calculateHeightO3s9Psw(TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo0), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo02), placeableMo5903measureBRTryo04.getHeight(), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo03), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo05), outlinedTextFieldMeasurePolicy.animationProgress, j, measureScope.getDensity(), outlinedTextFieldMeasurePolicy.paddingValues);
                int size6 = list.size();
                int i10 = 0;
                while (i10 < size6) {
                    Measurable measurable10 = list.get(i10);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable10), OutlinedTextFieldKt.BorderId)) {
                        final Placeable placeableMo5903measureBRTryo06 = measurable10.mo5903measureBRTryo0(ConstraintsKt.Constraints(iM1898calculateWidthO3s9Psw != Integer.MAX_VALUE ? iM1898calculateWidthO3s9Psw : 0, iM1898calculateWidthO3s9Psw, iM1897calculateHeightO3s9Psw != Integer.MAX_VALUE ? iM1897calculateHeightO3s9Psw : 0, iM1897calculateHeightO3s9Psw));
                        final int i11 = iM1898calculateWidthO3s9Psw;
                        final Placeable placeable = placeableMo5903measureBRTryo02;
                        final Placeable placeable2 = placeableMo5903measureBRTryo04;
                        final int i12 = iM1897calculateHeightO3s9Psw;
                        final Placeable placeable3 = placeableMo5903measureBRTryo0;
                        return MeasureScope.layout$default(measureScope, i11, i12, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.OutlinedTextFieldMeasurePolicy$measure$1
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
                                OutlinedTextFieldKt.place(placementScope, i12, i11, placeable3, placeable, placeable2, placeableMo5903measureBRTryo03, placeableMo5903measureBRTryo05, placeableMo5903measureBRTryo06, outlinedTextFieldMeasurePolicy.animationProgress, outlinedTextFieldMeasurePolicy.singleLine, measureScope.getDensity(), measureScope.getLayoutDirection(), outlinedTextFieldMeasurePolicy.paddingValues);
                            }
                        }, 4, null);
                    }
                    i10++;
                    placeableMo5903measureBRTryo04 = placeableMo5903measureBRTryo04;
                    iM1898calculateWidthO3s9Psw = iM1898calculateWidthO3s9Psw;
                    placeableMo5903measureBRTryo0 = placeableMo5903measureBRTryo0;
                    iM1897calculateHeightO3s9Psw = iM1897calculateHeightO3s9Psw;
                    outlinedTextFieldMeasurePolicy = this;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i8++;
            outlinedTextFieldMeasurePolicy = this;
            measureScope2 = measureScope;
            j2 = j;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.OutlinedTextFieldMeasurePolicy.maxIntrinsicHeight.1
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
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.OutlinedTextFieldMeasurePolicy.minIntrinsicHeight.1
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
        return intrinsicWidth(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.OutlinedTextFieldMeasurePolicy.maxIntrinsicWidth.1
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
        return intrinsicWidth(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.OutlinedTextFieldMeasurePolicy.minIntrinsicWidth.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicWidth(i2));
            }
        });
    }

    private final int intrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        IntrinsicMeasurable intrinsicMeasurable;
        IntrinsicMeasurable intrinsicMeasurable2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            IntrinsicMeasurable intrinsicMeasurable5 = list.get(i2);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), "TextField")) {
                int iIntValue = function2.invoke(intrinsicMeasurable5, Integer.valueOf(i)).intValue();
                int size2 = list.size();
                int i3 = 0;
                while (true) {
                    intrinsicMeasurable = null;
                    if (i3 >= size2) {
                        intrinsicMeasurable2 = null;
                        break;
                    }
                    intrinsicMeasurable2 = list.get(i3);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), "Label")) {
                        break;
                    }
                    i3++;
                }
                IntrinsicMeasurable intrinsicMeasurable6 = intrinsicMeasurable2;
                int iIntValue2 = intrinsicMeasurable6 != null ? function2.invoke(intrinsicMeasurable6, Integer.valueOf(i)).intValue() : 0;
                int size3 = list.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size3) {
                        intrinsicMeasurable3 = null;
                        break;
                    }
                    intrinsicMeasurable3 = list.get(i4);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), "Trailing")) {
                        break;
                    }
                    i4++;
                }
                IntrinsicMeasurable intrinsicMeasurable7 = intrinsicMeasurable3;
                int iIntValue3 = intrinsicMeasurable7 != null ? function2.invoke(intrinsicMeasurable7, Integer.valueOf(i)).intValue() : 0;
                int size4 = list.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size4) {
                        intrinsicMeasurable4 = null;
                        break;
                    }
                    intrinsicMeasurable4 = list.get(i5);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), "Leading")) {
                        break;
                    }
                    i5++;
                }
                IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable4;
                int iIntValue4 = intrinsicMeasurable8 != null ? function2.invoke(intrinsicMeasurable8, Integer.valueOf(i)).intValue() : 0;
                int size5 = list.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size5) {
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable9 = list.get(i6);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable9), "Hint")) {
                        intrinsicMeasurable = intrinsicMeasurable9;
                        break;
                    }
                    i6++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable;
                return OutlinedTextFieldKt.m1898calculateWidthO3s9Psw(iIntValue4, iIntValue3, iIntValue, iIntValue2, intrinsicMeasurable10 != null ? function2.invoke(intrinsicMeasurable10, Integer.valueOf(i)).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        IntrinsicMeasurable intrinsicMeasurable;
        IntrinsicMeasurable intrinsicMeasurable2;
        int iSubstractConstraintSafely;
        int iIntValue;
        IntrinsicMeasurable intrinsicMeasurable3;
        int iIntValue2;
        IntrinsicMeasurable intrinsicMeasurable4;
        int size = list.size();
        int i2 = 0;
        while (true) {
            intrinsicMeasurable = null;
            if (i2 >= size) {
                intrinsicMeasurable2 = null;
                break;
            }
            intrinsicMeasurable2 = list.get(i2);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), "Leading")) {
                break;
            }
            i2++;
        }
        IntrinsicMeasurable intrinsicMeasurable5 = intrinsicMeasurable2;
        if (intrinsicMeasurable5 != null) {
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(i, intrinsicMeasurable5.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = function2.invoke(intrinsicMeasurable5, Integer.valueOf(i)).intValue();
        } else {
            iSubstractConstraintSafely = i;
            iIntValue = 0;
        }
        int size2 = list.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size2) {
                intrinsicMeasurable3 = null;
                break;
            }
            intrinsicMeasurable3 = list.get(i3);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), "Trailing")) {
                break;
            }
            i3++;
        }
        IntrinsicMeasurable intrinsicMeasurable6 = intrinsicMeasurable3;
        if (intrinsicMeasurable6 != null) {
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable6.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue2 = function2.invoke(intrinsicMeasurable6, Integer.valueOf(i)).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size3) {
                intrinsicMeasurable4 = null;
                break;
            }
            intrinsicMeasurable4 = list.get(i4);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), "Label")) {
                break;
            }
            i4++;
        }
        IntrinsicMeasurable intrinsicMeasurable7 = intrinsicMeasurable4;
        int iIntValue3 = intrinsicMeasurable7 != null ? function2.invoke(intrinsicMeasurable7, Integer.valueOf(MathHelpersKt.lerp(iSubstractConstraintSafely, i, this.animationProgress))).intValue() : 0;
        int size4 = list.size();
        for (int i5 = 0; i5 < size4; i5++) {
            IntrinsicMeasurable intrinsicMeasurable8 = list.get(i5);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable8), "TextField")) {
                int iIntValue4 = function2.invoke(intrinsicMeasurable8, Integer.valueOf(iSubstractConstraintSafely)).intValue();
                int size5 = list.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size5) {
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable9 = list.get(i6);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable9), "Hint")) {
                        intrinsicMeasurable = intrinsicMeasurable9;
                        break;
                    }
                    i6++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable;
                return OutlinedTextFieldKt.m1897calculateHeightO3s9Psw(iIntValue, iIntValue2, iIntValue4, iIntValue3, intrinsicMeasurable10 != null ? function2.invoke(intrinsicMeasurable10, Integer.valueOf(iSubstractConstraintSafely)).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
