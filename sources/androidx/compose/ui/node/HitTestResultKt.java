package androidx.compose.ui.node;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class HitTestResultKt {
    public static final long access$DistanceAndInLayer(float f, boolean z) {
        return ((z ? 1L : 0L) & 4294967295L) | (Float.floatToRawIntBits(f) << 32);
    }
}
