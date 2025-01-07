package androidx.compose.ui.focus;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FocusRequester$focus$1 extends Lambda implements Function1 {
    public static final FocusRequester$focus$1 INSTANCE = new FocusRequester$focus$1();

    public FocusRequester$focus$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        FocusTargetNode focusTargetNode = (FocusTargetNode) obj;
        focusTargetNode.getClass();
        Boolean m296requestFocusMxy_nc0 = FocusTransactionsKt.m296requestFocusMxy_nc0(focusTargetNode, 7);
        return Boolean.valueOf(m296requestFocusMxy_nc0 != null ? m296requestFocusMxy_nc0.booleanValue() : false);
    }
}
