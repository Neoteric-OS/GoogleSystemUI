package com.android.wm.shell.dagger;

import android.content.Context;
import android.os.Handler;
import com.android.wm.shell.R;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellConcurrencyModule_ProvideShellMainExecutorFactory implements Provider {
    public static ShellExecutor provideShellMainExecutor(Context context, Handler handler, ShellExecutor shellExecutor) {
        if (context.getResources().getBoolean(R.bool.config_enableShellMainThread)) {
            shellExecutor = new HandlerExecutor(handler);
        }
        Preconditions.checkNotNullFromProvides(shellExecutor);
        return shellExecutor;
    }
}
