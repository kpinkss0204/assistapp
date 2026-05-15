package androidx.compose.foundation.text.input.internal;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextLayoutState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001e\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u000b\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\f\u0010\n\u001a\u001e\u0010\r\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000f"}, d2 = {"coerceIn", "Landroidx/compose/ui/geometry/Offset;", "rect", "Landroidx/compose/ui/geometry/Rect;", "coerceIn-3MmeM6k", "(JLandroidx/compose/ui/geometry/Rect;)J", "fromDecorationToTextLayout", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", TypedValues.CycleType.S_WAVE_OFFSET, "fromDecorationToTextLayout-Uv8p0NA", "(Landroidx/compose/foundation/text/input/internal/TextLayoutState;J)J", "fromTextLayoutToCore", "fromTextLayoutToCore-Uv8p0NA", "fromWindowToDecoration", "fromWindowToDecoration-Uv8p0NA", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class TextLayoutStateKt {
    /* JADX INFO: renamed from: coerceIn-3MmeM6k, reason: not valid java name */
    public static final long m1504coerceIn3MmeM6k(long j, Rect rect) {
        float right;
        float bottom;
        if (Offset.m4327getXimpl(j) < rect.getLeft()) {
            right = rect.getLeft();
        } else {
            right = Offset.m4327getXimpl(j) > rect.getRight() ? rect.getRight() : Offset.m4327getXimpl(j);
        }
        if (Offset.m4328getYimpl(j) < rect.getTop()) {
            bottom = rect.getTop();
        } else {
            bottom = Offset.m4328getYimpl(j) > rect.getBottom() ? rect.getBottom() : Offset.m4328getYimpl(j);
        }
        return OffsetKt.Offset(right, bottom);
    }

    /* JADX INFO: renamed from: fromTextLayoutToCore-Uv8p0NA, reason: not valid java name */
    public static final long m1506fromTextLayoutToCoreUv8p0NA(TextLayoutState textLayoutState, long j) {
        LayoutCoordinates textLayoutNodeCoordinates = textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates != null) {
            Offset offsetM4316boximpl = null;
            if (!textLayoutNodeCoordinates.isAttached()) {
                textLayoutNodeCoordinates = null;
            }
            if (textLayoutNodeCoordinates != null) {
                LayoutCoordinates coreNodeCoordinates = textLayoutState.getCoreNodeCoordinates();
                if (coreNodeCoordinates != null) {
                    if (!coreNodeCoordinates.isAttached()) {
                        coreNodeCoordinates = null;
                    }
                    if (coreNodeCoordinates != null) {
                        offsetM4316boximpl = Offset.m4316boximpl(coreNodeCoordinates.mo5912localPositionOfR5De75A(textLayoutNodeCoordinates, j));
                    }
                }
                if (offsetM4316boximpl != null) {
                    return offsetM4316boximpl.getPackedValue();
                }
            }
        }
        return j;
    }

    /* JADX INFO: renamed from: fromDecorationToTextLayout-Uv8p0NA, reason: not valid java name */
    public static final long m1505fromDecorationToTextLayoutUv8p0NA(TextLayoutState textLayoutState, long j) {
        Offset offsetM4316boximpl;
        LayoutCoordinates textLayoutNodeCoordinates = textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates != null) {
            LayoutCoordinates decoratorNodeCoordinates = textLayoutState.getDecoratorNodeCoordinates();
            if (decoratorNodeCoordinates != null) {
                offsetM4316boximpl = Offset.m4316boximpl((textLayoutNodeCoordinates.isAttached() && decoratorNodeCoordinates.isAttached()) ? textLayoutNodeCoordinates.mo5912localPositionOfR5De75A(decoratorNodeCoordinates, j) : j);
            } else {
                offsetM4316boximpl = null;
            }
            if (offsetM4316boximpl != null) {
                return offsetM4316boximpl.getPackedValue();
            }
        }
        return j;
    }

    /* JADX INFO: renamed from: fromWindowToDecoration-Uv8p0NA, reason: not valid java name */
    public static final long m1507fromWindowToDecorationUv8p0NA(TextLayoutState textLayoutState, long j) {
        LayoutCoordinates decoratorNodeCoordinates = textLayoutState.getDecoratorNodeCoordinates();
        return (decoratorNodeCoordinates == null || !decoratorNodeCoordinates.isAttached()) ? j : decoratorNodeCoordinates.mo5920windowToLocalMKHz9U(j);
    }
}
