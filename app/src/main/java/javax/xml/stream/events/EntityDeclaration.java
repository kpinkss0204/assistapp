package javax.xml.stream.events;

/* JADX INFO: loaded from: classes4.dex */
public interface EntityDeclaration extends XMLEvent {
    String getBaseURI();

    String getName();

    String getNotationName();

    String getPublicId();

    String getReplacementText();

    String getSystemId();
}
