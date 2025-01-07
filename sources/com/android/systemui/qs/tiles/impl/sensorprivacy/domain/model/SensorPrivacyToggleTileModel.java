package com.android.systemui.qs.tiles.impl.sensorprivacy.domain.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensorPrivacyToggleTileModel {
    public final boolean isBlocked;

    public final boolean equals(Object obj) {
        if (obj instanceof SensorPrivacyToggleTileModel) {
            return this.isBlocked == ((SensorPrivacyToggleTileModel) obj).isBlocked;
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isBlocked);
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("SensorPrivacyToggleTileModel(isBlocked="), this.isBlocked, ")");
    }
}
