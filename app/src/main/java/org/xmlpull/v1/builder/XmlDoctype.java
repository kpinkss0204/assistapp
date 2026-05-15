package org.xmlpull.v1.builder;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public interface XmlDoctype extends XmlContainer {
    XmlProcessingInstruction addProcessingInstruction(String str, String str2);

    Iterator children();

    XmlDocument getParent();

    String getPublicIdentifier();

    String getSystemIdentifier();

    void removeAllProcessingInstructions();
}
