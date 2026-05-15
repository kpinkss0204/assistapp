package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
interface MethodPart {
    Annotation getAnnotation();

    <T extends Annotation> T getAnnotation(Class<T> cls);

    Class getDeclaringClass();

    Class getDependent();

    Class[] getDependents();

    Method getMethod();

    MethodType getMethodType();

    String getName();

    Class getType();

    String toString();
}
