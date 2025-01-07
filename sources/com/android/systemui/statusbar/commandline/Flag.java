package com.android.systemui.statusbar.commandline;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Flag implements Describable {
    public final String description;
    public boolean inner;
    public final String longName;
    public final String shortName;

    public Flag(String str, String str2, String str3) {
        this.shortName = str;
        this.longName = str2;
        this.description = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Flag)) {
            return false;
        }
        Flag flag = (Flag) obj;
        return Intrinsics.areEqual(this.shortName, flag.shortName) && Intrinsics.areEqual(this.longName, flag.longName) && Intrinsics.areEqual(this.description, flag.description);
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final String getDescription() {
        return this.description;
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final String getLongName() {
        return this.longName;
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final String getShortName() {
        return this.shortName;
    }

    public final int hashCode() {
        String str = this.shortName;
        int m = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.longName, (str == null ? 0 : str.hashCode()) * 31, 31);
        String str2 = this.description;
        return m + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Flag(shortName=");
        sb.append(this.shortName);
        sb.append(", longName=");
        sb.append(this.longName);
        sb.append(", description=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.description, ")");
    }
}
