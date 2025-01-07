package com.android.systemui.navigationbar.gestural;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Step {
    public final Object postThreshold;
    public final Object preThreshold;
    public Value previousValue;
    public final Value startValue;
    public final float lowerFactor = 2 - 1.05f;
    public boolean hasCrossedUpperBoundAtLeastOnce = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Value {
        public final boolean isNewState;
        public final Object value;

        public Value(Object obj, boolean z) {
            this.value = obj;
            this.isNewState = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Value)) {
                return false;
            }
            Value value = (Value) obj;
            return Intrinsics.areEqual(this.value, value.value) && this.isNewState == value.isNewState;
        }

        public final int hashCode() {
            Object obj = this.value;
            return Boolean.hashCode(this.isNewState) + ((obj == null ? 0 : obj.hashCode()) * 31);
        }

        public final String toString() {
            return "Value(value=" + this.value + ", isNewState=" + this.isNewState + ")";
        }
    }

    public Step(Object obj, Object obj2) {
        this.postThreshold = obj;
        this.preThreshold = obj2;
        Value value = new Value(obj2, false);
        this.startValue = value;
        this.previousValue = value;
    }

    public final Value get(float f) {
        Value value;
        boolean z = f > 0.17325f;
        boolean z2 = f > 0.165f * this.lowerFactor;
        if (z && !this.hasCrossedUpperBoundAtLeastOnce) {
            this.hasCrossedUpperBoundAtLeastOnce = true;
            value = new Value(this.postThreshold, true);
        } else if (z2) {
            Value value2 = this.previousValue;
            if (value2 == null) {
                value2 = null;
            }
            value = new Value(value2.value, false);
        } else if (this.hasCrossedUpperBoundAtLeastOnce) {
            this.hasCrossedUpperBoundAtLeastOnce = false;
            value = new Value(this.preThreshold, true);
        } else {
            value = this.startValue;
        }
        this.previousValue = value;
        return value;
    }
}
