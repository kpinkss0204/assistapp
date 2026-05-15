package javax.xml.stream.events;

import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes4.dex */
public interface Attribute extends XMLEvent {
    String getDTDType();

    QName getName();

    String getValue();

    boolean isSpecified();
}
