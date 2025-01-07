package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;
import com.android.systemui.demomode.DemoMode;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface BatteryController extends DemoMode, CallbackController {
    default boolean isExtremeSaverOn() {
        return false;
    }

    default boolean isReverseOn() {
        return false;
    }

    default boolean isReverseSupported() {
        return false;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface BatteryStateChangeCallback extends Dumpable {
        @Override // com.android.systemui.Dumpable
        default void dump(PrintWriter printWriter, String[] strArr) {
            printWriter.println(this);
        }

        default void onBatteryUnknownStateChanged(boolean z) {
        }

        default void onExtremeBatterySaverChanged(boolean z) {
        }

        default void onIsBatteryDefenderChanged(boolean z) {
        }

        default void onIsIncompatibleChargingChanged(boolean z) {
        }

        default void onPowerSaveChanged(boolean z) {
        }

        default void onWirelessChargingChanged(boolean z) {
        }

        default void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        }

        default void onReverseChanged(int i, String str, boolean z) {
        }
    }

    default void setReverseState(boolean z) {
    }
}
