package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PathFillType {
    public final int value;

    public final boolean equals(Object obj) {
        if (obj instanceof PathFillType) {
            return this.value == ((PathFillType) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        int i = this.value;
        return i == 0 ? "NonZero" : i == 1 ? "EvenOdd" : "Unknown";
    }
}
