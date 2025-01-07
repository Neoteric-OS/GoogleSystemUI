package com.android.systemui.common.shared.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Color$Attribute {
    public final int attribute;

    public Color$Attribute(int i) {
        this.attribute = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Color$Attribute) && this.attribute == ((Color$Attribute) obj).attribute;
    }

    public final int hashCode() {
        return Integer.hashCode(this.attribute);
    }

    public final String toString() {
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("Attribute(attribute="), this.attribute, ")");
    }
}
