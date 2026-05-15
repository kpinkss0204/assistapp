package org.simpleframework.xml.util;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class LimitedCache<T> extends LinkedHashMap<Object, T> implements Cache<T> {
    private final int capacity;

    public LimitedCache() {
        this(50000);
    }

    public LimitedCache(int i) {
        this.capacity = i;
    }

    @Override // org.simpleframework.xml.util.Cache
    public void cache(Object obj, T t) {
        put(obj, t);
    }

    @Override // org.simpleframework.xml.util.Cache
    public T take(Object obj) {
        return (T) remove(obj);
    }

    @Override // org.simpleframework.xml.util.Cache
    public T fetch(Object obj) {
        return get(obj);
    }

    @Override // org.simpleframework.xml.util.Cache
    public boolean contains(Object obj) {
        return containsKey(obj);
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<Object, T> entry) {
        return size() > this.capacity;
    }
}
