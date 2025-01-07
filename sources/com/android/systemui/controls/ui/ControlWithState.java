package com.android.systemui.controls.ui;

import android.content.ComponentName;
import android.service.controls.Control;
import com.android.systemui.controls.controller.ControlInfo;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlWithState {
    public final ControlInfo ci;
    public final ComponentName componentName;
    public final Control control;

    public ControlWithState(ComponentName componentName, ControlInfo controlInfo, Control control) {
        this.componentName = componentName;
        this.ci = controlInfo;
        this.control = control;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlWithState)) {
            return false;
        }
        ControlWithState controlWithState = (ControlWithState) obj;
        return Intrinsics.areEqual(this.componentName, controlWithState.componentName) && Intrinsics.areEqual(this.ci, controlWithState.ci) && Intrinsics.areEqual(this.control, controlWithState.control);
    }

    public final int hashCode() {
        int hashCode = (this.ci.hashCode() + (this.componentName.hashCode() * 31)) * 31;
        Control control = this.control;
        return hashCode + (control == null ? 0 : control.hashCode());
    }

    public final String toString() {
        return "ControlWithState(componentName=" + this.componentName + ", ci=" + this.ci + ", control=" + this.control + ")";
    }
}
