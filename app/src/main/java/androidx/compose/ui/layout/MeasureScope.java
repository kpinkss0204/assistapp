package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.DpRect;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: MeasureScope.kt */
/* JADX INFO: loaded from: classes2.dex */
@MeasureScopeMarker
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001Jd\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b2\u001b\b\u0002\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b¢\u0006\u0002\b\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0016JG\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "layout", "Landroidx/compose/ui/layout/MeasureResult;", "width", "", "height", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "rulers", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/RulerScope;", "", "Lkotlin/ExtensionFunctionType;", "placementBlock", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public interface MeasureScope extends IntrinsicMeasureScope {

    /* JADX INFO: compiled from: MeasureScope.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean isLookingAhead(MeasureScope measureScope) {
            return MeasureScope.super.isLookingAhead();
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m5945roundToPxR2X_6o(MeasureScope measureScope, long j) {
            return MeasureScope.super.mo650roundToPxR2X_6o(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m5946roundToPx0680j_4(MeasureScope measureScope, float f) {
            return MeasureScope.super.mo651roundToPx0680j_4(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m5947toDpGaN1DYA(MeasureScope measureScope, long j) {
            return MeasureScope.super.mo652toDpGaN1DYA(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5948toDpu2uoSUM(MeasureScope measureScope, float f) {
            return MeasureScope.super.mo653toDpu2uoSUM(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5949toDpu2uoSUM(MeasureScope measureScope, int i) {
            return MeasureScope.super.mo654toDpu2uoSUM(i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m5950toDpSizekrfVVM(MeasureScope measureScope, long j) {
            return MeasureScope.super.mo655toDpSizekrfVVM(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m5951toPxR2X_6o(MeasureScope measureScope, long j) {
            return MeasureScope.super.mo656toPxR2X_6o(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m5952toPx0680j_4(MeasureScope measureScope, float f) {
            return MeasureScope.super.mo657toPx0680j_4(f);
        }

        @Deprecated
        public static Rect toRect(MeasureScope measureScope, DpRect dpRect) {
            return MeasureScope.super.toRect(dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m5953toSizeXkaWNTQ(MeasureScope measureScope, long j) {
            return MeasureScope.super.mo658toSizeXkaWNTQ(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m5954toSp0xMU5do(MeasureScope measureScope, float f) {
            return MeasureScope.super.mo659toSp0xMU5do(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5955toSpkPz2Gy4(MeasureScope measureScope, float f) {
            return MeasureScope.super.mo660toSpkPz2Gy4(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5956toSpkPz2Gy4(MeasureScope measureScope, int i) {
            return MeasureScope.super.mo661toSpkPz2Gy4(i);
        }

        @Deprecated
        public static MeasureResult layout(MeasureScope measureScope, int i, int i2, Map<AlignmentLine, Integer> map, Function1<? super Placeable.PlacementScope, Unit> function1) {
            return MeasureScope.super.layout(i, i2, map, function1);
        }

        @Deprecated
        public static MeasureResult layout(MeasureScope measureScope, int i, int i2, Map<AlignmentLine, Integer> map, Function1<? super RulerScope, Unit> function1, Function1<? super Placeable.PlacementScope, Unit> function12) {
            return MeasureScope.super.layout(i, i2, map, function1, function12);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ MeasureResult layout$default(MeasureScope measureScope, int i, int i2, Map map, Function1 function1, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: layout");
        }
        if ((i3 & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        return measureScope.layout(i, i2, map, function1);
    }

    default MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
        return layout(width, height, alignmentLines, null, placementBlock);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ MeasureResult layout$default(MeasureScope measureScope, int i, int i2, Map map, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: layout");
        }
        if ((i3 & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        if ((i3 & 8) != 0) {
            function1 = null;
        }
        return measureScope.layout(i, i2, map2, function1, function12);
    }

    default MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super RulerScope, Unit> rulers, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
        if (!((width & ViewCompat.MEASURED_STATE_MASK) == 0 && ((-16777216) & height) == 0)) {
            InlineClassHelperKt.throwIllegalStateException("Size(" + width + " x " + height + ") is out of range. Each dimension must be between 0 and 16777215.");
        }
        return new MeasureResult(width, height, alignmentLines, rulers, this, placementBlock) { // from class: androidx.compose.ui.layout.MeasureScope.layout.1
            final /* synthetic */ Function1<Placeable.PlacementScope, Unit> $placementBlock;
            final /* synthetic */ int $width;
            private final Map<AlignmentLine, Integer> alignmentLines;
            private final int height;
            private final Function1<RulerScope, Unit> rulers;
            final /* synthetic */ MeasureScope this$0;
            private final int width;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$width = width;
                this.this$0 = this;
                this.$placementBlock = placementBlock;
                this.width = width;
                this.height = height;
                this.alignmentLines = alignmentLines;
                this.rulers = rulers;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getWidth, reason: from getter */
            public int get$width() {
                return this.width;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getHeight, reason: from getter */
            public int get$height() {
                return this.height;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return this.alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Function1<RulerScope, Unit> getRulers() {
                return this.rulers;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                MeasureScope measureScope = this.this$0;
                if (measureScope instanceof LookaheadCapablePlaceable) {
                    this.$placementBlock.invoke(((LookaheadCapablePlaceable) measureScope).getPlacementScope());
                } else {
                    this.$placementBlock.invoke(new SimplePlacementScope(this.$width, this.this$0.getLayoutDirection()));
                }
            }
        };
    }
}
