package com.android.systemui.util.kotlin;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Quad {
    public final Object first;
    public final Object fourth;
    public final Object second;
    public final Object third;

    public Quad(Object obj, Object obj2, Object obj3, Object obj4) {
        this.first = obj;
        this.second = obj2;
        this.third = obj3;
        this.fourth = obj4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Quad)) {
            return false;
        }
        Quad quad = (Quad) obj;
        return Intrinsics.areEqual(this.first, quad.first) && Intrinsics.areEqual(this.second, quad.second) && Intrinsics.areEqual(this.third, quad.third) && Intrinsics.areEqual(this.fourth, quad.fourth);
    }

    public final int hashCode() {
        Object obj = this.first;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.second;
        int hashCode2 = (hashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.third;
        int hashCode3 = (hashCode2 + (obj3 == null ? 0 : obj3.hashCode())) * 31;
        Object obj4 = this.fourth;
        return hashCode3 + (obj4 != null ? obj4.hashCode() : 0);
    }

    public final String toString() {
        return "Quad(first=" + this.first + ", second=" + this.second + ", third=" + this.third + ", fourth=" + this.fourth + ")";
    }
}
