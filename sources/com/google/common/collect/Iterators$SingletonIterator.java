package com.google.common.collect;

import java.util.NoSuchElementException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Iterators$SingletonIterator extends UnmodifiableIterator {
    public static final Object SENTINEL = new Object();
    public Object valueOrSentinel;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.valueOrSentinel != SENTINEL;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object obj = this.valueOrSentinel;
        Object obj2 = SENTINEL;
        if (obj == obj2) {
            throw new NoSuchElementException();
        }
        this.valueOrSentinel = obj2;
        return obj;
    }
}
