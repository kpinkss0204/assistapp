package org.simpleframework.xml.transform;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
class AtomicIntegerTransform implements Transform<AtomicInteger> {
    AtomicIntegerTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public AtomicInteger read(String str) {
        return new AtomicInteger(Integer.valueOf(str).intValue());
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(AtomicInteger atomicInteger) {
        return atomicInteger.toString();
    }
}
