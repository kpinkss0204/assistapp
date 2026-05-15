package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.stream.Format;

/* JADX INFO: loaded from: classes3.dex */
class ElementArrayParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;

    public ElementArrayParameter(Constructor constructor, ElementArray elementArray, Format format, int i) throws Exception {
        Contact contact = new Contact(elementArray, constructor, i);
        this.contact = contact;
        ElementArrayLabel elementArrayLabel = new ElementArrayLabel(contact, elementArray, format);
        this.label = elementArrayLabel;
        this.expression = elementArrayLabel.getExpression();
        this.path = elementArrayLabel.getPath();
        this.type = elementArrayLabel.getType();
        this.name = elementArrayLabel.getName();
        this.key = elementArrayLabel.getKey();
        this.index = i;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Object getKey() {
        return this.key;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public String getPath() {
        return this.path;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public String getName() {
        return this.name;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Expression getExpression() {
        return this.expression;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Class getType() {
        return this.type;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Annotation getAnnotation() {
        return this.contact.getAnnotation();
    }

    @Override // org.simpleframework.xml.core.Parameter
    public int getIndex() {
        return this.index;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isRequired() {
        return this.label.isRequired();
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isPrimitive() {
        return this.type.isPrimitive();
    }

    @Override // org.simpleframework.xml.core.Parameter
    public String toString() {
        return this.contact.toString();
    }

    private static class Contact extends ParameterContact<ElementArray> {
        public Contact(ElementArray elementArray, Constructor constructor, int i) {
            super(elementArray, constructor, i);
        }

        @Override // org.simpleframework.xml.core.ParameterContact, org.simpleframework.xml.core.Contact
        public String getName() {
            return ((ElementArray) this.label).name();
        }
    }
}
