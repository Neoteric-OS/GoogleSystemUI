package com.android.systemui.qs.tiles.impl.uimodenight.domain.model;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import java.time.LocalTime;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UiModeNightTileModel {
    public final LocalTime customNightModeEnd;
    public final LocalTime customNightModeStart;
    public final boolean is24HourFormat;
    public final boolean isLocationEnabled;
    public final boolean isNightMode;
    public final boolean isPowerSave;
    public final int nightModeCustomType;
    public final int uiMode;

    public UiModeNightTileModel(int i, boolean z, boolean z2, boolean z3, int i2, boolean z4, LocalTime localTime, LocalTime localTime2) {
        this.uiMode = i;
        this.isNightMode = z;
        this.isPowerSave = z2;
        this.isLocationEnabled = z3;
        this.nightModeCustomType = i2;
        this.is24HourFormat = z4;
        this.customNightModeEnd = localTime;
        this.customNightModeStart = localTime2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UiModeNightTileModel)) {
            return false;
        }
        UiModeNightTileModel uiModeNightTileModel = (UiModeNightTileModel) obj;
        return this.uiMode == uiModeNightTileModel.uiMode && this.isNightMode == uiModeNightTileModel.isNightMode && this.isPowerSave == uiModeNightTileModel.isPowerSave && this.isLocationEnabled == uiModeNightTileModel.isLocationEnabled && this.nightModeCustomType == uiModeNightTileModel.nightModeCustomType && this.is24HourFormat == uiModeNightTileModel.is24HourFormat && Intrinsics.areEqual(this.customNightModeEnd, uiModeNightTileModel.customNightModeEnd) && Intrinsics.areEqual(this.customNightModeStart, uiModeNightTileModel.customNightModeStart);
    }

    public final int hashCode() {
        return this.customNightModeStart.hashCode() + ((this.customNightModeEnd.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.nightModeCustomType, TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Integer.hashCode(this.uiMode) * 31, 31, this.isNightMode), 31, this.isPowerSave), 31, this.isLocationEnabled), 31), 31, this.is24HourFormat)) * 31);
    }

    public final String toString() {
        return "UiModeNightTileModel(uiMode=" + this.uiMode + ", isNightMode=" + this.isNightMode + ", isPowerSave=" + this.isPowerSave + ", isLocationEnabled=" + this.isLocationEnabled + ", nightModeCustomType=" + this.nightModeCustomType + ", is24HourFormat=" + this.is24HourFormat + ", customNightModeEnd=" + this.customNightModeEnd + ", customNightModeStart=" + this.customNightModeStart + ")";
    }
}
