package org.simpleframework.xml.transform;

/* JADX INFO: loaded from: classes3.dex */
class DoubleTransform implements Transform<Double> {
    DoubleTransform() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Double read(String str) {
        return Double.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Double d) {
        return d.toString();
    }
}
