package com.android.systemui.settings;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DataItem {
    public final WeakReference callback;
    public final Executor executor;

    public DataItem(WeakReference weakReference, Executor executor) {
        this.callback = weakReference;
        this.executor = executor;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataItem)) {
            return false;
        }
        DataItem dataItem = (DataItem) obj;
        return Intrinsics.areEqual(this.callback, dataItem.callback) && Intrinsics.areEqual(this.executor, dataItem.executor);
    }

    public final int hashCode() {
        return this.executor.hashCode() + (this.callback.hashCode() * 31);
    }

    public final String toString() {
        return "DataItem(callback=" + this.callback + ", executor=" + this.executor + ")";
    }
}
