package com.android.systemui.keyboard.data.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Keyboard {
    public final int productId;
    public final int vendorId;

    public Keyboard(int i, int i2) {
        this.vendorId = i;
        this.productId = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Keyboard)) {
            return false;
        }
        Keyboard keyboard = (Keyboard) obj;
        return this.vendorId == keyboard.vendorId && this.productId == keyboard.productId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.productId) + (Integer.hashCode(this.vendorId) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Keyboard(vendorId=");
        sb.append(this.vendorId);
        sb.append(", productId=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.productId, ")");
    }
}
