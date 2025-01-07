package com.android.systemui.statusbar.notification.interruption;

import android.database.ContentObserver;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.EventLogImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.time.SystemClock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationInterruptStateProviderImpl {
    public final AmbientDisplayConfiguration mAmbientDisplayConfiguration;
    public final BatteryController mBatteryController;
    public final Optional mBubbles;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public final EventLogImpl mEventLog;
    public final GlobalSettings mGlobalSettings;
    public final HeadsUpManager mHeadsUpManager;
    public final KeyguardNotificationVisibilityProviderImpl mKeyguardNotificationVisibilityProvider;
    public final KeyguardStateController mKeyguardStateController;
    public final NotificationInterruptLogger mLogger;
    public final PowerManager mPowerManager;
    public final StatusBarStateController mStatusBarStateController;
    public final List mSuppressors;
    public final SystemClock mSystemClock;
    public final UiEventLogger mUiEventLogger;
    protected boolean mUseHeadsUp;
    public final UserTracker mUserTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum NotificationInterruptEvent implements UiEventLogger.UiEventEnum {
        FSI_SUPPRESSED_SUPPRESSIVE_GROUP_ALERT_BEHAVIOR(1235),
        FSI_SUPPRESSED_SUPPRESSIVE_BUBBLE_METADATA(1353),
        FSI_SUPPRESSED_NO_HUN_OR_KEYGUARD(1236),
        HUN_SUPPRESSED_OLD_WHEN(1237),
        /* JADX INFO: Fake field, exist only in values array */
        HUN_SNOOZE_BYPASSED_POTENTIALLY_SUPPRESSED_FSI(1269);

        public static final NotificationInterruptEvent HUN_SNOOZE_BYPASSED_POTENTIALLY_SUPPRESSED_FSI = null;
        private final int mId;

        NotificationInterruptEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    public NotificationInterruptStateProviderImpl(PowerManager powerManager, AmbientDisplayConfiguration ambientDisplayConfiguration, BatteryController batteryController, StatusBarStateController statusBarStateController, KeyguardStateController keyguardStateController, HeadsUpManager headsUpManager, NotificationInterruptLogger notificationInterruptLogger, Handler handler, KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProviderImpl, UiEventLogger uiEventLogger, UserTracker userTracker, DeviceProvisionedController deviceProvisionedController, SystemClock systemClock, GlobalSettings globalSettings, EventLogImpl eventLogImpl, Optional optional) {
        new ArrayList();
        this.mUseHeadsUp = false;
        this.mStatusBarStateController = statusBarStateController;
        this.mHeadsUpManager = headsUpManager;
        this.mLogger = notificationInterruptLogger;
        this.mUserTracker = userTracker;
        this.mGlobalSettings = globalSettings;
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.android.systemui.statusbar.notification.interruption.NotificationInterruptStateProviderImpl.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                NotificationInterruptStateProviderImpl notificationInterruptStateProviderImpl = NotificationInterruptStateProviderImpl.this;
                boolean z2 = notificationInterruptStateProviderImpl.mUseHeadsUp;
                boolean z3 = notificationInterruptStateProviderImpl.mGlobalSettings.getInt(0, "heads_up_notifications_enabled") != 0;
                NotificationInterruptStateProviderImpl notificationInterruptStateProviderImpl2 = NotificationInterruptStateProviderImpl.this;
                notificationInterruptStateProviderImpl2.mUseHeadsUp = z3;
                NotificationInterruptLogger notificationInterruptLogger2 = notificationInterruptStateProviderImpl2.mLogger;
                notificationInterruptLogger2.getClass();
                LogLevel logLevel = LogLevel.INFO;
                NotificationInterruptLogger$logHeadsUpFeatureChanged$2 notificationInterruptLogger$logHeadsUpFeatureChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.interruption.NotificationInterruptLogger$logHeadsUpFeatureChanged$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("heads up is enabled=", ((LogMessage) obj).getBool1());
                    }
                };
                LogBuffer logBuffer = notificationInterruptLogger2.buffer;
                LogMessage obtain = logBuffer.obtain("InterruptionStateProvider", logLevel, notificationInterruptLogger$logHeadsUpFeatureChanged$2, null);
                ((LogMessageImpl) obtain).bool1 = z3;
                logBuffer.commit(obtain);
                NotificationInterruptStateProviderImpl notificationInterruptStateProviderImpl3 = NotificationInterruptStateProviderImpl.this;
                boolean z4 = notificationInterruptStateProviderImpl3.mUseHeadsUp;
                if (z2 == z4 || z4) {
                    return;
                }
                NotificationInterruptLogger notificationInterruptLogger3 = notificationInterruptStateProviderImpl3.mLogger;
                notificationInterruptLogger3.getClass();
                NotificationInterruptLogger$logWillDismissAll$2 notificationInterruptLogger$logWillDismissAll$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.interruption.NotificationInterruptLogger$logWillDismissAll$2
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return "dismissing any existing heads up notification on disable event";
                    }
                };
                LogBuffer logBuffer2 = notificationInterruptLogger3.buffer;
                logBuffer2.commit(logBuffer2.obtain("InterruptionStateProvider", logLevel, notificationInterruptLogger$logWillDismissAll$2, null));
                ((BaseHeadsUpManager) NotificationInterruptStateProviderImpl.this.mHeadsUpManager).releaseAllImmediately();
            }
        };
        globalSettings.registerContentObserverSync(Settings.Global.getUriFor("heads_up_notifications_enabled"), true, contentObserver);
        globalSettings.registerContentObserverSync(Settings.Global.getUriFor("ticker_gets_heads_up"), true, contentObserver);
        contentObserver.onChange(true);
    }
}
