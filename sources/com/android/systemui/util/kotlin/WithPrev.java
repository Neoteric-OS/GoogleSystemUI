package com.android.systemui.util.kotlin;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WithPrev {
    public final Object newValue;
    public final Object previousValue;

    public WithPrev(Object obj, Object obj2) {
        this.previousValue = obj;
        this.newValue = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WithPrev)) {
            return false;
        }
        WithPrev withPrev = (WithPrev) obj;
        return Intrinsics.areEqual(this.previousValue, withPrev.previousValue) && Intrinsics.areEqual(this.newValue, withPrev.newValue);
    }

    public final int hashCode() {
        Object obj = this.previousValue;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.newValue;
        return hashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        return "WithPrev(previousValue=" + this.previousValue + ", newValue=" + this.newValue + ")";
    }
}
