package com.android.systemui.controls.management;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StructureContainer {
    public final AllModel model;
    public final CharSequence structureName;

    public StructureContainer(CharSequence charSequence, AllModel allModel) {
        this.structureName = charSequence;
        this.model = allModel;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StructureContainer)) {
            return false;
        }
        StructureContainer structureContainer = (StructureContainer) obj;
        return Intrinsics.areEqual(this.structureName, structureContainer.structureName) && this.model.equals(structureContainer.model);
    }

    public final int hashCode() {
        return this.model.hashCode() + (this.structureName.hashCode() * 31);
    }

    public final String toString() {
        return "StructureContainer(structureName=" + ((Object) this.structureName) + ", model=" + this.model + ")";
    }
}
