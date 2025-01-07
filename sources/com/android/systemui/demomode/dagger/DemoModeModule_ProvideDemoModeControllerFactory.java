package com.android.systemui.demomode.dagger;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.demomode.DemoModeController$tracker$1;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.util.settings.GlobalSettings;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DemoModeModule_ProvideDemoModeControllerFactory implements Provider {
    public static DemoModeController provideDemoModeController(Context context, DumpManager dumpManager, GlobalSettings globalSettings, BroadcastDispatcher broadcastDispatcher) {
        DemoModeController demoModeController = new DemoModeController(context, dumpManager, globalSettings, broadcastDispatcher);
        if (demoModeController.initialized) {
            throw new IllegalStateException("Already initialized");
        }
        demoModeController.initialized = true;
        dumpManager.registerNormalDumpable("DemoModeController", demoModeController);
        DemoModeController$tracker$1 demoModeController$tracker$1 = demoModeController.tracker;
        demoModeController$tracker$1.startTracking();
        demoModeController.isInDemoMode = demoModeController$tracker$1.isInDemoMode;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.systemui.demo");
        BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, demoModeController.broadcastReceiver, intentFilter, null, UserHandle.ALL, 0, 20);
        return demoModeController;
    }
}
