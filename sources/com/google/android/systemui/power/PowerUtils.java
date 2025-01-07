package com.google.android.systemui.power;

import android.R;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import com.android.systemui.util.settings.SecureSettings;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PowerUtils {
    public static PendingIntent createBatterySettingsPendingIntentAsUser(Context context) {
        return PendingIntent.getActivityAsUser(context, 0, new Intent("android.intent.action.POWER_USAGE_SUMMARY"), 67108864, null, UserHandle.CURRENT);
    }

    public static PendingIntent createHelpArticlePendingIntentAsUser(int i, Context context) {
        return PendingIntent.getActivityAsUser(context, 0, new Intent("android.intent.action.VIEW", Uri.parse(context.getString(i))), 67108864, null, UserHandle.CURRENT);
    }

    public static PendingIntent createPendingIntent(Context context, String str, Bundle bundle) {
        Intent flags = new Intent(str).setPackage(context.getPackageName()).setFlags(1342177280);
        if (bundle != null) {
            flags.putExtras(bundle);
        }
        return PendingIntent.getBroadcastAsUser(context, 0, flags, bundle != null ? 335544320 : 67108864, UserHandle.CURRENT);
    }

    public static Locale getLocale(Context context) {
        LocaleList locales = context.getResources().getConfiguration().getLocales();
        return (locales == null || locales.isEmpty()) ? Locale.getDefault() : locales.get(0);
    }

    public static boolean isChargeLimitEnabledForUser(SecureSettings secureSettings, int i) {
        return secureSettings.getIntForUser("charge_optimization_mode", 0, i) == 1;
    }

    public static boolean isFlipendoEnabled(ContentResolver contentResolver) {
        try {
            Bundle call = contentResolver.call("com.google.android.flipendo.api", "get_flipendo_state", (String) null, Bundle.EMPTY);
            if (call != null) {
                return call.getBoolean("flipendo_state", false);
            }
            return false;
        } catch (Exception e) {
            Log.e("PowerUtils", "isFlipendoEnabled() failed", e);
            return false;
        }
    }

    public static void overrideNotificationAppName(Context context, NotificationCompat$Builder notificationCompat$Builder) {
        Bundle bundle = new Bundle(1);
        bundle.putString("android.substName", context.getString(R.string.android_upgrading_complete));
        notificationCompat$Builder.addExtras(bundle);
    }
}
