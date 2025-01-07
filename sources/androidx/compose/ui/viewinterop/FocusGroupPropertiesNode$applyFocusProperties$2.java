package androidx.compose.ui.viewinterop;

import android.graphics.Rect;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class FocusGroupPropertiesNode$applyFocusProperties$2 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object obj) {
        return m702invoke3ESFkO8(((FocusDirection) obj).value);
    }

    /* renamed from: invoke-3ESFkO8, reason: not valid java name */
    public final FocusRequester m702invoke3ESFkO8(int i) {
        FocusGroupPropertiesNode focusGroupPropertiesNode = (FocusGroupPropertiesNode) this.receiver;
        focusGroupPropertiesNode.getClass();
        View access$getView = FocusGroupNode_androidKt.access$getView(focusGroupPropertiesNode);
        if (!access$getView.hasFocus()) {
            return FocusRequester.Default;
        }
        FocusOwnerImpl focusOwnerImpl = ((AndroidComposeView) DelegatableNodeKt.requireOwner(focusGroupPropertiesNode)).focusOwner;
        View view = (View) DelegatableNodeKt.requireOwner(focusGroupPropertiesNode);
        if (!(access$getView instanceof ViewGroup)) {
            if (view.requestFocus()) {
                return FocusRequester.Default;
            }
            throw new IllegalStateException("host view did not take focus");
        }
        Rect access$getCurrentlyFocusedRect = FocusGroupNode_androidKt.access$getCurrentlyFocusedRect(focusOwnerImpl, view, access$getView);
        Integer m286toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m286toAndroidFocusDirection3ESFkO8(i);
        int intValue = m286toAndroidFocusDirection3ESFkO8 != null ? m286toAndroidFocusDirection3ESFkO8.intValue() : 130;
        FocusFinder focusFinder = FocusFinder.getInstance();
        View view2 = focusGroupPropertiesNode.focusedChild;
        View findNextFocus = view2 != null ? focusFinder.findNextFocus((ViewGroup) view, view2, intValue) : focusFinder.findNextFocusFromRect((ViewGroup) view, access$getCurrentlyFocusedRect, intValue);
        if (findNextFocus != null && FocusGroupNode_androidKt.access$containsDescendant(access$getView, findNextFocus)) {
            findNextFocus.requestFocus(intValue, access$getCurrentlyFocusedRect);
            return FocusRequester.Cancel;
        }
        if (view.requestFocus()) {
            return FocusRequester.Default;
        }
        throw new IllegalStateException("host view did not take focus");
    }
}
