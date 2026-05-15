package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes3.dex */
class InputNodeMap extends LinkedHashMap<String, InputNode> implements NodeMap<InputNode> {
    private final InputNode source;

    protected InputNodeMap(InputNode inputNode) {
        this.source = inputNode;
    }

    public InputNodeMap(InputNode inputNode, EventNode eventNode) {
        this.source = inputNode;
        build(eventNode);
    }

    private void build(EventNode eventNode) {
        for (Attribute attribute : eventNode) {
            InputAttribute inputAttribute = new InputAttribute(this.source, attribute);
            if (!attribute.isReserved()) {
                put(inputAttribute.getName(), inputAttribute);
            }
        }
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public InputNode getNode() {
        return this.source;
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public String getName() {
        return this.source.getName();
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public InputNode put(String str, String str2) {
        InputAttribute inputAttribute = new InputAttribute(this.source, str, str2);
        if (str != null) {
            put(str, inputAttribute);
        }
        return inputAttribute;
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public InputNode remove(String str) {
        return (InputNode) super.remove((Object) str);
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public InputNode get(String str) {
        return (InputNode) super.get((Object) str);
    }

    @Override // org.simpleframework.xml.stream.NodeMap, java.lang.Iterable
    public Iterator<String> iterator() {
        return keySet().iterator();
    }
}
