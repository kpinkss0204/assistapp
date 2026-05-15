package org.simpleframework.xml.transform;

/* JADX INFO: loaded from: classes3.dex */
class ShortTransform implements Transform<Short> {
    ShortTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public Short read(String str) {
        return Short.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Short sh) {
        return sh.toString();
    }
}
