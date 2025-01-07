package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class HeadsUpCoordinatorKt {
    public static final void access$modifyHuns(HeadsUpManager headsUpManager, Function1 function1) {
        HunMutatorImpl hunMutatorImpl = new HunMutatorImpl(headsUpManager);
        function1.invoke(hunMutatorImpl);
        for (Pair pair : hunMutatorImpl.deferred) {
            ((BaseHeadsUpManager) hunMutatorImpl.headsUpManager).removeNotification$1((String) pair.component1(), "commitModifications", ((Boolean) pair.component2()).booleanValue());
        }
        hunMutatorImpl.deferred.clear();
    }
}
