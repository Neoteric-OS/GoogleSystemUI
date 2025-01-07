package com.android.systemui.statusbar.phone.fragment;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarVisibilityModel {
    public final boolean showClock;
    public final boolean showNotificationIcons;
    public final boolean showPrimaryOngoingActivityChip;
    public final boolean showSecondaryOngoingActivityChip;
    public final boolean showSystemInfo;

    public StatusBarVisibilityModel(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.showClock = z;
        this.showNotificationIcons = z2;
        this.showPrimaryOngoingActivityChip = z3;
        this.showSecondaryOngoingActivityChip = z4;
        this.showSystemInfo = z5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StatusBarVisibilityModel)) {
            return false;
        }
        StatusBarVisibilityModel statusBarVisibilityModel = (StatusBarVisibilityModel) obj;
        return this.showClock == statusBarVisibilityModel.showClock && this.showNotificationIcons == statusBarVisibilityModel.showNotificationIcons && this.showPrimaryOngoingActivityChip == statusBarVisibilityModel.showPrimaryOngoingActivityChip && this.showSecondaryOngoingActivityChip == statusBarVisibilityModel.showSecondaryOngoingActivityChip && this.showSystemInfo == statusBarVisibilityModel.showSystemInfo;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.showSystemInfo) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.showClock) * 31, 31, this.showNotificationIcons), 31, this.showPrimaryOngoingActivityChip), 31, this.showSecondaryOngoingActivityChip);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("StatusBarVisibilityModel(showClock=");
        sb.append(this.showClock);
        sb.append(", showNotificationIcons=");
        sb.append(this.showNotificationIcons);
        sb.append(", showPrimaryOngoingActivityChip=");
        sb.append(this.showPrimaryOngoingActivityChip);
        sb.append(", showSecondaryOngoingActivityChip=");
        sb.append(this.showSecondaryOngoingActivityChip);
        sb.append(", showSystemInfo=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.showSystemInfo, ")");
    }
}
