package javax.xml.stream.events;

/* JADX INFO: loaded from: classes4.dex */
public interface StartDocument extends XMLEvent {
    boolean encodingSet();

    String getCharacterEncodingScheme();

    String getSystemId();

    String getVersion();

    boolean isStandalone();

    boolean standaloneSet();
}
