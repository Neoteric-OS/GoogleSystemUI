package com.android.systemui.controls;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import android.service.controls.Control;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlStatus implements ControlInterface {
    public final ComponentName component;
    public final Control control;
    public boolean favorite;
    public final boolean removed;

    public ControlStatus(Control control, ComponentName componentName, boolean z, boolean z2) {
        this.control = control;
        this.component = componentName;
        this.favorite = z;
        this.removed = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlStatus)) {
            return false;
        }
        ControlStatus controlStatus = (ControlStatus) obj;
        return this.control.equals(controlStatus.control) && Intrinsics.areEqual(this.component, controlStatus.component) && this.favorite == controlStatus.favorite && this.removed == controlStatus.removed;
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final ComponentName getComponent() {
        return this.component;
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final String getControlId() {
        return this.control.getControlId();
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final Icon getCustomIcon() {
        return this.control.getCustomIcon();
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final int getDeviceType() {
        return this.control.getDeviceType();
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final boolean getFavorite() {
        return this.favorite;
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final boolean getRemoved() {
        return this.removed;
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final CharSequence getSubtitle() {
        return this.control.getSubtitle();
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final CharSequence getTitle() {
        return this.control.getTitle();
    }

    public final int hashCode() {
        return Boolean.hashCode(this.removed) + TransitionData$$ExternalSyntheticOutline0.m((this.component.hashCode() + (this.control.hashCode() * 31)) * 31, 31, this.favorite);
    }

    public final String toString() {
        Control control = this.control;
        ComponentName componentName = this.component;
        boolean z = this.favorite;
        StringBuilder sb = new StringBuilder("ControlStatus(control=");
        sb.append(control);
        sb.append(", component=");
        sb.append(componentName);
        sb.append(", favorite=");
        sb.append(z);
        sb.append(", removed=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.removed, ")");
    }
}
