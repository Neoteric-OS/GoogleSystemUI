package com.android.keyguard;

import android.os.VibrationAttributes;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthInteractionProperties {
    public final VibrationAttributes vibrationAttributes = VibrationAttributes.createForUsage(65);

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AuthInteractionProperties) && Intrinsics.areEqual(this.vibrationAttributes, ((AuthInteractionProperties) obj).vibrationAttributes);
    }

    public final int hashCode() {
        return this.vibrationAttributes.hashCode();
    }

    public final String toString() {
        return "AuthInteractionProperties(vibrationAttributes=" + this.vibrationAttributes + ")";
    }
}
