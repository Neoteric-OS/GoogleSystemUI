package androidx.compose.ui.node;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DistanceAndInLayer {
    /* renamed from: compareTo-S_HNhKs, reason: not valid java name */
    public static final int m504compareToS_HNhKs(long j, long j2) {
        boolean z = ((int) (j & 4294967295L)) != 0;
        return z != (((int) (4294967295L & j2)) != 0) ? z ? -1 : 1 : (int) Math.signum(Float.intBitsToFloat((int) (j >> 32)) - Float.intBitsToFloat((int) (j2 >> 32)));
    }
}
