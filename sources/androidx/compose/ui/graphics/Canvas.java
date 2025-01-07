package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface Canvas {
    /* renamed from: clipRect-mtrdD-E$default, reason: not valid java name */
    static void m360clipRectmtrdDE$default(Canvas canvas, Rect rect) {
        canvas.getClass();
        canvas.mo335clipRectN_I0leg(rect.left, rect.top, rect.right, rect.bottom, 1);
    }

    /* renamed from: clipPath-mtrdD-E */
    void mo334clipPathmtrdDE(Path path, int i);

    /* renamed from: clipRect-N_I0leg */
    void mo335clipRectN_I0leg(float f, float f2, float f3, float f4, int i);

    /* renamed from: concat-58bKbWc */
    void mo336concat58bKbWc(float[] fArr);

    void disableZ();

    /* renamed from: drawCircle-9KIMszo */
    void mo337drawCircle9KIMszo(float f, long j, Paint paint);

    /* renamed from: drawImage-d-4ec7I */
    void mo338drawImaged4ec7I(ImageBitmap imageBitmap, Paint paint);

    /* renamed from: drawImageRect-HPBpro0 */
    void mo339drawImageRectHPBpro0(ImageBitmap imageBitmap, long j, long j2, long j3, Paint paint);

    /* renamed from: drawLine-Wko1d7g */
    void mo340drawLineWko1d7g(long j, long j2, Paint paint);

    void drawPath(Path path, Paint paint);

    void drawRect(float f, float f2, float f3, float f4, Paint paint);

    void drawRoundRect(float f, float f2, float f3, float f4, float f5, float f6, Paint paint);

    void enableZ();

    void restore();

    void rotate();

    void save();

    void saveLayer(Rect rect, Paint paint);

    void scale(float f, float f2);

    void translate(float f, float f2);
}
