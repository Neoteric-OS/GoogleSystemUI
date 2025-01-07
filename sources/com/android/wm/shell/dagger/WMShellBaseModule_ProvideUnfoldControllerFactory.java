package com.android.wm.shell.dagger;

import com.android.wm.shell.unfold.ShellUnfoldProgressProvider;
import dagger.Lazy;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideUnfoldControllerFactory implements Provider {
    public static Optional provideUnfoldController(Lazy lazy, Optional optional) {
        Optional empty = (!optional.isPresent() || optional.get() == ShellUnfoldProgressProvider.NO_PROVIDER) ? Optional.empty() : (Optional) lazy.get();
        Preconditions.checkNotNullFromProvides(empty);
        return empty;
    }
}
