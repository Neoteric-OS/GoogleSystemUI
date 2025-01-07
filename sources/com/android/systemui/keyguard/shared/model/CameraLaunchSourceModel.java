package com.android.systemui.keyguard.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CameraLaunchSourceModel {
    public final long detectedTime;
    public final CameraLaunchType type;

    public CameraLaunchSourceModel(CameraLaunchType cameraLaunchType, int i) {
        cameraLaunchType = (i & 1) != 0 ? CameraLaunchType.IGNORE : cameraLaunchType;
        long currentTimeMillis = System.currentTimeMillis();
        this.type = cameraLaunchType;
        this.detectedTime = currentTimeMillis;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraLaunchSourceModel)) {
            return false;
        }
        CameraLaunchSourceModel cameraLaunchSourceModel = (CameraLaunchSourceModel) obj;
        return this.type == cameraLaunchSourceModel.type && this.detectedTime == cameraLaunchSourceModel.detectedTime;
    }

    public final int hashCode() {
        return Long.hashCode(this.detectedTime) + (this.type.hashCode() * 31);
    }

    public final String toString() {
        return "CameraLaunchSourceModel(type=" + this.type + ", detectedTime=" + this.detectedTime + ")";
    }
}
