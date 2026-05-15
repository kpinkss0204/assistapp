package org.simpleframework.xml.stream;

/* JADX INFO: loaded from: classes3.dex */
abstract class EventAttribute implements Attribute {
    @Override // org.simpleframework.xml.stream.Attribute
    public String getPrefix() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.Attribute
    public String getReference() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.Attribute
    public Object getSource() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.Attribute
    public boolean isReserved() {
        return false;
    }

    EventAttribute() {
    }
}
