package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNode$Companion$TraverseDescendantsAction;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.AndroidComposeView$pointerIconService$1;
import androidx.compose.ui.platform.CompositionLocalsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerHoverIconModifierNode extends Modifier.Node implements TraversableNode, PointerInputModifierNode, CompositionLocalConsumerModifierNode {
    public boolean cursorInBoundsOfNode;
    public AndroidPointerIconType icon;

    public final void displayIcon() {
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        TraversableNodeKt.traverseAncestors(this, new Function1() { // from class: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode$findOverridingAncestorNode$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((PointerHoverIconModifierNode) obj).getClass();
                return Boolean.TRUE;
            }
        });
        PointerHoverIconModifierNode pointerHoverIconModifierNode = (PointerHoverIconModifierNode) ref$ObjectRef.element;
        AndroidPointerIconType androidPointerIconType = pointerHoverIconModifierNode != null ? pointerHoverIconModifierNode.icon : this.icon;
        PointerIconService pointerIconService = (PointerIconService) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.LocalPointerIconService);
        if (pointerIconService != null) {
            ((AndroidComposeView$pointerIconService$1) pointerIconService).setIcon(androidPointerIconType);
        }
    }

    public final void displayIconIfDescendantsDoNotHavePriority() {
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        ref$BooleanRef.element = true;
        TraversableNodeKt.traverseDescendants(this, new Function1() { // from class: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode$displayIconIfDescendantsDoNotHavePriority$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                if (!((PointerHoverIconModifierNode) obj).cursorInBoundsOfNode) {
                    return TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal;
                }
                Ref$BooleanRef.this.element = false;
                return TraversableNode$Companion$TraverseDescendantsAction.CancelTraversal;
            }
        });
        if (ref$BooleanRef.element) {
            displayIcon();
        }
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public final /* bridge */ /* synthetic */ Object getTraverseKey() {
        return "androidx.compose.ui.input.pointer.PointerHoverIcon";
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        onExit();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        onExit();
    }

    public final void onExit() {
        Unit unit;
        PointerIconService pointerIconService;
        if (this.cursorInBoundsOfNode) {
            this.cursorInBoundsOfNode = false;
            if (this.isAttached) {
                final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                TraversableNodeKt.traverseAncestors(this, new Function1() { // from class: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode$displayIconFromAncestorNodeWithCursorInBoundsOrDefaultIcon$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        PointerHoverIconModifierNode pointerHoverIconModifierNode = (PointerHoverIconModifierNode) obj;
                        Ref$ObjectRef ref$ObjectRef2 = Ref$ObjectRef.this;
                        Object obj2 = ref$ObjectRef2.element;
                        if (obj2 == null && pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                            ref$ObjectRef2.element = pointerHoverIconModifierNode;
                        } else if (obj2 != null) {
                            pointerHoverIconModifierNode.getClass();
                        }
                        return Boolean.TRUE;
                    }
                });
                PointerHoverIconModifierNode pointerHoverIconModifierNode = (PointerHoverIconModifierNode) ref$ObjectRef.element;
                if (pointerHoverIconModifierNode != null) {
                    pointerHoverIconModifierNode.displayIcon();
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit != null || (pointerIconService = (PointerIconService) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.LocalPointerIconService)) == null) {
                    return;
                }
                ((AndroidComposeView$pointerIconService$1) pointerIconService).setIcon(null);
            }
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public final void mo15onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        if (pointerEventPass == PointerEventPass.Main) {
            if (PointerEventType.m461equalsimpl0(pointerEvent.type, 4)) {
                this.cursorInBoundsOfNode = true;
                displayIconIfDescendantsDoNotHavePriority();
            } else if (PointerEventType.m461equalsimpl0(pointerEvent.type, 5)) {
                onExit();
            }
        }
    }
}
