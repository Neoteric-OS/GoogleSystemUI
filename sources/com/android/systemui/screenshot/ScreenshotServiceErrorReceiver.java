package com.android.systemui.screenshot;

import android.app.NotificationManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ScreenshotServiceErrorReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        new ScreenshotNotificationsController(0, context, (NotificationManager) context.getSystemService(NotificationManager.class), (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class)).notifyScreenshotError(R.string.screenshot_failed_to_save_unknown_text);
    }
}
