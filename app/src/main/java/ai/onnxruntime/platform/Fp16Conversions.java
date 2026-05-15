package ai.onnxruntime.platform;

import androidx.compose.ui.layout.LayoutKt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.logging.Logger;
import kotlin.jvm.internal.ShortCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class Fp16Conversions {
    private static final Logger logger = Logger.getLogger(Fp16Conversions.class.getName());

    private Fp16Conversions() {
    }

    public static ShortBuffer convertFloatBufferToFp16Buffer(FloatBuffer floatBuffer) {
        int iPosition = floatBuffer.position();
        int iRemaining = floatBuffer.remaining();
        ShortBuffer shortBufferAsShortBuffer = ByteBuffer.allocateDirect(iRemaining * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
        for (int i = 0; i < iRemaining; i++) {
            shortBufferAsShortBuffer.put(i, floatToFp16(floatBuffer.get(i + iPosition)));
        }
        return shortBufferAsShortBuffer;
    }

    public static FloatBuffer convertFp16BufferToFloatBuffer(ShortBuffer shortBuffer) {
        int iPosition = shortBuffer.position();
        int iRemaining = shortBuffer.remaining();
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(iRemaining * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        for (int i = 0; i < iRemaining; i++) {
            floatBufferAsFloatBuffer.put(i, fp16ToFloat(shortBuffer.get(i + iPosition)));
        }
        return floatBufferAsFloatBuffer;
    }

    public static ShortBuffer convertFloatBufferToBf16Buffer(FloatBuffer floatBuffer) {
        int iPosition = floatBuffer.position();
        int iRemaining = floatBuffer.remaining();
        ShortBuffer shortBufferAsShortBuffer = ByteBuffer.allocateDirect(iRemaining * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
        for (int i = 0; i < iRemaining; i++) {
            shortBufferAsShortBuffer.put(i, floatToBf16(floatBuffer.get(i + iPosition)));
        }
        return shortBufferAsShortBuffer;
    }

    public static FloatBuffer convertBf16BufferToFloatBuffer(ShortBuffer shortBuffer) {
        int iPosition = shortBuffer.position();
        int iRemaining = shortBuffer.remaining();
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(iRemaining * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        for (int i = 0; i < iRemaining; i++) {
            floatBufferAsFloatBuffer.put(i, bf16ToFloat(shortBuffer.get(i + iPosition)));
        }
        return floatBufferAsFloatBuffer;
    }

    public static float fp16ToFloat(short s) {
        return mlasFp16ToFloat(s);
    }

    public static short floatToFp16(float f) {
        return mlasFloatToFp16(f);
    }

    public static float mlasFp16ToFloat(short s) {
        int i = (s & ShortCompanionObject.MAX_VALUE) << 13;
        int i2 = i & 260046848;
        int iFloatToIntBits = 939524096 + i;
        if (i2 == 260046848) {
            iFloatToIntBits = i + 1879048192;
        } else if (i2 == 0) {
            iFloatToIntBits = Float.floatToIntBits(Float.intBitsToFloat(i + 947912704) - Float.intBitsToFloat(947912704));
        }
        return Float.intBitsToFloat(((s & ShortCompanionObject.MIN_VALUE) << 16) | iFloatToIntBits);
    }

    public static short mlasFloatToFp16(float f) {
        short sFloatToIntBits;
        int iFloatToIntBits = Float.floatToIntBits(f);
        int iFloatToIntBits2 = Float.floatToIntBits(Float.POSITIVE_INFINITY);
        int i = Integer.MIN_VALUE & iFloatToIntBits;
        int i2 = iFloatToIntBits ^ i;
        if (i2 >= 1199570944) {
            sFloatToIntBits = i2 > iFloatToIntBits2 ? (short) 32256 : (short) 31744;
        } else {
            sFloatToIntBits = (short) (i2 < 947912704 ? Float.floatToIntBits(Float.intBitsToFloat(i2) + Float.intBitsToFloat(1056964608)) - 1056964608 : ((i2 - 939520001) + ((i2 >> 13) & 1)) >> 13);
        }
        return (short) (sFloatToIntBits | ((short) (i >> 16)));
    }

    public static float bf16ToFloat(short s) {
        return Float.intBitsToFloat(s << 16);
    }

    public static short floatToBf16(float f) {
        int iFloatToIntBits = Float.floatToIntBits(f);
        return (short) ((iFloatToIntBits + (((iFloatToIntBits >> 16) & 1) + LayoutKt.LargeDimension)) >> 16);
    }
}
