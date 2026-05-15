package org.pytorch;

import com.facebook.jni.HybridData;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Tensor {
    private static final int DOUBLE_SIZE_BYTES = 8;
    private static final String ERROR_MSG_DATA_ARRAY_NOT_NULL = "Data array must be not null";
    private static final String ERROR_MSG_DATA_BUFFER_MUST_BE_DIRECT = "Data buffer must be direct (java.nio.ByteBuffer#allocateDirect)";
    private static final String ERROR_MSG_DATA_BUFFER_MUST_HAVE_NATIVE_BYTE_ORDER = "Data buffer must have native byte order (java.nio.ByteOrder#nativeOrder)";
    private static final String ERROR_MSG_DATA_BUFFER_NOT_NULL = "Data buffer must be not null";
    private static final String ERROR_MSG_SHAPE_NON_NEGATIVE = "Shape elements must be non negative";
    private static final String ERROR_MSG_SHAPE_NOT_NULL = "Shape must be not null";
    private static final int FLOAT_SIZE_BYTES = 4;
    private static final int INT_SIZE_BYTES = 4;
    private static final int LONG_SIZE_BYTES = 8;
    private HybridData mHybridData;
    final MemoryFormat memoryFormat;
    final long[] shape;

    public abstract DType dtype();

    public static ByteBuffer allocateByteBuffer(int i) {
        return ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
    }

    public static IntBuffer allocateIntBuffer(int i) {
        return ByteBuffer.allocateDirect(i * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
    }

    public static FloatBuffer allocateFloatBuffer(int i) {
        return ByteBuffer.allocateDirect(i * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public static LongBuffer allocateLongBuffer(int i) {
        return ByteBuffer.allocateDirect(i * 8).order(ByteOrder.nativeOrder()).asLongBuffer();
    }

    public static DoubleBuffer allocateDoubleBuffer(int i) {
        return ByteBuffer.allocateDirect(i * 8).order(ByteOrder.nativeOrder()).asDoubleBuffer();
    }

    public static Tensor fromBlobUnsigned(byte[] bArr, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(bArr != null, ERROR_MSG_DATA_ARRAY_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(bArr.length, jArr);
        ByteBuffer byteBufferAllocateByteBuffer = allocateByteBuffer((int) numel(jArr));
        byteBufferAllocateByteBuffer.put(bArr);
        return new Tensor_uint8(byteBufferAllocateByteBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlobUnsigned(byte[] bArr, long[] jArr) {
        return fromBlobUnsigned(bArr, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(byte[] bArr, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(bArr != null, ERROR_MSG_DATA_ARRAY_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(bArr.length, jArr);
        ByteBuffer byteBufferAllocateByteBuffer = allocateByteBuffer((int) numel(jArr));
        byteBufferAllocateByteBuffer.put(bArr);
        return new Tensor_int8(byteBufferAllocateByteBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlob(byte[] bArr, long[] jArr) {
        return fromBlob(bArr, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(int[] iArr, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(iArr != null, ERROR_MSG_DATA_ARRAY_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(iArr.length, jArr);
        IntBuffer intBufferAllocateIntBuffer = allocateIntBuffer((int) numel(jArr));
        intBufferAllocateIntBuffer.put(iArr);
        return new Tensor_int32(intBufferAllocateIntBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlob(int[] iArr, long[] jArr) {
        return fromBlob(iArr, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(float[] fArr, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(fArr != null, ERROR_MSG_DATA_ARRAY_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(fArr.length, jArr);
        FloatBuffer floatBufferAllocateFloatBuffer = allocateFloatBuffer((int) numel(jArr));
        floatBufferAllocateFloatBuffer.put(fArr);
        return new Tensor_float32(floatBufferAllocateFloatBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlob(float[] fArr, long[] jArr) {
        return fromBlob(fArr, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(long[] jArr, long[] jArr2, MemoryFormat memoryFormat) {
        checkArgument(jArr != null, ERROR_MSG_DATA_ARRAY_NOT_NULL, new Object[0]);
        checkArgument(jArr2 != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr2);
        checkShapeAndDataCapacityConsistency(jArr.length, jArr2);
        LongBuffer longBufferAllocateLongBuffer = allocateLongBuffer((int) numel(jArr2));
        longBufferAllocateLongBuffer.put(jArr);
        return new Tensor_int64(longBufferAllocateLongBuffer, jArr2, memoryFormat);
    }

    public static Tensor fromBlob(long[] jArr, long[] jArr2) {
        return fromBlob(jArr, jArr2, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(double[] dArr, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(dArr != null, ERROR_MSG_DATA_ARRAY_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(dArr.length, jArr);
        DoubleBuffer doubleBufferAllocateDoubleBuffer = allocateDoubleBuffer((int) numel(jArr));
        doubleBufferAllocateDoubleBuffer.put(dArr);
        return new Tensor_float64(doubleBufferAllocateDoubleBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlob(double[] dArr, long[] jArr) {
        return fromBlob(dArr, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlobUnsigned(ByteBuffer byteBuffer, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(byteBuffer != null, ERROR_MSG_DATA_BUFFER_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(byteBuffer.capacity(), jArr);
        checkArgument(byteBuffer.isDirect(), ERROR_MSG_DATA_BUFFER_MUST_BE_DIRECT, new Object[0]);
        checkArgument(byteBuffer.order() == ByteOrder.nativeOrder(), ERROR_MSG_DATA_BUFFER_MUST_HAVE_NATIVE_BYTE_ORDER, new Object[0]);
        return new Tensor_uint8(byteBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlobUnsigned(ByteBuffer byteBuffer, long[] jArr) {
        return fromBlobUnsigned(byteBuffer, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(ByteBuffer byteBuffer, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(byteBuffer != null, ERROR_MSG_DATA_BUFFER_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(byteBuffer.capacity(), jArr);
        checkArgument(byteBuffer.isDirect(), ERROR_MSG_DATA_BUFFER_MUST_BE_DIRECT, new Object[0]);
        checkArgument(byteBuffer.order() == ByteOrder.nativeOrder(), ERROR_MSG_DATA_BUFFER_MUST_HAVE_NATIVE_BYTE_ORDER, new Object[0]);
        return new Tensor_int8(byteBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlob(ByteBuffer byteBuffer, long[] jArr) {
        return fromBlob(byteBuffer, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(IntBuffer intBuffer, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(intBuffer != null, ERROR_MSG_DATA_BUFFER_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(intBuffer.capacity(), jArr);
        checkArgument(intBuffer.isDirect(), ERROR_MSG_DATA_BUFFER_MUST_BE_DIRECT, new Object[0]);
        checkArgument(intBuffer.order() == ByteOrder.nativeOrder(), ERROR_MSG_DATA_BUFFER_MUST_HAVE_NATIVE_BYTE_ORDER, new Object[0]);
        return new Tensor_int32(intBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlob(IntBuffer intBuffer, long[] jArr) {
        return fromBlob(intBuffer, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(FloatBuffer floatBuffer, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(floatBuffer != null, ERROR_MSG_DATA_BUFFER_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(floatBuffer.capacity(), jArr);
        checkArgument(floatBuffer.isDirect(), ERROR_MSG_DATA_BUFFER_MUST_BE_DIRECT, new Object[0]);
        checkArgument(floatBuffer.order() == ByteOrder.nativeOrder(), ERROR_MSG_DATA_BUFFER_MUST_HAVE_NATIVE_BYTE_ORDER, new Object[0]);
        return new Tensor_float32(floatBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlob(FloatBuffer floatBuffer, long[] jArr) {
        return fromBlob(floatBuffer, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(LongBuffer longBuffer, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(longBuffer != null, ERROR_MSG_DATA_BUFFER_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(longBuffer.capacity(), jArr);
        checkArgument(longBuffer.isDirect(), ERROR_MSG_DATA_BUFFER_MUST_BE_DIRECT, new Object[0]);
        checkArgument(longBuffer.order() == ByteOrder.nativeOrder(), ERROR_MSG_DATA_BUFFER_MUST_HAVE_NATIVE_BYTE_ORDER, new Object[0]);
        return new Tensor_int64(longBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlob(LongBuffer longBuffer, long[] jArr) {
        return fromBlob(longBuffer, jArr, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor fromBlob(DoubleBuffer doubleBuffer, long[] jArr, MemoryFormat memoryFormat) {
        checkArgument(doubleBuffer != null, ERROR_MSG_DATA_BUFFER_NOT_NULL, new Object[0]);
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        checkShape(jArr);
        checkShapeAndDataCapacityConsistency(doubleBuffer.capacity(), jArr);
        checkArgument(doubleBuffer.isDirect(), ERROR_MSG_DATA_BUFFER_MUST_BE_DIRECT, new Object[0]);
        checkArgument(doubleBuffer.order() == ByteOrder.nativeOrder(), ERROR_MSG_DATA_BUFFER_MUST_HAVE_NATIVE_BYTE_ORDER, new Object[0]);
        return new Tensor_float64(doubleBuffer, jArr, memoryFormat);
    }

    public static Tensor fromBlob(DoubleBuffer doubleBuffer, long[] jArr) {
        return fromBlob(doubleBuffer, jArr, MemoryFormat.CONTIGUOUS);
    }

    private Tensor(long[] jArr, MemoryFormat memoryFormat) {
        checkShape(jArr);
        this.shape = Arrays.copyOf(jArr, jArr.length);
        this.memoryFormat = memoryFormat;
    }

    public long numel() {
        return numel(this.shape);
    }

    public static long numel(long[] jArr) {
        checkShape(jArr);
        int i = 1;
        for (long j : jArr) {
            i = (int) (((long) i) * j);
        }
        return i;
    }

    public long[] shape() {
        long[] jArr = this.shape;
        return Arrays.copyOf(jArr, jArr.length);
    }

    public MemoryFormat memoryFormat() {
        return this.memoryFormat;
    }

    int dtypeJniCode() {
        return dtype().jniCode;
    }

    int memoryFormatJniCode() {
        return this.memoryFormat.jniCode;
    }

    public byte[] getDataAsByteArray() {
        throw new IllegalStateException("Tensor of type " + getClass().getSimpleName() + " cannot return data as byte array.");
    }

    public byte[] getDataAsUnsignedByteArray() {
        throw new IllegalStateException("Tensor of type " + getClass().getSimpleName() + " cannot return data as byte array.");
    }

    public int[] getDataAsIntArray() {
        throw new IllegalStateException("Tensor of type " + getClass().getSimpleName() + " cannot return data as int array.");
    }

    public float[] getDataAsFloatArray() {
        throw new IllegalStateException("Tensor of type " + getClass().getSimpleName() + " cannot return data as float array.");
    }

    public long[] getDataAsLongArray() {
        throw new IllegalStateException("Tensor of type " + getClass().getSimpleName() + " cannot return data as long array.");
    }

    public double[] getDataAsDoubleArray() {
        throw new IllegalStateException("Tensor of type " + getClass().getSimpleName() + " cannot return data as double array.");
    }

    Buffer getRawDataBuffer() {
        throw new IllegalStateException("Tensor of type " + getClass().getSimpleName() + " cannot return raw data buffer.");
    }

    static class Tensor_uint8 extends Tensor {
        private final ByteBuffer data;

        private Tensor_uint8(ByteBuffer byteBuffer, long[] jArr, MemoryFormat memoryFormat) {
            super(jArr, memoryFormat);
            this.data = byteBuffer;
        }

        @Override // org.pytorch.Tensor
        public DType dtype() {
            return DType.UINT8;
        }

        @Override // org.pytorch.Tensor
        Buffer getRawDataBuffer() {
            return this.data;
        }

        @Override // org.pytorch.Tensor
        public byte[] getDataAsUnsignedByteArray() {
            this.data.rewind();
            byte[] bArr = new byte[this.data.remaining()];
            this.data.get(bArr);
            return bArr;
        }

        public String toString() {
            return String.format("Tensor(%s, dtype=torch.uint8)", Arrays.toString(this.shape));
        }
    }

    static class Tensor_int8 extends Tensor {
        private final ByteBuffer data;

        private Tensor_int8(ByteBuffer byteBuffer, long[] jArr, MemoryFormat memoryFormat) {
            super(jArr, memoryFormat);
            this.data = byteBuffer;
        }

        @Override // org.pytorch.Tensor
        public DType dtype() {
            return DType.INT8;
        }

        @Override // org.pytorch.Tensor
        Buffer getRawDataBuffer() {
            return this.data;
        }

        @Override // org.pytorch.Tensor
        public byte[] getDataAsByteArray() {
            this.data.rewind();
            byte[] bArr = new byte[this.data.remaining()];
            this.data.get(bArr);
            return bArr;
        }

        public String toString() {
            return String.format("Tensor(%s, dtype=torch.int8)", Arrays.toString(this.shape));
        }
    }

    static class Tensor_int32 extends Tensor {
        private final IntBuffer data;

        private Tensor_int32(IntBuffer intBuffer, long[] jArr, MemoryFormat memoryFormat) {
            super(jArr, memoryFormat);
            this.data = intBuffer;
        }

        @Override // org.pytorch.Tensor
        public DType dtype() {
            return DType.INT32;
        }

        @Override // org.pytorch.Tensor
        Buffer getRawDataBuffer() {
            return this.data;
        }

        @Override // org.pytorch.Tensor
        public int[] getDataAsIntArray() {
            this.data.rewind();
            int[] iArr = new int[this.data.remaining()];
            this.data.get(iArr);
            return iArr;
        }

        public String toString() {
            return String.format("Tensor(%s, dtype=torch.int32)", Arrays.toString(this.shape));
        }
    }

    static class Tensor_float32 extends Tensor {
        private final FloatBuffer data;

        Tensor_float32(FloatBuffer floatBuffer, long[] jArr, MemoryFormat memoryFormat) {
            super(jArr, memoryFormat);
            this.data = floatBuffer;
        }

        @Override // org.pytorch.Tensor
        public float[] getDataAsFloatArray() {
            this.data.rewind();
            float[] fArr = new float[this.data.remaining()];
            this.data.get(fArr);
            return fArr;
        }

        @Override // org.pytorch.Tensor
        public DType dtype() {
            return DType.FLOAT32;
        }

        @Override // org.pytorch.Tensor
        Buffer getRawDataBuffer() {
            return this.data;
        }

        public String toString() {
            return String.format("Tensor(%s, dtype=torch.float32)", Arrays.toString(this.shape));
        }
    }

    static class Tensor_int64 extends Tensor {
        private final LongBuffer data;

        private Tensor_int64(LongBuffer longBuffer, long[] jArr, MemoryFormat memoryFormat) {
            super(jArr, memoryFormat);
            this.data = longBuffer;
        }

        @Override // org.pytorch.Tensor
        public DType dtype() {
            return DType.INT64;
        }

        @Override // org.pytorch.Tensor
        Buffer getRawDataBuffer() {
            return this.data;
        }

        @Override // org.pytorch.Tensor
        public long[] getDataAsLongArray() {
            this.data.rewind();
            long[] jArr = new long[this.data.remaining()];
            this.data.get(jArr);
            return jArr;
        }

        public String toString() {
            return String.format("Tensor(%s, dtype=torch.int64)", Arrays.toString(this.shape));
        }
    }

    static class Tensor_float64 extends Tensor {
        private final DoubleBuffer data;

        private Tensor_float64(DoubleBuffer doubleBuffer, long[] jArr, MemoryFormat memoryFormat) {
            super(jArr, memoryFormat);
            this.data = doubleBuffer;
        }

        @Override // org.pytorch.Tensor
        public DType dtype() {
            return DType.FLOAT64;
        }

        @Override // org.pytorch.Tensor
        Buffer getRawDataBuffer() {
            return this.data;
        }

        @Override // org.pytorch.Tensor
        public double[] getDataAsDoubleArray() {
            this.data.rewind();
            double[] dArr = new double[this.data.remaining()];
            this.data.get(dArr);
            return dArr;
        }

        public String toString() {
            return String.format("Tensor(%s, dtype=torch.float64)", Arrays.toString(this.shape));
        }
    }

    private static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(Locale.US, str, objArr));
        }
    }

    private static void checkShape(long[] jArr) {
        checkArgument(jArr != null, ERROR_MSG_SHAPE_NOT_NULL, new Object[0]);
        for (long j : jArr) {
            checkArgument(j >= 0, ERROR_MSG_SHAPE_NON_NEGATIVE, new Object[0]);
        }
    }

    private static void checkShapeAndDataCapacityConsistency(int i, long[] jArr) {
        long jNumel = numel(jArr);
        checkArgument(jNumel == ((long) i), "Inconsistent data capacity:%d and shape number elements:%d shape:%s", Integer.valueOf(i), Long.valueOf(jNumel), Arrays.toString(jArr));
    }

    private static Tensor nativeNewTensor(ByteBuffer byteBuffer, long[] jArr, int i, int i2, HybridData hybridData) {
        Tensor tensor_int8;
        MemoryFormat memoryFormat = MemoryFormat.CONTIGUOUS;
        if (MemoryFormat.CHANNELS_LAST.jniCode == i2) {
            memoryFormat = MemoryFormat.CHANNELS_LAST;
        } else if (MemoryFormat.CHANNELS_LAST_3D.jniCode == i2) {
            memoryFormat = MemoryFormat.CHANNELS_LAST_3D;
        }
        if (DType.FLOAT32.jniCode == i) {
            tensor_int8 = new Tensor_float32(byteBuffer.asFloatBuffer(), jArr, memoryFormat);
        } else {
            if (DType.INT32.jniCode == i) {
                tensor_int8 = new Tensor_int32(byteBuffer.asIntBuffer(), jArr, memoryFormat);
            } else if (DType.INT64.jniCode == i) {
                tensor_int8 = new Tensor_int64(byteBuffer.asLongBuffer(), jArr, memoryFormat);
            } else if (DType.FLOAT64.jniCode == i) {
                tensor_int8 = new Tensor_float64(byteBuffer.asDoubleBuffer(), jArr, memoryFormat);
            } else if (DType.UINT8.jniCode == i) {
                tensor_int8 = new Tensor_uint8(byteBuffer, jArr, memoryFormat);
            } else if (DType.INT8.jniCode == i) {
                tensor_int8 = new Tensor_int8(byteBuffer, jArr, memoryFormat);
            } else {
                new IllegalArgumentException("Unknown Tensor dtype");
                tensor_int8 = null;
            }
        }
        tensor_int8.mHybridData = hybridData;
        return tensor_int8;
    }
}
