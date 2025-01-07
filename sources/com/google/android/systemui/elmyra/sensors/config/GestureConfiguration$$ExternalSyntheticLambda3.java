package com.google.android.systemui.elmyra.sensors.config;

import android.provider.Settings;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class GestureConfiguration$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ GestureConfiguration f$0;

    @Override // java.util.function.Supplier
    public final Object get() {
        return Float.valueOf(Settings.Secure.getFloatForUser(this.f$0.mContext.getContentResolver(), "assist_gesture_sensitivity", 0.5f, -2));
    }
}
