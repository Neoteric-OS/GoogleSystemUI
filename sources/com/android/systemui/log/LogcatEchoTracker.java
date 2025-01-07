package com.android.systemui.log;

import com.android.systemui.log.core.LogLevel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface LogcatEchoTracker {
    boolean isBufferLoggable(LogLevel logLevel, String str);

    boolean isTagLoggable(LogLevel logLevel, String str);
}
