package androidx.compose.foundation.relocation;

import android.view.View;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlin.Unit;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BringIntoViewRequesterKt {
    public static final BringIntoViewRequester BringIntoViewRequester() {
        return new BringIntoViewRequesterImpl();
    }

    public static final Modifier bringIntoViewRequester(Modifier modifier, BringIntoViewRequester bringIntoViewRequester) {
        return modifier.then(new BringIntoViewRequesterElement(bringIntoViewRequester));
    }

    public static final BringIntoViewParent findBringIntoViewParent(final DelegatableNode delegatableNode) {
        if (!((Modifier.Node) delegatableNode).node.isAttached) {
            return null;
        }
        BringIntoViewParent bringIntoViewParent = (BringIntoViewParent) TraversableNodeKt.findNearestAncestor(delegatableNode, BringIntoViewResponderNode.TraverseKey);
        if (bringIntoViewParent == null) {
            bringIntoViewParent = new BringIntoViewParent() { // from class: androidx.compose.foundation.relocation.BringIntoViewResponder_androidKt$defaultBringIntoViewParent$1
                @Override // androidx.compose.foundation.relocation.BringIntoViewParent
                public final Object bringChildIntoView(LayoutCoordinates layoutCoordinates, Function0 function0, ContinuationImpl continuationImpl) {
                    View requireView = DelegatableNode_androidKt.requireView(DelegatableNode.this);
                    long mo484localToRootMKHz9U = layoutCoordinates.mo484localToRootMKHz9U(0L);
                    Rect rect = (Rect) function0.invoke();
                    Rect m323translatek4lQ0M = rect != null ? rect.m323translatek4lQ0M(mo484localToRootMKHz9U) : null;
                    if (m323translatek4lQ0M != null) {
                        requireView.requestRectangleOnScreen(new android.graphics.Rect((int) m323translatek4lQ0M.left, (int) m323translatek4lQ0M.top, (int) m323translatek4lQ0M.right, (int) m323translatek4lQ0M.bottom), false);
                    }
                    return Unit.INSTANCE;
                }
            };
        }
        return bringIntoViewParent;
    }
}
