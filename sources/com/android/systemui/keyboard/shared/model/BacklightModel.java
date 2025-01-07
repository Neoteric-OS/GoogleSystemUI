package com.android.systemui.keyboard.shared.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BacklightModel {
    public final int level;
    public final int maxLevel;

    public BacklightModel(int i, int i2) {
        this.level = i;
        this.maxLevel = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BacklightModel)) {
            return false;
        }
        BacklightModel backlightModel = (BacklightModel) obj;
        return this.level == backlightModel.level && this.maxLevel == backlightModel.maxLevel;
    }

    public final int hashCode() {
        return Integer.hashCode(this.maxLevel) + (Integer.hashCode(this.level) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BacklightModel(level=");
        sb.append(this.level);
        sb.append(", maxLevel=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.maxLevel, ")");
    }
}
