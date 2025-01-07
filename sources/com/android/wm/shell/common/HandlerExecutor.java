package com.android.wm.shell.common;

import android.os.Handler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HandlerExecutor implements ShellExecutor {
    public final Handler mHandler;

    public HandlerExecutor(Handler handler) {
        this.mHandler = handler;
    }

    public final void assertCurrentThread() {
        if (this.mHandler.getLooper().isCurrentThread()) {
            return;
        }
        throw new IllegalStateException("must be called on " + this.mHandler);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (this.mHandler.getLooper().isCurrentThread()) {
            runnable.run();
        } else {
            if (this.mHandler.post(runnable)) {
                return;
            }
            throw new RuntimeException(this.mHandler + " is probably exiting");
        }
    }

    public final void executeDelayed(Runnable runnable, long j) {
        if (this.mHandler.postDelayed(runnable, j)) {
            return;
        }
        throw new RuntimeException(this.mHandler + " is probably exiting");
    }

    public final void removeCallbacks(Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }
}
