package com.android.settingslib.bluetooth;

import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class HearingAidDeviceManager$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ HearingAidDeviceManager$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return ((LocalBluetoothProfile) obj) instanceof HapClientProfile;
            case 1:
                return ((LocalBluetoothProfile) obj) instanceof CsipSetCoordinatorProfile;
            default:
                return ((CachedBluetoothDevice) obj).isConnected();
        }
    }
}
