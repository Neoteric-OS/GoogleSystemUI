package com.android.systemui.dagger;

import dagger.internal.Provider;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ContextComponentResolver_Factory implements Provider {
    public static ContextComponentResolver newInstance(Map map, Map map2, Map map3, Map map4) {
        return new ContextComponentResolver(map, map2, map3, map4);
    }
}
