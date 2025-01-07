package com.android.systemui.flags;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SysPropBooleanFlag implements Flag {

    /* renamed from: default, reason: not valid java name */
    public final boolean f28default;
    public final String name;

    public SysPropBooleanFlag(String str, boolean z) {
        this.name = str;
        this.f28default = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SysPropBooleanFlag)) {
            return false;
        }
        SysPropBooleanFlag sysPropBooleanFlag = (SysPropBooleanFlag) obj;
        return this.name.equals(sysPropBooleanFlag.name) && "systemui".equals("systemui") && this.f28default == sysPropBooleanFlag.f28default;
    }

    @Override // com.android.systemui.flags.Flag
    public final String getName() {
        throw null;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f28default) + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m("systemui", this.name.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SysPropBooleanFlag(name=");
        sb.append(this.name);
        sb.append(", namespace=systemui, default=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.f28default, ")");
    }
}
