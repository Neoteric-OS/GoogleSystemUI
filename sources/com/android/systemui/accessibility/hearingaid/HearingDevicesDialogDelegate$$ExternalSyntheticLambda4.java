package com.android.systemui.accessibility.hearingaid;

import com.android.systemui.bluetooth.qsdialog.DeviceItem;
import com.android.systemui.bluetooth.qsdialog.DeviceItemType;
import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class HearingDevicesDialogDelegate$$ExternalSyntheticLambda4 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        DeviceItem deviceItem = (DeviceItem) obj;
        switch (this.$r8$classId) {
            case 0:
                return deviceItem.type == DeviceItemType.ACTIVE_MEDIA_BLUETOOTH_DEVICE;
            default:
                return Objects.nonNull(deviceItem);
        }
    }
}
