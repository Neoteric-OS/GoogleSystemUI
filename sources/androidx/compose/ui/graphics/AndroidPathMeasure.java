package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidPathMeasure implements PathMeasure {
    public final android.graphics.PathMeasure internalPathMeasure;

    public AndroidPathMeasure(android.graphics.PathMeasure pathMeasure) {
        this.internalPathMeasure = pathMeasure;
    }

    public final boolean getSegment(float f, float f2, AndroidPath androidPath) {
        android.graphics.PathMeasure pathMeasure = this.internalPathMeasure;
        if (androidPath != null) {
            return pathMeasure.getSegment(f, f2, androidPath.internalPath, true);
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }
}
