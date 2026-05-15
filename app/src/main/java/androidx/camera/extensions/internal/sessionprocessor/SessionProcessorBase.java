package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.OutputSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.SessionProcessorSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
abstract class SessionProcessorBase implements SessionProcessor {
    private static final String TAG = "SessionProcessorBase";
    private String mCameraId;
    private HandlerThread mImageReaderHandlerThread;
    private final Set<Integer> mSupportedCameraOperations;
    private Map<Integer, ImageReader> mImageReaderMap = new HashMap();
    private Map<Integer, Camera2OutputConfig> mOutputConfigMap = new HashMap();
    private List<DeferrableSurface> mSurfacesList = new ArrayList();
    private final Object mLock = new Object();

    protected abstract void deInitSessionInternal();

    protected abstract Camera2SessionConfig initSessionInternal(String str, Map<String, CameraCharacteristics> map, OutputSurface outputSurface, OutputSurface outputSurface2, OutputSurface outputSurface3);

    SessionProcessorBase(List<CaptureRequest.Key> list) {
        this.mSupportedCameraOperations = getSupportedCameraOperations(list);
    }

    private Set<Integer> getSupportedCameraOperations(List<CaptureRequest.Key> list) {
        HashSet hashSet = new HashSet();
        if (Build.VERSION.SDK_INT >= 30) {
            if (list.contains(CaptureRequest.CONTROL_ZOOM_RATIO) || list.contains(CaptureRequest.SCALER_CROP_REGION)) {
                hashSet.add(0);
            }
        } else if (list.contains(CaptureRequest.SCALER_CROP_REGION)) {
            hashSet.add(0);
        }
        if (list.containsAll(Arrays.asList(CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AF_MODE))) {
            hashSet.add(1);
        }
        if (list.contains(CaptureRequest.CONTROL_AF_REGIONS)) {
            hashSet.add(2);
        }
        if (list.contains(CaptureRequest.CONTROL_AE_REGIONS)) {
            hashSet.add(3);
        }
        if (list.contains(CaptureRequest.CONTROL_AWB_REGIONS)) {
            hashSet.add(4);
        }
        if (list.containsAll(Arrays.asList(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER))) {
            hashSet.add(5);
        }
        if (list.containsAll(Arrays.asList(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.FLASH_MODE))) {
            hashSet.add(6);
        }
        if (list.contains(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION)) {
            hashSet.add(7);
        }
        return hashSet;
    }

