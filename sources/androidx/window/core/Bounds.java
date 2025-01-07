package androidx.window.core;

import android.graphics.Rect;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Bounds {
    public static final Bounds EMPTY_BOUNDS = new Bounds(0, 0, 0, 0);
    public final int bottom;
    public final int left;
    public final int right;
    public final int top;

    public Bounds(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
        if (i > i3) {
            throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("Left must be less than or equal to right, left: ", i, i3, ", right: ").toString());
        }
        if (i2 > i4) {
            throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("top must be less than or equal to bottom, top: ", i2, i4, ", bottom: ").toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Bounds.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        Bounds bounds = (Bounds) obj;
        return this.left == bounds.left && this.top == bounds.top && this.right == bounds.right && this.bottom == bounds.bottom;
    }

    public final int getHeight() {
        return this.bottom - this.top;
    }

    public final int getWidth() {
        return this.right - this.left;
    }

    public final int hashCode() {
        return (((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom;
    }

    public final Rect toRect() {
        return new Rect(this.left, this.top, this.right, this.bottom);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Bounds { [");
        sb.append(this.left);
        sb.append(',');
        sb.append(this.top);
        sb.append(',');
        sb.append(this.right);
        sb.append(',');
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.bottom, "] }");
    }

    public Bounds(Rect rect) {
        this(rect.left, rect.top, rect.right, rect.bottom);
    }
}
