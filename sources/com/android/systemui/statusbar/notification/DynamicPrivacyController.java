package com.android.systemui.statusbar.notification;

import android.util.ArraySet;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DynamicPrivacyController implements KeyguardStateController.Callback {
    public boolean mCacheInvalid;
    public final KeyguardStateController mKeyguardStateController;
    public boolean mLastDynamicUnlocked;
    public final ArraySet mListeners = new ArraySet();
    public final NotificationLockscreenUserManager mLockscreenUserManager;
    public final StatusBarStateController mStateController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onDynamicPrivacyChanged();
    }

    public DynamicPrivacyController(NotificationLockscreenUserManager notificationLockscreenUserManager, KeyguardStateController keyguardStateController, StatusBarStateController statusBarStateController) {
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mStateController = statusBarStateController;
        this.mKeyguardStateController = keyguardStateController;
        ((KeyguardStateControllerImpl) keyguardStateController).addCallback(this);
        this.mLastDynamicUnlocked = isDynamicallyUnlocked();
    }

    public boolean isDynamicPrivacyEnabled() {
        NotificationLockscreenUserManager notificationLockscreenUserManager = this.mLockscreenUserManager;
        return ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager).userAllowsNotificationsInPublic(((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager).mCurrentUserId);
    }

    public final boolean isDynamicallyUnlocked() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        return (keyguardStateControllerImpl.mCanDismissLockScreen || keyguardStateControllerImpl.mKeyguardGoingAway || keyguardStateControllerImpl.mKeyguardFadingAway) && isDynamicPrivacyEnabled();
    }

    public final boolean isInLockedDownShade() {
        int state;
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        return keyguardStateControllerImpl.mShowing && keyguardStateControllerImpl.mSecure && ((state = this.mStateController.getState()) == 0 || state == 2) && isDynamicPrivacyEnabled() && !isDynamicallyUnlocked();
    }

    @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
    public final void onKeyguardFadingAwayChanged() {
        onUnlockedChanged();
    }

    @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
    public final void onUnlockedChanged() {
        if (!isDynamicPrivacyEnabled()) {
            this.mCacheInvalid = true;
            return;
        }
        boolean isDynamicallyUnlocked = isDynamicallyUnlocked();
        if (isDynamicallyUnlocked != this.mLastDynamicUnlocked || this.mCacheInvalid) {
            this.mLastDynamicUnlocked = isDynamicallyUnlocked;
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onDynamicPrivacyChanged();
            }
        }
        this.mCacheInvalid = false;
    }
}
