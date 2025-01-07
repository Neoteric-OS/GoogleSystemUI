package androidx.compose.foundation.shape;

import androidx.compose.ui.unit.Density;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PxCornerSize implements CornerSize {
    public final float size;

    public PxCornerSize(float f) {
        this.size = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PxCornerSize) && Float.compare(this.size, ((PxCornerSize) obj).size) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.size);
    }

    @Override // androidx.compose.foundation.shape.CornerSize
    /* renamed from: toPx-TmRCtEA */
    public final float mo146toPxTmRCtEA(long j, Density density) {
        return this.size;
    }

    public final String toString() {
        return DpCornerSize$$ExternalSyntheticOutline0.m(new StringBuilder("CornerSize(size = "), this.size, ".px)");
    }
}
