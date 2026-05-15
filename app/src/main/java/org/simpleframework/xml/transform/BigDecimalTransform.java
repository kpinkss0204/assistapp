package org.simpleframework.xml.transform;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes3.dex */
class BigDecimalTransform implements Transform<BigDecimal> {
    BigDecimalTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public BigDecimal read(String str) {
        return new BigDecimal(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }
}
