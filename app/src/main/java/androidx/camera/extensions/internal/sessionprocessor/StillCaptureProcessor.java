package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.internal.Camera2CameraCaptureResult;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.ImageReaderProxys;
import androidx.camera.core.Logger;
import androidx.camera.core.SettableImageProxy;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;
import androidx.camera.extensions.impl.CaptureProcessorImpl;
import androidx.camera.extensions.impl.ProcessResultImpl;
import androidx.camera.extensions.internal.ClientVersion;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.Version;
import androidx.camera.extensions.internal.sessionprocessor.CaptureResultImageMatcher;
import androidx.camera.extensions.internal.sessionprocessor.YuvToJpegConverter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class StillCaptureProcessor {
    private static final int MAX_IMAGES = 2;
    private static final String TAG = "StillCaptureProcessor";
    final CaptureProcessorImpl mCaptureProcessorImpl;
    final CaptureResultImageMatcher mCaptureResultImageMatcher;
    HashMap<Integer, Pair<ImageReference, TotalCaptureResult>> mCaptureResults;
    boolean mIsClosed;
    final Object mLock;
    OnCaptureResultCallback mOnCaptureResultCallback;
    final ImageReaderProxy mProcessedYuvImageReader;
    TotalCaptureResult mSourceCaptureResult;
    YuvToJpegConverter mYuvToJpegConverter;

    interface OnCaptureResultCallback {
        void onCaptureResult(long j, List<Pair<CaptureResult.Key, Object>> list);

        void onCompleted();

        void onError(Exception exc);
    }

    StillCaptureProcessor(CaptureProcessorImpl captureProcessorImpl, Surface surface, Size size) {
        this.mCaptureResultImageMatcher = new CaptureResultImageMatcher();
        this.mLock = new Object();
        this.mCaptureResults = new HashMap<>();
        this.mOnCaptureResultCallback = null;
        this.mSourceCaptureResult = null;
        this.mIsClosed = false;
        this.mCaptureProcessorImpl = captureProcessorImpl;
        ImageReaderProxy imageReaderProxyCreateIsolatedReader = ImageReaderProxys.createIsolatedReader(size.getWidth(), size.getHeight(), 35, 2);
        this.mProcessedYuvImageReader = imageReaderProxyCreateIsolatedReader;
        this.mYuvToJpegConverter = new YuvToJpegConverter(100, surface);
        imageReaderProxyCreateIsolatedReader.setOnImageAvailableListener(new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor$$ExternalSyntheticLambda1
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                this.f$0.m204xba518613(imageReaderProxy);
            }
        }, CameraXExecutors.ioExecutor());
        captureProcessorImpl.onOutputSurface(imageReaderProxyCreateIsolatedReader.getSurface(), 35);
        captureProcessorImpl.onImageFormatUpdate(35);
        captureProcessorImpl.onResolutionUpdate(size);
    }

    /* JADX INFO: renamed from: lambda$new$0$androidx-camera-extensions-internal-sessionprocessor-StillCaptureProcessor, reason: not valid java name */
    /* synthetic */ void m204xba518613(ImageReaderProxy imageReaderProxy) {
        synchronized (this.mLock) {
            if (this.mIsClosed) {
                Logger.d(TAG, "Ignore JPEG processing in closed state");
                return;
            }
            ImageProxy imageProxyAcquireNextImage = imageReaderProxy.acquireNextImage();
            OnCaptureResultCallback onCaptureResultCallback = null;
            if (this.mSourceCaptureResult != null) {
                SettableImageProxy settableImageProxy = new SettableImageProxy(imageProxyAcquireNextImage, null, new CameraCaptureResultImageInfo(new Camera2CameraCaptureResult(this.mSourceCaptureResult)));
                this.mSourceCaptureResult = null;
                imageProxyAcquireNextImage = settableImageProxy;
            }
            if (imageProxyAcquireNextImage != null) {
                try {
                    this.mYuvToJpegConverter.writeYuvImage(imageProxyAcquireNextImage);
                    e = null;
                } catch (YuvToJpegConverter.ConversionFailedException e) {
                    e = e;
                }
                OnCaptureResultCallback onCaptureResultCallback2 = this.mOnCaptureResultCallback;
                if (onCaptureResultCallback2 != null) {
                    this.mOnCaptureResultCallback = null;
                    onCaptureResultCallback = onCaptureResultCallback2;
                }
            } else {
                e = null;
            }
            if (onCaptureResultCallback != null) {
                if (e != null) {
                    onCaptureResultCallback.onError(e);
                } else {
                    onCaptureResultCallback.onCompleted();
                }
            }
        }
    }

    StillCaptureProcessor(CaptureProcessorImpl captureProcessorImpl, Surface surface, Size size, YuvToJpegConverter yuvToJpegConverter) {
        this(captureProcessorImpl, surface, size);
        this.mYuvToJpegConverter = yuvToJpegConverter;
    }

    void clearCaptureResults() {
        synchronized (this.mLock) {
            Iterator<Pair<ImageReference, TotalCaptureResult>> it = this.mCaptureResults.values().iterator();
            while (it.hasNext()) {
                ((ImageReference) it.next().first).decrement();
            }
            this.mCaptureResults.clear();
        }
    }

    void startCapture(final List<Integer> list, final OnCaptureResultCallback onCaptureResultCallback) {
        Logger.d(TAG, "Start the processor");
        synchronized (this.mLock) {
            this.mOnCaptureResultCallback = onCaptureResultCallback;
            clearCaptureResults();
        }
        this.mCaptureResultImageMatcher.clear();
        this.mCaptureResultImageMatcher.setImageReferenceListener(new CaptureResultImageMatcher.ImageReferenceListener() { // from class: androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor$$ExternalSyntheticLambda0
            @Override // androidx.camera.extensions.internal.sessionprocessor.CaptureResultImageMatcher.ImageReferenceListener
            public final void onImageReferenceIncoming(ImageReference imageReference, TotalCaptureResult totalCaptureResult, int i) {
                this.f$0.m205xfcf99534(list, onCaptureResultCallback, imageReference, totalCaptureResult, i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$startCapture$1$androidx-camera-extensions-internal-sessionprocessor-StillCaptureProcessor, reason: not valid java name */
    /* synthetic */ void m205xfcf99534(List list, final OnCaptureResultCallback onCaptureResultCallback, ImageReference imageReference, TotalCaptureResult totalCaptureResult, int i) {
        synchronized (this.mLock) {
            if (this.mIsClosed) {
                imageReference.decrement();
                Logger.d(TAG, "Ignore image in closed state");
                return;
            }
            Logger.d(TAG, "onImageReferenceIncoming  captureStageId=" + i);
            this.mCaptureResults.put(Integer.valueOf(i), new Pair<>(imageReference, totalCaptureResult));
            Logger.d(TAG, "mCaptureResult has capture stage Id: " + this.mCaptureResults.keySet());
            Exception exc = null;
            if (this.mCaptureResults.keySet().containsAll(list)) {
                HashMap map = new HashMap();
                for (Integer num : this.mCaptureResults.keySet()) {
                    Pair<ImageReference, TotalCaptureResult> pair = this.mCaptureResults.get(num);
                    map.put(num, new Pair(((ImageReference) pair.first).get(), (TotalCaptureResult) pair.second));
                }
                Logger.d(TAG, "CaptureProcessorImpl.process()");
                try {
                    if (ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3) && ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
                        this.mCaptureProcessorImpl.process(map, new ProcessResultImpl() { // from class: androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor.1
                            public void onCaptureProcessProgressed(int i2) {
                            }

                            public void onCaptureCompleted(long j, List<Pair<CaptureResult.Key, Object>> list2) {
                                onCaptureResultCallback.onCaptureResult(j, list2);
                            }
                        }, CameraXExecutors.ioExecutor());
                    } else {
                        this.mCaptureProcessorImpl.process(map);
                    }
                } catch (Exception e) {
                    this.mOnCaptureResultCallback = null;
                    exc = e;
                }
                clearCaptureResults();
            }
            if (exc == null || onCaptureResultCallback == null) {
                return;
            }
            onCaptureResultCallback.onError(exc);
        }
    }

    void notifyCaptureResult(TotalCaptureResult totalCaptureResult, int i) {
        this.mCaptureResultImageMatcher.captureResultIncoming(totalCaptureResult, i);
        synchronized (this.mLock) {
            if (this.mSourceCaptureResult == null) {
                this.mSourceCaptureResult = totalCaptureResult;
            }
        }
    }

    void notifyImage(ImageReference imageReference) {
        this.mCaptureResultImageMatcher.imageIncoming(imageReference);
    }

    void setJpegQuality(int i) {
        this.mYuvToJpegConverter.setJpegQuality(i);
    }

    void setRotationDegrees(int i) {
        this.mYuvToJpegConverter.setRotationDegrees(i);
    }

    void close() {
        Logger.d(TAG, "Close the processor");
        synchronized (this.mLock) {
            this.mIsClosed = true;
            clearCaptureResults();
            this.mProcessedYuvImageReader.clearOnImageAvailableListener();
            this.mCaptureResultImageMatcher.clearImageReferenceListener();
            this.mCaptureResultImageMatcher.clear();
            this.mProcessedYuvImageReader.close();
        }
    }
}
