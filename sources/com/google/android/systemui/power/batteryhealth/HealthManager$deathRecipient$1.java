package com.google.android.systemui.power.batteryhealth;

import android.os.IBinder;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HealthManager$deathRecipient$1 implements IBinder.DeathRecipient {
    public static final HealthManager$deathRecipient$1 INSTANCE = new HealthManager$deathRecipient$1();

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        Log.w("HealthManager", "HW binder died");
    }
}
