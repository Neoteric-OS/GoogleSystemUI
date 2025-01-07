package com.android.systemui.controls.panels;

import android.content.ComponentName;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SelectedComponentRepository$SelectedComponent {
    public final ComponentName componentName;
    public final boolean isPanel;
    public final String name;

    public SelectedComponentRepository$SelectedComponent(String str, ComponentName componentName, boolean z) {
        this.name = str;
        this.componentName = componentName;
        this.isPanel = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SelectedComponentRepository$SelectedComponent)) {
            return false;
        }
        SelectedComponentRepository$SelectedComponent selectedComponentRepository$SelectedComponent = (SelectedComponentRepository$SelectedComponent) obj;
        return Intrinsics.areEqual(this.name, selectedComponentRepository$SelectedComponent.name) && Intrinsics.areEqual(this.componentName, selectedComponentRepository$SelectedComponent.componentName) && this.isPanel == selectedComponentRepository$SelectedComponent.isPanel;
    }

    public final int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        ComponentName componentName = this.componentName;
        return Boolean.hashCode(this.isPanel) + ((hashCode + (componentName == null ? 0 : componentName.hashCode())) * 31);
    }

    public final String toString() {
        ComponentName componentName = this.componentName;
        StringBuilder sb = new StringBuilder("SelectedComponent(name=");
        sb.append(this.name);
        sb.append(", componentName=");
        sb.append(componentName);
        sb.append(", isPanel=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isPanel, ")");
    }
}
