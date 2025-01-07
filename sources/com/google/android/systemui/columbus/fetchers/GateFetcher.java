package com.google.android.systemui.columbus.fetchers;

import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GateFetcher {
    public final Map blockingGateMap = new LinkedHashMap();
    public final CoroutineScope coroutineScope;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class GateCollectionKey {
        public final Set gateSet;

        public GateCollectionKey(Collection collection) {
            this.gateSet = CollectionsKt.toSet(collection);
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof GateCollectionKey) {
                GateCollectionKey gateCollectionKey = (GateCollectionKey) obj;
                if (this.gateSet.size() == gateCollectionKey.gateSet.size() && this.gateSet.containsAll(gateCollectionKey.gateSet)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            Iterator it = this.gateSet.iterator();
            if (it.hasNext()) {
                throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
            }
            return 0;
        }
    }

    public GateFetcher(CoroutineScope coroutineScope) {
        this.coroutineScope = coroutineScope;
    }
}
