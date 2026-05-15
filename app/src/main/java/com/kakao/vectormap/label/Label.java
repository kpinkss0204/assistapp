package com.kakao.vectormap.label;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.Log;
import com.kakao.vectormap.Const;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ILabelDelegate;
import com.kakao.vectormap.shape.Polygon;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class Label extends PointLabel {
    public boolean direction;
    private PointF offset;
    public PathOptions pathOptions;
    private float rotate;
    private PointF scale;

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

    Label(ILabelDelegate iLabelDelegate, String str, String str2, boolean z, long j, boolean z2, boolean z3, Object obj, LatLng latLng, LabelStyles labelStyles, LabelTextBuilder labelTextBuilder) {
        super(iLabelDelegate, str, str2, z, j, z2, z3, obj, latLng, labelStyles, labelTextBuilder);
        this.offset = new PointF(0.0f, 0.0f);
        this.direction = false;
        this.rotate = 0.0f;
        this.scale = new PointF(0.0f, 0.0f);
        this.labelTextBuilder = labelTextBuilder;
    }

    public synchronized LabelLayer getLayer() {
        return this.delegate.getLabelLayer(getLayerId());
    }

    public synchronized void changePixelOffset(float f, float f2) {
        changePixelOffset(f, f2, true);
    }

    public synchronized void changePixelOffset(float f, float f2, boolean z) {
        try {
            this.delegate.changePixelOffset(this, f, f2, z);
            this.offset.x = f;
            this.offset.y = f2;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized PointF getPixelOffset() {
        return new PointF(this.offset.x, this.offset.y);
    }

    public synchronized void show(boolean z) {
        try {
            checkValidate();
            this.delegate.setVisible(this.isLod, this.layerId, this.labelId, true, z, 300);
            this.visible = true;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void show(boolean z, int i) {
        try {
            checkValidate();
            this.delegate.setVisible(this.isLod, this.layerId, this.labelId, true, z, i);
            this.visible = true;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void moveTo(LatLng latLng) {
        try {
            checkValidate();
            this.delegate.setPosition(getLayerId(), getLabelId(), latLng);
            this.position = latLng;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void rotateTo(float f) {
        try {
            checkValidate();
            this.delegate.setRotation(getLayerId(), getLabelId(), f);
            this.rotate = f;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void scaleTo(float f, float f2) {
        try {
            checkValidate();
            this.delegate.scaleTo(getLayerId(), getLabelId(), f, f2, 0);
            this.scale = new PointF(f, f2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void moveTo(LatLng latLng, int i) {
        try {
            checkValidate();
            this.delegate.moveTo(getLayerId(), getLabelId(), latLng, i);
            this.position = latLng;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void rotateTo(float f, int i) {
        try {
            checkValidate();
            this.delegate.rotateTo(getLayerId(), getLabelId(), f, i);
            this.rotate = f;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void scaleTo(float f, float f2, int i) {
        try {
            checkValidate();
            this.delegate.scaleTo(getLayerId(), getLabelId(), f, f2, i);
            this.scale = new PointF(f, f2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setPathOptions(PathOptions pathOptions) {
        setPathOptions(pathOptions, false);
    }

    public synchronized void setPathOptions(PathOptions pathOptions, boolean z) {
        this.pathOptions = pathOptions;
        this.direction = z;
        pathOptions.toArray();
        this.changes |= 2;
    }

    public synchronized PathOptions getPathOptions() {
        return this.pathOptions;
    }

    public synchronized boolean isDirection() {
        return this.direction;
    }

    public synchronized void moveOnPath(PathOptions pathOptions) {
        try {
            checkValidate();
            moveOnPath(pathOptions, false);
            this.pathOptions = pathOptions;
            this.direction = false;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void moveOnPath(PathOptions pathOptions, boolean z) {
        try {
            checkValidate();
            pathOptions.toArray();
            this.delegate.moveOnPath(getLayerId(), getLabelId(), pathOptions, z);
            this.pathOptions = pathOptions;
            this.direction = z;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setStyles(LabelStyles labelStyles) {
        this.styles = labelStyles;
    }

    public synchronized void setStyles(LabelStyle... labelStyleArr) {
        this.styles = LabelStyles.from(labelStyleArr);
    }

    public synchronized void setStyles(int i) {
        this.styles = LabelStyles.from(LabelStyle.from(i));
    }

    public synchronized void setStyles(Bitmap bitmap) {
        this.styles = LabelStyles.from(LabelStyle.from(bitmap));
    }

    public synchronized void setTexts(LabelTextBuilder labelTextBuilder) {
        this.labelTextBuilder = labelTextBuilder;
    }

    public synchronized void invalidate() {
        invalidate(false);
    }

    public synchronized void invalidate(boolean z) {
        try {
            checkValidate();
            this.styles.invalidate();
            this.delegate.changeStylesAndText(this.layerId, this.labelId, this.styles, z, this.labelTextBuilder, this.isLod);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    @Override // com.kakao.vectormap.label.PointLabel
    public synchronized LatLng getPosition() {
        return this.position;
    }

    public synchronized float getRotation() {
        return this.rotate;
    }

    public synchronized PointF getScale() {
        return this.scale;
    }

    public synchronized void addShareTransform(Label label) {
        try {
            checkValidate();
            this.delegate.addTransformShare(getLayerId(), getLabelId(), label.getLayerId(), label.getLabelId(), true);
        } catch (RuntimeException e) {
            Log.d(Const.TAG, e.getLocalizedMessage());
        }
    }

    public synchronized void removeShareTransform(Label label) {
        try {
            checkValidate();
            this.delegate.removeTransformShare(getLayerId(), getLabelId(), label.getLayerId(), label.getLabelId(), true);
        } catch (RuntimeException e) {
            Log.d(Const.TAG, e.getLocalizedMessage());
        }
    }

    public synchronized void addShareTransform(Polygon polygon) {
        try {
            checkValidate();
            this.delegate.addTransformShare(getLayerId(), getLabelId(), polygon.getLayerId(), polygon.getId(), false);
        } catch (RuntimeException e) {
            Log.d(Const.TAG, e.getLocalizedMessage());
        }
    }

    public synchronized void removeShareTransform(Polygon polygon) {
        try {
            checkValidate();
            this.delegate.removeTransformShare(getLayerId(), getLabelId(), polygon.getLayerId(), polygon.getId(), false);
        } catch (RuntimeException e) {
            Log.d(Const.TAG, e.getLocalizedMessage());
        }
    }

    public void remove() {
        try {
            checkValidate();
            getLayer().remove(this);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void addSharePosition(Label label) {
        try {
            checkValidate();
            this.delegate.addPositionShareLabel(this.layerId, this.labelId, label.getLayerId(), label.getLabelId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeSharePosition(Label label) {
        try {
            checkValidate();
            this.delegate.removePositionShareLabel(this.layerId, this.labelId, label.getLayerId(), label.getLabelId());
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
        return Objects.equals(this.labelId, label.labelId) && Objects.equals(this.layerId, label.layerId);
    }

    public int hashCode() {
        return Objects.hash(this.labelId, this.layerId);
    }
}
