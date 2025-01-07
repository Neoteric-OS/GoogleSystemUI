package com.android.systemui.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.provider.Settings;
import com.android.systemui.CoreStartable;
import com.android.systemui.plugins.PluginManager;
import com.android.wm.shell.R;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationChannels implements CoreStartable {
    public final Context mContext;

    public NotificationChannels(Context context) {
        this.mContext = context;
    }

    public static void createAll(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        NotificationChannel notificationChannel = new NotificationChannel("BAT", context.getString(R.string.notification_channel_battery), 5);
        notificationChannel.setSound(Uri.parse("file://" + Settings.Global.getString(context.getContentResolver(), "low_battery_sound")), new AudioAttributes.Builder().setContentType(4).setUsage(10).build());
        notificationChannel.setBlockable(true);
        NotificationChannel notificationChannel2 = new NotificationChannel(PluginManager.NOTIFICATION_CHANNEL_ID, context.getString(R.string.notification_channel_alerts), 4);
        NotificationChannel notificationChannel3 = new NotificationChannel("INS", context.getString(R.string.notification_channel_instant), 1);
        NotificationChannel notificationChannel4 = new NotificationChannel("STP", context.getString(R.string.notification_channel_setup), 3);
        notificationChannel4.setSound(null, null);
        notificationManager.createNotificationChannels(Arrays.asList(notificationChannel2, notificationChannel3, notificationChannel4, new NotificationChannel("DSK", context.getString(R.string.notification_channel_storage), context.getPackageManager().hasSystemFeature("android.software.leanback") ? 3 : 2), createScreenshotChannel(context.getString(R.string.notification_channel_screenshot)), notificationChannel, new NotificationChannel("HNT", context.getString(R.string.notification_channel_hints), 3)));
        if (context.getPackageManager().hasSystemFeature("android.software.leanback")) {
            notificationManager.createNotificationChannel(new NotificationChannel("TVPIP", context.getString(R.string.notification_channel_tv_pip), 5));
        }
    }

    public static NotificationChannel createScreenshotChannel(String str) {
        NotificationChannel notificationChannel = new NotificationChannel("SCN_HEADSUP", str, 4);
        notificationChannel.setSound(null, new AudioAttributes.Builder().setUsage(5).build());
        notificationChannel.setBlockable(true);
        return notificationChannel;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        createAll(this.mContext);
        ((NotificationManager) this.mContext.getSystemService(NotificationManager.class)).deleteNotificationChannel("GEN");
    }
}
