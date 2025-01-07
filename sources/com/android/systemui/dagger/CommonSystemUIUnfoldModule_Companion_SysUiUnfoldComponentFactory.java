package com.android.systemui.dagger;

import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CommonSystemUIUnfoldModule_Companion_SysUiUnfoldComponentFactory implements Provider {
    public static Optional sysUiUnfoldComponent(Optional optional) {
        Optional optional2 = (Optional) (optional.isPresent() ? optional.get() : Optional.empty());
        Preconditions.checkNotNullFromProvides(optional2);
        return optional2;
    }
}
