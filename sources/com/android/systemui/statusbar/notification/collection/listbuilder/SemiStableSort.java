package com.android.systemui.statusbar.notification.collection.listbuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToIntFunction;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SemiStableSort {
    public static final Companion Companion = new Companion();
    public final Lazy preallocatedWorkspace$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.collection.listbuilder.SemiStableSort$preallocatedWorkspace$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new ArrayList();
        }
    });
    public final Lazy preallocatedAdditions$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.collection.listbuilder.SemiStableSort$preallocatedAdditions$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new ArrayList();
        }
    });
    public final Lazy preallocatedMapToIndex$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.collection.listbuilder.SemiStableSort$preallocatedMapToIndex$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new HashMap();
        }
    });
    public final Lazy preallocatedMapToIndexComparator$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.collection.listbuilder.SemiStableSort$preallocatedMapToIndexComparator$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            final SemiStableSort semiStableSort = SemiStableSort.this;
            return Comparator.comparingInt(new ToIntFunction() { // from class: com.android.systemui.statusbar.notification.collection.listbuilder.SemiStableSort$preallocatedMapToIndexComparator$2.1
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    Integer num = (Integer) ((HashMap) SemiStableSort.this.preallocatedMapToIndex$delegate.getValue()).get(obj);
                    if (num == null) {
                        return -1;
                    }
                    return num.intValue();
                }
            });
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final void access$insertPreSortedElementsWithFewestMisOrderings(List list, Iterable iterable, Comparator comparator) {
            int i = 0;
            for (Object obj : iterable) {
                int size = list.size();
                int i2 = 0;
                int i3 = 0;
                int i4 = i;
                while (i < size) {
                    i2 += MathKt.getSign(comparator.compare(obj, list.get(i)));
                    if (i2 > i3) {
                        i4 = i + 1;
                        i3 = i2;
                    }
                    i++;
                }
                list.add(i4, obj);
                i = i4 + 1;
            }
        }

        public final boolean isSorted(List list, Comparator comparator) {
            if (list.size() <= 1) {
                return true;
            }
            Iterator it = list.iterator();
            Object next = it.next();
            while (it.hasNext()) {
                Object next2 = it.next();
                if (comparator.compare(next, next2) > 0) {
                    return false;
                }
                next = next2;
            }
            return true;
        }
    }

    public final ArrayList getPreallocatedAdditions() {
        return (ArrayList) this.preallocatedAdditions$delegate.getValue();
    }
}
