package com.android.wifitrackerlib;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.util.ArraySet;
import com.android.wm.shell.R;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiTrackerInjector {
    public final ConnectivityManager mConnectivityManager;
    public final Context mContext;
    public final DevicePolicyManager mDevicePolicyManager;
    public final boolean mIsDemoMode;
    public final boolean mIsUserDebugVerboseLoggingEnabled;
    public final UserManager mUserManager;
    public final WifiManager mWifiManager;
    public boolean mVerboseLoggingDisabledOverride = false;
    public final Set mNoAttributionAnnotationPackages = new ArraySet();

    public WifiTrackerInjector(Context context) {
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        this.mIsDemoMode = UserManager.isDeviceInDemoMode(context);
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
        this.mDevicePolicyManager = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
        for (String str : context.getString(R.string.wifitrackerlib_no_attribution_annotation_packages).split(",")) {
            this.mNoAttributionAnnotationPackages.add(str);
        }
        this.mIsUserDebugVerboseLoggingEnabled = context.getResources().getBoolean(R.bool.wifitrackerlib_enable_verbose_logging_for_userdebug) && Build.TYPE.equals("userdebug");
    }

    public static boolean isSharedConnectivityFeatureEnabled() {
        return DeviceConfig.getBoolean("wifi", "shared_connectivity_enabled", false);
    }
}
