package com.android.systemui.telephony;

import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TelephonyListenerManager {
    public final Executor mExecutor;
    public boolean mListening = false;
    public final TelephonyCallback mTelephonyCallback;
    public final TelephonyManager mTelephonyManager;

    public TelephonyListenerManager(TelephonyManager telephonyManager, Executor executor, TelephonyCallback telephonyCallback) {
        this.mTelephonyManager = telephonyManager;
        this.mExecutor = executor;
        this.mTelephonyCallback = telephonyCallback;
    }

    public final void addCallStateListener(TelephonyCallback.CallStateListener callStateListener) {
        this.mTelephonyCallback.mCallStateListeners.add(callStateListener);
        updateListening();
    }

    public final void removeCallStateListener(TelephonyCallback.CallStateListener callStateListener) {
        this.mTelephonyCallback.mCallStateListeners.remove(callStateListener);
        updateListening();
    }

    public final void updateListening() {
        boolean z = this.mListening;
        TelephonyCallback telephonyCallback = this.mTelephonyCallback;
        if (!z && (!telephonyCallback.mActiveDataSubscriptionIdListeners.isEmpty() || !telephonyCallback.mCallStateListeners.isEmpty() || !telephonyCallback.mServiceStateListeners.isEmpty())) {
            this.mListening = true;
            this.mTelephonyManager.registerTelephonyCallback(this.mExecutor, telephonyCallback);
        } else if (this.mListening && telephonyCallback.mActiveDataSubscriptionIdListeners.isEmpty() && telephonyCallback.mCallStateListeners.isEmpty() && telephonyCallback.mServiceStateListeners.isEmpty()) {
            this.mTelephonyManager.unregisterTelephonyCallback(telephonyCallback);
            this.mListening = false;
        }
    }
}
