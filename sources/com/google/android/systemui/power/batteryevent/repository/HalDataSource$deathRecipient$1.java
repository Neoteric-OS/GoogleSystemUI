package com.google.android.systemui.power.batteryevent.repository;

import android.os.IBinder;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HalDataSource$deathRecipient$1 implements IBinder.DeathRecipient {
    public static final HalDataSource$deathRecipient$1 INSTANCE = new HalDataSource$deathRecipient$1();

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        Log.e("GoogleBatteryDataSource", "Service died!!");
    }
}
