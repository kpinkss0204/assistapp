package com.kakao.vectormap.internal;

import com.kakao.vectormap.Const;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.label.BadgeOptions;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelAnimator;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelLayerOptions;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
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
import com.kakao.vectormap.label.animation.AlphaAnimation;
import com.kakao.vectormap.label.animation.AlphaAnimations;
import com.kakao.vectormap.label.animation.DropAnimation;
import com.kakao.vectormap.label.animation.ScaleAlphaAnimation;
import com.kakao.vectormap.label.animation.ScaleAlphaAnimations;
import com.kakao.vectormap.label.animation.ScaleAnimation;
import com.kakao.vectormap.label.animation.ScaleAnimations;
import com.kakao.vectormap.label.animation.TransformAnimation;
import com.kakao.vectormap.label.animation.TransformAnimations;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
class LabelDelegate extends Destroyable implements ILabelDelegate {
    private long appEngineHandle;
    private ILabelFactory factory;
    private MapResourceManager resourceManager;
    private String viewName;
    private final String DARK_STYLE_POSTFIX = "_dark";
    private Map<String, LabelLayer> layerMap = new ConcurrentHashMap();
    private Map<String, LodLabelLayer> lodLayerMap = new ConcurrentHashMap();
    private final Map<String, LabelAnimator> animatorMap = new ConcurrentHashMap();
    private LabelStyler labelStyler = new LabelStyler();

    static native void addAlphaAnimator(long j, String str, String str2, float f, boolean z, boolean z2, boolean z3, int i, float[] fArr, int[] iArr, int[] iArr2);

    static native void addDropAnimator(long j, String str, String str2, int i, int i2, int i3, boolean z, boolean z2, float f);

    static native void addLayer(long j, String str, String str2, int i, int i2, int i3, int i4, String[] strArr, float f, boolean z, boolean z2, boolean z3);

    static native void addPointLabel(long j, String str, String str2, boolean z, LabelOptions labelOptions, String str3, boolean z2, String str4);

    static native void addPointLabelStyles(long j, String str, int i, String[] strArr, LabelStyles[] labelStylesArr);

    static native void addPointLabels(long j, String str, String str2, boolean z, int i, LabelOptions[] labelOptionsArr, String[] strArr, String str3);

    static native void addPolylineLabel(long j, String str, String str2, String str3, String str4, double[] dArr, double[] dArr2, String[] strArr, long j2, boolean z, String str5);

    static native void addPolylineLabelStyles(long j, String str, String str2, PolylineLabelStyles polylineLabelStyles);

    public static native void addPositionShareLabel(long j, String str, String str2, String str3, String str4, String str5);

    static native void addScaleAlphaAnimator(long j, String str, String str2, float f, float f2, float f3, boolean z, boolean z2, boolean z3, int i, float[] fArr, float[] fArr2, float[] fArr3, int[] iArr, int[] iArr2);

    static native void addScaleAnimator(long j, String str, String str2, float f, float f2, boolean z, boolean z2, boolean z3, int i, float[] fArr, float[] fArr2, int[] iArr, int[] iArr2);

    static native void addTransformAnimator(long j, String str, String str2, float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2, boolean z3, int i, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, float[] fArr6, int[] iArr, int[] iArr2);

    static native void changePolylineStyles(long j, String str, String str2, String str3, PolylineLabelStyles polylineLabelStyles, String str4, String str5);

    static native void clearAll(long j, String str);

    static native void nativeInit();

    static native void removeAllAnimator(long j, String str);

    static native void removeAllGroup(long j, String str, boolean z);

    static native void removeAnimator(long j, String str, String str2);

    static native void removeGroup(long j, String str, boolean z, String str2);

    public static native void removePositionShareLabel(long j, String str, String str2, String str3, String str4, String str5);

    static native void removeStyle(long j, String str, String str2);

    static native void setGroupVisible(long j, String str, boolean z, String str2, boolean z2);

    static native void startAnimator(long j, String str, String str2, int i, String[] strArr, String[] strArr2);

    static native void stopAnimator(long j, String str, String str2);

    static {
        nativeInit();
    }

    public LabelDelegate(String str, long j, MapResourceManager mapResourceManager) {
        this.viewName = str;
        this.appEngineHandle = j;
        this.resourceManager = mapResourceManager;
    }

