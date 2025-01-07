package com.android.systemui.statusbar.phone;

import android.app.StatusBarManager;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.ManagedProfileController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ManagedProfileControllerImpl implements ManagedProfileController {
    public final Context mContext;
    public int mCurrentUser;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public boolean mListening;
    public final Executor mMainExecutor;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;
    public final List mCallbacks = new ArrayList();
    public final UserTrackerCallback mUserTrackerCallback = new UserTrackerCallback();
    public final LinkedList mProfiles = new LinkedList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class UserTrackerCallback implements UserTracker.Callback {
        public UserTrackerCallback() {
        }

        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onProfilesChanged(List list) {
            ManagedProfileControllerImpl managedProfileControllerImpl = ManagedProfileControllerImpl.this;
            managedProfileControllerImpl.reloadManagedProfiles();
            Iterator it = new ArrayList(managedProfileControllerImpl.mCallbacks).iterator();
            while (it.hasNext()) {
                ((ManagedProfileController.Callback) it.next()).onManagedProfileChanged();
            }
        }

        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            ManagedProfileControllerImpl managedProfileControllerImpl = ManagedProfileControllerImpl.this;
            managedProfileControllerImpl.reloadManagedProfiles();
            Iterator it = new ArrayList(managedProfileControllerImpl.mCallbacks).iterator();
            while (it.hasNext()) {
                ((ManagedProfileController.Callback) it.next()).onManagedProfileChanged();
            }
        }
    }

    public ManagedProfileControllerImpl(Context context, Executor executor, UserTracker userTracker, UserManager userManager, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.mContext = context;
        this.mMainExecutor = executor;
        this.mUserManager = userManager;
        this.mUserTracker = userTracker;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        ManagedProfileController.Callback callback = (ManagedProfileController.Callback) obj;
        this.mCallbacks.add(callback);
        if (((ArrayList) this.mCallbacks).size() == 1) {
            setListening$2(true);
        }
        callback.onManagedProfileChanged();
    }

    public final boolean hasActiveProfile() {
        boolean z;
        if (!this.mListening || ((UserTrackerImpl) this.mUserTracker).getUserId() != this.mCurrentUser) {
            reloadManagedProfiles();
        }
        synchronized (this.mProfiles) {
            z = this.mProfiles.size() > 0;
        }
        return z;
    }

    public final boolean isWorkModeEnabled() {
        if (!this.mListening) {
            reloadManagedProfiles();
        }
        synchronized (this.mProfiles) {
            try {
                Iterator it = this.mProfiles.iterator();
                while (it.hasNext()) {
                    if (((UserInfo) it.next()).isQuietModeEnabled()) {
                        return false;
                    }
                }
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void reloadManagedProfiles() {
        synchronized (this.mProfiles) {
            try {
                boolean z = this.mProfiles.size() > 0;
                int userId = ((UserTrackerImpl) this.mUserTracker).getUserId();
                this.mProfiles.clear();
                for (UserInfo userInfo : this.mUserManager.getEnabledProfiles(userId)) {
                    if (userInfo.isManagedProfile()) {
                        this.mProfiles.add(userInfo);
                    }
                }
                if (this.mProfiles.size() == 0 && z && userId == this.mCurrentUser) {
                    this.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.ManagedProfileControllerImpl$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ManagedProfileControllerImpl managedProfileControllerImpl = ManagedProfileControllerImpl.this;
                            managedProfileControllerImpl.getClass();
                            Iterator it = new ArrayList(managedProfileControllerImpl.mCallbacks).iterator();
                            while (it.hasNext()) {
                                ((ManagedProfileController.Callback) it.next()).getClass();
                            }
                        }
                    });
                }
                this.mCurrentUser = userId;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        if (this.mCallbacks.remove((ManagedProfileController.Callback) obj) && ((ArrayList) this.mCallbacks).size() == 0) {
            setListening$2(false);
        }
    }

    public final void setListening$2(boolean z) {
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        UserTracker userTracker = this.mUserTracker;
        if (!z) {
            ((UserTrackerImpl) userTracker).removeCallback(this.mUserTrackerCallback);
        } else {
            reloadManagedProfiles();
            ((UserTrackerImpl) userTracker).addCallback(this.mUserTrackerCallback, this.mMainExecutor);
        }
    }

    public final void setWorkModeEnabled(boolean z) {
        synchronized (this.mProfiles) {
            try {
                Iterator it = this.mProfiles.iterator();
                while (it.hasNext()) {
                    if (!this.mUserManager.requestQuietModeEnabled(!z, UserHandle.of(((UserInfo) it.next()).id))) {
                        ((StatusBarManager) this.mContext.getSystemService("statusbar")).collapsePanels();
                        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
                        if (keyguardUpdateMonitor.mIsDreaming) {
                            try {
                                keyguardUpdateMonitor.mDreamManager.awaken();
                            } catch (RemoteException e) {
                                keyguardUpdateMonitor.mLogger.logException(e, "Unable to awaken from dream");
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
