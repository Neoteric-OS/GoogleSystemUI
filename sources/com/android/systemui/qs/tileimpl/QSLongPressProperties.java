package com.android.systemui.qs.tileimpl;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSLongPressProperties {
    public final int backgroundColor;
    public final int chevronColor;
    public final float cornerRadius;
    public float height;
    public final int iconColor;
    public final int labelColor;
    public final int overlayColor;
    public final int secondaryLabelColor;
    public float width;

    public QSLongPressProperties(float f, float f2, float f3, int i, int i2, int i3, int i4, int i5, int i6) {
        this.height = f;
        this.width = f2;
        this.cornerRadius = f3;
        this.backgroundColor = i;
        this.labelColor = i2;
        this.secondaryLabelColor = i3;
        this.chevronColor = i4;
        this.overlayColor = i5;
        this.iconColor = i6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QSLongPressProperties)) {
            return false;
        }
        QSLongPressProperties qSLongPressProperties = (QSLongPressProperties) obj;
        return Float.compare(this.height, qSLongPressProperties.height) == 0 && Float.compare(this.width, qSLongPressProperties.width) == 0 && Float.compare(this.cornerRadius, qSLongPressProperties.cornerRadius) == 0 && this.backgroundColor == qSLongPressProperties.backgroundColor && this.labelColor == qSLongPressProperties.labelColor && this.secondaryLabelColor == qSLongPressProperties.secondaryLabelColor && this.chevronColor == qSLongPressProperties.chevronColor && this.overlayColor == qSLongPressProperties.overlayColor && this.iconColor == qSLongPressProperties.iconColor;
    }

    public final int hashCode() {
        return Integer.hashCode(this.iconColor) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.overlayColor, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.chevronColor, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.secondaryLabelColor, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.labelColor, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.backgroundColor, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.height) * 31, this.width, 31), this.cornerRadius, 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        float f = this.height;
        float f2 = this.width;
        StringBuilder sb = new StringBuilder("QSLongPressProperties(height=");
        sb.append(f);
        sb.append(", width=");
        sb.append(f2);
        sb.append(", cornerRadius=");
        sb.append(this.cornerRadius);
        sb.append(", backgroundColor=");
        sb.append(this.backgroundColor);
        sb.append(", labelColor=");
        sb.append(this.labelColor);
        sb.append(", secondaryLabelColor=");
        sb.append(this.secondaryLabelColor);
        sb.append(", chevronColor=");
        sb.append(this.chevronColor);
        sb.append(", overlayColor=");
        sb.append(this.overlayColor);
        sb.append(", iconColor=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.iconColor, ")");
    }
}
