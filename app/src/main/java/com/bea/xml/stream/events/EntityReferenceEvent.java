package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;

/* JADX INFO: loaded from: classes2.dex */
public class EntityReferenceEvent extends BaseEvent implements EntityReference {
    private EntityDeclaration ed;
    private String name;
    private String replacementText;

    public String getBaseURI() {
        return null;
    }

    @Override // com.bea.xml.stream.events.BaseEvent, javax.xml.stream.Location
    public String getPublicId() {
        return null;
    }

    @Override // com.bea.xml.stream.events.BaseEvent, javax.xml.stream.Location
    public String getSystemId() {
        return null;
    }

    public EntityReferenceEvent() {
        init();
    }

    public EntityReferenceEvent(String str, EntityDeclaration entityDeclaration) {
        init();
        this.name = str;
        this.ed = entityDeclaration;
    }

    public String getReplacementText() {
        return this.ed.getReplacementText();
    }

    @Override // javax.xml.stream.events.EntityReference
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setReplacementText(String str) {
        this.replacementText = str;
    }

    @Override // javax.xml.stream.events.EntityReference
    public EntityDeclaration getDeclaration() {
        return this.ed;
    }

    protected void init() {
        setEventType(9);
    }

    @Override // com.bea.xml.stream.events.BaseEvent
    protected void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write(38);
        writer.write(getName());
        writer.write(59);
    }

    @Override // com.bea.xml.stream.events.BaseEvent
    public String toString() {
        String replacementText = getReplacementText();
        if (replacementText == null) {
            replacementText = "";
        }
        return new StringBuffer("&").append(getName()).append(":='").append(replacementText).append("'").toString();
    }
}
