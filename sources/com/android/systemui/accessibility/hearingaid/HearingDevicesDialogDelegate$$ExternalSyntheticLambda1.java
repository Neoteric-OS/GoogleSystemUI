package com.android.systemui.accessibility.hearingaid;

import android.bluetooth.BluetoothHapPresetInfo;
import com.android.systemui.bluetooth.qsdialog.DeviceItem;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class HearingDevicesDialogDelegate$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return ((BluetoothHapPresetInfo) obj).getName();
            default:
                return ((DeviceItem) obj).cachedBluetoothDevice;
        }
    }
}
