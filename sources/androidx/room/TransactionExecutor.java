package androidx.room;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransactionExecutor implements Executor {
    public Runnable active;
    public final Executor executor;
    public final ArrayDeque tasks = new ArrayDeque();
    public final Object syncLock = new Object();

    public TransactionExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(final Runnable runnable) {
        synchronized (this.syncLock) {
            this.tasks.offer(new Runnable() { // from class: androidx.room.TransactionExecutor$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Runnable runnable2 = runnable;
                    TransactionExecutor transactionExecutor = this;
                    try {
                        runnable2.run();
                    } finally {
                        transactionExecutor.scheduleNext();
                    }
                }
            });
            if (this.active == null) {
                scheduleNext();
            }
        }
    }

    public final void scheduleNext() {
        synchronized (this.syncLock) {
            Object poll = this.tasks.poll();
            Runnable runnable = (Runnable) poll;
            this.active = runnable;
            if (poll != null) {
                this.executor.execute(runnable);
            }
        }
    }
}
