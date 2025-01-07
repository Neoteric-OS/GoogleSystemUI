package com.android.systemui.controls.controller;

import android.content.ComponentName;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StructureInfo {
    public static final ComponentName EMPTY_COMPONENT;
    public static final StructureInfo EMPTY_STRUCTURE;
    public final ComponentName componentName;
    public final List controls;
    public final CharSequence structure;

    static {
        ComponentName componentName = new ComponentName("", "");
        EMPTY_COMPONENT = componentName;
        EMPTY_STRUCTURE = new StructureInfo(componentName, "", new ArrayList());
    }

    public StructureInfo(ComponentName componentName, CharSequence charSequence, List list) {
        this.componentName = componentName;
        this.structure = charSequence;
        this.controls = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StructureInfo)) {
            return false;
        }
        StructureInfo structureInfo = (StructureInfo) obj;
        return Intrinsics.areEqual(this.componentName, structureInfo.componentName) && Intrinsics.areEqual(this.structure, structureInfo.structure) && Intrinsics.areEqual(this.controls, structureInfo.controls);
    }

    public final int hashCode() {
        return this.controls.hashCode() + ((this.structure.hashCode() + (this.componentName.hashCode() * 31)) * 31);
    }

    public final String toString() {
        ComponentName componentName = this.componentName;
        CharSequence charSequence = this.structure;
        return "StructureInfo(componentName=" + componentName + ", structure=" + ((Object) charSequence) + ", controls=" + this.controls + ")";
    }
}
