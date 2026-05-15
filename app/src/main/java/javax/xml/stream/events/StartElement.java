package javax.xml.stream.events;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes4.dex */
public interface StartElement extends XMLEvent {
    Attribute getAttributeByName(QName qName);

    Iterator getAttributes();

    QName getName();

    NamespaceContext getNamespaceContext();

    String getNamespaceURI(String str);

    Iterator getNamespaces();
}
