package com.android.systemui.accessibility.data.model;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import java.time.LocalTime;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NightDisplayState {
    public final int autoMode;
    public final LocalTime endTime;
    public final boolean isActivated;
    public final boolean locationEnabled;
    public final boolean shouldForceAutoMode;
    public final LocalTime startTime;

    public NightDisplayState(int i, boolean z, LocalTime localTime, LocalTime localTime2, boolean z2, boolean z3) {
        this.autoMode = i;
        this.isActivated = z;
        this.startTime = localTime;
        this.endTime = localTime2;
        this.shouldForceAutoMode = z2;
        this.locationEnabled = z3;
    }

    public static NightDisplayState copy$default(NightDisplayState nightDisplayState, int i, boolean z, LocalTime localTime, LocalTime localTime2, boolean z2, boolean z3, int i2) {
        if ((i2 & 1) != 0) {
            i = nightDisplayState.autoMode;
        }
        int i3 = i;
        if ((i2 & 2) != 0) {
            z = nightDisplayState.isActivated;
        }
        boolean z4 = z;
        if ((i2 & 4) != 0) {
            localTime = nightDisplayState.startTime;
        }
        LocalTime localTime3 = localTime;
        if ((i2 & 8) != 0) {
            localTime2 = nightDisplayState.endTime;
        }
        LocalTime localTime4 = localTime2;
        if ((i2 & 16) != 0) {
            z2 = nightDisplayState.shouldForceAutoMode;
        }
        boolean z5 = z2;
        if ((i2 & 32) != 0) {
            z3 = nightDisplayState.locationEnabled;
        }
        nightDisplayState.getClass();
        return new NightDisplayState(i3, z4, localTime3, localTime4, z5, z3);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NightDisplayState)) {
            return false;
        }
        NightDisplayState nightDisplayState = (NightDisplayState) obj;
        return this.autoMode == nightDisplayState.autoMode && this.isActivated == nightDisplayState.isActivated && Intrinsics.areEqual(this.startTime, nightDisplayState.startTime) && Intrinsics.areEqual(this.endTime, nightDisplayState.endTime) && this.shouldForceAutoMode == nightDisplayState.shouldForceAutoMode && this.locationEnabled == nightDisplayState.locationEnabled;
    }

    public final int hashCode() {
        int m = TransitionData$$ExternalSyntheticOutline0.m(Integer.hashCode(this.autoMode) * 31, 31, this.isActivated);
        LocalTime localTime = this.startTime;
        int hashCode = (m + (localTime == null ? 0 : localTime.hashCode())) * 31;
        LocalTime localTime2 = this.endTime;
        return Boolean.hashCode(this.locationEnabled) + TransitionData$$ExternalSyntheticOutline0.m((hashCode + (localTime2 != null ? localTime2.hashCode() : 0)) * 31, 31, this.shouldForceAutoMode);
    }

    public final String toString() {
        return "NightDisplayState(autoMode=" + this.autoMode + ", isActivated=" + this.isActivated + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", shouldForceAutoMode=" + this.shouldForceAutoMode + ", locationEnabled=" + this.locationEnabled + ")";
    }
}
