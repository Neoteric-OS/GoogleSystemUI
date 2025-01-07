package com.android.systemui.common.shared.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NotificationContainerBounds {
    public final float bottom;
    public final boolean isAnimated;
    public final float top;

    public /* synthetic */ NotificationContainerBounds() {
        this(0.0f, 0.0f, false);
    }

    public static NotificationContainerBounds copy$default(NotificationContainerBounds notificationContainerBounds, float f, float f2, boolean z, int i) {
        if ((i & 1) != 0) {
            f = notificationContainerBounds.top;
        }
        if ((i & 2) != 0) {
            f2 = notificationContainerBounds.bottom;
        }
        if ((i & 4) != 0) {
            z = notificationContainerBounds.isAnimated;
        }
        notificationContainerBounds.getClass();
        return new NotificationContainerBounds(f, f2, z);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationContainerBounds)) {
            return false;
        }
        NotificationContainerBounds notificationContainerBounds = (NotificationContainerBounds) obj;
        return Float.compare(this.top, notificationContainerBounds.top) == 0 && Float.compare(this.bottom, notificationContainerBounds.bottom) == 0 && this.isAnimated == notificationContainerBounds.isAnimated;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isAnimated) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.top) * 31, this.bottom, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NotificationContainerBounds(top=");
        sb.append(this.top);
        sb.append(", bottom=");
        sb.append(this.bottom);
        sb.append(", isAnimated=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isAnimated, ")");
    }

    public NotificationContainerBounds(float f, float f2, boolean z) {
        this.top = f;
        this.bottom = f2;
        this.isAnimated = z;
    }
}
