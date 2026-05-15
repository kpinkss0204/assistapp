package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class ParameterMap extends LinkedHashMap<Object, Parameter> implements Iterable<Parameter> {
    @Override // java.lang.Iterable
    public Iterator<Parameter> iterator() {
        return values().iterator();
    }

    public Parameter get(int i) {
        return getAll().get(i);
    }

    public List<Parameter> getAll() {
        Collection<Parameter> collectionValues = values();
        if (!collectionValues.isEmpty()) {
            return new ArrayList(collectionValues);
        }
        return Collections.emptyList();
    }
}
