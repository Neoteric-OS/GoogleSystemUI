package com.android.systemui.flags;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ReleasedFlag extends BooleanFlag {
    public final String name;
    public final String namespace;

    public ReleasedFlag(String str, String str2) {
        super(str, str2, true, false, false);
        this.name = str;
        this.namespace = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReleasedFlag)) {
            return false;
        }
        ReleasedFlag releasedFlag = (ReleasedFlag) obj;
        return this.name.equals(releasedFlag.name) && this.namespace.equals(releasedFlag.namespace);
    }

    @Override // com.android.systemui.flags.BooleanFlag, com.android.systemui.flags.Flag
    public final String getName() {
        return this.name;
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public final String getNamespace() {
        return this.namespace;
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public final boolean getOverridden() {
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.namespace, this.name.hashCode() * 31, 31);
    }

    public final String toString() {
        return MotionLayout$$ExternalSyntheticOutline0.m("ReleasedFlag(name=", this.name, ", namespace=", this.namespace, ", overridden=false)");
    }
}
