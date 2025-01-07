package com.android.systemui.common.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Position {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Position)) {
            return false;
        }
        ((Position) obj).getClass();
        return true;
    }

    public final int hashCode() {
        return Integer.hashCode(0) + (Integer.hashCode(0) * 31);
    }

    public final String toString() {
        return "Position(x=0, y=0)";
    }
}
