package com.google.android.systemui.coversheet;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.Dependency;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CoversheetService {
    public static final boolean DEBUG = Log.isLoggable("Coversheet", 3);
    public final String mBuildId;
    public final KeyguardUpdateMonitorCallback mCallback;
    public final Context mContext;
    public boolean mKeyguardShowing;
    public boolean mUserUnlocked;

    /* renamed from: -$$Nest$mstartCoversheetIfNeeded, reason: not valid java name */
    public static void m915$$Nest$mstartCoversheetIfNeeded(CoversheetService coversheetService) {
        boolean z = DEBUG;
        if (z) {
            StringBuilder sb = new StringBuilder("mKeyguardShowing: ");
            sb.append(coversheetService.mKeyguardShowing);
            sb.append(", mUserUnlocked: ");
            CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, coversheetService.mUserUnlocked, "Coversheet");
        }
        if (coversheetService.mKeyguardShowing || !coversheetService.mUserUnlocked) {
            return;
        }
        ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.sInstance.getRunningTask();
        if (runningTask == null) {
            Log.w("Coversheet", "Not able to get any running task");
            return;
        }
        boolean z2 = runningTask.configuration.windowConfiguration.getActivityType() == 2;
        if (z) {
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("Going to home now? ", "Coversheet", z2);
        }
        if (z2) {
            try {
                Intent intent = new Intent();
                intent.setAction("com.google.android.gms.setupservices.COVERSHEET_WELCOME");
                intent.setPackage("com.google.android.gms");
                intent.setFlags(335544320);
                if (z) {
                    Log.d("Coversheet", "start el-cap coversheet page: " + intent.toURI());
                }
                coversheetService.mContext.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                Log.w("Coversheet", "el-cap coversheet page was not found");
                try {
                    Intent intent2 = new Intent("com.google.android.apps.tips.action.COVERSHEET");
                    intent2.setPackage("com.google.android.apps.tips");
                    intent2.setFlags(335544320);
                    if (z) {
                        Log.d("Coversheet", "start coversheet: " + intent2.toURI());
                    }
                    coversheetService.mContext.startActivity(intent2);
                } catch (ActivityNotFoundException unused2) {
                    Log.w("Coversheet", "Coversheet was not found");
                }
            }
            Settings.System.putString(coversheetService.mContext.getContentResolver(), "coversheet_id", coversheetService.mBuildId);
            ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).removeCallback(coversheetService.mCallback);
        }
    }

    public CoversheetService(Context context) {
        KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.google.android.systemui.coversheet.CoversheetService.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onKeyguardVisibilityChanged(boolean z) {
                if (CoversheetService.DEBUG) {
                    Log.d("Coversheet", "onKeyguardVisibilityChanged");
                }
                CoversheetService coversheetService = CoversheetService.this;
                coversheetService.mKeyguardShowing = z;
                CoversheetService.m915$$Nest$mstartCoversheetIfNeeded(coversheetService);
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onUserUnlocked() {
                if (CoversheetService.DEBUG) {
                    Log.d("Coversheet", "onUserUnlocked");
                }
                CoversheetService coversheetService = CoversheetService.this;
                coversheetService.mUserUnlocked = true;
                CoversheetService.m915$$Nest$mstartCoversheetIfNeeded(coversheetService);
            }
        };
        this.mCallback = keyguardUpdateMonitorCallback;
        String str = Build.ID.split("\\.")[0];
        this.mBuildId = str;
        this.mContext = context;
        boolean z = ((DeviceProvisionedControllerImpl) ((DeviceProvisionedController) Dependency.sDependency.getDependencyInner(DeviceProvisionedController.class))).deviceProvisioned.get();
        boolean z2 = DEBUG;
        if (!z) {
            if (z2) {
                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Store initial ID: ", str, "Coversheet");
            }
            Settings.System.putString(context.getContentResolver(), "coversheet_id", str);
        } else {
            if (TextUtils.equals(str, Settings.System.getString(context.getContentResolver(), "coversheet_id"))) {
                return;
            }
            if (z2) {
                Log.d("Coversheet", "Register callback.");
            }
            ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).registerCallback(keyguardUpdateMonitorCallback);
        }
    }
}
