package com.android.systemui.accessibility.hearingaid;

import android.bluetooth.BluetoothHapPresetInfo;
import com.android.settingslib.bluetooth.HapClientProfile;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class HearingDevicesPresetsController$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return ((LocalBluetoothProfile) obj) instanceof HapClientProfile;
            default:
                return ((BluetoothHapPresetInfo) obj).isAvailable();
        }
    }
}
