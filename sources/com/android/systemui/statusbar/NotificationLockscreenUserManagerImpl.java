package com.android.systemui.statusbar;

import android.app.ActivityOptions;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.UserInfo;
import android.database.ExecutorContentObserver;
import android.net.Uri;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentProvider$notifStateChangedListener$1;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.ListenerSet;
import com.android.systemui.util.settings.SecureSettings;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationLockscreenUserManagerImpl implements Dumpable, NotificationLockscreenUserManager, StatusBarStateController.StateListener {
    public static final Uri SHOW_LOCKSCREEN = Settings.Secure.getUriFor("lock_screen_show_notifications");
    public static final Uri SHOW_PRIVATE_LOCKSCREEN = Settings.Secure.getUriFor("lock_screen_allow_private_notifications");
    public final AnonymousClass1 mAllUsersReceiver;
    public final Executor mBackgroundExecutor;
    public final AnonymousClass1 mBaseBroadcastReceiver;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final NotificationClickNotifier mClickNotifier;
    public final Lazy mCommonNotifCollectionLazy;
    public final Context mContext;
    public final SparseArray mCurrentManagedProfiles;
    public final SparseArray mCurrentProfiles;
    public int mCurrentUserId;
    public final DevicePolicyManager mDevicePolicyManager;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public final KeyguardManager mKeyguardManager;
    public final AnonymousClass1 mKeyguardReceiver;
    public final KeyguardStateController mKeyguardStateController;
    public final LockPatternUtils mLockPatternUtils;
    public final Collection mLockScreenUris;
    public final AnonymousClass5 mLockscreenSettingsObserver;
    public final Executor mMainExecutor;
    public StatusBarNotificationPresenter mPresenter;
    public final SecureSettings mSecureSettings;
    public boolean mShowLockscreenNotifications;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;
    public final Lazy mVisibilityProviderLazy;
    public final Object mLock = new Object();
    public final SparseBooleanArray mLockscreenPublicMode = new SparseBooleanArray();
    public final SparseBooleanArray mUsersWithSeparateWorkChallenge = new SparseBooleanArray();
    public final SparseBooleanArray mUsersAllowingPrivateNotifications = new SparseBooleanArray();
    public final SparseBooleanArray mUsersAllowingNotifications = new SparseBooleanArray();
    public final SparseBooleanArray mUsersDpcAllowingNotifications = new SparseBooleanArray();
    public final SparseBooleanArray mUsersUsersAllowingNotifications = new SparseBooleanArray();
    public boolean mKeyguardAllowingNotifications = true;
    public final SparseBooleanArray mUsersDpcAllowingPrivateNotifications = new SparseBooleanArray();
    public final SparseBooleanArray mUsersUsersAllowingPrivateNotifications = new SparseBooleanArray();
    public final SparseBooleanArray mUsersInLockdownLatestResult = new SparseBooleanArray();
    public final SparseBooleanArray mShouldHideNotifsLatestResult = new SparseBooleanArray();
    public final List mListeners = new ArrayList();
    public int mState = 0;
    public final ListenerSet mNotifStateChangedListeners = new ListenerSet();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl$1, reason: invalid class name */
    public final class AnonymousClass1 extends BroadcastReceiver {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ NotificationLockscreenUserManagerImpl this$0;

        public /* synthetic */ AnonymousClass1(NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl, int i) {
            this.$r8$classId = i;
            this.this$0 = notificationLockscreenUserManagerImpl;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            boolean updateDpcSettings;
            switch (this.$r8$classId) {
                case 0:
                    if ("android.app.action.KEYGUARD_PRIVATE_NOTIFICATIONS_CHANGED".equals(intent.getAction())) {
                        this.this$0.mKeyguardAllowingNotifications = intent.getBooleanExtra("android.app.extra.KM_PRIVATE_NOTIFS_ALLOWED", false);
                        if (this.this$0.mCurrentUserId == getSendingUserId() && this.this$0.updateLockscreenNotificationSetting()) {
                            this.this$0.notifyNotificationStateChanged();
                            break;
                        }
                    }
                    break;
                case 1:
                    if ("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED".equals(intent.getAction())) {
                        int sendingUserId = getSendingUserId();
                        if (sendingUserId == -1) {
                            NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = this.this$0;
                            int i = notificationLockscreenUserManagerImpl.mCurrentUserId;
                            List users = notificationLockscreenUserManagerImpl.mUserManager.getUsers();
                            updateDpcSettings = false;
                            for (int size = users.size() - 1; size >= 0; size--) {
                                updateDpcSettings |= this.this$0.updateDpcSettings(((UserInfo) users.get(size)).id);
                            }
                            sendingUserId = i;
                        } else {
                            updateDpcSettings = this.this$0.updateDpcSettings(sendingUserId);
                        }
                        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl2 = this.this$0;
                        if (notificationLockscreenUserManagerImpl2.mCurrentUserId == sendingUserId) {
                            updateDpcSettings |= notificationLockscreenUserManagerImpl2.updateLockscreenNotificationSetting();
                        }
                        if (updateDpcSettings) {
                            this.this$0.notifyNotificationStateChanged();
                            break;
                        }
                    }
                    break;
                default:
                    String action = intent.getAction();
                    if (Objects.equals(action, "android.intent.action.USER_REMOVED")) {
                        int intExtra = intent.getIntExtra("android.intent.extra.user_handle", -1);
                        if (intExtra != -1) {
                            Iterator it = this.this$0.mListeners.iterator();
                            while (it.hasNext()) {
                                ((NotificationLockscreenUserManager.UserChangedListener) it.next()).onUserRemoved(intExtra);
                            }
                        }
                        this.this$0.updateCurrentProfilesCache();
                        break;
                    } else if (Objects.equals(action, "android.intent.action.USER_ADDED")) {
                        this.this$0.updateCurrentProfilesCache();
                        final int intExtra2 = intent.getIntExtra("android.intent.extra.user_handle", -10000);
                        this.this$0.mBackgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl$3$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                NotificationLockscreenUserManagerImpl.AnonymousClass1 anonymousClass1 = NotificationLockscreenUserManagerImpl.AnonymousClass1.this;
                                int i2 = intExtra2;
                                NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl3 = anonymousClass1.this$0;
                                notificationLockscreenUserManagerImpl3.mLockscreenSettingsObserver.onChange(false, notificationLockscreenUserManagerImpl3.mLockScreenUris, 0, UserHandle.of(i2));
                                notificationLockscreenUserManagerImpl3.updateDpcSettings(i2);
                                notificationLockscreenUserManagerImpl3.mKeyguardAllowingNotifications = notificationLockscreenUserManagerImpl3.mKeyguardManager.getPrivateNotificationsAllowed();
                            }
                        });
                        break;
                    } else {
                        this.this$0.getClass();
                        if (!Objects.equals(action, "android.intent.action.PROFILE_AVAILABLE") && !Objects.equals(action, "android.intent.action.PROFILE_UNAVAILABLE")) {
                            if (!Objects.equals(action, "android.intent.action.USER_UNLOCKED") && Objects.equals(action, "com.android.systemui.statusbar.work_challenge_unlocked_notification_action")) {
                                IntentSender intentSender = (IntentSender) intent.getParcelableExtra("android.intent.extra.INTENT");
                                String stringExtra = intent.getStringExtra("android.intent.extra.INDEX");
                                if (intentSender != null) {
                                    try {
                                        ActivityOptions makeBasic = ActivityOptions.makeBasic();
                                        makeBasic.setPendingIntentBackgroundActivityStartMode(1);
                                        this.this$0.mContext.startIntentSender(intentSender, null, 0, 0, 0, makeBasic.toBundle());
                                    } catch (IntentSender.SendIntentException unused) {
                                    }
                                }
                                if (stringExtra != null) {
                                    NotificationVisibilityProviderImpl notificationVisibilityProviderImpl = (NotificationVisibilityProviderImpl) ((NotificationVisibilityProvider) this.this$0.mVisibilityProviderLazy.get());
                                    NotificationEntry entry = ((NotifPipeline) notificationVisibilityProviderImpl.notifCollection).mNotifCollection.getEntry(stringExtra);
                                    this.this$0.mClickNotifier.onNotificationClick(stringExtra, entry != null ? notificationVisibilityProviderImpl.obtain(entry) : NotificationVisibility.obtain(stringExtra, -1, ((Number) notificationVisibilityProviderImpl.notifDataStore.activeNotifCount.atomicValue.get()).intValue(), false));
                                    break;
                                }
                            }
                        } else {
                            this.this$0.updateCurrentProfilesCache();
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public NotificationLockscreenUserManagerImpl(Context context, BroadcastDispatcher broadcastDispatcher, DevicePolicyManager devicePolicyManager, UserManager userManager, UserTracker userTracker, Lazy lazy, Lazy lazy2, NotificationClickNotifier notificationClickNotifier, Lazy lazy3, KeyguardManager keyguardManager, StatusBarStateController statusBarStateController, Executor executor, Executor executor2, DeviceProvisionedController deviceProvisionedController, KeyguardStateController keyguardStateController, SecureSettings secureSettings, DumpManager dumpManager, LockPatternUtils lockPatternUtils) {
        ArrayList arrayList = new ArrayList();
        this.mLockScreenUris = arrayList;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this, 0);
        AnonymousClass1 anonymousClass12 = new AnonymousClass1(this, 1);
        AnonymousClass1 anonymousClass13 = new AnonymousClass1(this, 2);
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl.4
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanging(int i) {
                NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = NotificationLockscreenUserManagerImpl.this;
                notificationLockscreenUserManagerImpl.mCurrentUserId = i;
                notificationLockscreenUserManagerImpl.updateCurrentProfilesCache();
                notificationLockscreenUserManagerImpl.updateLockscreenNotificationSetting();
                notificationLockscreenUserManagerImpl.updatePublicMode();
                StatusBarNotificationPresenter statusBarNotificationPresenter = notificationLockscreenUserManagerImpl.mPresenter;
                if (statusBarNotificationPresenter != null) {
                    ((BaseHeadsUpManager) statusBarNotificationPresenter.mHeadsUpManager).mUser = notificationLockscreenUserManagerImpl.mCurrentUserId;
                    statusBarNotificationPresenter.mCommandQueue.animateCollapsePanels();
                    NotificationMediaManager notificationMediaManager = statusBarNotificationPresenter.mMediaManager;
                    notificationMediaManager.mBackgroundExecutor.execute(new NotificationMediaManager$$ExternalSyntheticLambda5(notificationMediaManager));
                } else {
                    Log.w("LockscreenUserManager", "user switch before setup with presenter", new Exception());
                }
                Iterator it = notificationLockscreenUserManagerImpl.mListeners.iterator();
                while (it.hasNext()) {
                    ((NotificationLockscreenUserManager.UserChangedListener) it.next()).onUserChanged(notificationLockscreenUserManagerImpl.mCurrentUserId);
                }
            }
        };
        this.mUserChangedCallback = callback;
        this.mCurrentProfiles = new SparseArray();
        this.mCurrentManagedProfiles = new SparseArray();
        this.mCurrentUserId = 0;
        this.mContext = context;
        this.mMainExecutor = executor;
        this.mBackgroundExecutor = executor2;
        this.mDevicePolicyManager = devicePolicyManager;
        this.mUserManager = userManager;
        this.mUserTracker = userTracker;
        this.mCurrentUserId = ((UserTrackerImpl) userTracker).getUserId();
        this.mVisibilityProviderLazy = lazy;
        this.mCommonNotifCollectionLazy = lazy2;
        this.mClickNotifier = notificationClickNotifier;
        statusBarStateController.addCallback(this);
        this.mLockPatternUtils = lockPatternUtils;
        this.mKeyguardManager = keyguardManager;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mSecureSettings = secureSettings;
        this.mKeyguardStateController = keyguardStateController;
        Uri uri = SHOW_LOCKSCREEN;
        arrayList.add(uri);
        Uri uri2 = SHOW_PRIVATE_LOCKSCREEN;
        arrayList.add(uri2);
        dumpManager.registerDumpable(this);
        this.mLockscreenSettingsObserver = new AnonymousClass5(this, executor2, 0);
        new AnonymousClass5(this, executor, 1);
        context.getContentResolver().registerContentObserver(uri, false, this.mLockscreenSettingsObserver, -1);
        context.getContentResolver().registerContentObserver(uri2, true, this.mLockscreenSettingsObserver, -1);
        IntentFilter intentFilter = new IntentFilter("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED");
        UserHandle userHandle = UserHandle.ALL;
        broadcastDispatcher.registerReceiver(anonymousClass12, intentFilter, executor2, userHandle);
        broadcastDispatcher.registerReceiver(anonymousClass1, new IntentFilter("android.app.action.KEYGUARD_PRIVATE_NOTIFICATIONS_CHANGED"), executor2, userHandle);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.USER_ADDED");
        intentFilter2.addAction("android.intent.action.USER_REMOVED");
        intentFilter2.addAction("android.intent.action.USER_UNLOCKED");
        intentFilter2.addAction("android.intent.action.MANAGED_PROFILE_AVAILABLE");
        intentFilter2.addAction("android.intent.action.MANAGED_PROFILE_UNAVAILABLE");
        intentFilter2.addAction("android.intent.action.PROFILE_AVAILABLE");
        intentFilter2.addAction("android.intent.action.PROFILE_UNAVAILABLE");
        broadcastDispatcher.registerReceiver(anonymousClass13, intentFilter2, null, userHandle);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("com.android.systemui.statusbar.work_challenge_unlocked_notification_action");
        context.registerReceiver(anonymousClass13, intentFilter3, "com.android.systemui.permission.SELF", null, 2);
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) userTracker;
        userTrackerImpl.addCallback(callback, executor);
        this.mCurrentUserId = userTrackerImpl.getUserId();
        updateCurrentProfilesCache();
        executor2.execute(new NotificationLockscreenUserManagerImpl$$ExternalSyntheticLambda1(this, 0));
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("NotificationLockscreenUserManager state:");
        printWriter.print("  mCurrentUserId=");
        printWriter.println(this.mCurrentUserId);
        printWriter.print("  mShowLockscreenNotifications=");
        printWriter.println(this.mShowLockscreenNotifications);
        printWriter.print("  mCurrentProfiles=");
        synchronized (this.mLock) {
            try {
                for (int size = this.mCurrentProfiles.size() - 1; size >= 0; size += -1) {
                    printWriter.print("" + ((UserInfo) this.mCurrentProfiles.valueAt(size)).id + " ");
                }
            } finally {
            }
        }
        printWriter.println();
        printWriter.print("  mCurrentManagedProfiles=");
        synchronized (this.mLock) {
            try {
                for (int size2 = this.mCurrentManagedProfiles.size() - 1; size2 >= 0; size2 += -1) {
                    printWriter.print("" + ((UserInfo) this.mCurrentManagedProfiles.valueAt(size2)).id + " ");
                }
            } finally {
            }
        }
        printWriter.println();
        printWriter.print("  mLockscreenPublicMode=");
        printWriter.println(this.mLockscreenPublicMode);
        printWriter.print("  mUsersWithSeparateWorkChallenge=");
        printWriter.println(this.mUsersWithSeparateWorkChallenge);
        printWriter.print("  mUsersAllowingPrivateNotifications=");
        printWriter.println(this.mUsersAllowingPrivateNotifications);
        printWriter.print("  mUsersAllowingNotifications=");
        printWriter.println(this.mUsersAllowingNotifications);
        printWriter.print("  mUsersInLockdownLatestResult=");
        printWriter.println(this.mUsersInLockdownLatestResult);
        printWriter.print("  mShouldHideNotifsLatestResult=");
        printWriter.println(this.mShouldHideNotifsLatestResult);
        printWriter.print("  mUsersDpcAllowingNotifications=");
        printWriter.println(this.mUsersDpcAllowingNotifications);
        printWriter.print("  mUsersUsersAllowingNotifications=");
        printWriter.println(this.mUsersUsersAllowingNotifications);
        printWriter.print("  mKeyguardAllowingNotifications=");
        printWriter.println(this.mKeyguardAllowingNotifications);
        printWriter.print("  mUsersDpcAllowingPrivateNotifications=");
        printWriter.println(this.mUsersDpcAllowingPrivateNotifications);
        printWriter.print("  mUsersUsersAllowingPrivateNotifications=");
        printWriter.println(this.mUsersUsersAllowingPrivateNotifications);
    }

    public final boolean isAnyProfilePublicMode() {
        synchronized (this.mLock) {
            try {
                for (int size = this.mCurrentProfiles.size() - 1; size >= 0; size--) {
                    if (isLockscreenPublicMode(((UserInfo) this.mCurrentProfiles.valueAt(size)).id)) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isCurrentProfile(int i) {
        boolean z;
        synchronized (this.mLock) {
            if (i != -1) {
                try {
                    if (this.mCurrentProfiles.get(i) == null) {
                        z = false;
                    }
                } finally {
                }
            }
            z = true;
        }
        return z;
    }

    public final boolean isLockscreenPublicMode(int i) {
        return i == -1 ? this.mLockscreenPublicMode.get(this.mCurrentUserId, false) : this.mLockscreenPublicMode.get(i, false);
    }

    public final boolean isProfileAvailable(int i) {
        boolean isUserRunning;
        synchronized (this.mLock) {
            isUserRunning = this.mUserManager.isUserRunning(i);
        }
        return isUserRunning;
    }

    public final boolean needsRedaction(NotificationEntry notificationEntry) {
        boolean z;
        int userId = notificationEntry.mSbn.getUserId();
        boolean z2 = ((this.mCurrentManagedProfiles.contains(userId) || userAllowsPrivateNotificationsInPublic(this.mCurrentUserId)) && userAllowsPrivateNotificationsInPublic(userId)) ? false : true;
        boolean z3 = notificationEntry.mSbn.getNotification().visibility == 0;
        String key = notificationEntry.mSbn.getKey();
        Lazy lazy = this.mCommonNotifCollectionLazy;
        if (lazy.get() == null) {
            Log.wtf("LockscreenUserManager", "mEntryManager was null!", new Throwable());
        } else {
            NotificationEntry entry = ((NotifPipeline) ((CommonNotifCollection) lazy.get())).mNotifCollection.getEntry(key);
            if (entry == null || entry.mRanking.getChannel() == null || entry.mRanking.getChannel().getLockscreenVisibility() != 0) {
                z = false;
                if (!this.mKeyguardAllowingNotifications && !z) {
                    return z3 && z2;
                }
            }
        }
        z = true;
        return !this.mKeyguardAllowingNotifications ? true : true;
    }

    public final void notifyNotificationStateChanged() {
        if (!Looper.getMainLooper().isCurrentThread()) {
            this.mMainExecutor.execute(new NotificationLockscreenUserManagerImpl$$ExternalSyntheticLambda1(this, 2));
            return;
        }
        Iterator it = this.mNotifStateChangedListeners.listeners.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((NotifUiAdjustmentProvider$notifStateChangedListener$1) it.next()).this$0.dirtyListeners.iterator();
            while (it2.hasNext()) {
                ((Runnable) it2.next()).run();
            }
        }
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onStateChanged(int i) {
        this.mState = i;
        updatePublicMode();
    }

    public void setLockscreenPublicMode(boolean z, int i) {
        this.mLockscreenPublicMode.put(i, z);
    }

    public final void updateCurrentProfilesCache() {
        synchronized (this.mLock) {
            try {
                this.mCurrentProfiles.clear();
                this.mCurrentManagedProfiles.clear();
                UserManager userManager = this.mUserManager;
                if (userManager != null) {
                    for (UserInfo userInfo : userManager.getProfiles(this.mCurrentUserId)) {
                        this.mCurrentProfiles.put(userInfo.id, userInfo);
                        if ("android.os.usertype.profile.MANAGED".equals(userInfo.userType)) {
                            this.mCurrentManagedProfiles.put(userInfo.id, userInfo);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.mMainExecutor.execute(new NotificationLockscreenUserManagerImpl$$ExternalSyntheticLambda1(this, 1));
    }

    public final boolean updateDpcSettings(int i) {
        boolean z = this.mUsersDpcAllowingNotifications.get(i);
        boolean z2 = this.mUsersDpcAllowingPrivateNotifications.get(i);
        int keyguardDisabledFeatures = this.mDevicePolicyManager.getKeyguardDisabledFeatures(null, i);
        boolean z3 = (keyguardDisabledFeatures & 4) == 0;
        boolean z4 = (keyguardDisabledFeatures & 8) == 0;
        this.mUsersDpcAllowingNotifications.put(i, z3);
        this.mUsersDpcAllowingPrivateNotifications.put(i, z4);
        return (z == z3 && z2 == z4) ? false : true;
    }

    public final boolean updateLockscreenNotificationSetting() {
        boolean z = this.mUsersUsersAllowingNotifications.get(this.mCurrentUserId);
        boolean z2 = this.mUsersDpcAllowingNotifications.get(this.mCurrentUserId, true);
        boolean z3 = this.mShowLockscreenNotifications;
        boolean z4 = z && z2;
        this.mShowLockscreenNotifications = z4;
        return z3 != z4;
    }

    public final void updatePublicMode() {
        int i = this.mState;
        KeyguardStateController keyguardStateController = this.mKeyguardStateController;
        boolean z = i != 0 || ((KeyguardStateControllerImpl) keyguardStateController).mShowing;
        boolean z2 = z && ((KeyguardStateControllerImpl) keyguardStateController).mSecure;
        SparseArray sparseArray = this.mCurrentProfiles;
        SparseBooleanArray clone = this.mLockscreenPublicMode.clone();
        SparseBooleanArray clone2 = this.mUsersWithSeparateWorkChallenge.clone();
        this.mUsersWithSeparateWorkChallenge.clear();
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            final int i2 = ((UserInfo) sparseArray.valueAt(size)).id;
            boolean booleanValue = ((Boolean) DejankUtils.whitelistIpcs(new Supplier() { // from class: com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = NotificationLockscreenUserManagerImpl.this;
                    return Boolean.valueOf(notificationLockscreenUserManagerImpl.mLockPatternUtils.isSeparateProfileChallengeEnabled(i2));
                }
            })).booleanValue();
            setLockscreenPublicMode((z2 || i2 == this.mCurrentUserId || !booleanValue || !this.mLockPatternUtils.isSecure(i2)) ? z2 : z || this.mKeyguardManager.isDeviceLocked(i2), i2);
            this.mUsersWithSeparateWorkChallenge.put(i2, booleanValue);
        }
        if (this.mLockscreenPublicMode.equals(clone) && this.mUsersWithSeparateWorkChallenge.equals(clone2)) {
            return;
        }
        notifyNotificationStateChanged();
    }

    public final boolean userAllowsNotificationsInPublic(int i) {
        if (i == -1 || this.mCurrentManagedProfiles.contains(i)) {
            i = this.mCurrentUserId;
        }
        if (this.mUsersUsersAllowingNotifications.indexOfKey(i) < 0) {
            Log.wtf("LockscreenUserManager", "Asking for show notifs setting too early", new Throwable());
            this.mUsersUsersAllowingNotifications.get(i);
            this.mUsersUsersAllowingNotifications.put(i, this.mSecureSettings.getIntForUser("lock_screen_show_notifications", 1, i) != 0);
        }
        if (this.mUsersDpcAllowingNotifications.indexOfKey(i) < 0) {
            Log.wtf("LockscreenUserManager", "Asking for show notifs dpm override too early", new Throwable());
            updateDpcSettings(i);
        }
        return this.mUsersUsersAllowingNotifications.get(i) && this.mUsersDpcAllowingNotifications.get(i);
    }

    public final boolean userAllowsPrivateNotificationsInPublic(int i) {
        if (i == -1) {
            i = this.mCurrentUserId;
        }
        if (this.mUsersUsersAllowingPrivateNotifications.indexOfKey(i) < 0) {
            Log.i("LockscreenUserManager", "Asking for redact notifs setting too early", new Throwable());
            return false;
        }
        if (this.mUsersDpcAllowingPrivateNotifications.indexOfKey(i) >= 0) {
            return this.mUsersUsersAllowingPrivateNotifications.get(i) && this.mUsersDpcAllowingPrivateNotifications.get(i) && this.mKeyguardAllowingNotifications;
        }
        Log.i("LockscreenUserManager", "Asking for redact notifs dpm override too early", new Throwable());
        return false;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl$5, reason: invalid class name */
    public final class AnonymousClass5 extends ExecutorContentObserver {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ NotificationLockscreenUserManagerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass5(NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl, Executor executor, int i) {
            super(executor);
            this.$r8$classId = i;
            this.this$0 = notificationLockscreenUserManagerImpl;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void onChange(boolean z, Collection collection, int i) {
            switch (this.$r8$classId) {
                case 0:
                    List users = this.this$0.mUserManager.getUsers();
                    for (int size = users.size() - 1; size >= 0; size--) {
                        onChange(z, collection, i, ((UserInfo) users.get(size)).getUserHandle());
                    }
                    break;
                default:
                    super/*android.database.ContentObserver*/.onChange(z, collection, i);
                    break;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x006e, code lost:
        
            if (r4 != r3) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
        
            r2 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
        
            r9 = r9 | r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0042, code lost:
        
            if (r4 != r3) goto L19;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onChange(boolean r7, java.util.Collection r8, int r9, android.os.UserHandle r10) {
            /*
                r6 = this;
                int r0 = r6.$r8$classId
                switch(r0) {
                    case 0: goto L9;
                    default: goto L5;
                }
            L5:
                super/*android.database.ContentObserver*/.onChange(r7, r8, r9, r10)
                return
            L9:
                java.util.Iterator r7 = r8.iterator()
                r8 = 0
                r9 = r8
            Lf:
                boolean r0 = r7.hasNext()
                if (r0 == 0) goto L71
                java.lang.Object r0 = r7.next()
                android.net.Uri r0 = (android.net.Uri) r0
                android.net.Uri r1 = com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl.SHOW_LOCKSCREEN
                boolean r1 = r1.equals(r0)
                r2 = 1
                if (r1 == 0) goto L48
                com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl r0 = r6.this$0
                int r1 = r10.getIdentifier()
                android.util.SparseBooleanArray r3 = r0.mUsersUsersAllowingNotifications
                boolean r3 = r3.get(r1)
                com.android.systemui.util.settings.SecureSettings r4 = r0.mSecureSettings
                java.lang.String r5 = "lock_screen_show_notifications"
                int r4 = r4.getIntForUser(r5, r2, r1)
                if (r4 == 0) goto L3c
                r4 = r2
                goto L3d
            L3c:
                r4 = r8
            L3d:
                android.util.SparseBooleanArray r0 = r0.mUsersUsersAllowingNotifications
                r0.put(r1, r4)
                if (r4 == r3) goto L45
                goto L46
            L45:
                r2 = r8
            L46:
                r9 = r9 | r2
                goto Lf
            L48:
                android.net.Uri r1 = com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl.SHOW_PRIVATE_LOCKSCREEN
                boolean r0 = r1.equals(r0)
                if (r0 == 0) goto Lf
                com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl r0 = r6.this$0
                int r1 = r10.getIdentifier()
                android.util.SparseBooleanArray r3 = r0.mUsersUsersAllowingPrivateNotifications
                boolean r3 = r3.get(r1)
                com.android.systemui.util.settings.SecureSettings r4 = r0.mSecureSettings
                java.lang.String r5 = "lock_screen_allow_private_notifications"
                int r4 = r4.getIntForUser(r5, r8, r1)
                if (r4 == 0) goto L68
                r4 = r2
                goto L69
            L68:
                r4 = r8
            L69:
                android.util.SparseBooleanArray r0 = r0.mUsersUsersAllowingPrivateNotifications
                r0.put(r1, r4)
                if (r4 == r3) goto L45
                goto L46
            L71:
                com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl r7 = r6.this$0
                int r7 = r7.mCurrentUserId
                int r8 = r10.getIdentifier()
                if (r7 != r8) goto L82
                com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl r7 = r6.this$0
                boolean r7 = r7.updateLockscreenNotificationSetting()
                r9 = r9 | r7
            L82:
                if (r9 == 0) goto L89
                com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl r6 = r6.this$0
                r6.notifyNotificationStateChanged()
            L89:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl.AnonymousClass5.onChange(boolean, java.util.Collection, int, android.os.UserHandle):void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void onChange(boolean z) {
            switch (this.$r8$classId) {
                case 1:
                    this.this$0.updateLockscreenNotificationSetting();
                    ((DeviceProvisionedControllerImpl) this.this$0.mDeviceProvisionedController).deviceProvisioned.get();
                    break;
                default:
                    super/*android.database.ContentObserver*/.onChange(z);
                    break;
            }
        }
    }
}
