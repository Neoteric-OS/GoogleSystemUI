package com.android.systemui.util.concurrency;

import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.RepeatableExecutorImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RepeatableExecutorImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ RepeatableExecutorImpl.ExecutionToken f$0;

    @Override // java.lang.Runnable
    public final void run() {
        RepeatableExecutorImpl.ExecutionToken executionToken = this.f$0;
        synchronized (executionToken.mLock) {
            try {
                ExecutorImpl.ExecutionToken executionToken2 = executionToken.mCancel;
                if (executionToken2 != null) {
                    executionToken2.run();
                    executionToken.mCancel = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
