package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.StartDocument;

/* JADX INFO: loaded from: classes2.dex */
public class StartDocumentEvent extends BaseEvent implements StartDocument {
    protected String systemId = "";
    protected String publicId = "";
    protected String encodingScheme = "UTF-8";
    protected boolean standalone = false;
    protected String version = "1.0";
    private boolean encodingSchemeSet = false;
    private boolean standaloneSet = false;

    public StartDocumentEvent() {
        init();
    }

    protected void init() {
        setEventType(7);
    }

    @Override // com.bea.xml.stream.events.BaseEvent, javax.xml.stream.Location
    public String getSystemId() {
        return this.systemId;
    }

    @Override // javax.xml.stream.events.StartDocument
    public String getCharacterEncodingScheme() {
        return this.encodingScheme;
    }

    @Override // javax.xml.stream.events.StartDocument
    public boolean isStandalone() {
        return this.standalone;
    }

    @Override // javax.xml.stream.events.StartDocument
    public String getVersion() {
        return this.version;
    }

    public void setStandalone(boolean z) {
        this.standaloneSet = true;
        this.standalone = z;
    }

    public void setStandalone(String str) {
        this.standaloneSet = true;
        if (str == null) {
            this.standalone = true;
        } else if (str.equals("yes")) {
            this.standalone = true;
        } else {
            this.standalone = false;
        }
    }

    @Override // javax.xml.stream.events.StartDocument
    public boolean encodingSet() {
        return this.encodingSchemeSet;
    }

    @Override // javax.xml.stream.events.StartDocument
    public boolean standaloneSet() {
        return this.standaloneSet;
    }

    public void setEncoding(String str) {
        this.encodingScheme = str;
        this.encodingSchemeSet = true;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void clear() {
        this.encodingScheme = "UTF-8";
        this.standalone = true;
        this.version = "1.0";
        this.encodingSchemeSet = false;
        this.standaloneSet = false;
    }

    @Override // com.bea.xml.stream.events.BaseEvent
    protected void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("<?xml version=\"");
        writer.write(this.version);
        writer.write("\" encoding='");
        writer.write(this.encodingScheme);
        writer.write(39);
        if (this.standaloneSet) {
            writer.write(" standalone='");
            writer.write(this.standalone ? "yes'" : "no'");
        }
        writer.write("?>");
    }
}
