package com.android.systemui.log.table;

import android.app.ActivityManager;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.dump.DumpsysEntry;
import com.android.systemui.log.LogcatEchoTracker;
import com.android.systemui.util.time.SystemClock;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TableLogBufferFactory {
    public final DumpManager dumpManager;
    public final Map existingBuffers = new LinkedHashMap();
    public final LogcatEchoTracker logcatEchoTracker;
    public final SystemClock systemClock;

    public TableLogBufferFactory(DumpManager dumpManager, SystemClock systemClock, LogcatEchoTracker logcatEchoTracker) {
        this.dumpManager = dumpManager;
        this.systemClock = systemClock;
        this.logcatEchoTracker = logcatEchoTracker;
    }

    public final TableLogBuffer create(int i, String str) {
        if (ActivityManager.isLowRamDeviceStatic()) {
            i = Math.min(i, 20);
        }
        TableLogBuffer tableLogBuffer = new TableLogBuffer(i, str, this.systemClock, this.logcatEchoTracker);
        DumpManager dumpManager = this.dumpManager;
        synchronized (dumpManager) {
            if (!dumpManager.canAssignToNameLocked(tableLogBuffer, str)) {
                throw new IllegalArgumentException("'" + str + "' is already registered");
            }
            dumpManager.tableLogBuffers.put(str, new DumpsysEntry.TableLogBufferEntry(tableLogBuffer, str));
        }
        return tableLogBuffer;
    }
}
