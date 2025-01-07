package com.android.wm.shell.dagger;

import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInterface;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideShellSysuiCallbacksFactory implements Provider {
    public static ShellInterface provideShellSysuiCallbacks(ShellController shellController) {
        return shellController.mImpl;
    }
}
