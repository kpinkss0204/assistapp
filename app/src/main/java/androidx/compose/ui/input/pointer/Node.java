package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.util.PointerIdArray;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.PointerInputModifierNodeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HitPathTracker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u0016\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u0010\u0010 \u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0017\u0010!\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001d0#H\u0082\bJ.\u0010$\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bH\u0016J\u001a\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\r2\u0006\u0010'\u001a\u00020\rH\u0002J\u0006\u0010(\u001a\u00020\u001dJ\u001e\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00000-H\u0016J\b\u0010.\u001a\u00020/H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Landroidx/compose/ui/input/pointer/Node;", "Landroidx/compose/ui/input/pointer/NodeParent;", "modifierNode", "Landroidx/compose/ui/Modifier$Node;", "(Landroidx/compose/ui/Modifier$Node;)V", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "hasExited", "", "isIn", "getModifierNode", "()Landroidx/compose/ui/Modifier$Node;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerIds", "Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "getPointerIds", "()Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "relevantChanges", "Landroidx/collection/LongSparseArray;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "wasIn", "buildCache", "changes", "parentCoordinates", "internalPointerEvent", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "isInBounds", "cleanUpHits", "", "clearCache", "dispatchCancel", "dispatchFinalEventPass", "dispatchIfNeeded", "block", "Lkotlin/Function0;", "dispatchMainEventPass", "hasPositionChanged", "oldEvent", "newEvent", "markIsIn", "removeInvalidPointerIdsAndChanges", "pointerIdValue", "", "hitNodes", "Landroidx/collection/MutableObjectList;", "toString", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class Node extends NodeParent {
    public static final int $stable = 8;
    private LayoutCoordinates coordinates;
    private final Modifier.Node modifierNode;
    private PointerEvent pointerEvent;
    private boolean wasIn;
    private final PointerIdArray pointerIds = new PointerIdArray();
    private final LongSparseArray<PointerInputChange> relevantChanges = new LongSparseArray<>(2);
    private boolean isIn = true;
    private boolean hasExited = true;

    public Node(Modifier.Node node) {
        this.modifierNode = node;
    }

    public final Modifier.Node getModifierNode() {
        return this.modifierNode;
    }

    public final PointerIdArray getPointerIds() {
        return this.pointerIds;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void removeInvalidPointerIdsAndChanges(long pointerIdValue, MutableObjectList<Node> hitNodes) {
        if (this.pointerIds.contains(pointerIdValue) && !hitNodes.contains(this)) {
            this.pointerIds.remove(pointerIdValue);
            this.relevantChanges.remove(pointerIdValue);
        }
        MutableVector<Node> children = getChildren();
        int size = children.getSize();
        if (size > 0) {
            Node[] content = children.getContent();
            int i = 0;
            do {
                content[i].removeInvalidPointerIdsAndChanges(pointerIdValue, hitNodes);
                i++;
            } while (i < size);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v23 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean buildCache(LongSparseArray<PointerInputChange> changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        PointerInputChange pointerInputChange;
        boolean z;
        int iM5728getExit7fucELk;
        boolean z2;
        int i;
        boolean z3;
        int i2;
        int i3;
        boolean zBuildCache = super.buildCache(changes, parentCoordinates, internalPointerEvent, isInBounds);
        boolean z4 = true;
        if (!this.modifierNode.getIsAttached()) {
            return true;
        }
        Modifier.Node nodePop = this.modifierNode;
        int iM6138constructorimpl = NodeKind.m6138constructorimpl(16);
        MutableVector mutableVector = null;
        while (nodePop != 0) {
            if (nodePop instanceof PointerInputModifierNode) {
                this.coordinates = PointerInputModifierNodeKt.getLayoutCoordinates((PointerInputModifierNode) nodePop);
            } else if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                int i4 = 0;
                nodePop = nodePop;
                while (delegate != null) {
                    if ((delegate.getKindSet() & iM6138constructorimpl) != 0) {
                        i4++;
                        if (i4 == 1) {
                            nodePop = delegate;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (nodePop != 0) {
                                if (mutableVector != null) {
                                    mutableVector.add(nodePop);
                                }
                                nodePop = 0;
                            }
                            if (mutableVector != null) {
                                mutableVector.add(delegate);
                            }
                        }
                    }
                    delegate = delegate.getChild();
                    nodePop = nodePop;
                }
                if (i4 == 1) {
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
        }
        int size = changes.size();
        int i5 = 0;
        while (i5 < size) {
            long jKeyAt = changes.keyAt(i5);
            PointerInputChange pointerInputChangeValueAt = changes.valueAt(i5);
            if (this.pointerIds.contains(jKeyAt)) {
                boolean z5 = z4;
                int i6 = i5;
                long previousPosition = pointerInputChangeValueAt.getPreviousPosition();
                z3 = z5;
                long position = pointerInputChangeValueAt.getPosition();
                if (Offset.m4330isValidimpl(previousPosition) && Offset.m4330isValidimpl(position)) {
                    ArrayList arrayList = new ArrayList(pointerInputChangeValueAt.getHistorical().size());
                    List<HistoricalChange> historical = pointerInputChangeValueAt.getHistorical();
                    z2 = zBuildCache;
                    int size2 = historical.size();
                    i = size;
                    int i7 = 0;
                    while (i7 < size2) {
                        HistoricalChange historicalChange = historical.get(i7);
                        int i8 = size2;
                        int i9 = i7;
                        long position2 = historicalChange.getPosition();
                        if (Offset.m4330isValidimpl(position2)) {
                            long uptimeMillis = historicalChange.getUptimeMillis();
                            i3 = i6;
                            LayoutCoordinates layoutCoordinates = this.coordinates;
                            Intrinsics.checkNotNull(layoutCoordinates);
                            arrayList.add(new HistoricalChange(uptimeMillis, layoutCoordinates.mo5912localPositionOfR5De75A(parentCoordinates, position2), historicalChange.getOriginalEventPosition(), null));
                        } else {
                            i3 = i6;
                        }
                        i7 = i9 + 1;
                        size2 = i8;
                        i6 = i3;
                    }
                    i2 = i6;
                    LongSparseArray<PointerInputChange> longSparseArray = this.relevantChanges;
                    LayoutCoordinates layoutCoordinates2 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates2);
                    long jMo5912localPositionOfR5De75A = layoutCoordinates2.mo5912localPositionOfR5De75A(parentCoordinates, previousPosition);
                    LayoutCoordinates layoutCoordinates3 = this.coordinates;
                    Intrinsics.checkNotNull(layoutCoordinates3);
                    longSparseArray.put(jKeyAt, PointerInputChange.m5763copyOHpmEuE$default(pointerInputChangeValueAt, 0L, 0L, layoutCoordinates3.mo5912localPositionOfR5De75A(parentCoordinates, position), false, 0L, jMo5912localPositionOfR5De75A, false, 0, arrayList, 0L, 731, null));
                } else {
                    z2 = zBuildCache;
                    i = size;
                    i2 = i6;
                }
            } else {
                z2 = zBuildCache;
                i = size;
                z3 = z4;
                i2 = i5;
            }
            i5 = i2 + 1;
            z4 = z3;
            zBuildCache = z2;
            size = i;
        }
        boolean z6 = zBuildCache;
        boolean z7 = z4;
        if (this.relevantChanges.isEmpty()) {
            this.pointerIds.clear();
            getChildren().clear();
            return z7;
        }
        int size3 = this.pointerIds.getSize();
        while (true) {
            size3--;
            if (-1 >= size3) {
                break;
            }
            if (!changes.containsKey(this.pointerIds.m5869get_I2yYro(size3))) {
                this.pointerIds.removeAt(size3);
            }
        }
        ArrayList arrayList2 = new ArrayList(this.relevantChanges.size());
        int size4 = this.relevantChanges.size();
        for (int i10 = 0; i10 < size4; i10++) {
            arrayList2.add(this.relevantChanges.valueAt(i10));
        }
        PointerEvent pointerEvent = new PointerEvent(arrayList2, internalPointerEvent);
        List<PointerInputChange> changes2 = pointerEvent.getChanges();
        int size5 = changes2.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size5) {
                pointerInputChange = null;
                break;
            }
            PointerInputChange pointerInputChange2 = changes2.get(i11);
            if (internalPointerEvent.m5703activeHoverEvent0FcD4WY(pointerInputChange2.getId())) {
                pointerInputChange = pointerInputChange2;
                break;
            }
            i11++;
        }
        PointerInputChange pointerInputChange3 = pointerInputChange;
        if (pointerInputChange3 != null) {
            if (!isInBounds) {
                z = false;
                this.isIn = false;
            } else {
                z = false;
                if (!this.isIn && (pointerInputChange3.getPressed() || pointerInputChange3.getPreviousPressed())) {
                    Intrinsics.checkNotNull(this.coordinates);
                    this.isIn = !PointerEventKt.m5718isOutOfBoundsO0kMr_c(pointerInputChange3, r3.mo5911getSizeYbymL2g());
                }
            }
            if (this.isIn != this.wasIn && (PointerEventType.m5723equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m5729getMove7fucELk()) || PointerEventType.m5723equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m5727getEnter7fucELk()) || PointerEventType.m5723equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m5728getExit7fucELk()))) {
                if (this.isIn) {
                    iM5728getExit7fucELk = PointerEventType.INSTANCE.m5727getEnter7fucELk();
                } else {
                    iM5728getExit7fucELk = PointerEventType.INSTANCE.m5728getExit7fucELk();
                }
                pointerEvent.m5717setTypeEhbLWgg$ui_release(iM5728getExit7fucELk);
            } else if (PointerEventType.m5723equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m5727getEnter7fucELk()) && this.wasIn && !this.hasExited) {
                pointerEvent.m5717setTypeEhbLWgg$ui_release(PointerEventType.INSTANCE.m5729getMove7fucELk());
            } else if (PointerEventType.m5723equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m5728getExit7fucELk()) && this.isIn && pointerInputChange3.getPressed()) {
                pointerEvent.m5717setTypeEhbLWgg$ui_release(PointerEventType.INSTANCE.m5729getMove7fucELk());
            }
        } else {
            z = false;
        }
        boolean z8 = (z6 || !PointerEventType.m5723equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m5729getMove7fucELk()) || hasPositionChanged(this.pointerEvent, pointerEvent)) ? z7 : z;
        this.pointerEvent = pointerEvent;
        return z8;
    }

    private final boolean hasPositionChanged(PointerEvent oldEvent, PointerEvent newEvent) {
        if (oldEvent == null || oldEvent.getChanges().size() != newEvent.getChanges().size()) {
            return true;
        }
        int size = newEvent.getChanges().size();
        for (int i = 0; i < size; i++) {
            if (!Offset.m4324equalsimpl0(oldEvent.getChanges().get(i).getPosition(), newEvent.getChanges().get(i).getPosition())) {
                return true;
            }
        }
        return false;
    }

    private final void clearCache() {
        this.relevantChanges.clear();
        this.coordinates = null;
    }

    private final boolean dispatchIfNeeded(Function0<Unit> block) {
        if (this.relevantChanges.isEmpty() || !this.modifierNode.getIsAttached()) {
            return false;
        }
        block.invoke();
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void dispatchCancel() {
        MutableVector<Node> children = getChildren();
        int size = children.getSize();
        if (size > 0) {
            Node[] content = children.getContent();
            int i = 0;
            do {
                content[i].dispatchCancel();
                i++;
            } while (i < size);
        }
        Modifier.Node nodePop = this.modifierNode;
        int iM6138constructorimpl = NodeKind.m6138constructorimpl(16);
        MutableVector mutableVector = null;
        while (nodePop != 0) {
            if (nodePop instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) nodePop).onCancelPointerInput();
            } else if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                int i2 = 0;
                nodePop = nodePop;
                while (delegate != null) {
                    if ((delegate.getKindSet() & iM6138constructorimpl) != 0) {
                        i2++;
                        if (i2 == 1) {
                            nodePop = delegate;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (nodePop != 0) {
                                if (mutableVector != null) {
                                    mutableVector.add(nodePop);
                                }
                                nodePop = 0;
                            }
                            if (mutableVector != null) {
                                mutableVector.add(delegate);
                            }
                        }
                    }
                    delegate = delegate.getChild();
                    nodePop = nodePop;
                }
                if (i2 == 1) {
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
        }
    }

    public final void markIsIn() {
        this.isIn = true;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        super.cleanUpHits(internalPointerEvent);
        PointerEvent pointerEvent = this.pointerEvent;
        if (pointerEvent == null) {
            return;
        }
        this.wasIn = this.isIn;
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            PointerInputChange pointerInputChange = changes.get(i);
            boolean pressed = pointerInputChange.getPressed();
            boolean zM5703activeHoverEvent0FcD4WY = internalPointerEvent.m5703activeHoverEvent0FcD4WY(pointerInputChange.getId());
            boolean z = this.isIn;
            if ((!pressed && !zM5703activeHoverEvent0FcD4WY) || (!pressed && !z)) {
                this.pointerIds.m5870remove0FcD4WY(pointerInputChange.getId());
            }
        }
        this.isIn = false;
        this.hasExited = PointerEventType.m5723equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m5728getExit7fucELk());
    }

    public String toString() {
        return "Node(pointerInputFilter=" + this.modifierNode + ", children=" + getChildren() + ", pointerIds=" + this.pointerIds + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchMainEventPass(LongSparseArray<PointerInputChange> changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        MutableVector<Node> children;
        int size;
        if (this.relevantChanges.isEmpty() || !this.modifierNode.getIsAttached()) {
            return false;
        }
        PointerEvent pointerEvent = this.pointerEvent;
        Intrinsics.checkNotNull(pointerEvent);
        LayoutCoordinates layoutCoordinates = this.coordinates;
        Intrinsics.checkNotNull(layoutCoordinates);
        long jMo5911getSizeYbymL2g = layoutCoordinates.mo5911getSizeYbymL2g();
        Modifier.Node nodePop = this.modifierNode;
        int iM6138constructorimpl = NodeKind.m6138constructorimpl(16);
        MutableVector mutableVector = null;
        while (nodePop != 0) {
            if (nodePop instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) nodePop).mo493onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Initial, jMo5911getSizeYbymL2g);
            } else if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                int i = 0;
                nodePop = nodePop;
                while (delegate != null) {
                    if ((delegate.getKindSet() & iM6138constructorimpl) != 0) {
                        i++;
                        if (i == 1) {
                            nodePop = delegate;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (nodePop != 0) {
                                if (mutableVector != null) {
                                    mutableVector.add(nodePop);
                                }
                                nodePop = 0;
                            }
                            if (mutableVector != null) {
                                mutableVector.add(delegate);
                            }
                        }
                    }
                    delegate = delegate.getChild();
                    nodePop = nodePop;
                }
                if (i == 1) {
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
        }
        if (this.modifierNode.getIsAttached() && (size = (children = getChildren()).getSize()) > 0) {
            Node[] content = children.getContent();
            int i2 = 0;
            do {
                Node node = content[i2];
                LongSparseArray<PointerInputChange> longSparseArray = this.relevantChanges;
                LayoutCoordinates layoutCoordinates2 = this.coordinates;
                Intrinsics.checkNotNull(layoutCoordinates2);
                node.dispatchMainEventPass(longSparseArray, layoutCoordinates2, internalPointerEvent, isInBounds);
                i2++;
            } while (i2 < size);
        }
        if (this.modifierNode.getIsAttached()) {
            Modifier.Node nodePop2 = this.modifierNode;
            int iM6138constructorimpl2 = NodeKind.m6138constructorimpl(16);
            MutableVector mutableVector2 = null;
            while (nodePop2 != 0) {
                if (nodePop2 instanceof PointerInputModifierNode) {
                    ((PointerInputModifierNode) nodePop2).mo493onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Main, jMo5911getSizeYbymL2g);
                } else if ((nodePop2.getKindSet() & iM6138constructorimpl2) != 0 && (nodePop2 instanceof DelegatingNode)) {
                    Modifier.Node delegate2 = ((DelegatingNode) nodePop2).getDelegate();
                    int i3 = 0;
                    nodePop2 = nodePop2;
                    while (delegate2 != null) {
                        if ((delegate2.getKindSet() & iM6138constructorimpl2) != 0) {
                            i3++;
                            if (i3 == 1) {
                                nodePop2 = delegate2;
                            } else {
                                if (mutableVector2 == null) {
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (nodePop2 != 0) {
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(nodePop2);
                                    }
                                    nodePop2 = 0;
                                }
                                if (mutableVector2 != null) {
                                    mutableVector2.add(delegate2);
                                }
                            }
                        }
                        delegate2 = delegate2.getChild();
                        nodePop2 = nodePop2;
                    }
                    if (i3 == 1) {
                    }
                }
                nodePop2 = DelegatableNodeKt.pop(mutableVector2);
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        MutableVector<Node> children;
        int size;
        boolean z = false;
        int i = 0;
        z = false;
        if (!this.relevantChanges.isEmpty() && this.modifierNode.getIsAttached()) {
            PointerEvent pointerEvent = this.pointerEvent;
            Intrinsics.checkNotNull(pointerEvent);
            LayoutCoordinates layoutCoordinates = this.coordinates;
            Intrinsics.checkNotNull(layoutCoordinates);
            long jMo5911getSizeYbymL2g = layoutCoordinates.mo5911getSizeYbymL2g();
            Modifier.Node nodePop = this.modifierNode;
            int iM6138constructorimpl = NodeKind.m6138constructorimpl(16);
            MutableVector mutableVector = null;
            while (nodePop != 0) {
                if (nodePop instanceof PointerInputModifierNode) {
                    ((PointerInputModifierNode) nodePop).mo493onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Final, jMo5911getSizeYbymL2g);
                } else if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                    Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                    int i2 = 0;
                    nodePop = nodePop;
                    while (delegate != null) {
                        if ((delegate.getKindSet() & iM6138constructorimpl) != 0) {
                            i2++;
                            if (i2 == 1) {
                                nodePop = delegate;
                            } else {
                                if (mutableVector == null) {
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (nodePop != 0) {
                                    if (mutableVector != null) {
                                        mutableVector.add(nodePop);
                                    }
                                    nodePop = 0;
                                }
                                if (mutableVector != null) {
                                    mutableVector.add(delegate);
                                }
                            }
                        }
                        delegate = delegate.getChild();
                        nodePop = nodePop;
                    }
                    if (i2 == 1) {
                    }
                }
                nodePop = DelegatableNodeKt.pop(mutableVector);
            }
            if (this.modifierNode.getIsAttached() && (size = (children = getChildren()).getSize()) > 0) {
                Node[] content = children.getContent();
                do {
                    content[i].dispatchFinalEventPass(internalPointerEvent);
                    i++;
                } while (i < size);
            }
            z = true;
        }
        cleanUpHits(internalPointerEvent);
        clearCache();
        return z;
    }
}
