package com.android.systemui.volume.panel.ui.viewmodel;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ComponentState {
    public final ComposeVolumePanelUiComponent component;
    public final boolean isVisible;
    public final String key;

    public ComponentState(String str, ComposeVolumePanelUiComponent composeVolumePanelUiComponent, boolean z) {
        this.key = str;
        this.component = composeVolumePanelUiComponent;
        this.isVisible = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComponentState)) {
            return false;
        }
        ComponentState componentState = (ComponentState) obj;
        return Intrinsics.areEqual(this.key, componentState.key) && Intrinsics.areEqual(this.component, componentState.component) && this.isVisible == componentState.isVisible;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isVisible) + ((this.component.hashCode() + (this.key.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ComponentState(key=");
        sb.append(this.key);
        sb.append(", component=");
        sb.append(this.component);
        sb.append(", isVisible=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isVisible, ")");
    }
}
