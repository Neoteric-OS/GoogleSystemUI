package com.android.wm.shell.dagger;

import android.view.IWindowManager;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.draganddrop.GlobalDragListener;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellModule_ProvideGlobalDragListenerFactory implements Provider {
    public static GlobalDragListener provideGlobalDragListener(IWindowManager iWindowManager, ShellExecutor shellExecutor) {
        return new GlobalDragListener(iWindowManager, shellExecutor);
    }
}
