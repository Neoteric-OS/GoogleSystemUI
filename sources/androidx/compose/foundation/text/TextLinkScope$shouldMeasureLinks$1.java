package androidx.compose.foundation.text;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TextLinkScope$shouldMeasureLinks$1 extends Lambda implements Function0 {
    final /* synthetic */ TextLinkScope this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextLinkScope$shouldMeasureLinks$1(TextLinkScope textLinkScope) {
        super(0);
        this.this$0 = textLinkScope;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        TextLayoutInput textLayoutInput;
        TextLinkScope textLinkScope = this.this$0;
        AnnotatedString annotatedString = textLinkScope.text;
        TextLayoutResult textLayoutResult = (TextLayoutResult) ((SnapshotMutableStateImpl) textLinkScope.textLayoutResult$delegate).getValue();
        return Boolean.valueOf(Intrinsics.areEqual(annotatedString, (textLayoutResult == null || (textLayoutInput = textLayoutResult.layoutInput) == null) ? null : textLayoutInput.text));
    }
}
