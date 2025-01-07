package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface VisualTransformation {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = null;
        public static final VisualTransformation$Companion$$ExternalSyntheticLambda0 None = new VisualTransformation$Companion$$ExternalSyntheticLambda0();
    }

    TransformedText filter(AnnotatedString annotatedString);
}
