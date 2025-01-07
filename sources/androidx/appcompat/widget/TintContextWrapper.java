package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TintContextWrapper extends ContextWrapper {
    public static final Object CACHE_LOCK = null;

    public static void wrap(Context context) {
        if (context.getResources() instanceof TintResources) {
            return;
        }
        context.getResources();
        int i = VectorEnabledTintResources.$r8$clinit;
    }
}
