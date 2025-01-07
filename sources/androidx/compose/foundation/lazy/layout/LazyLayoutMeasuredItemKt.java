package androidx.compose.foundation.lazy.layout;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyLayoutMeasuredItemKt {
    public static final LazyLayoutMeasuredItemKt$$ExternalSyntheticLambda0 LazyLayoutMeasuredItemIndexComparator = new LazyLayoutMeasuredItemKt$$ExternalSyntheticLambda0();

    public static final List updatedVisibleItems(int i, int i2, List list, List list2) {
        if (list.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(list2);
        ArrayList arrayList2 = (ArrayList) list;
        int size = arrayList2.size();
        for (int i3 = 0; i3 < size; i3++) {
            LazyLayoutMeasuredItem lazyLayoutMeasuredItem = (LazyLayoutMeasuredItem) arrayList2.get(i3);
            int index = lazyLayoutMeasuredItem.getIndex();
            if (i <= index && index <= i2) {
                arrayList.add(lazyLayoutMeasuredItem);
            }
        }
        CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList, LazyLayoutMeasuredItemIndexComparator);
        return arrayList;
    }
}
