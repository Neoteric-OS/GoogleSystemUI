package com.android.systemui.assist;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import com.android.systemui.BootCompleteCache$BootCompleteListener;
import com.android.systemui.BootCompleteCacheImpl;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.PackageManagerWrapper;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PhoneStateMonitor {
    public static final String[] DEFAULT_HOME_CHANGE_ACTIONS = {"android.intent.action.ACTION_PREFERRED_ACTIVITY_CHANGED", "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED"};
    public final Lazy mCentralSurfacesOptionalLazy;
    public final Context mContext;
    public ComponentName mDefaultHome = getCurrentDefaultHome();
    public boolean mLauncherShowing;
    public final StatusBarStateController mStatusBarStateController;

    public PhoneStateMonitor(Context context, BroadcastDispatcher broadcastDispatcher, Lazy lazy, BootCompleteCacheImpl bootCompleteCacheImpl, StatusBarStateController statusBarStateController) {
        ComponentName componentName;
        this.mContext = context;
        this.mCentralSurfacesOptionalLazy = lazy;
        this.mStatusBarStateController = statusBarStateController;
        bootCompleteCacheImpl.addListener(new BootCompleteCache$BootCompleteListener() { // from class: com.android.systemui.assist.PhoneStateMonitor$$ExternalSyntheticLambda0
            @Override // com.android.systemui.BootCompleteCache$BootCompleteListener
            public final void onBootComplete() {
                PhoneStateMonitor phoneStateMonitor = PhoneStateMonitor.this;
                phoneStateMonitor.getClass();
                phoneStateMonitor.mDefaultHome = PhoneStateMonitor.getCurrentDefaultHome();
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        String[] strArr = DEFAULT_HOME_CHANGE_ACTIONS;
        boolean z = false;
        for (int i = 0; i < 4; i++) {
            intentFilter.addAction(strArr[i]);
        }
        broadcastDispatcher.registerReceiver(new BroadcastReceiver() { // from class: com.android.systemui.assist.PhoneStateMonitor.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                PhoneStateMonitor.this.mDefaultHome = PhoneStateMonitor.getCurrentDefaultHome();
            }
        }, intentFilter);
        ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.sInstance.getRunningTask();
        if (runningTask != null && (componentName = runningTask.topActivity) != null) {
            z = componentName.equals(this.mDefaultHome);
        }
        this.mLauncherShowing = z;
        TaskStackChangeListeners.INSTANCE.registerTaskStackListener(new TaskStackChangeListener() { // from class: com.android.systemui.assist.PhoneStateMonitor.2
            @Override // com.android.systemui.shared.system.TaskStackChangeListener
            public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
                ComponentName componentName2;
                PhoneStateMonitor phoneStateMonitor = PhoneStateMonitor.this;
                phoneStateMonitor.getClass();
                phoneStateMonitor.mLauncherShowing = (runningTaskInfo == null || (componentName2 = runningTaskInfo.topActivity) == null) ? false : componentName2.equals(phoneStateMonitor.mDefaultHome);
            }
        });
    }

    public static ComponentName getCurrentDefaultHome() {
        ComponentName componentName;
        ArrayList<ResolveInfo> arrayList = new ArrayList();
        PackageManagerWrapper packageManagerWrapper = PackageManagerWrapper.sInstance;
        try {
            componentName = PackageManagerWrapper.mIPackageManager.getHomeActivities(arrayList);
        } catch (RemoteException e) {
            e.printStackTrace();
            componentName = null;
        }
        if (componentName != null) {
            return componentName;
        }
        int i = Integer.MIN_VALUE;
        while (true) {
            ComponentName componentName2 = null;
            for (ResolveInfo resolveInfo : arrayList) {
                int i2 = resolveInfo.priority;
                if (i2 <= i) {
                    if (i2 == i) {
                        break;
                    }
                } else {
                    componentName2 = resolveInfo.activityInfo.getComponentName();
                    i = resolveInfo.priority;
                }
            }
            return componentName2;
        }
    }

    public final int getPhoneState() {
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        int state = statusBarStateController.getState();
        int i = 2;
        if (state != 1 && state != 2) {
            return this.mLauncherShowing ? 5 : 9;
        }
        if (statusBarStateController.isDozing()) {
            i = 1;
        } else if (((Boolean) ((Optional) this.mCentralSurfacesOptionalLazy.get()).map(new PhoneStateMonitor$$ExternalSyntheticLambda1()).orElse(Boolean.FALSE)).booleanValue()) {
            i = 3;
        } else {
            KeyguardManager keyguardManager = (KeyguardManager) this.mContext.getSystemService(KeyguardManager.class);
            if (keyguardManager == null || !keyguardManager.isKeyguardLocked()) {
                i = 4;
            }
        }
        return i;
    }
}
