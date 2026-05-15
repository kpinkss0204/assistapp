package org.simpleframework.xml.strategy;

import org.simpleframework.xml.core.PersistenceException;

/* JADX INFO: loaded from: classes3.dex */
public class CycleException extends PersistenceException {
    public CycleException(String str, Object... objArr) {
        super(str, objArr);
    }

    public CycleException(Throwable th, String str, Object... objArr) {
        super(th, str, objArr);
    }
}
