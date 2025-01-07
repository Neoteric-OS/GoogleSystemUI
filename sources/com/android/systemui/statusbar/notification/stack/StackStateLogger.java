package com.android.systemui.statusbar.notification.stack;

import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtils;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StackStateLogger {
    public final LogBuffer buffer;
    public final LogBuffer notificationRenderBuffer;

    public StackStateLogger(LogBuffer logBuffer, LogBuffer logBuffer2) {
        this.buffer = logBuffer;
        this.notificationRenderBuffer = logBuffer2;
    }

    public final void animationEnd(String str, String str2, boolean z) {
        LogLevel logLevel = LogLevel.INFO;
        StackStateLogger$animationEnd$2 stackStateLogger$animationEnd$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.StackStateLogger$animationEnd$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str22 = logMessage.getStr2();
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Animation End, type: ", str22, ", notif key: ", str1, ", isHeadsUp: ");
                m.append(bool1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.notificationRenderBuffer;
        LogMessage obtain = logBuffer.obtain("StackScroll", logLevel, stackStateLogger$animationEnd$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtils.logKey(str);
        logMessageImpl.str2 = str2;
        logMessageImpl.bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void animationStart(String str, String str2, boolean z) {
        LogLevel logLevel = LogLevel.INFO;
        StackStateLogger$animationStart$2 stackStateLogger$animationStart$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.StackStateLogger$animationStart$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str22 = logMessage.getStr2();
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Animation Start, type: ", str22, ", notif key: ", str1, ", isHeadsUp: ");
                m.append(bool1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.notificationRenderBuffer;
        LogMessage obtain = logBuffer.obtain("StackScroll", logLevel, stackStateLogger$animationStart$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtils.logKey(str);
        logMessageImpl.str2 = str2;
        logMessageImpl.bool1 = z;
        logBuffer.commit(obtain);
    }
}
