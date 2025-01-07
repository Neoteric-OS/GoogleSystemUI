package com.android.systemui.common.ui;

import android.content.Context;
import com.android.systemui.statusbar.policy.ConfigurationController;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ConfigurationStateModule_Companion_ProvideGlobalConfigurationStateFactory implements Provider {
    public static ConfigurationStateImpl provideGlobalConfigurationState(Context context, ConfigurationController configurationController) {
        return new ConfigurationStateImpl(context, configurationController);
    }
}
