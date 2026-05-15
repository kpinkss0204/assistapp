package org.simpleframework.xml.transform;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
class BigIntegerTransform implements Transform<BigInteger> {
    BigIntegerTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public BigInteger read(String str) {
        return new BigInteger(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(BigInteger bigInteger) {
        return bigInteger.toString();
    }
}
