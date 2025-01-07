package com.google.android.systemui.columbus.legacy.actions;

import android.app.IActivityManager;
import android.app.SynchronousUserSwitchObserver;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.content.res.ColorStateList;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.wm.shell.R;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import com.google.android.systemui.columbus.legacy.gates.KeyguardVisibility;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LaunchApp extends UserAction {
    public final ActivityStarter activityStarter;
    public final Set allowCertList;
    public final Set allowPackageList;
    public final Map availableApps;
    public final Map availableShortcuts;
    public final Handler bgHandler;
    public final LaunchApp$broadcastReceiver$1 broadcastReceiver;
    public ComponentName currentApp;
    public String currentShortcut;
    public final Set denyPackageList;
    public final LaunchApp$deviceConfigPropertiesChangedListener$1 deviceConfigPropertiesChangedListener;
    public final LaunchApp$gateListener$1 gateListener;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final LaunchApp$keyguardUpdateMonitorCallback$1 keyguardUpdateMonitorCallback;
    public final KeyguardVisibility keyguardVisibility;
    public final LauncherApps launcherApps;
    public final Handler mainHandler;
    public final MessageDigest messageDigest;
    public final LaunchApp$onDismissKeyguardAction$1 onDismissKeyguardAction;
    public final LaunchApp$settingsListener$1 settingsListener;
    public final StatusBarKeyguardViewManager statusBarKeyguardViewManager;
    public final String tag;
    public final UiEventLogger uiEventLogger;
    public final UserManager userManager;
    public final UserTracker userTracker;

    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.systemui.columbus.legacy.actions.LaunchApp$settingsListener$1] */
    /* JADX WARN: Type inference failed for: r5v1, types: [com.google.android.systemui.columbus.legacy.actions.LaunchApp$broadcastReceiver$1] */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.google.android.systemui.columbus.legacy.actions.LaunchApp$gateListener$1] */
    /* JADX WARN: Type inference failed for: r5v3, types: [com.google.android.systemui.columbus.legacy.actions.LaunchApp$keyguardUpdateMonitorCallback$1] */
    /* JADX WARN: Type inference failed for: r7v1, types: [com.google.android.systemui.columbus.legacy.actions.LaunchApp$onDismissKeyguardAction$1] */
    public LaunchApp(final Context context, LauncherApps launcherApps, ActivityStarter activityStarter, StatusBarKeyguardViewManager statusBarKeyguardViewManager, IActivityManager iActivityManager, UserManager userManager, ColumbusSettings columbusSettings, KeyguardVisibility keyguardVisibility, KeyguardUpdateMonitor keyguardUpdateMonitor, Handler handler, Handler handler2, Executor executor, UiEventLogger uiEventLogger, UserTracker userTracker) {
        super(context, null);
        this.launcherApps = launcherApps;
        this.activityStarter = activityStarter;
        this.statusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.userManager = userManager;
        this.keyguardVisibility = keyguardVisibility;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mainHandler = handler;
        this.bgHandler = handler2;
        this.uiEventLogger = uiEventLogger;
        this.userTracker = userTracker;
        this.tag = "Columbus/LaunchApp";
        this.settingsListener = new ColumbusSettings.ColumbusSettingsChangeListener() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchApp$settingsListener$1
            @Override // com.google.android.systemui.columbus.legacy.ColumbusSettings.ColumbusSettingsChangeListener
            public final void onSelectedAppChange(String str) {
                ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                LaunchApp launchApp = LaunchApp.this;
                launchApp.currentApp = unflattenFromString;
                launchApp.updateAvailable$4();
            }

            @Override // com.google.android.systemui.columbus.legacy.ColumbusSettings.ColumbusSettingsChangeListener
            public final void onSelectedAppShortcutChange(String str) {
                LaunchApp launchApp = LaunchApp.this;
                launchApp.currentShortcut = str;
                launchApp.updateAvailable$4();
            }
        };
        SynchronousUserSwitchObserver synchronousUserSwitchObserver = new SynchronousUserSwitchObserver() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchApp$userSwitchCallback$1
            public final void onUserSwitching(int i) {
                LaunchApp.this.updateAvailableAppsAndShortcutsAsync();
            }
        };
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchApp$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                LaunchApp.this.updateAvailableAppsAndShortcutsAsync();
            }
        };
        this.gateListener = new Gate.Listener() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchApp$gateListener$1
            @Override // com.google.android.systemui.columbus.legacy.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                if (gate.isBlocking()) {
                    return;
                }
                LaunchApp.this.updateAvailableAppsAndShortcutsAsync();
            }
        };
        this.keyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchApp$keyguardUpdateMonitorCallback$1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onKeyguardBouncerFullyShowingChanged(boolean z) {
                if (z) {
                    final LaunchApp launchApp = LaunchApp.this;
                    launchApp.keyguardUpdateMonitor.removeCallback(this);
                    final Context context2 = context;
                    launchApp.mainHandler.post(new Runnable() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchApp$keyguardUpdateMonitorCallback$1$onKeyguardBouncerFullyShowingChanged$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            LaunchApp.this.statusBarKeyguardViewManager.setKeyguardMessage(context2.getString(R.string.columbus_bouncer_message), ColorStateList.valueOf(-1));
                        }
                    });
                }
            }
        };
        DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchApp$deviceConfigPropertiesChangedListener$1
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                if (properties.getKeyset().contains("systemui_google_columbus_secure_deny_list")) {
                    LaunchApp.this.updateDenyList(properties.getString("systemui_google_columbus_secure_deny_list", (String) null));
                }
            }
        };
        this.onDismissKeyguardAction = new ActivityStarter.OnDismissAction() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchApp$onDismissKeyguardAction$1
            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
            public final boolean onDismiss() {
                ShortcutInfo shortcutInfo;
                LaunchApp launchApp = LaunchApp.this;
                if (launchApp.usingShortcut()) {
                    Map map = launchApp.availableShortcuts;
                    ComponentName componentName = launchApp.currentApp;
                    Map map2 = (Map) map.get(componentName != null ? componentName.getPackageName() : null);
                    if (map2 != null && (shortcutInfo = (ShortcutInfo) map2.get(launchApp.currentShortcut)) != null) {
                        UiEventLogger uiEventLogger2 = launchApp.uiEventLogger;
                        ColumbusEvent columbusEvent = ColumbusEvent.COLUMBUS_INVOKED_LAUNCH_SHORTCUT;
                        ComponentName componentName2 = launchApp.currentApp;
                        uiEventLogger2.log(columbusEvent, 0, componentName2 != null ? componentName2.getPackageName() : null);
                        launchApp.launcherApps.startShortcut(shortcutInfo, null, null);
                    }
                } else {
                    Intent intent = (Intent) launchApp.availableApps.get(launchApp.currentApp);
                    if (intent != null) {
                        UiEventLogger uiEventLogger3 = launchApp.uiEventLogger;
                        ColumbusEvent columbusEvent2 = ColumbusEvent.COLUMBUS_INVOKED_LAUNCH_APP;
                        ComponentName componentName3 = launchApp.currentApp;
                        uiEventLogger3.log(columbusEvent2, 0, componentName3 != null ? componentName3.getPackageName() : null);
                        launchApp.context.startActivityAsUser(intent, ((UserTrackerImpl) launchApp.userTracker).getUserHandle());
                    }
                }
                return false;
            }
        };
        this.messageDigest = MessageDigest.getInstance("SHA-256");
        String[] stringArray = context.getResources().getStringArray(R.array.columbus_sumatra_package_allow_list);
        this.allowPackageList = SetsKt.setOf(Arrays.copyOf(stringArray, stringArray.length));
        String[] stringArray2 = context.getResources().getStringArray(R.array.columbus_sumatra_cert_allow_list);
        this.allowCertList = SetsKt.setOf(Arrays.copyOf(stringArray2, stringArray2.length));
        this.denyPackageList = new LinkedHashSet();
        this.availableApps = new LinkedHashMap();
        this.availableShortcuts = new LinkedHashMap();
        this.currentShortcut = "";
        DeviceConfig.addOnPropertiesChangedListener("systemui", executor, onPropertiesChangedListener);
        updateDenyList(DeviceConfig.getString("systemui", "systemui_google_columbus_secure_deny_list", (String) null));
        try {
            iActivityManager.registerUserSwitchObserver(synchronousUserSwitchObserver, "Columbus/LaunchApp");
        } catch (RemoteException e) {
            Log.e("Columbus/LaunchApp", "Failed to register user switch observer", e);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(this.broadcastReceiver, intentFilter);
        context.registerReceiver(this.broadcastReceiver, new IntentFilter("android.intent.action.BOOT_COMPLETED"));
        updateAvailableAppsAndShortcutsAsync();
        columbusSettings.registerColumbusSettingsChangeListener(this.settingsListener);
        this.currentApp = ComponentName.unflattenFromString(columbusSettings.selectedApp());
        String stringForUser = Settings.Secure.getStringForUser(columbusSettings.contentResolver, "columbus_launch_app_shortcut", ((UserTrackerImpl) columbusSettings.userTracker).getUserId());
        this.currentShortcut = stringForUser != null ? stringForUser : "";
        this.keyguardVisibility.registerListener(this.gateListener);
        updateAvailable$4();
    }

    public static final void access$addShortcutsForApp(LaunchApp launchApp, LauncherActivityInfo launcherActivityInfo, List list) {
        launchApp.getClass();
        if (list != null) {
            ArrayList<ShortcutInfo> arrayList = new ArrayList();
            for (Object obj : list) {
                if (Intrinsics.areEqual(((ShortcutInfo) obj).getPackage(), launcherActivityInfo.getComponentName().getPackageName())) {
                    arrayList.add(obj);
                }
            }
            for (ShortcutInfo shortcutInfo : arrayList) {
                launchApp.availableShortcuts.putIfAbsent(shortcutInfo.getPackage(), new LinkedHashMap());
                Object obj2 = launchApp.availableShortcuts.get(shortcutInfo.getPackage());
                Intrinsics.checkNotNull(obj2);
                ((Map) obj2).put(shortcutInfo.getId(), shortcutInfo);
            }
        }
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00d3  */
    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onTrigger(com.google.android.systemui.columbus.legacy.sensors.GestureSensor.DetectionProperties r20) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.actions.LaunchApp.onTrigger(com.google.android.systemui.columbus.legacy.sensors.GestureSensor$DetectionProperties):void");
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String toString() {
        if (!usingShortcut()) {
            return "Launch " + this.currentApp;
        }
        return "Launch " + this.currentApp + " shortcut " + this.currentShortcut;
    }

    public final void updateAvailable$4() {
        if (!usingShortcut()) {
            setAvailable(this.availableApps.containsKey(this.currentApp));
            return;
        }
        Map map = this.availableShortcuts;
        ComponentName componentName = this.currentApp;
        Map map2 = (Map) map.get(componentName != null ? componentName.getPackageName() : null);
        boolean z = false;
        if (map2 != null && map2.containsKey(this.currentShortcut)) {
            z = true;
        }
        setAvailable(z);
    }

    public final void updateAvailableAppsAndShortcutsAsync() {
        this.bgHandler.post(new LaunchApp$updateAvailableAppsAndShortcutsAsync$1$2(this, 1));
    }

    public final void updateDenyList(String str) {
        this.denyPackageList.clear();
        if (str == null) {
            return;
        }
        Set set = this.denyPackageList;
        List split$default = StringsKt.split$default(str, new String[]{","}, 0, 6);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10));
        Iterator it = split$default.iterator();
        while (it.hasNext()) {
            arrayList.add(StringsKt.trim((String) it.next()).toString());
        }
        set.addAll(arrayList);
    }

    public final boolean usingShortcut() {
        if (this.currentShortcut.length() > 0) {
            String str = this.currentShortcut;
            ComponentName componentName = this.currentApp;
            if (!Intrinsics.areEqual(str, componentName != null ? componentName.flattenToString() : null)) {
                return true;
            }
        }
        return false;
    }
}
