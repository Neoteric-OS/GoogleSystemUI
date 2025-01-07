package com.android.systemui.statusbar.dagger;

import android.content.Context;
import com.android.systemui.dump.DumpHandler;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import dagger.Lazy;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CentralSurfacesDependenciesModule_ProvideCommandQueueFactory implements Provider {
    public static CommandQueue provideCommandQueue(Context context, DisplayTracker displayTracker, CommandRegistry commandRegistry, DumpHandler dumpHandler, Lazy lazy) {
        return new CommandQueue(context, displayTracker, commandRegistry, dumpHandler, lazy);
    }
}
