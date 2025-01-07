package com.android.systemui.statusbar.notification.collection.render;

import com.android.systemui.statusbar.notification.collection.ListEntry;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifViewBarn {
    public final Map rowMap = new LinkedHashMap();

    public final NotifViewController requireNodeController(ListEntry listEntry) {
        NotifViewController notifViewController = (NotifViewController) this.rowMap.get(listEntry.getKey());
        if (notifViewController != null) {
            return notifViewController;
        }
        throw new IllegalStateException(("No view has been registered for entry: " + listEntry.getKey()).toString());
    }
}
