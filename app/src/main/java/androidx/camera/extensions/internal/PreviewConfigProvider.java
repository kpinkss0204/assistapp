package androidx.camera.extensions.internal;

import androidx.camera.core.Preview;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.PreviewConfig;

/* JADX INFO: loaded from: classes.dex */
public class PreviewConfigProvider implements ConfigProvider<PreviewConfig> {
    static final Config.Option<Integer> OPTION_PREVIEW_CONFIG_PROVIDER_MODE = Config.Option.create("camerax.extensions.previewConfigProvider.mode", Integer.class);
    private static final String TAG = "PreviewConfigProvider";
    private final int mEffectMode;
    private final VendorExtender mVendorExtender;

    public PreviewConfigProvider(int i, VendorExtender vendorExtender) {
        this.mEffectMode = i;
        this.mVendorExtender = vendorExtender;
    }

    @Override // androidx.camera.core.impl.ConfigProvider
    public PreviewConfig getConfig() {
        Preview.Builder builder = new Preview.Builder();
        updateBuilderConfig(builder, this.mEffectMode, this.mVendorExtender);
        return builder.getUseCaseConfig();
    }

    void updateBuilderConfig(Preview.Builder builder, int i, VendorExtender vendorExtender) {
        builder.getMutableConfig().insertOption(OPTION_PREVIEW_CONFIG_PROVIDER_MODE, Integer.valueOf(i));
        builder.setSupportedResolutions(vendorExtender.getSupportedPreviewOutputResolutions());
        builder.setHighResolutionDisabled(true);
    }
}
