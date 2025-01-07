package com.android.systemui.media.controls.domain.pipeline;

import com.android.settingslib.media.MediaDevice;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AboutToConnectDevice {
    public final MediaDeviceData backupMediaDeviceData;
    public final MediaDevice fullMediaDevice;

    public AboutToConnectDevice(MediaDevice mediaDevice, MediaDeviceData mediaDeviceData) {
        this.fullMediaDevice = mediaDevice;
        this.backupMediaDeviceData = mediaDeviceData;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AboutToConnectDevice)) {
            return false;
        }
        AboutToConnectDevice aboutToConnectDevice = (AboutToConnectDevice) obj;
        return Intrinsics.areEqual(this.fullMediaDevice, aboutToConnectDevice.fullMediaDevice) && Intrinsics.areEqual(this.backupMediaDeviceData, aboutToConnectDevice.backupMediaDeviceData);
    }

    public final int hashCode() {
        MediaDevice mediaDevice = this.fullMediaDevice;
        int hashCode = (mediaDevice == null ? 0 : mediaDevice.hashCode()) * 31;
        MediaDeviceData mediaDeviceData = this.backupMediaDeviceData;
        return hashCode + (mediaDeviceData != null ? mediaDeviceData.hashCode() : 0);
    }

    public final String toString() {
        return "AboutToConnectDevice(fullMediaDevice=" + this.fullMediaDevice + ", backupMediaDeviceData=" + this.backupMediaDeviceData + ")";
    }
}
