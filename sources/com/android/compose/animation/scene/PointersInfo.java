package com.android.compose.animation.scene;

import androidx.compose.ui.geometry.Offset;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointersInfo {
    public final int pointersDown;
    public final Offset startedPosition;

    public PointersInfo(Offset offset, int i) {
        this.startedPosition = offset;
        this.pointersDown = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PointersInfo)) {
            return false;
        }
        PointersInfo pointersInfo = (PointersInfo) obj;
        return Intrinsics.areEqual(this.startedPosition, pointersInfo.startedPosition) && this.pointersDown == pointersInfo.pointersDown;
    }

    public final int hashCode() {
        Offset offset = this.startedPosition;
        return Integer.hashCode(this.pointersDown) + ((offset == null ? 0 : Long.hashCode(offset.packedValue)) * 31);
    }

    public final String toString() {
        return "PointersInfo(startedPosition=" + this.startedPosition + ", pointersDown=" + this.pointersDown + ")";
    }
}
