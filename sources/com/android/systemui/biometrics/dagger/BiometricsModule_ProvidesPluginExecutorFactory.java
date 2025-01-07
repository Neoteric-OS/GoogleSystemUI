package com.android.systemui.biometrics.dagger;

import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricsModule_ProvidesPluginExecutorFactory implements Provider {
    public static ExecutorImpl providesPluginExecutor(ThreadFactoryImpl threadFactoryImpl) {
        return threadFactoryImpl.buildExecutorOnNewThread("biometrics");
    }
}
