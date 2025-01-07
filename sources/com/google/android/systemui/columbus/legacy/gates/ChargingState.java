package com.google.android.systemui.columbus.legacy.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.systemui.broadcast.BroadcastDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChargingState extends TransientGate {
    public final BroadcastDispatcher broadcastDispatcher;
    public final long gateDuration = 500;
    public final ChargingState$powerReceiver$1 powerReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.columbus.legacy.gates.ChargingState$powerReceiver$1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            ChargingState chargingState = ChargingState.this;
            chargingState.blockForMillis(chargingState.gateDuration);
        }
    };

    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.android.systemui.columbus.legacy.gates.ChargingState$powerReceiver$1] */
    public ChargingState(BroadcastDispatcher broadcastDispatcher) {
        this.broadcastDispatcher = broadcastDispatcher;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.powerReceiver, intentFilter, null, null, 0, 60);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        this.broadcastDispatcher.unregisterReceiver(this.powerReceiver);
    }
}
