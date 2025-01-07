package com.google.android.msdl.data.model;

import android.os.VibrationEffect;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HapticComposition {
    public final VibrationEffect fallbackEffect;
    public final List primitives;

    public HapticComposition(List list, VibrationEffect vibrationEffect) {
        this.primitives = list;
        this.fallbackEffect = vibrationEffect;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HapticComposition)) {
            return false;
        }
        HapticComposition hapticComposition = (HapticComposition) obj;
        return Intrinsics.areEqual(this.primitives, hapticComposition.primitives) && Intrinsics.areEqual(this.fallbackEffect, hapticComposition.fallbackEffect);
    }

    public final int hashCode() {
        return this.fallbackEffect.hashCode() + (this.primitives.hashCode() * 31);
    }

    public final String toString() {
        return "HapticComposition(primitives=" + this.primitives + ", fallbackEffect=" + this.fallbackEffect + ")";
    }
}
