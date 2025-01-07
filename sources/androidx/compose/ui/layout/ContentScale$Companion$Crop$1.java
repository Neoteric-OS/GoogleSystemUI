package androidx.compose.ui.layout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContentScale$Companion$Crop$1 implements ContentScale {
    @Override // androidx.compose.ui.layout.ContentScale
    /* renamed from: computeScaleFactor-H7hwNQA */
    public final long mo476computeScaleFactorH7hwNQA(long j, long j2) {
        float max = Math.max(ContentScaleKt.m478computeFillWidthiLBOSCw(j, j2), ContentScaleKt.m477computeFillHeightiLBOSCw(j, j2));
        return ScaleFactorKt.ScaleFactor(max, max);
    }
}
