package com.bea.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: classes2.dex */
public class MXParserFactory extends XMLInputFactory {
    ConfigurationContextBase config = new ConfigurationContextBase();

    public static XMLInputFactory newInstance() {
        return XMLInputFactory.newInstance();
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(Source source) throws XMLStreamException {
        if (source instanceof SAXSource) {
            InputSource inputSource = ((SAXSource) source).getInputSource();
            if (inputSource != null) {
                String systemId = inputSource.getSystemId();
                Reader characterStream = inputSource.getCharacterStream();
                if (characterStream != null) {
                    return createXMLStreamReader(systemId, characterStream);
                }
                InputStream byteStream = inputSource.getByteStream();
                if (byteStream != null) {
                    return createXMLStreamReader(systemId, byteStream);
                }
            }
            throw new XMLStreamException("Can only create STaX reader for a SAXSource if Reader or InputStream exposed via getSource(); can not use -- not implemented.");
        }
        boolean z = source instanceof DOMSource;
        throw new UnsupportedOperationException(new StringBuffer("XMLInputFactory.createXMLStreamReader(").append(source.getClass().getName()).append(") not yet implemented").toString());
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(InputStream inputStream) throws XMLStreamException {
        MXParser mXParser = new MXParser();
        mXParser.setInput(inputStream);
        mXParser.setConfigurationContext(this.config);
        return mXParser;
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(InputStream inputStream, String str) throws XMLStreamException {
        MXParser mXParser = new MXParser();
        mXParser.setInput(inputStream, str);
        mXParser.setConfigurationContext(this.config);
        return mXParser;
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(String str, InputStream inputStream) throws XMLStreamException {
        return createXMLStreamReader(inputStream);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(String str, Reader reader) throws XMLStreamException {
        return createXMLStreamReader(reader);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(String str, Reader reader) throws XMLStreamException {
        return createXMLEventReader(reader);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(String str, InputStream inputStream) throws XMLStreamException {
        return createXMLEventReader(inputStream);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(Reader reader) throws XMLStreamException {
        return createXMLEventReader(createXMLStreamReader(reader));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        if (this.config.getEventAllocator() == null) {
            return new XMLEventReaderBase(xMLStreamReader);
        }
        return new XMLEventReaderBase(xMLStreamReader, this.config.getEventAllocator().newInstance());
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(Source source) throws XMLStreamException {
        return createXMLEventReader(createXMLStreamReader(source));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(InputStream inputStream) throws XMLStreamException {
        return createXMLEventReader(createXMLStreamReader(inputStream));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(InputStream inputStream, String str) throws XMLStreamException {
        return createXMLEventReader(createXMLStreamReader(inputStream, str));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLResolver getXMLResolver() {
        return this.config.getXMLResolver();
    }

    @Override // javax.xml.stream.XMLInputFactory
    public void setXMLResolver(XMLResolver xMLResolver) {
        this.config.setXMLResolver(xMLResolver);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createFilteredReader(XMLStreamReader xMLStreamReader, StreamFilter streamFilter) throws XMLStreamException {
        return new StreamReaderFilter(xMLStreamReader, streamFilter);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createFilteredReader(XMLEventReader xMLEventReader, EventFilter eventFilter) throws XMLStreamException {
        return new EventReaderFilter(xMLEventReader, eventFilter);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLReporter getXMLReporter() {
        return this.config.getXMLReporter();
    }

    @Override // javax.xml.stream.XMLInputFactory
    public void setXMLReporter(XMLReporter xMLReporter) {
        this.config.setXMLReporter(xMLReporter);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public void setEventAllocator(XMLEventAllocator xMLEventAllocator) {
        this.config.setEventAllocator(xMLEventAllocator);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventAllocator getEventAllocator() {
        return this.config.getEventAllocator();
    }

    public void setCoalescing(boolean z) {
        this.config.setCoalescing(z);
    }

    public boolean isCoalescing() {
        return this.config.isCoalescing();
    }

    @Override // javax.xml.stream.XMLInputFactory
    public void setProperty(String str, Object obj) throws IllegalArgumentException {
        this.config.setProperty(str, obj);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public Object getProperty(String str) throws IllegalArgumentException {
        return this.config.getProperty(str);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(Reader reader) throws XMLStreamException {
        MXParser mXParser = new MXParser();
        mXParser.setInput(reader);
        mXParser.setConfigurationContext(this.config);
        return mXParser;
    }

    @Override // javax.xml.stream.XMLInputFactory
    public boolean isPropertySupported(String str) {
        return this.config.isPropertySupported(str);
    }
}
