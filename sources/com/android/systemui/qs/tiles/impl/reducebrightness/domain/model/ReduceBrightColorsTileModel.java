package com.android.systemui.qs.tiles.impl.reducebrightness.domain.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReduceBrightColorsTileModel {
    public final boolean isEnabled;

    public final boolean equals(Object obj) {
        if (obj instanceof ReduceBrightColorsTileModel) {
            return this.isEnabled == ((ReduceBrightColorsTileModel) obj).isEnabled;
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isEnabled);
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("ReduceBrightColorsTileModel(isEnabled="), this.isEnabled, ")");
    }
}
