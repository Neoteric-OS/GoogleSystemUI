package androidx.compose.material3;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.Color;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliderColors {
    public final long activeTickColor;
    public final long activeTrackColor;
    public final long disabledActiveTickColor;
    public final long disabledActiveTrackColor;
    public final long disabledInactiveTickColor;
    public final long disabledInactiveTrackColor;
    public final long disabledThumbColor;
    public final long inactiveTickColor;
    public final long inactiveTrackColor;
    public final long thumbColor;

    public SliderColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10) {
        this.thumbColor = j;
        this.activeTrackColor = j2;
        this.activeTickColor = j3;
        this.inactiveTrackColor = j4;
        this.inactiveTickColor = j5;
        this.disabledThumbColor = j6;
        this.disabledActiveTrackColor = j7;
        this.disabledActiveTickColor = j8;
        this.disabledInactiveTrackColor = j9;
        this.disabledInactiveTickColor = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SliderColors)) {
            return false;
        }
        SliderColors sliderColors = (SliderColors) obj;
        return Color.m363equalsimpl0(this.thumbColor, sliderColors.thumbColor) && Color.m363equalsimpl0(this.activeTrackColor, sliderColors.activeTrackColor) && Color.m363equalsimpl0(this.activeTickColor, sliderColors.activeTickColor) && Color.m363equalsimpl0(this.inactiveTrackColor, sliderColors.inactiveTrackColor) && Color.m363equalsimpl0(this.inactiveTickColor, sliderColors.inactiveTickColor) && Color.m363equalsimpl0(this.disabledThumbColor, sliderColors.disabledThumbColor) && Color.m363equalsimpl0(this.disabledActiveTrackColor, sliderColors.disabledActiveTrackColor) && Color.m363equalsimpl0(this.disabledActiveTickColor, sliderColors.disabledActiveTickColor) && Color.m363equalsimpl0(this.disabledInactiveTrackColor, sliderColors.disabledInactiveTrackColor) && Color.m363equalsimpl0(this.disabledInactiveTickColor, sliderColors.disabledInactiveTickColor);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.disabledInactiveTickColor) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.thumbColor) * 31, 31, this.activeTrackColor), 31, this.activeTickColor), 31, this.inactiveTrackColor), 31, this.inactiveTickColor), 31, this.disabledThumbColor), 31, this.disabledActiveTrackColor), 31, this.disabledActiveTickColor), 31, this.disabledInactiveTrackColor);
    }

    /* renamed from: trackColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m228trackColorWaAFU9c$material3_release(boolean z, boolean z2) {
        return z ? z2 ? this.activeTrackColor : this.inactiveTrackColor : z2 ? this.disabledActiveTrackColor : this.disabledInactiveTrackColor;
    }
}
