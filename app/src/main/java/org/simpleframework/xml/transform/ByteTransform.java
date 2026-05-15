package org.simpleframework.xml.transform;

/* JADX INFO: loaded from: classes3.dex */
class ByteTransform implements Transform<Byte> {
    ByteTransform() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Byte read(String str) {
        return Byte.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Byte b) {
        return b.toString();
    }
}
