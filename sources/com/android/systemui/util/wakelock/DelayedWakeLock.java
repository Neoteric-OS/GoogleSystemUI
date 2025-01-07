package com.android.systemui.util.wakelock;

import android.content.Context;
import android.os.Handler;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DelayedWakeLock implements WakeLock {
    public final Handler mHandler;
    public final WakeLock mInner;

    public DelayedWakeLock(Lazy lazy, Context context, WakeLockLogger wakeLockLogger, String str) {
        this.mInner = WakeLock.wrap(WakeLock.createWakeLockInner(context, str, 1), wakeLockLogger, 20000L);
        this.mHandler = (Handler) lazy.get();
    }

    @Override // com.android.systemui.util.wakelock.WakeLock
    public final void acquire(String str) {
        this.mInner.acquire(str);
    }

    @Override // com.android.systemui.util.wakelock.WakeLock
    public final void release(final String str) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.android.systemui.util.wakelock.DelayedWakeLock$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DelayedWakeLock delayedWakeLock = DelayedWakeLock.this;
                delayedWakeLock.mInner.release(str);
            }
        }, 100L);
    }

    public final String toString() {
        return "[DelayedWakeLock] " + this.mInner;
    }

    @Override // com.android.systemui.util.wakelock.WakeLock
    public final Runnable wrap(Runnable runnable) {
        acquire("wrap");
        return new WakeLock$$ExternalSyntheticLambda0(runnable, this);
    }
}
