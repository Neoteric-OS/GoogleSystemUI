package com.android.systemui.plugins.clocks;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockReactiveAxis {
    private final float currentValue;
    private final String description;
    private final String key;
    private final float maxValue;
    private final float minValue;
    private final String name;
    private final AxisType type;

    public ClockReactiveAxis(String str, AxisType axisType, float f, float f2, float f3, String str2, String str3) {
        this.key = str;
        this.type = axisType;
        this.maxValue = f;
        this.minValue = f2;
        this.currentValue = f3;
        this.name = str2;
        this.description = str3;
    }

    public static /* synthetic */ ClockReactiveAxis copy$default(ClockReactiveAxis clockReactiveAxis, String str, AxisType axisType, float f, float f2, float f3, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = clockReactiveAxis.key;
        }
        if ((i & 2) != 0) {
            axisType = clockReactiveAxis.type;
        }
        AxisType axisType2 = axisType;
        if ((i & 4) != 0) {
            f = clockReactiveAxis.maxValue;
        }
        float f4 = f;
        if ((i & 8) != 0) {
            f2 = clockReactiveAxis.minValue;
        }
        float f5 = f2;
        if ((i & 16) != 0) {
            f3 = clockReactiveAxis.currentValue;
        }
        float f6 = f3;
        if ((i & 32) != 0) {
            str2 = clockReactiveAxis.name;
        }
        String str4 = str2;
        if ((i & 64) != 0) {
            str3 = clockReactiveAxis.description;
        }
        return clockReactiveAxis.copy(str, axisType2, f4, f5, f6, str4, str3);
    }

    public final String component1() {
        return this.key;
    }

    public final AxisType component2() {
        return this.type;
    }

    public final float component3() {
        return this.maxValue;
    }

    public final float component4() {
        return this.minValue;
    }

    public final float component5() {
        return this.currentValue;
    }

    public final String component6() {
        return this.name;
    }

    public final String component7() {
        return this.description;
    }

    public final ClockReactiveAxis copy(String str, AxisType axisType, float f, float f2, float f3, String str2, String str3) {
        return new ClockReactiveAxis(str, axisType, f, f2, f3, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClockReactiveAxis)) {
            return false;
        }
        ClockReactiveAxis clockReactiveAxis = (ClockReactiveAxis) obj;
        return Intrinsics.areEqual(this.key, clockReactiveAxis.key) && this.type == clockReactiveAxis.type && Float.compare(this.maxValue, clockReactiveAxis.maxValue) == 0 && Float.compare(this.minValue, clockReactiveAxis.minValue) == 0 && Float.compare(this.currentValue, clockReactiveAxis.currentValue) == 0 && Intrinsics.areEqual(this.name, clockReactiveAxis.name) && Intrinsics.areEqual(this.description, clockReactiveAxis.description);
    }

    public final float getCurrentValue() {
        return this.currentValue;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getKey() {
        return this.key;
    }

    public final float getMaxValue() {
        return this.maxValue;
    }

    public final float getMinValue() {
        return this.minValue;
    }

    public final String getName() {
        return this.name;
    }

    public final AxisType getType() {
        return this.type;
    }

    public int hashCode() {
        return this.description.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.name, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((this.type.hashCode() + (this.key.hashCode() * 31)) * 31, this.maxValue, 31), this.minValue, 31), this.currentValue, 31), 31);
    }

    public String toString() {
        String str = this.key;
        AxisType axisType = this.type;
        float f = this.maxValue;
        float f2 = this.minValue;
        float f3 = this.currentValue;
        String str2 = this.name;
        String str3 = this.description;
        StringBuilder sb = new StringBuilder("ClockReactiveAxis(key=");
        sb.append(str);
        sb.append(", type=");
        sb.append(axisType);
        sb.append(", maxValue=");
        sb.append(f);
        sb.append(", minValue=");
        sb.append(f2);
        sb.append(", currentValue=");
        sb.append(f3);
        sb.append(", name=");
        sb.append(str2);
        sb.append(", description=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, str3, ")");
    }
}
