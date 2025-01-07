package com.android.systemui.telephony;

import android.telephony.TelephonyManager;
import dagger.internal.Provider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TelephonyListenerManager_Factory implements Provider {
    public static TelephonyListenerManager newInstance(TelephonyManager telephonyManager, Executor executor, TelephonyCallback telephonyCallback) {
        return new TelephonyListenerManager(telephonyManager, executor, telephonyCallback);
    }
}
