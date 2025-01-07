package com.android.systemui.util.sensors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ThresholdSensorEvent {
    public final boolean mBelow;
    public final long mTimestampNs;

    public ThresholdSensorEvent(long j, boolean z) {
        this.mBelow = z;
        this.mTimestampNs = j;
    }

    public final String toString() {
        return "{near=" + this.mBelow + ", timestamp_ns=" + this.mTimestampNs + "}";
    }
}
