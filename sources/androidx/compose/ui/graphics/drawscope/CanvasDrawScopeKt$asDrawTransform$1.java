package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.InlineClassHelperKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CanvasDrawScopeKt$asDrawTransform$1 {
    public final /* synthetic */ CanvasDrawScope$drawContext$1 $this_asDrawTransform;

    public CanvasDrawScopeKt$asDrawTransform$1(CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1) {
        this.$this_asDrawTransform = canvasDrawScope$drawContext$1;
    }

    /* renamed from: clipRect-N_I0leg, reason: not valid java name */
    public final void m420clipRectN_I0leg(float f, float f2, float f3, float f4, int i) {
        this.$this_asDrawTransform.getCanvas().mo335clipRectN_I0leg(f, f2, f3, f4, i);
    }

    public final void inset(float f, float f2, float f3, float f4) {
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = this.$this_asDrawTransform;
        Canvas canvas = canvasDrawScope$drawContext$1.getCanvas();
        float intBitsToFloat = Float.intBitsToFloat((int) (canvasDrawScope$drawContext$1.m418getSizeNHjbRc() >> 32)) - (f3 + f);
        float intBitsToFloat2 = Float.intBitsToFloat((int) (canvasDrawScope$drawContext$1.m418getSizeNHjbRc() & 4294967295L)) - (f4 + f2);
        long floatToRawIntBits = (Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L) | (Float.floatToRawIntBits(intBitsToFloat) << 32);
        if (Float.intBitsToFloat((int) (floatToRawIntBits >> 32)) < 0.0f || Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L)) < 0.0f) {
            InlineClassHelperKt.throwIllegalArgumentException("Width and height must be greater than or equal to zero");
        }
        canvasDrawScope$drawContext$1.m419setSizeuvyYCjk(floatToRawIntBits);
        canvas.translate(f, f2);
    }

    /* renamed from: scale-0AR0LA0, reason: not valid java name */
    public final void m421scale0AR0LA0(float f, float f2, long j) {
        Canvas canvas = this.$this_asDrawTransform.getCanvas();
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        canvas.translate(Float.intBitsToFloat(i), Float.intBitsToFloat(i2));
        canvas.scale(f, f2);
        canvas.translate(-Float.intBitsToFloat(i), -Float.intBitsToFloat(i2));
    }

    public final void translate(float f, float f2) {
        this.$this_asDrawTransform.getCanvas().translate(f, f2);
    }
}
