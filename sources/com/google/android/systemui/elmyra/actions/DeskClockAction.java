package com.google.android.systemui.elmyra.actions;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DeskClockAction extends Action {
    public boolean mAlertFiring;
    public final AnonymousClass1 mAlertReceiver;
    public final Context mContext;
    public boolean mReceiverRegistered;

    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.systemui.elmyra.actions.DeskClockAction$1] */
    public DeskClockAction(Context context, Executor executor) {
        super(executor, null);
        this.mAlertReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.elmyra.actions.DeskClockAction.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if (intent.getAction().equals(DeskClockAction.this.getAlertAction())) {
                    DeskClockAction.this.mAlertFiring = true;
                } else if (intent.getAction().equals(DeskClockAction.this.getDoneAction())) {
                    DeskClockAction.this.mAlertFiring = false;
                }
                DeskClockAction.this.notifyListener();
            }
        };
        this.mContext = context;
        updateBroadcastReceiver();
        new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_silence_alerts_enabled"), new Consumer() { // from class: com.google.android.systemui.elmyra.actions.DeskClockAction$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DeskClockAction.this.updateBroadcastReceiver();
            }
        }, true);
    }

    public abstract Intent createDismissIntent();

    public abstract String getAlertAction();

    public abstract String getDoneAction();

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final boolean isAvailable() {
        return this.mAlertFiring;
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        try {
            Intent createDismissIntent = createDismissIntent();
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setDisallowEnterPictureInPictureWhileLaunching(true);
            createDismissIntent.setFlags(268435456);
            createDismissIntent.putExtra("android.intent.extra.REFERRER", Uri.parse("android-app://" + this.mContext.getPackageName()));
            this.mContext.startActivityAsUser(createDismissIntent, makeBasic.toBundle(), UserHandle.CURRENT);
        } catch (ActivityNotFoundException e) {
            Log.e("Elmyra/DeskClockAction", "Failed to dismiss alert", e);
        }
        this.mAlertFiring = false;
        notifyListener();
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mReceiverRegistered -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mReceiverRegistered, "]");
    }

    public final void updateBroadcastReceiver() {
        this.mAlertFiring = false;
        if (this.mReceiverRegistered) {
            this.mContext.unregisterReceiver(this.mAlertReceiver);
            this.mReceiverRegistered = false;
        }
        if (Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "assist_gesture_silence_alerts_enabled", 1, -2) != 0) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(getAlertAction());
            intentFilter.addAction(getDoneAction());
            this.mContext.registerReceiverAsUser(this.mAlertReceiver, UserHandle.CURRENT, intentFilter, "com.android.systemui.permission.SEND_ALERT_BROADCASTS", null, 2);
            this.mReceiverRegistered = true;
        }
        notifyListener();
    }
}
