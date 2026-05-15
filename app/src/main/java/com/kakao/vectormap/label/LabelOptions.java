package com.kakao.vectormap.label;

import android.graphics.Bitmap;
import androidx.camera.video.AudioStats;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.internal.ILabelOptions;
import com.kakao.vectormap.utils.MapUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class LabelOptions extends ILabelOptions {
    public double lat;
    public double lng;
    public LabelStyles styles;
    public Object tag;
    public boolean clickable = true;
    public int transform = -1;

    LabelOptions(String str, LatLng latLng) {
        this.lat = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.lng = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.labelId = str;
        this.lat = latLng.latitude;
        this.lng = latLng.longitude;
        this.labelTextBuilder = new LabelTextBuilder();
    }

    public static LabelOptions from(LatLng latLng) {
        return new LabelOptions(MapUtils.getUniqueId(), latLng);
    }

    public static LabelOptions from(String str, LatLng latLng) {
        return new LabelOptions(MapUtils.getUniqueId(str), latLng);
    }

    public LabelOptions setStyles(LabelStyles labelStyles) {
        this.styles = labelStyles;
        return this;
    }

    public LabelOptions setStyles(LabelStyle... labelStyleArr) {
        this.styles = LabelStyles.from(labelStyleArr);
        return this;
    }

    public LabelOptions setStyles(int i) {
        this.styles = LabelStyles.from(LabelStyle.from(i));
        return this;
    }

    public LabelOptions setStyles(Bitmap bitmap) {
        this.styles = LabelStyles.from(LabelStyle.from(bitmap));
        return this;
    }

    public LabelOptions setRank(long j) {
        this.rank = j;
        return this;
    }

    public LabelOptions setClickable(boolean z) {
        this.clickable = z;
        return this;
    }

    public LabelOptions setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    public LabelOptions setTransform(TransformMethod transformMethod) {
        this.transform = transformMethod.getValue();
        return this;
    }

    public String getLabelId() {
        return this.labelId;
    }

    public LabelOptions setTexts(LabelTextBuilder labelTextBuilder) {
        this.labelTextBuilder = labelTextBuilder;
        this.texts = labelTextBuilder.getTexts();
        this.textStyleIndexes = labelTextBuilder.getTextIndexes();
        return this;
    }

    public LabelOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public LabelTextBuilder getLabelTextBuilder() {
        return this.labelTextBuilder;
    }

    public String[] getTexts() {
        return this.texts;
    }

    public LabelStyles getStyles() {
        return this.styles;
    }

    public LatLng getPosition() {
        return LatLng.from(this.lat, this.lng);
    }

    public long getRank() {
        return this.rank;
    }

    public TransformMethod getTransform() {
        return TransformMethod.getEnum(this.transform);
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public Object getTag() {
        return this.tag;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LabelOptions) {
            return Objects.equals(this.labelId, ((LabelOptions) obj).labelId);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.labelId);
    }
}
