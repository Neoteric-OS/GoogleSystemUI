package com.google.android.systemui.dagger;

import android.database.ContentObserver;
import android.net.Uri;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl$observer$1;
import com.android.systemui.util.settings.GlobalSettings;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SystemUIGoogleModule_ProvideDeviceProvisionedControllerFactory implements Provider {
    public static void provideDeviceProvisionedController(DeviceProvisionedControllerImpl deviceProvisionedControllerImpl) {
        if (deviceProvisionedControllerImpl.initted.compareAndSet(false, true)) {
            deviceProvisionedControllerImpl.dumpManager.registerDumpable(deviceProvisionedControllerImpl);
            deviceProvisionedControllerImpl.updateValues(-1, true);
            ((UserTrackerImpl) deviceProvisionedControllerImpl.userTracker).addCallback(deviceProvisionedControllerImpl.userChangedCallback, deviceProvisionedControllerImpl.backgroundExecutor);
            Uri uri = deviceProvisionedControllerImpl.deviceProvisionedUri;
            GlobalSettings globalSettings = deviceProvisionedControllerImpl.globalSettings;
            DeviceProvisionedControllerImpl$observer$1 deviceProvisionedControllerImpl$observer$1 = deviceProvisionedControllerImpl.observer;
            globalSettings.registerContentObserverSync(uri, false, deviceProvisionedControllerImpl$observer$1);
            deviceProvisionedControllerImpl.secureSettings.registerContentObserverForUserSync(deviceProvisionedControllerImpl.userSetupUri, false, (ContentObserver) deviceProvisionedControllerImpl$observer$1, -1);
        }
    }
}
