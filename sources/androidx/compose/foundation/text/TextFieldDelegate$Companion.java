package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputSession;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldDelegate$Companion {
    /* renamed from: drawHighlight-Le-punE, reason: not valid java name */
    public static void m162drawHighlightLepunE(Canvas canvas, long j, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, AndroidPaint androidPaint) {
        int originalToTransformed = offsetMapping.originalToTransformed(TextRange.m601getMinimpl(j));
        int originalToTransformed2 = offsetMapping.originalToTransformed(TextRange.m600getMaximpl(j));
        if (originalToTransformed != originalToTransformed2) {
            canvas.drawPath(textLayoutResult.getPathForRange(originalToTransformed, originalToTransformed2), androidPaint);
        }
    }

    public static void notifyFocusedRect$foundation_release(TextFieldValue textFieldValue, TextDelegate textDelegate, TextLayoutResult textLayoutResult, LayoutCoordinates layoutCoordinates, TextInputSession textInputSession, boolean z, OffsetMapping offsetMapping) {
        long computeSizeForDefaultText;
        Rect rect;
        if (z) {
            int originalToTransformed = offsetMapping.originalToTransformed(TextRange.m600getMaximpl(textFieldValue.selection));
            if (originalToTransformed < textLayoutResult.layoutInput.text.text.length()) {
                rect = textLayoutResult.getBoundingBox(originalToTransformed);
            } else if (originalToTransformed != 0) {
                rect = textLayoutResult.getBoundingBox(originalToTransformed - 1);
            } else {
                computeSizeForDefaultText = TextFieldDelegateKt.computeSizeForDefaultText(textDelegate.style, textDelegate.density, textDelegate.fontFamilyResolver, TextFieldDelegateKt.EmptyTextReplacement, 1);
                rect = new Rect(0.0f, 0.0f, 1.0f, (int) (computeSizeForDefaultText & 4294967295L));
            }
            float f = rect.left;
            long floatToRawIntBits = Float.floatToRawIntBits(f);
            float f2 = rect.top;
            long mo484localToRootMKHz9U = layoutCoordinates.mo484localToRootMKHz9U((floatToRawIntBits << 32) | (Float.floatToRawIntBits(f2) & 4294967295L));
            float intBitsToFloat = Float.intBitsToFloat((int) (mo484localToRootMKHz9U >> 32));
            float intBitsToFloat2 = Float.intBitsToFloat((int) (mo484localToRootMKHz9U & 4294967295L));
            long floatToRawIntBits2 = (Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L) | (Float.floatToRawIntBits(intBitsToFloat) << 32);
            float f3 = rect.right - f;
            float f4 = rect.bottom - f2;
            Rect m324Recttz77jQw = RectKt.m324Recttz77jQw(floatToRawIntBits2, (Float.floatToRawIntBits(f3) << 32) | (Float.floatToRawIntBits(f4) & 4294967295L));
            if (Intrinsics.areEqual((TextInputSession) textInputSession.textInputService._currentInputSession.get(), textInputSession)) {
                textInputSession.platformTextInputService.notifyFocusedRect(m324Recttz77jQw);
            }
        }
    }
}
