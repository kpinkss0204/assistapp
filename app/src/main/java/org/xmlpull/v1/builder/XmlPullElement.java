package org.xmlpull.v1.builder;

import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public interface XmlPullElement extends XmlElement {
    @Override // org.xmlpull.v1.builder.XmlElement
    Iterator children();

    boolean fullyConstructed();

    XmlPullParser nextChildAsPullParser() throws XmlPullParserException, IOException;

    XmlPullElement readNextChild() throws XmlPullParserException, IOException;

    boolean skipNextChild() throws XmlPullParserException, IOException;
}
