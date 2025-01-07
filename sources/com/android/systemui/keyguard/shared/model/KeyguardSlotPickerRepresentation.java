package com.android.systemui.keyguard.shared.model;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSlotPickerRepresentation {
    public final String id;
    public final int maxSelectedAffordances;

    public KeyguardSlotPickerRepresentation(String str, int i) {
        this.id = str;
        this.maxSelectedAffordances = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyguardSlotPickerRepresentation)) {
            return false;
        }
        KeyguardSlotPickerRepresentation keyguardSlotPickerRepresentation = (KeyguardSlotPickerRepresentation) obj;
        return Intrinsics.areEqual(this.id, keyguardSlotPickerRepresentation.id) && this.maxSelectedAffordances == keyguardSlotPickerRepresentation.maxSelectedAffordances;
    }

    public final int hashCode() {
        return Integer.hashCode(this.maxSelectedAffordances) + (this.id.hashCode() * 31);
    }

    public final String toString() {
        return "KeyguardSlotPickerRepresentation(id=" + this.id + ", maxSelectedAffordances=" + this.maxSelectedAffordances + ")";
    }
}
