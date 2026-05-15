package com.bea.xml.stream.util;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class EmptyIterator implements Iterator {
    public static final EmptyIterator emptyIterator = new EmptyIterator();

    @Override // java.util.Iterator
    public boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
