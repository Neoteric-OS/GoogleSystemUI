package androidx.compose.animation;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.ui.graphics.TransformOrigin;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Scale {
    public final TweenSpec animationSpec;
    public final float scale;
    public final long transformOrigin;

    public Scale(float f, long j, TweenSpec tweenSpec) {
        this.scale = f;
        this.transformOrigin = j;
        this.animationSpec = tweenSpec;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scale)) {
            return false;
        }
        Scale scale = (Scale) obj;
        return Float.compare(this.scale, scale.scale) == 0 && TransformOrigin.m398equalsimpl0(this.transformOrigin, scale.transformOrigin) && this.animationSpec.equals(scale.animationSpec);
    }

    public final int hashCode() {
        int hashCode = Float.hashCode(this.scale) * 31;
        int i = TransformOrigin.$r8$clinit;
        return this.animationSpec.hashCode() + Scale$$ExternalSyntheticOutline0.m(hashCode, 31, this.transformOrigin);
    }

    public final String toString() {
        return "Scale(scale=" + this.scale + ", transformOrigin=" + ((Object) TransformOrigin.m401toStringimpl(this.transformOrigin)) + ", animationSpec=" + this.animationSpec + ')';
    }
}
