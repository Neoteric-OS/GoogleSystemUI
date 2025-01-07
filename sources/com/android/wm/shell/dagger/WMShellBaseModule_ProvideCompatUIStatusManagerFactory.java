package com.android.wm.shell.dagger;

import com.android.wm.shell.compatui.CompatUIStatusManager;
import com.android.wm.shell.compatui.CompatUIStatusManager$$ExternalSyntheticLambda0;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideCompatUIStatusManagerFactory implements Provider {
    public static CompatUIStatusManager provideCompatUIStatusManager() {
        return new CompatUIStatusManager(new CompatUIStatusManager$$ExternalSyntheticLambda0());
    }
}
