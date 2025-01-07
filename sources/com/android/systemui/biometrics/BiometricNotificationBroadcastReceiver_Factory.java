package com.android.systemui.biometrics;

import android.content.Context;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricNotificationBroadcastReceiver_Factory implements Provider {
    public static BiometricNotificationBroadcastReceiver newInstance(Context context, BiometricNotificationDialogFactory biometricNotificationDialogFactory) {
        return new BiometricNotificationBroadcastReceiver(context, biometricNotificationDialogFactory);
    }
}
