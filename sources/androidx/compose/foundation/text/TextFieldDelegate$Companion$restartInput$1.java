package androidx.compose.foundation.text;

import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputSession;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TextFieldDelegate$Companion$restartInput$1 extends Lambda implements Function1 {
    final /* synthetic */ EditProcessor $editProcessor;
    final /* synthetic */ Function1 $onValueChange;
    final /* synthetic */ Ref$ObjectRef $session;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldDelegate$Companion$restartInput$1(EditProcessor editProcessor, Function1 function1, Ref$ObjectRef ref$ObjectRef) {
        super(1);
        this.$editProcessor = editProcessor;
        this.$onValueChange = function1;
        this.$session = ref$ObjectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        EditProcessor editProcessor = this.$editProcessor;
        Function1 function1 = this.$onValueChange;
        TextInputSession textInputSession = (TextInputSession) this.$session.element;
        TextFieldValue apply = editProcessor.apply((List) obj);
        if (textInputSession != null && Intrinsics.areEqual((TextInputSession) textInputSession.textInputService._currentInputSession.get(), textInputSession)) {
            textInputSession.platformTextInputService.updateState(null, apply);
        }
        function1.invoke(apply);
        return Unit.INSTANCE;
    }
}
