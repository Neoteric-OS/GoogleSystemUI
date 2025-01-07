package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CollectionsKt__MutableCollectionsKt extends CollectionsKt__MutableCollectionsJVMKt {
    public static void addAll(Iterable iterable, Collection collection) {
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
            return;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
    }

    public static final boolean filterInPlace$CollectionsKt__MutableCollectionsKt(Iterable iterable, Function1 function1, boolean z) {
        Iterator it = iterable.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (((Boolean) function1.invoke(it.next())).booleanValue() == z) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }

    public static void removeAll(List list, Function1 function1) {
        int lastIndex;
        if (!(list instanceof RandomAccess)) {
            if (!(list instanceof KMappedMarker) || (list instanceof KMutableCollection)) {
                filterInPlace$CollectionsKt__MutableCollectionsKt(list, function1, true);
                return;
            } else {
                TypeIntrinsics.throwCce(list, "kotlin.collections.MutableIterable");
                throw null;
            }
        }
        int i = 0;
        IntProgressionIterator it = new IntRange(0, CollectionsKt__CollectionsKt.getLastIndex(list), 1).iterator();
        while (it.hasNext) {
            int nextInt = it.nextInt();
            Object obj = list.get(nextInt);
            if (!((Boolean) function1.invoke(obj)).booleanValue()) {
                if (i != nextInt) {
                    list.set(i, obj);
                }
                i++;
            }
        }
        if (i >= list.size() || i > (lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list))) {
            return;
        }
        while (true) {
            list.remove(lastIndex);
            if (lastIndex == i) {
                return;
            } else {
                lastIndex--;
            }
        }
    }

    public static boolean addAll(Collection collection, Sequence sequence) {
        Iterator it = sequence.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (collection.add(it.next())) {
                z = true;
            }
        }
        return z;
    }
}
