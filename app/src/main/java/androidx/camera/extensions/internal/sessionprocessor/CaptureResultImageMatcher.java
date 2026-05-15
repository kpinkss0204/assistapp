package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.LongSparseArray;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class CaptureResultImageMatcher {
    private static final int INVALID_TIMESTAMP = -1;
    ImageReferenceListener mImageReferenceListener;
    private final Object mLock = new Object();
    private final LongSparseArray<TotalCaptureResult> mPendingCaptureResults = new LongSparseArray<>();
    Map<TotalCaptureResult, Integer> mCaptureStageIdMap = new HashMap();
    private final LongSparseArray<ImageReference> mPendingImages = new LongSparseArray<>();

    interface ImageReferenceListener {
        void onImageReferenceIncoming(ImageReference imageReference, TotalCaptureResult totalCaptureResult, int i);
    }

    CaptureResultImageMatcher() {
    }

    void clear() {
        synchronized (this.mLock) {
            this.mPendingCaptureResults.clear();
            for (int i = 0; i < this.mPendingImages.size(); i++) {
                this.mPendingImages.get(this.mPendingImages.keyAt(i)).decrement();
            }
            this.mPendingImages.clear();
            this.mCaptureStageIdMap.clear();
        }
    }

    void setImageReferenceListener(ImageReferenceListener imageReferenceListener) {
        synchronized (this.mLock) {
            this.mImageReferenceListener = imageReferenceListener;
        }
    }

    void clearImageReferenceListener() {
        synchronized (this.mLock) {
            this.mImageReferenceListener = null;
        }
    }

    void imageIncoming(ImageReference imageReference) {
        synchronized (this.mLock) {
            this.mPendingImages.put(imageReference.get().getTimestamp(), imageReference);
        }
        matchImages();
    }

    void captureResultIncoming(TotalCaptureResult totalCaptureResult) {
        captureResultIncoming(totalCaptureResult, 0);
    }

    void captureResultIncoming(TotalCaptureResult totalCaptureResult, int i) {
        synchronized (this.mLock) {
            long timeStampFromCaptureResult = getTimeStampFromCaptureResult(totalCaptureResult);
            if (timeStampFromCaptureResult == -1) {
                return;
            }
            this.mPendingCaptureResults.put(timeStampFromCaptureResult, totalCaptureResult);
            this.mCaptureStageIdMap.put(totalCaptureResult, Integer.valueOf(i));
            matchImages();
        }
    }

    private long getTimeStampFromCaptureResult(TotalCaptureResult totalCaptureResult) {
        Long l = (Long) totalCaptureResult.get(CaptureResult.SENSOR_TIMESTAMP);
        if (l != null) {
            return l.longValue();
        }
        return -1L;
    }

    private void notifyImage(ImageReference imageReference, TotalCaptureResult totalCaptureResult) {
        ImageReferenceListener imageReferenceListener;
        Integer num;
        synchronized (this.mLock) {
            imageReferenceListener = this.mImageReferenceListener;
            if (imageReferenceListener != null) {
                num = this.mCaptureStageIdMap.get(totalCaptureResult);
            } else {
                imageReference.decrement();
                imageReferenceListener = null;
                num = null;
            }
        }
        if (imageReferenceListener != null) {
            imageReferenceListener.onImageReferenceIncoming(imageReference, totalCaptureResult, num.intValue());
        }
    }

    private void removeStaleData() {
        synchronized (this.mLock) {
            if (this.mPendingImages.size() != 0 && this.mPendingCaptureResults.size() != 0) {
                Long lValueOf = Long.valueOf(this.mPendingImages.keyAt(0));
                Long lValueOf2 = Long.valueOf(this.mPendingCaptureResults.keyAt(0));
                Preconditions.checkArgument(!lValueOf2.equals(lValueOf));
                if (lValueOf2.longValue() > lValueOf.longValue()) {
                    for (int size = this.mPendingImages.size() - 1; size >= 0; size--) {
                        if (this.mPendingImages.keyAt(size) < lValueOf2.longValue()) {
                            this.mPendingImages.valueAt(size).decrement();
                            this.mPendingImages.removeAt(size);
                        }
                    }
                } else {
                    for (int size2 = this.mPendingCaptureResults.size() - 1; size2 >= 0; size2--) {
                        if (this.mPendingCaptureResults.keyAt(size2) < lValueOf.longValue()) {
                            this.mPendingCaptureResults.removeAt(size2);
                        }
                    }
                }
            }
        }
    }

    private void matchImages() {
        ImageReference imageReference;
        TotalCaptureResult totalCaptureResult;
        synchronized (this.mLock) {
            imageReference = null;
            totalCaptureResult = null;
            for (int size = this.mPendingCaptureResults.size() - 1; size >= 0; size--) {
                TotalCaptureResult totalCaptureResultValueAt = this.mPendingCaptureResults.valueAt(size);
                long timeStampFromCaptureResult = getTimeStampFromCaptureResult(totalCaptureResultValueAt);
                ImageReference imageReference2 = this.mPendingImages.get(timeStampFromCaptureResult);
                if (imageReference2 != null) {
                    this.mPendingImages.remove(timeStampFromCaptureResult);
                    this.mPendingCaptureResults.removeAt(size);
                    totalCaptureResult = totalCaptureResultValueAt;
                    imageReference = imageReference2;
                }
            }
            removeStaleData();
        }
        if (imageReference == null || totalCaptureResult == null) {
            return;
        }
        notifyImage(imageReference, totalCaptureResult);
    }
}
