package com.android.systemui.util.kotlin;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Sextuple {
    public final Object fifth;
    public final Object first;
    public final Object fourth;
    public final Object second;
    public final Object sixth;
    public final Object third;

    public Sextuple(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        this.first = obj;
        this.second = obj2;
        this.third = obj3;
        this.fourth = obj4;
        this.fifth = obj5;
        this.sixth = obj6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sextuple)) {
            return false;
        }
        Sextuple sextuple = (Sextuple) obj;
        return Intrinsics.areEqual(this.first, sextuple.first) && Intrinsics.areEqual(this.second, sextuple.second) && Intrinsics.areEqual(this.third, sextuple.third) && Intrinsics.areEqual(this.fourth, sextuple.fourth) && Intrinsics.areEqual(this.fifth, sextuple.fifth) && Intrinsics.areEqual(this.sixth, sextuple.sixth);
    }

    public final int hashCode() {
        Object obj = this.first;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.second;
        int hashCode2 = (hashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.third;
        int hashCode3 = (hashCode2 + (obj3 == null ? 0 : obj3.hashCode())) * 31;
        Object obj4 = this.fourth;
        int hashCode4 = (hashCode3 + (obj4 == null ? 0 : obj4.hashCode())) * 31;
        Object obj5 = this.fifth;
        int hashCode5 = (hashCode4 + (obj5 == null ? 0 : obj5.hashCode())) * 31;
        Object obj6 = this.sixth;
        return hashCode5 + (obj6 != null ? obj6.hashCode() : 0);
    }

    public final String toString() {
        return "Sextuple(first=" + this.first + ", second=" + this.second + ", third=" + this.third + ", fourth=" + this.fourth + ", fifth=" + this.fifth + ", sixth=" + this.sixth + ")";
    }
}
