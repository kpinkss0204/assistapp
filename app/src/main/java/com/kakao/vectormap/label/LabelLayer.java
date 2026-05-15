package com.kakao.vectormap.label;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ILabelContainer;
import com.kakao.vectormap.internal.ILabelDelegate;
import com.kakao.vectormap.internal.ILabelFactory;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LabelLayer extends ILabelContainer {
    protected boolean clickable;
    protected final CompetitionType competitionType;
    protected final CompetitionUnit competitionUnit;
    protected final boolean isLodLayer;
    protected final OrderingType orderingType;
    protected Object tag;
    protected boolean visible;
    protected int zOrder;

    LabelLayer(ILabelDelegate iLabelDelegate, String str, int i, CompetitionType competitionType, CompetitionUnit competitionUnit, OrderingType orderingType, boolean z, boolean z2, boolean z3, Object obj, ILabelFactory iLabelFactory) {
        super(iLabelDelegate, str, iLabelFactory);
        this.zOrder = i;
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
            throw new RuntimeException("addLabel failure. LabelOptions is invalid.");
        }
    }

    private void checkLabelStyles(LabelStyles labelStyles) throws RuntimeException {
        if (labelStyles == null) {
            throw new RuntimeException("LabelStyles is null.");
        }
    }

    private void checkPolylineLabelOptions(PolylineLabelOptions polylineLabelOptions) throws RuntimeException {
        checkValidate();
        if (polylineLabelOptions == null) {
            throw new RuntimeException("addPolylineLabel failure. PolylineLabelOptions is null.");
        }
        if (polylineLabelOptions.getPoints() == null || polylineLabelOptions.getPoints().length < 2) {
            throw new RuntimeException("addPolylineLabel failure. Point is invalid.");
        }
    }

    private void checkPolylineLabelStyles(PolylineLabelStyles polylineLabelStyles) throws RuntimeException {
        if (polylineLabelStyles == null) {
            throw new RuntimeException("LabelStyles is null.");
        }
        polylineLabelStyles.checkStyles(this.delegate.getResourceManager());
    }

    public synchronized Label addLabel(LabelOptions labelOptions) {
        try {
            checkLabelOptions(labelOptions);
            checkLabelStyles(labelOptions.getStyles());
            if (this.labelMap.containsKey(labelOptions.getLabelId())) {
                return (Label) this.labelMap.get(labelOptions.getLabelId());
            }
            this.delegate.addLabel(this, labelOptions, null);
            return newLabel(labelOptions);
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized void addLabel(LabelOptions labelOptions, OnLabelCreateCallback onLabelCreateCallback) {
        try {
            checkLabelOptions(labelOptions);
            checkLabelStyles(labelOptions.getStyles());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (this.labelMap.containsKey(labelOptions.getLabelId())) {
            if (onLabelCreateCallback != null) {
                onLabelCreateCallback.onLabelCreated(this, (Label) this.labelMap.get(labelOptions.getLabelId()));
            }
        } else {
            this.delegate.addLabel(this, labelOptions, onLabelCreateCallback);
            newLabel(labelOptions);
        }
    }

    private Label[] addPointLabels(LabelOptions[] labelOptionsArr, OnLabelsCreateCallback onLabelsCreateCallback) throws RuntimeException {
        checkLabelOptions(labelOptionsArr);
        int length = labelOptionsArr.length;
        Label[] labelArr = new Label[length];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < length; i++) {
            LabelOptions labelOptions = labelOptionsArr[i];
            checkLabelStyles(labelOptions.getStyles());
            if (this.labelMap.containsKey(labelOptions.getLabelId())) {
                labelArr[i] = (Label) this.labelMap.get(labelOptions.getLabelId());
            } else {
                arrayList2.add(labelOptions);
                arrayList.add(labelOptions.getStyles());
                labelArr[i] = newLabel(labelOptions);
            }
        }
        this.delegate.addLabels(getLayerId(), isLodLayer(), arrayList2, arrayList, onLabelsCreateCallback == null ? "" : addCallback(onLabelsCreateCallback, labelOptionsArr));
        return labelArr;
    }

    public synchronized Label[] addLabels(LabelOptions[] labelOptionsArr) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return addPointLabels(labelOptionsArr, null);
    }

    public synchronized void addLabels(LabelOptions[] labelOptionsArr, OnLabelsCreateCallback onLabelsCreateCallback) {
        try {
            addPointLabels(labelOptionsArr, onLabelsCreateCallback);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized Label[] addLabels(List<LabelOptions> list) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return addPointLabels((LabelOptions[]) list.toArray(new LabelOptions[list.size()]), null);
    }

    public synchronized void addLabels(List<LabelOptions> list, OnLabelsCreateCallback onLabelsCreateCallback) {
        try {
            addPointLabels((LabelOptions[]) list.toArray(new LabelOptions[list.size()]), onLabelsCreateCallback);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized PolylineLabel addPolylineLabel(PolylineLabelOptions polylineLabelOptions) {
        try {
            checkPolylineLabelOptions(polylineLabelOptions);
            checkPolylineLabelStyles(polylineLabelOptions.getStyles());
            if (this.polylineLabelMap.containsKey(polylineLabelOptions.getLabelId())) {
                return (PolylineLabel) this.polylineLabelMap.get(polylineLabelOptions.getLabelId());
            }
            this.delegate.addPolylineLabel(this, polylineLabelOptions, null);
            return newPolylineLabel(polylineLabelOptions);
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized void addPolylineLabel(PolylineLabelOptions polylineLabelOptions, OnPolylineLabelCreateCallback onPolylineLabelCreateCallback) {
        try {
            checkPolylineLabelOptions(polylineLabelOptions);
            checkPolylineLabelStyles(polylineLabelOptions.getStyles());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (this.polylineLabelMap.containsKey(polylineLabelOptions.getLabelId())) {
            if (onPolylineLabelCreateCallback != null) {
                onPolylineLabelCreateCallback.onPolylineLabelCreated(this, (PolylineLabel) this.polylineLabelMap.get(polylineLabelOptions.getLabelId()));
            }
        } else {
            this.delegate.addPolylineLabel(this, polylineLabelOptions, onPolylineLabelCreateCallback);
            newPolylineLabel(polylineLabelOptions);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean hasLabel(com.kakao.vectormap.label.Label r3) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.kakao.vectormap.label.LabelLayer.hasLabel(com.kakao.vectormap.label.Label):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean hasPolylineLabel(com.kakao.vectormap.label.PolylineLabel r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.Map<java.lang.String, com.kakao.vectormap.internal.ILabel> r0 = r2.polylineLabelMap     // Catch: java.lang.Throwable -> L1a
            java.lang.String r1 = r3.getLabelId()     // Catch: java.lang.Throwable -> L1a
            boolean r0 = r0.containsKey(r1)     // Catch: java.lang.Throwable -> L1a
            if (r0 == 0) goto L17
            java.util.Map<java.lang.String, com.kakao.vectormap.internal.ILabel> r0 = r2.polylineLabelMap     // Catch: java.lang.Throwable -> L1a
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
        throw new UnsupportedOperationException("Method not decompiled: com.kakao.vectormap.label.LabelLayer.hasPolylineLabel(com.kakao.vectormap.label.PolylineLabel):boolean");
    }

    public synchronized boolean hasLabel(String str) {
        return this.labelMap.containsKey(str);
    }

    public synchronized boolean hasPolylineLabel(String str) {
        return this.polylineLabelMap.containsKey(str);
    }

    public synchronized Label getLabel(String str) {
        return (Label) this.labelMap.get(str);
    }

    public synchronized Label[] getAllLabels() {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return new Label[0];
        }
        return (Label[]) this.labelMap.values().toArray(new Label[this.labelMap.size()]);
    }

    public synchronized PolylineLabel getPolylineLabel(String str) {
        return (PolylineLabel) this.polylineLabelMap.get(str);
    }

    public synchronized PolylineLabel[] getAllPolylineLabels() {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return new PolylineLabel[0];
        }
        return (PolylineLabel[]) this.polylineLabelMap.values().toArray(new PolylineLabel[this.polylineLabelMap.size()]);
    }

    public synchronized void remove(Label... labelArr) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (labelArr == null || labelArr.length == 0) {
            throw new RuntimeException("remove failure. Label is invalid.");
        }
        String[] strArrRemoveLabels = this.delegate.removeLabels(this.layerId, labelArr, false);
        removeCallback(strArrRemoveLabels);
        for (String str : strArrRemoveLabels) {
            this.labelMap.remove(str);
        }
    }

    public synchronized void remove(PolylineLabel... polylineLabelArr) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (polylineLabelArr == null || polylineLabelArr.length == 0) {
            throw new RuntimeException("remove failure. Label is invalid.");
        }
        String[] strArrRemoveLabels = this.delegate.removeLabels(this.layerId, polylineLabelArr, true);
        removePolylineCallback(strArrRemoveLabels);
        for (String str : strArrRemoveLabels) {
            this.polylineLabelMap.remove(str);
        }
    }

    public synchronized void removeAll() {
        try {
            checkValidate();
            this.delegate.removeAllLabel(this.isLodLayer, this.layerId);
            removeAllCallback();
            this.labelMap.clear();
            this.polylineLabelMap.clear();
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

    public synchronized void showAllLabels() {
        try {
            checkValidate();
            this.delegate.setAllVisible(this.layerId, false, false, true);
            setAllLabelVisible(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hideAllLabels() {
        try {
            checkValidate();
            this.delegate.setAllVisible(this.layerId, false, false, false);
            setAllLabelVisible(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void showAllPolylineLabels() {
        try {
            checkValidate();
            this.delegate.setAllVisible(this.layerId, false, true, true);
            setAllPolylineLabelVisible(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hideAllPolylineLabels() {
        try {
            checkValidate();
            this.delegate.setAllVisible(this.layerId, false, true, false);
            setAllPolylineLabelVisible(false);
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

    public synchronized boolean isVisible() {
        return this.visible;
    }

    public synchronized int getLabelCount() {
        return this.labelMap.size();
    }

    public synchronized int getPolylineLabelCount() {
        return this.polylineLabelMap.size();
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }

    private synchronized void checkValidate() throws RuntimeException {
        if (!this.delegate.hasLayer(this.layerId)) {
            throw new RuntimeException("LabelLayer(id=" + this.layerId + ") is removed. LabelLayer must be added first.");
        }
    }
}
