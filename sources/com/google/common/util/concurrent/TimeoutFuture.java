package com.google.common.util.concurrent;

import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TimeoutFuture extends FluentFuture.TrustedFuture {
    public SettableFuture delegateRef;
    public ScheduledFuture timer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Fire implements Runnable {
        public TimeoutFuture timeoutFutureRef;

        @Override // java.lang.Runnable
        public final void run() {
            SettableFuture settableFuture;
            TimeoutFuture timeoutFuture = this.timeoutFutureRef;
            if (timeoutFuture == null || (settableFuture = timeoutFuture.delegateRef) == null) {
                return;
            }
            this.timeoutFutureRef = null;
            if (settableFuture.isDone()) {
                timeoutFuture.setFuture(settableFuture);
                return;
            }
            try {
                ScheduledFuture scheduledFuture = timeoutFuture.timer;
                timeoutFuture.timer = null;
                String str = "Timed out";
                int i = 0;
                if (scheduledFuture != null) {
                    try {
                        long abs = Math.abs(scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
                        if (abs > 10) {
                            str = "Timed out (timeout delayed by " + abs + " ms after scheduled time)";
                        }
                    } catch (Throwable th) {
                        timeoutFuture.setException(new TimeoutFutureException(str, i));
                        throw th;
                    }
                }
                timeoutFuture.setException(new TimeoutFutureException(str + ": " + settableFuture, i));
            } finally {
                settableFuture.cancel(true);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TimeoutFutureException extends TimeoutException {
        public /* synthetic */ TimeoutFutureException(String str, int i) {
            this(str);
        }

        @Override // java.lang.Throwable
        public final synchronized Throwable fillInStackTrace() {
            setStackTrace(new StackTraceElement[0]);
            return this;
        }

        private TimeoutFutureException(String str) {
            super(str);
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.delegateRef);
        ScheduledFuture scheduledFuture = this.timer;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.delegateRef = null;
        this.timer = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        SettableFuture settableFuture = this.delegateRef;
        ScheduledFuture scheduledFuture = this.timer;
        if (settableFuture == null) {
            return null;
        }
        String str = "inputFuture=[" + settableFuture + "]";
        if (scheduledFuture == null) {
            return str;
        }
        long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= 0) {
            return str;
        }
        return str + ", remaining delay=[" + delay + " ms]";
    }
}
