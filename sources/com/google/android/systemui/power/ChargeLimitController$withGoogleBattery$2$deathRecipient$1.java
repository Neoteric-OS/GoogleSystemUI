package com.google.android.systemui.power;

import android.os.IBinder;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChargeLimitController$withGoogleBattery$2$deathRecipient$1 implements IBinder.DeathRecipient {
    public static final ChargeLimitController$withGoogleBattery$2$deathRecipient$1 INSTANCE = new ChargeLimitController$withGoogleBattery$2$deathRecipient$1();

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        Log.e("ChargeLimitController", "Service died!!");
    }
}
