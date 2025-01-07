package com.google.android.systemui.elmyra.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import com.android.systemui.broadcast.BroadcastDispatcher;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PowerSaveState extends Gate {
    public boolean mBatterySaverEnabled;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public boolean mIsDeviceInteractive;
    public final Object mLock;
    public final PowerManager mPowerManager;
    public final AnonymousClass1 mReceiver;

    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.elmyra.gates.PowerSaveState$1] */
    public PowerSaveState(Executor executor, PowerManager powerManager, BroadcastDispatcher broadcastDispatcher) {
        super(executor);
        this.mLock = new Object();
        this.mReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.elmyra.gates.PowerSaveState.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                PowerSaveState.this.refreshStatus();
                PowerSaveState.this.notifyListener();
            }
        };
        this.mPowerManager = powerManager;
        this.mBroadcastDispatcher = broadcastDispatcher;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        boolean z;
        synchronized (this.mLock) {
            try {
                z = this.mBatterySaverEnabled && !this.mIsDeviceInteractive;
            } finally {
            }
        }
        return z;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.mBroadcastDispatcher.registerReceiver(this.mReceiver, intentFilter);
        refreshStatus();
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        this.mBroadcastDispatcher.unregisterReceiver(this.mReceiver);
    }

    public final void refreshStatus() {
        synchronized (this.mLock) {
            this.mBatterySaverEnabled = this.mPowerManager.getPowerSaveState(13).batterySaverEnabled;
            this.mIsDeviceInteractive = this.mPowerManager.isInteractive();
        }
    }
}
