package com.android.settingslib.volume.shared.model;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import java.util.Set;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RingerMode {
    public static final Set supportedRingerModes = SetsKt.setOf(0, 1, 2, 2);
    public final int value;

    public /* synthetic */ RingerMode(int i) {
        this.value = i;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static void m773constructorimpl(int i) {
        if (!supportedRingerModes.contains(Integer.valueOf(i))) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unsupported stream=").toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof RingerMode) {
            return this.value == ((RingerMode) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("RingerMode(value="), this.value, ")");
    }
}
