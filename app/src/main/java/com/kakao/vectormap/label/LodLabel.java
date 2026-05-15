package com.kakao.vectormap.label;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ILabelDelegate;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class LodLabel extends PointLabel {
    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ Badge[] addBadge(BadgeOptions[] badgeOptionsArr) {
        return super.addBadge(badgeOptionsArr);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void changeRank(long j) {
        super.changeRank(j);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void changeStyles(LabelStyles labelStyles) {
        super.changeStyles(labelStyles);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void changeStyles(LabelStyles labelStyles, boolean z) {
        super.changeStyles(labelStyles, z);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void changeStylesAndText(LabelStyles labelStyles, LabelTextBuilder labelTextBuilder) {
        super.changeStylesAndText(labelStyles, labelTextBuilder);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void changeStylesAndText(LabelStyles labelStyles, LabelTextBuilder labelTextBuilder, boolean z) {
        super.changeStylesAndText(labelStyles, labelTextBuilder, z);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void changeText(LabelTextBuilder labelTextBuilder) {
        super.changeText(labelTextBuilder);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void changeText(LabelTextBuilder labelTextBuilder, boolean z) {
        super.changeText(labelTextBuilder, z);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ String getLayerId() {
        return super.getLayerId();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ long getRank() {
        return super.getRank();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ LabelStyles getStyles() {
        return super.getStyles();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ Object getTag() {
        return super.getTag();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ String[] getTexts() {
        return super.getTexts();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void hide() {
        super.hide();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void hideAllStyleBadge() {
        super.hideAllStyleBadge();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void hideStyleBadge(String str) {
        super.hideStyleBadge(str);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ boolean isClickable() {
        return super.isClickable();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ boolean isLod() {
        return super.isLod();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ boolean isShow() {
        return super.isShow();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void removeAllBadge() {
        super.removeAllBadge();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void removeBadge(Badge badge) {
        super.removeBadge(badge);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void setClickable(boolean z) {
        super.setClickable(z);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void setRank(long j) {
        super.setRank(j);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void setTag(Object obj) {
        super.setTag(obj);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void show() {
        super.show();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void showAllStyleBadge() {
        super.showAllStyleBadge();
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public /* bridge */ /* synthetic */ void showStyleBadge(String str) {
        super.showStyleBadge(str);
    }

    LodLabel(ILabelDelegate iLabelDelegate, String str, String str2, boolean z, long j, boolean z2, boolean z3, Object obj, LatLng latLng, LabelStyles labelStyles, LabelTextBuilder labelTextBuilder) {
        super(iLabelDelegate, str, str2, z, j, z2, z3, obj, latLng, labelStyles, labelTextBuilder);
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public synchronized LatLng getPosition() {
        return this.position;
    }

    public synchronized LodLabelLayer getLayer() {
        return this.delegate.getLodLabelLayer(getLayerId());
    }

    public synchronized void remove() {
        try {
            checkValidate();
            getLayer().remove(this);
        } catch (RuntimeException e) {
            MapLogger.e(e);
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
