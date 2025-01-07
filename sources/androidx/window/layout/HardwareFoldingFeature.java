package androidx.window.layout;

import androidx.window.core.Bounds;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HardwareFoldingFeature {
    public final Bounds featureBounds;
    public final FoldingFeature$State state;
    public final Type type;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Type {
        public static final Type FOLD = new Type("FOLD");
        public static final Type HINGE = new Type("HINGE");
        public final String description;

        public Type(String str) {
            this.description = str;
        }

        public final String toString() {
            return this.description;
        }
    }

    public HardwareFoldingFeature(Bounds bounds, Type type, FoldingFeature$State foldingFeature$State) {
        this.featureBounds = bounds;
        this.type = type;
        this.state = foldingFeature$State;
        if (bounds.getWidth() == 0 && bounds.getHeight() == 0) {
            throw new IllegalArgumentException("Bounds must be non zero");
        }
        if (bounds.left != 0 && bounds.top != 0) {
            throw new IllegalArgumentException("Bounding rectangle must start at the top or left window edge for folding features");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!HardwareFoldingFeature.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        HardwareFoldingFeature hardwareFoldingFeature = (HardwareFoldingFeature) obj;
        return Intrinsics.areEqual(this.featureBounds, hardwareFoldingFeature.featureBounds) && Intrinsics.areEqual(this.type, hardwareFoldingFeature.type) && Intrinsics.areEqual(this.state, hardwareFoldingFeature.state);
    }

    public final FoldingFeature$State getOrientation() {
        Bounds bounds = this.featureBounds;
        return bounds.getWidth() > bounds.getHeight() ? FoldingFeature$State.HORIZONTAL : FoldingFeature$State.VERTICAL;
    }

    public final int hashCode() {
        return this.state.hashCode() + ((this.type.hashCode() + (this.featureBounds.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "HardwareFoldingFeature { " + this.featureBounds + ", type=" + this.type + ", state=" + this.state + " }";
    }
}
