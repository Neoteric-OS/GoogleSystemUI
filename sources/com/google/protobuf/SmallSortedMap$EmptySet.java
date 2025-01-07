package com.google.protobuf;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SmallSortedMap$EmptySet {
    public static final AnonymousClass1 ITERATOR = new AnonymousClass1();
    public static final AnonymousClass2 ITERABLE = new AnonymousClass2();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.protobuf.SmallSortedMap$EmptySet$1, reason: invalid class name */
    public final class AnonymousClass1 implements Iterator {
        @Override // java.util.Iterator
        public final boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public final Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.protobuf.SmallSortedMap$EmptySet$2, reason: invalid class name */
    public final class AnonymousClass2 implements Iterable {
        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return SmallSortedMap$EmptySet.ITERATOR;
        }
    }
}
