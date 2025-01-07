package com.android.systemui.volume.panel.domain.model;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ComponentModel {
    public final boolean isAvailable;
    public final String key;

    public ComponentModel(String str, boolean z) {
        this.key = str;
        this.isAvailable = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComponentModel)) {
            return false;
        }
        ComponentModel componentModel = (ComponentModel) obj;
        return Intrinsics.areEqual(this.key, componentModel.key) && this.isAvailable == componentModel.isAvailable;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isAvailable) + (this.key.hashCode() * 31);
    }

    public final String toString() {
        return "ComponentModel(key=" + this.key + ", isAvailable=" + this.isAvailable + ")";
    }
}
