package androidx.compose.ui.platform;

import androidx.compose.ui.input.InputMode;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AndroidComposeView$_inputModeManager$1 extends Lambda implements Function1 {
    final /* synthetic */ AndroidComposeView this$0;

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int i = ((InputMode) obj).value;
        boolean z = true;
        if (i == 1) {
            z = this.this$0.isInTouchMode();
        } else if (i != 2) {
            z = false;
        } else if (this.this$0.isInTouchMode()) {
            z = this.this$0.requestFocusFromTouch();
        }
        return Boolean.valueOf(z);
    }
}
