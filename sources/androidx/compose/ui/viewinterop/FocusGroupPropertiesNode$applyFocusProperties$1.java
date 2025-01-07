package androidx.compose.ui.viewinterop;

import android.view.View;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class FocusGroupPropertiesNode$applyFocusProperties$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object obj) {
        return m701invoke3ESFkO8(((FocusDirection) obj).value);
    }

    /* renamed from: invoke-3ESFkO8, reason: not valid java name */
    public final FocusRequester m701invoke3ESFkO8(int i) {
        FocusGroupPropertiesNode focusGroupPropertiesNode = (FocusGroupPropertiesNode) this.receiver;
        focusGroupPropertiesNode.getClass();
        View access$getView = FocusGroupNode_androidKt.access$getView(focusGroupPropertiesNode);
        if (access$getView.isFocused() || access$getView.hasFocus()) {
            return FocusRequester.Default;
        }
        return FocusInteropUtils_androidKt.requestInteropFocus(access$getView, FocusInteropUtils_androidKt.m286toAndroidFocusDirection3ESFkO8(i), FocusGroupNode_androidKt.access$getCurrentlyFocusedRect(((AndroidComposeView) DelegatableNodeKt.requireOwner(focusGroupPropertiesNode)).focusOwner, (View) DelegatableNodeKt.requireOwner(focusGroupPropertiesNode), access$getView)) ? FocusRequester.Default : FocusRequester.Cancel;
    }
}
