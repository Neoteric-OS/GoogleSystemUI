package com.android.systemui.statusbar.notification.interruption;

import com.android.systemui.CoreStartable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface VisualInterruptionDecisionProvider extends CoreStartable {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Decision {
        String getLogReason();

        boolean getShouldInterrupt();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface FullScreenIntentDecision extends Decision {
        boolean getWouldInterruptWithoutDnd();
    }

    void removeCondition(VisualInterruptionCondition visualInterruptionCondition);

    void removeFilter(VisualInterruptionFilter visualInterruptionFilter);

    void removeLegacySuppressor(NotificationInterruptSuppressor notificationInterruptSuppressor);
}
