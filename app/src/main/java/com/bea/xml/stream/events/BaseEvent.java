package com.bea.xml.stream.events;

import com.bea.xml.stream.util.ElementTypeNames;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseEvent implements XMLEvent, Location {
    private int characterOffset;
    private int column;
    private int eventType;
    private int line;
    private String locationURI;

    protected abstract void doWriteAsEncodedUnicode(Writer writer) throws XMLStreamException, IOException;

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

    public void recycle() {
    }

    public BaseEvent() {
        this.eventType = -1;
        this.line = -1;
        this.column = -1;
        this.characterOffset = 0;
    }

    public BaseEvent(int i) {
        this.line = -1;
        this.column = -1;
        this.characterOffset = 0;
        this.eventType = i;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public int getEventType() {
        return this.eventType;
    }

    protected void setEventType(int i) {
        this.eventType = i;
    }

    public String getTypeAsString() {
        return ElementTypeNames.getEventTypeString(this.eventType);
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isStartElement() {
        return this.eventType == 1;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isEndElement() {
        return this.eventType == 2;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isEntityReference() {
        return this.eventType == 9;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isProcessingInstruction() {
        return this.eventType == 3;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isCharacters() {
        return this.eventType == 4;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isStartDocument() {
        return this.eventType == 7;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isEndDocument() {
        return this.eventType == 8;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isAttribute() {
        return this.eventType == 10;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public boolean isNamespace() {
        return this.eventType == 13;
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.xml.stream.events.XMLEvent
    public StartElement asStartElement() {
        return (StartElement) this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.xml.stream.events.XMLEvent
    public EndElement asEndElement() {
        return (EndElement) this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.xml.stream.events.XMLEvent
    public Characters asCharacters() {
        return (Characters) this;
    }

    @Override // javax.xml.stream.events.XMLEvent
    public final void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            doWriteAsEncodedUnicode(writer);
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter(64);
        try {
            writeAsEncodedUnicode(stringWriter);
        } catch (XMLStreamException e) {
            stringWriter.write("[ERROR: ");
            stringWriter.write(e.toString());
            stringWriter.write("]");
        }
        return stringWriter.toString();
    }
}
