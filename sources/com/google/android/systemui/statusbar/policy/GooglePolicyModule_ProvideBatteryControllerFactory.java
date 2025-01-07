package com.google.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import android.os.PowerManager;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.BatteryControllerLogger;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.reversecharging.ReverseChargingController;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class GooglePolicyModule_ProvideBatteryControllerFactory implements Provider {
    public static BatteryControllerImplGoogle provideBatteryController(Context context, EnhancedEstimates enhancedEstimates, PowerManager powerManager, BroadcastDispatcher broadcastDispatcher, DemoModeController demoModeController, DumpManager dumpManager, BatteryControllerLogger batteryControllerLogger, Handler handler, Handler handler2, UserTracker userTracker, ReverseChargingController reverseChargingController, SecureSettings secureSettings, UserTracker userTracker2) {
        BatteryControllerImplGoogle batteryControllerImplGoogle = new BatteryControllerImplGoogle(context, enhancedEstimates, powerManager, broadcastDispatcher, demoModeController, dumpManager, batteryControllerLogger, handler, handler2, userTracker, reverseChargingController, secureSettings, userTracker2);
        batteryControllerImplGoogle.init$9();
        return batteryControllerImplGoogle;
    }
}
