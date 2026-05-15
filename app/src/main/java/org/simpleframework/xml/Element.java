package org.simpleframework.xml;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.RUNTIME)
public @interface Element {
    boolean data() default false;

    String name() default "";

    boolean required() default true;

    Class type() default void.class;
}
