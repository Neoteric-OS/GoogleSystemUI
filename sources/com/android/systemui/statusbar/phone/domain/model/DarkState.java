package com.android.systemui.statusbar.phone.domain.model;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DarkState {
    public final Collection areas;
    public final int tint;

    public DarkState(int i, Collection collection) {
        this.areas = collection;
        this.tint = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DarkState)) {
            return false;
        }
        DarkState darkState = (DarkState) obj;
        return Intrinsics.areEqual(this.areas, darkState.areas) && this.tint == darkState.tint;
    }

    public final int hashCode() {
        return Integer.hashCode(this.tint) + (this.areas.hashCode() * 31);
    }

    public final String toString() {
        return "DarkState(areas=" + this.areas + ", tint=" + this.tint + ")";
    }
}
