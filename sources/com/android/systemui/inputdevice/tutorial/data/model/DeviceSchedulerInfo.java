package com.android.systemui.inputdevice.tutorial.data.model;

import java.time.Instant;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceSchedulerInfo {
    public Instant firstConnectionTime;
    public Instant launchTime;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceSchedulerInfo)) {
            return false;
        }
        DeviceSchedulerInfo deviceSchedulerInfo = (DeviceSchedulerInfo) obj;
        return Intrinsics.areEqual(this.launchTime, deviceSchedulerInfo.launchTime) && Intrinsics.areEqual(this.firstConnectionTime, deviceSchedulerInfo.firstConnectionTime);
    }

    public final int hashCode() {
        Instant instant = this.launchTime;
        int hashCode = (instant == null ? 0 : instant.hashCode()) * 31;
        Instant instant2 = this.firstConnectionTime;
        return hashCode + (instant2 != null ? instant2.hashCode() : 0);
    }

    public final String toString() {
        return "DeviceSchedulerInfo(launchTime=" + this.launchTime + ", firstConnectionTime=" + this.firstConnectionTime + ")";
    }
}
