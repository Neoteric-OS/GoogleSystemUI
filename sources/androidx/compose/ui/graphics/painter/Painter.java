package androidx.compose.ui.graphics.painter;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Painter {
    public ColorFilter colorFilter;
    public AndroidPaint layerPaint;
    public boolean useLayer;
    public float alpha = 1.0f;
    public LayoutDirection layoutDirection = LayoutDirection.Ltr;

    public boolean applyAlpha(float f) {
        return false;
    }

    public boolean applyColorFilter(ColorFilter colorFilter) {
        return false;
    }

    /* renamed from: draw-x_KDEd0, reason: not valid java name */
    public final void m437drawx_KDEd0(ContentDrawScope contentDrawScope, long j, float f, ColorFilter colorFilter) {
        if (this.alpha != f) {
            if (!applyAlpha(f)) {
                if (f == 1.0f) {
                    AndroidPaint androidPaint = this.layerPaint;
                    if (androidPaint != null) {
                        androidPaint.setAlpha(f);
                    }
                    this.useLayer = false;
                } else {
                    AndroidPaint androidPaint2 = this.layerPaint;
                    if (androidPaint2 == null) {
                        androidPaint2 = AndroidPaint_androidKt.Paint();
                        this.layerPaint = androidPaint2;
                    }
                    androidPaint2.setAlpha(f);
                    this.useLayer = true;
                }
            }
            this.alpha = f;
        }
        if (!Intrinsics.areEqual(this.colorFilter, colorFilter)) {
            if (!applyColorFilter(colorFilter)) {
                if (colorFilter == null) {
                    AndroidPaint androidPaint3 = this.layerPaint;
                    if (androidPaint3 != null) {
                        androidPaint3.setColorFilter(null);
                    }
                    this.useLayer = false;
                } else {
                    AndroidPaint androidPaint4 = this.layerPaint;
                    if (androidPaint4 == null) {
                        androidPaint4 = AndroidPaint_androidKt.Paint();
                        this.layerPaint = androidPaint4;
                    }
                    androidPaint4.setColorFilter(colorFilter);
                    this.useLayer = true;
                }
            }
            this.colorFilter = colorFilter;
        }
        LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
        LayoutDirection layoutDirection = layoutNodeDrawScope.getLayoutDirection();
        if (this.layoutDirection != layoutDirection) {
            applyLayoutDirection(layoutDirection);
            this.layoutDirection = layoutDirection;
        }
        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
        int i = (int) (j >> 32);
        float intBitsToFloat = Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() >> 32)) - Float.intBitsToFloat(i);
        int i2 = (int) (j & 4294967295L);
        float intBitsToFloat2 = Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() & 4294967295L)) - Float.intBitsToFloat(i2);
        canvasDrawScope.drawContext.transform.inset(0.0f, 0.0f, intBitsToFloat, intBitsToFloat2);
        if (f > 0.0f) {
            try {
                if (Float.intBitsToFloat(i) > 0.0f && Float.intBitsToFloat(i2) > 0.0f) {
                    if (this.useLayer) {
                        float intBitsToFloat3 = Float.intBitsToFloat(i);
                        float intBitsToFloat4 = Float.intBitsToFloat(i2);
                        Rect m324Recttz77jQw = RectKt.m324Recttz77jQw(0L, (Float.floatToRawIntBits(intBitsToFloat4) & 4294967295L) | (Float.floatToRawIntBits(intBitsToFloat3) << 32));
                        Canvas canvas = ((LayoutNodeDrawScope) contentDrawScope).canvasDrawScope.drawContext.getCanvas();
                        AndroidPaint androidPaint5 = this.layerPaint;
                        if (androidPaint5 == null) {
                            androidPaint5 = AndroidPaint_androidKt.Paint();
                            this.layerPaint = androidPaint5;
                        }
                        try {
                            canvas.saveLayer(m324Recttz77jQw, androidPaint5);
                            onDraw(contentDrawScope);
                            canvas.restore();
                        } catch (Throwable th) {
                            canvas.restore();
                            throw th;
                        }
                    } else {
                        onDraw(contentDrawScope);
                    }
                }
            } catch (Throwable th2) {
                canvasDrawScope.drawContext.transform.inset(-0.0f, -0.0f, -intBitsToFloat, -intBitsToFloat2);
                throw th2;
            }
        }
        canvasDrawScope.drawContext.transform.inset(-0.0f, -0.0f, -intBitsToFloat, -intBitsToFloat2);
    }

    /* renamed from: getIntrinsicSize-NH-jbRc */
    public abstract long mo436getIntrinsicSizeNHjbRc();

    public abstract void onDraw(DrawScope drawScope);

    public void applyLayoutDirection(LayoutDirection layoutDirection) {
    }
}
