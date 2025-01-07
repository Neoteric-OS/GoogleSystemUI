package com.android.systemui.statusbar.notification.collection.coordinator;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.widget.MessagingGroup;
import com.android.internal.widget.MessagingMessage;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.notification.ColorUpdateLogger;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationGuts;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ViewConfigCoordinator implements Coordinator, ConfigurationController.ConfigurationListener {
    public final ColorUpdateLogger colorUpdateLogger;
    public final ConfigurationController mConfigurationController;
    public boolean mDispatchUiModeChangeOnUserSwitched;
    public final NotificationGutsManager mGutsManager;
    public boolean mIsSwitchingUser;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final NotificationLockscreenUserManager mLockscreenUserManager;
    public NotifPipeline mPipeline;
    public boolean mReinflateNotificationsOnUserSwitched;
    public final ViewConfigCoordinator$mKeyguardUpdateCallback$1 mKeyguardUpdateCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator$mKeyguardUpdateCallback$1
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserSwitchComplete(int i) {
            ViewConfigCoordinator viewConfigCoordinator = ViewConfigCoordinator.this;
            ColorUpdateLogger colorUpdateLogger = viewConfigCoordinator.colorUpdateLogger;
            int i2 = ColorUpdateLogger.$r8$clinit;
            colorUpdateLogger.getClass();
            viewConfigCoordinator.mIsSwitchingUser = false;
            ViewConfigCoordinator.access$applyChangesOnUserSwitched(viewConfigCoordinator);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserSwitching(int i) {
            ViewConfigCoordinator viewConfigCoordinator = ViewConfigCoordinator.this;
            ColorUpdateLogger colorUpdateLogger = viewConfigCoordinator.colorUpdateLogger;
            int i2 = ColorUpdateLogger.$r8$clinit;
            colorUpdateLogger.getClass();
            viewConfigCoordinator.mIsSwitchingUser = true;
        }
    };
    public final ViewConfigCoordinator$mUserChangedListener$1 mUserChangedListener = new NotificationLockscreenUserManager.UserChangedListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator$mUserChangedListener$1
        @Override // com.android.systemui.statusbar.NotificationLockscreenUserManager.UserChangedListener
        public final void onUserChanged(int i) {
            ViewConfigCoordinator viewConfigCoordinator = ViewConfigCoordinator.this;
            ColorUpdateLogger colorUpdateLogger = viewConfigCoordinator.colorUpdateLogger;
            int i2 = ColorUpdateLogger.$r8$clinit;
            colorUpdateLogger.getClass();
            ViewConfigCoordinator.access$applyChangesOnUserSwitched(viewConfigCoordinator);
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator$mKeyguardUpdateCallback$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator$mUserChangedListener$1] */
    public ViewConfigCoordinator(ConfigurationController configurationController, NotificationLockscreenUserManager notificationLockscreenUserManager, NotificationGutsManager notificationGutsManager, KeyguardUpdateMonitor keyguardUpdateMonitor, ColorUpdateLogger colorUpdateLogger) {
        this.mConfigurationController = configurationController;
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mGutsManager = notificationGutsManager;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.colorUpdateLogger = colorUpdateLogger;
    }

    public static final void access$applyChangesOnUserSwitched(ViewConfigCoordinator viewConfigCoordinator) {
        viewConfigCoordinator.getClass();
        int i = ColorUpdateLogger.$r8$clinit;
        viewConfigCoordinator.colorUpdateLogger.getClass();
        if (viewConfigCoordinator.mReinflateNotificationsOnUserSwitched) {
            viewConfigCoordinator.updateNotificationsOnDensityOrFontScaleChanged();
            viewConfigCoordinator.mReinflateNotificationsOnUserSwitched = false;
        }
        if (viewConfigCoordinator.mDispatchUiModeChangeOnUserSwitched) {
            viewConfigCoordinator.updateNotificationsOnUiModeChanged();
            viewConfigCoordinator.mDispatchUiModeChangeOnUserSwitched = false;
        }
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        this.mPipeline = notifPipeline;
        ((NotificationLockscreenUserManagerImpl) this.mLockscreenUserManager).mListeners.add(this.mUserChangedListener);
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this);
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateCallback);
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onDensityOrFontScaleChanged() {
        int i = ColorUpdateLogger.$r8$clinit;
        this.colorUpdateLogger.getClass();
        MessagingMessage.dropCache();
        MessagingGroup.dropCache();
        if (this.mIsSwitchingUser) {
            this.mReinflateNotificationsOnUserSwitched = true;
        } else {
            updateNotificationsOnDensityOrFontScaleChanged();
        }
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onThemeChanged() {
        int i = ColorUpdateLogger.$r8$clinit;
        this.colorUpdateLogger.getClass();
        onDensityOrFontScaleChanged();
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onUiModeChanged() {
        int i = ColorUpdateLogger.$r8$clinit;
        this.colorUpdateLogger.getClass();
        if (this.mIsSwitchingUser) {
            this.mDispatchUiModeChangeOnUserSwitched = true;
        } else {
            updateNotificationsOnUiModeChanged();
        }
    }

    public final void updateNotificationsOnDensityOrFontScaleChanged() {
        NotificationGuts notificationGuts;
        int i = ColorUpdateLogger.$r8$clinit;
        this.colorUpdateLogger.getClass();
        NotifPipeline notifPipeline = this.mPipeline;
        if (notifPipeline != null) {
            for (NotificationEntry notificationEntry : notifPipeline.getAllNotifs()) {
                ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                if (expandableNotificationRow != null) {
                    expandableNotificationRow.initDimens$3();
                    expandableNotificationRow.applyRoundnessAndInvalidate();
                    expandableNotificationRow.initDimens$1();
                    expandableNotificationRow.mBackgroundNormal.setCustomBackground$1();
                    expandableNotificationRow.reInflateViews$1();
                }
                ExpandableNotificationRow expandableNotificationRow2 = notificationEntry.row;
                if (expandableNotificationRow2 != null && (notificationGuts = expandableNotificationRow2.mGuts) != null && notificationGuts.mExposed) {
                    NotificationGutsManager notificationGutsManager = this.mGutsManager;
                    notificationGutsManager.getClass();
                    ExpandableNotificationRow expandableNotificationRow3 = notificationEntry.row;
                    notificationGutsManager.mNotificationGutsExposed = expandableNotificationRow3 != null ? expandableNotificationRow3.mGuts : null;
                    if (expandableNotificationRow3.mGuts == null) {
                        expandableNotificationRow3.mGutsStub.inflate();
                    }
                    notificationGutsManager.bindGuts(expandableNotificationRow3, notificationGutsManager.mGutsMenuItem);
                }
            }
        }
    }

    public final void updateNotificationsOnUiModeChanged() {
        ((ConfigurationControllerImpl) this.mConfigurationController).getClass();
        this.colorUpdateLogger.getClass();
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("updateNotifOnUiModeChanged");
        }
        try {
            NotifPipeline notifPipeline = this.mPipeline;
            if (notifPipeline != null) {
                Iterator it = notifPipeline.getAllNotifs().iterator();
                while (it.hasNext()) {
                    ExpandableNotificationRow expandableNotificationRow = ((NotificationEntry) it.next()).row;
                    if (expandableNotificationRow != null) {
                        expandableNotificationRow.onUiModeChanged();
                    }
                }
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}
