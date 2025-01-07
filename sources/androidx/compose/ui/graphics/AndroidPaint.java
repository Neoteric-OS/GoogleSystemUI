package androidx.compose.ui.graphics;

import android.graphics.Paint;
import android.graphics.Shader;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidPaint implements Paint {
    public int _blendMode = 3;
    public ColorFilter internalColorFilter;
    public final android.graphics.Paint internalPaint;
    public Shader internalShader;

    public AndroidPaint(android.graphics.Paint paint) {
        this.internalPaint = paint;
    }

    /* renamed from: getStrokeCap-KaPHkGw, reason: not valid java name */
    public final int m345getStrokeCapKaPHkGw() {
        Paint.Cap strokeCap = this.internalPaint.getStrokeCap();
        int i = strokeCap == null ? -1 : AndroidPaint_androidKt.WhenMappings.$EnumSwitchMapping$1[strokeCap.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i != 2) {
            return i != 3 ? 0 : 2;
        }
        return 1;
    }

    /* renamed from: getStrokeJoin-LxFBmk8, reason: not valid java name */
    public final int m346getStrokeJoinLxFBmk8() {
        Paint.Join strokeJoin = this.internalPaint.getStrokeJoin();
        int i = strokeJoin == null ? -1 : AndroidPaint_androidKt.WhenMappings.$EnumSwitchMapping$2[strokeJoin.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i != 2) {
            return i != 3 ? 0 : 1;
        }
        return 2;
    }

    public final void setAlpha(float f) {
        this.internalPaint.setAlpha((int) Math.rint(f * 255.0f));
    }

    /* renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m347setBlendModes9anfk8(int i) {
        if (BlendMode.m357equalsimpl0(this._blendMode, i)) {
            return;
        }
        this._blendMode = i;
        this.internalPaint.setBlendMode(AndroidBlendMode_androidKt.m333toAndroidBlendModes9anfk8(i));
    }

    /* renamed from: setColor-8_81llA, reason: not valid java name */
    public final void m348setColor8_81llA(long j) {
        this.internalPaint.setColor(ColorKt.m373toArgb8_81llA(j));
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.internalColorFilter = colorFilter;
        this.internalPaint.setColorFilter(colorFilter != null ? colorFilter.nativeColorFilter : null);
    }

    /* renamed from: setFilterQuality-vDHp3xo, reason: not valid java name */
    public final void m349setFilterQualityvDHp3xo(int i) {
        this.internalPaint.setFilterBitmap(!FilterQuality.m375equalsimpl0(i, 0));
    }

    public final void setShader(Shader shader) {
        this.internalShader = shader;
        this.internalPaint.setShader(shader);
    }

    /* renamed from: setStrokeCap-BeK7IIE, reason: not valid java name */
    public final void m350setStrokeCapBeK7IIE(int i) {
        this.internalPaint.setStrokeCap(StrokeCap.m392equalsimpl0(i, 2) ? Paint.Cap.SQUARE : StrokeCap.m392equalsimpl0(i, 1) ? Paint.Cap.ROUND : StrokeCap.m392equalsimpl0(i, 0) ? Paint.Cap.BUTT : Paint.Cap.BUTT);
    }

    /* renamed from: setStrokeJoin-Ww9F2mQ, reason: not valid java name */
    public final void m351setStrokeJoinWw9F2mQ(int i) {
        this.internalPaint.setStrokeJoin(StrokeJoin.m394equalsimpl0(i, 0) ? Paint.Join.MITER : StrokeJoin.m394equalsimpl0(i, 2) ? Paint.Join.BEVEL : StrokeJoin.m394equalsimpl0(i, 1) ? Paint.Join.ROUND : Paint.Join.MITER);
    }

    public final void setStrokeWidth(float f) {
        this.internalPaint.setStrokeWidth(f);
    }

    /* renamed from: setStyle-k9PVt8s, reason: not valid java name */
    public final void m352setStylek9PVt8s(int i) {
        this.internalPaint.setStyle(i == 1 ? Paint.Style.STROKE : Paint.Style.FILL);
    }
}
