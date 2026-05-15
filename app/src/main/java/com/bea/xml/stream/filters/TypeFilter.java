package com.bea.xml.stream.filters;

import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

/* JADX INFO: loaded from: classes2.dex */
public class TypeFilter implements EventFilter, StreamFilter {
    protected boolean[] types = new boolean[20];

    public void addType(int i) {
        this.types[i] = true;
    }

    @Override // javax.xml.stream.EventFilter
    public boolean accept(XMLEvent xMLEvent) {
        return this.types[xMLEvent.getEventType()];
    }

    @Override // javax.xml.stream.StreamFilter
    public boolean accept(XMLStreamReader xMLStreamReader) {
        return this.types[xMLStreamReader.getEventType()];
    }
}
