package androidx.compose.ui.layout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContentScale$Companion$Inside$1 implements ContentScale {
    @Override // androidx.compose.ui.layout.ContentScale
    /* renamed from: computeScaleFactor-H7hwNQA */
    public final long mo476computeScaleFactorH7hwNQA(long j, long j2) {
        if (Float.intBitsToFloat((int) (j >> 32)) <= Float.intBitsToFloat((int) (j2 >> 32)) && Float.intBitsToFloat((int) (j & 4294967295L)) <= Float.intBitsToFloat((int) (4294967295L & j2))) {
            return ScaleFactorKt.ScaleFactor(1.0f, 1.0f);
        }
        float min = Math.min(ContentScaleKt.m478computeFillWidthiLBOSCw(j, j2), ContentScaleKt.m477computeFillHeightiLBOSCw(j, j2));
        return ScaleFactorKt.ScaleFactor(min, min);
    }
}
