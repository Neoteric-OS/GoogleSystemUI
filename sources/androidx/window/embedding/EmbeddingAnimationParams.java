package androidx.window.embedding;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmbeddingAnimationParams {
    public final EmbeddingAnimationBackground animationBackground;
    public final AnimationSpec changeAnimation;
    public final AnimationSpec closeAnimation;
    public final AnimationSpec openAnimation;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationSpec {
        public static final AnimationSpec DEFAULT = new AnimationSpec(0);
        public static final AnimationSpec JUMP_CUT = new AnimationSpec(1);
        public final int value;

        public AnimationSpec(int i) {
            this.value = i;
        }

        public final String toString() {
            int i = this.value;
            return i != 0 ? i != 1 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown value: ") : "JUMP_CUT" : "DEFAULT";
        }
    }

    public EmbeddingAnimationParams(EmbeddingAnimationBackground embeddingAnimationBackground, AnimationSpec animationSpec, AnimationSpec animationSpec2, AnimationSpec animationSpec3) {
        this.animationBackground = embeddingAnimationBackground;
        this.openAnimation = animationSpec;
        this.closeAnimation = animationSpec2;
        this.changeAnimation = animationSpec3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmbeddingAnimationParams)) {
            return false;
        }
        EmbeddingAnimationParams embeddingAnimationParams = (EmbeddingAnimationParams) obj;
        return Intrinsics.areEqual(this.animationBackground, embeddingAnimationParams.animationBackground) && Intrinsics.areEqual(this.openAnimation, embeddingAnimationParams.openAnimation) && Intrinsics.areEqual(this.closeAnimation, embeddingAnimationParams.closeAnimation) && Intrinsics.areEqual(this.changeAnimation, embeddingAnimationParams.changeAnimation);
    }

    public final int hashCode() {
        return this.changeAnimation.hashCode() + ((this.closeAnimation.hashCode() + ((this.openAnimation.hashCode() + (this.animationBackground.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "EmbeddingAnimationParams:{animationBackground=" + this.animationBackground + ", openAnimation=" + this.openAnimation + ", closeAnimation=" + this.closeAnimation + ", changeAnimation=" + this.changeAnimation + " }";
    }
}
