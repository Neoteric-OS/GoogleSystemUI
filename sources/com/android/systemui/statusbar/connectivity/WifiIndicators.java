package com.android.systemui.statusbar.connectivity;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiIndicators {
    public final boolean activityIn;
    public final boolean activityOut;
    public final String description;
    public final boolean enabled;
    public final IconState qsIcon;
    public final IconState statusIcon;
    public final String statusLabel;

    public WifiIndicators(boolean z, IconState iconState, IconState iconState2, boolean z2, boolean z3, String str, String str2) {
        this.enabled = z;
        this.statusIcon = iconState;
        this.qsIcon = iconState2;
        this.activityIn = z2;
        this.activityOut = z3;
        this.description = str;
        this.statusLabel = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WifiIndicators)) {
            return false;
        }
        WifiIndicators wifiIndicators = (WifiIndicators) obj;
        return this.enabled == wifiIndicators.enabled && this.statusIcon.equals(wifiIndicators.statusIcon) && Intrinsics.areEqual(this.qsIcon, wifiIndicators.qsIcon) && this.activityIn == wifiIndicators.activityIn && this.activityOut == wifiIndicators.activityOut && Intrinsics.areEqual(this.description, wifiIndicators.description) && Intrinsics.areEqual(this.statusLabel, wifiIndicators.statusLabel);
    }

    public final int hashCode() {
        int hashCode = (this.statusIcon.hashCode() + (Boolean.hashCode(this.enabled) * 31)) * 31;
        IconState iconState = this.qsIcon;
        int m = TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((hashCode + (iconState == null ? 0 : iconState.hashCode())) * 31, 31, this.activityIn), 31, this.activityOut);
        String str = this.description;
        int m2 = TransitionData$$ExternalSyntheticOutline0.m((m + (str == null ? 0 : str.hashCode())) * 31, 31, false);
        String str2 = this.statusLabel;
        return m2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("WifiIndicators[enabled=");
        sb.append(this.enabled);
        sb.append(",statusIcon=");
        sb.append(this.statusIcon.toString());
        sb.append(",qsIcon=");
        IconState iconState = this.qsIcon;
        sb.append(iconState != null ? iconState.toString() : "");
        sb.append(",activityIn=");
        sb.append(this.activityIn);
        sb.append(",activityOut=");
        sb.append(this.activityOut);
        sb.append(",qsDescription=");
        sb.append(this.description);
        sb.append(",isTransient=false,statusLabel=");
        return OpaqueKey$$ExternalSyntheticOutline0.m(sb, this.statusLabel, ']');
    }
}
