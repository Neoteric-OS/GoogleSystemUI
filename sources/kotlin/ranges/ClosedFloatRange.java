package kotlin.ranges;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ClosedFloatRange implements ClosedFloatingPointRange {
    public final float _endInclusive;
    public final float _start;

    public ClosedFloatRange(float f, float f2) {
        this._start = f;
        this._endInclusive = f2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ClosedFloatRange) {
            if (!isEmpty() || !((ClosedFloatRange) obj).isEmpty()) {
                ClosedFloatRange closedFloatRange = (ClosedFloatRange) obj;
                if (this._start != closedFloatRange._start || this._endInclusive != closedFloatRange._endInclusive) {
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return Float.hashCode(this._endInclusive) + (Float.hashCode(this._start) * 31);
    }

    public final boolean isEmpty() {
        return this._start > this._endInclusive;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean lessThanOrEquals(Comparable comparable, Comparable comparable2) {
        return ((Number) comparable).floatValue() <= ((Number) comparable2).floatValue();
    }

    public final String toString() {
        return this._start + ".." + this._endInclusive;
    }
}
