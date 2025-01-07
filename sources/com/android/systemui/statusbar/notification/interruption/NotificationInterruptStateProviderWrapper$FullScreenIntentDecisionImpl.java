package com.android.systemui.statusbar.notification.interruption;

import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationInterruptStateProviderWrapper$FullScreenIntentDecisionImpl implements VisualInterruptionDecisionProvider.FullScreenIntentDecision {
    public final String logReason;

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.Decision
    public final String getLogReason() {
        return this.logReason;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.Decision
    public final boolean getShouldInterrupt() {
        return false;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.FullScreenIntentDecision
    public final boolean getWouldInterruptWithoutDnd() {
        return false;
    }
}
