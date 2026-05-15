package com.kakao.vectormap.label;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ILabel;
import com.kakao.vectormap.internal.ILabelDelegate;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolylineLabel extends ILabel {
    private String layerId;
    private LatLng[] points;
    private float rank;
    private PolylineLabelStyles styles;
    private Object tag;
    private String textLine;

    PolylineLabel(ILabelDelegate iLabelDelegate, String str, String str2, float f, Object obj, String str3, LatLng[] latLngArr, PolylineLabelStyles polylineLabelStyles, boolean z) {
        super(iLabelDelegate, str2, z);
        this.layerId = str;
        this.rank = f;
        this.tag = obj;
        this.textLine = str3;
        this.points = latLngArr;
        this.styles = polylineLabelStyles;
    }

    public String getLayerId() {
        return this.layerId;
    }

    @Override // com.kakao.vectormap.internal.ILabel
    public String getLabelId() {
        return this.labelId;
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }

    public synchronized float getRank() {
        return this.rank;
    }

    public synchronized LatLng[] getPoints() {
        return this.points;
    }

    public synchronized PolylineLabelStyles getStyles() {
        return this.styles;
    }

    public synchronized LabelLayer getLayer() {
        return this.delegate.getLabelLayer(this.layerId);
    }

    public synchronized String getText() {
        return this.textLine;
    }

    public synchronized void show() {
        try {
            checkValidate();
            this.delegate.setVisible(false, this.layerId, this.labelId, true, false, 0);
            this.visible = true;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hide() {
        try {
            checkValidate();
            this.delegate.setVisible(false, this.layerId, this.labelId, false, false, 0);
            this.visible = false;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isShow() {
        return this.visible;
    }

    public synchronized void changeStyles(PolylineLabelStyles polylineLabelStyles) {
        changeTextAndStyles(this.textLine, polylineLabelStyles);
    }

    public synchronized void changeTextAndStyles(String str, PolylineLabelStyles polylineLabelStyles) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (polylineLabelStyles == null) {
            MapLogger.e("PolylineLabel changeTextAndStyles failure. PolylineLabelStyles is null.");
            return;
        }
        if (polylineLabelStyles.getStyles() != null && polylineLabelStyles.getStyles().length != 0) {
            if (str == null) {
                str = "";
            }
            polylineLabelStyles.checkStyles(this.delegate.getResourceManager());
            this.delegate.changeTextAndStyles(this, str, polylineLabelStyles);
            this.styles = polylineLabelStyles;
            this.textLine = str;
            return;
        }
        MapLogger.e("PolylineLabel changeTextAndStyles failure. PolylineLabelStyles is empty.");
    }

    public synchronized void remove() {
        try {
            checkValidate();
            getLayer().remove(this);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    private void checkValidate() throws RuntimeException {
        LabelLayer labelLayer = this.delegate.getLabelLayer(this.layerId);
        if (labelLayer == null) {
            throw new RuntimeException("LabelLayer(id=" + this.layerId + ") is removed. LabelLayer must be added first.");
        }
        if (!labelLayer.hasLabel(this.labelId)) {
            throw new RuntimeException("Label(id=" + this.labelId + ") is removed. Label must be added first.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Label)) {
            return false;
        }
        Label label = (Label) obj;
        return Objects.equals(this.labelId, label.getLabelId()) && Objects.equals(this.layerId, label.layerId);
    }

    public int hashCode() {
        return Objects.hash(this.labelId, this.layerId);
    }
}
