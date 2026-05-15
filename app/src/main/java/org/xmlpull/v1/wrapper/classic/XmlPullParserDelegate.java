package org.xmlpull.v1.wrapper.classic;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public class XmlPullParserDelegate implements XmlPullParser {
    protected XmlPullParser pp;

    public XmlPullParserDelegate(XmlPullParser xmlPullParser) {
        this.pp = xmlPullParser;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getText() {
        return this.pp.getText();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setFeature(String str, boolean z) throws XmlPullParserException {
        this.pp.setFeature(str, z);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public char[] getTextCharacters(int[] iArr) {
        return this.pp.getTextCharacters(iArr);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getColumnNumber() {
        return this.pp.getColumnNumber();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getNamespaceCount(int i) throws XmlPullParserException {
        return this.pp.getNamespaceCount(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespacePrefix(int i) throws XmlPullParserException {
        return this.pp.getNamespacePrefix(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeName(int i) {
        return this.pp.getAttributeName(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getName() {
        return this.pp.getName();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean getFeature(String str) {
        return this.pp.getFeature(str);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getInputEncoding() {
        return this.pp.getInputEncoding();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(int i) {
        return this.pp.getAttributeValue(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace(String str) {
        return this.pp.getNamespace(str);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setInput(Reader reader) throws XmlPullParserException {
        this.pp.setInput(reader);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getLineNumber() {
        return this.pp.getLineNumber();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public Object getProperty(String str) {
        return this.pp.getProperty(str);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isEmptyElementTag() throws XmlPullParserException {
        return this.pp.isEmptyElementTag();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isAttributeDefault(int i) {
        return this.pp.isAttributeDefault(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespaceUri(int i) throws XmlPullParserException {
        return this.pp.getNamespaceUri(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int next() throws XmlPullParserException, IOException {
        return this.pp.next();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextToken() throws XmlPullParserException, IOException {
        return this.pp.nextToken();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        this.pp.defineEntityReplacementText(str, str2);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getAttributeCount() {
        return this.pp.getAttributeCount();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isWhitespace() throws XmlPullParserException {
        return this.pp.isWhitespace();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPrefix() {
        return this.pp.getPrefix();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        this.pp.require(i, str, str2);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String nextText() throws XmlPullParserException, IOException {
        return this.pp.nextText();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeType(int i) {
        return this.pp.getAttributeType(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getDepth() {
        return this.pp.getDepth();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextTag() throws XmlPullParserException, IOException {
        return this.pp.nextTag();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getEventType() throws XmlPullParserException {
        return this.pp.getEventType();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributePrefix(int i) {
        return this.pp.getAttributePrefix(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        this.pp.setInput(inputStream, str);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(String str, String str2) {
        return this.pp.getAttributeValue(str, str2);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setProperty(String str, Object obj) throws XmlPullParserException {
        this.pp.setProperty(str, obj);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPositionDescription() {
        return this.pp.getPositionDescription();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace() {
        return this.pp.getNamespace();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeNamespace(int i) {
        return this.pp.getAttributeNamespace(i);
    }
}
