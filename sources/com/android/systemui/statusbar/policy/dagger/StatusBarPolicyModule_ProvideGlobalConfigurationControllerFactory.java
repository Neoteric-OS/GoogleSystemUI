package com.android.systemui.statusbar.policy.dagger;

import android.content.Context;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$1;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarPolicyModule_ProvideGlobalConfigurationControllerFactory implements Provider {
    public static ConfigurationControllerImpl provideGlobalConfigurationController(Context context, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$1 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$1) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$1.getClass();
        return new ConfigurationControllerImpl(context);
    }
}
