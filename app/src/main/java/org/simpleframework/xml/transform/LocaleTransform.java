package org.simpleframework.xml.transform;

import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
class LocaleTransform implements Transform<Locale> {
    private final Pattern pattern = Pattern.compile("_");

    @Override // org.simpleframework.xml.transform.Transform
    public Locale read(String str) throws Exception {
        String[] strArrSplit = this.pattern.split(str);
        if (strArrSplit.length < 1) {
            throw new InvalidFormatException("Invalid locale %s", str);
        }
        return read(strArrSplit);
    }

    private Locale read(String[] strArr) throws Exception {
        String[] strArr2 = new String[3];
        strArr2[0] = "";
        strArr2[1] = "";
        strArr2[2] = "";
        for (int i = 0; i < 3; i++) {
            if (i < strArr.length) {
                strArr2[i] = strArr[i];
            }
        }
        return new Locale(strArr2[0], strArr2[1], strArr2[2]);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Locale locale) {
        return locale.toString();
    }
}
