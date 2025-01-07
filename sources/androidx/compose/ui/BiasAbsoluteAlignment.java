package androidx.compose.ui;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.foundation.shape.DpCornerSize$$ExternalSyntheticOutline0;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiasAbsoluteAlignment implements Alignment {
    public final float horizontalBias;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Horizontal implements Alignment.Horizontal {
        public final float bias;

        public Horizontal(float f) {
            this.bias = f;
        }

        @Override // androidx.compose.ui.Alignment.Horizontal
        public final int align(int i, int i2, LayoutDirection layoutDirection) {
            return Math.round((1 + this.bias) * ((i2 - i) / 2.0f));
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Horizontal) && Float.compare(this.bias, ((Horizontal) obj).bias) == 0;
        }

        public final int hashCode() {
            return Float.hashCode(this.bias);
        }

        public final String toString() {
            return AndroidFlingSpline$$ExternalSyntheticOutline0.m(new StringBuilder("Horizontal(bias="), this.bias, ')');
        }
    }

    public BiasAbsoluteAlignment(float f) {
        this.horizontalBias = f;
    }

    @Override // androidx.compose.ui.Alignment
    /* renamed from: align-KFBX0sM */
    public final long mo274alignKFBX0sM(long j, long j2, LayoutDirection layoutDirection) {
        long j3 = ((((int) (j2 >> 32)) - ((int) (j >> 32))) << 32) | ((((int) (j2 & 4294967295L)) - ((int) (j & 4294967295L))) & 4294967295L);
        float f = 1;
        int round = Math.round((this.horizontalBias + f) * (((int) (j3 >> 32)) / 2.0f));
        return (Math.round((f - 1.0f) * (((int) (j3 & 4294967295L)) / 2.0f)) & 4294967295L) | (round << 32);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BiasAbsoluteAlignment) {
            return Float.compare(this.horizontalBias, ((BiasAbsoluteAlignment) obj).horizontalBias) == 0 && Float.compare(-1.0f, -1.0f) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(-1.0f) + (Float.hashCode(this.horizontalBias) * 31);
    }

    public final String toString() {
        return DpCornerSize$$ExternalSyntheticOutline0.m(new StringBuilder("BiasAbsoluteAlignment(horizontalBias="), this.horizontalBias, ", verticalBias=-1.0)");
    }
}
