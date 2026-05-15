package org.xmlpull.v1.sax2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes4.dex */
public class Driver implements Locator, XMLReader, Attributes {
    protected static final String APACHE_DYNAMIC_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/dynamic";
    protected static final String APACHE_SCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
    protected static final String DECLARATION_HANDLER_PROPERTY = "http://xml.org/sax/properties/declaration-handler";
    protected static final String LEXICAL_HANDLER_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
    protected static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    protected ContentHandler contentHandler = new DefaultHandler();
    protected ErrorHandler errorHandler = new DefaultHandler();
    protected XmlPullParser pp;
    protected String systemId;

    @Override // org.xml.sax.XMLReader
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override // org.xml.sax.Locator
    public String getPublicId() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public void setDTDHandler(DTDHandler dTDHandler) {
    }

    @Override // org.xml.sax.XMLReader
    public void setEntityResolver(EntityResolver entityResolver) {
    }

    public Driver() throws XmlPullParserException {
        XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
        xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        this.pp = xmlPullParserFactoryNewInstance.newPullParser();
    }

    public Driver(XmlPullParser xmlPullParser) throws XmlPullParserException {
        this.pp = xmlPullParser;
    }

    @Override // org.xml.sax.Attributes
    public int getLength() {
        return this.pp.getAttributeCount();
    }

    @Override // org.xml.sax.Attributes
    public String getURI(int i) {
        return this.pp.getAttributeNamespace(i);
    }

    @Override // org.xml.sax.Attributes
    public String getLocalName(int i) {
        return this.pp.getAttributeName(i);
    }

    @Override // org.xml.sax.Attributes
    public String getQName(int i) {
        String attributePrefix = this.pp.getAttributePrefix(i);
        if (attributePrefix != null) {
            return new StringBuffer().append(attributePrefix).append(':').append(this.pp.getAttributeName(i)).toString();
        }
        return this.pp.getAttributeName(i);
    }

    @Override // org.xml.sax.Attributes
    public String getType(int i) {
        return this.pp.getAttributeType(i);
    }

    @Override // org.xml.sax.Attributes
    public String getValue(int i) {
        return this.pp.getAttributeValue(i);
    }

    @Override // org.xml.sax.Attributes
    public int getIndex(String str, String str2) {
        for (int i = 0; i < this.pp.getAttributeCount(); i++) {
            if (this.pp.getAttributeNamespace(i).equals(str) && this.pp.getAttributeName(i).equals(str2)) {
                return i;
            }
        }
        return -1;
    }

    @Override // org.xml.sax.Attributes
    public int getIndex(String str) {
        for (int i = 0; i < this.pp.getAttributeCount(); i++) {
            if (this.pp.getAttributeName(i).equals(str)) {
                return i;
            }
        }
        return -1;
    }

    @Override // org.xml.sax.Attributes
    public String getType(String str, String str2) {
        for (int i = 0; i < this.pp.getAttributeCount(); i++) {
            if (this.pp.getAttributeNamespace(i).equals(str) && this.pp.getAttributeName(i).equals(str2)) {
                return this.pp.getAttributeType(i);
            }
        }
        return null;
    }

    @Override // org.xml.sax.Attributes
    public String getType(String str) {
        for (int i = 0; i < this.pp.getAttributeCount(); i++) {
            if (this.pp.getAttributeName(i).equals(str)) {
                return this.pp.getAttributeType(i);
            }
        }
        return null;
    }

    @Override // org.xml.sax.Attributes
    public String getValue(String str, String str2) {
        return this.pp.getAttributeValue(str, str2);
    }

    @Override // org.xml.sax.Attributes
    public String getValue(String str) {
        return this.pp.getAttributeValue(null, str);
    }

    @Override // org.xml.sax.Locator
    public String getSystemId() {
        return this.systemId;
    }

    @Override // org.xml.sax.Locator
    public int getLineNumber() {
        return this.pp.getLineNumber();
    }

    @Override // org.xml.sax.Locator
    public int getColumnNumber() {
        return this.pp.getColumnNumber();
    }

