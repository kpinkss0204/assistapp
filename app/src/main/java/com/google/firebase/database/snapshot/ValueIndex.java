package com.google.firebase.database.snapshot;

/* JADX INFO: loaded from: classes3.dex */
public class ValueIndex extends Index {
    private static final ValueIndex INSTANCE = new ValueIndex();

    public int hashCode() {
        return 4;
    }

    @Override // com.google.firebase.database.snapshot.Index
    public boolean isDefinedOn(Node node) {
        return true;
    }

    private ValueIndex() {
    }

    public static ValueIndex getInstance() {
        return INSTANCE;
    }

    @Override // com.google.firebase.database.snapshot.Index
    public NamedNode makePost(ChildKey childKey, Node node) {
        return new NamedNode(childKey, node);
    }

    @Override // com.google.firebase.database.snapshot.Index
    public NamedNode maxPost() {
        return new NamedNode(ChildKey.getMaxName(), Node.MAX_NODE);
    }

    @Override // com.google.firebase.database.snapshot.Index
    public String getQueryDefinition() {
        return ".value";
    }

    @Override // java.util.Comparator
    public int compare(NamedNode namedNode, NamedNode namedNode2) {
        int iCompareTo = namedNode.getNode().compareTo(namedNode2.getNode());
        return iCompareTo == 0 ? namedNode.getName().compareTo(namedNode2.getName()) : iCompareTo;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        return obj instanceof ValueIndex;
    }

    public String toString() {
        return "ValueIndex";
    }
}
