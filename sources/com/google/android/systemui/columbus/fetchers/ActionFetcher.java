package com.google.android.systemui.columbus.fetchers;

import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActionFetcher {
    public final CoroutineScope coroutineScope;
    public final Map firstAvailableMap = new LinkedHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ActionListKey {
        public final List actions;

        public ActionListKey(List list) {
            this.actions = list;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ActionListKey) {
                ActionListKey actionListKey = (ActionListKey) obj;
                if (this.actions.size() == actionListKey.actions.size() && this.actions.containsAll(actionListKey.actions)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            Iterator it = this.actions.iterator();
            if (it.hasNext()) {
                throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
            }
            return 0;
        }
    }

    public ActionFetcher(CoroutineScope coroutineScope) {
        this.coroutineScope = coroutineScope;
    }
}
