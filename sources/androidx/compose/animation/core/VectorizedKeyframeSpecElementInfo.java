package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VectorizedKeyframeSpecElementInfo {
    public final Easing easing;
    public final AnimationVector vectorValue;

    public VectorizedKeyframeSpecElementInfo(AnimationVector animationVector, Easing easing) {
        this.vectorValue = animationVector;
        this.easing = easing;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorizedKeyframeSpecElementInfo)) {
            return false;
        }
        VectorizedKeyframeSpecElementInfo vectorizedKeyframeSpecElementInfo = (VectorizedKeyframeSpecElementInfo) obj;
        return Intrinsics.areEqual(this.vectorValue, vectorizedKeyframeSpecElementInfo.vectorValue) && Intrinsics.areEqual(this.easing, vectorizedKeyframeSpecElementInfo.easing);
    }

    public final int hashCode() {
        return Integer.hashCode(0) + ((this.easing.hashCode() + (this.vectorValue.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "VectorizedKeyframeSpecElementInfo(vectorValue=" + this.vectorValue + ", easing=" + this.easing + ", arcMode=ArcMode(value=0))";
    }
}
