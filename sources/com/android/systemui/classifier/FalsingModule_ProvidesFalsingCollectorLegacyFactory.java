package com.android.systemui.classifier;

import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FalsingModule_ProvidesFalsingCollectorLegacyFactory implements Provider {
    public static FalsingCollector providesFalsingCollectorLegacy(Object obj) {
        FalsingCollectorImpl falsingCollectorImpl = (FalsingCollectorImpl) obj;
        Preconditions.checkNotNullFromProvides(falsingCollectorImpl);
        return falsingCollectorImpl;
    }
}
