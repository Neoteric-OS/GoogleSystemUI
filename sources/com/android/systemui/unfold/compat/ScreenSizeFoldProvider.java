package com.android.systemui.unfold.compat;

import android.content.res.Configuration;
import com.android.systemui.unfold.updates.FoldProvider;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenSizeFoldProvider implements FoldProvider {
    public List callbacks;
    public boolean isFolded;

    public final void onConfigurationChange(Configuration configuration) {
        boolean z = configuration.smallestScreenWidthDp < 600;
        if (z == this.isFolded) {
            return;
        }
        this.isFolded = z;
        Iterator it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((FoldProvider.FoldCallback) it.next()).onFoldUpdated(this.isFolded);
        }
    }

    @Override // com.android.systemui.unfold.updates.FoldProvider
    public final void registerCallback(FoldProvider.FoldCallback foldCallback, Executor executor) {
        this.callbacks.add(foldCallback);
        foldCallback.onFoldUpdated(this.isFolded);
    }

    @Override // com.android.systemui.unfold.updates.FoldProvider
    public final void unregisterCallback(FoldProvider.FoldCallback foldCallback) {
        this.callbacks.remove(foldCallback);
    }
}
