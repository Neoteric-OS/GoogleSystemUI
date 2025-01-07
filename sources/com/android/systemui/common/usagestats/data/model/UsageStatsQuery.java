package com.android.systemui.common.usagestats.data.model;

import android.os.UserHandle;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UsageStatsQuery {
    public final long endTime;
    public final List packageNames;
    public final long startTime;
    public final UserHandle user;

    public UsageStatsQuery(UserHandle userHandle, long j, long j2, List list) {
        this.user = userHandle;
        this.startTime = j;
        this.endTime = j2;
        this.packageNames = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UsageStatsQuery)) {
            return false;
        }
        UsageStatsQuery usageStatsQuery = (UsageStatsQuery) obj;
        return Intrinsics.areEqual(this.user, usageStatsQuery.user) && this.startTime == usageStatsQuery.startTime && this.endTime == usageStatsQuery.endTime && Intrinsics.areEqual(this.packageNames, usageStatsQuery.packageNames);
    }

    public final int hashCode() {
        return this.packageNames.hashCode() + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(this.user.hashCode() * 31, 31, this.startTime), 31, this.endTime);
    }

    public final String toString() {
        return "UsageStatsQuery(user=" + this.user + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", packageNames=" + this.packageNames + ")";
    }
}
