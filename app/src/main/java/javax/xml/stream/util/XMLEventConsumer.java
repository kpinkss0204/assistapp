package javax.xml.stream.util;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/* JADX INFO: loaded from: classes4.dex */
public interface XMLEventConsumer {
    void add(XMLEvent xMLEvent) throws XMLStreamException;
}
