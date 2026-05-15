package org.xmlpull.v1.builder;

/* JADX INFO: loaded from: classes4.dex */
public interface XmlUnparsedEntity extends XmlContainer {
    String getDeclarationBaseUri();

    String getName();

    XmlNotation getNotation();

    String getNotationName();

    String getPublicIdentifier();

    String getSystemIdentifier();
}
