package com.android.systemui.util.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExecutorImpl implements DelayableExecutor {
    public final Handler mHandler;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExecutionToken implements Runnable {
        public final Runnable runnable;

        public ExecutionToken(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ExecutorImpl.this.mHandler.removeCallbacksAndMessages(this);
        }
    }

    public ExecutorImpl(Looper looper) {
        this.mHandler = new Handler(looper, new Handler.Callback() { // from class: com.android.systemui.util.concurrency.ExecutorImpl$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                ExecutorImpl.this.getClass();
                if (message.what == 0) {
                    ((ExecutorImpl.ExecutionToken) message.obj).runnable.run();
                    return true;
                }
                throw new IllegalStateException("Unrecognized message: " + message.what);
            }
        });
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (this.mHandler.post(runnable)) {
            return;
        }
        throw new RejectedExecutionException(this.mHandler + " is shutting down");
    }

    public final ExecutionToken executeDelayed(Runnable runnable, long j, TimeUnit timeUnit) {
        ExecutionToken executionToken = new ExecutionToken(runnable);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, executionToken), timeUnit.toMillis(j));
        return executionToken;
    }
}
