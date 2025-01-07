package com.android.systemui.animation;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DialogCuj {
    public final int cujType;
    public final String tag;

    public DialogCuj(int i, String str) {
        this.cujType = i;
        this.tag = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DialogCuj)) {
            return false;
        }
        DialogCuj dialogCuj = (DialogCuj) obj;
        return this.cujType == dialogCuj.cujType && Intrinsics.areEqual(this.tag, dialogCuj.tag);
    }

    public final int hashCode() {
        int hashCode = Integer.hashCode(this.cujType) * 31;
        String str = this.tag;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DialogCuj(cujType=");
        sb.append(this.cujType);
        sb.append(", tag=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.tag, ")");
    }
}
