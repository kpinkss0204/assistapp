package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;

/* JADX INFO: loaded from: classes3.dex */
public class KeyIndex extends Index {
    private static final KeyIndex INSTANCE = new KeyIndex();

    public int hashCode() {
        return 37;
    }

    @Override // com.google.firebase.database.snapshot.Index
    public boolean isDefinedOn(Node node) {
        return true;
    }

    public static KeyIndex getInstance() {
        return INSTANCE;
    }

    private KeyIndex() {
    }

    @Override // com.google.firebase.database.snapshot.Index
    public NamedNode makePost(ChildKey childKey, Node node) {
        Utilities.hardAssert(node instanceof StringNode);
        return new NamedNode(ChildKey.fromString((String) node.getValue()), EmptyNode.Empty());
    }

    @Override // com.google.firebase.database.snapshot.Index
    public NamedNode maxPost() {
        return NamedNode.getMaxNode();
    }

    @Override // com.google.firebase.database.snapshot.Index
    public String getQueryDefinition() {
        return ".key";
    }

    @Override // java.util.Comparator
    public int compare(NamedNode namedNode, NamedNode namedNode2) {
        return namedNode.getName().compareTo(namedNode2.getName());
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        return obj instanceof KeyIndex;
    }

    public String toString() {
        return "KeyIndex";
    }
}
