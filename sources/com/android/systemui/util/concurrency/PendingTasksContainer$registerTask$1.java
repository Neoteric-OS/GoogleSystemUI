package com.android.systemui.util.concurrency;

import android.os.Trace;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PendingTasksContainer$registerTask$1 implements Runnable {
    public final /* synthetic */ String $name;
    public final /* synthetic */ PendingTasksContainer this$0;

    public PendingTasksContainer$registerTask$1(String str, PendingTasksContainer pendingTasksContainer) {
        this.$name = str;
        this.this$0 = pendingTasksContainer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Runnable runnable;
        Trace.endAsyncSection("PendingTasksContainer#" + this.$name, 0);
        if (this.this$0.pendingTasksCount.decrementAndGet() != 0 || (runnable = (Runnable) this.this$0.completionCallback.getAndSet(null)) == null) {
            return;
        }
        runnable.run();
    }
}
