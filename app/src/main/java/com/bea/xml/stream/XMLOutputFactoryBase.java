package com.bea.xml.stream;

import com.google.logging.type.LogSeverity;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;

/* JADX INFO: loaded from: classes2.dex */
public class XMLOutputFactoryBase extends XMLOutputFactory {
    ConfigurationContextBase config = new ConfigurationContextBase();

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(Writer writer) throws XMLStreamException {
        XMLWriterBase xMLWriterBase = new XMLWriterBase(writer);
        xMLWriterBase.setConfigurationContext(this.config);
        return xMLWriterBase;
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream) throws XMLStreamException {
        return createXMLStreamWriter(new BufferedWriter(new OutputStreamWriter(outputStream), LogSeverity.ERROR_VALUE));
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream, String str) throws XMLStreamException {
        try {
            return createXMLStreamWriter(new BufferedWriter(new OutputStreamWriter(outputStream, str), LogSeverity.ERROR_VALUE));
        } catch (UnsupportedEncodingException e) {
            throw new XMLStreamException(new StringBuffer("Unsupported encoding ").append(str).toString(), e);
        }
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(OutputStream outputStream) throws XMLStreamException {
        return new XMLEventWriterBase(createXMLStreamWriter(outputStream));
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(Writer writer) throws XMLStreamException {
        return new XMLEventWriterBase(createXMLStreamWriter(writer));
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(OutputStream outputStream, String str) throws XMLStreamException {
        return new XMLEventWriterBase(createXMLStreamWriter(outputStream, str));
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public void setProperty(String str, Object obj) {
        this.config.setProperty(str, obj);
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public Object getProperty(String str) {
        return this.config.getProperty(str);
    }

    public boolean isPrefixDefaulting() {
        return this.config.isPrefixDefaulting();
    }

    public void setPrefixDefaulting(boolean z) {
        this.config.setPrefixDefaulting(z);
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public boolean isPropertySupported(String str) {
        return this.config.isPropertySupported(str);
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(Result result) throws XMLStreamException {
        throw new UnsupportedOperationException();
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(Result result) throws XMLStreamException {
        throw new UnsupportedOperationException();
    }
}
