package com.android.systemui.statusbar.connectivity;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconState {
    public final String contentDescription;
    public final int icon;
    public final boolean visible;

    public IconState(int i, String str, boolean z) {
        this.visible = z;
        this.icon = i;
        this.contentDescription = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IconState)) {
            return false;
        }
        IconState iconState = (IconState) obj;
        return this.visible == iconState.visible && this.icon == iconState.icon && Intrinsics.areEqual(this.contentDescription, iconState.contentDescription);
    }

    public final int hashCode() {
        return this.contentDescription.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.icon, Boolean.hashCode(this.visible) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[visible=");
        sb.append(this.visible);
        sb.append(",icon=");
        sb.append(this.icon);
        sb.append(",contentDescription=");
        return OpaqueKey$$ExternalSyntheticOutline0.m(sb, this.contentDescription, ']');
    }
}
