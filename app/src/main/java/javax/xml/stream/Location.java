package javax.xml.stream;

/* JADX INFO: loaded from: classes4.dex */
public interface Location {
    int getCharacterOffset();

    int getColumnNumber();

    int getLineNumber();

    String getPublicId();

    String getSystemId();
}
