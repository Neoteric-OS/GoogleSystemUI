package com.google.android.systemui.power.batteryhealth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HealthUpdateReceiver extends BroadcastReceiver {
    public final HealthManager healthManager;

    public HealthUpdateReceiver(HealthManager healthManager) {
        this.healthManager = healthManager;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Log.i("HealthUpdateReceiver", "Start new BHI update");
        BuildersKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new HealthUpdateReceiver$onReceive$1(this, null), 3);
    }
}
