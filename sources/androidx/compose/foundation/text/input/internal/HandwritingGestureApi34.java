package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.HandwritingGesture;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.SetSelectionCommand;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class HandwritingGestureApi34 {
    public static int fallbackOnLegacyTextField(HandwritingGesture handwritingGesture, Function1 function1) {
        String fallbackText = handwritingGesture.getFallbackText();
        if (fallbackText == null) {
            return 3;
        }
        ((RecordingInputConnection$performHandwritingGesture$1) function1).invoke(new CommitTextCommand(fallbackText, 1));
        return 5;
    }

    /* renamed from: performDeletionOnLegacyTextField-vJH6DeI, reason: not valid java name */
    public static void m170performDeletionOnLegacyTextFieldvJH6DeI(long j, AnnotatedString annotatedString, boolean z, Function1 function1) {
        if (z) {
            int i = TextRange.$r8$clinit;
            int i2 = (int) (j >> 32);
            int i3 = (int) (j & 4294967295L);
            int codePointBefore = i2 > 0 ? Character.codePointBefore(annotatedString, i2) : 10;
            int codePointAt = i3 < annotatedString.text.length() ? Character.codePointAt(annotatedString, i3) : 10;
            if (HandwritingGesture_androidKt.isWhitespaceExceptNewline(codePointBefore) && (HandwritingGesture_androidKt.isWhitespace(codePointAt) || HandwritingGesture_androidKt.isPunctuation(codePointAt))) {
                do {
                    i2 -= Character.charCount(codePointBefore);
                    if (i2 == 0) {
                        break;
                    } else {
                        codePointBefore = Character.codePointBefore(annotatedString, i2);
                    }
                } while (HandwritingGesture_androidKt.isWhitespaceExceptNewline(codePointBefore));
                j = TextRangeKt.TextRange(i2, i3);
            } else if (HandwritingGesture_androidKt.isWhitespaceExceptNewline(codePointAt) && (HandwritingGesture_androidKt.isWhitespace(codePointBefore) || HandwritingGesture_androidKt.isPunctuation(codePointBefore))) {
                do {
                    i3 += Character.charCount(codePointAt);
                    if (i3 == annotatedString.text.length()) {
                        break;
                    } else {
                        codePointAt = Character.codePointAt(annotatedString, i3);
                    }
                } while (HandwritingGesture_androidKt.isWhitespaceExceptNewline(codePointAt));
                j = TextRangeKt.TextRange(i2, i3);
            }
        }
        int i4 = (int) (4294967295L & j);
        ((RecordingInputConnection$performHandwritingGesture$1) function1).invoke(new HandwritingGesture_androidKt$compoundEditCommand$1(new EditCommand[]{new SetSelectionCommand(i4, i4), new DeleteSurroundingTextCommand(TextRange.m599getLengthimpl(j), 0)}));
    }

    /* renamed from: toTextGranularity-NUwxegE, reason: not valid java name */
    public static int m171toTextGranularityNUwxegE(int i) {
        return i != 1 ? 0 : 1;
    }
}
