package androidx.compose.foundation.text.selection;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$BooleanRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SelectionLayoutKt$isCollapsed$1 extends Lambda implements Function1 {
    final /* synthetic */ Ref$BooleanRef $allTextsEmpty;

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        if (((SelectableInfo) obj).textLayoutResult.layoutInput.text.text.length() > 0) {
            this.$allTextsEmpty.element = false;
        }
        return Unit.INSTANCE;
    }
}
