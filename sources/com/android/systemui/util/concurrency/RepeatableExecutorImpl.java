package com.android.systemui.util.concurrency;

import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel$onDestroy$1;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RepeatableExecutorImpl implements Executor {
    public final DelayableExecutor mExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExecutionToken implements Runnable {
        public ExecutorImpl.ExecutionToken mCancel;
        public final SeekBarViewModel$onDestroy$1 mCommand;
        public final Object mLock = new Object();
        public final TimeUnit mUnit;

        public ExecutionToken(SeekBarViewModel$onDestroy$1 seekBarViewModel$onDestroy$1, TimeUnit timeUnit) {
            this.mCommand = seekBarViewModel$onDestroy$1;
            this.mUnit = timeUnit;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.mCommand.run();
            synchronized (this.mLock) {
                try {
                    if (this.mCancel != null) {
                        this.mCancel = ((ExecutorImpl) RepeatableExecutorImpl.this.mExecutor).executeDelayed(this, 100L, this.mUnit);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public RepeatableExecutorImpl(DelayableExecutor delayableExecutor) {
        this.mExecutor = delayableExecutor;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        ((ExecutorImpl) this.mExecutor).execute(runnable);
    }
}
