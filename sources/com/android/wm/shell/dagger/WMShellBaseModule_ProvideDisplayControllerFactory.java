package com.android.wm.shell.dagger;

import android.content.Context;
import android.view.IWindowManager;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.sysui.ShellInit;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideDisplayControllerFactory implements Provider {
    public static DisplayController provideDisplayController(Context context, IWindowManager iWindowManager, ShellInit shellInit, ShellExecutor shellExecutor) {
        return new DisplayController(context, iWindowManager, shellInit, shellExecutor);
    }
}
