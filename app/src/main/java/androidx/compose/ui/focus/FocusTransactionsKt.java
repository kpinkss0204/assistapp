package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusTransactions.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a \u0010\u0003\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u001e\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u001e\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001e\u0010\u000f\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000e\u001a\u001e\u0010\u0011\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000e\u001a\u001e\u0010\u0013\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u000e\u001a\f\u0010\u0015\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a \u0010\u0016\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0014\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0002\u001a\f\u0010\u001b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u001c\u001a\u00020\u0002*\u00020\u0002H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"captureFocus", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "clearChildFocus", "forced", "refreshFocusEvents", "clearFocus", "freeFocus", "grantFocus", "performCustomClearFocus", "Landroidx/compose/ui/focus/CustomDestinationResult;", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "performCustomClearFocus-Mxy_nc0", "(Landroidx/compose/ui/focus/FocusTargetNode;I)Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomEnter", "performCustomEnter-Mxy_nc0", "performCustomExit", "performCustomExit-Mxy_nc0", "performCustomRequestFocus", "performCustomRequestFocus-Mxy_nc0", "performRequestFocus", "requestFocus", "requestFocus-Mxy_nc0", "(Landroidx/compose/ui/focus/FocusTargetNode;I)Ljava/lang/Boolean;", "requestFocusForChild", "childNode", "requestFocusForOwner", "requireActiveChild", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FocusTransactionsKt {

    /* JADX INFO: compiled from: FocusTransactions.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final boolean requestFocus(FocusTargetNode focusTargetNode) {
        Boolean boolM4278requestFocusMxy_nc0 = m4278requestFocusMxy_nc0(focusTargetNode, FocusDirection.INSTANCE.m4246getEnterdhqQ8s());
        if (boolM4278requestFocusMxy_nc0 != null) {
            return boolM4278requestFocusMxy_nc0.booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: requestFocus-Mxy_nc0, reason: not valid java name */
    public static final Boolean m4278requestFocusMxy_nc0(final FocusTargetNode focusTargetNode, int i) {
        Boolean boolValueOf;
        FocusTransactionManager focusTransactionManagerRequireTransactionManager = FocusTargetNodeKt.requireTransactionManager(focusTargetNode);
        Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTransactionsKt.requestFocus.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (focusTargetNode.getNode().getIsAttached()) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
                }
            }
        };
        try {
            if (focusTransactionManagerRequireTransactionManager.ongoingTransaction) {
                focusTransactionManagerRequireTransactionManager.cancelTransaction();
            }
            focusTransactionManagerRequireTransactionManager.beginTransaction();
            focusTransactionManagerRequireTransactionManager.cancellationListener.add(function0);
            int i2 = WhenMappings.$EnumSwitchMapping$0[m4277performCustomRequestFocusMxy_nc0(focusTargetNode, i).ordinal()];
            if (i2 == 1) {
                boolValueOf = Boolean.valueOf(performRequestFocus(focusTargetNode));
            } else if (i2 != 2) {
                if (i2 != 3 && i2 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                boolValueOf = null;
            } else {
                boolValueOf = true;
            }
            return boolValueOf;
        } finally {
            focusTransactionManagerRequireTransactionManager.commitTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x0103  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean performRequestFocus(androidx.compose.ui.focus.FocusTargetNode r11) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTransactionsKt.performRequestFocus(androidx.compose.ui.focus.FocusTargetNode):boolean");
    }

    public static final boolean captureFocus(FocusTargetNode focusTargetNode) {
        FocusTransactionManager focusTransactionManagerRequireTransactionManager = FocusTargetNodeKt.requireTransactionManager(focusTargetNode);
        try {
            if (focusTransactionManagerRequireTransactionManager.ongoingTransaction) {
                focusTransactionManagerRequireTransactionManager.cancelTransaction();
            }
            focusTransactionManagerRequireTransactionManager.beginTransaction();
            int i = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
            boolean z = true;
            if (i == 1) {
                focusTargetNode.setFocusState(FocusStateImpl.Captured);
                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
            } else if (i != 2) {
                if (i != 3 && i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                z = false;
            }
            return z;
        } finally {
            focusTransactionManagerRequireTransactionManager.commitTransaction();
        }
    }

    public static final boolean freeFocus(FocusTargetNode focusTargetNode) {
        FocusTransactionManager focusTransactionManagerRequireTransactionManager = FocusTargetNodeKt.requireTransactionManager(focusTargetNode);
        try {
            if (focusTransactionManagerRequireTransactionManager.ongoingTransaction) {
                focusTransactionManagerRequireTransactionManager.cancelTransaction();
            }
            focusTransactionManagerRequireTransactionManager.beginTransaction();
            int i = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
            boolean z = true;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3 && i != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    z = false;
                } else {
                    focusTargetNode.setFocusState(FocusStateImpl.Active);
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
                }
            }
            return z;
        } finally {
            focusTransactionManagerRequireTransactionManager.commitTransaction();
        }
    }

    public static /* synthetic */ boolean clearFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return clearFocus(focusTargetNode, z, z2);
    }

    public static final boolean clearFocus(FocusTargetNode focusTargetNode, boolean z, boolean z2) {
        int i = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i == 1) {
            focusTargetNode.setFocusState(FocusStateImpl.Inactive);
            if (z2) {
                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
            }
            return true;
        }
        if (i == 2) {
            if (z) {
                focusTargetNode.setFocusState(FocusStateImpl.Inactive);
                if (z2) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
                }
            }
            return z;
        }
        if (i != 3) {
            if (i == 4) {
                return true;
            }
            throw new NoWhenBranchMatchedException();
        }
        if (!clearChildFocus(focusTargetNode, z, z2)) {
            return false;
        }
        focusTargetNode.setFocusState(FocusStateImpl.Inactive);
        if (z2) {
            FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
        }
        return true;
    }

    private static final boolean grantFocus(final FocusTargetNode focusTargetNode) {
        ObserverModifierNodeKt.observeReads(focusTargetNode, new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTransactionsKt.grantFocus.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                focusTargetNode.fetchFocusProperties$ui_release();
            }
        });
        int i = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i != 3 && i != 4) {
            return true;
        }
        focusTargetNode.setFocusState(FocusStateImpl.Active);
        return true;
    }

    static /* synthetic */ boolean clearChildFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return clearChildFocus(focusTargetNode, z, z2);
    }

    private static final boolean clearChildFocus(FocusTargetNode focusTargetNode, boolean z, boolean z2) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild != null) {
            return clearFocus(activeChild, z, z2);
        }
        return true;
    }

    private static final boolean requestFocusForChild(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2) {
        Modifier.Node node;
        Modifier.Node nodePop;
        NodeChain nodes;
        NodeChain nodes2;
        FocusTargetNode focusTargetNode3 = focusTargetNode2;
        int iM6138constructorimpl = NodeKind.m6138constructorimpl(1024);
        if (!focusTargetNode3.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node parent = focusTargetNode3.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode3);
        loop0: while (true) {
            node = null;
            if (layoutNodeRequireLayoutNode == null) {
                nodePop = null;
                break;
            }
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM6138constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM6138constructorimpl) != 0) {
                        nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                break loop0;
                            }
                            if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i = 0;
                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & iM6138constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate);
                                            }
                                        }
                                    }
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes2 = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes2.getTail();
        }
        if (!Intrinsics.areEqual(nodePop, focusTargetNode)) {
            throw new IllegalStateException("Non child node cannot request focus.".toString());
        }
        int i2 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i2 == 1) {
            boolean zGrantFocus = grantFocus(focusTargetNode2);
            if (zGrantFocus) {
                focusTargetNode.setFocusState(FocusStateImpl.ActiveParent);
            }
            return zGrantFocus;
        }
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 == 4) {
                    FocusTargetNode focusTargetNode4 = focusTargetNode;
                    int iM6138constructorimpl2 = NodeKind.m6138constructorimpl(1024);
                    if (!focusTargetNode4.getNode().getIsAttached()) {
                        throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                    }
                    Modifier.Node parent2 = focusTargetNode4.getNode().getParent();
                    LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(focusTargetNode4);
                    loop4: while (true) {
                        if (layoutNodeRequireLayoutNode2 == null) {
                            break;
                        }
                        if ((layoutNodeRequireLayoutNode2.getNodes().getHead().getAggregateChildKindSet() & iM6138constructorimpl2) != 0) {
                            while (parent2 != null) {
                                if ((parent2.getKindSet() & iM6138constructorimpl2) != 0) {
                                    Modifier.Node nodePop2 = parent2;
                                    MutableVector mutableVector2 = null;
                                    while (nodePop2 != null) {
                                        if (nodePop2 instanceof FocusTargetNode) {
                                            node = nodePop2;
                                            break loop4;
                                        }
                                        if ((nodePop2.getKindSet() & iM6138constructorimpl2) != 0 && (nodePop2 instanceof DelegatingNode)) {
                                            int i3 = 0;
                                            for (Modifier.Node delegate2 = ((DelegatingNode) nodePop2).getDelegate(); delegate2 != null; delegate2 = delegate2.getChild()) {
                                                if ((delegate2.getKindSet() & iM6138constructorimpl2) != 0) {
                                                    i3++;
                                                    if (i3 == 1) {
                                                        nodePop2 = delegate2;
                                                    } else {
                                                        if (mutableVector2 == null) {
                                                            mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                        }
                                                        if (nodePop2 != null) {
                                                            if (mutableVector2 != null) {
                                                                mutableVector2.add(nodePop2);
                                                            }
                                                            nodePop2 = null;
                                                        }
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(delegate2);
                                                        }
                                                    }
                                                }
                                            }
                                            if (i3 == 1) {
                                            }
                                        }
                                        nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                    }
                                }
                                parent2 = parent2.getParent();
                            }
                        }
                        layoutNodeRequireLayoutNode2 = layoutNodeRequireLayoutNode2.getParent$ui_release();
                        parent2 = (layoutNodeRequireLayoutNode2 == null || (nodes = layoutNodeRequireLayoutNode2.getNodes()) == null) ? null : nodes.getTail();
                    }
                    FocusTargetNode focusTargetNode5 = (FocusTargetNode) node;
                    if (focusTargetNode5 == null && requestFocusForOwner(focusTargetNode)) {
                        boolean zGrantFocus2 = grantFocus(focusTargetNode2);
                        if (zGrantFocus2) {
                            focusTargetNode.setFocusState(FocusStateImpl.ActiveParent);
                        }
                        return zGrantFocus2;
                    }
                    if (focusTargetNode5 == null || !requestFocusForChild(focusTargetNode5, focusTargetNode)) {
                        return false;
                    }
                    boolean zRequestFocusForChild = requestFocusForChild(focusTargetNode, focusTargetNode2);
                    if (focusTargetNode.getFocusState() != FocusStateImpl.ActiveParent) {
                        throw new IllegalStateException("Deactivated node is focused".toString());
                    }
                    if (zRequestFocusForChild) {
                        FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode5);
                    }
                    return zRequestFocusForChild;
                }
                throw new NoWhenBranchMatchedException();
            }
            requireActiveChild(focusTargetNode);
            if (clearChildFocus$default(focusTargetNode, false, false, 3, null) && grantFocus(focusTargetNode2)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean requestFocusForOwner(FocusTargetNode focusTargetNode) {
        return DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().mo4260requestFocusForOwner7o62pno(null, null);
    }

    private static final FocusTargetNode requireActiveChild(FocusTargetNode focusTargetNode) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild != null) {
            return activeChild;
        }
        throw new IllegalArgumentException("ActiveParent with no focused child".toString());
    }

    /* JADX INFO: renamed from: performCustomRequestFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m4277performCustomRequestFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        Modifier.Node nodePop;
        NodeChain nodes;
        int i2 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i2 == 1 || i2 == 2) {
            return CustomDestinationResult.None;
        }
        if (i2 == 3) {
            return m4274performCustomClearFocusMxy_nc0(requireActiveChild(focusTargetNode), i);
        }
        if (i2 == 4) {
            FocusTargetNode focusTargetNode2 = focusTargetNode;
            int iM6138constructorimpl = NodeKind.m6138constructorimpl(1024);
            if (!focusTargetNode2.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node parent = focusTargetNode2.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode2);
            loop0: while (true) {
                if (layoutNodeRequireLayoutNode == null) {
                    nodePop = null;
                    break;
                }
                if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM6138constructorimpl) != 0) {
                    while (parent != null) {
                        if ((parent.getKindSet() & iM6138constructorimpl) != 0) {
                            nodePop = parent;
                            MutableVector mutableVector = null;
                            while (nodePop != null) {
                                if (nodePop instanceof FocusTargetNode) {
                                    break loop0;
                                }
                                if ((nodePop.getKindSet() & iM6138constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i3 = 0;
                                    for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                        if ((delegate.getKindSet() & iM6138constructorimpl) != 0) {
                                            i3++;
                                            if (i3 == 1) {
                                                nodePop = delegate;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(nodePop);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(delegate);
                                                }
                                            }
                                        }
                                    }
                                    if (i3 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                        parent = parent.getParent();
                    }
                }
                layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
                parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
            }
            FocusTargetNode focusTargetNode3 = (FocusTargetNode) nodePop;
            if (focusTargetNode3 == null) {
                return CustomDestinationResult.None;
            }
            int i4 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode3.getFocusState().ordinal()];
            if (i4 == 1) {
                return m4275performCustomEnterMxy_nc0(focusTargetNode3, i);
            }
            if (i4 == 2) {
                return CustomDestinationResult.Cancelled;
            }
            if (i4 == 3) {
                return m4277performCustomRequestFocusMxy_nc0(focusTargetNode3, i);
            }
            if (i4 != 4) {
                throw new NoWhenBranchMatchedException();
            }
            CustomDestinationResult customDestinationResultM4277performCustomRequestFocusMxy_nc0 = m4277performCustomRequestFocusMxy_nc0(focusTargetNode3, i);
            CustomDestinationResult customDestinationResult = customDestinationResultM4277performCustomRequestFocusMxy_nc0 != CustomDestinationResult.None ? customDestinationResultM4277performCustomRequestFocusMxy_nc0 : null;
            return customDestinationResult == null ? m4275performCustomEnterMxy_nc0(focusTargetNode3, i) : customDestinationResult;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: performCustomClearFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m4274performCustomClearFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        int i2 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return CustomDestinationResult.Cancelled;
            }
            if (i2 == 3) {
                CustomDestinationResult customDestinationResultM4274performCustomClearFocusMxy_nc0 = m4274performCustomClearFocusMxy_nc0(requireActiveChild(focusTargetNode), i);
                if (customDestinationResultM4274performCustomClearFocusMxy_nc0 == CustomDestinationResult.None) {
                    customDestinationResultM4274performCustomClearFocusMxy_nc0 = null;
                }
                return customDestinationResultM4274performCustomClearFocusMxy_nc0 == null ? m4276performCustomExitMxy_nc0(focusTargetNode, i) : customDestinationResultM4274performCustomClearFocusMxy_nc0;
            }
            if (i2 != 4) {
                throw new NoWhenBranchMatchedException();
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomEnter-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m4275performCustomEnterMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        if (!focusTargetNode.isProcessingCustomEnter) {
            focusTargetNode.isProcessingCustomEnter = true;
            try {
                FocusRequester focusRequesterInvoke = focusTargetNode.fetchFocusProperties$ui_release().getEnter().invoke(FocusDirection.m4236boximpl(i));
                if (focusRequesterInvoke != FocusRequester.INSTANCE.getDefault()) {
                    if (focusRequesterInvoke == FocusRequester.INSTANCE.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    return focusRequesterInvoke.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
            } finally {
                focusTargetNode.isProcessingCustomEnter = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomExit-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m4276performCustomExitMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        if (!focusTargetNode.isProcessingCustomExit) {
            focusTargetNode.isProcessingCustomExit = true;
            try {
                FocusRequester focusRequesterInvoke = focusTargetNode.fetchFocusProperties$ui_release().getExit().invoke(FocusDirection.m4236boximpl(i));
                if (focusRequesterInvoke != FocusRequester.INSTANCE.getDefault()) {
                    if (focusRequesterInvoke == FocusRequester.INSTANCE.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    return focusRequesterInvoke.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
            } finally {
                focusTargetNode.isProcessingCustomExit = false;
            }
        }
        return CustomDestinationResult.None;
    }
}
