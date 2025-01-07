package com.android.systemui.plugins.clocks;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockReactiveSetting {
    private final String key;
    private final float value;

    public ClockReactiveSetting(String str, float f) {
        this.key = str;
        this.value = f;
    }

    public static /* synthetic */ ClockReactiveSetting copy$default(ClockReactiveSetting clockReactiveSetting, String str, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            str = clockReactiveSetting.key;
        }
        if ((i & 2) != 0) {
            f = clockReactiveSetting.value;
        }
        return clockReactiveSetting.copy(str, f);
    }

    public final String component1() {
        return this.key;
    }

    public final float component2() {
        return this.value;
    }

    public final ClockReactiveSetting copy(String str, float f) {
        return new ClockReactiveSetting(str, f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClockReactiveSetting)) {
            return false;
        }
        ClockReactiveSetting clockReactiveSetting = (ClockReactiveSetting) obj;
        return Intrinsics.areEqual(this.key, clockReactiveSetting.key) && Float.compare(this.value, clockReactiveSetting.value) == 0;
    }

    public final String getKey() {
        return this.key;
    }

    public final float getValue() {
        return this.value;
    }

    public int hashCode() {
        return Float.hashCode(this.value) + (this.key.hashCode() * 31);
    }

    public String toString() {
        return "ClockReactiveSetting(key=" + this.key + ", value=" + this.value + ")";
    }
}
