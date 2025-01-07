package com.android.systemui.util.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import com.android.systemui.util.Assert;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UiThreadContext {
    public final Choreographer choreographer;
    public final Executor executor;
    public final Handler handler;
    public final Looper looper;

    public UiThreadContext(Looper looper, Handler handler, Executor executor, Choreographer choreographer) {
        this.looper = looper;
        this.handler = handler;
        this.executor = executor;
        this.choreographer = choreographer;
    }

    public final void isCurrentThread() {
        Looper looper = this.looper;
        Looper looper2 = Assert.sMainLooper;
        if (looper.isCurrentThread()) {
            return;
        }
        Thread thread = Assert.sTestThread;
        if (thread == null || thread != Thread.currentThread()) {
            throw new IllegalStateException("Called on wrong thread thread. wanted " + looper.getThread().getName() + " but instead got Thread.currentThread()=" + Thread.currentThread().getName());
        }
    }
}
