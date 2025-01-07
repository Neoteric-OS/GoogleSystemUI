package com.android.systemui.statusbar.notification.interruption;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.provider.Settings;
import android.util.IndentingPrintWriter;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.CoreStartable;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.provider.HighPriorityProvider;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.ListenerSet;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SecureSettings;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardNotificationVisibilityProviderImpl implements CoreStartable {
    public final GlobalSettings globalSettings;
    public final Handler handler;
    public boolean hideSilentNotificationsOnLockscreen;
    public final HighPriorityProvider highPriorityProvider;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final NotificationLockscreenUserManager lockscreenUserManager;
    public final SecureSettings secureSettings;
    public final SysuiStatusBarStateController statusBarStateController;
    public final UserTracker userTracker;
    public final Uri showSilentNotifsUri = Settings.Secure.getUriFor("lock_screen_show_silent_notifications");
    public final ListenerSet onStateChangedListeners = new ListenerSet();
    public final KeyguardNotificationVisibilityProviderImpl$userTrackerCallback$1 userTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl$userTrackerCallback$1
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProviderImpl = KeyguardNotificationVisibilityProviderImpl.this;
            keyguardNotificationVisibilityProviderImpl.readShowSilentNotificationSetting();
            if (keyguardNotificationVisibilityProviderImpl.isLockedOrLocking()) {
                KeyguardNotificationVisibilityProviderImpl.access$notifyStateChanged(keyguardNotificationVisibilityProviderImpl, "onUserSwitched");
            }
        }
    };

    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl$userTrackerCallback$1] */
    public KeyguardNotificationVisibilityProviderImpl(Handler handler, KeyguardStateController keyguardStateController, NotificationLockscreenUserManager notificationLockscreenUserManager, KeyguardUpdateMonitor keyguardUpdateMonitor, HighPriorityProvider highPriorityProvider, SysuiStatusBarStateController sysuiStatusBarStateController, UserTracker userTracker, SecureSettings secureSettings, GlobalSettings globalSettings) {
        this.handler = handler;
        this.keyguardStateController = keyguardStateController;
        this.lockscreenUserManager = notificationLockscreenUserManager;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.highPriorityProvider = highPriorityProvider;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.userTracker = userTracker;
        this.secureSettings = secureSettings;
        this.globalSettings = globalSettings;
    }

    public static final void access$notifyStateChanged(KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProviderImpl, String str) {
        Iterator it = keyguardNotificationVisibilityProviderImpl.onStateChangedListeners.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(str);
        }
    }

    public static final boolean userSettingsDisallowNotification$disallowForUser(KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProviderImpl, NotificationEntry notificationEntry, int i) {
        if (keyguardNotificationVisibilityProviderImpl.keyguardUpdateMonitor.isUserInLockdown(i)) {
            return true;
        }
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = (NotificationLockscreenUserManagerImpl) keyguardNotificationVisibilityProviderImpl.lockscreenUserManager;
        return notificationLockscreenUserManagerImpl.isLockscreenPublicMode(i) && ((notificationEntry.mRanking.getChannel() != null && notificationEntry.mRanking.getChannel().getLockscreenVisibility() == -1) || !notificationLockscreenUserManagerImpl.userAllowsNotificationsInPublic(i));
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        DumpUtilsKt.println(asIndenting, "isLockedOrLocking", Boolean.valueOf(isLockedOrLocking()));
        asIndenting.increaseIndent();
        try {
            DumpUtilsKt.println(asIndenting, "keyguardStateController.isShowing", Boolean.valueOf(((KeyguardStateControllerImpl) this.keyguardStateController).mShowing));
            DumpUtilsKt.println(asIndenting, "statusBarStateController.currentOrUpcomingState", Integer.valueOf(((StatusBarStateControllerImpl) this.statusBarStateController).mUpcomingState));
            asIndenting.decreaseIndent();
            DumpUtilsKt.println(asIndenting, "hideSilentNotificationsOnLockscreen", Boolean.valueOf(this.hideSilentNotificationsOnLockscreen));
        } catch (Throwable th) {
            asIndenting.decreaseIndent();
            throw th;
        }
    }

    public final boolean isLockedOrLocking() {
        return ((KeyguardStateControllerImpl) this.keyguardStateController).mShowing || ((StatusBarStateControllerImpl) this.statusBarStateController).mUpcomingState == 1;
    }

    public final void readShowSilentNotificationSetting() {
        this.hideSilentNotificationsOnLockscreen = !this.secureSettings.getBoolForUser(-2, "lock_screen_show_silent_notifications", false);
    }

    public final boolean shouldHideIfEntrySilent(ListEntry listEntry) {
        if (this.highPriorityProvider.isHighPriority(listEntry, false)) {
            return false;
        }
        NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
        if ((representativeEntry != null && representativeEntry.mRanking.isAmbient()) || this.hideSilentNotificationsOnLockscreen) {
            return true;
        }
        GroupEntry groupEntry = listEntry.mAttachState.parent;
        if (groupEntry == null) {
            return false;
        }
        shouldHideIfEntrySilent(groupEntry);
        return false;
    }

    public final boolean shouldHideNotification(NotificationEntry notificationEntry) {
        if (!isLockedOrLocking()) {
            return false;
        }
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = (NotificationLockscreenUserManagerImpl) this.lockscreenUserManager;
        if (notificationLockscreenUserManagerImpl.mShowLockscreenNotifications) {
            int i = notificationLockscreenUserManagerImpl.mCurrentUserId;
            int identifier = notificationEntry.mSbn.getUser().getIdentifier();
            if (!(userSettingsDisallowNotification$disallowForUser(this, notificationEntry, i) ? true : (identifier == -1 || identifier == i) ? false : userSettingsDisallowNotification$disallowForUser(this, notificationEntry, identifier)) && notificationEntry.mSbn.getNotification().visibility != -1 && !shouldHideIfEntrySilent(notificationEntry)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        readShowSilentNotificationSetting();
        ((KeyguardStateControllerImpl) this.keyguardStateController).addCallback(new KeyguardStateController.Callback() { // from class: com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl$start$1
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardShowingChanged() {
                KeyguardNotificationVisibilityProviderImpl.access$notifyStateChanged(KeyguardNotificationVisibilityProviderImpl.this, "onKeyguardShowingChanged");
            }

            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onUnlockedChanged() {
                KeyguardNotificationVisibilityProviderImpl.access$notifyStateChanged(KeyguardNotificationVisibilityProviderImpl.this, "onUnlockedChanged");
            }
        });
        this.keyguardUpdateMonitor.registerCallback(new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl$start$2
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onStrongAuthStateChanged(int i) {
                KeyguardNotificationVisibilityProviderImpl.access$notifyStateChanged(KeyguardNotificationVisibilityProviderImpl.this, "onStrongAuthStateChanged");
            }
        });
        final Handler handler = this.handler;
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl$start$settingsObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                if (Intrinsics.areEqual(uri, KeyguardNotificationVisibilityProviderImpl.this.showSilentNotifsUri)) {
                    KeyguardNotificationVisibilityProviderImpl.this.readShowSilentNotificationSetting();
                }
                if (KeyguardNotificationVisibilityProviderImpl.this.isLockedOrLocking()) {
                    KeyguardNotificationVisibilityProviderImpl.access$notifyStateChanged(KeyguardNotificationVisibilityProviderImpl.this, "Settings " + uri + " changed");
                }
            }
        };
        SecureSettings secureSettings = this.secureSettings;
        secureSettings.registerContentObserverForUserSync("lock_screen_show_notifications", contentObserver, -1);
        secureSettings.registerContentObserverForUserSync("lock_screen_allow_private_notifications", true, contentObserver, -1);
        this.globalSettings.registerContentObserverSync("zen_mode", contentObserver);
        secureSettings.registerContentObserverForUserSync("lock_screen_show_silent_notifications", contentObserver, -1);
        ((StatusBarStateControllerImpl) this.statusBarStateController).addCallback(new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl$start$3
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                KeyguardNotificationVisibilityProviderImpl.access$notifyStateChanged(KeyguardNotificationVisibilityProviderImpl.this, "onStatusBarStateChanged");
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onUpcomingStateChanged(int i) {
                KeyguardNotificationVisibilityProviderImpl.access$notifyStateChanged(KeyguardNotificationVisibilityProviderImpl.this, "onStatusBarUpcomingStateChanged");
            }
        });
        ((UserTrackerImpl) this.userTracker).addCallback(this.userTrackerCallback, new HandlerExecutor(handler));
    }
}
