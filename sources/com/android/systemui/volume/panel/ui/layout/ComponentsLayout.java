package com.android.systemui.volume.panel.ui.layout;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.volume.panel.ui.viewmodel.ComponentState;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ComponentsLayout {
    public final ComponentState bottomBarComponent;
    public final List contentComponents;
    public final List footerComponents;
    public final List headerComponents;

    public ComponentsLayout(List list, List list2, List list3, ComponentState componentState) {
        this.headerComponents = list;
        this.contentComponents = list2;
        this.footerComponents = list3;
        this.bottomBarComponent = componentState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComponentsLayout)) {
            return false;
        }
        ComponentsLayout componentsLayout = (ComponentsLayout) obj;
        return Intrinsics.areEqual(this.headerComponents, componentsLayout.headerComponents) && Intrinsics.areEqual(this.contentComponents, componentsLayout.contentComponents) && Intrinsics.areEqual(this.footerComponents, componentsLayout.footerComponents) && Intrinsics.areEqual(this.bottomBarComponent, componentsLayout.bottomBarComponent);
    }

    public final int hashCode() {
        return this.bottomBarComponent.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.headerComponents.hashCode() * 31, 31, this.contentComponents), 31, this.footerComponents);
    }

    public final String toString() {
        return "ComponentsLayout(headerComponents=" + this.headerComponents + ", contentComponents=" + this.contentComponents + ", footerComponents=" + this.footerComponents + ", bottomBarComponent=" + this.bottomBarComponent + ")";
    }
}
