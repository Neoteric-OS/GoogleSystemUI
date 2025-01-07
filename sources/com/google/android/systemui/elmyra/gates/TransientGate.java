package com.google.android.systemui.elmyra.gates;

import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TransientGate extends Gate {
    public final long mBlockDuration;
    public ExecutorImpl.ExecutionToken mCancelReset;
    public final DelayableExecutor mExecutor;
    public boolean mIsBlocking;
    public final AnonymousClass1 mResetGate;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.systemui.elmyra.gates.TransientGate$1] */
    public TransientGate(DelayableExecutor delayableExecutor, long j) {
        super(delayableExecutor);
        this.mResetGate = new Runnable() { // from class: com.google.android.systemui.elmyra.gates.TransientGate.1
            @Override // java.lang.Runnable
            public final void run() {
                TransientGate transientGate = TransientGate.this;
                transientGate.mIsBlocking = false;
                transientGate.notifyListener();
            }
        };
        this.mBlockDuration = j;
        this.mExecutor = delayableExecutor;
    }

    public final void block() {
        this.mIsBlocking = true;
        notifyListener();
        ExecutorImpl.ExecutionToken executionToken = this.mCancelReset;
        if (executionToken != null) {
            executionToken.run();
            this.mCancelReset = null;
        }
        this.mCancelReset = this.mExecutor.executeDelayed(this.mResetGate, this.mBlockDuration);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        return this.mIsBlocking;
    }
}
