package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IntSet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0001J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\r\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000eJ\u0006\u0010\u000f\u001a\u00020\rJ\r\u0010\u0010\u001a\u00020\rH\u0000¢\u0006\u0002\b\u0011J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0002J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\u0002J\b\u0010\u0015\u001a\u00020\rH\u0002J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0003H\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0011\u0010\u0019\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0001H\u0086\u0002J\u0011\u0010\u0019\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0003H\u0086\u0002J\u0011\u0010\u0019\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002J\u0011\u0010\u001a\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0001H\u0086\u0002J\u0011\u0010\u001a\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0003H\u0086\u0002J\u0011\u0010\u001a\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002J\u000e\u0010\u001b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003J\u000e\u0010\u001c\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0001J\u000e\u0010\u001c\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u0003H\u0002J\u0015\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0003H\u0000¢\u0006\u0002\b!J\b\u0010\"\u001a\u00020\u0003H\u0007R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/collection/MutableIntSet;", "Landroidx/collection/IntSet;", "initialCapacity", "", "(I)V", "growthLimit", "add", "", "element", "addAll", "elements", "", "adjustStorage", "", "adjustStorage$collection", "clear", "dropDeletes", "dropDeletes$collection", "findAbsoluteInsertIndex", "findFirstAvailableSlot", "hash1", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "plusAssign", "remove", "removeAll", "removeElementAt", FirebaseAnalytics.Param.INDEX, "resizeStorage", "newCapacity", "resizeStorage$collection", "trim", "collection"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MutableIntSet extends IntSet {
    private int growthLimit;

    public MutableIntSet() {
        this(0, 1, null);
    }

    public MutableIntSet(int i) {
        super(null);
        if (!(i >= 0)) {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
        }
        initializeStorage(ScatterMapKt.unloadedCapacity(i));
    }

    public /* synthetic */ MutableIntSet(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 6 : i);
    }

    private final void initializeStorage(int initialCapacity) {
        int iMax = initialCapacity > 0 ? Math.max(7, ScatterMapKt.normalizeCapacity(initialCapacity)) : 0;
        this._capacity = iMax;
        initializeMetadata(iMax);
        this.elements = new int[iMax];
    }

    private final void initializeMetadata(int capacity) {
        long[] jArr;
        if (capacity == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            long[] jArr2 = new long[((capacity + 15) & (-8)) >> 3];
            ArraysKt.fill$default(jArr2, -9187201950435737472L, 0, 0, 6, (Object) null);
            jArr = jArr2;
        }
        this.metadata = jArr;
        long[] jArr3 = this.metadata;
        int i = capacity >> 3;
        long j = 255 << ((capacity & 7) << 3);
        jArr3[i] = (jArr3[i] & (~j)) | j;
        initializeGrowth();
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(get_capacity()) - this._size;
    }

    public final boolean add(int element) {
        int i = this._size;
        this.elements[findAbsoluteInsertIndex(element)] = element;
        return this._size != i;
    }

    public final void plusAssign(int element) {
        this.elements[findAbsoluteInsertIndex(element)] = element;
    }

    public final boolean addAll(int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        plusAssign(elements);
        return i != this._size;
    }

    public final boolean addAll(IntSet elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        plusAssign(elements);
        return i != this._size;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x006a, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x006c, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean remove(int r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r0
            androidx.collection.IntSet r1 = (androidx.collection.IntSet) r1
            int r2 = java.lang.Integer.hashCode(r18)
            r3 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r2 = r2 * r3
            int r3 = r2 << 16
            r2 = r2 ^ r3
            r3 = r2 & 127(0x7f, float:1.78E-43)
            int r4 = r1._capacity
            int r2 = r2 >>> 7
            r2 = r2 & r4
            r6 = 0
        L18:
            long[] r7 = r1.metadata
            int r8 = r2 >> 3
            r9 = r2 & 7
            int r9 = r9 << 3
            r10 = r7[r8]
            long r10 = r10 >>> r9
            r12 = 1
            int r8 = r8 + r12
            r13 = r7[r8]
            int r7 = 64 - r9
            long r7 = r13 << r7
            long r13 = (long) r9
            long r13 = -r13
            r9 = 63
            long r13 = r13 >> r9
            long r7 = r7 & r13
            long r7 = r7 | r10
            long r9 = (long) r3
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r9 = r9 * r13
            long r9 = r9 ^ r7
            long r13 = r9 - r13
            long r9 = ~r9
            long r9 = r9 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r13
        L44:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L61
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r2
            r11 = r11 & r4
            int[] r15 = r1.elements
            r15 = r15[r11]
            r5 = r18
            if (r15 != r5) goto L5b
            goto L6d
        L5b:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L44
        L61:
            r5 = r18
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L78
            r11 = -1
        L6d:
            if (r11 < 0) goto L71
            r5 = r12
            goto L72
        L71:
            r5 = 0
        L72:
            if (r5 == 0) goto L77
            r0.removeElementAt(r11)
        L77:
            return r5
        L78:
            int r6 = r6 + 8
            int r2 = r2 + r6
            r2 = r2 & r4
            goto L18
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableIntSet.remove(int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0064, code lost:
    
        if (((r5 & ((~r5) << 6)) & (-9187201950435737472L)) == 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0066, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void minusAssign(int r15) {
        /*
            r14 = this;
            r0 = r14
            androidx.collection.IntSet r0 = (androidx.collection.IntSet) r0
            int r1 = java.lang.Integer.hashCode(r15)
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r0._capacity
            int r1 = r1 >>> 7
            r1 = r1 & r3
            r4 = 0
        L16:
            long[] r5 = r0.metadata
            int r6 = r1 >> 3
            r7 = r1 & 7
            int r7 = r7 << 3
            r8 = r5[r6]
            long r8 = r8 >>> r7
            int r6 = r6 + 1
            r10 = r5[r6]
            int r5 = 64 - r7
            long r5 = r10 << r5
            long r10 = (long) r7
            long r10 = -r10
            r7 = 63
            long r10 = r10 >> r7
            long r5 = r5 & r10
            long r5 = r5 | r8
            long r7 = (long) r2
            r9 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r7 = r7 * r9
            long r7 = r7 ^ r5
            long r9 = r7 - r9
            long r7 = ~r7
            long r7 = r7 & r9
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
        L42:
            r11 = 0
            int r13 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r13 == 0) goto L5d
            int r11 = java.lang.Long.numberOfTrailingZeros(r7)
            int r11 = r11 >> 3
            int r11 = r11 + r1
            r11 = r11 & r3
            int[] r12 = r0.elements
            r12 = r12[r11]
            if (r12 != r15) goto L57
            goto L67
        L57:
            r11 = 1
            long r11 = r7 - r11
            long r7 = r7 & r11
            goto L42
        L5d:
            long r7 = ~r5
            r13 = 6
            long r7 = r7 << r13
            long r5 = r5 & r7
            long r5 = r5 & r9
            int r5 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r5 == 0) goto L6d
            r11 = -1
        L67:
            if (r11 < 0) goto L6c
            r14.removeElementAt(r11)
        L6c:
            return
        L6d:
            int r4 = r4 + 8
            int r1 = r1 + r4
            r1 = r1 & r3
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableIntSet.minusAssign(int):void");
    }

    public final boolean removeAll(int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        minusAssign(elements);
        return i != this._size;
    }

    public final boolean removeAll(IntSet elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        minusAssign(elements);
        return i != this._size;
    }

    private final void removeElementAt(int index) {
        this._size--;
        long[] jArr = this.metadata;
        int i = this._capacity;
        int i2 = index >> 3;
        int i3 = (index & 7) << 3;
        long j = (jArr[i2] & (~(255 << i3))) | (254 << i3);
        jArr[i2] = j;
        jArr[(((index - 7) & i) + (i & 7)) >> 3] = j;
    }

    public final void clear() {
        this._size = 0;
        if (this.metadata != ScatterMapKt.EmptyGroup) {
            ArraysKt.fill$default(this.metadata, -9187201950435737472L, 0, 0, 6, (Object) null);
            long[] jArr = this.metadata;
            int i = this._capacity;
            int i2 = i >> 3;
            long j = 255 << ((i & 7) << 3);
            jArr[i2] = (jArr[i2] & (~j)) | j;
        }
        initializeGrowth();
    }

    private final int findFirstAvailableSlot(int hash1) {
        int i = this._capacity;
        int i2 = hash1 & i;
        int i3 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i4 = i2 >> 3;
            int i5 = (i2 & 7) << 3;
            long j = ((jArr[i4 + 1] << (64 - i5)) & ((-i5) >> 63)) | (jArr[i4] >>> i5);
            long j2 = j & ((~j) << 7) & (-9187201950435737472L);
            if (j2 != 0) {
                return (i2 + (Long.numberOfTrailingZeros(j2) >> 3)) & i;
            }
            i3 += 8;
            i2 = (i2 + i3) & i;
        }
    }

    public final int trim() {
        int i = this._capacity;
        int iNormalizeCapacity = ScatterMapKt.normalizeCapacity(ScatterMapKt.unloadedCapacity(this._size));
        if (iNormalizeCapacity >= i) {
            return 0;
        }
        resizeStorage$collection(iNormalizeCapacity);
        return i - this._capacity;
    }

    public final void adjustStorage$collection() {
        if (this._capacity > 8 && Long.compare(ULong.m8053constructorimpl(ULong.m8053constructorimpl(this._size) * 32) ^ Long.MIN_VALUE, ULong.m8053constructorimpl(ULong.m8053constructorimpl(this._capacity) * 25) ^ Long.MIN_VALUE) <= 0) {
            dropDeletes$collection();
        } else {
            resizeStorage$collection(ScatterMapKt.nextCapacity(this._capacity));
        }
    }

    public final void dropDeletes$collection() {
        long j;
        long[] jArr = this.metadata;
        int i = this._capacity;
        int[] iArr = this.elements;
        int i2 = (i + 7) >> 3;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            long j2 = jArr[i4] & (-9187201950435737472L);
            jArr[i4] = (-72340172838076674L) & ((~j2) + (j2 >>> 7));
        }
        int lastIndex = ArraysKt.getLastIndex(jArr);
        int i5 = lastIndex - 1;
        long j3 = 72057594037927935L;
        jArr[i5] = (jArr[i5] & 72057594037927935L) | (-72057594037927936L);
        jArr[lastIndex] = jArr[0];
        int i6 = 0;
        while (i6 != i) {
            int i7 = i6 >> 3;
            int i8 = (i6 & 7) << 3;
            long j4 = (jArr[i7] >> i8) & 255;
            if (j4 != 128 && j4 == 254) {
                int iHashCode = Integer.hashCode(iArr[i6]) * ScatterMapKt.MurmurHashC1;
                int i9 = iHashCode ^ (iHashCode << 16);
                int i10 = i9 >>> 7;
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i10);
                int i11 = i10 & i;
                int i12 = i3;
                if (((iFindFirstAvailableSlot - i11) & i) / 8 == ((i6 - i11) & i) / 8) {
                    jArr[i7] = (((long) (i9 & 127)) << i8) | ((~(255 << i8)) & jArr[i7]);
                    jArr[ArraysKt.getLastIndex(jArr)] = (jArr[i12] & j3) | Long.MIN_VALUE;
                    i6++;
                    i3 = i12;
                } else {
                    int i13 = iFindFirstAvailableSlot >> 3;
                    long j5 = jArr[i13];
                    int i14 = (iFindFirstAvailableSlot & 7) << 3;
                    if (((j5 >> i14) & 255) == 128) {
                        j = j3;
                        jArr[i13] = (((long) (i9 & 127)) << i14) | (j5 & (~(255 << i14)));
                        jArr[i7] = (jArr[i7] & (~(255 << i8))) | (128 << i8);
                        iArr[iFindFirstAvailableSlot] = iArr[i6];
                        iArr[i6] = i12;
                    } else {
                        j = j3;
                        jArr[i13] = (((long) (i9 & 127)) << i14) | (j5 & (~(255 << i14)));
                        int i15 = iArr[iFindFirstAvailableSlot];
                        iArr[iFindFirstAvailableSlot] = iArr[i6];
                        iArr[i6] = i15;
                        i6--;
                    }
                    jArr[ArraysKt.getLastIndex(jArr)] = (jArr[i12] & j) | Long.MIN_VALUE;
                    i6++;
                    i3 = i12;
                    j3 = j;
                }
            } else {
                i6++;
            }
        }
        initializeGrowth();
    }

    public final void resizeStorage$collection(int newCapacity) {
        long[] jArr = this.metadata;
        int[] iArr = this.elements;
        int i = this._capacity;
        initializeStorage(newCapacity);
        long[] jArr2 = this.metadata;
        int[] iArr2 = this.elements;
        int i2 = this._capacity;
        for (int i3 = 0; i3 < i; i3++) {
            if (((jArr[i3 >> 3] >> ((i3 & 7) << 3)) & 255) < 128) {
                int i4 = iArr[i3];
                int iHashCode = Integer.hashCode(i4) * ScatterMapKt.MurmurHashC1;
                int i5 = iHashCode ^ (iHashCode << 16);
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i5 >>> 7);
                long j = i5 & 127;
                int i6 = iFindFirstAvailableSlot >> 3;
                int i7 = (iFindFirstAvailableSlot & 7) << 3;
                long j2 = (jArr2[i6] & (~(255 << i7))) | (j << i7);
                jArr2[i6] = j2;
                jArr2[(((iFindFirstAvailableSlot - 7) & i2) + (i2 & 7)) >> 3] = j2;
                iArr2[iFindFirstAvailableSlot] = i4;
            }
        }
    }

    public final void plusAssign(int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (int i : elements) {
            plusAssign(i);
        }
    }

    public final void plusAssign(IntSet elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int[] iArr = elements.elements;
        long[] jArr = elements.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        plusAssign(iArr[(i << 3) + i3]);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void minusAssign(int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (int i : elements) {
            minusAssign(i);
        }
    }

    public final void minusAssign(IntSet elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int[] iArr = elements.elements;
        long[] jArr = elements.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        minusAssign(iArr[(i << 3) + i3]);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    private final int findAbsoluteInsertIndex(int element) {
        int iHashCode = Integer.hashCode(element) * ScatterMapKt.MurmurHashC1;
        int i = iHashCode ^ (iHashCode << 16);
        int i2 = i >>> 7;
        int i3 = i & 127;
        int i4 = this._capacity;
        int i5 = i2 & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            int i9 = 1;
            long j = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j2 = i3;
            int i10 = i6;
            long j3 = j ^ (j2 * ScatterMapKt.BitmaskLsb);
            long j4 = (~j3) & (j3 - ScatterMapKt.BitmaskLsb) & (-9187201950435737472L);
            while (j4 != 0) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i5) & i4;
                int i11 = i9;
                if (this.elements[iNumberOfTrailingZeros] == element) {
                    return iNumberOfTrailingZeros;
                }
                j4 &= j4 - 1;
                i9 = i11;
            }
            int i12 = i9;
            if ((((~j) << 6) & j & (-9187201950435737472L)) != 0) {
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i2);
                if (this.growthLimit == 0 && ((this.metadata[iFindFirstAvailableSlot >> 3] >> ((iFindFirstAvailableSlot & 7) << 3)) & 255) != 254) {
                    adjustStorage$collection();
                    iFindFirstAvailableSlot = findFirstAvailableSlot(i2);
                }
                this._size++;
                int i13 = iFindFirstAvailableSlot >> 3;
                int i14 = (iFindFirstAvailableSlot & 7) << 3;
                this.growthLimit -= ((this.metadata[i13] >> i14) & 255) == 128 ? i12 : 0;
                long[] jArr2 = this.metadata;
                int i15 = this._capacity;
                long j5 = ((~(255 << i14)) & jArr2[i13]) | (j2 << i14);
                jArr2[i13] = j5;
                jArr2[(((iFindFirstAvailableSlot - 7) & i15) + (i15 & 7)) >> 3] = j5;
                return iFindFirstAvailableSlot;
            }
            i6 = i10 + 8;
            i5 = (i5 + i6) & i4;
        }
    }
}
