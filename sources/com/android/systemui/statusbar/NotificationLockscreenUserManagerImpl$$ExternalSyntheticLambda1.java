package com.android.systemui.statusbar;

import android.content.pm.UserInfo;
import android.os.UserHandle;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentProvider$notifStateChangedListener$1;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationLockscreenUserManagerImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationLockscreenUserManagerImpl f$0;

    public /* synthetic */ NotificationLockscreenUserManagerImpl$$ExternalSyntheticLambda1(NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = notificationLockscreenUserManagerImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = this.f$0;
        switch (i) {
            case 0:
                List users = notificationLockscreenUserManagerImpl.mUserManager.getUsers();
                for (int size = users.size() - 1; size >= 0; size--) {
                    int i2 = ((UserInfo) users.get(size)).id;
                    notificationLockscreenUserManagerImpl.mLockscreenSettingsObserver.onChange(false, notificationLockscreenUserManagerImpl.mLockScreenUris, 0, UserHandle.of(i2));
                    notificationLockscreenUserManagerImpl.updateDpcSettings(i2);
                    notificationLockscreenUserManagerImpl.mKeyguardAllowingNotifications = notificationLockscreenUserManagerImpl.mKeyguardManager.getPrivateNotificationsAllowed();
                }
                break;
            case 1:
                Iterator it = notificationLockscreenUserManagerImpl.mListeners.iterator();
                while (it.hasNext()) {
                    ((NotificationLockscreenUserManager.UserChangedListener) it.next()).onCurrentProfilesChanged(notificationLockscreenUserManagerImpl.mCurrentProfiles);
                }
                break;
            default:
                Iterator it2 = notificationLockscreenUserManagerImpl.mNotifStateChangedListeners.listeners.iterator();
                while (it2.hasNext()) {
                    Iterator it3 = ((NotifUiAdjustmentProvider$notifStateChangedListener$1) it2.next()).this$0.dirtyListeners.iterator();
                    while (it3.hasNext()) {
                        ((Runnable) it3.next()).run();
                    }
                }
                break;
        }
    }
}
