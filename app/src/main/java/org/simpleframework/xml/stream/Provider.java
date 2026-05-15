package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: classes3.dex */
interface Provider {
    EventReader provide(InputStream inputStream) throws Exception;

    EventReader provide(Reader reader) throws Exception;
}
