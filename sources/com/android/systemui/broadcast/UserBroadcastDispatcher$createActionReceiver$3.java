package com.android.systemui.broadcast;

import android.content.BroadcastReceiver;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class UserBroadcastDispatcher$createActionReceiver$3 extends FunctionReferenceImpl implements Function2 {
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        boolean z;
        BroadcastReceiver broadcastReceiver = (BroadcastReceiver) obj;
        int intValue = ((Number) obj2).intValue();
        PendingRemovalStore pendingRemovalStore = (PendingRemovalStore) this.receiver;
        synchronized (pendingRemovalStore.pendingRemoval) {
            if (!pendingRemovalStore.pendingRemoval.contains(intValue, broadcastReceiver)) {
                z = pendingRemovalStore.pendingRemoval.contains(-1, broadcastReceiver);
            }
        }
        return Boolean.valueOf(z);
    }
}
