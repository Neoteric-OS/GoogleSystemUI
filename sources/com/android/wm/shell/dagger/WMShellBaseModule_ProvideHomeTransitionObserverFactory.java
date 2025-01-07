package com.android.wm.shell.dagger;

import android.content.Context;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.transition.HomeTransitionObserver;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideHomeTransitionObserverFactory implements Provider {
    public static HomeTransitionObserver provideHomeTransitionObserver(Context context, ShellExecutor shellExecutor) {
        return new HomeTransitionObserver(context, shellExecutor);
    }
}
