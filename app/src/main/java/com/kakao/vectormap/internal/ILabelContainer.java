package com.kakao.vectormap.internal;

import android.util.Pair;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.OnLabelCreateCallback;
import com.kakao.vectormap.label.OnLabelsCreateCallback;
import com.kakao.vectormap.label.OnPolylineLabelCreateCallback;
import com.kakao.vectormap.label.PolylineLabel;
import com.kakao.vectormap.label.PolylineLabelOptions;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ILabelContainer {
    protected final ILabelDelegate delegate;
    private ILabelFactory labelFactory;
    protected final String layerId;
    protected final Map<String, ILabel> labelMap = new LinkedHashMap();
    protected final Map<String, ILabel> polylineLabelMap = new LinkedHashMap();
    private Map<String, String> preLabels = new LinkedHashMap();
    private Map<String, Pair<OnLabelCreateCallback, LabelOptions>> labelCallback = new LinkedHashMap();
    private Map<String, Pair<OnLabelsCreateCallback, LabelOptions[]>> labelsCallback = new LinkedHashMap();
    private Map<String, Pair<OnPolylineLabelCreateCallback, PolylineLabelOptions>> lineLabelCallback = new LinkedHashMap();

    public ILabelContainer(ILabelDelegate iLabelDelegate, String str, ILabelFactory iLabelFactory) {
        this.delegate = iLabelDelegate;
        this.layerId = str;
        this.labelFactory = iLabelFactory;
    }

    protected synchronized void removeAllCallback() {
        this.labelCallback.clear();
        this.labelsCallback.clear();
        this.lineLabelCallback.clear();
        this.preLabels.clear();
    }

    protected synchronized void removePolylineCallback(String... strArr) {
        for (String str : strArr) {
            String strRemove = this.preLabels.remove(str);
            if (strRemove != null) {
                this.lineLabelCallback.remove(strRemove);
            }
        }
    }

    protected synchronized void removeCallback(String... strArr) {
        for (String str : strArr) {
            String strRemove = this.preLabels.remove(str);
            if (strRemove != null) {
                this.labelCallback.remove(strRemove);
                this.labelsCallback.remove(strRemove);
            }
        }
    }

    protected synchronized void setAllLabelVisible(boolean z) {
        Iterator<ILabel> it = this.labelMap.values().iterator();
        while (it.hasNext()) {
            it.next().setVisible(z);
        }
    }

    protected synchronized void setAllPolylineLabelVisible(boolean z) {
        Iterator<ILabel> it = this.polylineLabelMap.values().iterator();
        while (it.hasNext()) {
            it.next().setVisible(z);
        }
    }

    synchronized String addCallback(OnLabelCreateCallback onLabelCreateCallback, LabelOptions labelOptions) {
        String uniqueId;
        uniqueId = MapUtils.getUniqueId(this.layerId.hashCode());
        this.labelCallback.put(uniqueId, new Pair<>(onLabelCreateCallback, labelOptions));
        this.preLabels.put(labelOptions.getLabelId(), uniqueId);
        return uniqueId;
    }

    protected synchronized String addCallback(OnLabelsCreateCallback onLabelsCreateCallback, LabelOptions[] labelOptionsArr) {
        String uniqueId;
        uniqueId = MapUtils.getUniqueId(this.layerId.hashCode());
        this.labelsCallback.put(uniqueId, new Pair<>(onLabelsCreateCallback, labelOptionsArr));
        for (LabelOptions labelOptions : labelOptionsArr) {
            this.preLabels.put(labelOptions.getLabelId(), uniqueId);
        }
        return uniqueId;
    }

    synchronized String addCallback(OnPolylineLabelCreateCallback onPolylineLabelCreateCallback, PolylineLabelOptions polylineLabelOptions) {
        String uniqueId;
        uniqueId = MapUtils.getUniqueId(this.layerId.hashCode());
        this.lineLabelCallback.put(uniqueId, new Pair<>(onPolylineLabelCreateCallback, polylineLabelOptions));
        this.preLabels.put(polylineLabelOptions.getLabelId(), uniqueId);
        return uniqueId;
    }

    protected synchronized Label newLabel(LabelOptions labelOptions) {
        Label labelNewLabel;
        labelNewLabel = this.labelFactory.newLabel(this.delegate, this.layerId, labelOptions);
        this.labelMap.put(labelNewLabel.getLabelId(), labelNewLabel);
        return labelNewLabel;
    }

    synchronized Pair<OnLabelCreateCallback, Label> getLabel(String str) {
        if (!this.labelCallback.containsKey(str)) {
            return null;
        }
        Pair<OnLabelCreateCallback, LabelOptions> pairRemove = this.labelCallback.remove(str);
        String labelId = ((LabelOptions) pairRemove.second).getLabelId();
        if (!this.labelMap.containsKey(((LabelOptions) pairRemove.second).getLabelId())) {
            Label labelNewLabel = this.labelFactory.newLabel(this.delegate, this.layerId, (LabelOptions) pairRemove.second);
            this.labelMap.put(labelNewLabel.getLabelId(), labelNewLabel);
        }
        this.preLabels.remove(labelId);
        return new Pair<>((OnLabelCreateCallback) pairRemove.first, (Label) this.labelMap.get(labelId));
    }

    synchronized Pair<OnLabelsCreateCallback, Label[]> getLabels(String str) {
        if (!this.labelsCallback.containsKey(str)) {
            return null;
        }
        Pair<OnLabelsCreateCallback, LabelOptions[]> pairRemove = this.labelsCallback.remove(str);
        ArrayList arrayList = new ArrayList();
        for (LabelOptions labelOptions : (LabelOptions[]) pairRemove.second) {
            if (!this.labelMap.containsKey(labelOptions.getLabelId())) {
                Label labelNewLabel = this.labelFactory.newLabel(this.delegate, this.layerId, labelOptions);
                this.labelMap.put(labelNewLabel.getLabelId(), labelNewLabel);
            }
            this.preLabels.remove(labelOptions.getLabelId());
            arrayList.add((Label) this.labelMap.get(labelOptions.getLabelId()));
        }
        return new Pair<>((OnLabelsCreateCallback) pairRemove.first, (Label[]) arrayList.toArray(new Label[arrayList.size()]));
    }

    synchronized Pair<OnPolylineLabelCreateCallback, PolylineLabel> getPolylineLabel(String str) {
        if (!this.lineLabelCallback.containsKey(str)) {
            return null;
        }
        Pair<OnPolylineLabelCreateCallback, PolylineLabelOptions> pairRemove = this.lineLabelCallback.remove(str);
        String labelId = ((PolylineLabelOptions) pairRemove.second).getLabelId();
        if (!this.polylineLabelMap.containsKey(((PolylineLabelOptions) pairRemove.second).getLabelId())) {
            PolylineLabel polylineLabelNewPolylineLabel = this.labelFactory.newPolylineLabel(this.delegate, this.layerId, (PolylineLabelOptions) pairRemove.second);
            this.polylineLabelMap.put(polylineLabelNewPolylineLabel.getLabelId(), polylineLabelNewPolylineLabel);
        }
        this.preLabels.remove(labelId);
        return new Pair<>((OnPolylineLabelCreateCallback) pairRemove.first, (PolylineLabel) this.polylineLabelMap.get(labelId));
    }

    protected synchronized PolylineLabel newPolylineLabel(PolylineLabelOptions polylineLabelOptions) {
        PolylineLabel polylineLabelNewPolylineLabel;
        polylineLabelNewPolylineLabel = this.labelFactory.newPolylineLabel(this.delegate, this.layerId, polylineLabelOptions);
        this.polylineLabelMap.put(polylineLabelNewPolylineLabel.getLabelId(), polylineLabelNewPolylineLabel);
        return polylineLabelNewPolylineLabel;
    }
}
