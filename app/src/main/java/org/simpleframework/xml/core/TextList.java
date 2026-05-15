package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Iterator;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/* JADX INFO: loaded from: classes3.dex */
class TextList implements Repeater {
    private final CollectionFactory factory;
    private final Primitive primitive;
    private final Type type;

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) throws Exception {
        return true;
    }

    public TextList(Context context, Type type, Label label) {
        ClassType classType = new ClassType(String.class);
        this.type = classType;
        this.factory = new CollectionFactory(context, type);
        this.primitive = new Primitive(context, classType);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode) throws Exception {
        Instance collectionFactory = this.factory.getInstance(inputNode);
        Object instance = collectionFactory.getInstance();
        if (collectionFactory.isReference()) {
            return collectionFactory.getInstance();
        }
        return read(inputNode, instance);
    }

    @Override // org.simpleframework.xml.core.Repeater, org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode, Object obj) throws Exception {
        Collection collection = (Collection) obj;
        Object obj2 = this.primitive.read(inputNode);
        if (obj2 != null) {
            collection.add(obj2);
        }
        return obj;
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(OutputNode outputNode, Object obj) throws Exception {
        OutputNode parent = outputNode.getParent();
        Iterator it = ((Collection) obj).iterator();
        while (it.hasNext()) {
            this.primitive.write(parent, it.next());
        }
    }
}
