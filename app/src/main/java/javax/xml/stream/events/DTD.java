package javax.xml.stream.events;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface DTD extends XMLEvent {
    String getDocumentTypeDeclaration();

    List getEntities();

    List getNotations();

    Object getProcessedDTD();
}
