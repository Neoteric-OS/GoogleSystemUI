package com.android.systemui.util.kotlin;

import android.os.Handler;
import dagger.internal.Provider;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.android.HandlerDispatcherKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SysUICoroutinesModule_SettingsBgDispatcherFactory implements Provider {
    public static HandlerContext settingsBgDispatcher(SysUICoroutinesModule sysUICoroutinesModule, Handler handler) {
        sysUICoroutinesModule.getClass();
        int i = HandlerDispatcherKt.$r8$clinit;
        return new HandlerContext(handler, "SettingsBg", false);
    }
}
