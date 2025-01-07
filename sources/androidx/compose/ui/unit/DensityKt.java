package androidx.compose.ui.unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DensityKt {
    public static final Density Density(float f, float f2) {
        return new DensityImpl(f, f2);
    }

    public static Density Density$default() {
        return new DensityImpl(1.0f, 1.0f);
    }
}
