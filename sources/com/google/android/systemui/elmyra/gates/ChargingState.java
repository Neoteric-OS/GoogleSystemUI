package com.google.android.systemui.elmyra.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.systemui.util.concurrency.DelayableExecutor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChargingState extends TransientGate {
    public final Context mContext;
    public final AnonymousClass1 mPowerReceiver;

    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.systemui.elmyra.gates.ChargingState$1] */
    public ChargingState(Context context, DelayableExecutor delayableExecutor, int i) {
        super(delayableExecutor, i);
        this.mPowerReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.elmyra.gates.ChargingState.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                ChargingState.this.block();
            }
        };
        this.mContext = context;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        this.mContext.registerReceiver(this.mPowerReceiver, intentFilter);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        this.mContext.unregisterReceiver(this.mPowerReceiver);
    }
}
