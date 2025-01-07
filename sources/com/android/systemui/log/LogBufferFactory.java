package com.android.systemui.log;

import android.app.ActivityManager;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.dump.DumpsysEntry;
import com.android.systemui.log.echo.LogcatEchoTrackerAlways;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LogBufferFactory {
    public final DumpManager dumpManager;
    public final LogcatEchoTracker logcatEchoTracker;

    public LogBufferFactory(DumpManager dumpManager, LogcatEchoTracker logcatEchoTracker) {
        this.dumpManager = dumpManager;
        this.logcatEchoTracker = logcatEchoTracker;
    }

    public static /* synthetic */ LogBuffer create$default(LogBufferFactory logBufferFactory, String str, int i, boolean z, int i2) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        return logBufferFactory.create(i, str, z, false);
    }

    public final LogBuffer create(int i, String str) {
        return create$default(this, str, i, false, 12);
    }

    public final LogBuffer create(int i, String str, boolean z, boolean z2) {
        LogcatEchoTracker logcatEchoTracker = z2 ? LogcatEchoTrackerAlways.INSTANCE : this.logcatEchoTracker;
        if (ActivityManager.isLowRamDeviceStatic()) {
            i = Math.min(i, 20);
        }
        LogBuffer logBuffer = new LogBuffer(str, i, logcatEchoTracker, z);
        DumpManager dumpManager = this.dumpManager;
        synchronized (dumpManager) {
            if (!dumpManager.canAssignToNameLocked(logBuffer, str)) {
                throw new IllegalArgumentException("'" + str + "' is already registered");
            }
            dumpManager.buffers.put(str, new DumpsysEntry.LogBufferEntry(logBuffer, str));
        }
        return logBuffer;
    }
}
