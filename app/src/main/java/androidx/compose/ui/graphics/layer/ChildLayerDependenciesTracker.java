package androidx.compose.ui.graphics.layer;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.graphics.InlineClassHelperKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChildLayerDependenciesTracker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0005J\u001d\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\u0010H\u0086\bJ+\u0010\u0011\u001a\u00020\u000e2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\u00102\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013H\u0086\bR\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/graphics/layer/ChildLayerDependenciesTracker;", "", "()V", "dependenciesSet", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "dependency", "oldDependenciesSet", "oldDependency", "trackingInProgress", "", "onDependencyAdded", "graphicsLayer", "removeDependencies", "", "block", "Lkotlin/Function1;", "withTracking", "onDependencyRemoved", "Lkotlin/Function0;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ChildLayerDependenciesTracker {
    private MutableScatterSet<GraphicsLayer> dependenciesSet;
    private GraphicsLayer dependency;
    private MutableScatterSet<GraphicsLayer> oldDependenciesSet;
    private GraphicsLayer oldDependency;
    private boolean trackingInProgress;

    /* JADX WARN: Removed duplicated region for block: B:29:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void withTracking(kotlin.jvm.functions.Function1<? super androidx.compose.ui.graphics.layer.GraphicsLayer, kotlin.Unit> r18, kotlin.jvm.functions.Function0<kotlin.Unit> r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            androidx.compose.ui.graphics.layer.GraphicsLayer r2 = access$getDependency$p(r0)
            access$setOldDependency$p(r0, r2)
            androidx.collection.MutableScatterSet r2 = access$getDependenciesSet$p(r0)
            if (r2 == 0) goto L2d
            boolean r3 = r2.isNotEmpty()
            if (r3 == 0) goto L2d
            androidx.collection.MutableScatterSet r3 = access$getOldDependenciesSet$p(r0)
            if (r3 != 0) goto L24
            androidx.collection.MutableScatterSet r3 = androidx.collection.ScatterSetKt.mutableScatterSetOf()
            access$setOldDependenciesSet$p(r0, r3)
        L24:
            r4 = r2
            androidx.collection.ScatterSet r4 = (androidx.collection.ScatterSet) r4
            r3.addAll(r4)
            r2.clear()
        L2d:
            r2 = 1
            access$setTrackingInProgress$p(r0, r2)
            r19.invoke()
            r2 = 0
            access$setTrackingInProgress$p(r0, r2)
            androidx.compose.ui.graphics.layer.GraphicsLayer r3 = access$getOldDependency$p(r0)
            if (r3 == 0) goto L41
            r1.invoke(r3)
        L41:
            androidx.collection.MutableScatterSet r3 = access$getOldDependenciesSet$p(r0)
            if (r3 == 0) goto L95
            boolean r4 = r3.isNotEmpty()
            if (r4 == 0) goto L95
            r4 = r3
            androidx.collection.ScatterSet r4 = (androidx.collection.ScatterSet) r4
            java.lang.Object[] r5 = r4.elements
            long[] r4 = r4.metadata
            int r6 = r4.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L92
            r7 = r2
        L5a:
            r8 = r4[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L8d
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r2
        L74:
            if (r12 >= r10) goto L8b
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L87
            int r13 = r7 << 3
            int r13 = r13 + r12
            r13 = r5[r13]
            r1.invoke(r13)
        L87:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L74
        L8b:
            if (r10 != r11) goto L92
        L8d:
            if (r7 == r6) goto L92
            int r7 = r7 + 1
            goto L5a
        L92:
            r3.clear()
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.withTracking(kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0):void");
    }

    public final boolean onDependencyAdded(GraphicsLayer graphicsLayer) {
        if (!this.trackingInProgress) {
            InlineClassHelperKt.throwIllegalArgumentException("Only add dependencies during a tracking");
        }
        MutableScatterSet<GraphicsLayer> mutableScatterSet = this.dependenciesSet;
        if (mutableScatterSet != null) {
            Intrinsics.checkNotNull(mutableScatterSet);
            mutableScatterSet.add(graphicsLayer);
        } else if (this.dependency != null) {
            MutableScatterSet<GraphicsLayer> mutableScatterSetMutableScatterSetOf = ScatterSetKt.mutableScatterSetOf();
            GraphicsLayer graphicsLayer2 = this.dependency;
            Intrinsics.checkNotNull(graphicsLayer2);
            mutableScatterSetMutableScatterSetOf.add(graphicsLayer2);
            mutableScatterSetMutableScatterSetOf.add(graphicsLayer);
            this.dependenciesSet = mutableScatterSetMutableScatterSetOf;
            this.dependency = null;
        } else {
            this.dependency = graphicsLayer;
        }
        MutableScatterSet<GraphicsLayer> mutableScatterSet2 = this.oldDependenciesSet;
        if (mutableScatterSet2 != null) {
            Intrinsics.checkNotNull(mutableScatterSet2);
            return !mutableScatterSet2.remove(graphicsLayer);
        }
        if (this.oldDependency != graphicsLayer) {
            return true;
        }
        this.oldDependency = null;
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void removeDependencies(kotlin.jvm.functions.Function1<? super androidx.compose.ui.graphics.layer.GraphicsLayer, kotlin.Unit> r18) {
        /*
            r17 = this;
            r0 = r18
            androidx.compose.ui.graphics.layer.GraphicsLayer r1 = access$getDependency$p(r17)
            if (r1 == 0) goto L12
            r0.invoke(r1)
            r1 = 0
            r2 = r17
            access$setDependency$p(r2, r1)
            goto L14
        L12:
            r2 = r17
        L14:
            androidx.collection.MutableScatterSet r1 = access$getDependenciesSet$p(r2)
            if (r1 == 0) goto L63
            r3 = r1
            androidx.collection.ScatterSet r3 = (androidx.collection.ScatterSet) r3
            java.lang.Object[] r4 = r3.elements
            long[] r3 = r3.metadata
            int r5 = r3.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L60
            r6 = 0
            r7 = r6
        L28:
            r8 = r3[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L5b
            int r10 = r7 - r5
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r6
        L42:
            if (r12 >= r10) goto L59
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L55
            int r13 = r7 << 3
            int r13 = r13 + r12
            r13 = r4[r13]
            r0.invoke(r13)
        L55:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L42
        L59:
            if (r10 != r11) goto L60
        L5b:
            if (r7 == r5) goto L60
            int r7 = r7 + 1
            goto L28
        L60:
            r1.clear()
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.removeDependencies(kotlin.jvm.functions.Function1):void");
    }
}
