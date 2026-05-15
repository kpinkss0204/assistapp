package org.simpleframework.xml.stream;

/* JADX INFO: loaded from: classes3.dex */
interface EventNode extends Iterable<Attribute> {
    int getLine();

    String getName();

    String getPrefix();

    String getReference();

    Object getSource();

    String getValue();

    boolean isEnd();

    boolean isStart();

    boolean isText();
}
