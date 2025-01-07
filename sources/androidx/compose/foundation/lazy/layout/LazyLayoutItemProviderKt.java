package androidx.compose.foundation.lazy.layout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyLayoutItemProviderKt {
    public static final int findIndexByKey(int i, LazyLayoutItemProvider lazyLayoutItemProvider, Object obj) {
        if (obj != null && lazyLayoutItemProvider.getItemCount() != 0) {
            if (i < lazyLayoutItemProvider.getItemCount() && obj.equals(lazyLayoutItemProvider.getKey(i))) {
                return i;
            }
            int index = lazyLayoutItemProvider.getIndex(obj);
            if (index != -1) {
                return index;
            }
        }
        return i;
    }
}
