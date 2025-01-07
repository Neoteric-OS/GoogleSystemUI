package androidx.compose.foundation.shape;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CornerSizeKt {
    public static final CornerSize CornerSize(int i) {
        return new PercentCornerSize(i);
    }

    /* renamed from: CornerSize-0680j_4, reason: not valid java name */
    public static final CornerSize m147CornerSize0680j_4(float f) {
        return new DpCornerSize(f);
    }
}
