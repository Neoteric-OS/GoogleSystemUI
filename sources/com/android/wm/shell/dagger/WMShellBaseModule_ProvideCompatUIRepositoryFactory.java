package com.android.wm.shell.dagger;

import com.android.wm.shell.compatui.api.CompatUISpec;
import com.android.wm.shell.compatui.components.RestartButtonSpecKt;
import com.android.wm.shell.compatui.impl.DefaultCompatUIRepository;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideCompatUIRepositoryFactory implements Provider {
    public static DefaultCompatUIRepository provideCompatUIRepository() {
        DefaultCompatUIRepository defaultCompatUIRepository = new DefaultCompatUIRepository();
        CompatUISpec compatUISpec = RestartButtonSpecKt.RestartButtonSpec;
        if (defaultCompatUIRepository.allSpecs.get("restartButton") != null) {
            throw new IllegalStateException("Spec with id:restartButton already present");
        }
        defaultCompatUIRepository.allSpecs.put("restartButton", compatUISpec);
        return defaultCompatUIRepository;
    }
}
