package org.xmlpull.v1.builder;

/* JADX INFO: loaded from: classes4.dex */
public interface XmlUnexpandedEntityReference extends XmlContainer {
    String getDeclarationBaseUri();

    String getName();

    XmlElement getParent();

    String getPublicIdentifier();

    String getSystemIdentifier();
}
