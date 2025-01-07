package androidx.compose.material3;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TextFieldColors$copy$11 extends Lambda implements Function0 {
    final /* synthetic */ TextFieldColors this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldColors$copy$11(TextFieldColors textFieldColors) {
        super(0);
        this.this$0 = textFieldColors;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.this$0.textSelectionColors;
    }
}
