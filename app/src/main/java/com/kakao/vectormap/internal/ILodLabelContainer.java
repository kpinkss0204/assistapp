package com.kakao.vectormap.internal;

import android.util.Pair;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LodLabel;
import com.kakao.vectormap.label.OnLodLabelCreateCallback;
import com.kakao.vectormap.label.OnLodLabelsCreateCallback;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ILodLabelContainer {
    protected final ILabelDelegate delegate;
    private final ILabelFactory labelFactory;
    protected final String layerId;
    protected final Map<String, ILabel> labelMap = new LinkedHashMap();
    private Map<String, String> preLabels = new LinkedHashMap();
    private Map<String, Pair<OnLodLabelCreateCallback, LabelOptions>> labelCallback = new LinkedHashMap();
    private Map<String, Pair<OnLodLabelsCreateCallback, LabelOptions[]>> labelsCallback = new LinkedHashMap();

    public ILodLabelContainer(ILabelDelegate iLabelDelegate, String str, ILabelFactory iLabelFactory) {
        this.delegate = iLabelDelegate;
        this.layerId = str;
        this.labelFactory = iLabelFactory;
    }

    protected synchronized void removeAllCallback() {
        this.labelCallback.clear();
        this.labelsCallback.clear();
        this.preLabels.clear();
    }

    protected synchronized void removeCallback(String str) {
        String str2 = this.preLabels.get(str);
        if (str2 != null) {
            this.labelCallback.remove(str2);
            this.labelsCallback.remove(str2);
        }
    }

    protected synchronized void removeCallback(String[] strArr) {
        for (String str : strArr) {
            String strRemove = this.preLabels.remove(str);
            if (strRemove != null) {
                this.labelCallback.remove(strRemove);
                this.labelsCallback.remove(strRemove);
            }
        }
    }

    protected synchronized void setAllLodLabelVisible(boolean z) {
        for (ILabel iLabel : this.labelMap.values()) {
            if (iLabel != null) {
                iLabel.setVisible(z);
            }
        }
    }

    synchronized String addCallback(OnLodLabelCreateCallback onLodLabelCreateCallback, LabelOptions labelOptions) {
        String uniqueId;
        uniqueId = MapUtils.getUniqueId(this.layerId.hashCode());
        this.labelCallback.put(uniqueId, new Pair<>(onLodLabelCreateCallback, labelOptions));
        this.preLabels.put(labelOptions.getLabelId(), uniqueId);
        return uniqueId;
    }

    protected synchronized String addCallback(OnLodLabelsCreateCallback onLodLabelsCreateCallback, LabelOptions[] labelOptionsArr) {
        String uniqueId;
        uniqueId = MapUtils.getUniqueId(this.layerId.hashCode());
        this.labelsCallback.put(uniqueId, new Pair<>(onLodLabelsCreateCallback, labelOptionsArr));
        for (LabelOptions labelOptions : labelOptionsArr) {
            this.preLabels.put(labelOptions.getLabelId(), uniqueId);
        }
        return uniqueId;
    }

    protected synchronized LodLabel newLodLabel(LabelOptions labelOptions) {
        LodLabel lodLabelNewLodLabel;
        lodLabelNewLodLabel = this.labelFactory.newLodLabel(this.delegate, this.layerId, labelOptions);
        this.labelMap.put(lodLabelNewLodLabel.getLabelId(), lodLabelNewLodLabel);
        return lodLabelNewLodLabel;
    }

    protected synchronized LodLabel[] newLodLabels(Collection<LabelOptions> collection) {
        LodLabel[] lodLabelArr;
        lodLabelArr = new LodLabel[collection.size()];
        Iterator<LabelOptions> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            LodLabel lodLabelNewLodLabel = this.labelFactory.newLodLabel(this.delegate, this.layerId, it.next());
            this.labelMap.put(lodLabelNewLodLabel.getLabelId(), lodLabelNewLodLabel);
            lodLabelArr[i] = lodLabelNewLodLabel;
            i++;
        }
        return lodLabelArr;
    }

    synchronized Pair<OnLodLabelCreateCallback, LodLabel> getLabel(String str) {
        if (!this.labelCallback.containsKey(str)) {
            return null;
        }
        Pair<OnLodLabelCreateCallback, LabelOptions> pairRemove = this.labelCallback.remove(str);
        String labelId = ((LabelOptions) pairRemove.second).getLabelId();
        if (!this.labelMap.containsKey(((LabelOptions) pairRemove.second).getLabelId())) {
            LodLabel lodLabelNewLodLabel = this.labelFactory.newLodLabel(this.delegate, this.layerId, (LabelOptions) pairRemove.second);
            this.labelMap.put(lodLabelNewLodLabel.getLabelId(), lodLabelNewLodLabel);
        }
        this.preLabels.remove(labelId);
        return new Pair<>((OnLodLabelCreateCallback) pairRemove.first, (LodLabel) this.labelMap.get(labelId));
    }

    synchronized Pair<OnLodLabelsCreateCallback, LodLabel[]> getLabels(String str) {
        if (!this.labelsCallback.containsKey(str)) {
            return null;
        }
        Pair<OnLodLabelsCreateCallback, LabelOptions[]> pairRemove = this.labelsCallback.remove(str);
        ArrayList arrayList = new ArrayList();
        for (LabelOptions labelOptions : (LabelOptions[]) pairRemove.second) {
            if (!this.labelMap.containsKey(labelOptions.getLabelId())) {
                LodLabel lodLabelNewLodLabel = this.labelFactory.newLodLabel(this.delegate, this.layerId, labelOptions);
                this.labelMap.put(lodLabelNewLodLabel.getLabelId(), lodLabelNewLodLabel);
            }
            this.preLabels.remove(labelOptions.getLabelId());
            arrayList.add(this.labelMap.get(labelOptions.getLabelId()));
        }
        return new Pair<>((OnLodLabelsCreateCallback) pairRemove.first, (LodLabel[]) arrayList.toArray(new LodLabel[arrayList.size()]));
    }
}
