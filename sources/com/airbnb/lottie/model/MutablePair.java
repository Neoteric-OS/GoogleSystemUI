package com.airbnb.lottie.model;

import androidx.core.util.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutablePair {
    public Object first;
    public Object second;

    public final boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        Object obj2 = pair.first;
        Object obj3 = this.first;
        if (obj2 != obj3 && (obj2 == null || !obj2.equals(obj3))) {
            return false;
        }
        Object obj4 = this.second;
        Object obj5 = pair.second;
        return obj5 == obj4 || (obj5 != null && obj5.equals(obj4));
    }

    public final int hashCode() {
        Object obj = this.first;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.second;
        return hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        return "Pair{" + this.first + " " + this.second + "}";
    }
}
