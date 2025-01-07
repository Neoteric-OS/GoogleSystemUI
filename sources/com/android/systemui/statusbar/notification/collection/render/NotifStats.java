package com.android.systemui.statusbar.notification.collection.render;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifStats {
    public static final NotifStats empty = new NotifStats(0, false, false, false, false);
    public final boolean hasClearableAlertingNotifs;
    public final boolean hasClearableSilentNotifs;
    public final boolean hasNonClearableAlertingNotifs;
    public final boolean hasNonClearableSilentNotifs;
    public final int numActiveNotifs;

    public NotifStats(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        this.numActiveNotifs = i;
        this.hasNonClearableAlertingNotifs = z;
        this.hasClearableAlertingNotifs = z2;
        this.hasNonClearableSilentNotifs = z3;
        this.hasClearableSilentNotifs = z4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotifStats)) {
            return false;
        }
        NotifStats notifStats = (NotifStats) obj;
        return this.numActiveNotifs == notifStats.numActiveNotifs && this.hasNonClearableAlertingNotifs == notifStats.hasNonClearableAlertingNotifs && this.hasClearableAlertingNotifs == notifStats.hasClearableAlertingNotifs && this.hasNonClearableSilentNotifs == notifStats.hasNonClearableSilentNotifs && this.hasClearableSilentNotifs == notifStats.hasClearableSilentNotifs;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.hasClearableSilentNotifs) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Integer.hashCode(this.numActiveNotifs) * 31, 31, this.hasNonClearableAlertingNotifs), 31, this.hasClearableAlertingNotifs), 31, this.hasNonClearableSilentNotifs);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NotifStats(numActiveNotifs=");
        sb.append(this.numActiveNotifs);
        sb.append(", hasNonClearableAlertingNotifs=");
        sb.append(this.hasNonClearableAlertingNotifs);
        sb.append(", hasClearableAlertingNotifs=");
        sb.append(this.hasClearableAlertingNotifs);
        sb.append(", hasNonClearableSilentNotifs=");
        sb.append(this.hasNonClearableSilentNotifs);
        sb.append(", hasClearableSilentNotifs=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.hasClearableSilentNotifs, ")");
    }
}
