package com.google.android.systemui.columbus.legacy.actions;

import android.app.ActivityOptions;
import android.app.IActivityManager;
import android.app.SynchronousUserSwitchObserver;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DeskClockAction extends Action {
    public boolean alertFiring;
    public final DeskClockAction$alertReceiver$1 alertReceiver;
    public boolean receiverRegistered;
    public final SilenceAlertsDisabled silenceAlertsDisabled;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.actions.DeskClockAction$alertReceiver$1] */
    public DeskClockAction(Context context, SilenceAlertsDisabled silenceAlertsDisabled, IActivityManager iActivityManager) {
        super(context, null);
        this.silenceAlertsDisabled = silenceAlertsDisabled;
        Gate.Listener listener = new Gate.Listener() { // from class: com.google.android.systemui.columbus.legacy.actions.DeskClockAction$gateListener$1
            @Override // com.google.android.systemui.columbus.legacy.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                DeskClockAction.this.updateBroadcastReceiver();
            }
        };
        this.alertReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.columbus.legacy.actions.DeskClockAction$alertReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, DeskClockAction.this.getAlertAction())) {
                    DeskClockAction.this.alertFiring = true;
                } else {
                    if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, DeskClockAction.this.getDoneAction())) {
                        DeskClockAction.this.alertFiring = false;
                    }
                }
                DeskClockAction deskClockAction = DeskClockAction.this;
                deskClockAction.setAvailable(deskClockAction.alertFiring);
            }
        };
        SynchronousUserSwitchObserver synchronousUserSwitchObserver = new SynchronousUserSwitchObserver() { // from class: com.google.android.systemui.columbus.legacy.actions.DeskClockAction$userSwitchCallback$1
            public final void onUserSwitching(int i) {
                DeskClockAction.this.updateBroadcastReceiver();
            }
        };
        silenceAlertsDisabled.registerListener(listener);
        try {
            iActivityManager.registerUserSwitchObserver(synchronousUserSwitchObserver, "Columbus/DeskClockAct");
        } catch (RemoteException e) {
            Log.e("Columbus/DeskClockAct", "Failed to register user switch observer", e);
        }
        updateBroadcastReceiver();
    }

    public abstract Intent createDismissIntent();

    public abstract String getAlertAction();

    public abstract String getDoneAction();

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        Intent createDismissIntent = createDismissIntent();
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setDisallowEnterPictureInPictureWhileLaunching(true);
        createDismissIntent.setFlags(268435456);
        createDismissIntent.putExtra("android.intent.extra.REFERRER", Uri.parse("android-app://" + this.context.getPackageName()));
        try {
            this.context.startActivityAsUser(createDismissIntent, makeBasic.toBundle(), UserHandle.CURRENT);
        } catch (ActivityNotFoundException e) {
            Log.e("Columbus/DeskClockAct", "Failed to dismiss alert", e);
        }
        this.alertFiring = false;
        setAvailable(false);
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String toString() {
        return super.toString() + " [receiverRegistered -> " + this.receiverRegistered + "]";
    }

    public final void updateBroadcastReceiver() {
        this.alertFiring = false;
        if (this.receiverRegistered) {
            this.context.unregisterReceiver(this.alertReceiver);
            this.receiverRegistered = false;
        }
        if (!this.silenceAlertsDisabled.isBlocking()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(getAlertAction());
            intentFilter.addAction(getDoneAction());
            this.context.registerReceiverAsUser(this.alertReceiver, UserHandle.CURRENT, intentFilter, "com.android.systemui.permission.SEND_ALERT_BROADCASTS", null, 2);
            this.receiverRegistered = true;
        }
        setAvailable(this.alertFiring);
    }
}
