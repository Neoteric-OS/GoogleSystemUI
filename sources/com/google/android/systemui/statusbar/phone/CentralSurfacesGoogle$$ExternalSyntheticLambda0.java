package com.google.android.systemui.statusbar.phone;

import android.content.IntentFilter;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.google.android.systemui.power.batteryhealth.HealthManager;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesGoogle$$ExternalSyntheticLambda0 implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        HealthManager healthManager = (HealthManager) obj;
        if (healthManager.periodicUpdateEnabled) {
            Log.i("HealthManager", "Enable BHI");
            BroadcastDispatcher.registerReceiver$default(healthManager.broadcastDispatcher, healthManager.bootCompletedReceiver, new IntentFilter("android.intent.action.BOOT_COMPLETED"), null, null, 0, 60);
        }
    }
}
