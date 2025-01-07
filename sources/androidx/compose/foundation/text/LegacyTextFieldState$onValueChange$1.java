package androidx.compose.foundation.text;

import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeOwner;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LegacyTextFieldState$onValueChange$1 extends Lambda implements Function1 {
    final /* synthetic */ LegacyTextFieldState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyTextFieldState$onValueChange$1(LegacyTextFieldState legacyTextFieldState) {
        super(1);
        this.this$0 = legacyTextFieldState;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TextFieldValue textFieldValue = (TextFieldValue) obj;
        String str = textFieldValue.annotatedString.text;
        AnnotatedString annotatedString = this.this$0.untransformedText;
        if (!Intrinsics.areEqual(str, annotatedString != null ? annotatedString.text : null)) {
            ((SnapshotMutableStateImpl) this.this$0.handleState$delegate).setValue(HandleState.None);
        }
        LegacyTextFieldState legacyTextFieldState = this.this$0;
        long j = TextRange.Zero;
        legacyTextFieldState.m158setSelectionPreviewHighlightRange5zctL8(j);
        this.this$0.m157setDeletionPreviewHighlightRange5zctL8(j);
        this.this$0.onValueChangeOriginal.invoke(textFieldValue);
        RecomposeScopeImpl recomposeScopeImpl = this.this$0.recomposeScope;
        RecomposeScopeOwner recomposeScopeOwner = recomposeScopeImpl.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.invalidate(recomposeScopeImpl, null);
        }
        return Unit.INSTANCE;
    }
}
