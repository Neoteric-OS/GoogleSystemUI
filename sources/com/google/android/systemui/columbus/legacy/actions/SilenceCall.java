package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.telecom.TelecomManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SilenceCall extends Action {
    public boolean isPhoneRinging;
    public final SilenceCall$phoneStateListener$1 phoneStateListener;
    public final SilenceAlertsDisabled silenceAlertsDisabled;
    public final String tag;
    public final Lazy telecomManager;
    public final Lazy telephonyListenerManager;
    public final Lazy telephonyManager;

    /* JADX WARN: Type inference failed for: r2v2, types: [com.google.android.systemui.columbus.legacy.actions.SilenceCall$phoneStateListener$1] */
    public SilenceCall(Context context, SilenceAlertsDisabled silenceAlertsDisabled, Lazy lazy, Lazy lazy2, Lazy lazy3) {
        super(context, null);
        this.silenceAlertsDisabled = silenceAlertsDisabled;
        this.telecomManager = lazy;
        this.telephonyManager = lazy2;
        this.telephonyListenerManager = lazy3;
        this.tag = "Columbus/SilenceCall";
        this.phoneStateListener = new TelephonyCallback.CallStateListener() { // from class: com.google.android.systemui.columbus.legacy.actions.SilenceCall$phoneStateListener$1
            @Override // android.telephony.TelephonyCallback.CallStateListener
            public final void onCallStateChanged(int i) {
                SilenceCall silenceCall = SilenceCall.this;
                silenceCall.getClass();
                boolean z = false;
                silenceCall.isPhoneRinging = i == 1;
                SilenceCall silenceCall2 = SilenceCall.this;
                if (!silenceCall2.silenceAlertsDisabled.isBlocking() && silenceCall2.isPhoneRinging) {
                    z = true;
                }
                silenceCall2.setAvailable(z);
            }
        };
        silenceAlertsDisabled.registerListener(new Gate.Listener() { // from class: com.google.android.systemui.columbus.legacy.actions.SilenceCall$gateListener$1
            @Override // com.google.android.systemui.columbus.legacy.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                SilenceCall.this.updatePhoneStateListener();
            }
        });
        updatePhoneStateListener();
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        ((TelecomManager) this.telecomManager.get()).silenceRinger();
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String toString() {
        return super.toString() + " [isPhoneRinging -> " + this.isPhoneRinging + "]";
    }

    public final void updatePhoneStateListener() {
        SilenceAlertsDisabled silenceAlertsDisabled = this.silenceAlertsDisabled;
        boolean isBlocking = silenceAlertsDisabled.isBlocking();
        SilenceCall$phoneStateListener$1 silenceCall$phoneStateListener$1 = this.phoneStateListener;
        Lazy lazy = this.telephonyListenerManager;
        if (isBlocking) {
            ((TelephonyListenerManager) lazy.get()).removeCallStateListener(silenceCall$phoneStateListener$1);
        } else {
            ((TelephonyListenerManager) lazy.get()).addCallStateListener(silenceCall$phoneStateListener$1);
        }
        boolean z = false;
        this.isPhoneRinging = ((TelephonyManager) this.telephonyManager.get()).getCallState() == 1;
        if (!silenceAlertsDisabled.isBlocking() && this.isPhoneRinging) {
            z = true;
        }
        setAvailable(z);
    }
}
