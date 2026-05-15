package org.xmlpull.v1.wrapper.classic;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.util.XmlPullUtil;
import org.xmlpull.v1.wrapper.XmlPullParserWrapper;

/* JADX INFO: loaded from: classes4.dex */
public class StaticXmlPullParserWrapper extends XmlPullParserDelegate implements XmlPullParserWrapper {
    public StaticXmlPullParserWrapper(XmlPullParser xmlPullParser) {
        super(xmlPullParser);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public String getAttributeValue(String str) {
        return XmlPullUtil.getAttributeValue(this.pp, str);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public String getRequiredAttributeValue(String str) throws XmlPullParserException, IOException {
        return XmlPullUtil.getRequiredAttributeValue(this.pp, null, str);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public String getRequiredAttributeValue(String str, String str2) throws XmlPullParserException, IOException {
        return XmlPullUtil.getRequiredAttributeValue(this.pp, str, str2);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public String getRequiredElementText(String str, String str2) throws XmlPullParserException, IOException {
        String strNextText;
        if (str2 == null) {
            throw new XmlPullParserException("name for element can not be null");
        }
        nextStartTag(str, str2);
        if (isNil()) {
            nextEndTag(str, str2);
            strNextText = null;
        } else {
            strNextText = this.pp.nextText();
        }
        this.pp.require(3, str, str2);
        return strNextText;
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public boolean isNil() throws XmlPullParserException, IOException {
        return "true".equals(this.pp.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil"));
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public String getPITarget() throws IllegalStateException {
        return XmlPullUtil.getPITarget(this.pp);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public String getPIData() throws IllegalStateException {
        return XmlPullUtil.getPIData(this.pp);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public boolean matches(int i, String str, String str2) throws XmlPullParserException {
        return XmlPullUtil.matches(this.pp, i, str, str2);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public void nextStartTag() throws XmlPullParserException, IOException {
        if (this.pp.nextTag() != 2) {
            throw new XmlPullParserException(new StringBuffer("expected START_TAG and not ").append(this.pp.getPositionDescription()).toString());
        }
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public void nextStartTag(String str) throws XmlPullParserException, IOException {
        this.pp.nextTag();
        this.pp.require(2, null, str);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public void nextStartTag(String str, String str2) throws XmlPullParserException, IOException {
        this.pp.nextTag();
        this.pp.require(2, str, str2);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public void nextEndTag() throws XmlPullParserException, IOException {
        XmlPullUtil.nextEndTag(this.pp);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public void nextEndTag(String str) throws XmlPullParserException, IOException {
        XmlPullUtil.nextEndTag(this.pp, null, str);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public void nextEndTag(String str, String str2) throws XmlPullParserException, IOException {
        XmlPullUtil.nextEndTag(this.pp, str, str2);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public String nextText(String str, String str2) throws XmlPullParserException, IOException {
        return XmlPullUtil.nextText(this.pp, str, str2);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public void skipSubTree() throws XmlPullParserException, IOException {
        XmlPullUtil.skipSubTree(this.pp);
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public double readDouble() throws XmlPullParserException, IOException {
        String strNextText = this.pp.nextText();
        try {
            return Double.parseDouble(strNextText);
        } catch (NumberFormatException e) {
            if (strNextText.equals("INF") || strNextText.toLowerCase().equals("infinity")) {
                return Double.POSITIVE_INFINITY;
            }
            if (strNextText.equals("-INF") || strNextText.toLowerCase().equals("-infinity")) {
                return Double.NEGATIVE_INFINITY;
            }
            if (strNextText.equals("NaN")) {
                return Double.NaN;
            }
            throw new XmlPullParserException(new StringBuffer("can't parse double value '").append(strNextText).append("'").toString(), this, e);
        }
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public float readFloat() throws XmlPullParserException, IOException {
        String strNextText = this.pp.nextText();
        try {
            return Float.parseFloat(strNextText);
        } catch (NumberFormatException e) {
            if (strNextText.equals("INF") || strNextText.toLowerCase().equals("infinity")) {
                return Float.POSITIVE_INFINITY;
            }
            if (strNextText.equals("-INF") || strNextText.toLowerCase().equals("-infinity")) {
                return Float.NEGATIVE_INFINITY;
            }
            if (strNextText.equals("NaN")) {
                return Float.NaN;
            }
            throw new XmlPullParserException(new StringBuffer("can't parse float value '").append(strNextText).append("'").toString(), this, e);
        }
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public int readInt() throws XmlPullParserException, IOException {
        try {
            return Integer.parseInt(this.pp.nextText());
        } catch (NumberFormatException e) {
            throw new XmlPullParserException("can't parse int value", this, e);
        }
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public String readString() throws XmlPullParserException, IOException {
        return this.pp.nextText();
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public double readDoubleElement(String str, String str2) throws XmlPullParserException, IOException {
        this.pp.require(2, str, str2);
        return readDouble();
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public float readFloatElement(String str, String str2) throws XmlPullParserException, IOException {
        this.pp.require(2, str, str2);
        return readFloat();
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public int readIntElement(String str, String str2) throws XmlPullParserException, IOException {
        this.pp.require(2, str, str2);
        return readInt();
    }

    @Override // org.xmlpull.v1.wrapper.XmlPullParserWrapper
    public String readStringElemet(String str, String str2) throws XmlPullParserException, IOException {
        this.pp.require(2, str, str2);
        return readString();
    }
}
