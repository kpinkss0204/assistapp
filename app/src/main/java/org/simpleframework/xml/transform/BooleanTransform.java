package org.simpleframework.xml.transform;

/* JADX INFO: loaded from: classes3.dex */
class BooleanTransform implements Transform<Boolean> {
    BooleanTransform() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Boolean read(String str) {
        return Boolean.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Boolean bool) {
        return bool.toString();
    }
}
