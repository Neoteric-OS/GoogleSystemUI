package com.android.wm.shell.dagger;

import com.android.wm.shell.common.LaunchAdjacentController;
import com.android.wm.shell.common.SyncTransactionQueue;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideLaunchAdjacentControllerFactory implements Provider {
    public static LaunchAdjacentController provideLaunchAdjacentController(SyncTransactionQueue syncTransactionQueue) {
        return new LaunchAdjacentController(syncTransactionQueue);
    }
}
