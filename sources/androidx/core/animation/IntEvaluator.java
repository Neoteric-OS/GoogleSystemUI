package androidx.core.animation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IntEvaluator implements TypeEvaluator {
    public static final IntEvaluator sInstance = new IntEvaluator();

    @Override // androidx.core.animation.TypeEvaluator
    public final Object evaluate(float f, Object obj, Object obj2) {
        return Integer.valueOf((int) ((f * (((Integer) obj2).intValue() - r0)) + ((Integer) obj).intValue()));
    }
}
