package com.android.wm.shell.draganddrop;

import android.app.PendingIntent;
import android.content.ClipData;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DragUtils {
    public static PendingIntent getLaunchIntent(ClipData clipData, int i) {
        if ((i & 8192) == 0) {
            return null;
        }
        for (int i2 = 0; i2 < clipData.getItemCount(); i2++) {
            ClipData.Item itemAt = clipData.getItemAt(i2);
            if (itemAt.getIntentSender() != null) {
                PendingIntent pendingIntent = new PendingIntent(itemAt.getIntentSender().getTarget());
                if (pendingIntent.isActivity()) {
                    return pendingIntent;
                }
            }
        }
        return null;
    }
}
