package com.android.systemui.screenshot;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class SmartActionsReceiver extends BroadcastReceiver {
    public final ScreenshotSmartActions mScreenshotSmartActions;

    public SmartActionsReceiver(ScreenshotSmartActions screenshotSmartActions) {
        this.mScreenshotSmartActions = screenshotSmartActions;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("android:screenshot_action_intent", PendingIntent.class);
        Intent intent2 = (Intent) intent.getParcelableExtra("android:screenshot_action_intent_fillin", Intent.class);
        String stringExtra = intent.getStringExtra("android:screenshot_action_type");
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setPendingIntentBackgroundActivityStartMode(1);
        try {
            pendingIntent.send(context, 0, intent2, null, null, null, makeBasic.toBundle());
        } catch (PendingIntent.CanceledException e) {
            Log.e("SmartActionsReceiver", "Pending intent canceled", e);
        }
        ScreenshotSmartActions screenshotSmartActions = this.mScreenshotSmartActions;
        String stringExtra2 = intent.getStringExtra("android:screenshot_id");
        Intent intent3 = pendingIntent.getIntent();
        screenshotSmartActions.getClass();
        try {
            ((ScreenshotNotificationSmartActionsProvider) screenshotSmartActions.mScreenshotNotificationSmartActionsProviderProvider.get()).notifyAction(stringExtra2, stringExtra, intent3);
        } catch (Throwable th) {
            Log.e("Screenshot", "Error in notifyScreenshotAction: ", th);
        }
    }
}
