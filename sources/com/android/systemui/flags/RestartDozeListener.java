package com.android.systemui.flags;

import android.os.PowerManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.time.SystemClock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RestartDozeListener {
    public final DelayableExecutor bgExecutor;
    public boolean inited;
    public final RestartDozeListener$listener$1 listener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.flags.RestartDozeListener$listener$1
        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozingChanged(final boolean z) {
            final RestartDozeListener restartDozeListener = RestartDozeListener.this;
            restartDozeListener.getClass();
            ((ExecutorImpl) restartDozeListener.bgExecutor).execute(new Runnable() { // from class: com.android.systemui.flags.RestartDozeListener$storeSleepState$1
                @Override // java.lang.Runnable
                public final void run() {
                    SecureSettings secureSettings = RestartDozeListener.this.settings;
                    boolean z2 = z;
                    secureSettings.putIntForUser("restart_nap_after_start", z2 ? 1 : 0, secureSettings.getUserId());
                }
            });
        }
    };
    public final PowerManager powerManager;
    public final SecureSettings settings;
    public final StatusBarStateController statusBarStateController;
    public final SystemClock systemClock;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.flags.RestartDozeListener$listener$1] */
    public RestartDozeListener(SecureSettings secureSettings, StatusBarStateController statusBarStateController, PowerManager powerManager, SystemClock systemClock, DelayableExecutor delayableExecutor) {
        this.settings = secureSettings;
        this.statusBarStateController = statusBarStateController;
        this.powerManager = powerManager;
        this.systemClock = systemClock;
        this.bgExecutor = delayableExecutor;
    }
}
