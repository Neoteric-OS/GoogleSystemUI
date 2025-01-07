package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.Color;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileColors {
    public final long background;
    public final long icon;
    public final long iconBackground;
    public final long label;
    public final long secondaryLabel;

    public TileColors(long j, long j2, long j3, long j4, long j5) {
        this.background = j;
        this.iconBackground = j2;
        this.label = j3;
        this.secondaryLabel = j4;
        this.icon = j5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileColors)) {
            return false;
        }
        TileColors tileColors = (TileColors) obj;
        return Color.m363equalsimpl0(this.background, tileColors.background) && Color.m363equalsimpl0(this.iconBackground, tileColors.iconBackground) && Color.m363equalsimpl0(this.label, tileColors.label) && Color.m363equalsimpl0(this.secondaryLabel, tileColors.secondaryLabel) && Color.m363equalsimpl0(this.icon, tileColors.icon);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.icon) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.background) * 31, 31, this.iconBackground), 31, this.label), 31, this.secondaryLabel);
    }

    public final String toString() {
        String m369toStringimpl = Color.m369toStringimpl(this.background);
        String m369toStringimpl2 = Color.m369toStringimpl(this.iconBackground);
        String m369toStringimpl3 = Color.m369toStringimpl(this.label);
        String m369toStringimpl4 = Color.m369toStringimpl(this.secondaryLabel);
        String m369toStringimpl5 = Color.m369toStringimpl(this.icon);
        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("TileColors(background=", m369toStringimpl, ", iconBackground=", m369toStringimpl2, ", label=");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(m, m369toStringimpl3, ", secondaryLabel=", m369toStringimpl4, ", icon=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(m, m369toStringimpl5, ")");
    }
}
