package com.kakao.vectormap.label;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ILabelDelegate;
import com.kakao.vectormap.internal.ILabelFactory;
import com.kakao.vectormap.internal.ILodLabelContainer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LodLabelLayer extends ILodLabelContainer {
    public static int DEFAULT_Z_ORDER = 10001;
    private boolean clickable;
    private final CompetitionType competitionType;
    private final CompetitionUnit competitionUnit;
    private final boolean isLodLayer;
    private final OrderingType orderingType;
    private float radius;
    private Object tag;
    private boolean visible;
    private int zOrder;

    LodLabelLayer(ILabelDelegate iLabelDelegate, String str, int i, CompetitionType competitionType, CompetitionUnit competitionUnit, OrderingType orderingType, float f, boolean z, boolean z2, boolean z3, Object obj, ILabelFactory iLabelFactory) {
        super(iLabelDelegate, str, iLabelFactory);
        this.zOrder = i;
        this.radius = f;
        this.competitionType = competitionType;
        this.competitionUnit = competitionUnit;
        this.orderingType = orderingType;
        this.isLodLayer = z;
        this.visible = z2;
        this.clickable = z3;
        this.tag = obj;
    }

    public String getLayerId() {
        return this.layerId;
    }

    public CompetitionUnit getCompetitionUnit() {
        return this.competitionUnit;
    }

    public CompetitionType getCompetitionType() {
        return this.competitionType;
    }

    public OrderingType getOrderingType() {
        return this.orderingType;
    }

    public boolean isLodLayer() {
        return this.isLodLayer;
    }

    public synchronized int getZOrder() {
        return this.zOrder;
    }

    public synchronized void setZOrder(int i) {
        try {
            checkValidate();
            this.delegate.setZOrder(this.layerId, this.isLodLayer, i);
            this.zOrder = i;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    private void checkLabelOptions(LabelOptions... labelOptionsArr) throws RuntimeException {
        checkValidate();
        if (labelOptionsArr == null || labelOptionsArr.length <= 0) {
            throw new RuntimeException("addLodLabel failure. LabelOptions is invalid.");
        }
    }

    private void checkLabelStyles(LabelStyles labelStyles) throws RuntimeException {
        if (labelStyles == null) {
            throw new RuntimeException("LabelStyles is null.");
        }
    }

    public synchronized LodLabel addLodLabel(LabelOptions labelOptions) {
        try {
            checkLabelOptions(labelOptions);
            checkLabelStyles(labelOptions.getStyles());
            if (this.labelMap.containsKey(labelOptions.getLabelId())) {
                return (LodLabel) this.labelMap.get(labelOptions.getLabelId());
            }
            this.delegate.addLodLabel(this, labelOptions, null);
            return newLodLabel(labelOptions);
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized void addLodLabel(LabelOptions labelOptions, OnLodLabelCreateCallback onLodLabelCreateCallback) {
        try {
            checkLabelOptions(labelOptions);
            checkLabelStyles(labelOptions.getStyles());
            if (this.labelMap.containsKey(labelOptions.getLabelId()) && onLodLabelCreateCallback != null) {
                onLodLabelCreateCallback.onLodLabelCreated(this, (LodLabel) this.labelMap.get(this.labelMap));
            }
            this.delegate.addLodLabel(this, labelOptions, onLodLabelCreateCallback);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    private LodLabel[] addPointLabels(LabelOptions[] labelOptionsArr, OnLodLabelsCreateCallback onLodLabelsCreateCallback) throws RuntimeException {
        checkLabelOptions(labelOptionsArr);
        int length = labelOptionsArr.length;
        LodLabel[] lodLabelArr = new LodLabel[length];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < length; i++) {
            LabelOptions labelOptions = labelOptionsArr[i];
            checkLabelStyles(labelOptions.getStyles());
            if (this.labelMap.containsKey(labelOptions.getLabelId())) {
                lodLabelArr[i] = (LodLabel) this.labelMap.get(labelOptions.getLabelId());
            } else {
                arrayList2.add(labelOptions);
                arrayList.add(labelOptions.getStyles());
                lodLabelArr[i] = newLodLabel(labelOptions);
            }
        }
        this.delegate.addLabels(getLayerId(), isLodLayer(), arrayList2, arrayList, onLodLabelsCreateCallback == null ? "" : addCallback(onLodLabelsCreateCallback, labelOptionsArr));
        return lodLabelArr;
    }

    public synchronized LodLabel[] addLodLabels(LabelOptions[] labelOptionsArr) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return addPointLabels(labelOptionsArr, null);
    }

    public synchronized void addLodLabels(LabelOptions[] labelOptionsArr, OnLodLabelsCreateCallback onLodLabelsCreateCallback) {
        try {
            addPointLabels(labelOptionsArr, onLodLabelsCreateCallback);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized LodLabel[] addLodLabels(List<LabelOptions> list) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return addPointLabels((LabelOptions[]) list.toArray(new LabelOptions[list.size()]), null);
    }

    public synchronized void addLodLabels(List<LabelOptions> list, OnLodLabelsCreateCallback onLodLabelsCreateCallback) {
        try {
            addPointLabels((LabelOptions[]) list.toArray(new LabelOptions[list.size()]), onLodLabelsCreateCallback);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean hasLabel(com.kakao.vectormap.label.LodLabel r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.Map<java.lang.String, com.kakao.vectormap.internal.ILabel> r0 = r2.labelMap     // Catch: java.lang.Throwable -> L1a
            java.lang.String r1 = r3.getLabelId()     // Catch: java.lang.Throwable -> L1a
            boolean r0 = r0.containsKey(r1)     // Catch: java.lang.Throwable -> L1a
            if (r0 == 0) goto L17
            java.util.Map<java.lang.String, com.kakao.vectormap.internal.ILabel> r0 = r2.labelMap     // Catch: java.lang.Throwable -> L1a
            boolean r3 = r0.containsValue(r3)     // Catch: java.lang.Throwable -> L1a
            if (r3 == 0) goto L17
            r3 = 1
            goto L18
        L17:
            r3 = 0
        L18:
            monitor-exit(r2)
            return r3
        L1a:
            r3 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1a
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kakao.vectormap.label.LodLabelLayer.hasLabel(com.kakao.vectormap.label.LodLabel):boolean");
    }

    public synchronized boolean hasLabel(String str) {
        return this.labelMap.containsKey(str);
    }

    public synchronized void remove(LodLabel... lodLabelArr) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (lodLabelArr == null || lodLabelArr.length == 0) {
            throw new RuntimeException("remove failure. Label is invalid.");
        }
        String[] strArrRemoveLodLabels = this.delegate.removeLodLabels(this.layerId, lodLabelArr);
        removeCallback(strArrRemoveLodLabels);
        for (String str : strArrRemoveLodLabels) {
            this.labelMap.remove(str);
        }
    }

    public synchronized void removeAll() {
        try {
            checkValidate();
            this.delegate.removeAllLabel(this.isLodLayer, this.layerId);
            removeAllCallback();
            this.labelMap.clear();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setVisible(boolean z) {
        try {
            checkValidate();
            this.delegate.setLayerVisible(this.isLodLayer, this.layerId, z);
            this.visible = z;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void showAllLodLabels() {
        try {
            checkValidate();
            this.delegate.setAllVisible(this.layerId, true, false, true);
            setAllLodLabelVisible(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hideAllLodLabels() {
        try {
            checkValidate();
            this.delegate.setAllVisible(this.layerId, true, false, false);
            setAllLodLabelVisible(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setClickable(boolean z) {
        try {
            checkValidate();
            this.delegate.setLayerClickable(this.layerId, this.isLodLayer, z);
            this.clickable = z;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isClickable() {
        return this.clickable;
    }

    public float getRadius() {
        return this.radius;
    }

    public boolean containsLabel(String str) {
        return this.labelMap.containsKey(str);
    }

    public synchronized boolean isVisible() {
        return this.visible;
    }

    public synchronized int getLabelCount() {
        return this.labelMap.size();
    }

    public synchronized LodLabel getLabel(String str) {
        return (LodLabel) this.labelMap.get(str);
    }

    public synchronized LodLabel[] getAllLabels() {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return new LodLabel[0];
        }
        return (LodLabel[]) this.labelMap.values().toArray(new LodLabel[this.labelMap.size()]);
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }

    private void checkValidate() throws RuntimeException {
        if (!this.delegate.hasLodLayer(this.layerId)) {
            throw new RuntimeException("LodLabelLayer(id=" + this.layerId + ") is removed. LodLabelLayer must be added first.");
        }
    }
}
