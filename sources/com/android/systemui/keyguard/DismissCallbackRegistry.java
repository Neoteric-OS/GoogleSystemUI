package com.android.systemui.keyguard;

import android.util.Log;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DismissCallbackRegistry {
    public final ArrayList mDismissCallbacks = new ArrayList();
    public final Executor mUiBgExecutor;

    public DismissCallbackRegistry(Executor executor) {
        this.mUiBgExecutor = executor;
    }

    public final void notifyDismissCancelled() {
        Log.d("DismissCallbackRegistry", "notifyDismissCancelled(" + this.mDismissCallbacks.size() + ")");
        for (int size = this.mDismissCallbacks.size() + (-1); size >= 0; size--) {
            DismissCallbackWrapper dismissCallbackWrapper = (DismissCallbackWrapper) this.mDismissCallbacks.get(size);
            Executor executor = this.mUiBgExecutor;
            Objects.requireNonNull(dismissCallbackWrapper);
            executor.execute(new DismissCallbackRegistry$$ExternalSyntheticLambda0(dismissCallbackWrapper, 1));
        }
        this.mDismissCallbacks.clear();
    }
}
