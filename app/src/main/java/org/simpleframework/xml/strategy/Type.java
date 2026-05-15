package org.simpleframework.xml.strategy;

import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes3.dex */
public interface Type {
    <T extends Annotation> T getAnnotation(Class<T> cls);

    Class getType();

    String toString();
}
