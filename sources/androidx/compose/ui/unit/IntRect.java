package androidx.compose.ui.unit;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IntRect {
    public static final IntRect Zero = new IntRect(0, 0, 0, 0);
    public final int bottom;
    public final int left;
    public final int right;
    public final int top;

    public IntRect(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntRect)) {
            return false;
        }
        IntRect intRect = (IntRect) obj;
        return this.left == intRect.left && this.top == intRect.top && this.right == intRect.right && this.bottom == intRect.bottom;
    }

    public final int getHeight() {
        return this.bottom - this.top;
    }

    /* renamed from: getTopLeft-nOcc-ac, reason: not valid java name */
    public final long m680getTopLeftnOccac() {
        return (this.left << 32) | (this.top & 4294967295L);
    }

    public final int getWidth() {
        return this.right - this.left;
    }

    public final int hashCode() {
        return Integer.hashCode(this.bottom) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.right, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.top, Integer.hashCode(this.left) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("IntRect.fromLTRB(");
        sb.append(this.left);
        sb.append(", ");
        sb.append(this.top);
        sb.append(", ");
        sb.append(this.right);
        sb.append(", ");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.bottom, ')');
    }
}
