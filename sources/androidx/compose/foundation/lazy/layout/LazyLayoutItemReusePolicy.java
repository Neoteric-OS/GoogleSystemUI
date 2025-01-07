package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.ui.layout.SubcomposeSlotReusePolicy;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LazyLayoutItemReusePolicy implements SubcomposeSlotReusePolicy {
    public final MutableObjectIntMap countPerType;
    public final LazyLayoutItemContentFactory factory;

    public LazyLayoutItemReusePolicy(LazyLayoutItemContentFactory lazyLayoutItemContentFactory) {
        this.factory = lazyLayoutItemContentFactory;
        MutableObjectIntMap mutableObjectIntMap = ObjectIntMapKt.EmptyObjectIntMap;
        this.countPerType = new MutableObjectIntMap();
    }

    @Override // androidx.compose.ui.layout.SubcomposeSlotReusePolicy
    public final boolean areCompatible(Object obj, Object obj2) {
        LazyLayoutItemContentFactory lazyLayoutItemContentFactory = this.factory;
        return Intrinsics.areEqual(lazyLayoutItemContentFactory.getContentType(obj), lazyLayoutItemContentFactory.getContentType(obj2));
    }

    @Override // androidx.compose.ui.layout.SubcomposeSlotReusePolicy
    public final void getSlotsToRetain(SubcomposeSlotReusePolicy.SlotIdsSet slotIdsSet) {
        MutableObjectIntMap mutableObjectIntMap = this.countPerType;
        mutableObjectIntMap.clear();
        Iterator it = slotIdsSet.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            Object contentType = this.factory.getContentType(next);
            int findKeyIndex = mutableObjectIntMap.findKeyIndex(contentType);
            int i = findKeyIndex >= 0 ? mutableObjectIntMap.values[findKeyIndex] : 0;
            if (i == 7) {
                slotIdsSet.remove(next);
            } else {
                mutableObjectIntMap.set(i + 1, contentType);
            }
        }
    }
}
