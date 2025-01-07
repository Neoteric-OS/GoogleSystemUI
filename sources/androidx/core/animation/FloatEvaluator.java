package androidx.core.animation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FloatEvaluator implements TypeEvaluator {
    public static final FloatEvaluator sInstance = new FloatEvaluator();

    @Override // androidx.core.animation.TypeEvaluator
    public final Object evaluate(float f, Object obj, Object obj2) {
        float floatValue = ((Float) obj).floatValue();
        return Float.valueOf(((((Float) obj2).floatValue() - floatValue) * f) + floatValue);
    }
}
