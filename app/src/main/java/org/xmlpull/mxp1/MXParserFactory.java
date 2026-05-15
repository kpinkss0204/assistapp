package org.xmlpull.mxp1;

import java.util.Enumeration;
import org.xmlpull.mxp1_serializer.MXSerializer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes4.dex */
public class MXParserFactory extends XmlPullParserFactory {
    protected static boolean stringCachedParserAvailable = true;

    @Override // org.xmlpull.v1.XmlPullParserFactory
    public XmlPullParser newPullParser() throws XmlPullParserException {
        MXParser mXParserCachingStrings;
        if (stringCachedParserAvailable) {
            try {
                mXParserCachingStrings = new MXParserCachingStrings();
            } catch (Exception unused) {
                stringCachedParserAvailable = false;
                mXParserCachingStrings = null;
            }
        } else {
            mXParserCachingStrings = null;
        }
        if (mXParserCachingStrings == null) {
            mXParserCachingStrings = new MXParser();
        }
        Enumeration enumerationKeys = this.features.keys();
        while (enumerationKeys.hasMoreElements()) {
            String str = (String) enumerationKeys.nextElement();
            Boolean bool = (Boolean) this.features.get(str);
            if (bool != null && bool.booleanValue()) {
                mXParserCachingStrings.setFeature(str, true);
            }
        }
        return mXParserCachingStrings;
    }

    @Override // org.xmlpull.v1.XmlPullParserFactory
    public XmlSerializer newSerializer() throws XmlPullParserException {
        return new MXSerializer();
    }
}
