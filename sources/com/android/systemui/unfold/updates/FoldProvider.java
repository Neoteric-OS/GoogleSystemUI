package com.android.systemui.unfold.updates;

import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface FoldProvider {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface FoldCallback {
        void onFoldUpdated(boolean z);
    }

    void registerCallback(FoldCallback foldCallback, Executor executor);

    void unregisterCallback(FoldCallback foldCallback);
}
