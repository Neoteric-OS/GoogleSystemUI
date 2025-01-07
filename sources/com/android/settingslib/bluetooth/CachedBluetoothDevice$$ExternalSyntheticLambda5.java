package com.android.settingslib.bluetooth;

import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CachedBluetoothDevice$$ExternalSyntheticLambda5 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ CachedBluetoothDevice$$ExternalSyntheticLambda5(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return ((LocalBluetoothProfile) obj) instanceof LeAudioProfile;
            case 1:
                return Objects.nonNull((CachedBluetoothDevice) obj);
            case 2:
                return ((CachedBluetoothDevice) obj).mDevice.isConnected();
            case 3:
                return ((Integer) obj).intValue() > -1;
            case 4:
                return ((LocalBluetoothProfile) obj) instanceof HidProfile;
            default:
                return ((Integer) obj).intValue() > -1;
        }
    }
}
