package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LongSet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0001J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\r\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000fJ\u0006\u0010\u0010\u001a\u00020\u000eJ\r\u0010\u0011\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0012J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0003H\u0002J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0011\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0001H\u0086\u0002J\u0011\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0086\u0002J\u0011\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0002J\u0011\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0001H\u0086\u0002J\u0011\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0086\u0002J\u0011\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0002J\u000e\u0010\u001c\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0001J\u000e\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u0003H\u0002J\u0015\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\"J\b\u0010#\u001a\u00020\u0003H\u0007R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Landroidx/collection/MutableLongSet;", "Landroidx/collection/LongSet;", "initialCapacity", "", "(I)V", "growthLimit", "add", "", "element", "", "addAll", "elements", "", "adjustStorage", "", "adjustStorage$collection", "clear", "dropDeletes", "dropDeletes$collection", "findAbsoluteInsertIndex", "findFirstAvailableSlot", "hash1", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "plusAssign", "remove", "removeAll", "removeElementAt", FirebaseAnalytics.Param.INDEX, "resizeStorage", "newCapacity", "resizeStorage$collection", "trim", "collection"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MutableLongSet extends LongSet {
    private int growthLimit;

    public MutableLongSet() {
        this(0, 1, null);
    }

    public MutableLongSet(int i) {
        super(null);
        if (!(i >= 0)) {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
        }
        initializeStorage(ScatterMapKt.unloadedCapacity(i));
    }

    public /* synthetic */ MutableLongSet(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 6 : i);
    }

    private final void initializeStorage(int initialCapacity) {
        int iMax = initialCapacity > 0 ? Math.max(7, ScatterMapKt.normalizeCapacity(initialCapacity)) : 0;
        this._capacity = iMax;
        initializeMetadata(iMax);
        this.elements = new long[iMax];
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

    public final boolean add(long element) {
        int i = this._size;
        this.elements[findAbsoluteInsertIndex(element)] = element;
        return this._size != i;
    }

    public final void plusAssign(long element) {
        this.elements[findAbsoluteInsertIndex(element)] = element;
    }

    public final boolean addAll(long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        plusAssign(elements);
        return i != this._size;
    }

    public final boolean addAll(LongSet elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        plusAssign(elements);
        return i != this._size;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0069, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x006b, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean remove(long r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r0
            androidx.collection.LongSet r1 = (androidx.collection.LongSet) r1
            int r2 = java.lang.Long.hashCode(r19)
            r3 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r2 = r2 * r3
            int r3 = r2 << 16
            r2 = r2 ^ r3
            r3 = r2 & 127(0x7f, float:1.78E-43)
            int r4 = r1._capacity
            int r2 = r2 >>> 7
            r2 = r2 & r4
            r5 = 0
            r6 = r5
        L19:
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
        L45:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L62
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r2
            r11 = r11 & r4
            long[] r15 = r1.elements
            r16 = r15[r11]
            int r15 = (r16 > r19 ? 1 : (r16 == r19 ? 0 : -1))
            if (r15 != 0) goto L5c
            goto L6c
        L5c:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L45
        L62:
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L75
            r11 = -1
        L6c:
            if (r11 < 0) goto L6f
            r5 = r12
        L6f:
            if (r5 == 0) goto L74
            r0.removeElementAt(r11)
        L74:
            return r5
        L75:
            int r6 = r6 + 8
            int r2 = r2 + r6
            r2 = r2 & r4
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableLongSet.remove(long):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0068, code lost:
    
        if (((r6 & ((~r6) << 6)) & (-9187201950435737472L)) == 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x006a, code lost:
    
        r12 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void minusAssign(long r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r0
            androidx.collection.LongSet r1 = (androidx.collection.LongSet) r1
            int r2 = java.lang.Long.hashCode(r17)
            r3 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r2 = r2 * r3
            int r3 = r2 << 16
            r2 = r2 ^ r3
            r3 = r2 & 127(0x7f, float:1.78E-43)
            int r4 = r1._capacity
            int r2 = r2 >>> 7
            r2 = r2 & r4
            r5 = 0
        L18:
            long[] r6 = r1.metadata
            int r7 = r2 >> 3
            r8 = r2 & 7
            int r8 = r8 << 3
            r9 = r6[r7]
            long r9 = r9 >>> r8
            int r7 = r7 + 1
            r11 = r6[r7]
            int r6 = 64 - r8
            long r6 = r11 << r6
            long r11 = (long) r8
            long r11 = -r11
            r8 = 63
            long r11 = r11 >> r8
            long r6 = r6 & r11
            long r6 = r6 | r9
            long r8 = (long) r3
            r10 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r8 = r8 * r10
            long r8 = r8 ^ r6
            long r10 = r8 - r10
            long r8 = ~r8
            long r8 = r8 & r10
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
        L44:
            r12 = 0
            int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r14 == 0) goto L61
            int r12 = java.lang.Long.numberOfTrailingZeros(r8)
            int r12 = r12 >> 3
            int r12 = r12 + r2
            r12 = r12 & r4
            long[] r13 = r1.elements
            r14 = r13[r12]
            int r13 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r13 != 0) goto L5b
            goto L6b
        L5b:
            r12 = 1
            long r12 = r8 - r12
            long r8 = r8 & r12
            goto L44
        L61:
            long r8 = ~r6
            r14 = 6
            long r8 = r8 << r14
            long r6 = r6 & r8
            long r6 = r6 & r10
            int r6 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r6 == 0) goto L71
            r12 = -1
        L6b:
            if (r12 < 0) goto L70
            r0.removeElementAt(r12)
        L70:
            return
        L71:
            int r5 = r5 + 8
            int r2 = r2 + r5
            r2 = r2 & r4
            goto L18
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableLongSet.minusAssign(long):void");
    }

    public final boolean removeAll(long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        minusAssign(elements);
        return i != this._size;
    }

    public final boolean removeAll(LongSet elements) {
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
        long[] jArr2 = this.elements;
        int i2 = (i + 7) >> 3;
        char c = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = jArr[i3] & (-9187201950435737472L);
            jArr[i3] = (-72340172838076674L) & ((~j2) + (j2 >>> 7));
        }
        int lastIndex = ArraysKt.getLastIndex(jArr);
        int i4 = lastIndex - 1;
        long j3 = 72057594037927935L;
        jArr[i4] = (jArr[i4] & 72057594037927935L) | (-72057594037927936L);
        jArr[lastIndex] = jArr[0];
        int i5 = 0;
        while (i5 != i) {
            int i6 = i5 >> 3;
            int i7 = (i5 & 7) << 3;
            long j4 = (jArr[i6] >> i7) & 255;
            if (j4 != 128 && j4 == 254) {
                int iHashCode = Long.hashCode(jArr2[i5]) * ScatterMapKt.MurmurHashC1;
                int i8 = iHashCode ^ (iHashCode << 16);
                int i9 = i8 >>> 7;
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i9);
                int i10 = i9 & i;
                char c2 = c;
                if (((iFindFirstAvailableSlot - i10) & i) / 8 == ((i5 - i10) & i) / 8) {
                    jArr[i6] = (((long) (i8 & 127)) << i7) | ((~(255 << i7)) & jArr[i6]);
                    jArr[ArraysKt.getLastIndex(jArr)] = (jArr[c2] & j3) | Long.MIN_VALUE;
                    i5++;
                    c = c2;
                } else {
                    int i11 = iFindFirstAvailableSlot >> 3;
                    long j5 = jArr[i11];
                    int i12 = (iFindFirstAvailableSlot & 7) << 3;
                    if (((j5 >> i12) & 255) == 128) {
                        j = j3;
                        jArr[i11] = (((long) (i8 & 127)) << i12) | (j5 & (~(255 << i12)));
                        jArr[i6] = (jArr[i6] & (~(255 << i7))) | (128 << i7);
                        jArr2[iFindFirstAvailableSlot] = jArr2[i5];
                        jArr2[i5] = 0;
                    } else {
                        j = j3;
                        jArr[i11] = (((long) (i8 & 127)) << i12) | (j5 & (~(255 << i12)));
                        long j6 = jArr2[iFindFirstAvailableSlot];
                        jArr2[iFindFirstAvailableSlot] = jArr2[i5];
                        jArr2[i5] = j6;
                        i5--;
                    }
                    jArr[ArraysKt.getLastIndex(jArr)] = (jArr[c2] & j) | Long.MIN_VALUE;
                    i5++;
                    c = c2;
                    j3 = j;
                }
            } else {
                i5++;
            }
        }
        initializeGrowth();
    }

    public final void resizeStorage$collection(int newCapacity) {
        long[] jArr = this.metadata;
        long[] jArr2 = this.elements;
        int i = this._capacity;
        initializeStorage(newCapacity);
        long[] jArr3 = this.metadata;
        long[] jArr4 = this.elements;
        int i2 = this._capacity;
        for (int i3 = 0; i3 < i; i3++) {
            if (((jArr[i3 >> 3] >> ((i3 & 7) << 3)) & 255) < 128) {
                long j = jArr2[i3];
                int iHashCode = Long.hashCode(j) * ScatterMapKt.MurmurHashC1;
                int i4 = iHashCode ^ (iHashCode << 16);
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i4 >>> 7);
                long j2 = i4 & 127;
                int i5 = iFindFirstAvailableSlot >> 3;
                int i6 = (iFindFirstAvailableSlot & 7) << 3;
                long j3 = (jArr3[i5] & (~(255 << i6))) | (j2 << i6);
                jArr3[i5] = j3;
                jArr3[(((iFindFirstAvailableSlot - 7) & i2) + (i2 & 7)) >> 3] = j3;
                jArr4[iFindFirstAvailableSlot] = j;
            }
        }
    }

    public final void plusAssign(long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (long j : elements) {
            plusAssign(j);
        }
    }

    public final void plusAssign(LongSet elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        long[] jArr = elements.elements;
        long[] jArr2 = elements.metadata;
        int length = jArr2.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr2[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        plusAssign(jArr[(i << 3) + i3]);
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

    public final void minusAssign(long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (long j : elements) {
            minusAssign(j);
        }
    }

    public final void minusAssign(LongSet elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        long[] jArr = elements.elements;
        long[] jArr2 = elements.metadata;
        int length = jArr2.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr2[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        minusAssign(jArr[(i << 3) + i3]);
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

    private final int findAbsoluteInsertIndex(long element) {
        int iHashCode = Long.hashCode(element) * ScatterMapKt.MurmurHashC1;
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
            long j = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j2 = i3;
            int i9 = i6;
            long j3 = j ^ (j2 * ScatterMapKt.BitmaskLsb);
            for (long j4 = (~j3) & (j3 - ScatterMapKt.BitmaskLsb) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i5) & i4;
                if (this.elements[iNumberOfTrailingZeros] == element) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((((~j) << 6) & j & (-9187201950435737472L)) != 0) {
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i2);
                if (this.growthLimit == 0 && ((this.metadata[iFindFirstAvailableSlot >> 3] >> ((iFindFirstAvailableSlot & 7) << 3)) & 255) != 254) {
                    adjustStorage$collection();
                    iFindFirstAvailableSlot = findFirstAvailableSlot(i2);
                }
                this._size++;
                int i10 = iFindFirstAvailableSlot >> 3;
                int i11 = (iFindFirstAvailableSlot & 7) << 3;
                this.growthLimit -= ((this.metadata[i10] >> i11) & 255) == 128 ? 1 : 0;
                long[] jArr2 = this.metadata;
                int i12 = this._capacity;
                long j5 = ((~(255 << i11)) & jArr2[i10]) | (j2 << i11);
                jArr2[i10] = j5;
                jArr2[(((iFindFirstAvailableSlot - 7) & i12) + (i12 & 7)) >> 3] = j5;
                return iFindFirstAvailableSlot;
            }
            i6 = i9 + 8;
            i5 = (i5 + i6) & i4;
        }
    }
}
