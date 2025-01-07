package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RippleConfiguration {
    public final long color = Color.Unspecified;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RippleConfiguration) {
            return Color.m363equalsimpl0(this.color, ((RippleConfiguration) obj).color) && Intrinsics.areEqual((Object) null, (Object) null);
        }
        return false;
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.color) * 31;
    }

    public final String toString() {
        return "RippleConfiguration(color=" + ((Object) Color.m369toStringimpl(this.color)) + ", rippleAlpha=null)";
    }
}
