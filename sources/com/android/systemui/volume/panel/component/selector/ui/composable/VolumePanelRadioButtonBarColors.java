package com.android.systemui.volume.panel.component.selector.ui.composable;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.Color;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelRadioButtonBarColors {
    public final long iconColor;
    public final long indicatorBackgroundColor;
    public final long indicatorColor;
    public final long labelColor;
    public final long selectedIconColor;
    public final long selectedLabelColor;

    public VolumePanelRadioButtonBarColors(long j, long j2, long j3, long j4, long j5, long j6) {
        this.indicatorColor = j;
        this.indicatorBackgroundColor = j2;
        this.iconColor = j3;
        this.selectedIconColor = j4;
        this.labelColor = j5;
        this.selectedLabelColor = j6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VolumePanelRadioButtonBarColors)) {
            return false;
        }
        VolumePanelRadioButtonBarColors volumePanelRadioButtonBarColors = (VolumePanelRadioButtonBarColors) obj;
        return Color.m363equalsimpl0(this.indicatorColor, volumePanelRadioButtonBarColors.indicatorColor) && Color.m363equalsimpl0(this.indicatorBackgroundColor, volumePanelRadioButtonBarColors.indicatorBackgroundColor) && Color.m363equalsimpl0(this.iconColor, volumePanelRadioButtonBarColors.iconColor) && Color.m363equalsimpl0(this.selectedIconColor, volumePanelRadioButtonBarColors.selectedIconColor) && Color.m363equalsimpl0(this.labelColor, volumePanelRadioButtonBarColors.labelColor) && Color.m363equalsimpl0(this.selectedLabelColor, volumePanelRadioButtonBarColors.selectedLabelColor);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.selectedLabelColor) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.indicatorColor) * 31, 31, this.indicatorBackgroundColor), 31, this.iconColor), 31, this.selectedIconColor), 31, this.labelColor);
    }

    public final String toString() {
        String m369toStringimpl = Color.m369toStringimpl(this.indicatorColor);
        String m369toStringimpl2 = Color.m369toStringimpl(this.indicatorBackgroundColor);
        String m369toStringimpl3 = Color.m369toStringimpl(this.iconColor);
        String m369toStringimpl4 = Color.m369toStringimpl(this.selectedIconColor);
        String m369toStringimpl5 = Color.m369toStringimpl(this.labelColor);
        String m369toStringimpl6 = Color.m369toStringimpl(this.selectedLabelColor);
        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("VolumePanelRadioButtonBarColors(indicatorColor=", m369toStringimpl, ", indicatorBackgroundColor=", m369toStringimpl2, ", iconColor=");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(m, m369toStringimpl3, ", selectedIconColor=", m369toStringimpl4, ", labelColor=");
        m.append(m369toStringimpl5);
        m.append(", selectedLabelColor=");
        m.append(m369toStringimpl6);
        m.append(")");
        return m.toString();
    }
}
