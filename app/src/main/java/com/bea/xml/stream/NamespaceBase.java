package com.bea.xml.stream;

import javax.xml.XMLConstants;
import javax.xml.stream.events.Namespace;

/* JADX INFO: loaded from: classes2.dex */
public class NamespaceBase extends AttributeBase implements Namespace {
    boolean declaresDefaultNamespace;

    @Override // com.bea.xml.stream.AttributeBase, javax.xml.stream.events.XMLEvent
    public int getEventType() {
        return 13;
    }

    @Override // com.bea.xml.stream.AttributeBase, javax.xml.stream.events.XMLEvent
    public boolean isAttribute() {
        return false;
    }

    @Override // com.bea.xml.stream.AttributeBase, javax.xml.stream.events.XMLEvent
    public boolean isNamespace() {
        return true;
    }

    public NamespaceBase(String str, String str2) {
        super(XMLConstants.XMLNS_ATTRIBUTE, str, str2);
        this.declaresDefaultNamespace = false;
    }

    public NamespaceBase(String str) {
        super(XMLConstants.XMLNS_ATTRIBUTE, "", str);
        this.declaresDefaultNamespace = true;
    }

    @Override // javax.xml.stream.events.Namespace
    public String getPrefix() {
        return this.declaresDefaultNamespace ? "" : super.getLocalName();
    }

    @Override // com.bea.xml.stream.AttributeBase, javax.xml.stream.events.Namespace
    public String getNamespaceURI() {
        return super.getValue();
    }

    @Override // javax.xml.stream.events.Namespace
    public boolean isDefaultNamespaceDeclaration() {
        return this.declaresDefaultNamespace;
    }

    @Override // com.bea.xml.stream.AttributeBase
    public String toString() {
        if (this.declaresDefaultNamespace) {
            return new StringBuffer("xmlns='").append(getNamespaceURI()).append("'").toString();
        }
        return new StringBuffer("xmlns:").append(getPrefix()).append("='").append(getNamespaceURI()).append("'").toString();
    }
}
