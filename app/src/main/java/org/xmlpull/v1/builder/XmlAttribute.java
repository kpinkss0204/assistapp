package org.xmlpull.v1.builder;

/* JADX INFO: loaded from: classes4.dex */
public interface XmlAttribute {
    String getName();

    XmlNamespace getNamespace();

    String getNamespaceName();

    XmlElement getOwner();

    String getType();

    String getValue();

    boolean isSpecified();
}
