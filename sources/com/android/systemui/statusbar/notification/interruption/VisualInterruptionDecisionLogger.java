package com.android.systemui.statusbar.notification.interruption;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VisualInterruptionDecisionLogger {
    public final LogBuffer buffer;

    public VisualInterruptionDecisionLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logCooldownSetting(boolean z) {
        LogLevel logLevel = LogLevel.INFO;
        VisualInterruptionDecisionLogger$logCooldownSetting$2 visualInterruptionDecisionLogger$logCooldownSetting$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionLogger$logCooldownSetting$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Cooldown enabled: ", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("VisualInterruptionDecisionProvider", logLevel, visualInterruptionDecisionLogger$logCooldownSetting$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logFullScreenIntentDecision(NotificationEntry notificationEntry, VisualInterruptionDecisionProvider.FullScreenIntentDecision fullScreenIntentDecision, boolean z) {
        LogLevel logLevel = z ? LogLevel.WARNING : LogLevel.DEBUG;
        VisualInterruptionDecisionLogger$logFullScreenIntentDecision$2 visualInterruptionDecisionLogger$logFullScreenIntentDecision$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionLogger$logFullScreenIntentDecision$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("FSI ", logMessage.getBool1() ? "allowed" : logMessage.getBool2() ? "suppressed only by DND" : "suppressed", ": ", logMessage.getStr1(), " (key="), logMessage.getStr2(), ")");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("VisualInterruptionDecisionProvider", logLevel, visualInterruptionDecisionLogger$logFullScreenIntentDecision$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = fullScreenIntentDecision.getShouldInterrupt();
        logMessageImpl.bool2 = fullScreenIntentDecision.getWouldInterruptWithoutDnd();
        logMessageImpl.str1 = fullScreenIntentDecision.getLogReason();
        logMessageImpl.str2 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
    }
}
