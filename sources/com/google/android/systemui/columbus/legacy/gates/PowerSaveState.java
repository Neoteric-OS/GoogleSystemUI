package com.google.android.systemui.columbus.legacy.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.systemui.broadcast.BroadcastDispatcher;
import dagger.Lazy;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PowerSaveState extends Gate {
    public boolean batterySaverEnabled;
    public final CoroutineDispatcher bgDispatcher;
    public final BroadcastDispatcher broadcastDispatcher;
    public boolean isDeviceInteractive;
    public final Lazy powerManager;
    public final PowerSaveState$receiver$1 receiver = new BroadcastReceiver() { // from class: com.google.android.systemui.columbus.legacy.gates.PowerSaveState$receiver$1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            PowerSaveState powerSaveState = PowerSaveState.this;
            powerSaveState.getClass();
            BuildersKt.launch$default(powerSaveState.coroutineScope, null, null, new PowerSaveState$refreshStatus$1(powerSaveState, null), 3);
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.PowerSaveState$receiver$1] */
    public PowerSaveState(BroadcastDispatcher broadcastDispatcher, Lazy lazy, CoroutineDispatcher coroutineDispatcher) {
        this.broadcastDispatcher = broadcastDispatcher;
        this.powerManager = lazy;
        this.bgDispatcher = coroutineDispatcher;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.receiver, intentFilter, null, null, 0, 60);
        BuildersKt.launch$default(this.coroutineScope, null, null, new PowerSaveState$refreshStatus$1(this, null), 3);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        this.broadcastDispatcher.unregisterReceiver(this.receiver);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final String toString() {
        return super.toString() + BuildersKt.runBlocking(this.mainDispatcher, new PowerSaveState$toString$1(this, null));
    }
}
