package com.android.systemui.util.wakelock;

import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SettableWakeLock {
    public boolean mAcquired;
    public final WakeLock mInner;
    public final String mWhy;

    public SettableWakeLock(WakeLock wakeLock, String str) {
        Objects.requireNonNull(wakeLock, "inner wakelock required");
        this.mInner = wakeLock;
        this.mWhy = str;
    }

    public final synchronized void setAcquired(boolean z) {
        try {
            if (this.mAcquired != z) {
                if (z) {
                    this.mInner.acquire(this.mWhy);
                } else {
                    this.mInner.release(this.mWhy);
                }
                this.mAcquired = z;
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
