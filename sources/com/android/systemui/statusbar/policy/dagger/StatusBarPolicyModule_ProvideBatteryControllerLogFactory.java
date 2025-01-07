package com.android.systemui.statusbar.policy.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarPolicyModule_ProvideBatteryControllerLogFactory implements Provider {
    public static LogBuffer provideBatteryControllerLog(LogBufferFactory logBufferFactory) {
        return logBufferFactory.create(30, "BatteryControllerLog");
    }
}
