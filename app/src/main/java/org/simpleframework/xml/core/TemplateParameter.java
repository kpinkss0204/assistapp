package org.simpleframework.xml.core;

/* JADX INFO: loaded from: classes3.dex */
abstract class TemplateParameter implements Parameter {
    @Override // org.simpleframework.xml.core.Parameter
    public boolean isAttribute() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isText() {
        return false;
    }

    protected TemplateParameter() {
    }
}
