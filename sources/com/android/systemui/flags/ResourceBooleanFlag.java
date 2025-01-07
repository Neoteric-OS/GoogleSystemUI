package com.android.systemui.flags;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ResourceBooleanFlag implements Flag {
    public final String name;
    public final int resourceId;

    public ResourceBooleanFlag(String str, int i) {
        this.name = str;
        this.resourceId = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResourceBooleanFlag)) {
            return false;
        }
        ResourceBooleanFlag resourceBooleanFlag = (ResourceBooleanFlag) obj;
        return this.name.equals(resourceBooleanFlag.name) && "systemui".equals("systemui") && this.resourceId == resourceBooleanFlag.resourceId;
    }

    @Override // com.android.systemui.flags.Flag
    public final String getName() {
        throw null;
    }

    public final int hashCode() {
        return Integer.hashCode(this.resourceId) + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m("systemui", this.name.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ResourceBooleanFlag(name=");
        sb.append(this.name);
        sb.append(", namespace=systemui, resourceId=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.resourceId, ")");
    }
}
