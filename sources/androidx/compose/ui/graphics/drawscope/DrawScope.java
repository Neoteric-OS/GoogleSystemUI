package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RadialGradient;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DrawScope extends Density {
    /* renamed from: drawCircle-VaOC9Bg$default, reason: not valid java name */
    static /* synthetic */ void m422drawCircleVaOC9Bg$default(DrawScope drawScope, long j, float f, long j2, float f2, int i) {
        if ((i & 2) != 0) {
            f = Size.m328getMinDimensionimpl(drawScope.mo432getSizeNHjbRc()) / 2.0f;
        }
        float f3 = f;
        if ((i & 4) != 0) {
            j2 = drawScope.mo431getCenterF1C5BW0();
        }
        long j3 = j2;
        if ((i & 8) != 0) {
            f2 = 1.0f;
        }
        drawScope.mo409drawCircleVaOC9Bg(j, f3, j3, f2);
    }

    /* renamed from: drawLine-1RTmtNc$default, reason: not valid java name */
    static void m424drawLine1RTmtNc$default(LayoutNodeDrawScope layoutNodeDrawScope, Brush brush, long j, long j2, float f, float f2, int i) {
        if ((i & 64) != 0) {
            f2 = 1.0f;
        }
        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
        Canvas canvas = canvasDrawScope.drawParams.canvas;
        Paint obtainStrokePaint = canvasDrawScope.obtainStrokePaint();
        if (brush != null) {
            brush.mo358applyToPq9zytI(f2, canvasDrawScope.mo432getSizeNHjbRc(), obtainStrokePaint);
        } else {
            AndroidPaint androidPaint = (AndroidPaint) obtainStrokePaint;
            if (androidPaint.internalPaint.getAlpha() / 255.0f != f2) {
                androidPaint.setAlpha(f2);
            }
        }
        AndroidPaint androidPaint2 = (AndroidPaint) obtainStrokePaint;
        if (!Intrinsics.areEqual(androidPaint2.internalColorFilter, (Object) null)) {
            androidPaint2.setColorFilter(null);
        }
        if (!BlendMode.m357equalsimpl0(androidPaint2._blendMode, 3)) {
            androidPaint2.m347setBlendModes9anfk8(3);
        }
        if (androidPaint2.internalPaint.getStrokeWidth() != f) {
            androidPaint2.setStrokeWidth(f);
        }
        if (androidPaint2.internalPaint.getStrokeMiter() != 4.0f) {
            androidPaint2.internalPaint.setStrokeMiter(4.0f);
        }
        if (!StrokeCap.m392equalsimpl0(androidPaint2.m345getStrokeCapKaPHkGw(), 0)) {
            androidPaint2.m350setStrokeCapBeK7IIE(0);
        }
        if (!StrokeJoin.m394equalsimpl0(androidPaint2.m346getStrokeJoinLxFBmk8(), 0)) {
            androidPaint2.m351setStrokeJoinWw9F2mQ(0);
        }
        if (!Intrinsics.areEqual((Object) null, (Object) null)) {
            androidPaint2.internalPaint.setPathEffect(null);
        }
        if (!FilterQuality.m375equalsimpl0(androidPaint2.internalPaint.isFilterBitmap() ? 1 : 0, 1)) {
            androidPaint2.m349setFilterQualityvDHp3xo(1);
        }
        canvas.mo340drawLineWko1d7g(j, j2, obtainStrokePaint);
    }

    /* renamed from: drawPath-GBMwjPU$default, reason: not valid java name */
    static /* synthetic */ void m425drawPathGBMwjPU$default(DrawScope drawScope, Path path, Brush brush, float f, Stroke stroke, int i) {
        if ((i & 4) != 0) {
            f = 1.0f;
        }
        float f2 = f;
        DrawStyle drawStyle = stroke;
        if ((i & 8) != 0) {
            drawStyle = Fill.INSTANCE;
        }
        drawScope.mo413drawPathGBMwjPU(path, brush, f2, drawStyle, (i & 32) != 0 ? 3 : 0);
    }

    /* renamed from: drawRect-AsUm42w$default, reason: not valid java name */
    static void m426drawRectAsUm42w$default(ContentDrawScope contentDrawScope, Brush brush, long j, long j2, float f, DrawStyle drawStyle, int i) {
        long j3 = (i & 2) != 0 ? 0L : j;
        ((LayoutNodeDrawScope) contentDrawScope).m511drawRectAsUm42w(brush, j3, (i & 4) != 0 ? m430offsetSizePENXr5M(((LayoutNodeDrawScope) contentDrawScope).canvasDrawScope.mo432getSizeNHjbRc(), j3) : j2, (i & 8) != 0 ? 1.0f : f, (i & 16) != 0 ? Fill.INSTANCE : drawStyle);
    }

    /* renamed from: drawRoundRect-ZuiqVtQ$default, reason: not valid java name */
    static /* synthetic */ void m428drawRoundRectZuiqVtQ$default(DrawScope drawScope, Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, int i) {
        long j4 = (i & 2) != 0 ? 0L : j;
        drawScope.mo416drawRoundRectZuiqVtQ(brush, j4, (i & 4) != 0 ? m430offsetSizePENXr5M(drawScope.mo432getSizeNHjbRc(), j4) : j2, j3, (i & 16) != 0 ? 1.0f : f, (i & 32) != 0 ? Fill.INSTANCE : drawStyle);
    }

    /* renamed from: drawRoundRect-u-Aw5IA$default, reason: not valid java name */
    static /* synthetic */ void m429drawRoundRectuAw5IA$default(DrawScope drawScope, long j, long j2, long j3, long j4, Stroke stroke, float f, int i) {
        long j5 = (i & 2) != 0 ? 0L : j2;
        drawScope.mo417drawRoundRectuAw5IA(j, j5, (i & 4) != 0 ? m430offsetSizePENXr5M(drawScope.mo432getSizeNHjbRc(), j5) : j3, j4, (i & 16) != 0 ? Fill.INSTANCE : stroke, (i & 32) != 0 ? 1.0f : f);
    }

    /* renamed from: offsetSize-PENXr5M, reason: not valid java name */
    static long m430offsetSizePENXr5M(long j, long j2) {
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) - Float.intBitsToFloat((int) (j2 >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L)) - Float.intBitsToFloat((int) (j2 & 4294967295L));
        return (Float.floatToRawIntBits(intBitsToFloat) << 32) | (Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L);
    }

    /* renamed from: drawCircle-V9BoPsw */
    void mo408drawCircleV9BoPsw(RadialGradient radialGradient, float f, long j);

    /* renamed from: drawCircle-VaOC9Bg */
    void mo409drawCircleVaOC9Bg(long j, float f, long j2, float f2);

    /* renamed from: drawImage-AZ2fEMs */
    void mo410drawImageAZ2fEMs(ImageBitmap imageBitmap, long j, long j2, long j3, float f, ColorFilter colorFilter, int i);

    /* renamed from: drawLine-NGM6Ib0 */
    void mo412drawLineNGM6Ib0(long j, long j2, long j3, float f, int i, float f2);

    /* renamed from: drawPath-GBMwjPU */
    void mo413drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle drawStyle, int i);

    /* renamed from: drawPath-LG529CI */
    void mo414drawPathLG529CI(AndroidPath androidPath, long j);

    /* renamed from: drawRect-n-J9OG0 */
    void mo415drawRectnJ9OG0(long j, long j2, long j3, float f, ColorFilter colorFilter, int i);

    /* renamed from: drawRoundRect-ZuiqVtQ */
    void mo416drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle);

    /* renamed from: drawRoundRect-u-Aw5IA */
    void mo417drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle drawStyle, float f);

    /* renamed from: getCenter-F1C5BW0, reason: not valid java name */
    default long mo431getCenterF1C5BW0() {
        return SizeKt.m332getCenteruvyYCjk(getDrawContext().m418getSizeNHjbRc());
    }

    CanvasDrawScope$drawContext$1 getDrawContext();

    LayoutDirection getLayoutDirection();

    /* renamed from: getSize-NH-jbRc, reason: not valid java name */
    default long mo432getSizeNHjbRc() {
        return getDrawContext().m418getSizeNHjbRc();
    }
}
