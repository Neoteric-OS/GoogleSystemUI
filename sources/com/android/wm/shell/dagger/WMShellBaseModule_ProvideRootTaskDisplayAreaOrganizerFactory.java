package com.android.wm.shell.dagger;

import android.content.Context;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.sysui.ShellInit;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideRootTaskDisplayAreaOrganizerFactory implements Provider {
    public static RootTaskDisplayAreaOrganizer provideRootTaskDisplayAreaOrganizer(Context context, ShellExecutor shellExecutor, ShellInit shellInit) {
        return new RootTaskDisplayAreaOrganizer(context, shellExecutor, shellInit);
    }
}
