package androidx.compose.runtime;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecomposeScopeImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 `2\u00020\u00012\u00020\u0002:\u0001`B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u00106\u001a\u0002052\u0006\u00107\u001a\u000204J\u0010\u00108\u001a\u0002092\b\u0010\u0018\u001a\u0004\u0018\u00010:J\u0006\u0010;\u001a\u000205J\u000e\u0010<\u001a\u0002052\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010=\u001a\u000205H\u0016J\"\u0010>\u001a\u0002052\u0018\u00102\u001a\u0014\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020503H\u0016J\u000e\u0010N\u001a\u0002052\u0006\u0010O\u001a\u00020\u000bJ\u0006\u0010P\u001a\u000205J\u000e\u0010Q\u001a\u00020\u00132\u0006\u0010R\u001a\u00020:J\u001c\u0010S\u001a\u0002052\n\u0010R\u001a\u0006\u0012\u0002\b\u00030D2\b\u0010\u0018\u001a\u0004\u0018\u00010:J\u0010\u0010U\u001a\u00020\u00132\b\u0010V\u001a\u0004\u0018\u00010:J*\u0010W\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030D2\u0018\u0010X\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030D\u0012\u0006\u0012\u0004\u0018\u00010:0CH\u0002J\u0006\u0010Y\u001a\u000205J\u001c\u0010Z\u001a\u0010\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u000205\u0018\u00010[2\u0006\u0010O\u001a\u00020\u000bJ\u0011\u0010]\u001a\u00020\u00132\u0006\u0010^\u001a\u00020\u000bH\u0082\bJ\u0019\u0010_\u001a\u0002052\u0006\u0010^\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0013H\u0082\bR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R$\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u001cR$\u0010 \u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b!\u0010\u0015\"\u0004\b\"\u0010\u001cR$\u0010#\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b$\u0010\u0015\"\u0004\b%\u0010\u001cR$\u0010&\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0015\"\u0004\b(\u0010\u001cR$\u0010)\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010\u0015\"\u0004\b+\u0010\u001cR$\u0010,\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b-\u0010\u0015\"\u0004\b.\u0010\u001cR$\u0010/\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u0010\u0015\"\u0004\b1\u0010\u001cR\"\u00102\u001a\u0016\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u000205\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010@\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010AX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010B\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030D\u0012\u0006\u0012\u0004\u0018\u00010:\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010E\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bF\u0010\u0015\"\u0004\bG\u0010\u001cR$\u0010H\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bI\u0010\u0015\"\u0004\bJ\u0010\u001cR$\u0010K\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00138@@BX\u0080\u000e¢\u0006\f\u001a\u0004\bL\u0010\u0015\"\u0004\bM\u0010\u001cR\u0011\u0010T\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\bT\u0010\u0015¨\u0006a"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/ScopeUpdateScope;", "Landroidx/compose/runtime/RecomposeScope;", "owner", "Landroidx/compose/runtime/RecomposeScopeOwner;", "<init>", "(Landroidx/compose/runtime/RecomposeScopeOwner;)V", "getOwner$runtime", "()Landroidx/compose/runtime/RecomposeScopeOwner;", "setOwner$runtime", "flags", "", "anchor", "Landroidx/compose/runtime/Anchor;", "getAnchor", "()Landroidx/compose/runtime/Anchor;", "setAnchor", "(Landroidx/compose/runtime/Anchor;)V", "valid", "", "getValid", "()Z", "canRecompose", "getCanRecompose", "value", "used", "getUsed", "setUsed", "(Z)V", "reusing", "getReusing", "setReusing", "resetReusing", "getResetReusing", "setResetReusing", "paused", "getPaused", "setPaused", "resuming", "getResuming", "setResuming", "defaultsInScope", "getDefaultsInScope", "setDefaultsInScope", "defaultsInvalid", "getDefaultsInvalid", "setDefaultsInvalid", "requiresRecompose", "getRequiresRecompose", "setRequiresRecompose", "block", "Lkotlin/Function2;", "Landroidx/compose/runtime/Composer;", "", "compose", "composer", "invalidateForResult", "Landroidx/compose/runtime/InvalidationResult;", "", "release", "adoptedBy", "invalidate", "updateScope", "currentToken", "trackedInstances", "Landroidx/collection/MutableObjectIntMap;", "trackedDependencies", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/DerivedState;", "rereading", "getRereading", "setRereading", "forcedRecompose", "getForcedRecompose", "setForcedRecompose", "skipped", "getSkipped$runtime", "setSkipped", "start", "token", "scopeSkipped", "recordRead", "instance", "recordDerivedStateValue", "isConditional", "isInvalidFor", "instances", "checkDerivedStateChanged", "dependencies", "rereadTrackedInstances", "end", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "getFlag", "flag", "setFlag", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class RecomposeScopeImpl implements ScopeUpdateScope, RecomposeScope {
    private Anchor anchor;
    private Function2<? super Composer, ? super Integer, Unit> block;
    private int currentToken;
    private int flags;
    private RecomposeScopeOwner owner;
    private MutableScatterMap<DerivedState<?>, Object> trackedDependencies;
    private MutableObjectIntMap<Object> trackedInstances;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public RecomposeScopeImpl(RecomposeScopeOwner recomposeScopeOwner) {
        this.owner = recomposeScopeOwner;
    }

    /* JADX INFO: renamed from: getOwner$runtime, reason: from getter */
    public final RecomposeScopeOwner getOwner() {
        return this.owner;
    }

    public final void setOwner$runtime(RecomposeScopeOwner recomposeScopeOwner) {
        this.owner = recomposeScopeOwner;
    }

    public final Anchor getAnchor() {
        return this.anchor;
    }

    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public final boolean getValid() {
        if (this.owner != null) {
            Anchor anchor = this.anchor;
            if (anchor != null ? anchor.getValid() : false) {
                return true;
            }
        }
        return false;
    }

    public final boolean getCanRecompose() {
        return this.block != null;
    }

    public final void compose(Composer composer) {
        Function2<? super Composer, ? super Integer, Unit> function2 = this.block;
        if (function2 == null) {
            throw new IllegalStateException("Invalid restart scope".toString());
        }
        function2.invoke(composer, 1);
    }

    public final InvalidationResult invalidateForResult(Object value) {
        InvalidationResult invalidationResultInvalidate;
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        return (recomposeScopeOwner == null || (invalidationResultInvalidate = recomposeScopeOwner.invalidate(this, value)) == null) ? InvalidationResult.IGNORED : invalidationResultInvalidate;
    }

    public final void release() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.recomposeScopeReleased(this);
        }
        this.owner = null;
        this.trackedInstances = null;
        this.trackedDependencies = null;
        this.block = null;
    }

    public final void adoptedBy(RecomposeScopeOwner owner) {
        this.owner = owner;
    }

    @Override // androidx.compose.runtime.RecomposeScope
    public void invalidate() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.invalidate(this, null);
        }
    }

    @Override // androidx.compose.runtime.ScopeUpdateScope
    public void updateScope(Function2<? super Composer, ? super Integer, Unit> block) {
        this.block = block;
    }

    public final void start(int token) {
        this.currentToken = token;
        setSkipped(false);
    }

    public final void scopeSkipped() {
        if (getReusing()) {
            return;
        }
        setSkipped(true);
    }

    public final boolean recordRead(Object instance) {
        int i = 0;
        if (getRereading()) {
            return false;
        }
        MutableObjectIntMap<Object> mutableObjectIntMap = this.trackedInstances;
        int i2 = 1;
        if (mutableObjectIntMap == null) {
            mutableObjectIntMap = new MutableObjectIntMap<>(i, i2, null);
            this.trackedInstances = mutableObjectIntMap;
        }
        return mutableObjectIntMap.put(instance, this.currentToken, -1) == this.currentToken;
    }

    public final void recordDerivedStateValue(DerivedState<?> instance, Object value) {
        MutableScatterMap<DerivedState<?>, Object> mutableScatterMap = this.trackedDependencies;
        if (mutableScatterMap == null) {
            mutableScatterMap = new MutableScatterMap<>(0, 1, null);
            this.trackedDependencies = mutableScatterMap;
        }
        mutableScatterMap.set(instance, value);
    }

    public final boolean isConditional() {
        return this.trackedDependencies != null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0065, code lost:
    
        return true;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isInvalidFor(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 1
            if (r1 != 0) goto L8
            return r2
        L8:
            androidx.collection.MutableScatterMap<androidx.compose.runtime.DerivedState<?>, java.lang.Object> r3 = r0.trackedDependencies
            if (r3 != 0) goto Ld
            return r2
        Ld:
            boolean r4 = r1 instanceof androidx.compose.runtime.DerivedState
            if (r4 == 0) goto L18
            androidx.compose.runtime.DerivedState r1 = (androidx.compose.runtime.DerivedState) r1
            boolean r1 = r0.checkDerivedStateChanged(r1, r3)
            return r1
        L18:
            boolean r4 = r1 instanceof androidx.collection.ScatterSet
            if (r4 == 0) goto L72
            androidx.collection.ScatterSet r1 = (androidx.collection.ScatterSet) r1
            boolean r4 = r1.isNotEmpty()
            r5 = 0
            if (r4 == 0) goto L71
            java.lang.Object[] r4 = r1.elements
            long[] r1 = r1.metadata
            int r6 = r1.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L71
            r7 = r5
        L2f:
            r8 = r1[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L6c
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r5
        L49:
            if (r12 >= r10) goto L6a
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L66
            int r13 = r7 << 3
            int r13 = r13 + r12
            r13 = r4[r13]
            boolean r14 = r13 instanceof androidx.compose.runtime.DerivedState
            if (r14 == 0) goto L65
            androidx.compose.runtime.DerivedState r13 = (androidx.compose.runtime.DerivedState) r13
            boolean r13 = r0.checkDerivedStateChanged(r13, r3)
            if (r13 == 0) goto L66
        L65:
            return r2
        L66:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L49
        L6a:
            if (r10 != r11) goto L71
        L6c:
            if (r7 == r6) goto L71
            int r7 = r7 + 1
            goto L2f
        L71:
            return r5
        L72:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.isInvalidFor(java.lang.Object):boolean");
    }

    private final boolean checkDerivedStateChanged(DerivedState<?> derivedState, MutableScatterMap<DerivedState<?>, Object> mutableScatterMap) {
        Intrinsics.checkNotNull(derivedState, "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>");
        SnapshotMutationPolicy<?> policy = derivedState.getPolicy();
        if (policy == null) {
            policy = SnapshotStateKt.structuralEqualityPolicy();
        }
        return !policy.equivalent(derivedState.getCurrentRecord().getCurrentValue(), mutableScatterMap.get(derivedState));
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void rereadTrackedInstances() {
        /*
            r17 = this;
            r1 = r17
            androidx.compose.runtime.RecomposeScopeOwner r0 = r1.owner
            if (r0 == 0) goto L60
            androidx.collection.MutableObjectIntMap<java.lang.Object> r2 = r1.trackedInstances
            if (r2 == 0) goto L60
            r3 = 1
            r1.setRereading(r3)
            r3 = 0
            androidx.collection.ObjectIntMap r2 = (androidx.collection.ObjectIntMap) r2     // Catch: java.lang.Throwable -> L5b
            java.lang.Object[] r4 = r2.keys     // Catch: java.lang.Throwable -> L5b
            int[] r5 = r2.values     // Catch: java.lang.Throwable -> L5b
            long[] r2 = r2.metadata     // Catch: java.lang.Throwable -> L5b
            int r6 = r2.length     // Catch: java.lang.Throwable -> L5b
            int r6 = r6 + (-2)
            if (r6 < 0) goto L57
            r7 = r3
        L1d:
            r8 = r2[r7]     // Catch: java.lang.Throwable -> L5b
            long r10 = ~r8     // Catch: java.lang.Throwable -> L5b
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L52
            int r10 = r7 - r6
            int r10 = ~r10     // Catch: java.lang.Throwable -> L5b
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r3
        L37:
            if (r12 >= r10) goto L50
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L4c
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r4[r13]     // Catch: java.lang.Throwable -> L5b
            r13 = r5[r13]     // Catch: java.lang.Throwable -> L5b
            r0.recordReadOf(r14)     // Catch: java.lang.Throwable -> L5b
        L4c:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L37
        L50:
            if (r10 != r11) goto L57
        L52:
            if (r7 == r6) goto L57
            int r7 = r7 + 1
            goto L1d
        L57:
            r1.setRereading(r3)
            return
        L5b:
            r0 = move-exception
            r1.setRereading(r3)
            throw r0
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.rereadTrackedInstances():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.jvm.functions.Function1<androidx.compose.runtime.Composition, kotlin.Unit> end(final int r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            androidx.collection.MutableObjectIntMap<java.lang.Object> r2 = r0.trackedInstances
            r3 = 0
            if (r2 == 0) goto L5e
            boolean r4 = r0.getSkipped$runtime()
            if (r4 != 0) goto L5e
            r4 = r2
            androidx.collection.ObjectIntMap r4 = (androidx.collection.ObjectIntMap) r4
            java.lang.Object[] r5 = r4.keys
            int[] r6 = r4.values
            long[] r4 = r4.metadata
            int r7 = r4.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L5e
            r8 = 0
            r9 = r8
        L1f:
            r10 = r4[r9]
            long r12 = ~r10
            r14 = 7
            long r12 = r12 << r14
            long r12 = r12 & r10
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r12 & r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L59
            int r12 = r9 - r7
            int r12 = ~r12
            int r12 = r12 >>> 31
            r13 = 8
            int r12 = 8 - r12
            r14 = r8
        L39:
            if (r14 >= r12) goto L57
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r15 & r10
            r17 = 128(0x80, double:6.3E-322)
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L53
            int r15 = r9 << 3
            int r15 = r15 + r14
            r16 = r5[r15]
            r15 = r6[r15]
            if (r15 == r1) goto L53
            androidx.compose.runtime.RecomposeScopeImpl$$ExternalSyntheticLambda0 r3 = new androidx.compose.runtime.RecomposeScopeImpl$$ExternalSyntheticLambda0
            r3.<init>()
            return r3
        L53:
            long r10 = r10 >> r13
            int r14 = r14 + 1
            goto L39
        L57:
            if (r12 != r13) goto L5e
        L59:
            if (r9 == r7) goto L5e
            int r9 = r9 + 1
            goto L1f
        L5e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.end(int):kotlin.jvm.functions.Function1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.Unit end$lambda$9$lambda$8(androidx.compose.runtime.RecomposeScopeImpl r18, int r19, androidx.collection.MutableObjectIntMap r20, androidx.compose.runtime.Composition r21) {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            int r4 = r0.currentToken
            if (r4 != r1) goto L8d
            androidx.collection.MutableObjectIntMap<java.lang.Object> r4 = r0.trackedInstances
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)
            if (r4 == 0) goto L8d
            boolean r4 = r3 instanceof androidx.compose.runtime.CompositionImpl
            if (r4 == 0) goto L8d
            r4 = r2
            androidx.collection.ObjectIntMap r4 = (androidx.collection.ObjectIntMap) r4
            long[] r4 = r4.metadata
            int r5 = r4.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L8d
            r7 = 0
        L23:
            r8 = r4[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L88
            int r10 = r7 - r5
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = 0
        L3d:
            if (r12 >= r10) goto L85
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L7c
            int r13 = r7 << 3
            int r13 = r13 + r12
            java.lang.Object[] r14 = r2.keys
            r14 = r14[r13]
            int[] r15 = r2.values
            r15 = r15[r13]
            if (r15 == r1) goto L57
            r15 = 1
            goto L58
        L57:
            r15 = 0
        L58:
            if (r15 == 0) goto L74
            r6 = r3
            androidx.compose.runtime.CompositionImpl r6 = (androidx.compose.runtime.CompositionImpl) r6
            r6.removeObservation$runtime(r14, r0)
            r17 = r11
            boolean r11 = r14 instanceof androidx.compose.runtime.DerivedState
            if (r11 == 0) goto L76
            r11 = r14
            androidx.compose.runtime.DerivedState r11 = (androidx.compose.runtime.DerivedState) r11
            r6.removeDerivedStateObservation$runtime(r11)
            androidx.collection.MutableScatterMap<androidx.compose.runtime.DerivedState<?>, java.lang.Object> r6 = r0.trackedDependencies
            if (r6 == 0) goto L76
            r6.remove(r14)
            goto L76
        L74:
            r17 = r11
        L76:
            if (r15 == 0) goto L7e
            r2.removeValueAt(r13)
            goto L7e
        L7c:
            r17 = r11
        L7e:
            long r8 = r8 >> r17
            int r12 = r12 + 1
            r11 = r17
            goto L3d
        L85:
            r6 = r11
            if (r10 != r6) goto L8d
        L88:
            if (r7 == r5) goto L8d
            int r7 = r7 + 1
            goto L23
        L8d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.end$lambda$9$lambda$8(androidx.compose.runtime.RecomposeScopeImpl, int, androidx.collection.MutableObjectIntMap, androidx.compose.runtime.Composition):kotlin.Unit");
    }

    private final boolean getFlag(int flag) {
        return (flag & this.flags) != 0;
    }

    private final void setFlag(int flag, boolean value) {
        int i = this.flags;
        this.flags = value ? flag | i : (~flag) & i;
    }

    /* JADX INFO: compiled from: RecomposeScopeImpl.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\rJ#\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00102\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl$Companion;", "", "<init>", "()V", "adoptAnchoredScopes", "", "slots", "Landroidx/compose/runtime/SlotWriter;", "anchors", "", "Landroidx/compose/runtime/Anchor;", "newOwner", "Landroidx/compose/runtime/RecomposeScopeOwner;", "adoptAnchoredScopes$runtime", "hasAnchoredRecomposeScopes", "", "Landroidx/compose/runtime/SlotTable;", "hasAnchoredRecomposeScopes$runtime", "runtime"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void adoptAnchoredScopes$runtime(SlotWriter slots, List<Anchor> anchors, RecomposeScopeOwner newOwner) {
            List<Anchor> list = anchors;
            if (list.isEmpty()) {
                return;
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object objSlot = slots.slot(anchors.get(i), 0);
                RecomposeScopeImpl recomposeScopeImpl = objSlot instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) objSlot : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.adoptedBy(newOwner);
                }
            }
        }

        public final boolean hasAnchoredRecomposeScopes$runtime(SlotTable slots, List<Anchor> anchors) {
            List<Anchor> list = anchors;
            if (!list.isEmpty()) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    Anchor anchor = anchors.get(i);
                    if (slots.ownsAnchor(anchor) && (slots.slot$runtime(slots.anchorIndex(anchor), 0) instanceof RecomposeScopeImpl)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public final boolean getUsed() {
        return (this.flags & 1) != 0;
    }

    public final void setUsed(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 1 : i & (-2);
    }

    public final boolean getReusing() {
        return (this.flags & 128) != 0;
    }

    public final void setReusing(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 128 : i & (-129);
    }

    public final boolean getResetReusing() {
        return (this.flags & 1024) != 0;
    }

    public final void setResetReusing(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 1024 : i & (-1025);
    }

    public final boolean getPaused() {
        return (this.flags & 256) != 0;
    }

    public final void setPaused(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 256 : i & (-257);
    }

    public final boolean getResuming() {
        return (this.flags & 512) != 0;
    }

    public final void setResuming(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 512 : i & (-513);
    }

    public final boolean getDefaultsInScope() {
        return (this.flags & 2) != 0;
    }

    public final void setDefaultsInScope(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 2 : i & (-3);
    }

    public final boolean getDefaultsInvalid() {
        return (this.flags & 4) != 0;
    }

    public final void setDefaultsInvalid(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 4 : i & (-5);
    }

    public final boolean getRequiresRecompose() {
        return (this.flags & 8) != 0;
    }

    public final void setRequiresRecompose(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 8 : i & (-9);
    }

    private final boolean getRereading() {
        return (this.flags & 32) != 0;
    }

    private final void setRereading(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 32 : i & (-33);
    }

    public final boolean getForcedRecompose() {
        return (this.flags & 64) != 0;
    }

    public final void setForcedRecompose(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 64 : i & (-65);
    }

    public final boolean getSkipped$runtime() {
        return (this.flags & 16) != 0;
    }

    private final void setSkipped(boolean z) {
        int i = this.flags;
        this.flags = z ? i | 16 : i & (-17);
    }
}
