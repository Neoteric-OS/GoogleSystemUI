package com.android.systemui;

import android.view.DisplayCutout;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SysUICutoutInformation {
    public final CameraProtectionInfo cameraProtection;
    public final DisplayCutout cutout;

    public SysUICutoutInformation(DisplayCutout displayCutout, CameraProtectionInfo cameraProtectionInfo) {
        this.cutout = displayCutout;
        this.cameraProtection = cameraProtectionInfo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SysUICutoutInformation)) {
            return false;
        }
        SysUICutoutInformation sysUICutoutInformation = (SysUICutoutInformation) obj;
        return Intrinsics.areEqual(this.cutout, sysUICutoutInformation.cutout) && Intrinsics.areEqual(this.cameraProtection, sysUICutoutInformation.cameraProtection);
    }

    public final int hashCode() {
        int hashCode = this.cutout.hashCode() * 31;
        CameraProtectionInfo cameraProtectionInfo = this.cameraProtection;
        return hashCode + (cameraProtectionInfo == null ? 0 : cameraProtectionInfo.hashCode());
    }

    public final String toString() {
        return "SysUICutoutInformation(cutout=" + this.cutout + ", cameraProtection=" + this.cameraProtection + ")";
    }
}
