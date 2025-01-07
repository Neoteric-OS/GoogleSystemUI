package com.android.systemui.statusbar.notification.collection.provider;

import android.util.ArraySet;
import com.android.systemui.util.ListenerSet;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VisualStabilityProvider {
    public boolean isReorderingAllowed;
    public final ListenerSet allListeners = new ListenerSet();
    public final ArraySet temporaryListeners = new ArraySet();

    public VisualStabilityProvider() {
        new CopyOnWriteArrayList();
        this.isReorderingAllowed = true;
    }
}
