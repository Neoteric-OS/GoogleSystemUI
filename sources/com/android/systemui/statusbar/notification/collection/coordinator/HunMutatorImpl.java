package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.policy.HeadsUpManager;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HunMutatorImpl {
    public final List deferred = new ArrayList();
    public final HeadsUpManager headsUpManager;

    public HunMutatorImpl(HeadsUpManager headsUpManager) {
        this.headsUpManager = headsUpManager;
    }
}