    @Override // org.xml.sax.XMLReader
    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (NAMESPACES_FEATURE.equals(str)) {
            return this.pp.getFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces");
        }
        if (NAMESPACE_PREFIXES_FEATURE.equals(str)) {
            return this.pp.getFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES);
        }
        if (VALIDATION_FEATURE.equals(str)) {
            return this.pp.getFeature(XmlPullParser.FEATURE_VALIDATION);
        }
        return this.pp.getFeature(str);
    }

    @Override // org.xml.sax.XMLReader
    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (NAMESPACES_FEATURE.equals(str)) {
                this.pp.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", z);
                return;
            }
            if (NAMESPACE_PREFIXES_FEATURE.equals(str)) {
                if (this.pp.getFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES) != z) {
                    this.pp.setFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES, z);
                }
            } else if (VALIDATION_FEATURE.equals(str)) {
                this.pp.setFeature(XmlPullParser.FEATURE_VALIDATION, z);
            } else {
                this.pp.setFeature(str, z);
            }
        } catch (XmlPullParserException e) {
            throw new SAXNotSupportedException(new StringBuffer("problem with setting feature ").append(str).append(": ").append(e).toString());
        }
    }

    @Override // org.xml.sax.XMLReader
    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (DECLARATION_HANDLER_PROPERTY.equals(str) || LEXICAL_HANDLER_PROPERTY.equals(str)) {
            return null;
        }
        return this.pp.getProperty(str);
    }

    @Override // org.xml.sax.XMLReader
    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (DECLARATION_HANDLER_PROPERTY.equals(str)) {
            throw new SAXNotSupportedException(new StringBuffer("not supported setting property ").append(str).toString());
        }
        if (LEXICAL_HANDLER_PROPERTY.equals(str)) {
            throw new SAXNotSupportedException(new StringBuffer("not supported setting property ").append(str).toString());
        }
        try {
            this.pp.setProperty(str, obj);
        } catch (XmlPullParserException e) {
            throw new SAXNotSupportedException(new StringBuffer("not supported set property ").append(str).append(": ").append(e).toString());
        }
    }

    @Override // org.xml.sax.XMLReader
    public void setContentHandler(ContentHandler contentHandler) {
        this.contentHandler = contentHandler;
    }

    @Override // org.xml.sax.XMLReader
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }

    @Override // org.xml.sax.XMLReader
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override // org.xml.sax.XMLReader
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    @Override // org.xml.sax.XMLReader
    public void parse(InputSource inputSource) throws SAXException, IOException {
        this.systemId = inputSource.getSystemId();
        this.contentHandler.setDocumentLocator(this);
        Reader characterStream = inputSource.getCharacterStream();
        try {
            if (characterStream == null) {
                InputStream byteStream = inputSource.getByteStream();
                String encoding = inputSource.getEncoding();
                if (byteStream == null) {
                    String systemId = inputSource.getSystemId();
                    this.systemId = systemId;
                    if (systemId == null) {
                        this.errorHandler.fatalError(new SAXParseException("null source systemId", this));
                        return;
                    }
                    try {
                        try {
                            byteStream = new URL(this.systemId).openStream();
                        } catch (FileNotFoundException e) {
                            this.errorHandler.fatalError(new SAXParseException(new StringBuffer("could not open file with systemId ").append(this.systemId).toString(), this, e));
                            return;
                        }
                    } catch (MalformedURLException unused) {
                        byteStream = new FileInputStream(this.systemId);
                    }
                }
                this.pp.setInput(byteStream, encoding);
            } else {
                this.pp.setInput(characterStream);
            }
            try {
                this.contentHandler.startDocument();
                this.pp.next();
                if (this.pp.getEventType() != 2) {
                    this.errorHandler.fatalError(new SAXParseException(new StringBuffer("expected start tag not").append(this.pp.getPositionDescription()).toString(), this));
                } else {
                    parseSubTree(this.pp);
                    this.contentHandler.endDocument();
                }
            } catch (XmlPullParserException e2) {
                this.errorHandler.fatalError(new SAXParseException(new StringBuffer("parsing initialization error: ").append(e2).toString(), this, e2));
            }
        } catch (XmlPullParserException e3) {
            this.errorHandler.fatalError(new SAXParseException(new StringBuffer("parsing initialization error: ").append(e3).toString(), this, e3));
        }
    }

    @Override // org.xml.sax.XMLReader
    public void parse(String str) throws SAXException, IOException {
        parse(new InputSource(str));
    }

    public void parseSubTree(XmlPullParser xmlPullParser) throws SAXException, IOException {
        this.pp = xmlPullParser;
        boolean feature = xmlPullParser.getFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces");
        try {
            if (xmlPullParser.getEventType() != 2) {
                throw new SAXException(new StringBuffer("start tag must be read before skiping subtree").append(xmlPullParser.getPositionDescription()).toString());
            }
            int[] iArr = new int[2];
            StringBuffer stringBuffer = new StringBuffer(16);
            int depth = xmlPullParser.getDepth() - 1;
            int next = 2;
            while (next != 1) {
                if (next != 2) {
                    if (next != 3) {
                        if (next == 4) {
                            this.contentHandler.characters(xmlPullParser.getTextCharacters(iArr), iArr[0], iArr[1]);
                        }
                    } else if (feature) {
                        String name = xmlPullParser.getName();
                        String prefix = xmlPullParser.getPrefix();
                        if (prefix != null) {
                            stringBuffer.setLength(0);
                            stringBuffer.append(prefix);
                            stringBuffer.append(':');
                            stringBuffer.append(name);
                        }
                        this.contentHandler.endElement(xmlPullParser.getNamespace(), name, prefix != null ? name : stringBuffer.toString());
                        int namespaceCount = depth > xmlPullParser.getDepth() ? xmlPullParser.getNamespaceCount(xmlPullParser.getDepth()) : 0;
                        for (int namespaceCount2 = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth() - 1) - 1; namespaceCount2 >= namespaceCount; namespaceCount2--) {
                            this.contentHandler.endPrefixMapping(xmlPullParser.getNamespacePrefix(namespaceCount2));
                        }
                    } else {
                        this.contentHandler.endElement(xmlPullParser.getNamespace(), xmlPullParser.getName(), xmlPullParser.getName());
                    }
                } else if (feature) {
                    int depth2 = xmlPullParser.getDepth();
                    int i = depth2 - 1;
                    int namespaceCount3 = xmlPullParser.getNamespaceCount(depth2);
                    for (int namespaceCount4 = depth > i ? xmlPullParser.getNamespaceCount(i) : 0; namespaceCount4 < namespaceCount3; namespaceCount4++) {
                        this.contentHandler.startPrefixMapping(xmlPullParser.getNamespacePrefix(namespaceCount4), xmlPullParser.getNamespaceUri(namespaceCount4));
                    }
                    String name2 = xmlPullParser.getName();
                    String prefix2 = xmlPullParser.getPrefix();
                    if (prefix2 != null) {
                        stringBuffer.setLength(0);
                        stringBuffer.append(prefix2);
                        stringBuffer.append(':');
                        stringBuffer.append(name2);
                    }
                    startElement(xmlPullParser.getNamespace(), name2, prefix2 != null ? name2 : stringBuffer.toString());
                } else {
                    startElement(xmlPullParser.getNamespace(), xmlPullParser.getName(), xmlPullParser.getName());
                }
                next = xmlPullParser.next();
                if (xmlPullParser.getDepth() <= depth) {
                    return;
                }
            }
        } catch (XmlPullParserException e) {
            SAXParseException sAXParseException = new SAXParseException(new StringBuffer("parsing error: ").append(e).toString(), this, e);
            e.printStackTrace();
            this.errorHandler.fatalError(sAXParseException);
        }
    }

    protected void startElement(String str, String str2, String str3) throws SAXException {
        this.contentHandler.startElement(str, str2, str3, this);
    }
}
