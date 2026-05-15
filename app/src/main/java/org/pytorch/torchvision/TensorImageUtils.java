package org.pytorch.torchvision;

import android.graphics.Bitmap;
import android.media.Image;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.soloader.nativeloader.SystemDelegate;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Locale;
import org.pytorch.MemoryFormat;
import org.pytorch.Tensor;

/* JADX INFO: loaded from: classes4.dex */
public final class TensorImageUtils {
    public static float[] TORCHVISION_NORM_MEAN_RGB = {0.485f, 0.456f, 0.406f};
    public static float[] TORCHVISION_NORM_STD_RGB = {0.229f, 0.224f, 0.225f};

    public static Tensor bitmapToFloat32Tensor(Bitmap bitmap, float[] fArr, float[] fArr2, MemoryFormat memoryFormat) {
        checkNormMeanArg(fArr);
        checkNormStdArg(fArr2);
        return bitmapToFloat32Tensor(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), fArr, fArr2, memoryFormat);
    }

    public static Tensor bitmapToFloat32Tensor(Bitmap bitmap, float[] fArr, float[] fArr2) {
        return bitmapToFloat32Tensor(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), fArr, fArr2, MemoryFormat.CONTIGUOUS);
    }

    public static void bitmapToFloatBuffer(Bitmap bitmap, int i, int i2, int i3, int i4, float[] fArr, float[] fArr2, FloatBuffer floatBuffer, int i5, MemoryFormat memoryFormat) {
        checkOutBufferCapacity(floatBuffer, i5, i3, i4);
        checkNormMeanArg(fArr);
        checkNormStdArg(fArr2);
        if (memoryFormat != MemoryFormat.CONTIGUOUS && memoryFormat != MemoryFormat.CHANNELS_LAST) {
            throw new IllegalArgumentException("Unsupported memory format " + memoryFormat);
        }
        int i6 = i4 * i3;
        int[] iArr = new int[i6];
        bitmap.getPixels(iArr, 0, i3, i, i2, i3, i4);
        if (MemoryFormat.CONTIGUOUS == memoryFormat) {
            int i7 = i6 * 2;
            for (int i8 = 0; i8 < i6; i8++) {
                int i9 = iArr[i8];
                floatBuffer.put(i5 + i8, ((((i9 >> 16) & 255) / 255.0f) - fArr[0]) / fArr2[0]);
                floatBuffer.put(i5 + i6 + i8, ((((i9 >> 8) & 255) / 255.0f) - fArr[1]) / fArr2[1]);
                floatBuffer.put(i5 + i7 + i8, (((i9 & 255) / 255.0f) - fArr[2]) / fArr2[2]);
            }
            return;
        }
        for (int i10 = 0; i10 < i6; i10++) {
            int i11 = iArr[i10];
            int i12 = (i10 * 3) + i5;
            floatBuffer.put(i12, ((((i11 >> 16) & 255) / 255.0f) - fArr[0]) / fArr2[0]);
            floatBuffer.put(i12 + 1, ((((i11 >> 8) & 255) / 255.0f) - fArr[1]) / fArr2[1]);
            floatBuffer.put(i12 + 2, (((i11 & 255) / 255.0f) - fArr[2]) / fArr2[2]);
        }
    }

    public static void bitmapToFloatBuffer(Bitmap bitmap, int i, int i2, int i3, int i4, float[] fArr, float[] fArr2, FloatBuffer floatBuffer, int i5) {
        bitmapToFloatBuffer(bitmap, i, i2, i3, i4, fArr, fArr2, floatBuffer, i5, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor bitmapToFloat32Tensor(Bitmap bitmap, int i, int i2, int i3, int i4, float[] fArr, float[] fArr2, MemoryFormat memoryFormat) {
        checkNormMeanArg(fArr);
        checkNormStdArg(fArr2);
        FloatBuffer floatBufferAllocateFloatBuffer = Tensor.allocateFloatBuffer(i3 * 3 * i4);
        bitmapToFloatBuffer(bitmap, i, i2, i3, i4, fArr, fArr2, floatBufferAllocateFloatBuffer, 0, memoryFormat);
        return Tensor.fromBlob(floatBufferAllocateFloatBuffer, new long[]{1, 3, i4, i3}, memoryFormat);
    }

    public static Tensor bitmapToFloat32Tensor(Bitmap bitmap, int i, int i2, int i3, int i4, float[] fArr, float[] fArr2) {
        return bitmapToFloat32Tensor(bitmap, i, i2, i3, i4, fArr, fArr2, MemoryFormat.CONTIGUOUS);
    }

    public static Tensor imageYUV420CenterCropToFloat32Tensor(Image image, int i, int i2, int i3, float[] fArr, float[] fArr2, MemoryFormat memoryFormat) {
        if (image.getFormat() != 35) {
            throw new IllegalArgumentException(String.format(Locale.US, "Image format %d != ImageFormat.YUV_420_888", Integer.valueOf(image.getFormat())));
        }
        checkNormMeanArg(fArr);
        checkNormStdArg(fArr2);
        checkRotateCWDegrees(i);
        checkTensorSize(i2, i3);
        FloatBuffer floatBufferAllocateFloatBuffer = Tensor.allocateFloatBuffer(i2 * 3 * i3);
        imageYUV420CenterCropToFloatBuffer(image, i, i2, i3, fArr, fArr2, floatBufferAllocateFloatBuffer, 0, memoryFormat);
        return Tensor.fromBlob(floatBufferAllocateFloatBuffer, new long[]{1, 3, i3, i2}, memoryFormat);
    }

    public static Tensor imageYUV420CenterCropToFloat32Tensor(Image image, int i, int i2, int i3, float[] fArr, float[] fArr2) {
        return imageYUV420CenterCropToFloat32Tensor(image, i, i2, i3, fArr, fArr2, MemoryFormat.CONTIGUOUS);
    }

    public static void imageYUV420CenterCropToFloatBuffer(Image image, int i, int i2, int i3, float[] fArr, float[] fArr2, FloatBuffer floatBuffer, int i4, MemoryFormat memoryFormat) {
        int i5;
        checkOutBufferCapacity(floatBuffer, i4, i2, i3);
        if (image.getFormat() != 35) {
            throw new IllegalArgumentException(String.format(Locale.US, "Image format %d != ImageFormat.YUV_420_888", Integer.valueOf(image.getFormat())));
        }
        checkNormMeanArg(fArr);
        checkNormStdArg(fArr2);
        checkRotateCWDegrees(i);
        checkTensorSize(i2, i3);
        Image.Plane[] planes = image.getPlanes();
        Image.Plane plane = planes[0];
        Image.Plane plane2 = planes[1];
        Image.Plane plane3 = planes[2];
        if (MemoryFormat.CONTIGUOUS == memoryFormat) {
            i5 = 1;
        } else {
            i5 = MemoryFormat.CHANNELS_LAST == memoryFormat ? 2 : 0;
        }
        NativePeer.imageYUV420CenterCropToFloatBuffer(plane.getBuffer(), plane.getRowStride(), plane.getPixelStride(), plane2.getBuffer(), plane3.getBuffer(), plane2.getRowStride(), plane2.getPixelStride(), image.getWidth(), image.getHeight(), i, i2, i3, fArr, fArr2, floatBuffer, i4, i5);
    }

    public static void imageYUV420CenterCropToFloatBuffer(Image image, int i, int i2, int i3, float[] fArr, float[] fArr2, FloatBuffer floatBuffer, int i4) {
        imageYUV420CenterCropToFloatBuffer(image, i, i2, i3, fArr, fArr2, floatBuffer, i4, MemoryFormat.CONTIGUOUS);
    }

    private static class NativePeer {
        /* JADX INFO: Access modifiers changed from: private */
        public static native void imageYUV420CenterCropToFloatBuffer(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i3, int i4, int i5, int i6, int i7, int i8, int i9, float[] fArr, float[] fArr2, Buffer buffer, int i10, int i11);

        private NativePeer() {
        }

        static {
            if (!NativeLoader.isInitialized()) {
                NativeLoader.init(new SystemDelegate());
            }
            NativeLoader.loadLibrary("pytorch_vision_jni");
        }
    }

    private static void checkOutBufferCapacity(FloatBuffer floatBuffer, int i, int i2, int i3) {
        if (i + (i2 * 3 * i3) > floatBuffer.capacity()) {
            throw new IllegalStateException("Buffer underflow");
        }
    }

    private static void checkTensorSize(int i, int i2) {
        if (i2 <= 0 || i <= 0) {
            throw new IllegalArgumentException("tensorHeight and tensorWidth must be positive");
        }
    }

    private static void checkRotateCWDegrees(int i) {
        if (i != 0 && i != 90 && i != 180 && i != 270) {
            throw new IllegalArgumentException("rotateCWDegrees must be one of 0, 90, 180, 270");
        }
    }

    private static void checkNormStdArg(float[] fArr) {
        if (fArr.length != 3) {
            throw new IllegalArgumentException("normStdRGB length must be 3");
        }
    }

    private static void checkNormMeanArg(float[] fArr) {
        if (fArr.length != 3) {
            throw new IllegalArgumentException("normMeanRGB length must be 3");
        }
    }
}
