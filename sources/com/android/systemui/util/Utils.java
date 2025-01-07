package com.android.systemui.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.view.DisplayCutout;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Utils {
    public static Boolean sUseQsMediaPlayer;

    public static int getStatusBarHeaderHeightKeyguard(Context context) {
        int statusBarHeight = SystemBarUtils.getStatusBarHeight(context);
        DisplayCutout cutout = context.getDisplay().getCutout();
        return Math.max(statusBarHeight, context.getResources().getDimensionPixelSize(R.dimen.status_bar_header_height_keyguard) + (cutout == null ? 0 : cutout.getWaterfallInsets().top));
    }

    public static boolean isGesturalModeOnDefaultDisplay(Context context, DisplayTracker displayTracker, int i) {
        int displayId = context.getDisplayId();
        displayTracker.getClass();
        return displayId == 0 && QuickStepContract.isGesturalMode(i);
    }

    public static boolean isHeadlessRemoteDisplayProvider(PackageManager packageManager, String str) {
        if (packageManager.checkPermission("android.permission.REMOTE_DISPLAY_PROVIDER", str) != 0) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        return packageManager.queryIntentActivities(intent, 0).isEmpty();
    }

    public static boolean useMediaResumption(Context context) {
        return useQsMediaPlayer(context) && Settings.Secure.getInt(context.getContentResolver(), "qs_media_resumption", 1) > 0;
    }

    public static boolean useQsMediaPlayer(Context context) {
        if (sUseQsMediaPlayer == null) {
            sUseQsMediaPlayer = Boolean.valueOf(Settings.Global.getInt(context.getContentResolver(), "qs_media_controls", 1) > 0 && context.getResources().getBoolean(android.R.bool.config_reverseDefaultRotation));
        }
        return sUseQsMediaPlayer.booleanValue();
    }
}
