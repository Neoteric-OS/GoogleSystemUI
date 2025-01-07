package com.android.systemui.statusbar.notification;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FeedbackIcon {
    public final int contentDescRes;
    public final int iconRes;

    public FeedbackIcon(int i, int i2) {
        this.iconRes = i;
        this.contentDescRes = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeedbackIcon)) {
            return false;
        }
        FeedbackIcon feedbackIcon = (FeedbackIcon) obj;
        return this.iconRes == feedbackIcon.iconRes && this.contentDescRes == feedbackIcon.contentDescRes;
    }

    public final int hashCode() {
        return Integer.hashCode(this.contentDescRes) + (Integer.hashCode(this.iconRes) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FeedbackIcon(iconRes=");
        sb.append(this.iconRes);
        sb.append(", contentDescRes=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.contentDescRes, ")");
    }
}
