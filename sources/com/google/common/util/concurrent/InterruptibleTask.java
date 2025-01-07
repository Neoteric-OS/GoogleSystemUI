package com.google.common.util.concurrent;

import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import com.google.common.util.concurrent.TrustedListenableFutureTask;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
abstract class InterruptibleTask extends AtomicReference implements Runnable {
    public static final DoNothingRunnable DONE = new DoNothingRunnable();
    public static final DoNothingRunnable PARKED = new DoNothingRunnable();

    @Override // java.lang.Runnable
    public final void run() {
        Thread currentThread = Thread.currentThread();
        Object obj = null;
        if (compareAndSet(null, currentThread)) {
            TrustedListenableFutureTask.TrustedFutureInterruptibleTask trustedFutureInterruptibleTask = (TrustedListenableFutureTask.TrustedFutureInterruptibleTask) this;
            boolean isDone = trustedFutureInterruptibleTask.this$0.isDone();
            DoNothingRunnable doNothingRunnable = DONE;
            if (!isDone) {
                try {
                    obj = runInterruptibly();
                } catch (Throwable th) {
                    try {
                        if (th instanceof InterruptedException) {
                            Thread.currentThread().interrupt();
                        }
                        if (!compareAndSet(currentThread, doNothingRunnable)) {
                            waitForInterrupt(currentThread);
                        }
                        if (isDone) {
                            return;
                        }
                        trustedFutureInterruptibleTask.this$0.setException(th);
                        return;
                    } finally {
                        if (!compareAndSet(currentThread, doNothingRunnable)) {
                            waitForInterrupt(currentThread);
                        }
                        if (!isDone) {
                            trustedFutureInterruptibleTask.this$0.set(null);
                        }
                    }
                }
            }
        }
    }

    public abstract Object runInterruptibly();

    public abstract String toPendingString();

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable instanceof Blocker) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, ", ");
        m.append(toPendingString());
        return m.toString();
    }

    public final void waitForInterrupt(Thread thread) {
        Runnable runnable = (Runnable) get();
        Blocker blocker = null;
        boolean z = false;
        int i = 0;
        while (true) {
            boolean z2 = runnable instanceof Blocker;
            DoNothingRunnable doNothingRunnable = PARKED;
            if (!z2 && runnable != doNothingRunnable) {
                break;
            }
            if (z2) {
                blocker = (Blocker) runnable;
            }
            i++;
            if (i <= 1000) {
                Thread.yield();
            } else if (runnable == doNothingRunnable || compareAndSet(runnable, doNothingRunnable)) {
                z = Thread.interrupted() || z;
                LockSupport.park(blocker);
            }
            runnable = (Runnable) get();
        }
        if (z) {
            thread.interrupt();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Blocker extends AbstractOwnableSynchronizer implements Runnable {
        private final InterruptibleTask task;

        public Blocker(TrustedListenableFutureTask.TrustedFutureInterruptibleTask trustedFutureInterruptibleTask) {
            this.task = trustedFutureInterruptibleTask;
        }

        public final String toString() {
            return this.task.toString();
        }

        @Override // java.lang.Runnable
        public final void run() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DoNothingRunnable implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
        }
    }
}
