package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FocusRequester {
    public final MutableVector focusRequesterNodes = new MutableVector(new FocusRequesterModifierNode[16]);
    public static final FocusRequester Default = new FocusRequester();
    public static final FocusRequester Cancel = new FocusRequester();

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0037, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean findFocusTargetNode$ui_release(kotlin.jvm.functions.Function1 r14) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusRequester.findFocusTargetNode$ui_release(kotlin.jvm.functions.Function1):boolean");
    }

    public final void focus$ui_release() {
        findFocusTargetNode$ui_release(FocusRequester$focus$1.INSTANCE);
    }
}
