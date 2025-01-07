package com.android.systemui.util.concurrency;

import android.os.Looper;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExecutionImpl {
    public final Looper mainLooper = Looper.getMainLooper();

    public final void assertIsMainThread() {
        if (!this.mainLooper.isCurrentThread()) {
            throw new IllegalStateException(GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("should be called from the main thread. Main thread name=", this.mainLooper.getThread().getName(), " Thread.currentThread()=", Thread.currentThread().getName()));
        }
    }
}
