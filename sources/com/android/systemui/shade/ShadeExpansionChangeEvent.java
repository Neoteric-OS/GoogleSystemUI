package com.android.systemui.shade;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeExpansionChangeEvent {
    public final boolean expanded;
    public final float fraction;
    public final boolean tracking;

    public ShadeExpansionChangeEvent(float f, boolean z, boolean z2) {
        this.fraction = f;
        this.expanded = z;
        this.tracking = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShadeExpansionChangeEvent)) {
            return false;
        }
        ShadeExpansionChangeEvent shadeExpansionChangeEvent = (ShadeExpansionChangeEvent) obj;
        return Float.compare(this.fraction, shadeExpansionChangeEvent.fraction) == 0 && this.expanded == shadeExpansionChangeEvent.expanded && this.tracking == shadeExpansionChangeEvent.tracking;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.tracking) + TransitionData$$ExternalSyntheticOutline0.m(Float.hashCode(this.fraction) * 31, 31, this.expanded);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ShadeExpansionChangeEvent(fraction=");
        sb.append(this.fraction);
        sb.append(", expanded=");
        sb.append(this.expanded);
        sb.append(", tracking=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.tracking, ")");
    }
}
