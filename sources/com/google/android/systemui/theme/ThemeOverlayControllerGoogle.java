package com.google.android.systemui.theme;

import android.R;
import android.app.ActivityManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemProperties;
import android.os.UserManager;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.SystemPropertiesHelper;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.theme.ThemeOverlayApplier;
import com.android.systemui.theme.ThemeOverlayController;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.settings.SecureSettings;
import java.io.PrintWriter;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ThemeOverlayControllerGoogle extends ThemeOverlayController {
    public final Context context;
    public final Resources resources;
    public final SystemPropertiesHelper systemProperties;
    public final UserTracker userTracker;

    public ThemeOverlayControllerGoogle(Context context, BroadcastDispatcher broadcastDispatcher, Handler handler, Executor executor, Executor executor2, ThemeOverlayApplier themeOverlayApplier, SecureSettings secureSettings, SystemPropertiesHelper systemPropertiesHelper, Resources resources, WallpaperManager wallpaperManager, UserManager userManager, DumpManager dumpManager, DeviceProvisionedController deviceProvisionedController, UserTracker userTracker, FeatureFlags featureFlags, WakefulnessLifecycle wakefulnessLifecycle, JavaAdapter javaAdapter, KeyguardTransitionInteractor keyguardTransitionInteractor, UiModeManager uiModeManager, ActivityManager activityManager, ConfigurationController configurationController) {
        super(context, broadcastDispatcher, handler, executor, executor2, themeOverlayApplier, secureSettings, wallpaperManager, userManager, deviceProvisionedController, userTracker, dumpManager, featureFlags, resources, wakefulnessLifecycle, javaAdapter, keyguardTransitionInteractor, uiModeManager, activityManager);
        this.context = context;
        this.systemProperties = systemPropertiesHelper;
        this.resources = resources;
        this.userTracker = userTracker;
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.google.android.systemui.theme.ThemeOverlayControllerGoogle$configurationChangedListener$1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                ThemeOverlayControllerGoogle themeOverlayControllerGoogle = ThemeOverlayControllerGoogle.this;
                if (((UserTrackerImpl) themeOverlayControllerGoogle.userTracker).getUserId() != 0) {
                    return;
                }
                try {
                    int[] bootColors = themeOverlayControllerGoogle.getBootColors();
                    int length = bootColors.length;
                    int i = 0;
                    while (i < length) {
                        int i2 = bootColors[i];
                        i++;
                        themeOverlayControllerGoogle.systemProperties.getClass();
                        SystemProperties.set("persist.bootanim.color" + i, String.valueOf(i2));
                        Log.d("ThemeOverlayController", "Writing boot animation colors " + i + ": " + Integer.toHexString(i2));
                    }
                } catch (RuntimeException unused) {
                    Log.w("ThemeOverlayController", "Cannot set sysprop. Look for 'init' and 'dmesg' logs for more info.");
                }
            }
        });
        int[] bootColors = getBootColors();
        int length = bootColors.length;
        int i = 0;
        while (i < length) {
            int i2 = bootColors[i];
            i++;
            Log.d("ThemeOverlayController", "Boot animation colors " + i + ": " + i2);
        }
    }

    @Override // com.android.systemui.theme.ThemeOverlayController, com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
        printWriter.println("ThemeOverlayControllerGoogle: yes");
    }

    public final int[] getBootColors() {
        int color = this.context.getColor(R.color.system_accent1_500);
        return (Color.red(color) == Color.green(color) && Color.green(color) == Color.blue(color)) ? new int[]{this.resources.getColor(com.android.wm.shell.R.color.super_g_primary_mono, this.context.getTheme()), this.resources.getColor(com.android.wm.shell.R.color.super_g_tertiary_mono, this.context.getTheme()), this.resources.getColor(com.android.wm.shell.R.color.super_g_quaternary_mono, this.context.getTheme()), this.resources.getColor(com.android.wm.shell.R.color.super_g_secondary_mono, this.context.getTheme())} : new int[]{this.resources.getColor(com.android.wm.shell.R.color.super_g_primary, this.context.getTheme()), this.resources.getColor(com.android.wm.shell.R.color.super_g_tertiary, this.context.getTheme()), this.resources.getColor(com.android.wm.shell.R.color.super_g_quaternary, this.context.getTheme()), this.resources.getColor(com.android.wm.shell.R.color.super_g_secondary, this.context.getTheme())};
    }
}
