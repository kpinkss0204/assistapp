package ai.onnxruntime;

import ai.onnxruntime.OnnxValue;
import ai.onnxruntime.OrtUtil;
import ai.onnxruntime.platform.Fp16Conversions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class OnnxSparseTensor extends OnnxTensorLike {
    private static final Logger logger = Logger.getLogger(OnnxSparseTensor.class.getName());
    private final Buffer indices;
    private final LongBuffer innerIndices;
    private final SparseTensorType sparseTensorType;
    private final Buffer values;

    private native void close(long j, long j2);

    private static native long createCSRCSparseTensorFromBuffer(long j, long j2, Buffer buffer, int i, long j3, Buffer buffer2, int i2, long j4, Buffer buffer3, int i3, long[] jArr, long[] jArr2, int i4) throws OrtException;

    private static native long createSparseTensorFromBuffer(long j, long j2, Buffer buffer, int i, long j3, Buffer buffer2, int i2, long[] jArr, long[] jArr2, long[] jArr3, int i3, int i4) throws OrtException;

    private native ByteBuffer getIndicesBuffer(long j, long j2);

    private native long[] getIndicesShape(long j, long j2);

    private native ByteBuffer getInnerIndicesBuffer(long j, long j2);

    private native long[] getInnerIndicesShape(long j, long j2);

    private native ByteBuffer getValuesBuffer(long j, long j2);

    private native long[] getValuesShape(long j, long j2);

    OnnxSparseTensor(long j, long j2, int i, TensorInfo tensorInfo) {
        this(j, j2, SparseTensorType.mapFromInt(i), tensorInfo, null, null, null);
    }

    OnnxSparseTensor(long j, long j2, SparseTensorType sparseTensorType, TensorInfo tensorInfo, Buffer buffer, Buffer buffer2) {
        this(j, j2, sparseTensorType, tensorInfo, buffer, null, buffer2);
    }

    OnnxSparseTensor(long j, long j2, SparseTensorType sparseTensorType, TensorInfo tensorInfo, Buffer buffer, LongBuffer longBuffer, Buffer buffer2) {
        super(j, j2, tensorInfo);
        this.sparseTensorType = sparseTensorType;
        this.indices = buffer;
        this.innerIndices = longBuffer;
        this.values = buffer2;
    }

    public static <T extends Buffer> OnnxSparseTensor createSparseTensor(OrtEnvironment ortEnvironment, SparseTensor<T> sparseTensor) throws OrtException {
        return createSparseTensor(ortEnvironment, ortEnvironment.defaultAllocator, sparseTensor);
    }

    static <T extends Buffer> OnnxSparseTensor createSparseTensor(OrtEnvironment ortEnvironment, OrtAllocator ortAllocator, SparseTensor<T> sparseTensor) throws OrtException {
        if (!ortAllocator.isClosed()) {
            TensorInfo tensorInfoConstructFromSparseTensor = TensorInfo.constructFromSparseTensor(sparseTensor);
            OnnxJavaType indicesType = sparseTensor.getIndicesType();
            OrtUtil.BufferTuple bufferTuplePrepareBuffer = OrtUtil.prepareBuffer(sparseTensor.getIndices(), indicesType);
            OrtUtil.BufferTuple bufferTuplePrepareBuffer2 = OrtUtil.prepareBuffer(sparseTensor.getValues(), tensorInfoConstructFromSparseTensor.type);
            if (!(bufferTuplePrepareBuffer.data instanceof LongBuffer) && !(bufferTuplePrepareBuffer.data instanceof IntBuffer)) {
                throw new IllegalStateException("Unexpected type of indices buffer, found " + bufferTuplePrepareBuffer.data.getClass() + ", expected IntBuffer or LongBuffer");
            }
            int i = AnonymousClass1.$SwitchMap$ai$onnxruntime$OnnxSparseTensor$SparseTensorType[sparseTensor.getSparsityType().ordinal()];
            if (i == 1 || i == 2) {
                return new OnnxSparseTensor(createSparseTensorFromBuffer(OnnxRuntime.ortApiHandle, ortAllocator.handle, bufferTuplePrepareBuffer.data, bufferTuplePrepareBuffer.pos, bufferTuplePrepareBuffer.size, bufferTuplePrepareBuffer2.data, bufferTuplePrepareBuffer2.pos, tensorInfoConstructFromSparseTensor.shape, sparseTensor.getIndicesShape(), sparseTensor.getValuesShape(), tensorInfoConstructFromSparseTensor.onnxType.value, sparseTensor.getSparsityType().value), ortAllocator.handle, sparseTensor.getSparsityType(), tensorInfoConstructFromSparseTensor, bufferTuplePrepareBuffer.data, bufferTuplePrepareBuffer2.data);
            }
            if (i == 3) {
                OrtUtil.BufferTuple bufferTuplePrepareBuffer3 = OrtUtil.prepareBuffer(((CSRCTensor) sparseTensor).getInnerIndices(), indicesType);
                return new OnnxSparseTensor(createCSRCSparseTensorFromBuffer(OnnxRuntime.ortApiHandle, ortAllocator.handle, bufferTuplePrepareBuffer.data, bufferTuplePrepareBuffer.pos, bufferTuplePrepareBuffer.size, bufferTuplePrepareBuffer3.data, bufferTuplePrepareBuffer3.pos, bufferTuplePrepareBuffer3.size, bufferTuplePrepareBuffer2.data, bufferTuplePrepareBuffer2.pos, tensorInfoConstructFromSparseTensor.shape, sparseTensor.getValuesShape(), tensorInfoConstructFromSparseTensor.onnxType.value), ortAllocator.handle, sparseTensor.getSparsityType(), tensorInfoConstructFromSparseTensor, bufferTuplePrepareBuffer.data, (LongBuffer) bufferTuplePrepareBuffer3.data, bufferTuplePrepareBuffer2.data);
            }
            throw new IllegalArgumentException("Cannot create an UNDEFINED sparse tensor.");
        }
        throw new IllegalStateException("Trying to create an OnnxSparseTensor on a closed OrtAllocator.");
    }

    @Override // ai.onnxruntime.OnnxValue
    public OnnxValue.OnnxValueType getType() {
        return OnnxValue.OnnxValueType.ONNX_TYPE_SPARSETENSOR;
    }

    @Override // ai.onnxruntime.OnnxValue
    public SparseTensor<? extends Buffer> getValue() throws OrtException {
        checkClosed();
        Buffer valuesBuffer = getValuesBuffer();
        long[] indicesShape = getIndicesShape(OnnxRuntime.ortApiHandle, this.nativeHandle);
        int i = AnonymousClass1.$SwitchMap$ai$onnxruntime$OnnxSparseTensor$SparseTensorType[this.sparseTensorType.ordinal()];
        if (i == 1) {
            return new COOTensor((LongBuffer) getIndicesBuffer(), indicesShape, valuesBuffer, this.info.shape, this.info.type, valuesBuffer.remaining());
        }
        if (i == 2) {
            return new BlockSparseTensor((IntBuffer) getIndicesBuffer(), indicesShape, valuesBuffer, getValuesShape(OnnxRuntime.ortApiHandle, this.nativeHandle), this.info.shape, this.info.type, valuesBuffer.remaining());
        }
        if (i == 3) {
            return new CSRCTensor((LongBuffer) getIndicesBuffer(), getInnerIndicesBuffer(), valuesBuffer, this.info.shape, this.info.type, valuesBuffer.remaining());
        }
        throw new IllegalStateException("Undefined sparsity type in this sparse tensor.");
    }

    @Override // ai.onnxruntime.OnnxValue, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.closed) {
            close(OnnxRuntime.ortApiHandle, this.nativeHandle);
            this.closed = true;
        } else {
            logger.warning("Closing an already closed OnnxSparseTensor.");
        }
    }

    public SparseTensorType getSparseTensorType() {
        return this.sparseTensorType;
    }

    public Buffer getIndicesBuffer() {
        checkClosed();
        int i = AnonymousClass1.$SwitchMap$ai$onnxruntime$OnnxSparseTensor$SparseTensorType[this.sparseTensorType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                IntBuffer intBufferAsIntBuffer = getIndicesBuffer(OnnxRuntime.ortApiHandle, this.nativeHandle).order(ByteOrder.nativeOrder()).asIntBuffer();
                IntBuffer intBufferAllocate = IntBuffer.allocate(intBufferAsIntBuffer.capacity());
                intBufferAllocate.put(intBufferAsIntBuffer);
                intBufferAllocate.rewind();
                return intBufferAllocate;
            }
            if (i != 3) {
                throw new IllegalStateException("UNDEFINED sparse tensor type.");
            }
        }
        LongBuffer longBufferAsLongBuffer = getIndicesBuffer(OnnxRuntime.ortApiHandle, this.nativeHandle).order(ByteOrder.nativeOrder()).asLongBuffer();
        LongBuffer longBufferAllocate = LongBuffer.allocate(longBufferAsLongBuffer.capacity());
        longBufferAllocate.put(longBufferAsLongBuffer);
        longBufferAllocate.rewind();
        return longBufferAllocate;
    }

    public LongBuffer getInnerIndicesBuffer() {
        checkClosed();
        if (this.sparseTensorType == SparseTensorType.CSRC) {
            LongBuffer longBufferAsLongBuffer = getInnerIndicesBuffer(OnnxRuntime.ortApiHandle, this.nativeHandle).order(ByteOrder.nativeOrder()).asLongBuffer();
            LongBuffer longBufferAllocate = LongBuffer.allocate(longBufferAsLongBuffer.capacity());
            longBufferAllocate.put(longBufferAsLongBuffer);
            longBufferAllocate.rewind();
            return longBufferAllocate;
        }
        throw new IllegalStateException("Inner indices are only available for CSRC sparse tensors, this sparse tensor is " + this.sparseTensorType);
    }

    public Buffer getValuesBuffer() {
        checkClosed();
        ByteBuffer byteBufferOrder = getValuesBuffer(OnnxRuntime.ortApiHandle, this.nativeHandle).order(ByteOrder.nativeOrder());
        switch (AnonymousClass1.$SwitchMap$ai$onnxruntime$OnnxJavaType[this.info.type.ordinal()]) {
            case 1:
                FloatBuffer floatBufferAsFloatBuffer = byteBufferOrder.asFloatBuffer();
                FloatBuffer floatBufferAllocate = FloatBuffer.allocate(floatBufferAsFloatBuffer.capacity());
                floatBufferAllocate.put(floatBufferAsFloatBuffer);
                floatBufferAllocate.rewind();
                return floatBufferAllocate;
            case 2:
                return Fp16Conversions.convertFp16BufferToFloatBuffer(byteBufferOrder.asShortBuffer());
            case 3:
                return Fp16Conversions.convertBf16BufferToFloatBuffer(byteBufferOrder.asShortBuffer());
            case 4:
                DoubleBuffer doubleBufferAsDoubleBuffer = byteBufferOrder.asDoubleBuffer();
                DoubleBuffer doubleBufferAllocate = DoubleBuffer.allocate(doubleBufferAsDoubleBuffer.capacity());
                doubleBufferAllocate.put(doubleBufferAsDoubleBuffer);
                doubleBufferAllocate.rewind();
                return doubleBufferAllocate;
            case 5:
                ShortBuffer shortBufferAsShortBuffer = byteBufferOrder.asShortBuffer();
                ShortBuffer shortBufferAllocate = ShortBuffer.allocate(shortBufferAsShortBuffer.capacity());
                shortBufferAllocate.put(shortBufferAsShortBuffer);
                shortBufferAllocate.rewind();
                return shortBufferAllocate;
            case 6:
                IntBuffer intBufferAsIntBuffer = byteBufferOrder.asIntBuffer();
                IntBuffer intBufferAllocate = IntBuffer.allocate(intBufferAsIntBuffer.capacity());
                intBufferAllocate.put(intBufferAsIntBuffer);
                intBufferAllocate.rewind();
                return intBufferAllocate;
            case 7:
                LongBuffer longBufferAsLongBuffer = byteBufferOrder.asLongBuffer();
                LongBuffer longBufferAllocate = LongBuffer.allocate(longBufferAsLongBuffer.capacity());
                longBufferAllocate.put(longBufferAsLongBuffer);
                longBufferAllocate.rewind();
                return longBufferAllocate;
            case 8:
            case 9:
            case 10:
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBufferOrder.capacity());
                byteBufferAllocate.put(byteBufferOrder);
                byteBufferAllocate.rewind();
                return byteBufferAllocate;
            case 11:
                throw new IllegalStateException("Unsupported data type String");
            default:
                throw new IllegalStateException("Unsupported data type");
        }
    }

    /* JADX INFO: renamed from: ai.onnxruntime.OnnxSparseTensor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ai$onnxruntime$OnnxJavaType;
        static final /* synthetic */ int[] $SwitchMap$ai$onnxruntime$OnnxSparseTensor$SparseTensorType;

        static {
            int[] iArr = new int[OnnxJavaType.values().length];
            $SwitchMap$ai$onnxruntime$OnnxJavaType = iArr;
            try {
                iArr[OnnxJavaType.FLOAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.FLOAT16.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.BFLOAT16.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.INT16.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.INT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.INT64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.INT8.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.UINT8.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.STRING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.UNKNOWN.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr2 = new int[SparseTensorType.values().length];
            $SwitchMap$ai$onnxruntime$OnnxSparseTensor$SparseTensorType = iArr2;
            try {
                iArr2[SparseTensorType.COO.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxSparseTensor$SparseTensorType[SparseTensorType.BLOCK_SPARSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxSparseTensor$SparseTensorType[SparseTensorType.CSRC.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxSparseTensor$SparseTensorType[SparseTensorType.UNDEFINED.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public long[] getIndicesShape() {
        checkClosed();
        return getIndicesShape(OnnxRuntime.ortApiHandle, this.nativeHandle);
    }

    public long[] getInnerIndicesShape() {
        checkClosed();
        if (this.sparseTensorType == SparseTensorType.CSRC) {
            return getInnerIndicesShape(OnnxRuntime.ortApiHandle, this.nativeHandle);
        }
        throw new IllegalStateException("Inner indices are only available for CSRC sparse tensors, this sparse tensor is " + this.sparseTensorType);
    }

    public long[] getValuesShape() {
        checkClosed();
        return getValuesShape(OnnxRuntime.ortApiHandle, this.nativeHandle);
    }

    public enum SparseTensorType {
        UNDEFINED(0),
        COO(1),
        CSRC(2),
        BLOCK_SPARSE(4);

        private static final SparseTensorType[] values;
        public final int value;

        static {
            SparseTensorType sparseTensorType = UNDEFINED;
            values = new SparseTensorType[]{sparseTensorType, COO, CSRC, sparseTensorType, BLOCK_SPARSE};
        }

        SparseTensorType(int i) {
            this.value = i;
        }

        public static SparseTensorType mapFromInt(int i) {
            if (i > 0) {
                SparseTensorType[] sparseTensorTypeArr = values;
                if (i < sparseTensorTypeArr.length) {
                    return sparseTensorTypeArr[i];
                }
            }
            return UNDEFINED;
        }
    }

    public static abstract class SparseTensor<T extends Buffer> {
        private final long[] denseShape;
        final T indices;
        private final long[] indicesShape;
        private final long numNonZero;
        private final OnnxJavaType type;
        final Buffer values;
        private final long[] valuesShape;

        public abstract OnnxJavaType getIndicesType();

        public abstract SparseTensorType getSparsityType();

        SparseTensor(T t, long[] jArr, Buffer buffer, long[] jArr2, long[] jArr3, OnnxJavaType onnxJavaType, long j) {
            this.indices = t;
            this.indicesShape = jArr;
            this.values = buffer;
            this.valuesShape = jArr2;
            this.denseShape = jArr3;
            this.type = onnxJavaType;
            this.numNonZero = j;
            if (buffer.remaining() != j) {
                throw new IllegalArgumentException("Expected numNonZero and data.remaining to be equal, found " + j + " and " + buffer.remaining() + " respectively");
            }
            if (onnxJavaType == OnnxJavaType.STRING) {
                throw new IllegalArgumentException("String SparseTensors are not supported.");
            }
        }

        public long[] getDenseShape() {
            return this.denseShape;
        }

        public OnnxJavaType getType() {
            return this.type;
        }

        public long getNumNonZeroElements() {
            return this.numNonZero;
        }

        public T getIndices() {
            return this.indices;
        }

        public Buffer getValues() {
            return this.values;
        }

        public long[] getValuesShape() {
            return this.valuesShape;
        }

        public long[] getIndicesShape() {
            return this.indicesShape;
        }
    }

    public static final class COOTensor extends SparseTensor<LongBuffer> {
        public COOTensor(LongBuffer longBuffer, long[] jArr, Buffer buffer, long[] jArr2, OnnxJavaType onnxJavaType, long j) {
            super(longBuffer, jArr, buffer, new long[]{j}, jArr2, onnxJavaType, j);
            if (jArr.length > 2 || jArr.length == 0 || jArr[0] != j) {
                throw new IllegalArgumentException("Invalid indices shape, expected [numNonZero, dimension] or [numNonZero] found " + Arrays.toString(jArr));
            }
            long jElementCount = OrtUtil.elementCount(jArr);
            if (jElementCount != longBuffer.remaining()) {
                throw new IllegalArgumentException("Unexpected number of indices found in buffer, expected " + jElementCount + " found " + longBuffer.remaining());
            }
            if (buffer.remaining() != j) {
                throw new IllegalArgumentException("Expected data.remaining() - " + buffer.remaining() + " to equal numNonZero - " + j);
            }
        }

        @Override // ai.onnxruntime.OnnxSparseTensor.SparseTensor
        public OnnxJavaType getIndicesType() {
            return OnnxJavaType.INT64;
        }

        @Override // ai.onnxruntime.OnnxSparseTensor.SparseTensor
        public SparseTensorType getSparsityType() {
            return SparseTensorType.COO;
        }
    }

    public static final class CSRCTensor extends SparseTensor<LongBuffer> {
        private final LongBuffer innerIndices;

        public CSRCTensor(LongBuffer longBuffer, LongBuffer longBuffer2, Buffer buffer, long[] jArr, OnnxJavaType onnxJavaType, long j) {
            super(longBuffer, new long[]{longBuffer.remaining()}, buffer, new long[]{j}, jArr, onnxJavaType, j);
            this.innerIndices = longBuffer2;
            long j2 = jArr[0] + 1;
            if (longBuffer.remaining() != j2) {
                throw new IllegalArgumentException("Outer indices should be equal to the number of rows + 1 in the dense shape, found " + longBuffer.remaining() + ", expected " + j2);
            }
            if (longBuffer2.remaining() != j) {
                throw new IllegalArgumentException("Inner indices should be equal to the number of non-zero elements, found " + longBuffer2.remaining() + ", expected " + j);
            }
        }

        public long[] getInnerIndicesShape() {
            return new long[]{this.innerIndices.remaining()};
        }

        public LongBuffer getInnerIndices() {
            return this.innerIndices;
        }

        @Override // ai.onnxruntime.OnnxSparseTensor.SparseTensor
        public OnnxJavaType getIndicesType() {
            return OnnxJavaType.INT64;
        }

        @Override // ai.onnxruntime.OnnxSparseTensor.SparseTensor
        public SparseTensorType getSparsityType() {
            return SparseTensorType.CSRC;
        }
    }

    public static final class BlockSparseTensor extends SparseTensor<IntBuffer> {
        public BlockSparseTensor(IntBuffer intBuffer, long[] jArr, Buffer buffer, long[] jArr2, long[] jArr3, OnnxJavaType onnxJavaType, long j) {
            super(intBuffer, jArr, buffer, jArr2, jArr3, onnxJavaType, j);
            if (OrtUtil.elementCount(jArr2) != j) {
                throw new IllegalArgumentException("Expected " + j + " entries in the data shape, found " + Arrays.toString(jArr2));
            }
            if (j != buffer.remaining()) {
                throw new IllegalArgumentException("Expected " + j + " elements in the data buffer, found " + buffer.remaining());
            }
            if (OrtUtil.elementCount(jArr) != intBuffer.remaining()) {
                throw new IllegalArgumentException("Expected " + OrtUtil.elementCount(jArr) + " elements in the indices buffer, found " + intBuffer.remaining());
            }
            if (jArr2.length < 3) {
                throw new IllegalArgumentException("Expected [numBlocks, blockSize, blockSize] or larger, but data shape was " + Arrays.toString(jArr2));
            }
            if (jArr.length < 2) {
                throw new IllegalArgumentException("Expected [numBlocks, co-ordinates] or larger, but indices shape was " + Arrays.toString(jArr));
            }
        }

        @Override // ai.onnxruntime.OnnxSparseTensor.SparseTensor
        public OnnxJavaType getIndicesType() {
            return OnnxJavaType.INT32;
        }

        @Override // ai.onnxruntime.OnnxSparseTensor.SparseTensor
        public SparseTensorType getSparsityType() {
            return SparseTensorType.BLOCK_SPARSE;
        }
    }
}
