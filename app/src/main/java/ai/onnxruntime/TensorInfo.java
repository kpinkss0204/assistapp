package ai.onnxruntime;

import ai.onnxruntime.OnnxSparseTensor;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class TensorInfo implements ValueInfo {
    public static final int MAX_DIMENSIONS = 8;
    final String[] dimensionNames;
    private final boolean hasNames;
    final long numElements;
    public final OnnxTensorType onnxType;
    final long[] shape;
    public final OnnxJavaType type;

    public enum OnnxTensorType {
        ONNX_TENSOR_ELEMENT_DATA_TYPE_UNDEFINED(0),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_UINT8(1),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_INT8(2),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_UINT16(3),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_INT16(4),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_UINT32(5),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_INT32(6),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_UINT64(7),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_INT64(8),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT16(9),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT(10),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_DOUBLE(11),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_STRING(12),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_BOOL(13),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_COMPLEX64(14),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_COMPLEX128(15),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_BFLOAT16(16),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT8E4M3FN(17),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT8E4M3FNUZ(18),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT8E5M2(19),
        ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT8E5M2FNUZ(20);

        private static final OnnxTensorType[] values = new OnnxTensorType[21];
        public final int value;

        static {
            for (OnnxTensorType onnxTensorType : values()) {
                values[onnxTensorType.value] = onnxTensorType;
            }
        }

        OnnxTensorType(int i) {
            this.value = i;
        }

        public static OnnxTensorType mapFromInt(int i) {
            if (i > 0) {
                OnnxTensorType[] onnxTensorTypeArr = values;
                if (i < onnxTensorTypeArr.length) {
                    return onnxTensorTypeArr[i];
                }
            }
            return ONNX_TENSOR_ELEMENT_DATA_TYPE_UNDEFINED;
        }

        public static OnnxTensorType mapFromJavaType(OnnxJavaType onnxJavaType) {
            switch (AnonymousClass1.$SwitchMap$ai$onnxruntime$OnnxJavaType[onnxJavaType.ordinal()]) {
                case 1:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT;
                case 2:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_DOUBLE;
                case 3:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_INT8;
                case 4:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_UINT8;
                case 5:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_INT16;
                case 6:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_INT32;
                case 7:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_INT64;
                case 8:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_BOOL;
                case 9:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_STRING;
                case 10:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT16;
                case 11:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_BFLOAT16;
                default:
                    return ONNX_TENSOR_ELEMENT_DATA_TYPE_UNDEFINED;
            }
        }
    }

    /* JADX INFO: renamed from: ai.onnxruntime.TensorInfo$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ai$onnxruntime$OnnxJavaType;

        static {
            int[] iArr = new int[OnnxJavaType.values().length];
            $SwitchMap$ai$onnxruntime$OnnxJavaType = iArr;
            try {
                iArr[OnnxJavaType.FLOAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.DOUBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.INT8.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.UINT8.ordinal()] = 4;
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
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.STRING.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.FLOAT16.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.BFLOAT16.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OnnxJavaType[OnnxJavaType.UNKNOWN.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    TensorInfo(long[] jArr, OnnxJavaType onnxJavaType, OnnxTensorType onnxTensorType) {
        this.shape = jArr;
        String[] strArr = new String[jArr.length];
        this.dimensionNames = strArr;
        Arrays.fill(strArr, "");
        this.hasNames = false;
        this.type = onnxJavaType;
        this.onnxType = onnxTensorType;
        this.numElements = elementCount(jArr);
    }

    TensorInfo(long[] jArr, String[] strArr, int i) {
        this.shape = jArr;
        this.dimensionNames = strArr;
        int length = strArr.length;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (!strArr[i2].isEmpty()) {
                z = true;
                break;
            }
            i2++;
        }
        this.hasNames = z;
        OnnxTensorType onnxTensorTypeMapFromInt = OnnxTensorType.mapFromInt(i);
        this.onnxType = onnxTensorTypeMapFromInt;
        this.type = OnnxJavaType.mapFromOnnxTensorType(onnxTensorTypeMapFromInt);
        this.numElements = elementCount(jArr);
    }

    public long[] getShape() {
        long[] jArr = this.shape;
        return Arrays.copyOf(jArr, jArr.length);
    }

    public String[] getDimensionNames() {
        String[] strArr = this.dimensionNames;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String toString() {
        String str = "TensorInfo(javaType=" + this.type.toString() + ",onnxType=" + this.onnxType.toString() + ",shape=" + Arrays.toString(this.shape);
        if (this.hasNames) {
            str = str + ",dimNames=[" + ((String) Arrays.stream(this.dimensionNames).map(new Function() { // from class: ai.onnxruntime.TensorInfo$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TensorInfo.lambda$toString$0((String) obj);
                }
            }).collect(Collectors.joining(","))) + "]";
        }
        return str + ")";
    }

    static /* synthetic */ String lambda$toString$0(String str) {
        return str.isEmpty() ? "\"\"" : str;
    }

    public boolean isScalar() {
        return this.shape.length == 0;
    }

    private boolean validateShape() {
        return OrtUtil.validateShape(this.shape);
    }

    private static long elementCount(long[] jArr) {
        long j = 1;
        for (long j2 : jArr) {
            j *= j2;
        }
        return j;
    }

    public long getNumElements() {
        return this.numElements;
    }

    public Object makeCarrier() throws OrtException {
        if (!validateShape() && this.numElements != 0) {
            throw new OrtException("This tensor is not representable in Java, it's too big - shape = " + Arrays.toString(this.shape));
        }
        int i = AnonymousClass1.$SwitchMap$ai$onnxruntime$OnnxJavaType[this.type.ordinal()];
        if (i != 12) {
            switch (i) {
                case 1:
                    return OrtUtil.newFloatArray(this.shape);
                case 2:
                    return OrtUtil.newDoubleArray(this.shape);
                case 3:
                case 4:
                    return OrtUtil.newByteArray(this.shape);
                case 5:
                    return OrtUtil.newShortArray(this.shape);
                case 6:
                    return OrtUtil.newIntArray(this.shape);
                case 7:
                    return OrtUtil.newLongArray(this.shape);
                case 8:
                    return OrtUtil.newBooleanArray(this.shape);
                case 9:
                    return new String[(int) OrtUtil.elementCount(this.shape)];
                default:
                    throw new OrtException("Unsupported type - " + this.type);
            }
        }
        throw new OrtException("Can't construct a carrier for an invalid type.");
    }

    public static TensorInfo constructFromJavaArray(Object obj) throws OrtException {
        Class<?> componentType = obj.getClass();
        if (!componentType.isArray()) {
            OnnxJavaType onnxJavaTypeMapFromClass = OnnxJavaType.mapFromClass(componentType);
            if (onnxJavaTypeMapFromClass == OnnxJavaType.UNKNOWN) {
                throw new OrtException("Cannot convert " + componentType + " to a OnnxTensor.");
            }
            return new TensorInfo(new long[0], onnxJavaTypeMapFromClass, OnnxTensorType.mapFromJavaType(onnxJavaTypeMapFromClass));
        }
        int i = 0;
        while (componentType.isArray()) {
            componentType = componentType.getComponentType();
            i++;
        }
        if (!componentType.isPrimitive() && !componentType.equals(String.class)) {
            throw new OrtException("Cannot create an OnnxTensor from a base type of " + componentType);
        }
        if (i > 8) {
            throw new OrtException("Cannot create an OnnxTensor with more than 8 dimensions. Found " + i + " dimensions.");
        }
        OnnxJavaType onnxJavaTypeMapFromClass2 = OnnxJavaType.mapFromClass(componentType);
        long[] jArr = new long[i];
        extractShape(jArr, 0, obj);
        return new TensorInfo(jArr, onnxJavaTypeMapFromClass2, OnnxTensorType.mapFromJavaType(onnxJavaTypeMapFromClass2));
    }

    public static TensorInfo constructFromBuffer(Buffer buffer, long[] jArr, OnnxJavaType onnxJavaType) throws OrtException {
        if (onnxJavaType == OnnxJavaType.STRING || onnxJavaType == OnnxJavaType.UNKNOWN) {
            throw new OrtException("Cannot create a tensor from a string or unknown buffer.");
        }
        long jElementCount = OrtUtil.elementCount(jArr);
        long jRemaining = buffer.remaining();
        if (jElementCount != jRemaining && jElementCount != jRemaining / ((long) onnxJavaType.size)) {
            throw new OrtException("Shape " + Arrays.toString(jArr) + ", requires " + jElementCount + " elements but the buffer has " + jRemaining + " elements.");
        }
        return new TensorInfo(Arrays.copyOf(jArr, jArr.length), onnxJavaType, OnnxTensorType.mapFromJavaType(onnxJavaType));
    }

    public static <T extends Buffer> TensorInfo constructFromSparseTensor(OnnxSparseTensor.SparseTensor<T> sparseTensor) throws OrtException {
        long[] denseShape = sparseTensor.getDenseShape();
        long jElementCount = OrtUtil.elementCount(denseShape);
        long jRemaining = sparseTensor.getValues().remaining();
        if (jElementCount < jRemaining) {
            throw new OrtException("Shape " + Arrays.toString(denseShape) + ", has at most " + jElementCount + " elements but the buffer has " + jRemaining + " elements.");
        }
        return new TensorInfo(Arrays.copyOf(denseShape, denseShape.length), sparseTensor.getType(), OnnxTensorType.mapFromJavaType(sparseTensor.getType()));
    }

    private static void extractShape(long[] jArr, int i, Object obj) throws OrtException {
        if (jArr.length != i) {
            int length = Array.getLength(obj);
            if (length == 0) {
                throw new OrtException("Supplied array has a zero dimension at " + i + ", all dimensions must be positive");
            }
            long j = jArr[i];
            if (j == 0) {
                jArr[i] = length;
            } else if (j != length) {
                throw new OrtException("Supplied array is ragged, expected " + jArr[i] + ", found " + length);
            }
            for (int i2 = 0; i2 < length; i2++) {
                extractShape(jArr, i + 1, Array.get(obj, i2));
            }
        }
    }
}
