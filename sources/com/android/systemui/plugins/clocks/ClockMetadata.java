package com.android.systemui.plugins.clocks;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockMetadata {
    private final String clockId;

    public ClockMetadata(String str) {
        this.clockId = str;
    }

    public static /* synthetic */ ClockMetadata copy$default(ClockMetadata clockMetadata, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = clockMetadata.clockId;
        }
        return clockMetadata.copy(str);
    }

    public final String component1() {
        return this.clockId;
    }

    public final ClockMetadata copy(String str) {
        return new ClockMetadata(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ClockMetadata) && Intrinsics.areEqual(this.clockId, ((ClockMetadata) obj).clockId);
    }

    public final String getClockId() {
        return this.clockId;
    }

    public int hashCode() {
        return this.clockId.hashCode();
    }

    public String toString() {
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("ClockMetadata(clockId=", this.clockId, ")");
    }
}