    private static SessionProcessorSurface createOutputConfigSurface(Camera2OutputConfig camera2OutputConfig, Map<Integer, ImageReader> map) {
        if (camera2OutputConfig instanceof SurfaceOutputConfig) {
            return new SessionProcessorSurface(((SurfaceOutputConfig) camera2OutputConfig).getSurface(), camera2OutputConfig.getId());
        }
        if (camera2OutputConfig instanceof ImageReaderOutputConfig) {
            ImageReaderOutputConfig imageReaderOutputConfig = (ImageReaderOutputConfig) camera2OutputConfig;
            final ImageReader imageReaderNewInstance = ImageReader.newInstance(imageReaderOutputConfig.getSize().getWidth(), imageReaderOutputConfig.getSize().getHeight(), imageReaderOutputConfig.getImageFormat(), imageReaderOutputConfig.getMaxImages());
            map.put(Integer.valueOf(camera2OutputConfig.getId()), imageReaderNewInstance);
            SessionProcessorSurface sessionProcessorSurface = new SessionProcessorSurface(imageReaderNewInstance.getSurface(), camera2OutputConfig.getId());
            sessionProcessorSurface.getTerminationFuture().addListener(new Runnable() { // from class: androidx.camera.extensions.internal.sessionprocessor.SessionProcessorBase$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    imageReaderNewInstance.close();
                }
            }, CameraXExecutors.directExecutor());
            return sessionProcessorSurface;
        }
        if (camera2OutputConfig instanceof MultiResolutionImageReaderOutputConfig) {
            throw new UnsupportedOperationException("MultiResolutionImageReader not supported yet");
        }
        throw new UnsupportedOperationException("Unsupported Camera2OutputConfig:" + camera2OutputConfig);
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public final SessionConfig initSession(CameraInfo cameraInfo, OutputSurface outputSurface, OutputSurface outputSurface2, OutputSurface outputSurface3) {
        Camera2CameraInfo camera2CameraInfoFrom = Camera2CameraInfo.from(cameraInfo);
        Camera2SessionConfig camera2SessionConfigInitSessionInternal = initSessionInternal(camera2CameraInfoFrom.getCameraId(), camera2CameraInfoFrom.getCameraCharacteristicsMap(), outputSurface, outputSurface2, outputSurface3);
        SessionConfig.Builder builder = new SessionConfig.Builder();
        synchronized (this.mLock) {
            for (Camera2OutputConfig camera2OutputConfig : camera2SessionConfigInitSessionInternal.getOutputConfigs()) {
                SessionProcessorSurface sessionProcessorSurfaceCreateOutputConfigSurface = createOutputConfigSurface(camera2OutputConfig, this.mImageReaderMap);
                this.mSurfacesList.add(sessionProcessorSurfaceCreateOutputConfigSurface);
                this.mOutputConfigMap.put(Integer.valueOf(camera2OutputConfig.getId()), camera2OutputConfig);
                SessionConfig.OutputConfig.Builder surfaceGroupId = SessionConfig.OutputConfig.builder(sessionProcessorSurfaceCreateOutputConfigSurface).setPhysicalCameraId(camera2OutputConfig.getPhysicalCameraId()).setSurfaceGroupId(camera2OutputConfig.getSurfaceGroupId());
                List<Camera2OutputConfig> surfaceSharingOutputConfigs = camera2OutputConfig.getSurfaceSharingOutputConfigs();
                if (surfaceSharingOutputConfigs != null && !surfaceSharingOutputConfigs.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    for (Camera2OutputConfig camera2OutputConfig2 : surfaceSharingOutputConfigs) {
                        this.mOutputConfigMap.put(Integer.valueOf(camera2OutputConfig2.getId()), camera2OutputConfig2);
                        arrayList.add(createOutputConfigSurface(camera2OutputConfig2, this.mImageReaderMap));
                    }
                    surfaceGroupId.setSharedSurfaces(arrayList);
                }
                builder.addOutputConfig(surfaceGroupId.build());
            }
        }
        Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
        for (CaptureRequest.Key<?> key : camera2SessionConfigInitSessionInternal.getSessionParameters().keySet()) {
            builder2.setCaptureRequestOption(key, camera2SessionConfigInitSessionInternal.getSessionParameters().get(key));
        }
        builder.setImplementationOptions(builder2.build());
        builder.setTemplateType(camera2SessionConfigInitSessionInternal.getSessionTemplateId());
        HandlerThread handlerThread = new HandlerThread("CameraX-extensions_image_reader");
        this.mImageReaderHandlerThread = handlerThread;
        handlerThread.start();
        this.mCameraId = camera2CameraInfoFrom.getCameraId();
        Logger.d(TAG, "initSession: cameraId=" + this.mCameraId);
        return builder.build();
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public Set<Integer> getSupportedCameraOperations() {
        return this.mSupportedCameraOperations;
    }

    protected void setImageProcessor(final int i, final ImageProcessor imageProcessor) {
        ImageReader imageReader;
        final String physicalCameraId;
        synchronized (this.mLock) {
            imageReader = this.mImageReaderMap.get(Integer.valueOf(i));
            Camera2OutputConfig camera2OutputConfig = this.mOutputConfigMap.get(Integer.valueOf(i));
            physicalCameraId = camera2OutputConfig == null ? null : camera2OutputConfig.getPhysicalCameraId();
        }
        if (imageReader != null) {
            imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: androidx.camera.extensions.internal.sessionprocessor.SessionProcessorBase$$ExternalSyntheticLambda0
                @Override // android.media.ImageReader.OnImageAvailableListener
                public final void onImageAvailable(ImageReader imageReader2) {
                    SessionProcessorBase.lambda$setImageProcessor$1(imageProcessor, i, physicalCameraId, imageReader2);
                }
            }, new Handler(this.mImageReaderHandlerThread.getLooper()));
        }
    }

    static /* synthetic */ void lambda$setImageProcessor$1(ImageProcessor imageProcessor, int i, String str, ImageReader imageReader) {
        try {
            Image imageAcquireNextImage = imageReader.acquireNextImage();
            imageProcessor.onNextImageAvailable(i, imageAcquireNextImage.getTimestamp(), new ImageRefHolder(imageAcquireNextImage), str);
        } catch (IllegalStateException e) {
            Logger.e(TAG, "Failed to acquire next image.", e);
        }
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public final void deInitSession() {
        Logger.e(TAG, "deInitSession: cameraId=" + this.mCameraId);
        deInitSessionInternal();
        synchronized (this.mLock) {
            Iterator<DeferrableSurface> it = this.mSurfacesList.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            this.mSurfacesList.clear();
            this.mImageReaderMap.clear();
            this.mOutputConfigMap.clear();
        }
        HandlerThread handlerThread = this.mImageReaderHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mImageReaderHandlerThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ImageRefHolder implements ImageReference {
        private Image mImage;
        private final Object mImageLock = new Object();
        private int mRefCount = 1;

        ImageRefHolder(Image image) {
            this.mImage = image;
        }

        @Override // androidx.camera.extensions.internal.sessionprocessor.ImageReference
        public boolean increment() {
            synchronized (this.mImageLock) {
                int i = this.mRefCount;
                if (i <= 0) {
                    return false;
                }
                this.mRefCount = i + 1;
                return true;
            }
        }

        @Override // androidx.camera.extensions.internal.sessionprocessor.ImageReference
        public boolean decrement() {
            synchronized (this.mImageLock) {
                int i = this.mRefCount;
                if (i <= 0) {
                    return false;
                }
                int i2 = i - 1;
                this.mRefCount = i2;
                if (i2 <= 0) {
                    this.mImage.close();
                }
                return true;
            }
        }

        @Override // androidx.camera.extensions.internal.sessionprocessor.ImageReference
        public Image get() {
            return this.mImage;
        }
    }
}
