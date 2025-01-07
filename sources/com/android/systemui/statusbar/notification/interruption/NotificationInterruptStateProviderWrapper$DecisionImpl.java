package com.android.systemui.statusbar.notification.interruption;

import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationInterruptStateProviderWrapper$DecisionImpl implements VisualInterruptionDecisionProvider.Decision {
    public static final /* synthetic */ NotificationInterruptStateProviderWrapper$DecisionImpl[] $VALUES;
    public static final NotificationInterruptStateProviderWrapper$DecisionImpl SHOULD_INTERRUPT = null;
    public static final NotificationInterruptStateProviderWrapper$DecisionImpl SHOULD_NOT_INTERRUPT = null;
    private final String logReason = "unknown";
    private final boolean shouldInterrupt;

    static {
        NotificationInterruptStateProviderWrapper$DecisionImpl[] notificationInterruptStateProviderWrapper$DecisionImplArr = {new NotificationInterruptStateProviderWrapper$DecisionImpl(0, "SHOULD_INTERRUPT", true), new NotificationInterruptStateProviderWrapper$DecisionImpl(1, "SHOULD_NOT_INTERRUPT", false)};
        $VALUES = notificationInterruptStateProviderWrapper$DecisionImplArr;
        EnumEntriesKt.enumEntries(notificationInterruptStateProviderWrapper$DecisionImplArr);
    }

    public NotificationInterruptStateProviderWrapper$DecisionImpl(int i, String str, boolean z) {
        this.shouldInterrupt = z;
    }

    public static NotificationInterruptStateProviderWrapper$DecisionImpl valueOf(String str) {
        return (NotificationInterruptStateProviderWrapper$DecisionImpl) Enum.valueOf(NotificationInterruptStateProviderWrapper$DecisionImpl.class, str);
    }

    public static NotificationInterruptStateProviderWrapper$DecisionImpl[] values() {
        return (NotificationInterruptStateProviderWrapper$DecisionImpl[]) $VALUES.clone();
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.Decision
    public final String getLogReason() {
        return this.logReason;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider.Decision
    public final boolean getShouldInterrupt() {
        return this.shouldInterrupt;
    }
}
