package com.kakao.vectormap.label;

import android.graphics.PointF;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ILabel;
import com.kakao.vectormap.internal.ILabelDelegate;

/* JADX INFO: loaded from: classes4.dex */
abstract class PointLabel extends ILabel {
    public static final int NO_CHANGE = 1;
    public static final int PATH_OPTIONS_CHANGE = 2;
    public static final int STYLE_CHANGE = 1;
    public int changes;
    protected boolean clickable;
    protected final boolean isLod;
    protected LabelTextBuilder labelTextBuilder;
    protected final String layerId;
    protected LatLng position;
    protected long rank;
    protected LabelStyles styles;
    protected Object tag;

    public abstract LatLng getPosition();

    protected PointLabel(ILabelDelegate iLabelDelegate, String str, String str2, boolean z, long j, boolean z2, boolean z3, Object obj, LatLng latLng, LabelStyles labelStyles, LabelTextBuilder labelTextBuilder) {
        super(iLabelDelegate, str2, z2);
        this.changes = 0;
        this.layerId = str;
        this.isLod = z;
        this.rank = j;
        this.clickable = z3;
        this.tag = obj;
        this.styles = labelStyles;
        this.position = latLng;
        this.labelTextBuilder = labelTextBuilder;
    }

    public String getLayerId() {
        return this.layerId;
    }

    public boolean isLod() {
        return this.isLod;
    }

    public synchronized void show() {
        try {
            checkValidate();
            this.delegate.setVisible(this.isLod, this.layerId, this.labelId, true, false, 0);
            this.visible = true;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hide() {
        try {
            checkValidate();
            this.delegate.setVisible(this.isLod, this.layerId, this.labelId, false, false, 0);
            this.visible = false;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isShow() {
        return this.visible;
    }

    public synchronized void setClickable(boolean z) {
        try {
            checkValidate();
            this.clickable = z;
            this.delegate.setClickable(this.isLod, this.layerId, this.labelId, z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isClickable() {
        return this.clickable;
    }

    public synchronized void setRank(long j) {
        try {
            this.rank = j;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeRank(long j) {
        try {
            checkValidate();
            this.delegate.setRank(this.isLod, this.layerId, this.labelId, j);
            this.rank = j;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeStyles(LabelStyles labelStyles) {
        changeStyles(labelStyles, false);
    }

    public synchronized void changeStyles(LabelStyles labelStyles, boolean z) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (labelStyles == null) {
            MapLogger.e("changeStyles failure. LabelStyles is null");
        } else {
            this.delegate.changeStyles(this.layerId, this.labelId, labelStyles, z, this.isLod);
            this.styles = labelStyles;
        }
    }

    public synchronized void changeText(LabelTextBuilder labelTextBuilder) {
        changeText(labelTextBuilder, false);
    }

    public synchronized void changeText(LabelTextBuilder labelTextBuilder, boolean z) {
        try {
            checkValidate();
            this.delegate.changeStylesAndText(this.layerId, this.labelId, this.styles, z, labelTextBuilder, this.isLod);
            this.labelTextBuilder = labelTextBuilder;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeStylesAndText(LabelStyles labelStyles, LabelTextBuilder labelTextBuilder) {
        changeStylesAndText(labelStyles, labelTextBuilder, false);
    }

    public synchronized void changeStylesAndText(LabelStyles labelStyles, LabelTextBuilder labelTextBuilder, boolean z) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (labelStyles == null) {
            MapLogger.e("changeStylesAndText failure. LabelStyles is null");
            return;
        }
        this.delegate.changeStylesAndText(this.layerId, this.labelId, labelStyles, z, labelTextBuilder, this.isLod);
        this.styles = labelStyles;
        this.labelTextBuilder = labelTextBuilder;
    }

    public synchronized long getRank() {
        return this.rank;
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }

    public synchronized String[] getTexts() {
        LabelTextBuilder labelTextBuilder;
        labelTextBuilder = this.labelTextBuilder;
        return labelTextBuilder == null ? new String[0] : labelTextBuilder.getTexts();
    }

    public synchronized LabelStyles getStyles() {
        return this.styles;
    }

    public synchronized Badge[] addBadge(BadgeOptions... badgeOptionsArr) {
        Badge[] badgeArr;
        try {
            checkValidate();
            String[] strArrAddBadges = this.delegate.addBadges(isLod(), getLayerId(), getLabelId(), badgeOptionsArr);
            badgeArr = new Badge[strArrAddBadges.length];
            for (int i = 0; i < strArrAddBadges.length; i++) {
                BadgeOptions badgeOptions = badgeOptionsArr[i];
                PointF offset = badgeOptions.getOffset();
                badgeArr[i] = new Badge(this.delegate, this.isLod, this.layerId, this.labelId, strArrAddBadges[i], offset.x, offset.y, badgeOptions.getZOrder(), badgeOptions.getTag());
            }
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return badgeArr;
    }

    public synchronized void showAllStyleBadge() {
        try {
            checkValidate();
            this.delegate.visibleAllStyleBadge(this.isLod, this.layerId, this.labelId, true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hideAllStyleBadge() {
        try {
            checkValidate();
            this.delegate.visibleAllStyleBadge(this.isLod, this.layerId, this.labelId, false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void showStyleBadge(String str) {
        try {
            checkValidate();
            this.delegate.visibleStyleBadge(this.isLod, this.layerId, this.labelId, str, true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hideStyleBadge(String str) {
        try {
            checkValidate();
            this.delegate.visibleStyleBadge(this.isLod, this.layerId, this.labelId, str, false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeBadge(Badge badge) {
        try {
            checkValidate();
            this.delegate.removeBadge(this.isLod, this.layerId, this.labelId, badge.getId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeAllBadge() {
        try {
            checkValidate();
            this.delegate.removeAllBadge(this.isLod, this.layerId, this.labelId);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    protected void checkValidate() throws RuntimeException {
        if (this.isLod) {
            LodLabelLayer lodLabelLayer = this.delegate.getLodLabelLayer(this.layerId);
            if (lodLabelLayer == null) {
                throw new RuntimeException("LodLabelLayer(id=" + this.layerId + ") is removed. LodLabelLayer must be added first.");
            }
            if (!lodLabelLayer.hasLabel(this.labelId)) {
                throw new RuntimeException("LodLabel(id=" + this.labelId + ") is removed. LodLabel must be added first.");
            }
            return;
        }
        LabelLayer labelLayer = this.delegate.getLabelLayer(this.layerId);
        if (labelLayer == null) {
            throw new RuntimeException("LabelLayer(id=" + this.layerId + ") is removed. LabelLayer must be added first.");
        }
        if (!labelLayer.hasLabel(this.labelId)) {
            throw new RuntimeException("Label(id=" + this.labelId + ") is removed. Label must be added first.");
        }
    }
}
