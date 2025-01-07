package com.android.systemui.shortcut;

import android.os.Handler;
import android.os.Message;
import com.android.internal.policy.IShortcutService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShortcutKeyServiceProxy extends IShortcutService.Stub {
    public final ShortcutKeyDispatcher mCallbacks;
    public final Object mLock = new Object();
    public final H mHandler = new H();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class H extends Handler {
        public H() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            ShortcutKeyDispatcher shortcutKeyDispatcher = ShortcutKeyServiceProxy.this.mCallbacks;
            ((Long) message.obj).longValue();
            int i = shortcutKeyDispatcher.mContext.getResources().getConfiguration().orientation;
        }
    }

    public ShortcutKeyServiceProxy(ShortcutKeyDispatcher shortcutKeyDispatcher) {
        this.mCallbacks = shortcutKeyDispatcher;
    }

    public final void notifyShortcutKeyPressed(long j) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1, Long.valueOf(j)).sendToTarget();
        }
    }
}
