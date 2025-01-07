package com.android.systemui.log.echo;

import com.android.systemui.log.LogcatEchoTracker;
import com.android.systemui.log.core.LogLevel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LogcatEchoTrackerAlways implements LogcatEchoTracker {
    public static final LogcatEchoTrackerAlways INSTANCE = new LogcatEchoTrackerAlways();

    @Override // com.android.systemui.log.LogcatEchoTracker
    public final boolean isBufferLoggable(LogLevel logLevel, String str) {
        return true;
    }

    @Override // com.android.systemui.log.LogcatEchoTracker
    public final boolean isTagLoggable(LogLevel logLevel, String str) {
        return true;
    }
}
