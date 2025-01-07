package com.android.systemui.util.leak;

import com.android.systemui.dump.DumpManager;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class LeakModule_ProvidesLeakDetectorFactory implements Provider {
    public static LeakDetector providesLeakDetector(LeakModule leakModule, DumpManager dumpManager) {
        leakModule.getClass();
        return new LeakDetector(null, null, null, dumpManager);
    }
}
