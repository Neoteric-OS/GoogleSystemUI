package androidx.compose.foundation.text;

import androidx.compose.ui.text.AnnotatedString;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TextAnnotatorScope {
    public final AnnotatedString initialText;
    public AnnotatedString styledText;

    public TextAnnotatorScope(AnnotatedString annotatedString) {
        this.initialText = annotatedString;
        this.styledText = annotatedString;
    }
}
