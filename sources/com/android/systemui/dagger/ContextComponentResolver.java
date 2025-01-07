package com.android.systemui.dagger;

import java.util.Map;
import javax.inject.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextComponentResolver {
    public final Map mActivityCreators;
    public final Map mBroadcastReceiverCreators;
    public final Map mRecentsCreators;
    public final Map mServiceCreators;

    public ContextComponentResolver(Map map, Map map2, Map map3, Map map4) {
        this.mActivityCreators = map;
        this.mServiceCreators = map2;
        this.mRecentsCreators = map3;
        this.mBroadcastReceiverCreators = map4;
    }

    public static Object resolve(String str, Map map) {
        try {
            Provider provider = (Provider) map.get(Class.forName(str));
            if (provider == null) {
                return null;
            }
            return provider.get();
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
