package androidx.compose.foundation;

import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.relocation.BringIntoViewParent;
import androidx.compose.foundation.relocation.BringIntoViewRequesterKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.focus.FocusStateImpl;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class FocusableNode$focusTargetNode$1 extends FunctionReferenceImpl implements Function2 {
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        boolean isFocused;
        FocusedBoundsObserverNode focusedBoundsObserver;
        FocusState focusState = (FocusState) obj;
        FocusState focusState2 = (FocusState) obj2;
        FocusableNode focusableNode = (FocusableNode) this.receiver;
        if (focusableNode.isAttached && (isFocused = ((FocusStateImpl) focusState2).isFocused()) != ((FocusStateImpl) focusState).isFocused()) {
            Function1 function1 = focusableNode.onFocusChange;
            if (function1 != null) {
                ((AbstractClickableNode$focusableNode$1) function1).invoke(Boolean.valueOf(isFocused));
            }
            if (isFocused) {
                BringIntoViewParent findBringIntoViewParent = BringIntoViewRequesterKt.findBringIntoViewParent(focusableNode);
                if (findBringIntoViewParent != null) {
                    BuildersKt.launch$default(focusableNode.getCoroutineScope(), null, null, new FocusableNode$onFocusStateChange$1(focusableNode, findBringIntoViewParent, DelegatableNodeKt.requireLayoutCoordinates(focusableNode), null), 3);
                }
                Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                ObserverModifierNodeKt.observeReads(focusableNode, new FocusableNode$retrievePinnableContainer$1(ref$ObjectRef, focusableNode));
                PinnableContainer pinnableContainer = (PinnableContainer) ref$ObjectRef.element;
                focusableNode.pinnedHandle = pinnableContainer != null ? pinnableContainer.pin() : null;
                NodeCoordinator nodeCoordinator = focusableNode.globalLayoutCoordinates;
                if (nodeCoordinator != null && nodeCoordinator.getTail().isAttached && (focusedBoundsObserver = focusableNode.getFocusedBoundsObserver()) != null) {
                    focusedBoundsObserver.onFocusBoundsChanged(focusableNode.globalLayoutCoordinates);
                }
            } else {
                PinnableContainer.PinnedHandle pinnedHandle = focusableNode.pinnedHandle;
                if (pinnedHandle != null) {
                    pinnedHandle.release();
                }
                focusableNode.pinnedHandle = null;
                FocusedBoundsObserverNode focusedBoundsObserver2 = focusableNode.getFocusedBoundsObserver();
                if (focusedBoundsObserver2 != null) {
                    focusedBoundsObserver2.onFocusBoundsChanged(null);
                }
            }
            SemanticsModifierNodeKt.invalidateSemantics(focusableNode);
            MutableInteractionSource mutableInteractionSource = focusableNode.interactionSource;
            if (mutableInteractionSource != null) {
                if (isFocused) {
                    FocusInteraction$Focus focusInteraction$Focus = focusableNode.focusedInteraction;
                    if (focusInteraction$Focus != null) {
                        focusableNode.emitWithFallback(mutableInteractionSource, new FocusInteraction$Unfocus(focusInteraction$Focus));
                        focusableNode.focusedInteraction = null;
                    }
                    FocusInteraction$Focus focusInteraction$Focus2 = new FocusInteraction$Focus();
                    focusableNode.emitWithFallback(mutableInteractionSource, focusInteraction$Focus2);
                    focusableNode.focusedInteraction = focusInteraction$Focus2;
                } else {
                    FocusInteraction$Focus focusInteraction$Focus3 = focusableNode.focusedInteraction;
                    if (focusInteraction$Focus3 != null) {
                        focusableNode.emitWithFallback(mutableInteractionSource, new FocusInteraction$Unfocus(focusInteraction$Focus3));
                        focusableNode.focusedInteraction = null;
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
