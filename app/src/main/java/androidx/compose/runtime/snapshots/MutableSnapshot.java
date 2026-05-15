package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.snapshots.tooling.SnapshotInstanceObservers;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserver;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserverKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.strategy.Name;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0014\b\u0017\u0018\u0000 l2\u00020\u0001:\u0001lBI\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J8\u0010\u0013\u001a\u00020\u00002\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\u001e\u0010\u001d\u001a\u00020\u00012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016J\u0015\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b J\u0015\u0010!\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b\"J\r\u0010#\u001a\u00020\nH\u0010¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\nH\u0010¢\u0006\u0002\b&J\r\u0010'\u001a\u00020\nH\u0010¢\u0006\u0002\b(J\b\u0010)\u001a\u00020\nH\u0002J\b\u0010*\u001a\u00020\nH\u0002J\b\u0010+\u001a\u00020\nH\u0002JG\u0010,\u001a\u00020\u00152\n\u0010-\u001a\u00060\u0003j\u0002`\u00042\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0014\u00101\u001a\u0010\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000203\u0018\u0001022\u0006\u00104\u001a\u00020\u0006H\u0000¢\u0006\u0004\b5\u00106J$\u00107\u001a\u0002H8\"\u0004\b\u0000\u001082\f\u00109\u001a\b\u0012\u0004\u0012\u0002H80:H\u0080\b¢\u0006\u0004\b;\u0010<J\r\u00107\u001a\u00020\nH\u0000¢\u0006\u0002\b;J\u001b\u0010=\u001a\u00020\n2\n\u0010>\u001a\u00060\u0003j\u0002`\u0004H\u0000¢\u0006\u0004\b?\u0010@J\u0015\u0010A\u001a\u00020\n2\u0006\u0010>\u001a\u00020BH\u0000¢\u0006\u0002\bCJ\u0015\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020FH\u0000¢\u0006\u0002\bGJ\b\u0010H\u001a\u00020\nH\u0002J\u0015\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020\u0006H\u0000¢\u0006\u0002\bKJ\u0015\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u000200H\u0010¢\u0006\u0002\bNR\"\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010O\u001a\u00020BX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\"\u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\"\u0010X\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010YX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001a\u0010^\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u001a\u0010c\u001a\u00020FX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u000e\u0010J\u001a\u00020BX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010h\u001a\u00020\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u0018\"\u0004\bj\u0010k¨\u0006m"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/Snapshot;", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "readObserver", "Lkotlin/Function1;", "", "", "writeObserver", "<init>", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getReadObserver$runtime", "()Lkotlin/jvm/functions/Function1;", "getWriteObserver$runtime", "hasPendingChanges", "", "takeNestedMutableSnapshot", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "readOnly", "getReadOnly", "()Z", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "dispose", "takeNestedSnapshot", "nestedActivated", "snapshot", "nestedActivated$runtime", "nestedDeactivated", "nestedDeactivated$runtime", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime", "closeLocked", "closeLocked$runtime", "releasePinnedSnapshotsForCloseLocked", "releasePinnedSnapshotsForCloseLocked$runtime", "validateNotApplied", "validateNotAppliedOrPinned", "abandon", "innerApplyLocked", "nextId", "modified", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/runtime/snapshots/StateObject;", "optimisticMerges", "", "Landroidx/compose/runtime/snapshots/StateRecord;", "invalidSnapshots", "innerApplyLocked$runtime", "(JLandroidx/collection/MutableScatterSet;Ljava/util/Map;Landroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "advance", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "advance$runtime", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "recordPrevious", Name.MARK, "recordPrevious$runtime", "(J)V", "recordPreviousPinnedSnapshot", "", "recordPreviousPinnedSnapshot$runtime", "recordPreviousPinnedSnapshots", "handles", "", "recordPreviousPinnedSnapshots$runtime", "releasePreviouslyPinnedSnapshotsLocked", "recordPreviousList", "snapshots", "recordPreviousList$runtime", "recordModified", "state", "recordModified$runtime", "writeCount", "getWriteCount$runtime", "()I", "setWriteCount$runtime", "(I)V", "getModified$runtime", "()Landroidx/collection/MutableScatterSet;", "setModified$runtime", "(Landroidx/collection/MutableScatterSet;)V", "merged", "", "getMerged$runtime", "()Ljava/util/List;", "setMerged$runtime", "(Ljava/util/List;)V", "previousIds", "getPreviousIds$runtime", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setPreviousIds$runtime", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "previousPinnedSnapshots", "getPreviousPinnedSnapshots$runtime", "()[I", "setPreviousPinnedSnapshots$runtime", "([I)V", "applied", "getApplied$runtime", "setApplied$runtime", "(Z)V", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public class MutableSnapshot extends Snapshot {
    private boolean applied;
    private List<? extends StateObject> merged;
    private MutableScatterSet<StateObject> modified;
    private SnapshotIdSet previousIds;
    private int[] previousPinnedSnapshots;
    private final Function1<Object, Unit> readObserver;
    private int snapshots;
    private int writeCount;
    private final Function1<Object, Unit> writeObserver;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final int[] EmptyIntArray = new int[0];

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime */
    public Function1<Object, Unit> getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1<Object, Unit> getWriteObserver$runtime() {
        return this.writeObserver;
    }

    public MutableSnapshot(long j, SnapshotIdSet snapshotIdSet, Function1<Object, Unit> function1, Function1<Object, Unit> function12) {
        super(j, snapshotIdSet, (DefaultConstructorMarker) null);
        this.readObserver = function1;
        this.writeObserver = function12;
        this.previousIds = SnapshotIdSet.INSTANCE.getEMPTY();
        this.previousPinnedSnapshots = EmptyIntArray;
        this.snapshots = 1;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        return modified$runtime != null && modified$runtime.isNotEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MutableSnapshot takeNestedMutableSnapshot$default(MutableSnapshot mutableSnapshot, Function1 function1, Function1 function12, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: takeNestedMutableSnapshot");
        }
        if ((i & 1) != 0) {
            function1 = null;
        }
        if ((i & 2) != 0) {
            function12 = null;
        }
        return mutableSnapshot.takeNestedMutableSnapshot(function1, function12);
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
        Function1<Object, Unit> function1;
        Function1<Object, Unit> writeObserver2;
        Map<SnapshotObserver, SnapshotInstanceObservers> second;
        long j;
        NestedMutableSnapshot nestedMutableSnapshot;
        validateNotDisposed$runtime();
        validateNotAppliedOrPinned();
        PersistentList persistentList = SnapshotObserverKt.observers;
        if (persistentList != null) {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(persistentList, this, false, readObserver, writeObserver);
            SnapshotInstanceObservers first = pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = first.getReadObserver();
            writeObserver2 = first.getWriteObserver();
            function1 = readObserver2;
            second = pairMergeObservers.getSecond();
        } else {
            function1 = readObserver;
            writeObserver2 = writeObserver;
            second = null;
        }
        recordPrevious$runtime(getSnapshotId());
        synchronized (SnapshotKt.getLock()) {
            long j2 = SnapshotKt.nextSnapshotId;
            j = 1;
            SnapshotKt.nextSnapshotId += j;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(j2);
            SnapshotIdSet invalid$runtime = getInvalid();
            setInvalid$runtime(invalid$runtime.set(j2));
            nestedMutableSnapshot = new NestedMutableSnapshot(j2, SnapshotKt.addRange(invalid$runtime, getSnapshotId() + j, j2), SnapshotKt.mergedReadObserver$default(function1, getReadObserver(), false, 4, null), SnapshotKt.mergedWriteObserver(writeObserver2, getWriteObserver$runtime()), this);
        }
        if (!getApplied() && !getDisposed()) {
            long snapshotId = getSnapshotId();
            synchronized (SnapshotKt.getLock()) {
                long j3 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId += j;
                setSnapshotId$runtime(j3);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                Unit unit = Unit.INSTANCE;
            }
            setInvalid$runtime(SnapshotKt.addRange(getInvalid(), snapshotId + j, getSnapshotId()));
        }
        NestedMutableSnapshot nestedMutableSnapshot2 = nestedMutableSnapshot;
        if (persistentList != null) {
            SnapshotObserverKt.dispatchCreatedObservers(persistentList, this, nestedMutableSnapshot2, second);
        }
        return nestedMutableSnapshot2;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0187  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.runtime.snapshots.SnapshotApplyResult apply() {
        /*
            Method dump skipped, instruction units count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.apply():androidx.compose.runtime.snapshots.SnapshotApplyResult");
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        super.dispose();
        MutableSnapshot mutableSnapshot = this;
        mo4150nestedDeactivated$runtime(mutableSnapshot);
        SnapshotObserverKt.dispatchObserverOnPreDispose(mutableSnapshot);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1<Object, Unit> readObserver) {
        Map<SnapshotObserver, SnapshotInstanceObservers> second;
        long j;
        NestedReadonlySnapshot nestedReadonlySnapshot;
        validateNotDisposed$runtime();
        validateNotAppliedOrPinned();
        long snapshotId = getSnapshotId();
        MutableSnapshot mutableSnapshot = this instanceof GlobalSnapshot ? null : this;
        PersistentList persistentList = SnapshotObserverKt.observers;
        Function1<Object, Unit> function1 = readObserver;
        if (persistentList != null) {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(persistentList, mutableSnapshot, true, function1, null);
            SnapshotInstanceObservers first = pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = first.getReadObserver();
            first.getWriteObserver();
            second = pairMergeObservers.getSecond();
            function1 = readObserver2;
        } else {
            second = null;
        }
        recordPrevious$runtime(getSnapshotId());
        synchronized (SnapshotKt.getLock()) {
            long j2 = SnapshotKt.nextSnapshotId;
            j = 1;
            SnapshotKt.nextSnapshotId += j;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(j2);
            nestedReadonlySnapshot = new NestedReadonlySnapshot(j2, SnapshotKt.addRange(getInvalid(), snapshotId + j, j2), SnapshotKt.mergedReadObserver$default(function1, getReadObserver(), false, 4, null), this);
        }
        if (!getApplied() && !getDisposed()) {
            long snapshotId2 = getSnapshotId();
            synchronized (SnapshotKt.getLock()) {
                long j3 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId += j;
                setSnapshotId$runtime(j3);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                Unit unit = Unit.INSTANCE;
            }
            setInvalid$runtime(SnapshotKt.addRange(getInvalid(), snapshotId2 + j, getSnapshotId()));
        }
        NestedReadonlySnapshot nestedReadonlySnapshot2 = nestedReadonlySnapshot;
        if (persistentList != null) {
            SnapshotObserverKt.dispatchCreatedObservers(persistentList, mutableSnapshot, nestedReadonlySnapshot2, second);
        }
        return nestedReadonlySnapshot2;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime */
    public void mo4149nestedActivated$runtime(Snapshot snapshot) {
        this.snapshots++;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime */
    public void mo4150nestedDeactivated$runtime(Snapshot snapshot) {
        if (!(this.snapshots > 0)) {
            PreconditionsKt.throwIllegalArgumentException("no pending nested snapshots");
        }
        int i = this.snapshots - 1;
        this.snapshots = i;
        if (i != 0 || this.applied) {
            return;
        }
        abandon();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime() {
        if (this.applied || getDisposed()) {
            return;
        }
        advance$runtime();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void closeLocked$runtime() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getSnapshotId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void releasePinnedSnapshotsForCloseLocked$runtime() {
        releasePreviouslyPinnedSnapshotsLocked();
        super.releasePinnedSnapshotsForCloseLocked$runtime();
    }

    private final void validateNotApplied() {
        if (this.applied) {
            PreconditionsKt.throwIllegalStateException("Unsupported operation on a snapshot that has been applied");
        }
    }

    private final void validateNotAppliedOrPinned() {
        if (!this.applied || ((Snapshot) this).pinningTrackingHandle >= 0) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unsupported operation on a disposed or applied snapshot");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void abandon() {
        /*
            r17 = this;
            r0 = r17
            androidx.collection.MutableScatterSet r1 = r0.getModified$runtime()
            if (r1 == 0) goto L83
            r0.validateNotApplied()
            r2 = 0
            r0.setModified$runtime(r2)
            long r2 = r0.getSnapshotId()
            androidx.collection.ScatterSet r1 = (androidx.collection.ScatterSet) r1
            java.lang.Object[] r4 = r1.elements
            long[] r1 = r1.metadata
            int r5 = r1.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L83
            r6 = 0
            r7 = r6
        L20:
            r8 = r1[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L7e
            int r10 = r7 - r5
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r6
        L3a:
            if (r12 >= r10) goto L7c
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L78
            int r13 = r7 << 3
            int r13 = r13 + r12
            r13 = r4[r13]
            androidx.compose.runtime.snapshots.StateObject r13 = (androidx.compose.runtime.snapshots.StateObject) r13
            androidx.compose.runtime.snapshots.StateRecord r13 = r13.getFirstStateRecord()
        L50:
            if (r13 == 0) goto L78
            long r14 = r13.getSnapshotId()
            int r14 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r14 == 0) goto L6c
            androidx.compose.runtime.snapshots.SnapshotIdSet r14 = r0.previousIds
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            long r15 = r13.getSnapshotId()
            java.lang.Long r15 = java.lang.Long.valueOf(r15)
            boolean r14 = kotlin.collections.CollectionsKt.contains(r14, r15)
            if (r14 == 0) goto L73
        L6c:
            long r14 = androidx.compose.runtime.snapshots.SnapshotKt.access$getINVALID_SNAPSHOT$p()
            r13.setSnapshotId$runtime(r14)
        L73:
            androidx.compose.runtime.snapshots.StateRecord r13 = r13.getNext()
            goto L50
        L78:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L3a
        L7c:
            if (r10 != r11) goto L83
        L7e:
            if (r7 == r5) goto L83
            int r7 = r7 + 1
            goto L20
        L83:
            r0.closeAndReleasePinning$runtime()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.abandon():void");
    }

    public final SnapshotApplyResult innerApplyLocked$runtime(long nextId, MutableScatterSet<StateObject> modified, Map<StateRecord, ? extends StateRecord> optimisticMerges, SnapshotIdSet invalidSnapshots) {
        SnapshotIdSet snapshotIdSet;
        long[] jArr;
        Object[] objArr;
        SnapshotIdSet snapshotIdSet2;
        long[] jArr2;
        Object[] objArr2;
        int i;
        long j;
        int i2;
        StateRecord stateRecordMergeRecords;
        SnapshotIdSet snapshotIdSetOr = getInvalid().set(getSnapshotId()).or(this.previousIds);
        MutableScatterSet<StateObject> mutableScatterSet = modified;
        Object[] objArr3 = mutableScatterSet.elements;
        long[] jArr3 = mutableScatterSet.metadata;
        int length = jArr3.length - 2;
        ArrayList arrayList = null;
        List<? extends StateObject> listPlus = null;
        if (length >= 0) {
            int i3 = 0;
            while (true) {
                long j2 = jArr3[i3];
                ArrayList arrayList2 = listPlus;
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8;
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    int i6 = 0;
                    while (i6 < i5) {
                        if ((j2 & 255) < 128) {
                            i = i4;
                            StateObject stateObject = (StateObject) objArr3[(i3 << 3) + i6];
                            jArr2 = jArr3;
                            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                            objArr2 = objArr3;
                            ArrayList arrayList3 = arrayList;
                            StateRecord stateRecord = SnapshotKt.readable(firstStateRecord, nextId, invalidSnapshots);
                            if (stateRecord == null) {
                                j = j2;
                            } else {
                                j = j2;
                                StateRecord stateRecord2 = SnapshotKt.readable(firstStateRecord, getSnapshotId(), snapshotIdSetOr);
                                if (stateRecord2 != null && stateRecord2.getSnapshotId() != SnapshotId_jvmKt.toSnapshotId(1) && !Intrinsics.areEqual(stateRecord, stateRecord2)) {
                                    i2 = i6;
                                    snapshotIdSet2 = snapshotIdSetOr;
                                    StateRecord stateRecord3 = SnapshotKt.readable(firstStateRecord, getSnapshotId(), getInvalid());
                                    if (stateRecord3 == null) {
                                        SnapshotKt.readError();
                                        throw new KotlinNothingValueException();
                                    }
                                    if (optimisticMerges == null || (stateRecordMergeRecords = optimisticMerges.get(stateRecord)) == null) {
                                        stateRecordMergeRecords = stateObject.mergeRecords(stateRecord2, stateRecord, stateRecord3);
                                    }
                                    if (stateRecordMergeRecords == null) {
                                        return new SnapshotApplyResult.Failure(this);
                                    }
                                    if (!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord3)) {
                                        if (Intrinsics.areEqual(stateRecordMergeRecords, stateRecord)) {
                                            ArrayList arrayList4 = arrayList3 == null ? new ArrayList() : arrayList3;
                                            arrayList4.add(TuplesKt.to(stateObject, stateRecord.create(getSnapshotId())));
                                            if (arrayList2 == null) {
                                                arrayList2 = new ArrayList();
                                            }
                                            List<? extends StateObject> list = arrayList2;
                                            list.add(stateObject);
                                            arrayList = arrayList4;
                                            arrayList2 = list;
                                        } else {
                                            arrayList = arrayList3 == null ? new ArrayList() : arrayList3;
                                            arrayList.add(!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord2) ? TuplesKt.to(stateObject, stateRecordMergeRecords) : TuplesKt.to(stateObject, stateRecord2.create(getSnapshotId())));
                                        }
                                    }
                                }
                                arrayList = arrayList3;
                            }
                            snapshotIdSet2 = snapshotIdSetOr;
                            i2 = i6;
                            arrayList = arrayList3;
                        } else {
                            snapshotIdSet2 = snapshotIdSetOr;
                            jArr2 = jArr3;
                            objArr2 = objArr3;
                            i = i4;
                            j = j2;
                            i2 = i6;
                        }
                        j2 = j >> i;
                        i6 = i2 + 1;
                        jArr3 = jArr2;
                        i4 = i;
                        objArr3 = objArr2;
                        snapshotIdSetOr = snapshotIdSet2;
                    }
                    snapshotIdSet = snapshotIdSetOr;
                    jArr = jArr3;
                    objArr = objArr3;
                    ArrayList arrayList5 = arrayList;
                    if (i5 != i4) {
                        listPlus = arrayList2;
                        arrayList = arrayList5;
                        break;
                    }
                    arrayList = arrayList5;
                } else {
                    snapshotIdSet = snapshotIdSetOr;
                    jArr = jArr3;
                    objArr = objArr3;
                }
                listPlus = arrayList2;
                if (i3 == length) {
                    break;
                }
                i3++;
                jArr3 = jArr;
                objArr3 = objArr;
                snapshotIdSetOr = snapshotIdSet;
            }
        }
        if (arrayList != null) {
            advance$runtime();
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                Pair pair = (Pair) arrayList.get(i7);
                StateObject stateObject2 = (StateObject) pair.component1();
                StateRecord stateRecord4 = (StateRecord) pair.component2();
                stateRecord4.setSnapshotId$runtime(nextId);
                synchronized (SnapshotKt.getLock()) {
                    stateRecord4.setNext$runtime(stateObject2.getFirstStateRecord());
                    stateObject2.prependStateRecord(stateRecord4);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        if (listPlus != null) {
            int size2 = listPlus.size();
            for (int i8 = 0; i8 < size2; i8++) {
                modified.remove(listPlus.get(i8));
            }
            List<? extends StateObject> list2 = this.merged;
            if (list2 != null) {
                listPlus = CollectionsKt.plus((Collection) list2, (Iterable) listPlus);
            }
            this.merged = listPlus;
        }
        return SnapshotApplyResult.Success.INSTANCE;
    }

    public final <T> T advance$runtime(Function0<? extends T> block) {
        long j;
        recordPrevious$runtime(getSnapshotId());
        T tInvoke = block.invoke();
        if (getApplied() || getDisposed()) {
            return tInvoke;
        }
        long snapshotId = getSnapshotId();
        synchronized (SnapshotKt.getLock()) {
            long j2 = SnapshotKt.nextSnapshotId;
            j = 1;
            SnapshotKt.nextSnapshotId += j;
            setSnapshotId$runtime(j2);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
            Unit unit = Unit.INSTANCE;
        }
        setInvalid$runtime(SnapshotKt.addRange(getInvalid(), snapshotId + j, getSnapshotId()));
        return tInvoke;
    }

    public final void recordPreviousPinnedSnapshot$runtime(int id) {
        if (id >= 0) {
            this.previousPinnedSnapshots = ArraysKt.plus(this.previousPinnedSnapshots, id);
        }
    }

    public final void recordPreviousPinnedSnapshots$runtime(int[] handles) {
        if (handles.length == 0) {
            return;
        }
        int[] iArr = this.previousPinnedSnapshots;
        if (iArr.length != 0) {
            handles = ArraysKt.plus(iArr, handles);
        }
        this.previousPinnedSnapshots = handles;
    }

    private final void releasePreviouslyPinnedSnapshotsLocked() {
        int length = this.previousPinnedSnapshots.length;
        for (int i = 0; i < length; i++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[i]);
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime */
    public void mo4151recordModified$runtime(StateObject state) {
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        if (modified$runtime == null) {
            modified$runtime = ScatterSetKt.mutableScatterSetOf();
            setModified$runtime(modified$runtime);
        }
        modified$runtime.add(state);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getWriteCount$runtime, reason: from getter */
    public int getWriteCount() {
        return this.writeCount;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setWriteCount$runtime(int i) {
        this.writeCount = i;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public MutableScatterSet<StateObject> getModified$runtime() {
        return this.modified;
    }

    public void setModified$runtime(MutableScatterSet<StateObject> mutableScatterSet) {
        this.modified = mutableScatterSet;
    }

    public final List<StateObject> getMerged$runtime() {
        return this.merged;
    }

    public final void setMerged$runtime(List<? extends StateObject> list) {
        this.merged = list;
    }

    /* JADX INFO: renamed from: getPreviousIds$runtime, reason: from getter */
    public final SnapshotIdSet getPreviousIds() {
        return this.previousIds;
    }

    public final void setPreviousIds$runtime(SnapshotIdSet snapshotIdSet) {
        this.previousIds = snapshotIdSet;
    }

    /* JADX INFO: renamed from: getPreviousPinnedSnapshots$runtime, reason: from getter */
    public final int[] getPreviousPinnedSnapshots() {
        return this.previousPinnedSnapshots;
    }

    public final void setPreviousPinnedSnapshots$runtime(int[] iArr) {
        this.previousPinnedSnapshots = iArr;
    }

    /* JADX INFO: renamed from: getApplied$runtime, reason: from getter */
    public final boolean getApplied() {
        return this.applied;
    }

    public final void setApplied$runtime(boolean z) {
        this.applied = z;
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot$Companion;", "", "<init>", "()V", "EmptyIntArray", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void advance$runtime() {
        long j;
        recordPrevious$runtime(getSnapshotId());
        Unit unit = Unit.INSTANCE;
        if (getApplied() || getDisposed()) {
            return;
        }
        long snapshotId = getSnapshotId();
        synchronized (SnapshotKt.getLock()) {
            long j2 = SnapshotKt.nextSnapshotId;
            j = 1;
            SnapshotKt.nextSnapshotId += j;
            setSnapshotId$runtime(j2);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
            Unit unit2 = Unit.INSTANCE;
        }
        setInvalid$runtime(SnapshotKt.addRange(getInvalid(), snapshotId + j, getSnapshotId()));
    }

    public final void recordPrevious$runtime(long id) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.set(id);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void recordPreviousList$runtime(SnapshotIdSet snapshots) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.or(snapshots);
            Unit unit = Unit.INSTANCE;
        }
    }
}
