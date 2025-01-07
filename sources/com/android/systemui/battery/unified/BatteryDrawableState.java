package com.android.systemui.battery.unified;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BatteryDrawableState {
    public static final BatteryDrawableState DefaultInitialState;

    static {
        ColorProfile colorProfile = ColorProfile.None;
        DefaultInitialState = new BatteryDrawableState();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BatteryDrawableState)) {
            return false;
        }
        ((BatteryDrawableState) obj).getClass();
        ColorProfile colorProfile = ColorProfile.None;
        return Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final int hashCode() {
        return (ColorProfile.None.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(Integer.hashCode(50) * 31, 31, false)) * 31;
    }

    public final String toString() {
        return "BatteryDrawableState(level=50, showPercent=false, color=" + ColorProfile.None + ", attribution=null)";
    }
}
