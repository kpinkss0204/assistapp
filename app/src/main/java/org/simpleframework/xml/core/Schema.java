package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;

/* JADX INFO: loaded from: classes3.dex */
interface Schema {
    Caller getCaller();

    Decorator getDecorator();

    Instantiator getInstantiator();

    Version getRevision();

    Section getSection();

    Label getText();

    Label getVersion();

    boolean isPrimitive();
}
