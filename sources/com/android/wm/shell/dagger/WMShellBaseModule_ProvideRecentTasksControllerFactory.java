package com.android.wm.shell.dagger;

import android.R;
import android.app.ActivityTaskManager;
import android.content.Context;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.TaskStackListenerImpl;
import com.android.wm.shell.recents.RecentTasksController;
import com.android.wm.shell.recents.TaskStackTransitionObserver;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideRecentTasksControllerFactory implements Provider {
    public static Optional provideRecentTasksController(Context context, ShellInit shellInit, ShellController shellController, ShellCommandHandler shellCommandHandler, TaskStackListenerImpl taskStackListenerImpl, ActivityTaskManager activityTaskManager, Optional optional, TaskStackTransitionObserver taskStackTransitionObserver, ShellExecutor shellExecutor) {
        Optional ofNullable = Optional.ofNullable(!context.getResources().getBoolean(R.bool.config_imeDrawsImeNavBar) ? null : new RecentTasksController(context, shellInit, shellController, shellCommandHandler, taskStackListenerImpl, activityTaskManager, optional, taskStackTransitionObserver, shellExecutor));
        Preconditions.checkNotNullFromProvides(ofNullable);
        return ofNullable;
    }
}
