package com.android.systemui.statusbar.notification.logging;

import android.app.StatsManager;
import android.util.Log;
import com.android.systemui.CoreStartable;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationMemoryMonitor implements CoreStartable {
    public final NotificationMemoryDumper notificationMemoryDumper;
    public final Lazy notificationMemoryLogger;

    public NotificationMemoryMonitor(NotificationMemoryDumper notificationMemoryDumper, Lazy lazy) {
        this.notificationMemoryDumper = notificationMemoryDumper;
        this.notificationMemoryLogger = lazy;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        Log.d("NotificationMemory", "NotificationMemoryMonitor initialized.");
        NotificationMemoryDumper notificationMemoryDumper = this.notificationMemoryDumper;
        notificationMemoryDumper.dumpManager.registerNormalDumpable("NotificationMemoryDumper", notificationMemoryDumper);
        Log.i("NotificationMemory", "Registered dumpable.");
        NotificationMemoryLogger notificationMemoryLogger = (NotificationMemoryLogger) this.notificationMemoryLogger.get();
        notificationMemoryLogger.statsManager.setPullAtomCallback(10174, (StatsManager.PullAtomMetadata) null, notificationMemoryLogger.backgroundExecutor, notificationMemoryLogger);
    }
}
