package org.simpleframework.xml.transform;

import org.simpleframework.xml.core.PersistenceException;

/* JADX INFO: loaded from: classes3.dex */
public class TransformException extends PersistenceException {
    public TransformException(String str, Object... objArr) {
        super(String.format(str, objArr), new Object[0]);
    }

    public TransformException(Throwable th, String str, Object... objArr) {
        super(String.format(str, objArr), th);
    }
}
