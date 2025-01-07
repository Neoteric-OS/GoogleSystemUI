package androidx.compose.ui.text.platform.style;

import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DrawStyleSpan extends CharacterStyle implements UpdateAppearance {
    public final DrawStyle drawStyle;

    public DrawStyleSpan(DrawStyle drawStyle) {
        this.drawStyle = drawStyle;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        if (textPaint != null) {
            DrawStyle drawStyle = this.drawStyle;
            if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
                textPaint.setStyle(Paint.Style.FILL);
                return;
            }
            if (drawStyle instanceof Stroke) {
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setStrokeWidth(((Stroke) this.drawStyle).width);
                textPaint.setStrokeMiter(((Stroke) this.drawStyle).miter);
                int i = ((Stroke) this.drawStyle).join;
                textPaint.setStrokeJoin(StrokeJoin.m394equalsimpl0(i, 0) ? Paint.Join.MITER : StrokeJoin.m394equalsimpl0(i, 1) ? Paint.Join.ROUND : StrokeJoin.m394equalsimpl0(i, 2) ? Paint.Join.BEVEL : Paint.Join.MITER);
                int i2 = ((Stroke) this.drawStyle).cap;
                textPaint.setStrokeCap(StrokeCap.m392equalsimpl0(i2, 0) ? Paint.Cap.BUTT : StrokeCap.m392equalsimpl0(i2, 1) ? Paint.Cap.ROUND : StrokeCap.m392equalsimpl0(i2, 2) ? Paint.Cap.SQUARE : Paint.Cap.BUTT);
                ((Stroke) this.drawStyle).getClass();
                textPaint.setPathEffect(null);
            }
        }
    }
}
