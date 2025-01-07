package com.android.systemui.flags;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UnreleasedFlag extends BooleanFlag {
    public final String name;

    public UnreleasedFlag(String str) {
        super(str, "systemui", false, false, false);
        this.name = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UnreleasedFlag) && this.name.equals(((UnreleasedFlag) obj).name) && "systemui".equals("systemui");
    }

    @Override // com.android.systemui.flags.BooleanFlag, com.android.systemui.flags.Flag
    public final String getName() {
        return this.name;
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public final String getNamespace() {
        return "systemui";
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public final boolean getOverridden() {
        return false;
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public final boolean getTeamfood() {
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + TransitionData$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m("systemui", this.name.hashCode() * 31, 31), 31, false);
    }

    public final String toString() {
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("UnreleasedFlag(name=", this.name, ", namespace=systemui, teamfood=false, overridden=false)");
    }
}
