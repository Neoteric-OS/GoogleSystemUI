package com.android.settingslib.bluetooth;

import java.util.function.ToIntFunction;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CachedBluetoothDevice$$ExternalSyntheticLambda2 implements ToIntFunction {
    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((CachedBluetoothDevice) obj).mDevice.getBatteryLevel();
    }
}
