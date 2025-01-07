package com.android.systemui.util.kotlin;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Septuple {
    public final Object fifth;
    public final Object first;
    public final Object fourth;
    public final Object second;
    public final Object seventh;
    public final Object sixth;
    public final Object third;

    public Septuple(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        this.first = obj;
        this.second = obj2;
        this.third = obj3;
        this.fourth = obj4;
        this.fifth = obj5;
        this.sixth = obj6;
        this.seventh = obj7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Septuple)) {
            return false;
        }
        Septuple septuple = (Septuple) obj;
        return Intrinsics.areEqual(this.first, septuple.first) && Intrinsics.areEqual(this.second, septuple.second) && Intrinsics.areEqual(this.third, septuple.third) && Intrinsics.areEqual(this.fourth, septuple.fourth) && Intrinsics.areEqual(this.fifth, septuple.fifth) && Intrinsics.areEqual(this.sixth, septuple.sixth) && Intrinsics.areEqual(this.seventh, septuple.seventh);
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
        int hashCode6 = (hashCode5 + (obj6 == null ? 0 : obj6.hashCode())) * 31;
        Object obj7 = this.seventh;
        return hashCode6 + (obj7 != null ? obj7.hashCode() : 0);
    }

    public final String toString() {
        return "Septuple(first=" + this.first + ", second=" + this.second + ", third=" + this.third + ", fourth=" + this.fourth + ", fifth=" + this.fifth + ", sixth=" + this.sixth + ", seventh=" + this.seventh + ")";
    }
}
