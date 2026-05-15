package org.simpleframework.xml.transform;

/* JADX INFO: loaded from: classes3.dex */
class LongTransform implements Transform<Long> {
    LongTransform() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Long read(String str) {
        return Long.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Long l) {
        return l.toString();
    }
}
