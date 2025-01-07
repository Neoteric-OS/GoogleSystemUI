package androidx.compose.ui.layout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ContentScaleKt {
    /* renamed from: computeFillHeight-iLBOSCw, reason: not valid java name */
    public static final float m477computeFillHeightiLBOSCw(long j, long j2) {
        return Float.intBitsToFloat((int) (j2 & 4294967295L)) / Float.intBitsToFloat((int) (j & 4294967295L));
    }

    /* renamed from: computeFillWidth-iLBOSCw, reason: not valid java name */
    public static final float m478computeFillWidthiLBOSCw(long j, long j2) {
        return Float.intBitsToFloat((int) (j2 >> 32)) / Float.intBitsToFloat((int) (j >> 32));
    }
}
