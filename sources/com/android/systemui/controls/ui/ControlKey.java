package com.android.systemui.controls.ui;

import android.content.ComponentName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlKey {
    public final ComponentName componentName;
    public final String controlId;

    public ControlKey(ComponentName componentName, String str) {
        this.componentName = componentName;
        this.controlId = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlKey)) {
            return false;
        }
        ControlKey controlKey = (ControlKey) obj;
        return Intrinsics.areEqual(this.componentName, controlKey.componentName) && Intrinsics.areEqual(this.controlId, controlKey.controlId);
    }

    public final int hashCode() {
        return this.controlId.hashCode() + (this.componentName.hashCode() * 31);
    }

    public final String toString() {
        return "ControlKey(componentName=" + this.componentName + ", controlId=" + this.controlId + ")";
    }
}
