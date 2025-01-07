package androidx.compose.foundation.shape;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RoundedCornerShape extends CornerBasedShape {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundedCornerShape)) {
            return false;
        }
        RoundedCornerShape roundedCornerShape = (RoundedCornerShape) obj;
        if (!Intrinsics.areEqual(this.topStart, roundedCornerShape.topStart)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.topEnd, roundedCornerShape.topEnd)) {
            return false;
        }
        if (Intrinsics.areEqual(this.bottomEnd, roundedCornerShape.bottomEnd)) {
            return Intrinsics.areEqual(this.bottomStart, roundedCornerShape.bottomStart);
        }
        return false;
    }

    public final int hashCode() {
        return this.bottomStart.hashCode() + ((this.bottomEnd.hashCode() + ((this.topEnd.hashCode() + (this.topStart.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "RoundedCornerShape(topStart = " + this.topStart + ", topEnd = " + this.topEnd + ", bottomEnd = " + this.bottomEnd + ", bottomStart = " + this.bottomStart + ')';
    }
}
