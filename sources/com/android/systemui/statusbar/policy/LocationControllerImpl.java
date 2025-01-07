package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.provider.Settings;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.BootCompleteCacheImpl;
import com.android.systemui.appops.AppOpItem;
import com.android.systemui.appops.AppOpsController;
import com.android.systemui.appops.AppOpsControllerImpl;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.settings.SecureSettings;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LocationControllerImpl extends BroadcastReceiver implements LocationController, AppOpsController.Callback {
    public final AppOpsController mAppOpsController;
    public boolean mAreActiveLocationRequests;
    public final Handler mBackgroundHandler;
    public final BootCompleteCacheImpl mBootCompleteCache;
    public final AnonymousClass1 mContentObserver;
    public final Context mContext;
    public final DeviceConfigProxy mDeviceConfigProxy;
    public final H mHandler;
    public final PackageManager mPackageManager;
    public final SecureSettings mSecureSettings;
    public boolean mShouldDisplayAllAccesses;
    public boolean mShowSystemAccessesFlag;
    public boolean mShowSystemAccessesSetting;
    public final UiEventLogger mUiEventLogger;
    public final UserTracker mUserTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class H extends Handler {
        public static final /* synthetic */ int $r8$clinit = 0;
        public final ArrayList mSettingsChangeCallbacks;

        public H(Looper looper) {
            super(looper);
            this.mSettingsChangeCallbacks = new ArrayList();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                locationSettingsChanged();
                return;
            }
            if (i != 2) {
                if (i == 3) {
                    synchronized (this.mSettingsChangeCallbacks) {
                        this.mSettingsChangeCallbacks.add((LocationController.LocationChangeCallback) message.obj);
                    }
                    return;
                } else {
                    if (i != 4) {
                        return;
                    }
                    synchronized (this.mSettingsChangeCallbacks) {
                        this.mSettingsChangeCallbacks.remove((LocationController.LocationChangeCallback) message.obj);
                    }
                    return;
                }
            }
            synchronized (this.mSettingsChangeCallbacks) {
                try {
                    int size = this.mSettingsChangeCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        LocationController.LocationChangeCallback locationChangeCallback = (LocationController.LocationChangeCallback) this.mSettingsChangeCallbacks.get(i2);
                        boolean z = LocationControllerImpl.this.mAreActiveLocationRequests;
                        locationChangeCallback.onLocationActiveChanged();
                    }
                } finally {
                }
            }
        }

        public final void locationSettingsChanged() {
            boolean isLocationEnabled$1 = LocationControllerImpl.this.isLocationEnabled$1();
            synchronized (this.mSettingsChangeCallbacks) {
                try {
                    int size = this.mSettingsChangeCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((LocationController.LocationChangeCallback) this.mSettingsChangeCallbacks.get(i)).onLocationSettingsChanged(isLocationEnabled$1);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    enum LocationIndicatorEvent implements UiEventLogger.UiEventEnum {
        LOCATION_INDICATOR_MONITOR_HIGH_POWER(935),
        LOCATION_INDICATOR_SYSTEM_APP(936),
        LOCATION_INDICATOR_NON_SYSTEM_APP(937);

        private final int mId;

        LocationIndicatorEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    public LocationControllerImpl(Context context, AppOpsController appOpsController, DeviceConfigProxy deviceConfigProxy, Looper looper, Handler handler, BroadcastDispatcher broadcastDispatcher, BootCompleteCacheImpl bootCompleteCacheImpl, UserTracker userTracker, PackageManager packageManager, UiEventLogger uiEventLogger, SecureSettings secureSettings) {
        this.mContext = context;
        this.mAppOpsController = appOpsController;
        this.mDeviceConfigProxy = deviceConfigProxy;
        this.mBootCompleteCache = bootCompleteCacheImpl;
        H h = new H(looper);
        this.mHandler = h;
        this.mUserTracker = userTracker;
        this.mUiEventLogger = uiEventLogger;
        this.mSecureSettings = secureSettings;
        this.mBackgroundHandler = handler;
        this.mPackageManager = packageManager;
        deviceConfigProxy.getClass();
        this.mShouldDisplayAllAccesses = DeviceConfig.getBoolean("privacy", "location_indicators_small_enabled", false);
        deviceConfigProxy.getClass();
        this.mShowSystemAccessesFlag = DeviceConfig.getBoolean("privacy", "location_indicators_show_system", false);
        this.mShowSystemAccessesSetting = secureSettings.getIntForUser("locationShowSystemOps", 0, -2) == 1;
        secureSettings.registerContentObserverForUserSync("locationShowSystemOps", new ContentObserver(handler) { // from class: com.android.systemui.statusbar.policy.LocationControllerImpl.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                LocationControllerImpl locationControllerImpl = LocationControllerImpl.this;
                locationControllerImpl.mShowSystemAccessesSetting = locationControllerImpl.mSecureSettings.getIntForUser("locationShowSystemOps", 0, -2) == 1;
            }
        }, -1);
        Objects.requireNonNull(handler);
        DeviceConfig.addOnPropertiesChangedListener("privacy", new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler), new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.statusbar.policy.LocationControllerImpl$$ExternalSyntheticLambda0
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                LocationControllerImpl locationControllerImpl = LocationControllerImpl.this;
                locationControllerImpl.mDeviceConfigProxy.getClass();
                locationControllerImpl.mShouldDisplayAllAccesses = DeviceConfig.getBoolean("privacy", "location_indicators_small_enabled", false);
                locationControllerImpl.mShowSystemAccessesFlag = locationControllerImpl.mSecureSettings.getIntForUser("locationShowSystemOps", 0, -2) == 1;
                locationControllerImpl.updateActiveLocationRequests();
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.location.MODE_CHANGED");
        UserHandle userHandle = UserHandle.ALL;
        broadcastDispatcher.getClass();
        BroadcastDispatcher.registerReceiverWithHandler$default(broadcastDispatcher, this, intentFilter, h, userHandle, 48);
        ((AppOpsControllerImpl) appOpsController).addCallback(new int[]{0, 1, 42}, this);
        handler.post(new LocationControllerImpl$$ExternalSyntheticLambda1(this, 0));
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        this.mHandler.obtainMessage(3, (LocationController.LocationChangeCallback) obj).sendToTarget();
        this.mHandler.sendEmptyMessage(1);
    }

    public boolean areActiveHighPowerLocationRequests() {
        ArrayList arrayList = (ArrayList) ((AppOpsControllerImpl) this.mAppOpsController).getActiveAppOps(false);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (((AppOpItem) arrayList.get(i)).mCode == 42) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void areActiveLocationRequests() {
        /*
            Method dump skipped, instructions count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.LocationControllerImpl.areActiveLocationRequests():void");
    }

    public final boolean isLocationEnabled$1() {
        return this.mBootCompleteCache.bootComplete.get() && ((LocationManager) this.mContext.getSystemService("location")).isLocationEnabledForUser(((UserTrackerImpl) this.mUserTracker).getUserHandle());
    }

    @Override // com.android.systemui.appops.AppOpsController.Callback
    public final void onActiveStateChanged(int i, int i2, String str, boolean z) {
        updateActiveLocationRequests();
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.location.MODE_CHANGED".equals(intent.getAction())) {
            H h = this.mHandler;
            int i = H.$r8$clinit;
            h.locationSettingsChanged();
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.mHandler.obtainMessage(4, (LocationController.LocationChangeCallback) obj).sendToTarget();
    }

    public final boolean setLocationEnabled(boolean z) {
        int userId = ((UserTrackerImpl) this.mUserTracker).getUserId();
        if (((UserManager) this.mContext.getSystemService("user")).hasUserRestriction("no_share_location", UserHandle.of(userId))) {
            return false;
        }
        Context context = this.mContext;
        Settings.Secure.putIntForUser(context.getContentResolver(), "location_changer", 2, userId);
        ((LocationManager) context.getSystemService(LocationManager.class)).setLocationEnabledForUser(z, UserHandle.of(userId));
        return true;
    }

    public final void updateActiveLocationRequests() {
        if (this.mShouldDisplayAllAccesses) {
            this.mBackgroundHandler.post(new LocationControllerImpl$$ExternalSyntheticLambda1(this, 1));
            return;
        }
        boolean z = this.mAreActiveLocationRequests;
        boolean areActiveHighPowerLocationRequests = areActiveHighPowerLocationRequests();
        this.mAreActiveLocationRequests = areActiveHighPowerLocationRequests;
        if (areActiveHighPowerLocationRequests != z) {
            this.mHandler.sendEmptyMessage(2);
            if (this.mAreActiveLocationRequests) {
                this.mUiEventLogger.log(LocationIndicatorEvent.LOCATION_INDICATOR_MONITOR_HIGH_POWER);
            }
        }
    }
}
