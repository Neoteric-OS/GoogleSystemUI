package com.android.systemui.deviceentry;

import com.android.keyguard.LockIconViewController;
import dagger.Lazy;
import dagger.internal.Provider;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DeviceEntryModule_Companion_ProvideLockIconViewControllerFactory implements Provider {
    public static LockIconViewController provideLockIconViewController(Lazy lazy) {
        Object obj = lazy.get();
        Intrinsics.checkNotNull(obj);
        return (LockIconViewController) obj;
    }
}
