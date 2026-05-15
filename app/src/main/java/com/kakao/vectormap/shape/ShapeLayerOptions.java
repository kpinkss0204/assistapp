package com.kakao.vectormap.shape;

import com.kakao.vectormap.utils.MapUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class ShapeLayerOptions {
    private String layerId;
    private ShapeLayerPass passType;
    private Object tag;
    private boolean visible = true;
    private int zOrder;

    ShapeLayerOptions(String str, int i, ShapeLayerPass shapeLayerPass) {
        this.layerId = MapUtils.getUniqueId(str);
        this.zOrder = i;
        this.passType = shapeLayerPass;
    }

    public static ShapeLayerOptions from() {
        return new ShapeLayerOptions("", 10000, ShapeLayerPass.Default);
    }

    public static ShapeLayerOptions from(String str) {
        return new ShapeLayerOptions(str, 10000, ShapeLayerPass.Default);
    }

    public static ShapeLayerOptions from(String str, int i) {
        return new ShapeLayerOptions(str, i, ShapeLayerPass.Default);
    }

    public static ShapeLayerOptions from(String str, int i, ShapeLayerPass shapeLayerPass) {
        return new ShapeLayerOptions(str, i, shapeLayerPass);
    }

    public static ShapeLayerOptions from(int i, ShapeLayerPass shapeLayerPass) {
        return new ShapeLayerOptions("", i, shapeLayerPass);
    }

    public ShapeLayerOptions setPassType(ShapeLayerPass shapeLayerPass) {
        this.passType = shapeLayerPass;
        return this;
    }

    public ShapeLayerOptions setZOrder(int i) {
        this.zOrder = i;
        return this;
    }

    public ShapeLayerOptions setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    public ShapeLayerOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public ShapeLayerPass getPassType() {
        return this.passType;
    }

    public String getLayerId() {
        return this.layerId;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public Object getTag() {
        return this.tag;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ShapeLayerOptions) {
            return Objects.equals(getLayerId(), ((ShapeLayerOptions) obj).getLayerId());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getLayerId());
    }
}
