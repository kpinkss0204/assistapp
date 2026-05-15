package com.bea.xml.stream;

import java.io.IOException;
import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;

/* JADX INFO: loaded from: classes2.dex */
public class AttributeBase implements Attribute, Location {
    private QName attributeType;
    private String locationURI;
    private QName name;
    private String value;
    private int eventType = -1;
    private int line = -1;
    private int column = -1;
    private int characterOffset = 0;

    @Override // javax.xml.stream.events.XMLEvent
    public int getEventType() {
        return 10;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public Location getLocation() {
        return this;
    }

    @Override // javax.xml.stream.Location
    public String getPublicId() {
        return null;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public QName getSchemaType() {
        return null;
    }

    public String getSourceName() {
        return null;
    }

    @Override // javax.xml.stream.Location
    public String getSystemId() {
        return null;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isAttribute() {
        return true;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isCharacters() {
        return false;
    }

    public boolean isDefault() {
        return true;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isEndDocument() {
        return false;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isEndElement() {
        return false;
    }

    public boolean isEndEntity() {
        return false;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isEntityReference() {
        return false;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isNamespace() {
        return false;
    }

    public boolean isNamespaceDeclaration() {
        return false;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isProcessingInstruction() {
        return false;
    }

    @Override // javax.xml.stream.events.Attribute
    public boolean isSpecified() {
        return true;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isStartDocument() {
        return false;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isStartElement() {
        return false;
    }

    public boolean isStartEntity() {
        return false;
    }

    public void recycle() {
    }

    public AttributeBase(String str, String str2, String str3, String str4, String str5) {
        this.name = new QName(str2, str3, str == null ? "" : str);
        this.value = str4;
        this.attributeType = new QName(str5);
    }

    public AttributeBase(String str, String str2, String str3) {
        this.name = new QName("", str2, str == null ? "" : str);
        this.value = str3;
    }

    public AttributeBase(QName qName, String str) {
        this.name = qName;
        this.value = str;
    }

    public String toString() {
        if (this.name.getPrefix() != null && !this.name.getPrefix().equals("")) {
            return new StringBuffer("['").append(this.name.getNamespaceURI()).append("']:").append(this.name.getPrefix()).append(":").append(this.name.getLocalPart()).append("='").append(this.value).append("'").toString();
        }
        return new StringBuffer().append(this.name.getLocalPart()).append("='").append(this.value).append("'").toString();
    }

    @Override // javax.xml.stream.Location
    public int getLineNumber() {
        return this.line;
    }

    public void setLineNumber(int i) {
        this.line = i;
    }

    @Override // javax.xml.stream.Location
    public int getColumnNumber() {
        return this.column;
    }

    public void setColumnNumber(int i) {
        this.column = i;
    }

    @Override // javax.xml.stream.Location
    public int getCharacterOffset() {
        return this.characterOffset;
    }

    public void setCharacterOffset(int i) {
        this.characterOffset = i;
    }

    public String getLocationURI() {
        return this.locationURI;
    }

    public void setLocationURI(String str) {
        this.locationURI = str;
    }

    public boolean hasName() {
        return this.name != null;
    }

    @Override // javax.xml.stream.events.Attribute
    public QName getName() {
        return this.name;
    }

    public String getLocalName() {
        return this.name.getLocalPart();
    }

    @Override // javax.xml.stream.events.Attribute
    public String getValue() {
        return this.value;
    }

    @Override // javax.xml.stream.events.Attribute
    public String getDTDType() {
        return "CDATA";
    }

    public String getNamespaceURI() {
        return this.name.getNamespaceURI();
    }

    public void setNamespaceURI(String str) {
        this.name = new QName(str, this.name.getLocalPart());
    }

    @Override // javax.xml.stream.events.XMLEvent
    public StartElement asStartElement() {
        throw new ClassCastException("cannnot cast AttributeBase to StartElement");
    }

    @Override // javax.xml.stream.events.XMLEvent
    public EndElement asEndElement() {
        throw new ClassCastException("cannnot cast AttributeBase to EndElement");
    }

    @Override // javax.xml.stream.events.XMLEvent
    public Characters asCharacters() {
        throw new ClassCastException("cannnot cast AttributeBase to Characters");
    }

    @Override // javax.xml.stream.events.XMLEvent
    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        char cCharAt;
        try {
            String prefix = this.name.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                writer.write(prefix);
                writer.write(58);
            }
            writer.write(this.name.getLocalPart());
            writer.write("=\"");
            String str = this.value;
            int length = str.length();
            if (length > 0) {
                int i = 0;
                while (i < length && (cCharAt = str.charAt(i)) != '\"' && cCharAt != '&' && cCharAt != '<' && cCharAt >= ' ') {
                    i++;
                }
                if (i == length) {
                    writer.write(str);
                } else {
                    if (i > 0) {
                        writer.write(str, 0, i);
                    }
                    while (i < length) {
                        char cCharAt2 = str.charAt(i);
                        if (cCharAt2 == '\"') {
                            writer.write("&quot;");
                        } else if (cCharAt2 == '&') {
                            writer.write("&amp;");
                        } else if (cCharAt2 == '<') {
                            writer.write("&lt;");
                        } else if (cCharAt2 < ' ') {
                            writeEncodedChar(writer, cCharAt2);
                        } else {
                            writer.write(cCharAt2);
                        }
                        i++;
                    }
                }
            }
            writer.write(34);
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    public static void writeEncodedChar(Writer writer, char c) throws IOException {
        writer.write("&#");
        writer.write(Integer.toString(c));
        writer.write(59);
    }
}
