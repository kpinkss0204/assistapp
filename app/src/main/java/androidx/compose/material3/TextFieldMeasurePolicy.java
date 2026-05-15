package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.TextFieldImplKt;
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

/* JADX INFO: compiled from: TextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ8\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J<\u0010\u0011\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J\"\u0010\u0014\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u0015\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016J,\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\"\u0010\u001e\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u001f\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"Landroidx/compose/material3/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicWidth", "", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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
        long j2;
        Placeable placeableMo5903measureBRTryo0;
        Measurable measurable3;
        Measurable measurable4;
        int i;
        final Placeable placeable;
        Placeable placeable2;
        long j3;
        Placeable placeableMo5903measureBRTryo02;
        Placeable placeable3;
        Measurable measurable5;
        Placeable placeable4;
        Measurable measurable6;
        Placeable placeable5;
        Measurable measurable7;
        final TextFieldMeasurePolicy textFieldMeasurePolicy = this;
        final MeasureScope measureScope2 = measureScope;
        List<? extends Measurable> list2 = list;
        final int i2 = measureScope2.mo651roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getTop());
        int i3 = measureScope2.mo651roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getBottom());
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
        Measurable measurable8 = measurable;
        Placeable placeableMo5903measureBRTryo03 = measurable8 != null ? measurable8.mo5903measureBRTryo0(jM6971copyZbe2FdA$default) : null;
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo03);
        int iMax = Math.max(0, TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo03));
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
        Measurable measurable9 = measurable2;
        if (measurable9 != null) {
            j2 = jM6971copyZbe2FdA$default;
            placeableMo5903measureBRTryo0 = measurable9.mo5903measureBRTryo0(ConstraintsKt.m7000offsetNN6EwU$default(j2, -iWidthOrZero, 0, 2, null));
        } else {
            j2 = jM6971copyZbe2FdA$default;
            placeableMo5903measureBRTryo0 = null;
        }
        int iWidthOrZero2 = iWidthOrZero + TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo0);
        int iMax2 = Math.max(iMax, TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo0));
        int size3 = list2.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size3) {
                measurable3 = null;
                break;
            }
            measurable3 = list2.get(i6);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), TextFieldImplKt.PrefixId)) {
                break;
            }
            i6++;
        }
        Measurable measurable10 = measurable3;
        final Placeable placeableMo5903measureBRTryo04 = measurable10 != null ? measurable10.mo5903measureBRTryo0(ConstraintsKt.m7000offsetNN6EwU$default(j2, -iWidthOrZero2, 0, 2, null)) : null;
        int iWidthOrZero3 = iWidthOrZero2 + TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo04);
        int iMax3 = Math.max(iMax2, TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo04));
        int size4 = list2.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size4) {
                measurable4 = null;
                break;
            }
            measurable4 = list2.get(i7);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.SuffixId)) {
                break;
            }
            i7++;
        }
        Measurable measurable11 = measurable4;
        if (measurable11 != null) {
            placeable = placeableMo5903measureBRTryo0;
            i = iWidthOrZero3;
            placeable2 = placeableMo5903measureBRTryo03;
            j3 = j2;
            placeableMo5903measureBRTryo02 = measurable11.mo5903measureBRTryo0(ConstraintsKt.m7000offsetNN6EwU$default(j2, -iWidthOrZero3, 0, 2, null));
        } else {
            i = iWidthOrZero3;
            placeable = placeableMo5903measureBRTryo0;
            placeable2 = placeableMo5903measureBRTryo03;
            j3 = j2;
            placeableMo5903measureBRTryo02 = null;
        }
        int iWidthOrZero4 = TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo02) + i;
        int iMax4 = Math.max(iMax3, TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo02));
        int i8 = -iWidthOrZero4;
        long jM6999offsetNN6EwU = ConstraintsKt.m6999offsetNN6EwU(j3, i8, -i3);
        int size5 = list2.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size5) {
                placeable3 = placeableMo5903measureBRTryo02;
                measurable5 = null;
                break;
            }
            measurable5 = list2.get(i9);
            int i10 = i9;
            placeable3 = placeableMo5903measureBRTryo02;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), "Label")) {
                break;
            }
            i9 = i10 + 1;
            placeableMo5903measureBRTryo02 = placeable3;
        }
        Measurable measurable12 = measurable5;
        Placeable placeableMo5903measureBRTryo05 = measurable12 != null ? measurable12.mo5903measureBRTryo0(jM6999offsetNN6EwU) : null;
        int size6 = list2.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size6) {
                placeable4 = placeableMo5903measureBRTryo05;
                measurable6 = null;
                break;
            }
            measurable6 = list2.get(i11);
            placeable4 = placeableMo5903measureBRTryo05;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable6), TextFieldImplKt.SupportingId)) {
                break;
            }
            i11++;
            placeableMo5903measureBRTryo05 = placeable4;
        }
        Measurable measurable13 = measurable6;
        int iMinIntrinsicHeight = measurable13 != null ? measurable13.minIntrinsicHeight(Constraints.m6982getMinWidthimpl(j)) : 0;
        int iHeightOrZero = TextFieldImplKt.heightOrZero(placeable4) + i2;
        long j4 = j3;
        long jM6999offsetNN6EwU2 = ConstraintsKt.m6999offsetNN6EwU(Constraints.m6971copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null), i8, ((-iHeightOrZero) - i3) - iMinIntrinsicHeight);
        int size7 = list2.size();
        int i12 = 0;
        while (i12 < size7) {
            Measurable measurable14 = list2.get(i12);
            int i13 = size7;
            int i14 = iHeightOrZero;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable14), "TextField")) {
                Placeable placeableMo5903measureBRTryo06 = measurable14.mo5903measureBRTryo0(jM6999offsetNN6EwU2);
                long jM6971copyZbe2FdA$default2 = Constraints.m6971copyZbe2FdA$default(jM6999offsetNN6EwU2, 0, 0, 0, 0, 14, null);
                int size8 = list2.size();
                int i15 = 0;
                while (true) {
                    if (i15 >= size8) {
                        placeable5 = placeableMo5903measureBRTryo06;
                        measurable7 = null;
                        break;
                    }
                    measurable7 = list2.get(i15);
                    placeable5 = placeableMo5903measureBRTryo06;
                    int i16 = size8;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable7), "Hint")) {
                        break;
                    }
                    i15++;
                    size8 = i16;
                    placeableMo5903measureBRTryo06 = placeable5;
                }
                Measurable measurable15 = measurable7;
                final Placeable placeableMo5903measureBRTryo07 = measurable15 != null ? measurable15.mo5903measureBRTryo0(jM6971copyZbe2FdA$default2) : null;
                int iMax5 = Math.max(iMax4, Math.max(TextFieldImplKt.heightOrZero(placeable5), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo07)) + i14 + i3);
                int iM2994calculateWidthyeHjK3Y = TextFieldKt.m2994calculateWidthyeHjK3Y(TextFieldImplKt.widthOrZero(placeable2), TextFieldImplKt.widthOrZero(placeable), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo04), TextFieldImplKt.widthOrZero(placeable3), placeable5.getWidth(), TextFieldImplKt.widthOrZero(placeable4), TextFieldImplKt.widthOrZero(placeableMo5903measureBRTryo07), j);
                int i17 = iM2994calculateWidthyeHjK3Y;
                final Placeable placeableMo5903measureBRTryo08 = measurable13 != null ? measurable13.mo5903measureBRTryo0(Constraints.m6971copyZbe2FdA$default(ConstraintsKt.m7000offsetNN6EwU$default(j4, 0, -iMax5, 1, null), 0, iM2994calculateWidthyeHjK3Y, 0, 0, 9, null)) : null;
                int iHeightOrZero2 = TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo08);
                int iM2993calculateHeightmKXJcVc = TextFieldKt.m2993calculateHeightmKXJcVc(placeable5.getHeight(), TextFieldImplKt.heightOrZero(placeable4), TextFieldImplKt.heightOrZero(placeable2), TextFieldImplKt.heightOrZero(placeable), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo04), TextFieldImplKt.heightOrZero(placeable3), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo07), TextFieldImplKt.heightOrZero(placeableMo5903measureBRTryo08), textFieldMeasurePolicy.animationProgress, j, measureScope2.getDensity(), textFieldMeasurePolicy.paddingValues);
                int i18 = iM2993calculateHeightmKXJcVc - iHeightOrZero2;
                int size9 = list2.size();
                int i19 = 0;
                while (i19 < size9) {
                    Measurable measurable16 = list2.get(i19);
                    final int i20 = iM2993calculateHeightmKXJcVc;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable16), TextFieldImplKt.ContainerId)) {
                        final Placeable placeableMo5903measureBRTryo09 = measurable16.mo5903measureBRTryo0(ConstraintsKt.Constraints(i17 != Integer.MAX_VALUE ? i17 : 0, i17, i18 != Integer.MAX_VALUE ? i18 : 0, i18));
                        final int i21 = i17;
                        final Placeable placeable6 = placeable2;
                        final Placeable placeable7 = placeable3;
                        final Placeable placeable8 = placeable4;
                        final Placeable placeable9 = placeable5;
                        return MeasureScope.layout$default(measureScope, i21, i20, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$measure$1
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
                                Placeable placeable10 = placeable8;
                                if (placeable10 == null) {
                                    TextFieldKt.placeWithoutLabel(placementScope, i21, i20, placeable9, placeableMo5903measureBRTryo07, placeable6, placeable, placeableMo5903measureBRTryo04, placeable7, placeableMo5903measureBRTryo09, placeableMo5903measureBRTryo08, textFieldMeasurePolicy.singleLine, measureScope2.getDensity(), textFieldMeasurePolicy.paddingValues);
                                    return;
                                }
                                int i22 = i21;
                                int i23 = i20;
                                Placeable placeable11 = placeable9;
                                Placeable placeable12 = placeableMo5903measureBRTryo07;
                                Placeable placeable13 = placeable6;
                                Placeable placeable14 = placeable;
                                Placeable placeable15 = placeableMo5903measureBRTryo04;
                                Placeable placeable16 = placeable7;
                                Placeable placeable17 = placeableMo5903measureBRTryo09;
                                Placeable placeable18 = placeableMo5903measureBRTryo08;
                                boolean z = textFieldMeasurePolicy.singleLine;
                                int i24 = i2;
                                TextFieldKt.placeWithLabel(placementScope, i22, i23, placeable11, placeable10, placeable12, placeable13, placeable14, placeable15, placeable16, placeable17, placeable18, z, i24, i24 + placeable8.getHeight(), textFieldMeasurePolicy.animationProgress, measureScope2.getDensity());
                            }
                        }, 4, null);
                    }
                    i19++;
                    i17 = i17;
                    iM2993calculateHeightmKXJcVc = i20;
                    textFieldMeasurePolicy = this;
                    measureScope2 = measureScope;
                    list2 = list;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i12++;
            textFieldMeasurePolicy = this;
            measureScope2 = measureScope;
            iHeightOrZero = i14;
            jM6999offsetNN6EwU2 = jM6999offsetNN6EwU2;
            size7 = i13;
            list2 = list;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.maxIntrinsicHeight.1
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
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.minIntrinsicHeight.1
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
        return intrinsicWidth(list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.maxIntrinsicWidth.1
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
        return intrinsicWidth(list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.minIntrinsicWidth.1
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
        IntrinsicMeasurable intrinsicMeasurable5;
        IntrinsicMeasurable intrinsicMeasurable6;
        int size = measurables.size();
        for (int i = 0; i < size; i++) {
            IntrinsicMeasurable intrinsicMeasurable7 = measurables.get(i);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable7), "TextField")) {
                int iIntValue = intrinsicMeasurer.invoke(intrinsicMeasurable7, Integer.valueOf(height)).intValue();
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
                IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable2;
                int iIntValue2 = intrinsicMeasurable8 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable8, Integer.valueOf(height)).intValue() : 0;
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
                IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable3;
                int iIntValue3 = intrinsicMeasurable9 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable9, Integer.valueOf(height)).intValue() : 0;
                int size4 = measurables.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size4) {
                        intrinsicMeasurable4 = null;
                        break;
                    }
                    intrinsicMeasurable4 = measurables.get(i4);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    i4++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable4;
                int iIntValue4 = intrinsicMeasurable10 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable10, Integer.valueOf(height)).intValue() : 0;
                int size5 = measurables.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size5) {
                        intrinsicMeasurable5 = null;
                        break;
                    }
                    intrinsicMeasurable5 = measurables.get(i5);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    i5++;
                }
                IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable5;
                int iIntValue5 = intrinsicMeasurable11 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable11, Integer.valueOf(height)).intValue() : 0;
                int size6 = measurables.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size6) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = measurables.get(i6);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), "Leading")) {
                        break;
                    }
                    i6++;
                }
                IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable6;
                int iIntValue6 = intrinsicMeasurable12 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable12, Integer.valueOf(height)).intValue() : 0;
                int size7 = measurables.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size7) {
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable13 = measurables.get(i7);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), "Hint")) {
                        intrinsicMeasurable = intrinsicMeasurable13;
                        break;
                    }
                    i7++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable;
                return TextFieldKt.m2994calculateWidthyeHjK3Y(iIntValue6, iIntValue3, iIntValue4, iIntValue5, iIntValue, iIntValue2, intrinsicMeasurable14 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable14, Integer.valueOf(height)).intValue() : 0, TextFieldImplKt.getZeroConstraints());
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        IntrinsicMeasurable intrinsicMeasurable;
        int i2;
        int iSubstractConstraintSafely;
        int iIntValue;
        IntrinsicMeasurable intrinsicMeasurable2;
        int iIntValue2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        int iIntValue3;
        IntrinsicMeasurable intrinsicMeasurable5;
        int i3;
        IntrinsicMeasurable intrinsicMeasurable6;
        IntrinsicMeasurable intrinsicMeasurable7;
        int size = list.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                intrinsicMeasurable = null;
                break;
            }
            intrinsicMeasurable = list.get(i4);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable), "Leading")) {
                break;
            }
            i4++;
        }
        IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable;
        if (intrinsicMeasurable8 != null) {
            i2 = i;
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(i2, intrinsicMeasurable8.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = function2.invoke(intrinsicMeasurable8, Integer.valueOf(i2)).intValue();
        } else {
            i2 = i;
            iSubstractConstraintSafely = i2;
            iIntValue = 0;
        }
        int size2 = list.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size2) {
                intrinsicMeasurable2 = null;
                break;
            }
            intrinsicMeasurable2 = list.get(i5);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), "Trailing")) {
                break;
            }
            i5++;
        }
        IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable2;
        if (intrinsicMeasurable9 != null) {
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable9.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue2 = function2.invoke(intrinsicMeasurable9, Integer.valueOf(i2)).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size3) {
                intrinsicMeasurable3 = null;
                break;
            }
            intrinsicMeasurable3 = list.get(i6);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), "Label")) {
                break;
            }
            i6++;
        }
        IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable3;
        int iIntValue4 = intrinsicMeasurable10 != null ? function2.invoke(intrinsicMeasurable10, Integer.valueOf(iSubstractConstraintSafely)).intValue() : 0;
        int size4 = list.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size4) {
                intrinsicMeasurable4 = null;
                break;
            }
            intrinsicMeasurable4 = list.get(i7);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                break;
            }
            i7++;
        }
        IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable4;
        if (intrinsicMeasurable11 != null) {
            iIntValue3 = function2.invoke(intrinsicMeasurable11, Integer.valueOf(iSubstractConstraintSafely)).intValue();
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable11.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue3 = 0;
        }
        int size5 = list.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size5) {
                intrinsicMeasurable5 = null;
                break;
            }
            intrinsicMeasurable5 = list.get(i8);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                break;
            }
            i8++;
        }
        IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable5;
        if (intrinsicMeasurable12 != null) {
            int iIntValue5 = function2.invoke(intrinsicMeasurable12, Integer.valueOf(iSubstractConstraintSafely)).intValue();
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable12.maxIntrinsicWidth(Integer.MAX_VALUE));
            i3 = iIntValue5;
        } else {
            i3 = 0;
        }
        int size6 = list.size();
        for (int i9 = 0; i9 < size6; i9++) {
            IntrinsicMeasurable intrinsicMeasurable13 = list.get(i9);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), "TextField")) {
                int iIntValue6 = function2.invoke(intrinsicMeasurable13, Integer.valueOf(iSubstractConstraintSafely)).intValue();
                int size7 = list.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size7) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i10);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), "Hint")) {
                        break;
                    }
                    i10++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable6;
                int iIntValue7 = intrinsicMeasurable14 != null ? function2.invoke(intrinsicMeasurable14, Integer.valueOf(iSubstractConstraintSafely)).intValue() : 0;
                int size8 = list.size();
                int i11 = 0;
                while (true) {
                    if (i11 >= size8) {
                        intrinsicMeasurable7 = null;
                        break;
                    }
                    intrinsicMeasurable7 = list.get(i11);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.SupportingId)) {
                        break;
                    }
                    i11++;
                }
                IntrinsicMeasurable intrinsicMeasurable15 = intrinsicMeasurable7;
                return TextFieldKt.m2993calculateHeightmKXJcVc(iIntValue6, iIntValue4, iIntValue, iIntValue2, iIntValue3, i3, iIntValue7, intrinsicMeasurable15 != null ? function2.invoke(intrinsicMeasurable15, Integer.valueOf(i2)).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
