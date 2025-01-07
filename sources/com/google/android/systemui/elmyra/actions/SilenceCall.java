package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SilenceCall extends Action {
    public final Context mContext;
    public boolean mIsPhoneRinging;
    public final AnonymousClass1 mPhoneStateListener;
    public boolean mSilenceSettingEnabled;
    public final TelecomManager mTelecomManager;
    public final TelephonyListenerManager mTelephonyListenerManager;
    public final TelephonyManager mTelephonyManager;

    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.android.systemui.elmyra.actions.SilenceCall$1] */
    public SilenceCall(Context context, Executor executor, TelephonyListenerManager telephonyListenerManager) {
        super(executor, null);
        this.mPhoneStateListener = new TelephonyCallback.CallStateListener() { // from class: com.google.android.systemui.elmyra.actions.SilenceCall.1
            @Override // android.telephony.TelephonyCallback.CallStateListener
            public final void onCallStateChanged(int i) {
                SilenceCall.this.getClass();
                boolean z = i == 1;
                SilenceCall silenceCall = SilenceCall.this;
                if (silenceCall.mIsPhoneRinging != z) {
                    silenceCall.mIsPhoneRinging = z;
                    silenceCall.notifyListener();
                }
            }
        };
        this.mContext = context;
        this.mTelecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.mTelephonyListenerManager = telephonyListenerManager;
        updatePhoneStateListener();
        new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_silence_alerts_enabled"), new Consumer() { // from class: com.google.android.systemui.elmyra.actions.SilenceCall$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SilenceCall.this.updatePhoneStateListener();
            }
        }, true);
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final boolean isAvailable() {
        if (this.mSilenceSettingEnabled) {
            return this.mIsPhoneRinging;
        }
        return false;
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.mTelecomManager.silenceRinger();
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mSilenceSettingEnabled -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mSilenceSettingEnabled, "]");
    }

    public final void updatePhoneStateListener() {
        boolean z = Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "assist_gesture_silence_alerts_enabled", 1, -2) != 0;
        if (z != this.mSilenceSettingEnabled) {
            this.mSilenceSettingEnabled = z;
            AnonymousClass1 anonymousClass1 = this.mPhoneStateListener;
            TelephonyListenerManager telephonyListenerManager = this.mTelephonyListenerManager;
            if (z) {
                telephonyListenerManager.addCallStateListener(anonymousClass1);
            } else {
                telephonyListenerManager.removeCallStateListener(anonymousClass1);
            }
            this.mIsPhoneRinging = this.mTelephonyManager.getCallState() == 1;
            notifyListener();
        }
    }
}
