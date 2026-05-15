package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.extensions.impl.PreviewImageProcessorImpl;
import androidx.camera.extensions.impl.ProcessResultImpl;
import androidx.camera.extensions.internal.ClientVersion;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.Version;
import androidx.camera.extensions.internal.sessionprocessor.CaptureResultImageMatcher;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class PreviewProcessor {
    private static final String TAG = "PreviewProcessor";
    final PreviewImageProcessorImpl mPreviewImageProcessor;
    final CaptureResultImageMatcher mCaptureResultImageMatcher = new CaptureResultImageMatcher();
    final Object mLock = new Object();
    boolean mIsClosed = false;

    interface OnCaptureResultCallback {
        void onCaptureResult(long j, List<Pair<CaptureResult.Key, Object>> list);
    }

    PreviewProcessor(PreviewImageProcessorImpl previewImageProcessorImpl, Surface surface, Size size) {
        this.mPreviewImageProcessor = previewImageProcessorImpl;
        previewImageProcessorImpl.onResolutionUpdate(size);
        previewImageProcessorImpl.onOutputSurface(surface, 1);
        previewImageProcessorImpl.onImageFormatUpdate(35);
    }

    void start(final OnCaptureResultCallback onCaptureResultCallback) {
        this.mCaptureResultImageMatcher.setImageReferenceListener(new CaptureResultImageMatcher.ImageReferenceListener() { // from class: androidx.camera.extensions.internal.sessionprocessor.PreviewProcessor$$ExternalSyntheticLambda0
            @Override // androidx.camera.extensions.internal.sessionprocessor.CaptureResultImageMatcher.ImageReferenceListener
            public final void onImageReferenceIncoming(ImageReference imageReference, TotalCaptureResult totalCaptureResult, int i) {
                this.f$0.m203xdcf599ed(onCaptureResultCallback, imageReference, totalCaptureResult, i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$start$0$androidx-camera-extensions-internal-sessionprocessor-PreviewProcessor, reason: not valid java name */
    /* synthetic */ void m203xdcf599ed(final OnCaptureResultCallback onCaptureResultCallback, ImageReference imageReference, TotalCaptureResult totalCaptureResult, int i) {
        synchronized (this.mLock) {
            if (this.mIsClosed) {
                imageReference.decrement();
                Logger.d(TAG, "Ignore image in closed state");
                return;
            }
            if (ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_3) && ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
                this.mPreviewImageProcessor.process(imageReference.get(), totalCaptureResult, new ProcessResultImpl() { // from class: androidx.camera.extensions.internal.sessionprocessor.PreviewProcessor.1
                    public void onCaptureProcessProgressed(int i2) {
                    }

                    public void onCaptureCompleted(long j, List<Pair<CaptureResult.Key, Object>> list) {
                        onCaptureResultCallback.onCaptureResult(j, list);
                    }
                }, CameraXExecutors.ioExecutor());
            } else {
                this.mPreviewImageProcessor.process(imageReference.get(), totalCaptureResult);
            }
            imageReference.decrement();
        }
    }

    void notifyCaptureResult(TotalCaptureResult totalCaptureResult) {
        this.mCaptureResultImageMatcher.captureResultIncoming(totalCaptureResult);
    }

    void notifyImage(ImageReference imageReference) {
        this.mCaptureResultImageMatcher.imageIncoming(imageReference);
    }

    void close() {
        synchronized (this.mLock) {
            this.mIsClosed = true;
            this.mCaptureResultImageMatcher.clear();
            this.mCaptureResultImageMatcher.clearImageReferenceListener();
        }
    }
}
