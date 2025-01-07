package androidx.coordinatorlayout.widget;

import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools$SimplePool;
import java.util.ArrayList;
import java.util.HashSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DirectedAcyclicGraph {
    public final Pools$SimplePool mListPool = new Pools$SimplePool(10);
    public final SimpleArrayMap mGraph = new SimpleArrayMap(0);
    public final ArrayList mSortResult = new ArrayList();
    public final HashSet mSortTmpMarked = new HashSet();

    public final void dfs(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (hashSet.contains(obj)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(obj);
        ArrayList arrayList2 = (ArrayList) this.mGraph.get(obj);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                dfs(arrayList2.get(i), arrayList, hashSet);
            }
        }
        hashSet.remove(obj);
        arrayList.add(obj);
    }
}
