package com.android.systemui.util;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.util.NamedListenerSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NamedListenerSet implements Set, KMappedMarker {
    public final Function1 getName = new Function1() { // from class: com.android.systemui.util.NamedListenerSet.1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return obj.getClass().getName();
        }
    };
    public final CopyOnWriteArrayList listeners = new CopyOnWriteArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NamedListener {
        public final Object listener;
        public final String name;

        public NamedListener(NamedListenerSet namedListenerSet, Object obj) {
            this.listener = obj;
            this.name = (String) namedListenerSet.getName.invoke(obj);
        }

        public final boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj != this) {
                if (!(obj instanceof NamedListener)) {
                    return false;
                }
                if (!Intrinsics.areEqual(this.listener, ((NamedListener) obj).listener)) {
                    return false;
                }
            }
            return true;
        }

        public final int hashCode() {
            return this.listener.hashCode();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        Object obj2;
        if (obj == null) {
            return false;
        }
        Iterator it = this.listeners.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            if (Intrinsics.areEqual(((NamedListener) obj2).listener, obj)) {
                break;
            }
        }
        return obj2 != null;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        int i;
        CopyOnWriteArrayList copyOnWriteArrayList = this.listeners;
        if (copyOnWriteArrayList == null || !copyOnWriteArrayList.isEmpty()) {
            Iterator it = copyOnWriteArrayList.iterator();
            i = 0;
            while (it.hasNext()) {
                if (collection.contains(((NamedListener) it.next()).listener) && (i = i + 1) < 0) {
                    CollectionsKt__CollectionsKt.throwCountOverflow();
                    throw null;
                }
            }
        } else {
            i = 0;
        }
        return i == collection.size();
    }

    public final void forEachTraced(Consumer consumer) {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            NamedListener namedListener = (NamedListener) it.next();
            String str = namedListener.name;
            Object obj = namedListener.listener;
            boolean isEnabled = Trace.isEnabled();
            if (isEnabled) {
                TraceUtilsKt.beginSlice(str);
            }
            try {
                consumer.accept(obj);
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            } catch (Throwable th) {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
                throw th;
            }
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.listeners.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return SequencesKt__SequenceBuilderKt.iterator(new NamedListenerSet$iterator$1(this, null));
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(final Object obj) {
        if (obj == null) {
            return false;
        }
        return this.listeners.removeIf(new Predicate() { // from class: com.android.systemui.util.NamedListenerSet$remove$1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj2) {
                return Intrinsics.areEqual(((NamedListenerSet.NamedListener) obj2).listener, obj);
            }
        });
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.listeners.size();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return CollectionToArray.toArray(this, objArr);
    }
}
