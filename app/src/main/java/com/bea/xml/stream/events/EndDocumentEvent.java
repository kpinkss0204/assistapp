package com.bea.xml.stream.events;

import java.io.Writer;
import javax.xml.stream.events.EndDocument;

/* JADX INFO: loaded from: classes2.dex */
public class EndDocumentEvent extends BaseEvent implements EndDocument {
    @Override // com.bea.xml.stream.events.BaseEvent
    protected void doWriteAsEncodedUnicode(Writer writer) {
    }

    public EndDocumentEvent() {
        init();
    }

    protected void init() {
        setEventType(8);
    }

    @Override // com.bea.xml.stream.events.BaseEvent
    public String toString() {
        return "<? EndDocument ?>";
    }
}
