package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes3.dex */
class PullProvider implements Provider {
    private final XmlPullParserFactory factory;

    public PullProvider() throws Exception {
        XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
        this.factory = xmlPullParserFactoryNewInstance;
        xmlPullParserFactoryNewInstance.setNamespaceAware(true);
    }

    @Override // org.simpleframework.xml.stream.Provider
    public EventReader provide(InputStream inputStream) throws Exception {
        XmlPullParser xmlPullParserNewPullParser = this.factory.newPullParser();
        if (inputStream != null) {
            xmlPullParserNewPullParser.setInput(inputStream, null);
        }
        return new PullReader(xmlPullParserNewPullParser);
    }

    @Override // org.simpleframework.xml.stream.Provider
    public EventReader provide(Reader reader) throws Exception {
        XmlPullParser xmlPullParserNewPullParser = this.factory.newPullParser();
        if (reader != null) {
            xmlPullParserNewPullParser.setInput(reader);
        }
        return new PullReader(xmlPullParserNewPullParser);
    }
}
