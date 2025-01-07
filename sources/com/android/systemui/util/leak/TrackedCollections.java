package com.android.systemui.util.leak;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TrackedCollections {
    public final WeakIdentityHashMap mCollections = new WeakIdentityHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CollectionState {
        public int halfwayCount;
        public int lastCount;
        public long startUptime;
    }
}
