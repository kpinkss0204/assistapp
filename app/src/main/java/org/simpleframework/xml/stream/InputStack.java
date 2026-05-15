package org.simpleframework.xml.stream;

/* JADX INFO: loaded from: classes3.dex */
class InputStack extends Stack<InputNode> {
    public InputStack() {
        super(6);
    }

    public boolean isRelevant(InputNode inputNode) {
        return contains(inputNode) || isEmpty();
    }
}
