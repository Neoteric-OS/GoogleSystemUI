package com.android.systemui.statusbar.connectivity;

import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.qs.tiles.dialog.InternetDialogController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.connectivity.AccessPointController;
import com.android.wifitrackerlib.MergedCarrierEntry;
import com.android.wifitrackerlib.WifiEntry;
import com.android.wifitrackerlib.WifiPickerTracker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AccessPointControllerImpl implements AccessPointController, WifiPickerTracker.WifiPickerTrackerCallback, LifecycleOwner {
    public static final boolean DEBUG = Log.isLoggable("AccessPointController", 3);
    public int mCurrentUser;
    public final Executor mMainExecutor;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;
    public WifiPickerTracker mWifiPickerTracker;
    public final WifiPickerTrackerFactory mWifiPickerTrackerFactory;
    public final ArrayList mCallbacks = new ArrayList();
    public final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    static {
        int[] iArr = WifiIcons.WIFI_FULL_ICONS;
    }

    public AccessPointControllerImpl(UserManager userManager, UserTracker userTracker, Executor executor, WifiPickerTrackerFactory wifiPickerTrackerFactory) {
        this.mUserManager = userManager;
        this.mUserTracker = userTracker;
        this.mCurrentUser = ((UserTrackerImpl) userTracker).getUserId();
        this.mMainExecutor = executor;
        this.mWifiPickerTrackerFactory = wifiPickerTrackerFactory;
        executor.execute(new AccessPointControllerImpl$$ExternalSyntheticLambda0(this, 1));
    }

    public final boolean canConfigMobileData() {
        return !this.mUserManager.hasUserRestriction("no_config_mobile_networks", UserHandle.of(this.mCurrentUser)) && ((UserTrackerImpl) this.mUserTracker).getUserInfo().isAdmin();
    }

    public final boolean canConfigWifi() {
        if (this.mWifiPickerTrackerFactory.wifiManager != null) {
            return !this.mUserManager.hasUserRestriction("no_config_wifi", new UserHandle(this.mCurrentUser));
        }
        return false;
    }

    public final void finalize() {
        this.mMainExecutor.execute(new AccessPointControllerImpl$$ExternalSyntheticLambda0(this, 0));
        super.finalize();
    }

    public final void fireAccessPointsCallback(List list) {
        Iterator it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            ((InternetDialogController) ((AccessPointController.AccessPointCallback) it.next())).onAccessPointsChanged(list);
        }
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public final MergedCarrierEntry getMergedCarrierEntry() {
        WifiPickerTracker wifiPickerTracker = this.mWifiPickerTracker;
        if (wifiPickerTracker != null) {
            return wifiPickerTracker.getMergedCarrierEntry();
        }
        fireAccessPointsCallback(Collections.emptyList());
        return null;
    }

    @Override // com.android.wifitrackerlib.WifiPickerTracker.WifiPickerTrackerCallback
    public final void onScanRequested() {
        Iterator it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            ((InternetDialogController) ((AccessPointController.AccessPointCallback) it.next())).onWifiScan(true);
        }
    }

    @Override // com.android.wifitrackerlib.WifiPickerTracker.WifiPickerTrackerCallback
    public final void onWifiEntriesChanged() {
        scanForAccessPoints();
    }

    @Override // com.android.wifitrackerlib.WifiPickerTracker.WifiPickerTrackerCallback
    public final void onWifiStateChanged() {
        scanForAccessPoints();
    }

    public final void scanForAccessPoints() {
        WifiPickerTracker wifiPickerTracker = this.mWifiPickerTracker;
        if (wifiPickerTracker == null) {
            fireAccessPointsCallback(Collections.emptyList());
            return;
        }
        ArrayList arrayList = new ArrayList(wifiPickerTracker.mWifiEntries);
        WifiEntry wifiEntry = this.mWifiPickerTracker.mConnectedWifiEntry;
        if (wifiEntry != null) {
            arrayList.add(0, wifiEntry);
        }
        fireAccessPointsCallback(arrayList);
    }

    @Override // com.android.wifitrackerlib.WifiPickerTracker.WifiPickerTrackerCallback
    public final void onWifiEntriesChanged(int i) {
        scanForAccessPoints();
        if (i == 1) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((InternetDialogController) ((AccessPointController.AccessPointCallback) it.next())).onWifiScan(false);
            }
        }
    }
}
