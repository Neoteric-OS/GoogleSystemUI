package androidx.window.layout;

import android.graphics.Rect;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.core.view.WindowInsetsCompat;
import androidx.window.core.Bounds;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowMetrics {
    public final Bounds _bounds;
    public final WindowInsetsCompat _windowInsetsCompat;
    public final float density;

    public WindowMetrics(Rect rect, WindowInsetsCompat windowInsetsCompat, float f) {
        this._bounds = new Bounds(rect);
        this._windowInsetsCompat = windowInsetsCompat;
        this.density = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!WindowMetrics.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        WindowMetrics windowMetrics = (WindowMetrics) obj;
        return Intrinsics.areEqual(this._bounds, windowMetrics._bounds) && Intrinsics.areEqual(this._windowInsetsCompat, windowMetrics._windowInsetsCompat) && this.density == windowMetrics.density;
    }

    public final int hashCode() {
        return Float.hashCode(this.density) + ((this._windowInsetsCompat.hashCode() + (this._bounds.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("WindowMetrics(_bounds=");
        sb.append(this._bounds);
        sb.append(", _windowInsetsCompat=");
        sb.append(this._windowInsetsCompat);
        sb.append(", density=");
        return AndroidFlingSpline$$ExternalSyntheticOutline0.m(sb, this.density, ')');
    }
}
