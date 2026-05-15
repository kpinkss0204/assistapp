package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookaheadLayoutCoordinates.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001bH\u0096\u0002J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00012\u0006\u0010'\u001a\u00020\nH\u0016J\"\u0010(\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u0011H\u0016ø\u0001\u0000¢\u0006\u0004\b*\u0010+J*\u0010(\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0004\b-\u0010.J\u001a\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0011H\u0016ø\u0001\u0000¢\u0006\u0004\b1\u00102J\u001a\u00103\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0011H\u0016ø\u0001\u0000¢\u0006\u0004\b4\u00102J\u001a\u00105\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0011H\u0016ø\u0001\u0000¢\u0006\u0004\b6\u00102J\u001a\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u0011H\u0016ø\u0001\u0000¢\u0006\u0004\b9\u00102J\"\u0010:\u001a\u00020;2\u0006\u0010&\u001a\u00020\u00012\u0006\u0010<\u001a\u00020=H\u0016ø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u001a\u0010@\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0016ø\u0001\u0000¢\u0006\u0004\bA\u0010BJ\u001a\u0010C\u001a\u00020\u00112\u0006\u0010D\u001a\u00020\u0011H\u0016ø\u0001\u0000¢\u0006\u0004\bE\u00102R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001f8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b \u0010\u0013\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006F"}, d2 = {"Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "lookaheadDelegate", "Landroidx/compose/ui/node/LookaheadDelegate;", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "coordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "getCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "introducesMotionFrameOfReference", "", "getIntroducesMotionFrameOfReference", "()Z", "isAttached", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "lookaheadOffset", "Landroidx/compose/ui/geometry/Offset;", "getLookaheadOffset-F1C5BW0", "()J", "parentCoordinates", "getParentCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "parentLayoutCoordinates", "getParentLayoutCoordinates", "providedAlignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "getProvidedAlignmentLines", "()Ljava/util/Set;", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "get", "", "alignmentLine", "localBoundingBoxOf", "Landroidx/compose/ui/geometry/Rect;", "sourceCoordinates", "clipBounds", "localPositionOf", "relativeToSource", "localPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "includeMotionFrameOfReference", "localPositionOf-S_NoaFU", "(Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "localToRoot", "relativeToLocal", "localToRoot-MK-Hz9U", "(J)J", "localToScreen", "localToScreen-MK-Hz9U", "localToWindow", "localToWindow-MK-Hz9U", "screenToLocal", "relativeToScreen", "screenToLocal-MK-Hz9U", "transformFrom", "", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "transformFrom-EL8BTi8", "(Landroidx/compose/ui/layout/LayoutCoordinates;[F)V", "transformToScreen", "transformToScreen-58bKbWc", "([F)V", "windowToLocal", "relativeToWindow", "windowToLocal-MK-Hz9U", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LookaheadLayoutCoordinates implements LayoutCoordinates {
    public static final int $stable = 0;
    private final LookaheadDelegate lookaheadDelegate;

    public LookaheadLayoutCoordinates(LookaheadDelegate lookaheadDelegate) {
        this.lookaheadDelegate = lookaheadDelegate;
    }

    public final LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    public final NodeCoordinator getCoordinator() {
        return this.lookaheadDelegate.getCoordinator();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: getSize-YbymL2g */
    public long mo5911getSizeYbymL2g() {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        return IntSizeKt.IntSize(lookaheadDelegate.getWidth(), lookaheadDelegate.getHeight());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Set<AlignmentLine> getProvidedAlignmentLines() {
        return getCoordinator().getProvidedAlignmentLines();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public LayoutCoordinates getParentLayoutCoordinates() {
        LookaheadDelegate lookaheadDelegate;
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(NodeCoordinator.ExpectAttachedLayoutCoordinates);
        }
        NodeCoordinator wrappedBy = getCoordinator().getLayoutNode().getOuterCoordinator$ui_release().getWrappedBy();
        if (wrappedBy == null || (lookaheadDelegate = wrappedBy.getLookaheadDelegate()) == null) {
            return null;
        }
        return lookaheadDelegate.getCoordinates();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public LayoutCoordinates getParentCoordinates() {
        LookaheadDelegate lookaheadDelegate;
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException(NodeCoordinator.ExpectAttachedLayoutCoordinates);
        }
        NodeCoordinator wrappedBy = getCoordinator().getWrappedBy();
        if (wrappedBy == null || (lookaheadDelegate = wrappedBy.getLookaheadDelegate()) == null) {
            return null;
        }
        return lookaheadDelegate.getCoordinates();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean isAttached() {
        return getCoordinator().isAttached();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean getIntroducesMotionFrameOfReference() {
        return this.lookaheadDelegate.getIsPlacedUnderMotionFrameOfReference();
    }

    /* JADX INFO: renamed from: getLookaheadOffset-F1C5BW0, reason: not valid java name */
    private final long m5930getLookaheadOffsetF1C5BW0() {
        LookaheadDelegate rootLookaheadDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
        return Offset.m4331minusMKHz9U(mo5912localPositionOfR5De75A(rootLookaheadDelegate.getCoordinates(), Offset.INSTANCE.m4343getZeroF1C5BW0()), getCoordinator().mo5912localPositionOfR5De75A(rootLookaheadDelegate.getCoordinator(), Offset.INSTANCE.m4343getZeroF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo5917screenToLocalMKHz9U(long relativeToScreen) {
        return Offset.m4332plusMKHz9U(getCoordinator().mo5917screenToLocalMKHz9U(relativeToScreen), m5930getLookaheadOffsetF1C5BW0());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U */
    public long mo5915localToScreenMKHz9U(long relativeToLocal) {
        return getCoordinator().mo5915localToScreenMKHz9U(Offset.m4332plusMKHz9U(relativeToLocal, m5930getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: windowToLocal-MK-Hz9U */
    public long mo5920windowToLocalMKHz9U(long relativeToWindow) {
        return Offset.m4332plusMKHz9U(getCoordinator().mo5920windowToLocalMKHz9U(relativeToWindow), m5930getLookaheadOffsetF1C5BW0());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToWindow-MK-Hz9U */
    public long mo5916localToWindowMKHz9U(long relativeToLocal) {
        return getCoordinator().mo5916localToWindowMKHz9U(Offset.m4332plusMKHz9U(relativeToLocal, m5930getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToRoot-MK-Hz9U */
    public long mo5914localToRootMKHz9U(long relativeToLocal) {
        return getCoordinator().mo5914localToRootMKHz9U(Offset.m4332plusMKHz9U(relativeToLocal, m5930getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-R5De75A */
    public long mo5912localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long relativeToSource) {
        return mo5913localPositionOfS_NoaFU(sourceCoordinates, relativeToSource, true);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-S_NoaFU */
    public long mo5913localPositionOfS_NoaFU(LayoutCoordinates sourceCoordinates, long relativeToSource, boolean includeMotionFrameOfReference) {
        if (sourceCoordinates instanceof LookaheadLayoutCoordinates) {
            LookaheadDelegate lookaheadDelegate = ((LookaheadLayoutCoordinates) sourceCoordinates).lookaheadDelegate;
            lookaheadDelegate.getCoordinator().onCoordinatesUsed$ui_release();
            LookaheadDelegate lookaheadDelegate2 = getCoordinator().findCommonAncestor$ui_release(lookaheadDelegate.getCoordinator()).getLookaheadDelegate();
            if (lookaheadDelegate2 != null) {
                long jM7158minusqkQi6aY = IntOffset.m7158minusqkQi6aY(IntOffset.m7159plusqkQi6aY(lookaheadDelegate.m6088positionIniSbpLlY$ui_release(lookaheadDelegate2, !includeMotionFrameOfReference), IntOffsetKt.m7171roundk4lQ0M(relativeToSource)), this.lookaheadDelegate.m6088positionIniSbpLlY$ui_release(lookaheadDelegate2, !includeMotionFrameOfReference));
                return OffsetKt.Offset(IntOffset.m7155getXimpl(jM7158minusqkQi6aY), IntOffset.m7156getYimpl(jM7158minusqkQi6aY));
            }
            LookaheadDelegate rootLookaheadDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate);
            long jM7159plusqkQi6aY = IntOffset.m7159plusqkQi6aY(IntOffset.m7159plusqkQi6aY(lookaheadDelegate.m6088positionIniSbpLlY$ui_release(rootLookaheadDelegate, !includeMotionFrameOfReference), rootLookaheadDelegate.getPosition()), IntOffsetKt.m7171roundk4lQ0M(relativeToSource));
            LookaheadDelegate rootLookaheadDelegate2 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
            long jM7158minusqkQi6aY2 = IntOffset.m7158minusqkQi6aY(jM7159plusqkQi6aY, IntOffset.m7159plusqkQi6aY(this.lookaheadDelegate.m6088positionIniSbpLlY$ui_release(rootLookaheadDelegate2, !includeMotionFrameOfReference), rootLookaheadDelegate2.getPosition()));
            long jOffset = OffsetKt.Offset(IntOffset.m7155getXimpl(jM7158minusqkQi6aY2), IntOffset.m7156getYimpl(jM7158minusqkQi6aY2));
            NodeCoordinator wrappedBy = rootLookaheadDelegate2.getCoordinator().getWrappedBy();
            Intrinsics.checkNotNull(wrappedBy);
            NodeCoordinator wrappedBy2 = rootLookaheadDelegate.getCoordinator().getWrappedBy();
            Intrinsics.checkNotNull(wrappedBy2);
            return wrappedBy.mo5913localPositionOfS_NoaFU(wrappedBy2, jOffset, includeMotionFrameOfReference);
        }
        LookaheadDelegate rootLookaheadDelegate3 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
        return Offset.m4332plusMKHz9U(mo5913localPositionOfS_NoaFU(rootLookaheadDelegate3.getLookaheadLayoutCoordinates(), relativeToSource, includeMotionFrameOfReference), rootLookaheadDelegate3.getCoordinator().getCoordinates().mo5913localPositionOfS_NoaFU(sourceCoordinates, Offset.INSTANCE.m4343getZeroF1C5BW0(), includeMotionFrameOfReference));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates sourceCoordinates, boolean clipBounds) {
        return getCoordinator().localBoundingBoxOf(sourceCoordinates, clipBounds);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformFrom-EL8BTi8 */
    public void mo5918transformFromEL8BTi8(LayoutCoordinates sourceCoordinates, float[] matrix) {
        getCoordinator().mo5918transformFromEL8BTi8(sourceCoordinates, matrix);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformToScreen-58bKbWc */
    public void mo5919transformToScreen58bKbWc(float[] matrix) {
        getCoordinator().mo5919transformToScreen58bKbWc(matrix);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public int get(AlignmentLine alignmentLine) {
        return this.lookaheadDelegate.get(alignmentLine);
    }
}
