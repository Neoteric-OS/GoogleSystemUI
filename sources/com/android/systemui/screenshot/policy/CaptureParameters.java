package com.android.systemui.screenshot.policy;

import android.content.ComponentName;
import android.os.UserHandle;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CaptureParameters {
    public final ComponentName component;
    public final UserHandle owner;
    public final CaptureType type;

    public CaptureParameters(CaptureType captureType, ComponentName componentName, UserHandle userHandle) {
        this.type = captureType;
        this.component = componentName;
        this.owner = userHandle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CaptureParameters)) {
            return false;
        }
        CaptureParameters captureParameters = (CaptureParameters) obj;
        return Intrinsics.areEqual(this.type, captureParameters.type) && Intrinsics.areEqual(this.component, captureParameters.component) && Intrinsics.areEqual(this.owner, captureParameters.owner);
    }

    public final int hashCode() {
        int hashCode = this.type.hashCode() * 31;
        ComponentName componentName = this.component;
        return this.owner.hashCode() + ((hashCode + (componentName == null ? 0 : componentName.hashCode())) * 31);
    }

    public final String toString() {
        return "CaptureParameters(type=" + this.type + ", component=" + this.component + ", owner=" + this.owner + ")";
    }
}
