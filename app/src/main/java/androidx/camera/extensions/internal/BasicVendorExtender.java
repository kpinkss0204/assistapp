package androidx.camera.extensions.internal;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.extensions.impl.AutoImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.AutoPreviewExtenderImpl;
import androidx.camera.extensions.impl.BeautyImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.BeautyPreviewExtenderImpl;
import androidx.camera.extensions.impl.BokehImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.BokehPreviewExtenderImpl;
import androidx.camera.extensions.impl.HdrImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.HdrPreviewExtenderImpl;
import androidx.camera.extensions.impl.ImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.NightImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.NightPreviewExtenderImpl;
import androidx.camera.extensions.impl.PreviewExtenderImpl;
import androidx.camera.extensions.internal.compat.workaround.AvailableKeysRetriever;
import androidx.camera.extensions.internal.compat.workaround.ExtensionDisabledValidator;
import androidx.camera.extensions.internal.compat.workaround.ImageAnalysisAvailability;
import androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BasicVendorExtender implements VendorExtender {
    private static final String TAG = "BasicVendorExtender";
    static final List<CaptureRequest.Key> sBaseSupportedKeys;
    private AvailableKeysRetriever mAvailableKeysRetriever;
    private CameraCharacteristics mCameraCharacteristics;
    private String mCameraId;
    private CameraInfo mCameraInfo;
    private final ExtensionDisabledValidator mExtensionDisabledValidator;
    private ImageCaptureExtenderImpl mImageCaptureExtenderImpl;
    private int mMode;
    private PreviewExtenderImpl mPreviewExtenderImpl;

    static {
        ArrayList arrayList = new ArrayList(Arrays.asList(CaptureRequest.SCALER_CROP_REGION, CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AF_REGIONS, CaptureRequest.CONTROL_AE_REGIONS, CaptureRequest.CONTROL_AWB_REGIONS, CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, CaptureRequest.FLASH_MODE, CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION));
        sBaseSupportedKeys = arrayList;
        if (Build.VERSION.SDK_INT >= 30) {
            arrayList.add(CaptureRequest.CONTROL_ZOOM_RATIO);
        }
    }

    public BasicVendorExtender(int i) {
        this.mExtensionDisabledValidator = new ExtensionDisabledValidator();
        this.mPreviewExtenderImpl = null;
        this.mImageCaptureExtenderImpl = null;
        this.mAvailableKeysRetriever = new AvailableKeysRetriever();
        try {
            this.mMode = i;
            if (i == 1) {
                this.mPreviewExtenderImpl = new BokehPreviewExtenderImpl();
                this.mImageCaptureExtenderImpl = new BokehImageCaptureExtenderImpl();
                return;
            }
            if (i == 2) {
                this.mPreviewExtenderImpl = new HdrPreviewExtenderImpl();
                this.mImageCaptureExtenderImpl = new HdrImageCaptureExtenderImpl();
                return;
            }
            if (i == 3) {
                this.mPreviewExtenderImpl = new NightPreviewExtenderImpl();
                this.mImageCaptureExtenderImpl = new NightImageCaptureExtenderImpl();
            } else if (i == 4) {
                this.mPreviewExtenderImpl = new BeautyPreviewExtenderImpl();
                this.mImageCaptureExtenderImpl = new BeautyImageCaptureExtenderImpl();
            } else {
                if (i == 5) {
                    this.mPreviewExtenderImpl = new AutoPreviewExtenderImpl();
                    this.mImageCaptureExtenderImpl = new AutoImageCaptureExtenderImpl();
                    return;
                }
                throw new IllegalArgumentException("Should not activate ExtensionMode.NONE");
            }
        } catch (NoClassDefFoundError unused) {
            Logger.e(TAG, "OEM implementation for extension mode " + i + "does not exist!");
        }
    }

    BasicVendorExtender(ImageCaptureExtenderImpl imageCaptureExtenderImpl, PreviewExtenderImpl previewExtenderImpl) {
        this.mExtensionDisabledValidator = new ExtensionDisabledValidator();
        this.mPreviewExtenderImpl = null;
        this.mImageCaptureExtenderImpl = null;
        this.mAvailableKeysRetriever = new AvailableKeysRetriever();
        this.mMode = 0;
        this.mPreviewExtenderImpl = previewExtenderImpl;
        this.mImageCaptureExtenderImpl = imageCaptureExtenderImpl;
    }

    @Override // androidx.camera.extensions.internal.VendorExtender
    public boolean isExtensionAvailable(String str, Map<String, CameraCharacteristics> map) {
        if (!this.mExtensionDisabledValidator.shouldDisableExtension() && this.mPreviewExtenderImpl != null && this.mImageCaptureExtenderImpl != null) {
            CameraCharacteristics cameraCharacteristics = map.get(str);
            if (this.mPreviewExtenderImpl.isExtensionAvailable(str, cameraCharacteristics) && this.mImageCaptureExtenderImpl.isExtensionAvailable(str, cameraCharacteristics)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.camera.extensions.internal.VendorExtender
    public void init(CameraInfo cameraInfo) {
        this.mCameraInfo = cameraInfo;
        if (this.mPreviewExtenderImpl == null || this.mImageCaptureExtenderImpl == null) {
            return;
        }
        this.mCameraId = Camera2CameraInfo.from(cameraInfo).getCameraId();
        CameraCharacteristics cameraCharacteristicsExtractCameraCharacteristics = Camera2CameraInfo.extractCameraCharacteristics(cameraInfo);
        this.mCameraCharacteristics = cameraCharacteristicsExtractCameraCharacteristics;
        this.mPreviewExtenderImpl.init(this.mCameraId, cameraCharacteristicsExtractCameraCharacteristics);
        this.mImageCaptureExtenderImpl.init(this.mCameraId, this.mCameraCharacteristics);
        Logger.d(TAG, "PreviewExtender processorType= " + this.mPreviewExtenderImpl.getProcessorType());
        Logger.d(TAG, "ImageCaptureExtender processor= " + this.mImageCaptureExtenderImpl.getCaptureProcessor());
    }

    @Override // androidx.camera.extensions.internal.VendorExtender
    public Range<Long> getEstimatedCaptureLatencyRange(Size size) {
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        if (this.mImageCaptureExtenderImpl == null || ExtensionVersion.getRuntimeVersion().compareTo(Version.VERSION_1_2) < 0) {
            return null;
        }
        try {
            return this.mImageCaptureExtenderImpl.getEstimatedCaptureLatencyRange(size);
        } catch (NoSuchMethodError unused) {
            return null;
        }
    }

    private Size[] getOutputSizes(int i) {
        return ((StreamConfigurationMap) Camera2CameraInfo.from(this.mCameraInfo).getCameraCharacteristic(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(i);
    }

    private int getCaptureInputImageFormat() {
        ImageCaptureExtenderImpl imageCaptureExtenderImpl = this.mImageCaptureExtenderImpl;
        return (imageCaptureExtenderImpl == null || imageCaptureExtenderImpl.getCaptureProcessor() == null) ? 256 : 35;
    }

    private int getPreviewInputImageFormat() {
        PreviewExtenderImpl previewExtenderImpl = this.mPreviewExtenderImpl;
        return (previewExtenderImpl == null || previewExtenderImpl.getProcessorType() != PreviewExtenderImpl.ProcessorType.PROCESSOR_TYPE_IMAGE_PROCESSOR) ? 34 : 35;
    }

    @Override // androidx.camera.extensions.internal.VendorExtender
    public List<Pair<Integer, Size[]>> getSupportedPreviewOutputResolutions() {
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        if (this.mPreviewExtenderImpl != null && ExtensionVersion.getRuntimeVersion().compareTo(Version.VERSION_1_1) >= 0) {
            try {
                List<Pair<Integer, Size[]>> supportedResolutions = this.mPreviewExtenderImpl.getSupportedResolutions();
                if (supportedResolutions != null) {
                    return replaceImageFormatIfMissing(supportedResolutions, 35, 34);
                }
            } catch (NoSuchMethodError unused) {
            }
        }
        return Arrays.asList(new Pair(34, getOutputSizes(getPreviewInputImageFormat())));
    }

    @Override // androidx.camera.extensions.internal.VendorExtender
    public List<Pair<Integer, Size[]>> getSupportedCaptureOutputResolutions() {
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        if (this.mImageCaptureExtenderImpl != null && ExtensionVersion.getRuntimeVersion().compareTo(Version.VERSION_1_1) >= 0) {
            try {
                List<Pair<Integer, Size[]>> supportedResolutions = this.mImageCaptureExtenderImpl.getSupportedResolutions();
                if (supportedResolutions != null) {
                    return replaceImageFormatIfMissing(supportedResolutions, 35, 256);
                }
            } catch (NoSuchMethodError unused) {
            }
        }
        return Arrays.asList(new Pair(256, getOutputSizes(getCaptureInputImageFormat())));
    }

    private List<Pair<Integer, Size[]>> replaceImageFormatIfMissing(List<Pair<Integer, Size[]>> list, int i, int i2) {
        Iterator<Pair<Integer, Size[]>> it = list.iterator();
        while (it.hasNext()) {
            if (((Integer) it.next().first).intValue() == i2) {
                return list;
            }
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (Pair<Integer, Size[]> pair : list) {
            if (((Integer) pair.first).intValue() == i) {
                arrayList.add(new Pair(Integer.valueOf(i2), (Size[]) pair.second));
                z = true;
            } else {
                arrayList.add(pair);
            }
        }
        if (z) {
            return arrayList;
        }
        throw new IllegalArgumentException("Supported resolution should contain " + i2 + " format.");
    }

    @Override // androidx.camera.extensions.internal.VendorExtender
    public Size[] getSupportedYuvAnalysisResolutions() {
        if (!new ImageAnalysisAvailability().isAvailable(this.mCameraId, getHardwareLevel(), this.mMode, this.mPreviewExtenderImpl.getProcessorType() == PreviewExtenderImpl.ProcessorType.PROCESSOR_TYPE_IMAGE_PROCESSOR, this.mImageCaptureExtenderImpl.getCaptureProcessor() != null)) {
            return new Size[0];
        }
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        return getOutputSizes(35);
    }

    private int getHardwareLevel() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        if (num != null) {
            return num.intValue();
        }
        return 2;
    }

    private List<CaptureRequest.Key> getSupportedParameterKeys(Context context) {
        if (ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
            try {
                List<CaptureRequest.Key> availableCaptureRequestKeys = this.mAvailableKeysRetriever.getAvailableCaptureRequestKeys(this.mImageCaptureExtenderImpl, this.mCameraId, this.mCameraCharacteristics, context);
                if (availableCaptureRequestKeys != null) {
                    return Collections.unmodifiableList(availableCaptureRequestKeys);
                }
            } catch (Exception e) {
                Logger.e(TAG, "ImageCaptureExtenderImpl.getAvailableCaptureRequestKeys throws exceptions", e);
            }
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(sBaseSupportedKeys);
    }

    private List<CaptureResult.Key> getSupportedCaptureResultKeys() {
        if (ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
            try {
                List availableCaptureResultKeys = this.mImageCaptureExtenderImpl.getAvailableCaptureResultKeys();
                if (availableCaptureResultKeys != null) {
                    return Collections.unmodifiableList(availableCaptureResultKeys);
                }
            } catch (Exception e) {
                Logger.e(TAG, "ImageCaptureExtenderImpl.getAvailableCaptureResultKeys throws exceptions", e);
            }
        }
        return Collections.emptyList();
    }

    @Override // androidx.camera.extensions.internal.VendorExtender
    public SessionProcessor createSessionProcessor(Context context) {
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        return new BasicExtenderSessionProcessor(this.mPreviewExtenderImpl, this.mImageCaptureExtenderImpl, getSupportedParameterKeys(context), getSupportedCaptureResultKeys(), context);
    }
}
