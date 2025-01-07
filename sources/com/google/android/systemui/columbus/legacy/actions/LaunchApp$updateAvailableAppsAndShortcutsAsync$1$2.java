package com.google.android.systemui.columbus.legacy.actions;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import com.android.app.tracing.TraceUtilsKt;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LaunchApp$updateAvailableAppsAndShortcutsAsync$1$2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LaunchApp this$0;

    public /* synthetic */ LaunchApp$updateAvailableAppsAndShortcutsAsync$1$2(LaunchApp launchApp, int i) {
        this.$r8$classId = i;
        this.this$0 = launchApp;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List<ShortcutInfo> list;
        switch (this.$r8$classId) {
            case 0:
                this.this$0.updateAvailable$4();
                return;
            default:
                boolean isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("updateAvailableAppsAndShortcutsAsync");
                }
                try {
                    int currentUser = ActivityManager.getCurrentUser();
                    if (this.this$0.userManager.isUserUnlocked(currentUser)) {
                        this.this$0.availableApps.clear();
                        this.this$0.availableShortcuts.clear();
                        List<LauncherActivityInfo> activityList = this.this$0.launcherApps.getActivityList(null, UserHandle.of(currentUser));
                        LaunchApp launchApp = this.this$0;
                        launchApp.getClass();
                        LauncherApps.ShortcutQuery shortcutQuery = new LauncherApps.ShortcutQuery();
                        shortcutQuery.setQueryFlags(9);
                        try {
                            list = launchApp.launcherApps.getShortcuts(shortcutQuery, UserHandle.of(currentUser));
                        } catch (Exception e) {
                            if (!(e instanceof SecurityException) && !(e instanceof IllegalStateException)) {
                                throw e;
                            }
                            Log.e("Columbus/LaunchApp", "Failed to query for shortcuts", e);
                            list = null;
                        }
                        for (LauncherActivityInfo launcherActivityInfo : activityList) {
                            String str = "getMainActivityLaunchIntent component=" + launcherActivityInfo.getComponentName();
                            boolean isEnabled2 = Trace.isEnabled();
                            if (isEnabled2) {
                                TraceUtilsKt.beginSlice(str);
                            }
                            try {
                                PendingIntent mainActivityLaunchIntent = this.this$0.launcherApps.getMainActivityLaunchIntent(launcherActivityInfo.getComponentName(), null, UserHandle.of(currentUser));
                                if (mainActivityLaunchIntent != null) {
                                    Intent intent = new Intent(mainActivityLaunchIntent.getIntent());
                                    intent.putExtra("systemui_google_quick_tap_is_source", true);
                                    this.this$0.availableApps.put(launcherActivityInfo.getComponentName(), intent);
                                    LaunchApp.access$addShortcutsForApp(this.this$0, launcherActivityInfo, list);
                                }
                            } catch (RuntimeException unused) {
                            } catch (Throwable th) {
                                if (isEnabled2) {
                                    TraceUtilsKt.endSlice();
                                }
                                throw th;
                            }
                            if (isEnabled2) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                        LaunchApp launchApp2 = this.this$0;
                        launchApp2.mainHandler.post(new LaunchApp$updateAvailableAppsAndShortcutsAsync$1$2(launchApp2, 0));
                    } else {
                        Log.d("Columbus/LaunchApp", "Did not update apps and shortcuts, user " + currentUser + " not unlocked");
                    }
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                    throw th2;
                }
        }
    }
}
