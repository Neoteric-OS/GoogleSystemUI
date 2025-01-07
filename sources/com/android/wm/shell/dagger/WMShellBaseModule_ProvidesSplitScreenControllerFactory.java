package com.android.wm.shell.dagger;

import android.app.ActivityTaskManager;
import android.content.Context;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvidesSplitScreenControllerFactory implements Provider {
    public static Optional providesSplitScreenController(Context context, Optional optional) {
        if (!ActivityTaskManager.supportsSplitScreenMultiWindow(context)) {
            optional = Optional.empty();
        }
        Preconditions.checkNotNullFromProvides(optional);
        return optional;
    }
}
