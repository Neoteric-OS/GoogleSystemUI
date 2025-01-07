package androidx.compose.ui.text.style;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.SpanStyleKt;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.util.MathHelpersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextDrawStyleKt {
    public static final TextForegroundStyle lerp(TextForegroundStyle textForegroundStyle, TextForegroundStyle textForegroundStyle2, float f) {
        boolean z = textForegroundStyle instanceof BrushStyle;
        return (z || (textForegroundStyle2 instanceof BrushStyle)) ? (z && (textForegroundStyle2 instanceof BrushStyle)) ? TextForegroundStyle.Companion.from((Brush) SpanStyleKt.lerpDiscrete(f, ((BrushStyle) textForegroundStyle).value, ((BrushStyle) textForegroundStyle2).value), MathHelpersKt.lerp(((BrushStyle) textForegroundStyle).alpha, ((BrushStyle) textForegroundStyle2).alpha, f)) : (TextForegroundStyle) SpanStyleKt.lerpDiscrete(f, textForegroundStyle, textForegroundStyle2) : TextForegroundStyle.Companion.m645from8_81llA(ColorKt.m372lerpjxsXWHM(f, textForegroundStyle.mo631getColor0d7_KjU(), textForegroundStyle2.mo631getColor0d7_KjU()));
    }

    /* renamed from: modulate-DxMtmZc, reason: not valid java name */
    public static final long m644modulateDxMtmZc(float f, long j) {
        long Color;
        if (Float.isNaN(f) || f >= 1.0f) {
            return j;
        }
        Color = ColorKt.Color(Color.m368getRedimpl(j), Color.m367getGreenimpl(j), Color.m365getBlueimpl(j), Color.m364getAlphaimpl(j) * f, Color.m366getColorSpaceimpl(j));
        return Color;
    }
}
