package com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Color$Attribute;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConnectedDeviceViewModel {
    public final CharSequence deviceName;
    public final Color$Attribute deviceNameColor;
    public final CharSequence label;
    public final Color$Attribute labelColor;

    public ConnectedDeviceViewModel(CharSequence charSequence, Color$Attribute color$Attribute, CharSequence charSequence2, Color$Attribute color$Attribute2) {
        this.label = charSequence;
        this.labelColor = color$Attribute;
        this.deviceName = charSequence2;
        this.deviceNameColor = color$Attribute2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConnectedDeviceViewModel)) {
            return false;
        }
        ConnectedDeviceViewModel connectedDeviceViewModel = (ConnectedDeviceViewModel) obj;
        return this.label.equals(connectedDeviceViewModel.label) && this.labelColor.equals(connectedDeviceViewModel.labelColor) && Intrinsics.areEqual(this.deviceName, connectedDeviceViewModel.deviceName) && this.deviceNameColor.equals(connectedDeviceViewModel.deviceNameColor);
    }

    public final int hashCode() {
        int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.labelColor.attribute, this.label.hashCode() * 31, 31);
        CharSequence charSequence = this.deviceName;
        return Integer.hashCode(this.deviceNameColor.attribute) + ((m + (charSequence == null ? 0 : charSequence.hashCode())) * 31);
    }

    public final String toString() {
        return "ConnectedDeviceViewModel(label=" + ((Object) this.label) + ", labelColor=" + this.labelColor + ", deviceName=" + ((Object) this.deviceName) + ", deviceNameColor=" + this.deviceNameColor + ")";
    }
}
