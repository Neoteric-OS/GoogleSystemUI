package com.android.systemui.statusbar.policy;

import com.android.systemui.statusbar.policy.BatteryController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BatteryControllerImpl$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BatteryControllerImpl f$0;

    public /* synthetic */ BatteryControllerImpl$$ExternalSyntheticLambda1(BatteryControllerImpl batteryControllerImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = batteryControllerImpl;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        BatteryControllerImpl batteryControllerImpl = this.f$0;
        BatteryController.BatteryStateChangeCallback batteryStateChangeCallback = (BatteryController.BatteryStateChangeCallback) obj;
        switch (i) {
            case 0:
                batteryStateChangeCallback.onBatteryUnknownStateChanged(batteryControllerImpl.mStateUnknown);
                break;
            case 1:
                batteryStateChangeCallback.onPowerSaveChanged(batteryControllerImpl.mPowerSave);
                break;
            case 2:
                batteryStateChangeCallback.onWirelessChargingChanged(batteryControllerImpl.mWirelessCharging);
                break;
            case 3:
                batteryStateChangeCallback.onIsIncompatibleChargingChanged(batteryControllerImpl.mIsIncompatibleCharging);
                break;
            case 4:
                batteryStateChangeCallback.onBatteryLevelChanged(batteryControllerImpl.mLevel, batteryControllerImpl.mPluggedIn, batteryControllerImpl.mCharging);
                break;
            default:
                batteryStateChangeCallback.onIsBatteryDefenderChanged(batteryControllerImpl.mIsBatteryDefender);
                break;
        }
    }
}
