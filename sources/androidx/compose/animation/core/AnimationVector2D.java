package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimationVector2D extends AnimationVector {
    public float v1;
    public float v2;

    public AnimationVector2D(float f, float f2) {
        this.v1 = f;
        this.v2 = f2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof AnimationVector2D) {
            AnimationVector2D animationVector2D = (AnimationVector2D) obj;
            if (animationVector2D.v1 == this.v1 && animationVector2D.v2 == this.v2) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public final float get$animation_core_release(int i) {
        if (i == 0) {
            return this.v1;
        }
        if (i != 1) {
            return 0.0f;
        }
        return this.v2;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public final int getSize$animation_core_release() {
        return 2;
    }

    public final int hashCode() {
        return Float.hashCode(this.v2) + (Float.hashCode(this.v1) * 31);
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public final AnimationVector newVector$animation_core_release() {
        return new AnimationVector2D(0.0f, 0.0f);
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public final void reset$animation_core_release() {
        this.v1 = 0.0f;
        this.v2 = 0.0f;
    }

    @Override // androidx.compose.animation.core.AnimationVector
    public final void set$animation_core_release(int i, float f) {
        if (i == 0) {
            this.v1 = f;
        } else {
            if (i != 1) {
                return;
            }
            this.v2 = f;
        }
    }

    public final String toString() {
        return "AnimationVector2D: v1 = " + this.v1 + ", v2 = " + this.v2;
    }
}
