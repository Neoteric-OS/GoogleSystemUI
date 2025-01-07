package com.android.settingslib.bluetooth;

import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CachedBluetoothDeviceManager$$ExternalSyntheticLambda0 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((CachedBluetoothDevice) obj).mDevice.getBondState() == 10;
    }
}
