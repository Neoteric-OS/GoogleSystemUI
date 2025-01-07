package com.android.systemui.util.wakelock;

import android.content.Context;
import android.os.PowerManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface WakeLock {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final Context mContext;
        public final WakeLockLogger mLogger;
        public String mTag;
        public int mLevelsAndFlags = 1;
        public long mMaxTimeout = 20000;

        public Builder(Context context, WakeLockLogger wakeLockLogger) {
            this.mContext = context;
            this.mLogger = wakeLockLogger;
        }

        public final WakeLock build() {
            Context context = this.mContext;
            String str = this.mTag;
            int i = this.mLevelsAndFlags;
            return WakeLock.wrap(WakeLock.createWakeLockInner(context, str, i), this.mLogger, this.mMaxTimeout);
        }
    }

    static PowerManager.WakeLock createWakeLockInner(Context context, String str, int i) {
        return ((PowerManager) context.getSystemService(PowerManager.class)).newWakeLock(i, str);
    }

    static WakeLock wrap(PowerManager.WakeLock wakeLock, WakeLockLogger wakeLockLogger, long j) {
        return new ClientTrackingWakeLock(wakeLock, wakeLockLogger, j);
    }

    void acquire(String str);

    void release(String str);

    Runnable wrap(Runnable runnable);
}
