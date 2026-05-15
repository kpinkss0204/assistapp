package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.TextFieldImplKt;
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
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B1\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ<\u0010\r\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000e2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u0015H\u0002J<\u0010\u0016\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000e2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u0015H\u0002J\"\u0010\u0018\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\"\u0010\u0019\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J,\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0016ø\u0001\u0000¢\u0006\u0004\b \u0010!J\"\u0010\"\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\"\u0010#\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000eH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "onLabelMeasured", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Size;", "", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Lkotlin/jvm/functions/Function1;ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicWidth", "height", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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
    public MeasureResult mo322measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        Measurable measurable;
        Measurable measurable2;
        Measurable measurable3;
        Measurable measurable4;
        Placeable placeable;
        Measurable measurable5;
        Measurable measurable6;
        Measurable measurable7;
        final OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy = this;
        final MeasureScope measureScope2 = measureScope;
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
        Measurable measurable8 = measurable;
        Placeable placeableMo5903measureBRTryo0 = measurable8 != null ? measurable8.mo5903measureBRTryo0(jM6971copyZbe2FdA$default) : null;
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo0);
        int iMax = Math.max(0, TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo0));
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
        Measurable measurable9 = measurable2;
        Placeable placeableMo5903measureBRTryo02 = measurable9 != null ? measurable9.mo5903measureBRTryo0(ConstraintsKt.m7000offsetNN6EwU$default(jM6971copyZbe2FdA$default, -iWidthOrZero, 0, 2, null)) : null;
        int iWidthOrZero2 = iWidthOrZero + TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo02);
        int iMax2 = Math.max(iMax, TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo02));
        int size3 = list.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size3) {
                measurable3 = null;
                break;
            }
            measurable3 = list.get(i4);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), TextFieldImplKt.PrefixId)) {
                break;
            }
            i4++;
        }
        Measurable measurable10 = measurable3;
        Placeable placeableMo5903measureBRTryo03 = measurable10 != null ? measurable10.mo5903measureBRTryo0(ConstraintsKt.m7000offsetNN6EwU$default(jM6971copyZbe2FdA$default, -iWidthOrZero2, 0, 2, null)) : null;
        int iWidthOrZero3 = iWidthOrZero2 + TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo03);
        int iMax3 = Math.max(iMax2, TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo03));
        int size4 = list.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size4) {
                measurable4 = null;
                break;
            }
            measurable4 = list.get(i5);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.SuffixId)) {
                break;
            }
            i5++;
        }
        Measurable measurable11 = measurable4;
        Placeable placeableMo5903measureBRTryo04 = measurable11 != null ? measurable11.mo5903measureBRTryo0(ConstraintsKt.m7000offsetNN6EwU$default(jM6971copyZbe2FdA$default, -iWidthOrZero3, 0, 2, null)) : null;
        int iWidthOrZero4 = iWidthOrZero3 + TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo04);
        int iMax4 = Math.max(iMax3, TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo04));
        int i6 = measureScope2.mo651roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.mo916calculateLeftPaddingu2uoSUM(measureScope2.getLayoutDirection())) + measureScope2.mo651roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.mo917calculateRightPaddingu2uoSUM(measureScope2.getLayoutDirection()));
        int i7 = -iWidthOrZero4;
        int i8 = -i;
        long jM6999offsetNN6EwU = ConstraintsKt.m6999offsetNN6EwU(jM6971copyZbe2FdA$default, MathHelpersKt.lerp(i7 - i6, -i6, outlinedTextFieldMeasurePolicy.animationProgress), i8);
        int size5 = list.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size5) {
                placeable = placeableMo5903measureBRTryo0;
                measurable5 = null;
                break;
            }
            measurable5 = list.get(i9);
            int i10 = i9;
            placeable = placeableMo5903measureBRTryo0;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), "Label")) {
                break;
            }
            i9 = i10 + 1;
            placeableMo5903measureBRTryo0 = placeable;
        }
        Measurable measurable12 = measurable5;
        final Placeable placeableMo5903measureBRTryo05 = measurable12 != null ? measurable12.mo5903measureBRTryo0(jM6999offsetNN6EwU) : null;
        outlinedTextFieldMeasurePolicy.onLabelMeasured.invoke(Size.m4384boximpl(placeableMo5903measureBRTryo05 != null ? SizeKt.Size(placeableMo5903measureBRTryo05.getWidth(), placeableMo5903measureBRTryo05.getHeight()) : Size.INSTANCE.m4405getZeroNHjbRc()));
        int size6 = list.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size6) {
                measurable6 = null;
                break;
            }
            measurable6 = list.get(i11);
            int i12 = size6;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable6), TextFieldImplKt.SupportingId)) {
                break;
            }
            i11++;
            size6 = i12;
        }
        Measurable measurable13 = measurable6;
        int iMinIntrinsicHeight = measurable13 != null ? measurable13.minIntrinsicHeight(Constraints.m6982getMinWidthimpl(j)) : 0;
        int iMax5 = Math.max(TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo05) / 2, measureScope2.mo651roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.getTop()));
        long jM6971copyZbe2FdA$default2 = Constraints.m6971copyZbe2FdA$default(ConstraintsKt.m6999offsetNN6EwU(j, i7, (i8 - iMax5) - iMinIntrinsicHeight), 0, 0, 0, 0, 11, null);
        int size7 = list.size();
        final Placeable placeable2 = placeableMo5903measureBRTryo03;
        int i13 = 0;
        while (i13 < size7) {
            int i14 = size7;
            Measurable measurable14 = list.get(i13);
            final Placeable placeable3 = placeableMo5903measureBRTryo04;
            final Placeable placeable4 = placeableMo5903measureBRTryo02;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable14), "TextField")) {
                final Placeable placeableMo5903measureBRTryo06 = measurable14.mo5903measureBRTryo0(jM6971copyZbe2FdA$default2);
                long jM6971copyZbe2FdA$default3 = Constraints.m6971copyZbe2FdA$default(jM6971copyZbe2FdA$default2, 0, 0, 0, 0, 14, null);
                int size8 = list.size();
                int i15 = 0;
                while (true) {
                    if (i15 >= size8) {
                        measurable7 = null;
                        break;
                    }
                    measurable7 = list.get(i15);
                    int i16 = size8;
                    int i17 = i15;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable7), "Hint")) {
                        break;
                    }
                    i15 = i17 + 1;
                    size8 = i16;
                }
                Measurable measurable15 = measurable7;
                final Placeable placeableMo5903measureBRTryo07 = measurable15 != null ? measurable15.mo5903measureBRTryo0(jM6971copyZbe2FdA$default3) : null;
                int iMax6 = Math.max(iMax4, Math.max(TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo06), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo07)) + iMax5 + i);
                int iM2655calculateWidthDHJA7U0 = OutlinedTextFieldKt.m2655calculateWidthDHJA7U0(TextFieldImplKt.widthOrZero(placeable), TextFieldImplKt.widthOrZero(placeable4), TextFieldImplKt.widthOrZero(placeable2), TextFieldImplKt.widthOrZero(placeable3), placeableMo5903measureBRTryo06.getWidth(), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo05), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo07), outlinedTextFieldMeasurePolicy.animationProgress, j, measureScope2.getDensity(), outlinedTextFieldMeasurePolicy.paddingValues);
                int i18 = iM2655calculateWidthDHJA7U0;
                final Placeable placeableMo5903measureBRTryo08 = measurable13 != null ? measurable13.mo5903measureBRTryo0(Constraints.m6971copyZbe2FdA$default(ConstraintsKt.m7000offsetNN6EwU$default(jM6971copyZbe2FdA$default, 0, -iMax6, 1, null), 0, iM2655calculateWidthDHJA7U0, 0, 0, 9, null)) : null;
                int iHeightOrZero = TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo08);
                final int iM2654calculateHeightmKXJcVc = OutlinedTextFieldKt.m2654calculateHeightmKXJcVc(TextFieldImplKt.heightOrZero(placeable), TextFieldImplKt.heightOrZero(placeable4), TextFieldImplKt.heightOrZero(placeable2), TextFieldImplKt.heightOrZero(placeable3), placeableMo5903measureBRTryo06.getHeight(), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo05), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo07), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo08), outlinedTextFieldMeasurePolicy.animationProgress, j, measureScope2.getDensity(), outlinedTextFieldMeasurePolicy.paddingValues);
                int i19 = iM2654calculateHeightmKXJcVc - iHeightOrZero;
                int size9 = list.size();
                int i20 = 0;
                while (i20 < size9) {
                    Measurable measurable16 = list.get(i20);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable16), TextFieldImplKt.ContainerId)) {
                        final Placeable placeableMo5903measureBRTryo09 = measurable16.mo5903measureBRTryo0(ConstraintsKt.Constraints(i18 != Integer.MAX_VALUE ? i18 : 0, i18, i19 != Integer.MAX_VALUE ? i19 : 0, i19));
                        final int i21 = i18;
                        final Placeable placeable5 = placeable;
                        return MeasureScope.layout$default(measureScope, i21, iM2654calculateHeightmKXJcVc, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$measure$1
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
                                OutlinedTextFieldKt.place(placementScope, iM2654calculateHeightmKXJcVc, i21, placeable5, placeable4, placeable2, placeable3, placeableMo5903measureBRTryo06, placeableMo5903measureBRTryo05, placeableMo5903measureBRTryo07, placeableMo5903measureBRTryo09, placeableMo5903measureBRTryo08, outlinedTextFieldMeasurePolicy.animationProgress, outlinedTextFieldMeasurePolicy.singleLine, measureScope2.getDensity(), measureScope2.getLayoutDirection(), outlinedTextFieldMeasurePolicy.paddingValues);
                            }
                        }, 4, null);
                    }
                    i20++;
                    outlinedTextFieldMeasurePolicy = this;
                    measureScope2 = measureScope;
                    i18 = i18;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i13++;
            outlinedTextFieldMeasurePolicy = this;
            measureScope2 = measureScope;
            placeableMo5903measureBRTryo02 = placeable4;
            size7 = i14;
            placeableMo5903measureBRTryo04 = placeable3;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy.maxIntrinsicHeight.1
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
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy.minIntrinsicHeight.1
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
        return intrinsicWidth(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy.maxIntrinsicWidth.1
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
        return intrinsicWidth(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy.minIntrinsicWidth.1
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
        IntrinsicMeasurable intrinsicMeasurable5;
        IntrinsicMeasurable intrinsicMeasurable6;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            IntrinsicMeasurable intrinsicMeasurable7 = list.get(i2);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable7), "TextField")) {
                int iIntValue = function2.invoke(intrinsicMeasurable7, Integer.valueOf(i)).intValue();
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
                IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable2;
                int iIntValue2 = intrinsicMeasurable8 != null ? function2.invoke(intrinsicMeasurable8, Integer.valueOf(i)).intValue() : 0;
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
                IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable3;
                int iIntValue3 = intrinsicMeasurable9 != null ? function2.invoke(intrinsicMeasurable9, Integer.valueOf(i)).intValue() : 0;
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
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable4;
                int iIntValue4 = intrinsicMeasurable10 != null ? function2.invoke(intrinsicMeasurable10, Integer.valueOf(i)).intValue() : 0;
                int size5 = list.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size5) {
                        intrinsicMeasurable5 = null;
                        break;
                    }
                    intrinsicMeasurable5 = list.get(i6);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    i6++;
                }
                IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable5;
                int iIntValue5 = intrinsicMeasurable11 != null ? function2.invoke(intrinsicMeasurable11, Integer.valueOf(i)).intValue() : 0;
                int size6 = list.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size6) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i7);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    i7++;
                }
                IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable6;
                int iIntValue6 = intrinsicMeasurable12 != null ? function2.invoke(intrinsicMeasurable12, Integer.valueOf(i)).intValue() : 0;
                int size7 = list.size();
                int i8 = 0;
                while (true) {
                    if (i8 >= size7) {
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable13 = list.get(i8);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), "Hint")) {
                        intrinsicMeasurable = intrinsicMeasurable13;
                        break;
                    }
                    i8++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable;
                return OutlinedTextFieldKt.m2655calculateWidthDHJA7U0(iIntValue4, iIntValue3, iIntValue5, iIntValue6, iIntValue, iIntValue2, intrinsicMeasurable14 != null ? function2.invoke(intrinsicMeasurable14, Integer.valueOf(i)).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        IntrinsicMeasurable intrinsicMeasurable;
        int iSubstractConstraintSafely;
        int iIntValue;
        IntrinsicMeasurable intrinsicMeasurable2;
        int iIntValue2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        int iIntValue3;
        IntrinsicMeasurable intrinsicMeasurable5;
        int i2;
        IntrinsicMeasurable intrinsicMeasurable6;
        IntrinsicMeasurable intrinsicMeasurable7;
        int size = list.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                intrinsicMeasurable = null;
                break;
            }
            intrinsicMeasurable = list.get(i3);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable), "Leading")) {
                break;
            }
            i3++;
        }
        IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable;
        if (intrinsicMeasurable8 != null) {
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(i, intrinsicMeasurable8.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = function2.invoke(intrinsicMeasurable8, Integer.valueOf(i)).intValue();
        } else {
            iSubstractConstraintSafely = i;
            iIntValue = 0;
        }
        int size2 = list.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size2) {
                intrinsicMeasurable2 = null;
                break;
            }
            intrinsicMeasurable2 = list.get(i4);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), "Trailing")) {
                break;
            }
            i4++;
        }
        IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable2;
        if (intrinsicMeasurable9 != null) {
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable9.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue2 = function2.invoke(intrinsicMeasurable9, Integer.valueOf(i)).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size3) {
                intrinsicMeasurable3 = null;
                break;
            }
            intrinsicMeasurable3 = list.get(i5);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), "Label")) {
                break;
            }
            i5++;
        }
        IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable3;
        int iIntValue4 = intrinsicMeasurable10 != null ? function2.invoke(intrinsicMeasurable10, Integer.valueOf(MathHelpersKt.lerp(iSubstractConstraintSafely, i, this.animationProgress))).intValue() : 0;
        int size4 = list.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size4) {
                intrinsicMeasurable4 = null;
                break;
            }
            intrinsicMeasurable4 = list.get(i6);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                break;
            }
            i6++;
        }
        IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable4;
        if (intrinsicMeasurable11 != null) {
            iIntValue3 = function2.invoke(intrinsicMeasurable11, Integer.valueOf(iSubstractConstraintSafely)).intValue();
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable11.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue3 = 0;
        }
        int size5 = list.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size5) {
                intrinsicMeasurable5 = null;
                break;
            }
            intrinsicMeasurable5 = list.get(i7);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                break;
            }
            i7++;
        }
        IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable5;
        if (intrinsicMeasurable12 != null) {
            int iIntValue5 = function2.invoke(intrinsicMeasurable12, Integer.valueOf(iSubstractConstraintSafely)).intValue();
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable12.maxIntrinsicWidth(Integer.MAX_VALUE));
            i2 = iIntValue5;
        } else {
            i2 = 0;
        }
        int size6 = list.size();
        for (int i8 = 0; i8 < size6; i8++) {
            IntrinsicMeasurable intrinsicMeasurable13 = list.get(i8);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), "TextField")) {
                int iIntValue6 = function2.invoke(intrinsicMeasurable13, Integer.valueOf(iSubstractConstraintSafely)).intValue();
                int size7 = list.size();
                int i9 = 0;
                while (true) {
                    if (i9 >= size7) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i9);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), "Hint")) {
                        break;
                    }
                    i9++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable6;
                int iIntValue7 = intrinsicMeasurable14 != null ? function2.invoke(intrinsicMeasurable14, Integer.valueOf(iSubstractConstraintSafely)).intValue() : 0;
                int size8 = list.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size8) {
                        intrinsicMeasurable7 = null;
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable15 = list.get(i10);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable15), TextFieldImplKt.SupportingId)) {
                        intrinsicMeasurable7 = intrinsicMeasurable15;
                        break;
                    }
                    i10++;
                }
                IntrinsicMeasurable intrinsicMeasurable16 = intrinsicMeasurable7;
                return OutlinedTextFieldKt.m2654calculateHeightmKXJcVc(iIntValue, iIntValue2, iIntValue3, i2, iIntValue6, iIntValue4, iIntValue7, intrinsicMeasurable16 != null ? function2.invoke(intrinsicMeasurable16, Integer.valueOf(i)).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
