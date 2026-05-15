package com.bea.xml.stream.events;

import com.bea.xml.stream.util.EmptyIterator;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;

/* JADX INFO: loaded from: classes2.dex */
public class EndElementEvent extends NamedEvent implements EndElement {
    private List outOfScopeNamespaces;

    public EndElementEvent() {
        init();
    }

    public EndElementEvent(QName qName) {
        super(qName);
        init();
    }

    protected void init() {
        setEventType(2);
    }

    @Override // javax.xml.stream.events.EndElement
    public Iterator getNamespaces() {
        List list = this.outOfScopeNamespaces;
        if (list == null) {
            return EmptyIterator.emptyIterator;
        }
        return list.iterator();
    }

    public void addNamespace(Namespace namespace) {
        if (this.outOfScopeNamespaces == null) {
            this.outOfScopeNamespaces = new ArrayList();
        }
        this.outOfScopeNamespaces.add(namespace);
    }

    public void reset() {
        List list = this.outOfScopeNamespaces;
        if (list != null) {
            list.clear();
        }
    }

    @Override // com.bea.xml.stream.events.BaseEvent
    public String toString() {
        String string = new StringBuffer("</").append(nameAsString()).toString();
        Iterator namespaces = getNamespaces();
        while (namespaces.hasNext()) {
            string = new StringBuffer().append(string).append(" ").append(namespaces.next().toString()).toString();
        }
        return new StringBuffer().append(string).append(">").toString();
    }

    @Override // com.bea.xml.stream.events.NamedEvent, com.bea.xml.stream.events.BaseEvent
    protected void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("</");
        QName name = getName();
        String prefix = name.getPrefix();
        if (prefix != null && prefix.length() > 0) {
            writer.write(prefix);
            writer.write(58);
        }
        writer.write(name.getLocalPart());
        writer.write(62);
    }
}
