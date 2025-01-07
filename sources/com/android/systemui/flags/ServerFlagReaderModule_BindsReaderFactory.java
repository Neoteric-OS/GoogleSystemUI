package com.android.systemui.flags;

import com.android.systemui.util.DeviceConfigProxy;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ServerFlagReaderModule_BindsReaderFactory implements Provider {
    public static ServerFlagReaderImpl bindsReader(DeviceConfigProxy deviceConfigProxy, boolean z) {
        return new ServerFlagReaderImpl(deviceConfigProxy, z);
    }
}
