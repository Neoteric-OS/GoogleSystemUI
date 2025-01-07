package com.google.android.systemui.lowlightclock;

import android.content.Context;
import android.content.res.Resources;
import com.android.internal.app.IBatteryStats;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.fuelgauge.BatteryStatus;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChargingStatusProvider {
    public final IBatteryStats mBatteryInfo;
    public final BatteryState mBatteryState = new BatteryState();
    public LowLightClockDreamService$$ExternalSyntheticLambda0 mCallback;
    public ChargingStatusCallback mChargingStatusCallback;
    public final Context mContext;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final Resources mResources;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BatteryState {
        public BatteryStatus mBatteryStatus;

        public final boolean isChargingOrFull() {
            BatteryStatus batteryStatus;
            int i;
            return isValid() && ((i = (batteryStatus = this.mBatteryStatus).status) == 2 || BatteryStatus.isCharged(i, batteryStatus.level));
        }

        public final boolean isValid() {
            return this.mBatteryStatus != null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class ChargingStatusCallback extends KeyguardUpdateMonitorCallback {
        public ChargingStatusCallback() {
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onRefreshBatteryInfo(BatteryStatus batteryStatus) {
            ChargingStatusProvider chargingStatusProvider = ChargingStatusProvider.this;
            chargingStatusProvider.mBatteryState.mBatteryStatus = batteryStatus;
            chargingStatusProvider.reportStatusToCallback();
        }
    }

    public ChargingStatusProvider(Context context, Resources resources, IBatteryStats iBatteryStats, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.mContext = context;
        this.mResources = resources;
        this.mBatteryInfo = iBatteryStats;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x0157, code lost:
    
        if (r6 != false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0104, code lost:
    
        if (r6 != false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0106, code lost:
    
        r11 = com.android.wm.shell.R.string.keyguard_indication_charging_time;
     */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0169  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void reportStatusToCallback() {
        /*
            Method dump skipped, instructions count: 415
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.lowlightclock.ChargingStatusProvider.reportStatusToCallback():void");
    }
}
