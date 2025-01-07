package com.android.systemui.statusbar.policy.dagger;

import android.os.UserManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.connectivity.AccessPointControllerImpl;
import com.android.systemui.statusbar.connectivity.WifiPickerTrackerFactory;
import dagger.internal.Provider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarPolicyModule_ProvideAccessPointControllerImplFactory implements Provider {
    public static AccessPointControllerImpl provideAccessPointControllerImpl(UserManager userManager, UserTracker userTracker, Executor executor, WifiPickerTrackerFactory wifiPickerTrackerFactory) {
        AccessPointControllerImpl accessPointControllerImpl = new AccessPointControllerImpl(userManager, userTracker, executor, wifiPickerTrackerFactory);
        if (accessPointControllerImpl.mWifiPickerTracker == null) {
            accessPointControllerImpl.mWifiPickerTracker = accessPointControllerImpl.mWifiPickerTrackerFactory.create(accessPointControllerImpl.mLifecycle, accessPointControllerImpl, "AccessPointController");
        }
        return accessPointControllerImpl;
    }
}
