package org.simpleframework.xml.transform;

/* JADX INFO: loaded from: classes3.dex */
class StringTransform implements Transform<String> {
    @Override // org.simpleframework.xml.transform.Transform
    public String read(String str) {
        return str;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(String str) {
        return str;
    }

    StringTransform() {
    }
}
