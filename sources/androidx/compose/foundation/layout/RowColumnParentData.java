package androidx.compose.foundation.layout;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RowColumnParentData {
    public float weight = 0.0f;
    public boolean fill = true;
    public CrossAxisAlignment crossAxisAlignment = null;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RowColumnParentData)) {
            return false;
        }
        RowColumnParentData rowColumnParentData = (RowColumnParentData) obj;
        return Float.compare(this.weight, rowColumnParentData.weight) == 0 && this.fill == rowColumnParentData.fill && Intrinsics.areEqual(this.crossAxisAlignment, rowColumnParentData.crossAxisAlignment) && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final int hashCode() {
        int m = TransitionData$$ExternalSyntheticOutline0.m(Float.hashCode(this.weight) * 31, 31, this.fill);
        CrossAxisAlignment crossAxisAlignment = this.crossAxisAlignment;
        return (m + (crossAxisAlignment == null ? 0 : crossAxisAlignment.hashCode())) * 31;
    }

    public final String toString() {
        return "RowColumnParentData(weight=" + this.weight + ", fill=" + this.fill + ", crossAxisAlignment=" + this.crossAxisAlignment + ", flowLayoutData=null)";
    }
}
