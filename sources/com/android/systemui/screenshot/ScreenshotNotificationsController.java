package com.android.systemui.screenshot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.UserHandle;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.plugins.PluginManager;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotNotificationsController {
    public final Context context;
    public final DevicePolicyManager devicePolicyManager;
    public final int displayId;
    public final NotificationManager notificationManager;
    public final Resources res;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Factory {
    }

    public ScreenshotNotificationsController(int i, Context context, NotificationManager notificationManager, DevicePolicyManager devicePolicyManager) {
        this.displayId = i;
        this.context = context;
        this.notificationManager = notificationManager;
        this.devicePolicyManager = devicePolicyManager;
        this.res = context.getResources();
    }

    public final void notifyScreenshotError(int i) {
        int i2 = this.displayId;
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(this.res.getString(i), i2 != 0 ? DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(" (", this.res.getString(R.string.screenshot_failed_external_display_indication), ")") : "");
        Notification.Builder color = new Notification.Builder(this.context, PluginManager.NOTIFICATION_CHANNEL_ID).setTicker(this.res.getString(R.string.screenshot_failed_title)).setContentTitle(this.res.getString(R.string.screenshot_failed_title)).setContentText(m).setSmallIcon(R.drawable.stat_notify_image_error).setWhen(System.currentTimeMillis()).setVisibility(1).setCategory("err").setAutoCancel(true).setColor(this.context.getColor(android.R.color.system_notification_accent_color));
        Intent createAdminSupportIntent = this.devicePolicyManager.createAdminSupportIntent("policy_disable_screen_capture");
        if (createAdminSupportIntent != null) {
            color.setContentIntent(PendingIntent.getActivityAsUser(this.context, 0, createAdminSupportIntent, 67108864, null, UserHandle.CURRENT));
        }
        SystemUIApplication.overrideNotificationAppName(this.context, color, true);
        this.notificationManager.notify(i2 != 0 ? 1008 : 1, new Notification.BigTextStyle(color).bigText(m).build());
    }
}
