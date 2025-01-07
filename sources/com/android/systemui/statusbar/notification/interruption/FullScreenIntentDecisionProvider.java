package com.android.systemui.statusbar.notification.interruption;

import android.app.Notification;
import android.os.PowerManager;
import android.service.notification.StatusBarNotification;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.NotificationInterruptStateProviderImpl;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionSuppressor;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FullScreenIntentDecisionProvider {
    public final DeviceProvisionedController deviceProvisionedController;
    public final KeyguardStateController keyguardStateController;
    public final PowerManager powerManager;
    public final StatusBarStateController statusBarStateController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Decision {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class DecisionImpl implements Decision {
        public static final /* synthetic */ DecisionImpl[] $VALUES;
        public static final DecisionImpl FSI_DEVICE_DREAMING;
        public static final DecisionImpl FSI_DEVICE_NOT_INTERACTIVE;
        public static final DecisionImpl FSI_DEVICE_NOT_PROVISIONED;
        public static final DecisionImpl FSI_KEYGUARD_OCCLUDED;
        public static final DecisionImpl FSI_KEYGUARD_SHOWING;
        public static final DecisionImpl FSI_LOCKED_SHADE;
        public static final DecisionImpl FSI_USER_SETUP_INCOMPLETE;
        public static final DecisionImpl NO_FSI_EXPECTED_TO_HUN;
        public static final DecisionImpl NO_FSI_NOT_IMPORTANT_ENOUGH;
        public static final DecisionImpl NO_FSI_NO_FULL_SCREEN_INTENT;
        public static final DecisionImpl NO_FSI_NO_HUN_OR_KEYGUARD;
        public static final DecisionImpl NO_FSI_PACKAGE_SUSPENDED;
        public static final DecisionImpl NO_FSI_SHOW_STICKY_HUN;
        public static final DecisionImpl NO_FSI_SUPPRESSED_BY_DND;
        public static final DecisionImpl NO_FSI_SUPPRESSED_ONLY_BY_DND;
        public static final DecisionImpl NO_FSI_SUPPRESSIVE_BUBBLE_METADATA;
        public static final DecisionImpl NO_FSI_SUPPRESSIVE_GROUP_ALERT_BEHAVIOR;
        private final VisualInterruptionSuppressor.EventLogData eventLogData;
        private final boolean isWarning;
        private final String logReason;
        private final boolean shouldFsi;
        private final boolean shouldLog;
        private final boolean supersedesDnd;
        private final UiEventLogger.UiEventEnum uiEventId;
        private final boolean wouldFsiWithoutDnd;

        static {
            DecisionImpl decisionImpl = new DecisionImpl("NO_FSI_NO_FULL_SCREEN_INTENT", 0, false, "no full-screen intent", false, null, null, 228);
            NO_FSI_NO_FULL_SCREEN_INTENT = decisionImpl;
            DecisionImpl decisionImpl2 = new DecisionImpl("NO_FSI_SHOW_STICKY_HUN", 1, false, "full-screen intents are disabled", false, null, null, 244);
            NO_FSI_SHOW_STICKY_HUN = decisionImpl2;
            DecisionImpl decisionImpl3 = new DecisionImpl("NO_FSI_NOT_IMPORTANT_ENOUGH", 2, false, "not important enough", false, null, null, 252);
            NO_FSI_NOT_IMPORTANT_ENOUGH = decisionImpl3;
            DecisionImpl decisionImpl4 = new DecisionImpl("NO_FSI_SUPPRESSIVE_GROUP_ALERT_BEHAVIOR", 3, false, "suppressive group alert behavior", false, NotificationInterruptStateProviderImpl.NotificationInterruptEvent.FSI_SUPPRESSED_SUPPRESSIVE_GROUP_ALERT_BEHAVIOR, new VisualInterruptionSuppressor.EventLogData("231322873", "groupAlertBehavior"), 28);
            NO_FSI_SUPPRESSIVE_GROUP_ALERT_BEHAVIOR = decisionImpl4;
            DecisionImpl decisionImpl5 = new DecisionImpl("NO_FSI_SUPPRESSIVE_BUBBLE_METADATA", 4, false, "suppressive bubble metadata", false, NotificationInterruptStateProviderImpl.NotificationInterruptEvent.FSI_SUPPRESSED_SUPPRESSIVE_BUBBLE_METADATA, new VisualInterruptionSuppressor.EventLogData("274759612", "bubbleMetadata"), 28);
            NO_FSI_SUPPRESSIVE_BUBBLE_METADATA = decisionImpl5;
            DecisionImpl decisionImpl6 = new DecisionImpl("NO_FSI_SUPPRESSIVE_SILENT_NOTIFICATION", 5, false, "suppressive setSilent notification", false, null, null, 252);
            DecisionImpl decisionImpl7 = new DecisionImpl("NO_FSI_PACKAGE_SUSPENDED", 6, false, "package suspended", false, null, null, 252);
            NO_FSI_PACKAGE_SUSPENDED = decisionImpl7;
            DecisionImpl decisionImpl8 = new DecisionImpl("FSI_DEVICE_NOT_INTERACTIVE", 7, true, "device is not interactive", false, null, null, 252);
            FSI_DEVICE_NOT_INTERACTIVE = decisionImpl8;
            DecisionImpl decisionImpl9 = new DecisionImpl("FSI_DEVICE_DREAMING", 8, true, "device is dreaming", false, null, null, 252);
            FSI_DEVICE_DREAMING = decisionImpl9;
            DecisionImpl decisionImpl10 = new DecisionImpl("FSI_KEYGUARD_SHOWING", 9, true, "keyguard is showing", false, null, null, 252);
            FSI_KEYGUARD_SHOWING = decisionImpl10;
            DecisionImpl decisionImpl11 = new DecisionImpl("NO_FSI_EXPECTED_TO_HUN", 10, false, "expected to heads-up instead", false, null, null, 252);
            NO_FSI_EXPECTED_TO_HUN = decisionImpl11;
            DecisionImpl decisionImpl12 = new DecisionImpl("FSI_KEYGUARD_OCCLUDED", 11, true, "keyguard is occluded", false, null, null, 252);
            FSI_KEYGUARD_OCCLUDED = decisionImpl12;
            DecisionImpl decisionImpl13 = new DecisionImpl("FSI_LOCKED_SHADE", 12, true, "locked shade", false, null, null, 252);
            FSI_LOCKED_SHADE = decisionImpl13;
            DecisionImpl decisionImpl14 = new DecisionImpl("FSI_DEVICE_NOT_PROVISIONED", 13, true, "device not provisioned", false, null, null, 252);
            FSI_DEVICE_NOT_PROVISIONED = decisionImpl14;
            DecisionImpl decisionImpl15 = new DecisionImpl("FSI_USER_SETUP_INCOMPLETE", 14, true, "user setup incomplete", false, null, null, 252);
            FSI_USER_SETUP_INCOMPLETE = decisionImpl15;
            DecisionImpl decisionImpl16 = new DecisionImpl("NO_FSI_NO_HUN_OR_KEYGUARD", 15, false, "no HUN or keyguard", false, NotificationInterruptStateProviderImpl.NotificationInterruptEvent.FSI_SUPPRESSED_NO_HUN_OR_KEYGUARD, new VisualInterruptionSuppressor.EventLogData("231322873", "no hun or keyguard"), 28);
            NO_FSI_NO_HUN_OR_KEYGUARD = decisionImpl16;
            DecisionImpl decisionImpl17 = new DecisionImpl("NO_FSI_SUPPRESSED_BY_DND", 16, false, "suppressed by DND", false, null, null, 248);
            NO_FSI_SUPPRESSED_BY_DND = decisionImpl17;
            DecisionImpl decisionImpl18 = new DecisionImpl("NO_FSI_SUPPRESSED_ONLY_BY_DND", 17, false, "suppressed only by DND", true, null, null, 248);
            NO_FSI_SUPPRESSED_ONLY_BY_DND = decisionImpl18;
            DecisionImpl[] decisionImplArr = {decisionImpl, decisionImpl2, decisionImpl3, decisionImpl4, decisionImpl5, decisionImpl6, decisionImpl7, decisionImpl8, decisionImpl9, decisionImpl10, decisionImpl11, decisionImpl12, decisionImpl13, decisionImpl14, decisionImpl15, decisionImpl16, decisionImpl17, decisionImpl18};
            $VALUES = decisionImplArr;
            EnumEntriesKt.enumEntries(decisionImplArr);
        }

        public DecisionImpl(String str, int i, boolean z, String str2, boolean z2, NotificationInterruptStateProviderImpl.NotificationInterruptEvent notificationInterruptEvent, VisualInterruptionSuppressor.EventLogData eventLogData, int i2) {
            z2 = (i2 & 4) != 0 ? z : z2;
            boolean z3 = (i2 & 8) == 0;
            boolean z4 = (i2 & 16) != 0;
            boolean z5 = (i2 & 32) == 0;
            notificationInterruptEvent = (i2 & 64) != 0 ? null : notificationInterruptEvent;
            eventLogData = (i2 & 128) != 0 ? null : eventLogData;
            this.shouldFsi = z;
            this.logReason = str2;
            this.wouldFsiWithoutDnd = z2;
            this.supersedesDnd = z3;
            this.shouldLog = z4;
            this.isWarning = z5;
            this.uiEventId = notificationInterruptEvent;
            this.eventLogData = eventLogData;
        }

        public static DecisionImpl valueOf(String str) {
            return (DecisionImpl) Enum.valueOf(DecisionImpl.class, str);
        }

        public static DecisionImpl[] values() {
            return (DecisionImpl[]) $VALUES.clone();
        }

        public final VisualInterruptionSuppressor.EventLogData getEventLogData() {
            return this.eventLogData;
        }

        public final String getLogReason() {
            return this.logReason;
        }

        public final boolean getShouldFsi() {
            return this.shouldFsi;
        }

        public final boolean getShouldLog() {
            return this.shouldLog;
        }

        public final boolean getSupersedesDnd() {
            return this.supersedesDnd;
        }

        public final UiEventLogger.UiEventEnum getUiEventId() {
            return this.uiEventId;
        }

        public final boolean getWouldFsiWithoutDnd() {
            return this.wouldFsiWithoutDnd;
        }

        public final boolean isWarning() {
            return this.isWarning;
        }
    }

    public FullScreenIntentDecisionProvider(DeviceProvisionedController deviceProvisionedController, KeyguardStateController keyguardStateController, PowerManager powerManager, StatusBarStateController statusBarStateController) {
        this.deviceProvisionedController = deviceProvisionedController;
        this.keyguardStateController = keyguardStateController;
        this.powerManager = powerManager;
        this.statusBarStateController = statusBarStateController;
    }

    public final Decision makeFullScreenIntentDecision(NotificationEntry notificationEntry, boolean z) {
        DecisionImpl decisionImpl;
        StatusBarNotification statusBarNotification = notificationEntry.mSbn;
        Notification notification = statusBarNotification.getNotification();
        Intrinsics.checkNotNull(notification);
        if (notification.fullScreenIntent == null) {
            decisionImpl = notificationEntry.isStickyAndNotDemoted() ? DecisionImpl.NO_FSI_SHOW_STICKY_HUN : DecisionImpl.NO_FSI_NO_FULL_SCREEN_INTENT;
        } else if (notificationEntry.mRanking.getImportance() < 4) {
            decisionImpl = DecisionImpl.NO_FSI_NOT_IMPORTANT_ENOUGH;
        } else if (statusBarNotification.isGroup() && notification.suppressAlertingDueToGrouping()) {
            decisionImpl = DecisionImpl.NO_FSI_SUPPRESSIVE_GROUP_ALERT_BEHAVIOR;
        } else {
            Notification.BubbleMetadata bubbleMetadata = notification.getBubbleMetadata();
            if (bubbleMetadata != null && bubbleMetadata.isNotificationSuppressed()) {
                decisionImpl = DecisionImpl.NO_FSI_SUPPRESSIVE_BUBBLE_METADATA;
            } else if (notificationEntry.mRanking.isSuspended()) {
                decisionImpl = DecisionImpl.NO_FSI_PACKAGE_SUSPENDED;
            } else if (this.powerManager.isInteractive()) {
                StatusBarStateController statusBarStateController = this.statusBarStateController;
                if (statusBarStateController.isDreaming()) {
                    decisionImpl = DecisionImpl.FSI_DEVICE_DREAMING;
                } else if (statusBarStateController.getState() == 1) {
                    decisionImpl = DecisionImpl.FSI_KEYGUARD_SHOWING;
                } else if (z) {
                    decisionImpl = DecisionImpl.NO_FSI_EXPECTED_TO_HUN;
                } else {
                    KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
                    if (keyguardStateControllerImpl.mShowing) {
                        decisionImpl = keyguardStateControllerImpl.mOccluded ? DecisionImpl.FSI_KEYGUARD_OCCLUDED : DecisionImpl.FSI_LOCKED_SHADE;
                    } else {
                        DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) this.deviceProvisionedController;
                        decisionImpl = !deviceProvisionedControllerImpl.deviceProvisioned.get() ? DecisionImpl.FSI_DEVICE_NOT_PROVISIONED : !deviceProvisionedControllerImpl.isCurrentUserSetup() ? DecisionImpl.FSI_USER_SETUP_INCOMPLETE : DecisionImpl.NO_FSI_NO_HUN_OR_KEYGUARD;
                    }
                }
            } else {
                decisionImpl = DecisionImpl.FSI_DEVICE_NOT_INTERACTIVE;
            }
        }
        boolean shouldFsi = decisionImpl.getShouldFsi();
        boolean shouldSuppressVisualEffect = notificationEntry.shouldSuppressVisualEffect(4);
        return decisionImpl.getSupersedesDnd() ? decisionImpl : (shouldSuppressVisualEffect && shouldFsi) ? DecisionImpl.NO_FSI_SUPPRESSED_ONLY_BY_DND : shouldSuppressVisualEffect ? DecisionImpl.NO_FSI_SUPPRESSED_BY_DND : decisionImpl;
    }
}
