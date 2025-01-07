package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RadialGradient;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CanvasDrawScope implements DrawScope {
    public final CanvasDrawScope$drawContext$1 drawContext;
    public final DrawParams drawParams;
    public AndroidPaint fillPaint;
    public AndroidPaint strokePaint;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DrawParams {
        public Canvas canvas;
        public Density density;
        public LayoutDirection layoutDirection;
        public long size;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrawParams)) {
                return false;
            }
            DrawParams drawParams = (DrawParams) obj;
            return Intrinsics.areEqual(this.density, drawParams.density) && this.layoutDirection == drawParams.layoutDirection && Intrinsics.areEqual(this.canvas, drawParams.canvas) && Size.m326equalsimpl0(this.size, drawParams.size);
        }

        public final int hashCode() {
            return Long.hashCode(this.size) + ((this.canvas.hashCode() + ((this.layoutDirection.hashCode() + (this.density.hashCode() * 31)) * 31)) * 31);
        }

        public final String toString() {
            return "DrawParams(density=" + this.density + ", layoutDirection=" + this.layoutDirection + ", canvas=" + this.canvas + ", size=" + ((Object) Size.m331toStringimpl(this.size)) + ')';
        }
    }

    public CanvasDrawScope() {
        Density density = DrawContextKt.DefaultDensity;
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        EmptyCanvas emptyCanvas = EmptyCanvas.INSTANCE;
        DrawParams drawParams = new DrawParams();
        drawParams.density = density;
        drawParams.layoutDirection = layoutDirection;
        drawParams.canvas = emptyCanvas;
        drawParams.size = 0L;
        this.drawParams = drawParams;
        this.drawContext = new CanvasDrawScope$drawContext$1(this);
    }

    /* renamed from: configurePaint-2qPWKa0$default, reason: not valid java name */
    public static Paint m406configurePaint2qPWKa0$default(CanvasDrawScope canvasDrawScope, long j, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i) {
        Paint selectPaint = canvasDrawScope.selectPaint(drawStyle);
        if (f != 1.0f) {
            j = ColorKt.Color(Color.m368getRedimpl(j), Color.m367getGreenimpl(j), Color.m365getBlueimpl(j), Color.m364getAlphaimpl(j) * f, Color.m366getColorSpaceimpl(j));
        }
        AndroidPaint androidPaint = (AndroidPaint) selectPaint;
        if (!Color.m363equalsimpl0(ColorKt.Color(androidPaint.internalPaint.getColor()), j)) {
            androidPaint.m348setColor8_81llA(j);
        }
        if (androidPaint.internalShader != null) {
            androidPaint.setShader(null);
        }
        if (!Intrinsics.areEqual(androidPaint.internalColorFilter, colorFilter)) {
            androidPaint.setColorFilter(colorFilter);
        }
        if (!BlendMode.m357equalsimpl0(androidPaint._blendMode, i)) {
            androidPaint.m347setBlendModes9anfk8(i);
        }
        if (!FilterQuality.m375equalsimpl0(androidPaint.internalPaint.isFilterBitmap() ? 1 : 0, 1)) {
            androidPaint.m349setFilterQualityvDHp3xo(1);
        }
        return selectPaint;
    }

    /* renamed from: configurePaint-swdJneE, reason: not valid java name */
    public final Paint m407configurePaintswdJneE(Brush brush, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i, int i2) {
        Paint selectPaint = selectPaint(drawStyle);
        if (brush != null) {
            brush.mo358applyToPq9zytI(f, mo432getSizeNHjbRc(), selectPaint);
        } else {
            AndroidPaint androidPaint = (AndroidPaint) selectPaint;
            if (androidPaint.internalShader != null) {
                androidPaint.setShader(null);
            }
            long Color = ColorKt.Color(androidPaint.internalPaint.getColor());
            long j = Color.Black;
            if (!Color.m363equalsimpl0(Color, j)) {
                androidPaint.m348setColor8_81llA(j);
            }
            if (androidPaint.internalPaint.getAlpha() / 255.0f != f) {
                androidPaint.setAlpha(f);
            }
        }
        AndroidPaint androidPaint2 = (AndroidPaint) selectPaint;
        if (!Intrinsics.areEqual(androidPaint2.internalColorFilter, colorFilter)) {
            androidPaint2.setColorFilter(colorFilter);
        }
        if (!BlendMode.m357equalsimpl0(androidPaint2._blendMode, i)) {
            androidPaint2.m347setBlendModes9anfk8(i);
        }
        if (!FilterQuality.m375equalsimpl0(androidPaint2.internalPaint.isFilterBitmap() ? 1 : 0, i2)) {
            androidPaint2.m349setFilterQualityvDHp3xo(i2);
        }
        return selectPaint;
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-V9BoPsw, reason: not valid java name */
    public final void mo408drawCircleV9BoPsw(RadialGradient radialGradient, float f, long j) {
        this.drawParams.canvas.mo337drawCircle9KIMszo(f, j, m407configurePaintswdJneE(radialGradient, Fill.INSTANCE, 1.0f, null, 9, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-VaOC9Bg, reason: not valid java name */
    public final void mo409drawCircleVaOC9Bg(long j, float f, long j2, float f2) {
        this.drawParams.canvas.mo337drawCircle9KIMszo(f, j2, m406configurePaint2qPWKa0$default(this, j, Fill.INSTANCE, f2, null, 3));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-AZ2fEMs, reason: not valid java name */
    public final void mo410drawImageAZ2fEMs(ImageBitmap imageBitmap, long j, long j2, long j3, float f, ColorFilter colorFilter, int i) {
        this.drawParams.canvas.mo339drawImageRectHPBpro0(imageBitmap, j, j2, j3, m407configurePaintswdJneE(null, Fill.INSTANCE, f, colorFilter, 3, i));
    }

    /* renamed from: drawImage-gbVJVH8, reason: not valid java name */
    public final void m411drawImagegbVJVH8(ImageBitmap imageBitmap, ColorFilter colorFilter) {
        this.drawParams.canvas.mo338drawImaged4ec7I(imageBitmap, m407configurePaintswdJneE(null, Fill.INSTANCE, 1.0f, colorFilter, 3, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawLine-NGM6Ib0, reason: not valid java name */
    public final void mo412drawLineNGM6Ib0(long j, long j2, long j3, float f, int i, float f2) {
        Canvas canvas = this.drawParams.canvas;
        Paint obtainStrokePaint = obtainStrokePaint();
        long Color = f2 == 1.0f ? j : ColorKt.Color(Color.m368getRedimpl(j), Color.m367getGreenimpl(j), Color.m365getBlueimpl(j), Color.m364getAlphaimpl(j) * f2, Color.m366getColorSpaceimpl(j));
        AndroidPaint androidPaint = (AndroidPaint) obtainStrokePaint;
        if (!Color.m363equalsimpl0(ColorKt.Color(androidPaint.internalPaint.getColor()), Color)) {
            androidPaint.m348setColor8_81llA(Color);
        }
        if (androidPaint.internalShader != null) {
            androidPaint.setShader(null);
        }
        if (!Intrinsics.areEqual(androidPaint.internalColorFilter, (Object) null)) {
            androidPaint.setColorFilter(null);
        }
        if (!BlendMode.m357equalsimpl0(androidPaint._blendMode, 3)) {
            androidPaint.m347setBlendModes9anfk8(3);
        }
        if (androidPaint.internalPaint.getStrokeWidth() != f) {
            androidPaint.setStrokeWidth(f);
        }
        if (androidPaint.internalPaint.getStrokeMiter() != 4.0f) {
            androidPaint.internalPaint.setStrokeMiter(4.0f);
        }
        if (!StrokeCap.m392equalsimpl0(androidPaint.m345getStrokeCapKaPHkGw(), i)) {
            androidPaint.m350setStrokeCapBeK7IIE(i);
        }
        if (!StrokeJoin.m394equalsimpl0(androidPaint.m346getStrokeJoinLxFBmk8(), 0)) {
            androidPaint.m351setStrokeJoinWw9F2mQ(0);
        }
        if (!Intrinsics.areEqual((Object) null, (Object) null)) {
            androidPaint.internalPaint.setPathEffect(null);
        }
        if (!FilterQuality.m375equalsimpl0(androidPaint.internalPaint.isFilterBitmap() ? 1 : 0, 1)) {
            androidPaint.m349setFilterQualityvDHp3xo(1);
        }
        canvas.mo340drawLineWko1d7g(j2, j3, obtainStrokePaint);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-GBMwjPU, reason: not valid java name */
    public final void mo413drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle drawStyle, int i) {
        this.drawParams.canvas.drawPath(path, m407configurePaintswdJneE(brush, drawStyle, f, null, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-LG529CI, reason: not valid java name */
    public final void mo414drawPathLG529CI(AndroidPath androidPath, long j) {
        this.drawParams.canvas.drawPath(androidPath, m406configurePaint2qPWKa0$default(this, j, Fill.INSTANCE, 1.0f, null, 3));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-n-J9OG0, reason: not valid java name */
    public final void mo415drawRectnJ9OG0(long j, long j2, long j3, float f, ColorFilter colorFilter, int i) {
        Fill fill = Fill.INSTANCE;
        int i2 = (int) (j2 >> 32);
        int i3 = (int) (j2 & 4294967295L);
        this.drawParams.canvas.drawRect(Float.intBitsToFloat(i2), Float.intBitsToFloat(i3), Float.intBitsToFloat((int) (j3 >> 32)) + Float.intBitsToFloat(i2), Float.intBitsToFloat((int) (j3 & 4294967295L)) + Float.intBitsToFloat(i3), m406configurePaint2qPWKa0$default(this, j, fill, f, colorFilter, i));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-ZuiqVtQ, reason: not valid java name */
    public final void mo416drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle) {
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        this.drawParams.canvas.drawRoundRect(Float.intBitsToFloat(i), Float.intBitsToFloat(i2), Float.intBitsToFloat((int) (j2 >> 32)) + Float.intBitsToFloat(i), Float.intBitsToFloat((int) (j2 & 4294967295L)) + Float.intBitsToFloat(i2), Float.intBitsToFloat((int) (j3 >> 32)), Float.intBitsToFloat((int) (j3 & 4294967295L)), m407configurePaintswdJneE(brush, drawStyle, f, null, 3, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-u-Aw5IA, reason: not valid java name */
    public final void mo417drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle drawStyle, float f) {
        int i = (int) (j2 >> 32);
        int i2 = (int) (j2 & 4294967295L);
        this.drawParams.canvas.drawRoundRect(Float.intBitsToFloat(i), Float.intBitsToFloat(i2), Float.intBitsToFloat((int) (j3 >> 32)) + Float.intBitsToFloat(i), Float.intBitsToFloat((int) (j3 & 4294967295L)) + Float.intBitsToFloat(i2), Float.intBitsToFloat((int) (j4 >> 32)), Float.intBitsToFloat((int) (j4 & 4294967295L)), m406configurePaint2qPWKa0$default(this, j, drawStyle, f, null, 3));
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.drawParams.density.getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public final CanvasDrawScope$drawContext$1 getDrawContext() {
        return this.drawContext;
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public final float getFontScale() {
        return this.drawParams.density.getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public final LayoutDirection getLayoutDirection() {
        return this.drawParams.layoutDirection;
    }

    public final Paint obtainStrokePaint() {
        AndroidPaint androidPaint = this.strokePaint;
        if (androidPaint != null) {
            return androidPaint;
        }
        AndroidPaint Paint = AndroidPaint_androidKt.Paint();
        Paint.m352setStylek9PVt8s(1);
        this.strokePaint = Paint;
        return Paint;
    }

    public final Paint selectPaint(DrawStyle drawStyle) {
        if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
            AndroidPaint androidPaint = this.fillPaint;
            if (androidPaint != null) {
                return androidPaint;
            }
            AndroidPaint Paint = AndroidPaint_androidKt.Paint();
            Paint.m352setStylek9PVt8s(0);
            this.fillPaint = Paint;
            return Paint;
        }
        if (!(drawStyle instanceof Stroke)) {
            throw new NoWhenBranchMatchedException();
        }
        Paint obtainStrokePaint = obtainStrokePaint();
        AndroidPaint androidPaint2 = (AndroidPaint) obtainStrokePaint;
        float strokeWidth = androidPaint2.internalPaint.getStrokeWidth();
        Stroke stroke = (Stroke) drawStyle;
        float f = stroke.width;
        if (strokeWidth != f) {
            androidPaint2.setStrokeWidth(f);
        }
        int m345getStrokeCapKaPHkGw = androidPaint2.m345getStrokeCapKaPHkGw();
        int i = stroke.cap;
        if (!StrokeCap.m392equalsimpl0(m345getStrokeCapKaPHkGw, i)) {
            androidPaint2.m350setStrokeCapBeK7IIE(i);
        }
        float strokeMiter = androidPaint2.internalPaint.getStrokeMiter();
        float f2 = stroke.miter;
        if (strokeMiter != f2) {
            androidPaint2.internalPaint.setStrokeMiter(f2);
        }
        int m346getStrokeJoinLxFBmk8 = androidPaint2.m346getStrokeJoinLxFBmk8();
        int i2 = stroke.join;
        if (!StrokeJoin.m394equalsimpl0(m346getStrokeJoinLxFBmk8, i2)) {
            androidPaint2.m351setStrokeJoinWw9F2mQ(i2);
        }
        if (!Intrinsics.areEqual((Object) null, (Object) null)) {
            androidPaint2.internalPaint.setPathEffect(null);
        }
        return obtainStrokePaint;
    }
}
