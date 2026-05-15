package org.xmlpull.v1.wrapper.classic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes4.dex */
public class XmlSerializerDelegate implements XmlSerializer {
    protected XmlSerializer xs;

    public XmlSerializerDelegate(XmlSerializer xmlSerializer) {
        this.xs = xmlSerializer;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getName() {
        return this.xs.getName();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setPrefix(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.setPrefix(str, str2);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(OutputStream outputStream, String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.setOutput(outputStream, str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void endDocument() throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.endDocument();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void comment(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.comment(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public int getDepth() {
        return this.xs.getDepth();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setProperty(String str, Object obj) throws IllegalStateException, IllegalArgumentException {
        this.xs.setProperty(str, obj);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void cdsect(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.cdsect(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setFeature(String str, boolean z) throws IllegalStateException, IllegalArgumentException {
        this.xs.setFeature(str, z);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void entityRef(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.entityRef(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void processingInstruction(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.processingInstruction(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(Writer writer) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.setOutput(writer);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void docdecl(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.docdecl(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void flush() throws IOException {
        this.xs.flush();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public Object getProperty(String str) {
        return this.xs.getProperty(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer startTag(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.xs.startTag(str, str2);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void ignorableWhitespace(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.ignorableWhitespace(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.xs.text(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public boolean getFeature(String str) {
        return this.xs.getFeature(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer attribute(String str, String str2, String str3) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.xs.attribute(str, str2, str3);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void startDocument(String str, Boolean bool) throws IllegalStateException, IOException, IllegalArgumentException {
        this.xs.startDocument(str, bool);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getPrefix(String str, boolean z) throws IllegalArgumentException {
        return this.xs.getPrefix(str, z);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getNamespace() {
        return this.xs.getNamespace();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer endTag(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.xs.endTag(str, str2);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(char[] cArr, int i, int i2) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.xs.text(cArr, i, i2);
    }
}
