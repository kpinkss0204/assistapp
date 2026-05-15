package org.simpleframework.xml.core;

/* JADX INFO: loaded from: classes3.dex */
public class PersistenceException extends Exception {
    public PersistenceException(String str, Object... objArr) {
        super(String.format(str, objArr));
    }

    public PersistenceException(Throwable th, String str, Object... objArr) {
        super(String.format(str, objArr), th);
    }
}
