package androidx.compose.ui.graphics;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.foundation.OverscrollConfiguration$$ExternalSyntheticOutline0;
import androidx.compose.ui.geometry.Offset;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Shadow {
    public static final Shadow None = new Shadow();
    public final float blurRadius;
    public final long color;
    public final long offset;

    public Shadow(float f, long j, long j2) {
        this.color = j;
        this.offset = j2;
        this.blurRadius = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Shadow)) {
            return false;
        }
        Shadow shadow = (Shadow) obj;
        return Color.m363equalsimpl0(this.color, shadow.color) && Offset.m310equalsimpl0(this.offset, shadow.offset) && this.blurRadius == shadow.blurRadius;
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Float.hashCode(this.blurRadius) + Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.color) * 31, 31, this.offset);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Shadow(color=");
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(this.color, ", offset=", sb);
        sb.append((Object) Offset.m317toStringimpl(this.offset));
        sb.append(", blurRadius=");
        return AndroidFlingSpline$$ExternalSyntheticOutline0.m(sb, this.blurRadius, ')');
    }

    public /* synthetic */ Shadow() {
        this(0.0f, ColorKt.Color(4278190080L), 0L);
    }
}
