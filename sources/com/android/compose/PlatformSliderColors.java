package com.android.compose;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.Color;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PlatformSliderColors {
    public final long disabledIconColor;
    public final long disabledIndicatorColor;
    public final long disabledLabelColor;
    public final long disabledTrackColor;
    public final long iconColor;
    public final long indicatorColor;
    public final long labelColorOnIndicator;
    public final long labelColorOnTrack;
    public final long trackColor;

    public PlatformSliderColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9) {
        this.trackColor = j;
        this.indicatorColor = j2;
        this.iconColor = j3;
        this.labelColorOnIndicator = j4;
        this.labelColorOnTrack = j5;
        this.disabledTrackColor = j6;
        this.disabledIndicatorColor = j7;
        this.disabledIconColor = j8;
        this.disabledLabelColor = j9;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlatformSliderColors)) {
            return false;
        }
        PlatformSliderColors platformSliderColors = (PlatformSliderColors) obj;
        return Color.m363equalsimpl0(this.trackColor, platformSliderColors.trackColor) && Color.m363equalsimpl0(this.indicatorColor, platformSliderColors.indicatorColor) && Color.m363equalsimpl0(this.iconColor, platformSliderColors.iconColor) && Color.m363equalsimpl0(this.labelColorOnIndicator, platformSliderColors.labelColorOnIndicator) && Color.m363equalsimpl0(this.labelColorOnTrack, platformSliderColors.labelColorOnTrack) && Color.m363equalsimpl0(this.disabledTrackColor, platformSliderColors.disabledTrackColor) && Color.m363equalsimpl0(this.disabledIndicatorColor, platformSliderColors.disabledIndicatorColor) && Color.m363equalsimpl0(this.disabledIconColor, platformSliderColors.disabledIconColor) && Color.m363equalsimpl0(this.disabledLabelColor, platformSliderColors.disabledLabelColor);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.disabledLabelColor) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.trackColor) * 31, 31, this.indicatorColor), 31, this.iconColor), 31, this.labelColorOnIndicator), 31, this.labelColorOnTrack), 31, this.disabledTrackColor), 31, this.disabledIndicatorColor), 31, this.disabledIconColor);
    }

    public final String toString() {
        String m369toStringimpl = Color.m369toStringimpl(this.trackColor);
        String m369toStringimpl2 = Color.m369toStringimpl(this.indicatorColor);
        String m369toStringimpl3 = Color.m369toStringimpl(this.iconColor);
        String m369toStringimpl4 = Color.m369toStringimpl(this.labelColorOnIndicator);
        String m369toStringimpl5 = Color.m369toStringimpl(this.labelColorOnTrack);
        String m369toStringimpl6 = Color.m369toStringimpl(this.disabledTrackColor);
        String m369toStringimpl7 = Color.m369toStringimpl(this.disabledIndicatorColor);
        String m369toStringimpl8 = Color.m369toStringimpl(this.disabledIconColor);
        String m369toStringimpl9 = Color.m369toStringimpl(this.disabledLabelColor);
        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("PlatformSliderColors(trackColor=", m369toStringimpl, ", indicatorColor=", m369toStringimpl2, ", iconColor=");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(m, m369toStringimpl3, ", labelColorOnIndicator=", m369toStringimpl4, ", labelColorOnTrack=");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(m, m369toStringimpl5, ", disabledTrackColor=", m369toStringimpl6, ", disabledIndicatorColor=");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(m, m369toStringimpl7, ", disabledIconColor=", m369toStringimpl8, ", disabledLabelColor=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(m, m369toStringimpl9, ")");
    }
}
