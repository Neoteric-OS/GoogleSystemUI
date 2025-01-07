package com.google.android.systemui.elmyra.gates;

import android.os.PowerManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class PowerState extends Gate {
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    public final PowerManager mPowerManager;

    public PowerState(Executor executor, PowerManager powerManager, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        super(executor);
        this.mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.google.android.systemui.elmyra.gates.PowerState.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onFinishedGoingToSleep(int i) {
                PowerState.this.notifyListener();
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onStartedWakingUp() {
                PowerState.this.notifyListener();
            }
        };
        this.mPowerManager = powerManager;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public boolean isBlocked() {
        return !this.mPowerManager.isInteractive();
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public void onActivate() {
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public void onDeactivate() {
        this.mKeyguardUpdateMonitor.removeCallback(this.mKeyguardUpdateMonitorCallback);
    }
}
