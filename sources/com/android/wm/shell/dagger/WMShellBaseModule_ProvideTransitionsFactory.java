package com.android.wm.shell.dagger;

import android.content.Context;
import android.os.Handler;
import com.android.wm.shell.R;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.HomeTransitionObserver;
import com.android.wm.shell.transition.Transitions;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideTransitionsFactory implements Provider {
    public static Transitions provideTransitions(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, ShellController shellController, ShellTaskOrganizer shellTaskOrganizer, TransactionPool transactionPool, DisplayController displayController, ShellExecutor shellExecutor, Handler handler, ShellExecutor shellExecutor2, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, HomeTransitionObserver homeTransitionObserver) {
        return new Transitions(context, !context.getResources().getBoolean(R.bool.config_registerShellTransitionsOnInit) ? new ShellInit() : shellInit, shellCommandHandler, shellController, shellTaskOrganizer, transactionPool, displayController, shellExecutor, handler, shellExecutor2, rootTaskDisplayAreaOrganizer, homeTransitionObserver);
    }
}
