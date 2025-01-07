package androidx.compose.foundation;

import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTransactionsKt;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FocusableNode extends DelegatingNode implements SemanticsModifierNode, GlobalPositionAwareModifierNode, CompositionLocalConsumerModifierNode, ObserverModifierNode, TraversableNode {
    public static final TraverseKey TraverseKey = new TraverseKey();
    public final FocusTargetModifierNode focusTargetNode;
    public FocusInteraction$Focus focusedInteraction;
    public NodeCoordinator globalLayoutCoordinates;
    public MutableInteractionSource interactionSource;
    public final Function1 onFocusChange;
    public PinnableContainer.PinnedHandle pinnedHandle;
    public Function0 requestFocus;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TraverseKey {
    }

    public FocusableNode(MutableInteractionSource mutableInteractionSource, int i, Function1 function1) {
        this.interactionSource = mutableInteractionSource;
        this.onFocusChange = function1;
        FocusTargetNode focusTargetNode = new FocusTargetNode(i, new FocusableNode$focusTargetNode$1(2, this, FocusableNode.class, "onFocusStateChange", "onFocusStateChange(Landroidx/compose/ui/focus/FocusState;Landroidx/compose/ui/focus/FocusState;)V", 0));
        delegate(focusTargetNode);
        this.focusTargetNode = focusTargetNode;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        boolean isFocused = ((FocusTargetNode) this.focusTargetNode).getFocusState().isFocused();
        KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.Focused;
        KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[4];
        semanticsPropertyKey.setValue(semanticsPropertyReceiver, Boolean.valueOf(isFocused));
        if (this.requestFocus == null) {
            this.requestFocus = new Function0() { // from class: androidx.compose.foundation.FocusableNode$applySemantics$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    FocusTargetNode focusTargetNode = (FocusTargetNode) FocusableNode.this.focusTargetNode;
                    focusTargetNode.getClass();
                    Boolean m296requestFocusMxy_nc0 = FocusTransactionsKt.m296requestFocusMxy_nc0(focusTargetNode, 7);
                    return Boolean.valueOf(m296requestFocusMxy_nc0 != null ? m296requestFocusMxy_nc0.booleanValue() : false);
                }
            };
        }
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) semanticsPropertyReceiver;
        semanticsConfiguration.set(SemanticsActions.RequestFocus, new AccessibilityAction(null, this.requestFocus));
    }

    public final void emitWithFallback(final MutableInteractionSource mutableInteractionSource, final Interaction interaction) {
        if (!this.isAttached) {
            mutableInteractionSource.tryEmit(interaction);
            return;
        }
        Job job = (Job) ((ContextScope) getCoroutineScope()).coroutineContext.get(Job.Key.$$INSTANCE);
        BuildersKt.launch$default(getCoroutineScope(), null, null, new FocusableNode$emitWithFallback$1(mutableInteractionSource, interaction, job != null ? job.invokeOnCompletion(new Function1() { // from class: androidx.compose.foundation.FocusableNode$emitWithFallback$handler$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                MutableInteractionSource.this.tryEmit(interaction);
                return Unit.INSTANCE;
            }
        }) : null, null), 3);
    }

    public final FocusedBoundsObserverNode getFocusedBoundsObserver() {
        if (!this.isAttached) {
            return null;
        }
        TraversableNode findNearestAncestor = TraversableNodeKt.findNearestAncestor(this, FocusedBoundsObserverNode.TraverseKey);
        if (findNearestAncestor instanceof FocusedBoundsObserverNode) {
            return (FocusedBoundsObserverNode) findNearestAncestor;
        }
        return null;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public final Object getTraverseKey() {
        return TraverseKey;
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public final void onGloballyPositioned(NodeCoordinator nodeCoordinator) {
        FocusedBoundsObserverNode focusedBoundsObserver;
        this.globalLayoutCoordinates = nodeCoordinator;
        if (((FocusTargetNode) this.focusTargetNode).getFocusState().isFocused()) {
            if (!nodeCoordinator.getTail().isAttached) {
                FocusedBoundsObserverNode focusedBoundsObserver2 = getFocusedBoundsObserver();
                if (focusedBoundsObserver2 != null) {
                    focusedBoundsObserver2.onFocusBoundsChanged(null);
                    return;
                }
                return;
            }
            NodeCoordinator nodeCoordinator2 = this.globalLayoutCoordinates;
            if (nodeCoordinator2 == null || !nodeCoordinator2.getTail().isAttached || (focusedBoundsObserver = getFocusedBoundsObserver()) == null) {
                return;
            }
            focusedBoundsObserver.onFocusBoundsChanged(this.globalLayoutCoordinates);
        }
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public final void onObservedReadsChanged() {
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ObserverModifierNodeKt.observeReads(this, new FocusableNode$retrievePinnableContainer$1(ref$ObjectRef, this));
        PinnableContainer pinnableContainer = (PinnableContainer) ref$ObjectRef.element;
        if (((FocusTargetNode) this.focusTargetNode).getFocusState().isFocused()) {
            PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
            if (pinnedHandle != null) {
                pinnedHandle.release();
            }
            this.pinnedHandle = pinnableContainer != null ? pinnableContainer.pin() : null;
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onReset() {
        PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
        if (pinnedHandle != null) {
            pinnedHandle.release();
        }
        this.pinnedHandle = null;
    }

    public final void update(MutableInteractionSource mutableInteractionSource) {
        FocusInteraction$Focus focusInteraction$Focus;
        if (Intrinsics.areEqual(this.interactionSource, mutableInteractionSource)) {
            return;
        }
        MutableInteractionSource mutableInteractionSource2 = this.interactionSource;
        if (mutableInteractionSource2 != null && (focusInteraction$Focus = this.focusedInteraction) != null) {
            mutableInteractionSource2.tryEmit(new FocusInteraction$Unfocus(focusInteraction$Focus));
        }
        this.focusedInteraction = null;
        this.interactionSource = mutableInteractionSource;
    }
}
