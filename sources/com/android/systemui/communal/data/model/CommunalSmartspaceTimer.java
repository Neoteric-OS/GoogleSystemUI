package com.android.systemui.communal.data.model;

import android.widget.RemoteViews;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSmartspaceTimer {
    public final long createdTimestampMillis;
    public final RemoteViews remoteViews;
    public final String smartspaceTargetId;

    public CommunalSmartspaceTimer(long j, RemoteViews remoteViews, String str) {
        this.smartspaceTargetId = str;
        this.createdTimestampMillis = j;
        this.remoteViews = remoteViews;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommunalSmartspaceTimer)) {
            return false;
        }
        CommunalSmartspaceTimer communalSmartspaceTimer = (CommunalSmartspaceTimer) obj;
        return Intrinsics.areEqual(this.smartspaceTargetId, communalSmartspaceTimer.smartspaceTargetId) && this.createdTimestampMillis == communalSmartspaceTimer.createdTimestampMillis && Intrinsics.areEqual(this.remoteViews, communalSmartspaceTimer.remoteViews);
    }

    public final int hashCode() {
        return this.remoteViews.hashCode() + Scale$$ExternalSyntheticOutline0.m(this.smartspaceTargetId.hashCode() * 31, 31, this.createdTimestampMillis);
    }

    public final String toString() {
        return "CommunalSmartspaceTimer(smartspaceTargetId=" + this.smartspaceTargetId + ", createdTimestampMillis=" + this.createdTimestampMillis + ", remoteViews=" + this.remoteViews + ")";
    }
}
