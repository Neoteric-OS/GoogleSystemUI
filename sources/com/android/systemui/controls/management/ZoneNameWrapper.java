package com.android.systemui.controls.management;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZoneNameWrapper extends ElementWrapper {
    public final CharSequence zoneName;

    public ZoneNameWrapper(CharSequence charSequence) {
        this.zoneName = charSequence;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ZoneNameWrapper) && Intrinsics.areEqual(this.zoneName, ((ZoneNameWrapper) obj).zoneName);
    }

    public final int hashCode() {
        return this.zoneName.hashCode();
    }

    public final String toString() {
        return "ZoneNameWrapper(zoneName=" + ((Object) this.zoneName) + ")";
    }
}
