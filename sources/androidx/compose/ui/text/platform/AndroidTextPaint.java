package androidx.compose.ui.text.platform;

import android.graphics.Paint;
import android.text.TextPaint;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidTextPaint extends TextPaint {
    public int backingBlendMode;
    public AndroidPaint backingComposePaint;
    public Brush brush;
    public Size brushSize;
    public DrawStyle drawStyle;
    public State shaderState;
    public Shadow shadow;
    public TextDecoration textDecoration;

    public final Paint getComposePaint() {
        AndroidPaint androidPaint = this.backingComposePaint;
        if (androidPaint != null) {
            return androidPaint;
        }
        AndroidPaint androidPaint2 = new AndroidPaint(this);
        this.backingComposePaint = androidPaint2;
        return androidPaint2;
    }

    /* renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m625setBlendModes9anfk8(int i) {
        if (BlendMode.m357equalsimpl0(i, this.backingBlendMode)) {
            return;
        }
        ((AndroidPaint) getComposePaint()).m347setBlendModes9anfk8(i);
        this.backingBlendMode = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
    
        if ((r1 == null ? false : androidx.compose.ui.geometry.Size.m326equalsimpl0(r1.packedValue, r7)) == false) goto L17;
     */
    /* renamed from: setBrush-12SF9DM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m626setBrush12SF9DM(final androidx.compose.ui.graphics.Brush r6, final long r7, float r9) {
        /*
            r5 = this;
            r0 = 0
            if (r6 != 0) goto Ld
            r5.shaderState = r0
            r5.brush = r0
            r5.brushSize = r0
            r5.setShader(r0)
            goto L6f
        Ld:
            boolean r1 = r6 instanceof androidx.compose.ui.graphics.SolidColor
            if (r1 == 0) goto L1d
            androidx.compose.ui.graphics.SolidColor r6 = (androidx.compose.ui.graphics.SolidColor) r6
            long r6 = r6.value
            long r6 = androidx.compose.ui.text.style.TextDrawStyleKt.m644modulateDxMtmZc(r9, r6)
            r5.m627setColor8_81llA(r6)
            goto L6f
        L1d:
            boolean r1 = r6 instanceof androidx.compose.ui.graphics.ShaderBrush
            if (r1 == 0) goto L6f
            androidx.compose.ui.graphics.Brush r1 = r5.brush
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r6)
            r2 = 0
            if (r1 == 0) goto L38
            androidx.compose.ui.geometry.Size r1 = r5.brushSize
            if (r1 != 0) goto L30
            r1 = r2
            goto L36
        L30:
            long r3 = r1.packedValue
            boolean r1 = androidx.compose.ui.geometry.Size.m326equalsimpl0(r3, r7)
        L36:
            if (r1 != 0) goto L58
        L38:
            r3 = 9205357640488583168(0x7fc000007fc00000, double:2.247117487993712E307)
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 == 0) goto L42
            r2 = 1
        L42:
            if (r2 == 0) goto L58
            r5.brush = r6
            androidx.compose.ui.geometry.Size r1 = new androidx.compose.ui.geometry.Size
            r1.<init>(r7)
            r5.brushSize = r1
            androidx.compose.ui.text.platform.AndroidTextPaint$setBrush$1 r1 = new androidx.compose.ui.text.platform.AndroidTextPaint$setBrush$1
            r1.<init>()
            androidx.compose.runtime.State r6 = androidx.compose.runtime.SnapshotStateKt.derivedStateOf(r1)
            r5.shaderState = r6
        L58:
            androidx.compose.ui.graphics.Paint r6 = r5.getComposePaint()
            androidx.compose.runtime.State r7 = r5.shaderState
            if (r7 == 0) goto L67
            java.lang.Object r7 = r7.getValue()
            r0 = r7
            android.graphics.Shader r0 = (android.graphics.Shader) r0
        L67:
            androidx.compose.ui.graphics.AndroidPaint r6 = (androidx.compose.ui.graphics.AndroidPaint) r6
            r6.setShader(r0)
            androidx.compose.ui.text.platform.AndroidTextPaint_androidKt.setAlpha(r5, r9)
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.platform.AndroidTextPaint.m626setBrush12SF9DM(androidx.compose.ui.graphics.Brush, long, float):void");
    }

    /* renamed from: setColor-8_81llA, reason: not valid java name */
    public final void m627setColor8_81llA(long j) {
        if (j != 16) {
            setColor(ColorKt.m373toArgb8_81llA(j));
            this.shaderState = null;
            this.brush = null;
            this.brushSize = null;
            setShader(null);
        }
    }

    public final void setDrawStyle(DrawStyle drawStyle) {
        if (drawStyle == null || Intrinsics.areEqual(this.drawStyle, drawStyle)) {
            return;
        }
        this.drawStyle = drawStyle;
        if (drawStyle.equals(Fill.INSTANCE)) {
            setStyle(Paint.Style.FILL);
            return;
        }
        if (drawStyle instanceof Stroke) {
            ((AndroidPaint) getComposePaint()).m352setStylek9PVt8s(1);
            Stroke stroke = (Stroke) drawStyle;
            ((AndroidPaint) getComposePaint()).setStrokeWidth(stroke.width);
            ((AndroidPaint) getComposePaint()).internalPaint.setStrokeMiter(stroke.miter);
            ((AndroidPaint) getComposePaint()).m351setStrokeJoinWw9F2mQ(stroke.join);
            ((AndroidPaint) getComposePaint()).m350setStrokeCapBeK7IIE(stroke.cap);
            ((AndroidPaint) getComposePaint()).internalPaint.setPathEffect(null);
        }
    }

    public final void setShadow(Shadow shadow) {
        if (shadow == null || Intrinsics.areEqual(this.shadow, shadow)) {
            return;
        }
        this.shadow = shadow;
        if (shadow.equals(Shadow.None)) {
            clearShadowLayer();
            return;
        }
        Shadow shadow2 = this.shadow;
        float f = shadow2.blurRadius;
        if (f == 0.0f) {
            f = Float.MIN_VALUE;
        }
        setShadowLayer(f, Float.intBitsToFloat((int) (shadow2.offset >> 32)), Float.intBitsToFloat((int) (this.shadow.offset & 4294967295L)), ColorKt.m373toArgb8_81llA(this.shadow.color));
    }

    public final void setTextDecoration(TextDecoration textDecoration) {
        if (textDecoration == null || Intrinsics.areEqual(this.textDecoration, textDecoration)) {
            return;
        }
        this.textDecoration = textDecoration;
        setUnderlineText(textDecoration.contains(TextDecoration.Underline));
        setStrikeThruText(this.textDecoration.contains(TextDecoration.LineThrough));
    }
}
