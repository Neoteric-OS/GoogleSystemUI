package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class OutlineKt {
    /* renamed from: drawOutline-hn5TExg$default, reason: not valid java name */
    public static void m386drawOutlinehn5TExg$default(ContentDrawScope contentDrawScope, Outline outline, Brush brush, float f) {
        AndroidPath androidPath;
        Fill fill = Fill.INSTANCE;
        if (outline instanceof Outline.Rectangle) {
            ((LayoutNodeDrawScope) contentDrawScope).m511drawRectAsUm42w(brush, (Float.floatToRawIntBits(r0.left) << 32) | (Float.floatToRawIntBits(r0.top) & 4294967295L), size(((Outline.Rectangle) outline).rect), f, fill);
            return;
        }
        if (outline instanceof Outline.Rounded) {
            Outline.Rounded rounded = (Outline.Rounded) outline;
            androidPath = rounded.roundRectPath;
            if (androidPath == null) {
                RoundRect roundRect = rounded.roundRect;
                float intBitsToFloat = Float.intBitsToFloat((int) (roundRect.bottomLeftCornerRadius >> 32));
                long floatToRawIntBits = (Float.floatToRawIntBits(roundRect.left) << 32) | (Float.floatToRawIntBits(roundRect.top) & 4294967295L);
                float width = roundRect.getWidth();
                float height = roundRect.getHeight();
                ((LayoutNodeDrawScope) contentDrawScope).mo416drawRoundRectZuiqVtQ(brush, floatToRawIntBits, (Float.floatToRawIntBits(width) << 32) | (Float.floatToRawIntBits(height) & 4294967295L), (Float.floatToRawIntBits(intBitsToFloat) << 32) | (Float.floatToRawIntBits(intBitsToFloat) & 4294967295L), f, fill);
                return;
            }
        } else {
            if (!(outline instanceof Outline.Generic)) {
                throw new NoWhenBranchMatchedException();
            }
            androidPath = ((Outline.Generic) outline).path;
        }
        ((LayoutNodeDrawScope) contentDrawScope).mo413drawPathGBMwjPU(androidPath, brush, f, fill, 3);
    }

    /* renamed from: drawOutline-wDX37Ww$default, reason: not valid java name */
    public static void m387drawOutlinewDX37Ww$default(DrawScope drawScope, Outline outline, long j) {
        Fill fill = Fill.INSTANCE;
        if (outline instanceof Outline.Rectangle) {
            Rect rect = ((Outline.Rectangle) outline).rect;
            drawScope.mo415drawRectnJ9OG0(j, (Float.floatToRawIntBits(rect.top) & 4294967295L) | (Float.floatToRawIntBits(rect.left) << 32), size(rect), 1.0f, null, 3);
            return;
        }
        if (!(outline instanceof Outline.Rounded)) {
            if (!(outline instanceof Outline.Generic)) {
                throw new NoWhenBranchMatchedException();
            }
            drawScope.mo414drawPathLG529CI(((Outline.Generic) outline).path, j);
            return;
        }
        Outline.Rounded rounded = (Outline.Rounded) outline;
        AndroidPath androidPath = rounded.roundRectPath;
        if (androidPath != null) {
            drawScope.mo414drawPathLG529CI(androidPath, j);
            return;
        }
        RoundRect roundRect = rounded.roundRect;
        float intBitsToFloat = Float.intBitsToFloat((int) (roundRect.bottomLeftCornerRadius >> 32));
        long floatToRawIntBits = (Float.floatToRawIntBits(roundRect.left) << 32) | (Float.floatToRawIntBits(roundRect.top) & 4294967295L);
        float width = roundRect.getWidth();
        float height = roundRect.getHeight();
        drawScope.mo417drawRoundRectuAw5IA(j, floatToRawIntBits, (Float.floatToRawIntBits(height) & 4294967295L) | (Float.floatToRawIntBits(width) << 32), (Float.floatToRawIntBits(intBitsToFloat) << 32) | (Float.floatToRawIntBits(intBitsToFloat) & 4294967295L), fill, 1.0f);
    }

    public static final long size(Rect rect) {
        float f = rect.right - rect.left;
        float f2 = rect.bottom - rect.top;
        return (Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(f2) & 4294967295L);
    }
}
