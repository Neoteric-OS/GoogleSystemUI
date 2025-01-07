package com.android.systemui.statusbar.pipeline.dagger;

import android.net.wifi.WifiManager;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.RealWifiRepository;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.DisabledWifiRepository;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarPipelineModule_Companion_ProvideRealWifiRepositoryFactory implements Provider {
    public static RealWifiRepository provideRealWifiRepository(WifiManager wifiManager, DisabledWifiRepository disabledWifiRepository, WifiRepositoryImpl.Factory factory) {
        if (wifiManager == null) {
            return disabledWifiRepository;
        }
        return new WifiRepositoryImpl(factory.scope, factory.mainExecutor, factory.bgDispatcher, factory.wifiPickerTrackerFactory, wifiManager, factory.inputLogger, factory.tableLogger);
    }
}
