package com.android.systemui.bluetooth.qsdialog;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceItem {
    public final String actionAccessibilityLabel;
    public final Integer background;
    public final CachedBluetoothDevice cachedBluetoothDevice;
    public final String connectionSummary;
    public final String deviceName;
    public final Pair iconWithDescription;
    public final boolean isActive;
    public final boolean isEnabled;
    public final DeviceItemType type;

    public DeviceItem(DeviceItemType deviceItemType, CachedBluetoothDevice cachedBluetoothDevice, String str, String str2, Pair pair, Integer num, boolean z, String str3, boolean z2) {
        this.type = deviceItemType;
        this.cachedBluetoothDevice = cachedBluetoothDevice;
        this.deviceName = str;
        this.connectionSummary = str2;
        this.iconWithDescription = pair;
        this.background = num;
        this.isEnabled = z;
        this.actionAccessibilityLabel = str3;
        this.isActive = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceItem)) {
            return false;
        }
        DeviceItem deviceItem = (DeviceItem) obj;
        return this.type == deviceItem.type && Intrinsics.areEqual(this.cachedBluetoothDevice, deviceItem.cachedBluetoothDevice) && Intrinsics.areEqual(this.deviceName, deviceItem.deviceName) && Intrinsics.areEqual(this.connectionSummary, deviceItem.connectionSummary) && Intrinsics.areEqual(this.iconWithDescription, deviceItem.iconWithDescription) && Intrinsics.areEqual(this.background, deviceItem.background) && this.isEnabled == deviceItem.isEnabled && Intrinsics.areEqual(this.actionAccessibilityLabel, deviceItem.actionAccessibilityLabel) && this.isActive == deviceItem.isActive;
    }

    public final int hashCode() {
        int m = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.connectionSummary, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.deviceName, (this.cachedBluetoothDevice.hashCode() + (this.type.hashCode() * 31)) * 31, 31), 31);
        Pair pair = this.iconWithDescription;
        int hashCode = (m + (pair == null ? 0 : pair.hashCode())) * 31;
        Integer num = this.background;
        return Boolean.hashCode(this.isActive) + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.actionAccessibilityLabel, TransitionData$$ExternalSyntheticOutline0.m((hashCode + (num != null ? num.hashCode() : 0)) * 31, 31, this.isEnabled), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeviceItem(type=");
        sb.append(this.type);
        sb.append(", cachedBluetoothDevice=");
        sb.append(this.cachedBluetoothDevice);
        sb.append(", deviceName=");
        sb.append(this.deviceName);
        sb.append(", connectionSummary=");
        sb.append(this.connectionSummary);
        sb.append(", iconWithDescription=");
        sb.append(this.iconWithDescription);
        sb.append(", background=");
        sb.append(this.background);
        sb.append(", isEnabled=");
        sb.append(this.isEnabled);
        sb.append(", actionAccessibilityLabel=");
        sb.append(this.actionAccessibilityLabel);
        sb.append(", isActive=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isActive, ")");
    }
}
