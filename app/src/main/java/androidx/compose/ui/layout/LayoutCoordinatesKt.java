package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: LayoutCoordinates.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0002*\u00020\u0002\u001a\u000f\u0010\u0006\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\t\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\n\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\u000b\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b¨\u0006\f"}, d2 = {"boundsInParent", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "boundsInRoot", "boundsInWindow", "findRootCoordinates", "positionInParent", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "positionInRoot", "positionInWindow", "positionOnScreen", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LayoutCoordinatesKt {
    public static final long positionInRoot(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo5914localToRootMKHz9U(Offset.INSTANCE.m4343getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo5916localToWindowMKHz9U(Offset.INSTANCE.m4343getZeroF1C5BW0());
    }

    public static final long positionOnScreen(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo5915localToScreenMKHz9U(Offset.INSTANCE.m4343getZeroF1C5BW0());
    }

    public static final Rect boundsInRoot(LayoutCoordinates layoutCoordinates) {
        return LayoutCoordinates.localBoundingBoxOf$default(findRootCoordinates(layoutCoordinates), layoutCoordinates, false, 2, null);
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = findRootCoordinates(layoutCoordinates);
        float fM7197getWidthimpl = IntSize.m7197getWidthimpl(layoutCoordinatesFindRootCoordinates.mo5911getSizeYbymL2g());
        float fM7196getHeightimpl = IntSize.m7196getHeightimpl(layoutCoordinatesFindRootCoordinates.mo5911getSizeYbymL2g());
        Rect rectBoundsInRoot = boundsInRoot(layoutCoordinates);
        float left = rectBoundsInRoot.getLeft();
        if (left < 0.0f) {
            left = 0.0f;
        }
        if (left > fM7197getWidthimpl) {
            left = fM7197getWidthimpl;
        }
        float top = rectBoundsInRoot.getTop();
        if (top < 0.0f) {
            top = 0.0f;
        }
        if (top > fM7196getHeightimpl) {
            top = fM7196getHeightimpl;
        }
        float right = rectBoundsInRoot.getRight();
        if (right < 0.0f) {
            right = 0.0f;
        }
        if (right <= fM7197getWidthimpl) {
            fM7197getWidthimpl = right;
        }
        float bottom = rectBoundsInRoot.getBottom();
        float f = bottom >= 0.0f ? bottom : 0.0f;
        if (f <= fM7196getHeightimpl) {
            fM7196getHeightimpl = f;
        }
        if (left == fM7197getWidthimpl || top == fM7196getHeightimpl) {
            return Rect.INSTANCE.getZero();
        }
        long jMo5916localToWindowMKHz9U = layoutCoordinatesFindRootCoordinates.mo5916localToWindowMKHz9U(OffsetKt.Offset(left, top));
        long jMo5916localToWindowMKHz9U2 = layoutCoordinatesFindRootCoordinates.mo5916localToWindowMKHz9U(OffsetKt.Offset(fM7197getWidthimpl, top));
        long jMo5916localToWindowMKHz9U3 = layoutCoordinatesFindRootCoordinates.mo5916localToWindowMKHz9U(OffsetKt.Offset(fM7197getWidthimpl, fM7196getHeightimpl));
        long jMo5916localToWindowMKHz9U4 = layoutCoordinatesFindRootCoordinates.mo5916localToWindowMKHz9U(OffsetKt.Offset(left, fM7196getHeightimpl));
        float fM4327getXimpl = Offset.m4327getXimpl(jMo5916localToWindowMKHz9U);
        float fM4327getXimpl2 = Offset.m4327getXimpl(jMo5916localToWindowMKHz9U2);
        float fM4327getXimpl3 = Offset.m4327getXimpl(jMo5916localToWindowMKHz9U4);
        float fM4327getXimpl4 = Offset.m4327getXimpl(jMo5916localToWindowMKHz9U3);
        float fMin = Math.min(fM4327getXimpl, Math.min(fM4327getXimpl2, Math.min(fM4327getXimpl3, fM4327getXimpl4)));
        float fMax = Math.max(fM4327getXimpl, Math.max(fM4327getXimpl2, Math.max(fM4327getXimpl3, fM4327getXimpl4)));
        float fM4328getYimpl = Offset.m4328getYimpl(jMo5916localToWindowMKHz9U);
        float fM4328getYimpl2 = Offset.m4328getYimpl(jMo5916localToWindowMKHz9U2);
        float fM4328getYimpl3 = Offset.m4328getYimpl(jMo5916localToWindowMKHz9U4);
        float fM4328getYimpl4 = Offset.m4328getYimpl(jMo5916localToWindowMKHz9U3);
        return new Rect(fMin, Math.min(fM4328getYimpl, Math.min(fM4328getYimpl2, Math.min(fM4328getYimpl3, fM4328getYimpl4))), fMax, Math.max(fM4328getYimpl, Math.max(fM4328getYimpl2, Math.max(fM4328getYimpl3, fM4328getYimpl4))));
    }

    public static final long positionInParent(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return parentLayoutCoordinates != null ? parentLayoutCoordinates.mo5912localPositionOfR5De75A(layoutCoordinates, Offset.INSTANCE.m4343getZeroF1C5BW0()) : Offset.INSTANCE.m4343getZeroF1C5BW0();
    }

    public static final Rect boundsInParent(LayoutCoordinates layoutCoordinates) {
        Rect rectLocalBoundingBoxOf$default;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return (parentLayoutCoordinates == null || (rectLocalBoundingBoxOf$default = LayoutCoordinates.localBoundingBoxOf$default(parentLayoutCoordinates, layoutCoordinates, false, 2, null)) == null) ? new Rect(0.0f, 0.0f, IntSize.m7197getWidthimpl(layoutCoordinates.mo5911getSizeYbymL2g()), IntSize.m7196getHeightimpl(layoutCoordinates.mo5911getSizeYbymL2g())) : rectLocalBoundingBoxOf$default;
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates3 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates3;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        NodeCoordinator nodeCoordinator = layoutCoordinates2 instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinates2 : null;
        if (nodeCoordinator == null) {
            return layoutCoordinates2;
        }
        NodeCoordinator wrappedBy = nodeCoordinator.getWrappedBy();
        while (true) {
            NodeCoordinator nodeCoordinator2 = wrappedBy;
            NodeCoordinator nodeCoordinator3 = nodeCoordinator;
            nodeCoordinator = nodeCoordinator2;
            if (nodeCoordinator != null) {
                wrappedBy = nodeCoordinator.getWrappedBy();
            } else {
                return nodeCoordinator3;
            }
        }
    }
}
