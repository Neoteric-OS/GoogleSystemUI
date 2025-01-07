package com.android.systemui.media.controls.domain.pipeline;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDeviceLogger {
    public final LogBuffer buffer;

    public MediaDeviceLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logBroadcastEvent(String str, int i, int i2) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaDeviceLogger$logBroadcastEvent$2 mediaDeviceLogger$logBroadcastEvent$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaDeviceLogger$logBroadcastEvent$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return logMessage.getStr1() + ", reason = " + logMessage.getInt1() + ", broadcastId = " + logMessage.getInt2();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaDeviceLog", logLevel, mediaDeviceLogger$logBroadcastEvent$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.int1 = i;
        logMessageImpl.int2 = i2;
        logBuffer.commit(obtain);
    }
}
