package androidx.compose.runtime;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class JoinedKey {
    public final Object left;
    public final Object right;

    public JoinedKey(Object obj, Object obj2) {
        this.left = obj;
        this.right = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JoinedKey)) {
            return false;
        }
        JoinedKey joinedKey = (JoinedKey) obj;
        return Intrinsics.areEqual(this.left, joinedKey.left) && Intrinsics.areEqual(this.right, joinedKey.right);
    }

    public final int hashCode() {
        Object obj = this.left;
        int ordinal = (obj instanceof Enum ? ((Enum) obj).ordinal() : obj.hashCode()) * 31;
        Object obj2 = this.right;
        return (obj2 instanceof Enum ? ((Enum) obj2).ordinal() : obj2 != null ? obj2.hashCode() : 0) + ordinal;
    }

    public final String toString() {
        return "JoinedKey(left=" + this.left + ", right=" + this.right + ')';
    }
}
