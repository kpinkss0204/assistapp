package ai.onnxruntime;

import ai.onnxruntime.TensorInfo;

/* JADX INFO: loaded from: classes.dex */
public enum OnnxJavaType {
    FLOAT(1, Float.TYPE, 4),
    DOUBLE(2, Double.TYPE, 8),
    INT8(3, Byte.TYPE, 1),
    INT16(4, Short.TYPE, 2),
    INT32(5, Integer.TYPE, 4),
    INT64(6, Long.TYPE, 8),
    BOOL(7, Boolean.TYPE, 1),
    STRING(8, String.class, 4),
    UINT8(9, Byte.TYPE, 1),
    FLOAT16(10, Short.TYPE, 2),
    BFLOAT16(11, Short.TYPE, 2),
    UNKNOWN(0, Object.class, 0);

    private static final OnnxJavaType[] values;
    public final Class<?> clazz;
    public final int size;
    public final int value;

    static {
        OnnxJavaType[] onnxJavaTypeArrValues = values();
        values = new OnnxJavaType[onnxJavaTypeArrValues.length];
        for (OnnxJavaType onnxJavaType : onnxJavaTypeArrValues) {
            values[onnxJavaType.value] = onnxJavaType;
        }
    }

    OnnxJavaType(int i, Class cls, int i2) {
        this.value = i;
        this.clazz = cls;
        this.size = i2;
    }

    public static OnnxJavaType mapFromInt(int i) {
        if (i > 0) {
            OnnxJavaType[] onnxJavaTypeArr = values;
            if (i < onnxJavaTypeArr.length) {
                return onnxJavaTypeArr[i];
            }
        }
        return UNKNOWN;
    }

    /* JADX INFO: renamed from: ai.onnxruntime.OnnxJavaType$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType;

        static {
            int[] iArr = new int[TensorInfo.OnnxTensorType.values().length];
            $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType = iArr;
            try {
                iArr[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_UINT8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_INT8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_UINT16.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_INT16.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_UINT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_INT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_UINT64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_INT64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT16.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_BFLOAT16.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_FLOAT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_DOUBLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_STRING.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_BOOL.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_UNDEFINED.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_COMPLEX64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[TensorInfo.OnnxTensorType.ONNX_TENSOR_ELEMENT_DATA_TYPE_COMPLEX128.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public static OnnxJavaType mapFromOnnxTensorType(TensorInfo.OnnxTensorType onnxTensorType) {
        switch (AnonymousClass1.$SwitchMap$ai$onnxruntime$TensorInfo$OnnxTensorType[onnxTensorType.ordinal()]) {
            case 1:
                return UINT8;
            case 2:
                return INT8;
            case 3:
            case 4:
                return INT16;
            case 5:
            case 6:
                return INT32;
            case 7:
            case 8:
                return INT64;
            case 9:
                return FLOAT16;
            case 10:
                return BFLOAT16;
            case 11:
                return FLOAT;
            case 12:
                return DOUBLE;
            case 13:
                return STRING;
            case 14:
                return BOOL;
            default:
                return UNKNOWN;
        }
    }

    public static OnnxJavaType mapFromClass(Class<?> cls) {
        if (cls.equals(Byte.TYPE) || cls.equals(Byte.class)) {
            return INT8;
        }
        if (cls.equals(Short.TYPE) || cls.equals(Short.class)) {
            return INT16;
        }
        if (cls.equals(Integer.TYPE) || cls.equals(Integer.class)) {
            return INT32;
        }
        if (cls.equals(Long.TYPE) || cls.equals(Long.class)) {
            return INT64;
        }
        if (cls.equals(Float.TYPE) || cls.equals(Float.class)) {
            return FLOAT;
        }
        if (cls.equals(Double.TYPE) || cls.equals(Double.class)) {
            return DOUBLE;
        }
        if (cls.equals(Boolean.TYPE) || cls.equals(Boolean.class)) {
            return BOOL;
        }
        if (cls.equals(String.class)) {
            return STRING;
        }
        return UNKNOWN;
    }
}
