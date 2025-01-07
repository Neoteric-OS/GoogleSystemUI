package com.android.systemui.statusbar.phone;

import android.graphics.Rect;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BoundsPair {
    public final Rect end;
    public final Rect start;

    public BoundsPair(Rect rect, Rect rect2) {
        this.start = rect;
        this.end = rect2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BoundsPair)) {
            return false;
        }
        BoundsPair boundsPair = (BoundsPair) obj;
        return Intrinsics.areEqual(this.start, boundsPair.start) && Intrinsics.areEqual(this.end, boundsPair.end);
    }

    public final int hashCode() {
        return this.end.hashCode() + (this.start.hashCode() * 31);
    }

    public final String toString() {
        return "BoundsPair(start=" + this.start + ", end=" + this.end + ")";
    }
}