    @Override // com.kakao.vectormap.internal.Destroyable
    void onDestroy() {
        this.appEngineHandle = 0L;
        this.viewName = "";
        this.factory = null;
        this.resourceManager = null;
        this.layerMap.clear();
        this.layerMap = null;
        this.lodLayerMap.clear();
        this.lodLayerMap = null;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void setLabelFactory(ILabelFactory iLabelFactory) {
        this.factory = iLabelFactory;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public IMapResourceManager getResourceManager() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.resourceManager;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public String[] addLabelStyles(LabelStyles... labelStylesArr) throws RuntimeException {
        LabelStyles[] labelStylesArr2 = labelStylesArr;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String[] strArr = new String[labelStylesArr2.length];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (i < labelStylesArr2.length) {
            LabelStyles labelStyles = labelStylesArr2[i];
            for (LabelStyle labelStyle : labelStyles.getStyles()) {
                labelStyle.iconStyle.assetId = this.resourceManager.addImage(labelStyle.getIconResId(), labelStyle.getIconBitmap());
                labelStyle.assetId = labelStyle.iconStyle.assetId;
                for (BadgeOptions badgeOptions : labelStyle.badges) {
                    badgeOptions.assetId = this.resourceManager.addImage(badgeOptions.getResId(), badgeOptions.getBitmap());
                }
            }
            if (this.resourceManager.isDarkMode()) {
                strArr[i] = labelStyles.getStyleId() + "_dark";
                if (!this.labelStyler.hasDarkModeLabelStyles(labelStyles.getStyleId())) {
                    arrayList2.add(labelStyles);
                    arrayList.add(strArr[i]);
                    this.labelStyler.addDarkModeLabelStyles(labelStyles);
                }
            } else {
                strArr[i] = labelStyles.getStyleId();
                if (!this.labelStyler.hasLightModeLabelStyles(labelStyles.getStyleId())) {
                    arrayList2.add(labelStyles);
                    arrayList.add(strArr[i]);
                    this.labelStyler.addLightModeLabelStyles(labelStyles);
                }
            }
            i++;
            labelStylesArr2 = labelStylesArr;
        }
        int size = arrayList.size();
        if (size > 0) {
            addPointLabelStyles(this.appEngineHandle, this.viewName, size, (String[]) arrayList.toArray(new String[size]), (LabelStyles[]) arrayList2.toArray(new LabelStyles[size]));
        }
        return strArr;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public String addPolylineLabelStyles(PolylineLabelStyles polylineLabelStyles) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.resourceManager.isDarkMode()) {
            if (!this.labelStyler.hasDarkModePolylineLabelStyles(polylineLabelStyles.getStyleId())) {
                addPolylineLabelStyles(this.appEngineHandle, this.viewName, polylineLabelStyles.getStyleId() + "_dark", polylineLabelStyles);
                this.labelStyler.addDarkPolylineLabelStyles(polylineLabelStyles);
            }
            return polylineLabelStyles.getStyleId() + "_dark";
        }
        if (!this.labelStyler.hasLightModePolylineLabelStyles(polylineLabelStyles.getStyleId())) {
            addPolylineLabelStyles(this.appEngineHandle, this.viewName, polylineLabelStyles.getStyleId(), polylineLabelStyles);
            this.labelStyler.addLightPolylineLabelStyles(polylineLabelStyles);
        }
        return polylineLabelStyles.getStyleId();
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public LabelStyles getLabelStyles(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.resourceManager.isDarkMode()) {
            return this.labelStyler.getDarkModeLabelStyles(str);
        }
        return this.labelStyler.getLightModeLabelStyles(str);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized LabelLayer getLabelLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.layerMap.get(str);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized LodLabelLayer getLodLabelLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.lodLayerMap.get(str);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized boolean hasLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.layerMap.containsKey(str);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized boolean hasLodLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.lodLayerMap.containsKey(str);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized LabelLayer addLayer(LabelLayerOptions labelLayerOptions) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        addLayer(this.appEngineHandle, this.viewName, labelLayerOptions.getLayerId(), labelLayerOptions.getZOrder(), labelLayerOptions.getCompetitionType().getValue(), labelLayerOptions.getCompetitionUnit().getValue(), labelLayerOptions.getOrderingType().getValue(), labelLayerOptions.getCompetitionExceptions(), labelLayerOptions.getLodRadius(), false, labelLayerOptions.isVisible(), labelLayerOptions.isClickable());
        this.layerMap.put(labelLayerOptions.getLayerId(), this.factory.newLayer(this, labelLayerOptions.getLayerId(), labelLayerOptions, this.factory));
        return this.layerMap.get(labelLayerOptions.getLayerId());
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized LodLabelLayer addLodLayer(LabelLayerOptions labelLayerOptions) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        addLayer(this.appEngineHandle, this.viewName, labelLayerOptions.getLayerId(), labelLayerOptions.getZOrder(), labelLayerOptions.getCompetitionType().getValue(), labelLayerOptions.getCompetitionUnit().getValue(), labelLayerOptions.getOrderingType().getValue(), labelLayerOptions.getCompetitionExceptions(), labelLayerOptions.getLodRadius(), true, labelLayerOptions.isVisible(), labelLayerOptions.isClickable());
        this.lodLayerMap.put(labelLayerOptions.getLayerId(), this.factory.newLodLayer(this, labelLayerOptions.getLayerId(), labelLayerOptions, this.factory));
        return this.lodLayerMap.get(labelLayerOptions.getLayerId());
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void removeLayer(boolean z, String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeGroup(this.appEngineHandle, this.viewName, z, str);
        if (z) {
            this.lodLayerMap.remove(str);
        } else {
            this.layerMap.remove(str);
        }
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void removeAllLayer() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAllGroup(this.appEngineHandle, this.viewName, false);
        this.layerMap.clear();
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void removeAllLodLayer() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAllGroup(this.appEngineHandle, this.viewName, true);
        this.lodLayerMap.clear();
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void clearAll() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        clearAll(this.appEngineHandle, this.viewName);
        this.layerMap.clear();
        this.lodLayerMap.clear();
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void addLabel(LabelLayer labelLayer, LabelOptions labelOptions, OnLabelCreateCallback onLabelCreateCallback) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        addPointLabel(this.appEngineHandle, this.viewName, labelLayer.getLayerId(), labelLayer.isLodLayer(), labelOptions, addLabelStyles(labelOptions.getStyles())[0], labelOptions.isVisible(), onLabelCreateCallback == null ? "" : labelLayer.addCallback(onLabelCreateCallback, labelOptions));
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void addLabels(String str, boolean z, List<LabelOptions> list, List<LabelStyles> list2, String str2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String[] strArrAddLabelStyles = addLabelStyles((LabelStyles[]) list2.toArray(new LabelStyles[list2.size()]));
        int size = list.size();
        addPointLabels(this.appEngineHandle, this.viewName, str, z, size, (LabelOptions[]) list.toArray(new LabelOptions[size]), strArrAddLabelStyles, str2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void addLodLabel(LodLabelLayer lodLabelLayer, LabelOptions labelOptions, OnLodLabelCreateCallback onLodLabelCreateCallback) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        addPointLabel(this.appEngineHandle, this.viewName, lodLabelLayer.getLayerId(), lodLabelLayer.isLodLayer(), labelOptions, addLabelStyles(labelOptions.getStyles())[0], labelOptions.isVisible(), onLodLabelCreateCallback == null ? "" : lodLabelLayer.addCallback(onLodLabelCreateCallback, labelOptions));
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void addPolylineLabel(LabelLayer labelLayer, PolylineLabelOptions polylineLabelOptions, OnPolylineLabelCreateCallback onPolylineLabelCreateCallback) throws RuntimeException {
        LabelLayer labelLayer2;
        PolylineLabelOptions polylineLabelOptions2;
        String strAddCallback;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String strAddPolylineLabelStyles = addPolylineLabelStyles(polylineLabelOptions.getStyles());
        if (onPolylineLabelCreateCallback == null) {
            strAddCallback = "";
            labelLayer2 = labelLayer;
            polylineLabelOptions2 = polylineLabelOptions;
        } else {
            labelLayer2 = labelLayer;
            polylineLabelOptions2 = polylineLabelOptions;
            strAddCallback = labelLayer2.addCallback(onPolylineLabelCreateCallback, polylineLabelOptions2);
        }
        String str = strAddCallback;
        int length = polylineLabelOptions2.getPoints().length;
        double[] dArr = new double[length];
        double[] dArr2 = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = polylineLabelOptions2.getPoints()[i].latitude;
            dArr2[i] = polylineLabelOptions2.getPoints()[i].longitude;
        }
        addPolylineLabel(this.appEngineHandle, this.viewName, labelLayer2.getLayerId(), polylineLabelOptions.getLabelId(), strAddPolylineLabelStyles, dArr, dArr2, polylineLabelOptions.getTexts(), polylineLabelOptions.getRank(), polylineLabelOptions.isVisible(), str);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void setVisible(boolean z, String str, String str2, boolean z2, boolean z3, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setVisible(this.appEngineHandle, this.viewName, str, str2, z2, z, z3, i);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void setClickable(boolean z, String str, String str2, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setClickable(this.appEngineHandle, this.viewName, str, str2, z2, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void setRank(boolean z, String str, String str2, long j) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setRank(this.appEngineHandle, this.viewName, str, str2, j, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void setLayerVisible(boolean z, String str, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setGroupVisible(this.appEngineHandle, this.viewName, z, str, z2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void removeLabel(boolean z, String str, String str2, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.removeLabel(this.appEngineHandle, this.viewName, str, str2, z, z2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized String[] removeLabels(String str, ILabel[] iLabelArr, boolean z) throws RuntimeException {
        String[] strArr;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        ArrayList arrayList = new ArrayList();
        for (ILabel iLabel : iLabelArr) {
            if (iLabel != null) {
                arrayList.add(iLabel.getLabelId());
            }
        }
        strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        LabelController.removeLabels(this.appEngineHandle, this.viewName, str, strArr, false, z);
        return strArr;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized String[] removeLodLabels(String str, LodLabel[] lodLabelArr) throws RuntimeException {
        String[] strArr;
        ArrayList arrayList = new ArrayList();
        for (LodLabel lodLabel : lodLabelArr) {
            if (lodLabel != null) {
                arrayList.add(lodLabel.getLabelId());
            }
        }
        strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        LabelController.removeLabels(this.appEngineHandle, this.viewName, str, strArr, true, false);
        return strArr;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void removeAllLabel(boolean z, String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.removeAllLabel(this.appEngineHandle, this.viewName, str, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void setPosition(String str, String str2, LatLng latLng) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setPosition(this.appEngineHandle, this.viewName, str, str2, latLng.getLatitude(), latLng.getLongitude());
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void setRotation(String str, String str2, float f) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setRotation(this.appEngineHandle, this.viewName, str, str2, f);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void moveTo(String str, String str2, LatLng latLng, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.moveTo(this.appEngineHandle, this.viewName, str, str2, latLng.latitude, latLng.longitude, i);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void rotateTo(String str, String str2, float f, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.rotateTo(this.appEngineHandle, this.viewName, str, str2, f, i);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void scaleTo(String str, String str2, float f, float f2, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.scaleTo(this.appEngineHandle, this.viewName, str, str2, f, f2, i);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void moveOnPath(String str, String str2, PathOptions pathOptions, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.moveOnPath(this.appEngineHandle, this.viewName, str, str2, pathOptions.latArray, pathOptions.lngArray, pathOptions.getDuration(), pathOptions.getCornerRadius(), pathOptions.getJumpThreshold(), pathOptions.getBaseRadian(), pathOptions.getSimplifyWeight(), z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void changeTextAndStyles(PolylineLabel polylineLabel, String str, PolylineLabelStyles polylineLabelStyles) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        changePolylineStyles(this.appEngineHandle, this.viewName, polylineLabel.getLayerId(), polylineLabel.getLabelId(), polylineLabelStyles, addPolylineLabelStyles(polylineLabelStyles), polylineLabel.getText());
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized LatLng getPosition(String str, String str2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return LabelController.getPosition(this.appEngineHandle, this.viewName, str, str2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized float getRotation(String str, String str2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return LabelController.getOrientation(this.appEngineHandle, this.viewName, str, str2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void changePixelOffset(Label label, float f, float f2, boolean z) throws RuntimeException {
        boolean z2;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (z) {
            for (LabelStyle labelStyle : label.getStyles().getStyles()) {
                if (labelStyle.isApplyDpScale()) {
                    z2 = false;
                    break;
                }
            }
            z2 = z;
        } else {
            z2 = z;
        }
        LabelController.changePixelOffset(this.appEngineHandle, this.viewName, label.getLayerId(), label.getLabelId(), f, f2, z2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized String[] addBadges(boolean z, String str, String str2, BadgeOptions... badgeOptionsArr) throws RuntimeException {
        String[] strArr;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int length = badgeOptionsArr.length;
        strArr = new String[length];
        String[] strArr2 = new String[length];
        float[] fArr = new float[length];
        float[] fArr2 = new float[length];
        int[] iArr = new int[length];
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            BadgeOptions badgeOptions = badgeOptionsArr[i];
            strArr[i] = badgeOptions.getId();
            badgeOptions.assetId = this.resourceManager.addImage(badgeOptions.getResId(), badgeOptions.getBitmap());
            strArr2[i] = badgeOptions.assetId;
            fArr[i] = badgeOptions.getOffset().x;
            fArr2[i] = badgeOptions.getOffset().y;
            iArr[i] = badgeOptions.getZOrder();
        }
        LabelController.addBadges(this.appEngineHandle, this.viewName, str, str2, strArr, strArr2, fArr, fArr2, iArr, z);
        return strArr;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void setBadgeVisible(boolean z, String str, String str2, String str3, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setBadgeVisible(this.appEngineHandle, this.viewName, str, str2, str3, z2, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void setBadgeOffset(boolean z, String str, String str2, String str3, float f, float f2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setBadgeOffset(this.appEngineHandle, this.viewName, str, str2, str3, f, f2, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void removeBadge(boolean z, String str, String str2, String str3) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.removeBadge(this.appEngineHandle, this.viewName, str, str2, str3, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void removeAllBadge(boolean z, String str, String str2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.removeAllBadge(this.appEngineHandle, this.viewName, str, str2, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void visibleAllStyleBadge(boolean z, String str, String str2, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelCommonController.setVisibleAllStyleBadge(this.appEngineHandle, this.viewName, str, str2, z, z2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void visibleStyleBadge(boolean z, String str, String str2, String str3, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelCommonController.setVisibleStyleBadge(this.appEngineHandle, this.viewName, str, str2, z, str3, z2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void addTransformShare(String str, String str2, String str3, String str4, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.addTransformShare(this.appEngineHandle, this.viewName, str, str2, str3, str4, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public synchronized void removeTransformShare(String str, String str2, String str3, String str4, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.removeTransformShare(this.appEngineHandle, this.viewName, str, str2, str3, str4, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void updateLabels(Label[] labelArr) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.updateLabels(this.appEngineHandle, this.viewName, labelArr.length, labelArr);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void setAllVisible(String str, boolean z, boolean z2, boolean z3) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setAllVisible(this.appEngineHandle, this.viewName, str, z, z2, z3);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void setLayerClickable(String str, boolean z, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setLayerClickable(this.appEngineHandle, this.viewName, str, z, z2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void setZOrder(String str, boolean z, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.setZOrder(this.appEngineHandle, this.viewName, str, i, z);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void addPositionShareLabel(String str, String str2, String str3, String str4) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        addPositionShareLabel(this.appEngineHandle, this.viewName, str, str2, str3, str4);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void removePositionShareLabel(String str, String str2, String str3, String str4) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removePositionShareLabel(this.appEngineHandle, this.viewName, str, str2, str3, str4);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void changeStyles(String str, String str2, LabelStyles labelStyles, boolean z, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LabelController.changeStylesAndText(this.appEngineHandle, this.viewName, z2, str, str2, addLabelStyles(labelStyles)[0], labelStyles, z, false, null, null);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void changeStylesAndText(String str, String str2, LabelStyles labelStyles, boolean z, LabelTextBuilder labelTextBuilder, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String[] strArrAddLabelStyles = addLabelStyles(labelStyles);
        LabelTextBuilder labelTextBuilder2 = labelTextBuilder == null ? new LabelTextBuilder() : labelTextBuilder;
        LabelController.changeStylesAndText(this.appEngineHandle, this.viewName, z2, str, str2, strArrAddLabelStyles[0], labelStyles, z, labelTextBuilder2.getTextLineCount() > 0, labelTextBuilder2.getTexts(), labelTextBuilder2.getTextIndexes());
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public LabelAnimator addDropAnimator(DropAnimation dropAnimation) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        addDropAnimator(this.appEngineHandle, this.viewName, dropAnimation.getId(), dropAnimation.getInterpolation().getValue(), dropAnimation.getRepeatCount(), dropAnimation.getDuration(), dropAnimation.isHideShapeAtStop(), dropAnimation.isRemoveLabelAtStop(), dropAnimation.getPixelHeight());
        LabelAnimator labelAnimatorNewAnimator = this.factory.newAnimator(dropAnimation.getId(), this, dropAnimation.isHideShapeAtStop(), dropAnimation.isRemoveLabelAtStop());
        this.animatorMap.put(labelAnimatorNewAnimator.getId(), labelAnimatorNewAnimator);
        return labelAnimatorNewAnimator;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public LabelAnimator addScaleAnimator(ScaleAnimations scaleAnimations) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int scaleAnimationCount = scaleAnimations.getScaleAnimationCount();
        float[] fArr = new float[scaleAnimationCount];
        float[] fArr2 = new float[scaleAnimationCount];
        int[] iArr = new int[scaleAnimationCount];
        int[] iArr2 = new int[scaleAnimationCount];
        for (int i = 0; i < scaleAnimationCount; i++) {
            ScaleAnimation scaleAnimation = scaleAnimations.getScaleAnimations().get(i);
            fArr[i] = scaleAnimation.getScale().x;
            fArr2[i] = scaleAnimation.getScale().y;
            iArr[i] = scaleAnimation.getDuration();
            iArr2[i] = scaleAnimation.getInterpolation().getValue();
        }
        addScaleAnimator(this.appEngineHandle, this.viewName, scaleAnimations.getId(), scaleAnimations.getInitScale().x, scaleAnimations.getInitScale().y, scaleAnimations.isHideLabelAtStop(), scaleAnimations.isRemoveLabelAtStop(), scaleAnimations.isResetToInitialState(), scaleAnimationCount, fArr, fArr2, iArr2, iArr);
        LabelAnimator labelAnimatorNewAnimator = this.factory.newAnimator(scaleAnimations.getId(), this, scaleAnimations.isHideLabelAtStop(), scaleAnimations.isRemoveLabelAtStop());
        this.animatorMap.put(labelAnimatorNewAnimator.getId(), labelAnimatorNewAnimator);
        return labelAnimatorNewAnimator;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public LabelAnimator addScaleAlphaAnimator(ScaleAlphaAnimations scaleAlphaAnimations) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int scaleAlphaAnimationCount = scaleAlphaAnimations.getScaleAlphaAnimationCount();
        float[] fArr = new float[scaleAlphaAnimationCount];
        float[] fArr2 = new float[scaleAlphaAnimationCount];
        float[] fArr3 = new float[scaleAlphaAnimationCount];
        int[] iArr = new int[scaleAlphaAnimationCount];
        int[] iArr2 = new int[scaleAlphaAnimationCount];
        for (int i = 0; i < scaleAlphaAnimationCount; i++) {
            ScaleAlphaAnimation scaleAlphaAnimation = scaleAlphaAnimations.getScaleAlphaAnimations().get(i);
            fArr[i] = scaleAlphaAnimation.getScale().x;
            fArr2[i] = scaleAlphaAnimation.getScale().y;
            fArr3[i] = scaleAlphaAnimation.getAlpha();
            iArr[i] = scaleAlphaAnimation.getDuration();
            iArr2[i] = scaleAlphaAnimation.getInterpolation().getValue();
        }
        addScaleAlphaAnimator(this.appEngineHandle, this.viewName, scaleAlphaAnimations.getId(), scaleAlphaAnimations.getInitScale().x, scaleAlphaAnimations.getInitScale().y, scaleAlphaAnimations.getInitAlpha(), scaleAlphaAnimations.isHideLabelAtStop(), scaleAlphaAnimations.isRemoveLabelAtStop(), scaleAlphaAnimations.isResetToInitialState(), scaleAlphaAnimationCount, fArr, fArr2, fArr3, iArr2, iArr);
        LabelAnimator labelAnimatorNewAnimator = this.factory.newAnimator(scaleAlphaAnimations.getId(), this, scaleAlphaAnimations.isHideLabelAtStop(), scaleAlphaAnimations.isRemoveLabelAtStop());
        this.animatorMap.put(labelAnimatorNewAnimator.getId(), labelAnimatorNewAnimator);
        return labelAnimatorNewAnimator;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public LabelAnimator addAlphaAnimator(AlphaAnimations alphaAnimations) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int alphaAnimationCount = alphaAnimations.getAlphaAnimationCount();
        float[] fArr = new float[alphaAnimationCount];
        float[] fArr2 = new float[alphaAnimationCount];
        float[] fArr3 = new float[alphaAnimationCount];
        float[] fArr4 = new float[alphaAnimationCount];
        int[] iArr = new int[alphaAnimationCount];
        int[] iArr2 = new int[alphaAnimationCount];
        for (int i = 0; i < alphaAnimationCount; i++) {
            AlphaAnimation alphaAnimation = alphaAnimations.getAlphaAnimations().get(i);
            fArr[i] = alphaAnimation.getAlpha();
            iArr[i] = alphaAnimation.getDuration();
            iArr2[i] = alphaAnimation.getInterpolation().getValue();
        }
        addAlphaAnimator(this.appEngineHandle, this.viewName, alphaAnimations.getId(), alphaAnimations.getInitAlpha(), alphaAnimations.isHideLabelAtStop(), alphaAnimations.isRemoveLabelAtStop(), alphaAnimations.isResetToInitialState(), alphaAnimationCount, fArr, iArr2, iArr);
        LabelAnimator labelAnimatorNewAnimator = this.factory.newAnimator(alphaAnimations.getId(), this, alphaAnimations.isHideLabelAtStop(), alphaAnimations.isRemoveLabelAtStop());
        this.animatorMap.put(labelAnimatorNewAnimator.getId(), labelAnimatorNewAnimator);
        return labelAnimatorNewAnimator;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public LabelAnimator addTransformAnimator(TransformAnimations transformAnimations) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int transformAnimationCount = transformAnimations.getTransformAnimationCount();
        float[] fArr = new float[transformAnimationCount];
        float[] fArr2 = new float[transformAnimationCount];
        float[] fArr3 = new float[transformAnimationCount];
        float[] fArr4 = new float[transformAnimationCount];
        float[] fArr5 = new float[transformAnimationCount];
        int[] iArr = new int[transformAnimationCount];
        int[] iArr2 = new int[transformAnimationCount];
        float[] fArr6 = new float[transformAnimationCount];
        for (int i = 0; i < transformAnimationCount; i++) {
            TransformAnimation transformAnimation = transformAnimations.getTransformAnimations().get(i);
            fArr[i] = transformAnimation.getScale().x;
            fArr2[i] = transformAnimation.getScale().y;
            fArr3[i] = transformAnimation.getPixelTranslation().x;
            fArr4[i] = transformAnimation.getPixelTranslation().x;
            fArr5[i] = transformAnimation.getRotation();
            fArr6[i] = transformAnimation.getAlpha();
            iArr[i] = transformAnimation.getDuration();
            iArr2[i] = transformAnimation.getInterpolation().getValue();
        }
        addTransformAnimator(this.appEngineHandle, this.viewName, transformAnimations.getId(), transformAnimations.getInitScale().x, transformAnimations.getInitScale().y, transformAnimations.getInitPixelTranslation().x, transformAnimations.getInitPixelTranslation().y, transformAnimations.getInitRotation(), transformAnimations.getInitAlpha(), transformAnimations.isHideLabelAtStop(), transformAnimations.isRemoveLabelAtStop(), transformAnimations.isResetToInitialState(), transformAnimationCount, fArr, fArr2, fArr3, fArr4, fArr5, fArr6, iArr2, iArr);
        LabelAnimator labelAnimatorNewAnimator = this.factory.newAnimator(transformAnimations.getId(), this, transformAnimations.isHideLabelAtStop(), transformAnimations.isRemoveLabelAtStop());
        this.animatorMap.put(labelAnimatorNewAnimator.getId(), labelAnimatorNewAnimator);
        return labelAnimatorNewAnimator;
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void removeAnimator(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAnimator(this.appEngineHandle, this.viewName, str);
        this.animatorMap.remove(str);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void removeAllAnimator() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAllAnimator(this.appEngineHandle, this.viewName);
        this.animatorMap.clear();
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void startAnimator(String str, List<Label> list) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String[] strArr = new String[list.size()];
        String[] strArr2 = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getLayerId();
            strArr2[i] = list.get(i).getLabelId();
        }
        startAnimator(this.appEngineHandle, this.viewName, str, list.size(), strArr, strArr2);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public void stopAnimator(String str) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        stopAnimator(this.appEngineHandle, this.viewName, str);
    }

    @Override // com.kakao.vectormap.internal.ILabelDelegate
    public LabelAnimator getAnimator(String str) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.animatorMap.get(str);
    }
}
