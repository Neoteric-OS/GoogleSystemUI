package androidx.compose.foundation.relocation;

import androidx.compose.foundation.gestures.ContentInViewNode;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.unit.IntSize;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BringIntoViewResponderNode extends Modifier.Node implements BringIntoViewParent, LayoutAwareModifierNode, TraversableNode {
    public static final TraverseKey TraverseKey = new TraverseKey();
    public boolean hasBeenPlaced;
    public ContentInViewNode responder;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TraverseKey {
    }

    public static final Rect access$bringChildIntoView$localRect(BringIntoViewResponderNode bringIntoViewResponderNode, LayoutCoordinates layoutCoordinates, Function0 function0) {
        Rect rect;
        if (!bringIntoViewResponderNode.isAttached || !bringIntoViewResponderNode.hasBeenPlaced) {
            return null;
        }
        NodeCoordinator requireLayoutCoordinates = DelegatableNodeKt.requireLayoutCoordinates(bringIntoViewResponderNode);
        if (!layoutCoordinates.isAttached()) {
            layoutCoordinates = null;
        }
        if (layoutCoordinates == null || (rect = (Rect) function0.invoke()) == null) {
            return null;
        }
        return rect.m323translatek4lQ0M(requireLayoutCoordinates.localBoundingBoxOf(layoutCoordinates, false).m322getTopLeftF1C5BW0());
    }

    @Override // androidx.compose.foundation.relocation.BringIntoViewParent
    public final Object bringChildIntoView(final LayoutCoordinates layoutCoordinates, final Function0 function0, ContinuationImpl continuationImpl) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(continuationImpl, new BringIntoViewResponderNode$bringChildIntoView$2(this, layoutCoordinates, function0, new Function0() { // from class: androidx.compose.foundation.relocation.BringIntoViewResponderNode$bringChildIntoView$parentRect$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Rect access$bringChildIntoView$localRect = BringIntoViewResponderNode.access$bringChildIntoView$localRect(BringIntoViewResponderNode.this, layoutCoordinates, function0);
                if (access$bringChildIntoView$localRect == null) {
                    return null;
                }
                ContentInViewNode contentInViewNode = BringIntoViewResponderNode.this.responder;
                if (IntSize.m683equalsimpl0(contentInViewNode.viewportSize, 0L)) {
                    InlineClassHelperKt.throwIllegalStateException("Expected BringIntoViewRequester to not be used before parents are placed.");
                }
                return access$bringChildIntoView$localRect.m323translatek4lQ0M(contentInViewNode.m56relocationOffsetBMxPBkI(access$bringChildIntoView$localRect, contentInViewNode.viewportSize) ^ (-9223372034707292160L));
            }
        }, null));
        return coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED ? coroutineScope : Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public final Object getTraverseKey() {
        return TraverseKey;
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public final void onPlaced(LayoutCoordinates layoutCoordinates) {
        this.hasBeenPlaced = true;
    }
}
