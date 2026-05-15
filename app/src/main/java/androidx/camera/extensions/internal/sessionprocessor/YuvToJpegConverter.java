package androidx.camera.extensions.internal.sessionprocessor;

import android.view.Surface;
import androidx.camera.core.ImageProcessingUtil;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;

/* JADX INFO: loaded from: classes.dex */
class YuvToJpegConverter {
    private static final String TAG = "YuvToJpegConverter";
    private volatile int mJpegQuality;
    private final Surface mOutputJpegSurface;
    private volatile int mRotationDegrees = 0;

    YuvToJpegConverter(int i, Surface surface) {
        this.mJpegQuality = i;
        this.mOutputJpegSurface = surface;
    }

    public void setRotationDegrees(int i) {
        this.mRotationDegrees = i;
    }

    void setJpegQuality(int i) {
        this.mJpegQuality = i;
    }

    static class ConversionFailedException extends Exception {
        ConversionFailedException(String str) {
            super(str);
        }

        ConversionFailedException(String str, Throwable th) {
            super(str, th);
        }
    }

    void writeYuvImage(ImageProxy imageProxy) throws ConversionFailedException {
        Preconditions.checkState(imageProxy.getFormat() == 35, "Input image is not expected YUV_420_888 image format");
        try {
            try {
                if (ImageProcessingUtil.convertYuvToJpegBytesIntoSurface(imageProxy, this.mJpegQuality, this.mRotationDegrees, this.mOutputJpegSurface)) {
                    return;
                } else {
                    throw new ConversionFailedException("Failed to process YUV -> JPEG");
                }
            } catch (Exception e) {
                Logger.e(TAG, "Failed to process YUV -> JPEG", e);
                throw new ConversionFailedException("Failed to process YUV -> JPEG", e);
            }
        } finally {
            imageProxy.close();
        }
        imageProxy.close();
    }
}
