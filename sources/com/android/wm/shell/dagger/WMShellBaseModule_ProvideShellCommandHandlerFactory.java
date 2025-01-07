package com.android.wm.shell.dagger;

import com.android.wm.shell.sysui.ShellCommandHandler;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideShellCommandHandlerFactory implements Provider {
    public static ShellCommandHandler provideShellCommandHandler() {
        return new ShellCommandHandler();
    }
}
