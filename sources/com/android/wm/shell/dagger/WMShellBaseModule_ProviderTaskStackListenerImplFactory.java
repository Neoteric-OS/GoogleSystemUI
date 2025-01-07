package com.android.wm.shell.dagger;

import android.os.Handler;
import com.android.wm.shell.common.TaskStackListenerImpl;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProviderTaskStackListenerImplFactory implements Provider {
    public static TaskStackListenerImpl providerTaskStackListenerImpl(Handler handler) {
        return new TaskStackListenerImpl(handler);
    }
}
