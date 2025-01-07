package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldValueKt {
    public static final AnnotatedString getSelectedText(TextFieldValue textFieldValue) {
        AnnotatedString annotatedString = textFieldValue.annotatedString;
        annotatedString.getClass();
        long j = textFieldValue.selection;
        return annotatedString.subSequence(TextRange.m601getMinimpl(j), TextRange.m600getMaximpl(j));
    }

    public static final AnnotatedString getTextAfterSelection(TextFieldValue textFieldValue, int i) {
        AnnotatedString annotatedString = textFieldValue.annotatedString;
        long j = textFieldValue.selection;
        return annotatedString.subSequence(TextRange.m600getMaximpl(j), Math.min(TextRange.m600getMaximpl(j) + i, textFieldValue.annotatedString.text.length()));
    }

    public static final AnnotatedString getTextBeforeSelection(TextFieldValue textFieldValue, int i) {
        AnnotatedString annotatedString = textFieldValue.annotatedString;
        long j = textFieldValue.selection;
        return annotatedString.subSequence(Math.max(0, TextRange.m601getMinimpl(j) - i), TextRange.m601getMinimpl(j));
    }
}
