package androidx.camera.extensions.internal;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.ImageCaptureConfig;

/* JADX INFO: loaded from: classes.dex */
public class ImageCaptureConfigProvider implements ConfigProvider<ImageCaptureConfig> {
    static final Config.Option<Integer> OPTION_IMAGE_CAPTURE_CONFIG_PROVIDER_MODE = Config.Option.create("camerax.extensions.imageCaptureConfigProvider.mode", Integer.class);
    private final int mEffectMode;
    private final VendorExtender mVendorExtender;

    public ImageCaptureConfigProvider(int i, VendorExtender vendorExtender) {
        this.mEffectMode = i;
        this.mVendorExtender = vendorExtender;
    }

    @Override // androidx.camera.core.impl.ConfigProvider
    public ImageCaptureConfig getConfig() {
        ImageCapture.Builder builder = new ImageCapture.Builder();
        updateBuilderConfig(builder, this.mEffectMode, this.mVendorExtender);
        return builder.getUseCaseConfig();
    }

    void updateBuilderConfig(ImageCapture.Builder builder, int i, VendorExtender vendorExtender) {
        builder.getMutableConfig().insertOption(OPTION_IMAGE_CAPTURE_CONFIG_PROVIDER_MODE, Integer.valueOf(i));
        builder.setSupportedResolutions(vendorExtender.getSupportedCaptureOutputResolutions());
        builder.setHighResolutionDisabled(true);
    }
}
