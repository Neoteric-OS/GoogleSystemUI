package com.android.systemui.shade;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConstraintsChanges {
    public final Function1 largeScreenConstraintsChanges;
    public final Function1 qqsConstraintsChanges;
    public final Function1 qsConstraintsChanges;

    public ConstraintsChanges(Function1 function1, Function1 function12, Function1 function13) {
        this.qqsConstraintsChanges = function1;
        this.qsConstraintsChanges = function12;
        this.largeScreenConstraintsChanges = function13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConstraintsChanges)) {
            return false;
        }
        ConstraintsChanges constraintsChanges = (ConstraintsChanges) obj;
        return Intrinsics.areEqual(this.qqsConstraintsChanges, constraintsChanges.qqsConstraintsChanges) && Intrinsics.areEqual(this.qsConstraintsChanges, constraintsChanges.qsConstraintsChanges) && Intrinsics.areEqual(this.largeScreenConstraintsChanges, constraintsChanges.largeScreenConstraintsChanges);
    }

    public final int hashCode() {
        Function1 function1 = this.qqsConstraintsChanges;
        int hashCode = (function1 == null ? 0 : function1.hashCode()) * 31;
        Function1 function12 = this.qsConstraintsChanges;
        int hashCode2 = (hashCode + (function12 == null ? 0 : function12.hashCode())) * 31;
        Function1 function13 = this.largeScreenConstraintsChanges;
        return hashCode2 + (function13 != null ? function13.hashCode() : 0);
    }

    public final String toString() {
        return "ConstraintsChanges(qqsConstraintsChanges=" + this.qqsConstraintsChanges + ", qsConstraintsChanges=" + this.qsConstraintsChanges + ", largeScreenConstraintsChanges=" + this.largeScreenConstraintsChanges + ")";
    }
}
