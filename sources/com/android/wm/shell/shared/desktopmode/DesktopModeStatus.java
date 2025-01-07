package com.android.wm.shell.shared.desktopmode;

import android.R;
import android.content.Context;
import android.os.SystemProperties;
import android.window.flags.DesktopModeFlags;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DesktopModeStatus {
    public static final int DESKTOP_DENSITY_OVERRIDE;
    public static final boolean DESKTOP_DENSITY_OVERRIDE_ENABLED;
    public static final boolean ENFORCE_DEVICE_RESTRICTIONS;
    public static final boolean IS_VEILED_RESIZE_ENABLED = SystemProperties.getBoolean("persist.wm.debug.desktop_veiled_resizing", true);
    public static final boolean USE_APP_TO_WEB_BUILD_TIME_GENERIC_LINKS;
    public static final boolean USE_ROUNDED_CORNERS;
    public static final boolean USE_WINDOW_SHADOWS;
    public static final boolean USE_WINDOW_SHADOWS_FOCUSED_WINDOW;

    static {
        SystemProperties.getBoolean("persist.wm.debug.desktop_change_display", false);
        USE_WINDOW_SHADOWS = SystemProperties.getBoolean("persist.wm.debug.desktop_use_window_shadows", true);
        USE_WINDOW_SHADOWS_FOCUSED_WINDOW = SystemProperties.getBoolean("persist.wm.debug.desktop_use_window_shadows_focused_window", false);
        USE_ROUNDED_CORNERS = SystemProperties.getBoolean("persist.wm.debug.desktop_use_rounded_corners", true);
        ENFORCE_DEVICE_RESTRICTIONS = SystemProperties.getBoolean("persist.wm.debug.desktop_mode_enforce_device_restrictions", true);
        USE_APP_TO_WEB_BUILD_TIME_GENERIC_LINKS = SystemProperties.getBoolean("persist.wm.debug.use_app_to_web_build_time_generic_links", true);
        DESKTOP_DENSITY_OVERRIDE_ENABLED = SystemProperties.getBoolean("persist.wm.debug.desktop_mode_density_enabled", false);
        DESKTOP_DENSITY_OVERRIDE = SystemProperties.getInt("persist.wm.debug.desktop_mode_density", 284);
    }

    public static boolean canEnterDesktopMode(Context context) {
        if (!enforceDeviceRestrictions() || isDesktopModeSupported(context)) {
            return DesktopModeFlags.ENABLE_DESKTOP_WINDOWING_MODE.isTrue();
        }
        return false;
    }

    public static boolean enforceDeviceRestrictions() {
        return ENFORCE_DEVICE_RESTRICTIONS;
    }

    public static boolean isDesktopModeSupported(Context context) {
        return context.getResources().getBoolean(R.bool.config_keepDreamingWhenUnplugging);
    }

    public static boolean useDesktopOverrideDensity() {
        int i;
        return DESKTOP_DENSITY_OVERRIDE_ENABLED && (i = DESKTOP_DENSITY_OVERRIDE) >= 100 && i <= 1000;
    }
}
