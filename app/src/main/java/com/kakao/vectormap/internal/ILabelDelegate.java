package com.kakao.vectormap.internal;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.label.BadgeOptions;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelAnimator;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelLayerOptions;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyles;
import com.kakao.vectormap.label.LabelTextBuilder;
import com.kakao.vectormap.label.LodLabel;
import com.kakao.vectormap.label.LodLabelLayer;
import com.kakao.vectormap.label.OnLabelCreateCallback;
import com.kakao.vectormap.label.OnLodLabelCreateCallback;
import com.kakao.vectormap.label.OnPolylineLabelCreateCallback;
import com.kakao.vectormap.label.PathOptions;
import com.kakao.vectormap.label.PolylineLabel;
import com.kakao.vectormap.label.PolylineLabelOptions;
import com.kakao.vectormap.label.PolylineLabelStyles;
import com.kakao.vectormap.label.animation.AlphaAnimations;
import com.kakao.vectormap.label.animation.DropAnimation;
import com.kakao.vectormap.label.animation.ScaleAlphaAnimations;
import com.kakao.vectormap.label.animation.ScaleAnimations;
import com.kakao.vectormap.label.animation.TransformAnimations;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface ILabelDelegate {
    LabelAnimator addAlphaAnimator(AlphaAnimations alphaAnimations) throws RuntimeException;

    String[] addBadges(boolean z, String str, String str2, BadgeOptions... badgeOptionsArr) throws RuntimeException;

    LabelAnimator addDropAnimator(DropAnimation dropAnimation) throws RuntimeException;

    void addLabel(LabelLayer labelLayer, LabelOptions labelOptions, OnLabelCreateCallback onLabelCreateCallback) throws RuntimeException;

    String[] addLabelStyles(LabelStyles... labelStylesArr) throws RuntimeException;

    void addLabels(String str, boolean z, List<LabelOptions> list, List<LabelStyles> list2, String str2) throws RuntimeException;

    LabelLayer addLayer(LabelLayerOptions labelLayerOptions) throws RuntimeException;

    void addLodLabel(LodLabelLayer lodLabelLayer, LabelOptions labelOptions, OnLodLabelCreateCallback onLodLabelCreateCallback) throws RuntimeException;

    LodLabelLayer addLodLayer(LabelLayerOptions labelLayerOptions) throws RuntimeException;

    void addPolylineLabel(LabelLayer labelLayer, PolylineLabelOptions polylineLabelOptions, OnPolylineLabelCreateCallback onPolylineLabelCreateCallback) throws RuntimeException;

    String addPolylineLabelStyles(PolylineLabelStyles polylineLabelStyles) throws RuntimeException;

    void addPositionShareLabel(String str, String str2, String str3, String str4) throws RuntimeException;

    LabelAnimator addScaleAlphaAnimator(ScaleAlphaAnimations scaleAlphaAnimations) throws RuntimeException;

    LabelAnimator addScaleAnimator(ScaleAnimations scaleAnimations) throws RuntimeException;

    LabelAnimator addTransformAnimator(TransformAnimations transformAnimations) throws RuntimeException;

    void addTransformShare(String str, String str2, String str3, String str4, boolean z) throws RuntimeException;

    void changePixelOffset(Label label, float f, float f2, boolean z) throws RuntimeException;

    void changeStyles(String str, String str2, LabelStyles labelStyles, boolean z, boolean z2) throws RuntimeException;

    void changeStylesAndText(String str, String str2, LabelStyles labelStyles, boolean z, LabelTextBuilder labelTextBuilder, boolean z2) throws RuntimeException;

    void changeTextAndStyles(PolylineLabel polylineLabel, String str, PolylineLabelStyles polylineLabelStyles) throws RuntimeException;

    void clearAll() throws RuntimeException;

    LabelAnimator getAnimator(String str);

    LabelLayer getLabelLayer(String str) throws RuntimeException;

    LabelStyles getLabelStyles(String str) throws RuntimeException;

    LodLabelLayer getLodLabelLayer(String str) throws RuntimeException;

    LatLng getPosition(String str, String str2) throws RuntimeException;

    IMapResourceManager getResourceManager() throws RuntimeException;

    float getRotation(String str, String str2) throws RuntimeException;

    boolean hasLayer(String str) throws RuntimeException;

    boolean hasLodLayer(String str) throws RuntimeException;

    void moveOnPath(String str, String str2, PathOptions pathOptions, boolean z) throws RuntimeException;

    void moveTo(String str, String str2, LatLng latLng, int i) throws RuntimeException;

    void removeAllAnimator() throws RuntimeException;

    void removeAllBadge(boolean z, String str, String str2) throws RuntimeException;

    void removeAllLabel(boolean z, String str) throws RuntimeException;

    void removeAllLayer() throws RuntimeException;

    void removeAllLodLayer() throws RuntimeException;

    void removeAnimator(String str) throws RuntimeException;

    void removeBadge(boolean z, String str, String str2, String str3) throws RuntimeException;

    void removeLabel(boolean z, String str, String str2, boolean z2) throws RuntimeException;

    String[] removeLabels(String str, ILabel[] iLabelArr, boolean z) throws RuntimeException;

    void removeLayer(boolean z, String str) throws RuntimeException;

    String[] removeLodLabels(String str, LodLabel[] lodLabelArr) throws RuntimeException;

    void removePositionShareLabel(String str, String str2, String str3, String str4) throws RuntimeException;

    void removeTransformShare(String str, String str2, String str3, String str4, boolean z) throws RuntimeException;

    void rotateTo(String str, String str2, float f, int i) throws RuntimeException;

    void scaleTo(String str, String str2, float f, float f2, int i) throws RuntimeException;

    void setAllVisible(String str, boolean z, boolean z2, boolean z3) throws RuntimeException;

    void setBadgeOffset(boolean z, String str, String str2, String str3, float f, float f2) throws RuntimeException;

    void setBadgeVisible(boolean z, String str, String str2, String str3, boolean z2) throws RuntimeException;

    void setClickable(boolean z, String str, String str2, boolean z2) throws RuntimeException;

    void setLabelFactory(ILabelFactory iLabelFactory) throws RuntimeException;

    void setLayerClickable(String str, boolean z, boolean z2) throws RuntimeException;

    void setLayerVisible(boolean z, String str, boolean z2) throws RuntimeException;

    void setPosition(String str, String str2, LatLng latLng) throws RuntimeException;

    void setRank(boolean z, String str, String str2, long j) throws RuntimeException;

    void setRotation(String str, String str2, float f) throws RuntimeException;

    void setVisible(boolean z, String str, String str2, boolean z2, boolean z3, int i) throws RuntimeException;

    void setZOrder(String str, boolean z, int i) throws RuntimeException;

    void startAnimator(String str, List<Label> list);

    void stopAnimator(String str);

    void updateLabels(Label[] labelArr) throws RuntimeException;

    void visibleAllStyleBadge(boolean z, String str, String str2, boolean z2) throws RuntimeException;

    void visibleStyleBadge(boolean z, String str, String str2, String str3, boolean z2) throws RuntimeException;
}
