package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnimationVectorsKt {
    public static final AnimationVector copy(AnimationVector animationVector) {
        AnimationVector newVector$animation_core_release = animationVector.newVector$animation_core_release();
        int size$animation_core_release = newVector$animation_core_release.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            newVector$animation_core_release.set$animation_core_release(i, animationVector.get$animation_core_release(i));
        }
        return newVector$animation_core_release;
    }
}
