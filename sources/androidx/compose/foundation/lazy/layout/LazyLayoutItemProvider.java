package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface LazyLayoutItemProvider {
    void Item(int i, Object obj, Composer composer, int i2);

    default Object getContentType(int i) {
        return null;
    }

    int getIndex(Object obj);

    int getItemCount();

    Object getKey(int i);
}
