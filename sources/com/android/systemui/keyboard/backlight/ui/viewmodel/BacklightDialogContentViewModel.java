package com.android.systemui.keyboard.backlight.ui.viewmodel;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BacklightDialogContentViewModel {
    public final int currentValue;
    public final int maxValue;

    public BacklightDialogContentViewModel(int i, int i2) {
        this.currentValue = i;
        this.maxValue = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BacklightDialogContentViewModel)) {
            return false;
        }
        BacklightDialogContentViewModel backlightDialogContentViewModel = (BacklightDialogContentViewModel) obj;
        return this.currentValue == backlightDialogContentViewModel.currentValue && this.maxValue == backlightDialogContentViewModel.maxValue;
    }

    public final int hashCode() {
        return Integer.hashCode(this.maxValue) + (Integer.hashCode(this.currentValue) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BacklightDialogContentViewModel(currentValue=");
        sb.append(this.currentValue);
        sb.append(", maxValue=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.maxValue, ")");
    }
}
