package org.simpleframework.xml.convert;

/* JADX INFO: loaded from: classes.dex */
public class ConvertException extends Exception {
    public ConvertException(String str, Object... objArr) {
        super(String.format(str, objArr));
    }
}
