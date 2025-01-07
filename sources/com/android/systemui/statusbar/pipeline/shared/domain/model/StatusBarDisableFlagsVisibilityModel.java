package com.android.systemui.statusbar.pipeline.shared.domain.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarDisableFlagsVisibilityModel {
    public final boolean animate;
    public final boolean areNotificationIconsAllowed;
    public final boolean isClockAllowed;
    public final boolean isSystemInfoAllowed;

    public StatusBarDisableFlagsVisibilityModel(boolean z, boolean z2, boolean z3, boolean z4) {
        this.isClockAllowed = z;
        this.areNotificationIconsAllowed = z2;
        this.isSystemInfoAllowed = z3;
        this.animate = z4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StatusBarDisableFlagsVisibilityModel)) {
            return false;
        }
        StatusBarDisableFlagsVisibilityModel statusBarDisableFlagsVisibilityModel = (StatusBarDisableFlagsVisibilityModel) obj;
        return this.isClockAllowed == statusBarDisableFlagsVisibilityModel.isClockAllowed && this.areNotificationIconsAllowed == statusBarDisableFlagsVisibilityModel.areNotificationIconsAllowed && this.isSystemInfoAllowed == statusBarDisableFlagsVisibilityModel.isSystemInfoAllowed && this.animate == statusBarDisableFlagsVisibilityModel.animate;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.animate) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.isClockAllowed) * 31, 31, this.areNotificationIconsAllowed), 31, this.isSystemInfoAllowed);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("StatusBarDisableFlagsVisibilityModel(isClockAllowed=");
        sb.append(this.isClockAllowed);
        sb.append(", areNotificationIconsAllowed=");
        sb.append(this.areNotificationIconsAllowed);
        sb.append(", isSystemInfoAllowed=");
        sb.append(this.isSystemInfoAllowed);
        sb.append(", animate=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.animate, ")");
    }
}
