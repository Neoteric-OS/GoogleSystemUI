package com.google.android.systemui.elmyra.gates;

import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import com.android.systemui.telephony.TelephonyListenerManager;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TelephonyActivity extends Gate {
    public boolean mIsCallBlocked;
    public final AnonymousClass1 mPhoneStateListener;
    public final TelephonyListenerManager mTelephonyListenerManager;
    public final TelephonyManager mTelephonyManager;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.elmyra.gates.TelephonyActivity$1] */
    public TelephonyActivity(Executor executor, TelephonyManager telephonyManager, TelephonyListenerManager telephonyListenerManager) {
        super(executor);
        this.mPhoneStateListener = new TelephonyCallback.CallStateListener() { // from class: com.google.android.systemui.elmyra.gates.TelephonyActivity.1
            @Override // android.telephony.TelephonyCallback.CallStateListener
            public final void onCallStateChanged(int i) {
                TelephonyActivity.this.getClass();
                boolean z = i == 2;
                TelephonyActivity telephonyActivity = TelephonyActivity.this;
                if (z != telephonyActivity.mIsCallBlocked) {
                    telephonyActivity.mIsCallBlocked = z;
                    telephonyActivity.notifyListener();
                }
            }
        };
        this.mTelephonyManager = telephonyManager;
        this.mTelephonyListenerManager = telephonyListenerManager;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        return this.mIsCallBlocked;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        this.mIsCallBlocked = this.mTelephonyManager.getCallState() == 2;
        this.mTelephonyListenerManager.addCallStateListener(this.mPhoneStateListener);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        this.mTelephonyListenerManager.removeCallStateListener(this.mPhoneStateListener);
    }
}
