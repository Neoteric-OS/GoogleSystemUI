package com.android.systemui.keyguard.shared.model;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardPickerFlag {
    public final String name;
    public final boolean value;

    public KeyguardPickerFlag(String str, boolean z) {
        this.name = str;
        this.value = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyguardPickerFlag)) {
            return false;
        }
        KeyguardPickerFlag keyguardPickerFlag = (KeyguardPickerFlag) obj;
        return Intrinsics.areEqual(this.name, keyguardPickerFlag.name) && this.value == keyguardPickerFlag.value;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.value) + (this.name.hashCode() * 31);
    }

    public final String toString() {
        return "KeyguardPickerFlag(name=" + this.name + ", value=" + this.value + ")";
    }
}
