package com.android.wm.shell.dagger;

import android.content.Context;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideDesktopTaskRepositoryFactory implements Provider {
    public static Optional provideDesktopTaskRepository(Context context, Optional optional) {
        Optional flatMap = optional.flatMap(new WMShellBaseModule$$ExternalSyntheticLambda0(1, context));
        Preconditions.checkNotNullFromProvides(flatMap);
        return flatMap;
    }
}
