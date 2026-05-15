package org.simpleframework.xml.stream;

/* JADX INFO: loaded from: classes3.dex */
interface EventReader {
    EventNode next() throws Exception;

    EventNode peek() throws Exception;
}
