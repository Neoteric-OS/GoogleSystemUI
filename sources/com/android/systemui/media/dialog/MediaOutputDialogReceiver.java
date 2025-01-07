package com.android.systemui.media.dialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaOutputDialogReceiver extends BroadcastReceiver {
    public final MediaOutputDialogManager mediaOutputDialogManager;

    public MediaOutputDialogReceiver(MediaOutputDialogManager mediaOutputDialogManager, MediaOutputBroadcastDialogManager mediaOutputBroadcastDialogManager) {
        this.mediaOutputDialogManager = mediaOutputDialogManager;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            int hashCode = action.hashCode();
            if (hashCode == -2095758866) {
                if (action.equals("com.android.systemui.action.LAUNCH_SYSTEM_MEDIA_OUTPUT_DIALOG")) {
                    this.mediaOutputDialogManager.createAndShow(null, false, null, false, null, null);
                    return;
                }
                return;
            }
            if (hashCode != 1575256440) {
                if (hashCode != 2052997846) {
                    return;
                }
                action.equals("com.android.systemui.action.LAUNCH_MEDIA_OUTPUT_BROADCAST_DIALOG");
            } else if (action.equals("com.android.systemui.action.LAUNCH_MEDIA_OUTPUT_DIALOG")) {
                String stringExtra = intent.getStringExtra("package_name");
                if (stringExtra != null && stringExtra.length() != 0) {
                    this.mediaOutputDialogManager.createAndShow(stringExtra, false, null, true, null, null);
                } else if (MediaOutputDialogReceiverKt.DEBUG) {
                    Log.e("MediaOutputDlgReceiver", "Unable to launch media output dialog. Package name is empty.");
                }
            }
        }
    }
}
