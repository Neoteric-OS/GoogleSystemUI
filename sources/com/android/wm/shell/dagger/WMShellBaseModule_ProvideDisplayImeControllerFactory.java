package com.android.wm.shell.dagger;

import android.view.IWindowManager;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.sysui.ShellInit;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideDisplayImeControllerFactory implements Provider {
    public static DisplayImeController provideDisplayImeController(IWindowManager iWindowManager, ShellInit shellInit, DisplayController displayController, DisplayInsetsController displayInsetsController, TransactionPool transactionPool) {
        return new DisplayImeController(iWindowManager, shellInit, displayController, displayInsetsController, transactionPool);
    }
}
