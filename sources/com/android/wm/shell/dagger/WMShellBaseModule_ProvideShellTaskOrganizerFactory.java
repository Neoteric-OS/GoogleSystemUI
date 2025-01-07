package com.android.wm.shell.dagger;

import android.content.Context;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.compatui.api.CompatUIHandler;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellInit;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideShellTaskOrganizerFactory implements Provider {
    public static ShellTaskOrganizer provideShellTaskOrganizer(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, Optional optional, Optional optional2, Optional optional3, ShellExecutor shellExecutor) {
        if (!context.getResources().getBoolean(R.bool.config_registerShellTaskOrganizerOnInit)) {
            shellInit = new ShellInit();
        }
        return new ShellTaskOrganizer(shellInit, shellCommandHandler, null, (CompatUIHandler) optional.orElse(null), optional2, optional3, shellExecutor);
    }
}
