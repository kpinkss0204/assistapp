package com.kakao.vectormap.label;

import com.kakao.vectormap.utils.MapUtils;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class LabelLayerOptions {
    public final String layerId;
    public Object tag;
    public CompetitionType competitionType = CompetitionType.None;
    public CompetitionUnit competitionUnit = CompetitionUnit.IconAndText;
    public OrderingType orderingType = OrderingType.Rank;
    public Set<String> competitionExceptions = new HashSet();
    public int zOrder = 10001;
    public boolean visible = true;
    public float lodRadius = 20.0f;
    public boolean clickable = true;

    LabelLayerOptions(String str) {
        this.layerId = MapUtils.getUniqueId(str);
    }

    public static LabelLayerOptions from() {
        return new LabelLayerOptions("");
    }

    public static LabelLayerOptions from(String str) {
        return new LabelLayerOptions(str);
    }

    public String getLayerId() {
        return this.layerId;
    }

    public LabelLayerOptions setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
        return this;
    }

    public CompetitionType getCompetitionType() {
        return this.competitionType;
    }

    public LabelLayerOptions setCompetitionUnit(CompetitionUnit competitionUnit) {
        this.competitionUnit = competitionUnit;
        return this;
    }

    public CompetitionUnit getCompetitionUnit() {
        return this.competitionUnit;
    }

    public LabelLayerOptions setOrderingType(OrderingType orderingType) {
        this.orderingType = orderingType;
        return this;
    }

    public OrderingType getOrderingType() {
        return this.orderingType;
    }

    public LabelLayerOptions setZOrder(int i) {
        this.zOrder = i;
        return this;
    }

    public LabelLayerOptions setLodRadius(float f) {
        this.lodRadius = f;
        return this;
    }

    public float getLodRadius() {
        return this.lodRadius;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public LabelLayerOptions setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public LabelLayerOptions setClickable(boolean z) {
        this.clickable = z;
        return this;
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public LabelLayerOptions addCompetitionExceptions(String... strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                this.competitionExceptions.add(str);
            }
        }
        return this;
    }

    public String[] getCompetitionExceptions() {
        Set<String> set = this.competitionExceptions;
        return (String[]) set.toArray(new String[set.size()]);
    }

    public LabelLayerOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public Object getTag() {
        return this.tag;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LabelLayerOptions) {
            return Objects.equals(getLayerId(), ((LabelLayerOptions) obj).getLayerId());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getLayerId());
    }
}
