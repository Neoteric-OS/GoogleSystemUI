package androidx.compose.foundation;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.Role;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CombinedClickableElement extends ModifierNodeElement {
    public final boolean enabled;
    public final boolean hapticFeedbackEnabled;
    public final IndicationNodeFactory indicationNodeFactory;
    public final MutableInteractionSource interactionSource;
    public final Function0 onClick;
    public final String onClickLabel;
    public final Function0 onDoubleClick;
    public final Function0 onLongClick;
    public final String onLongClickLabel;
    public final Role role;

    public CombinedClickableElement(IndicationNodeFactory indicationNodeFactory, MutableInteractionSource mutableInteractionSource, Role role, String str, String str2, Function0 function0, Function0 function02, Function0 function03, boolean z, boolean z2) {
        this.interactionSource = mutableInteractionSource;
        this.indicationNodeFactory = indicationNodeFactory;
        this.enabled = z;
        this.onClickLabel = str;
        this.role = role;
        this.onClick = function0;
        this.onLongClickLabel = str2;
        this.onLongClick = function02;
        this.onDoubleClick = function03;
        this.hapticFeedbackEnabled = z2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        IndicationNodeFactory indicationNodeFactory = this.indicationNodeFactory;
        Role role = this.role;
        Function0 function0 = this.onClick;
        String str = this.onLongClickLabel;
        Function0 function02 = this.onLongClick;
        Function0 function03 = this.onDoubleClick;
        boolean z = this.hapticFeedbackEnabled;
        return new CombinedClickableNodeImpl(indicationNodeFactory, this.interactionSource, role, str, this.onClickLabel, function0, function02, function03, z, this.enabled);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CombinedClickableElement.class != obj.getClass()) {
            return false;
        }
        CombinedClickableElement combinedClickableElement = (CombinedClickableElement) obj;
        return Intrinsics.areEqual(this.interactionSource, combinedClickableElement.interactionSource) && Intrinsics.areEqual(this.indicationNodeFactory, combinedClickableElement.indicationNodeFactory) && this.enabled == combinedClickableElement.enabled && Intrinsics.areEqual(this.onClickLabel, combinedClickableElement.onClickLabel) && Intrinsics.areEqual(this.role, combinedClickableElement.role) && this.onClick == combinedClickableElement.onClick && Intrinsics.areEqual(this.onLongClickLabel, combinedClickableElement.onLongClickLabel) && this.onLongClick == combinedClickableElement.onLongClick && this.onDoubleClick == combinedClickableElement.onDoubleClick && this.hapticFeedbackEnabled == combinedClickableElement.hapticFeedbackEnabled;
    }

    public final int hashCode() {
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        int hashCode = (mutableInteractionSource != null ? mutableInteractionSource.hashCode() : 0) * 31;
        IndicationNodeFactory indicationNodeFactory = this.indicationNodeFactory;
        int m = TransitionData$$ExternalSyntheticOutline0.m((hashCode + (indicationNodeFactory != null ? indicationNodeFactory.hashCode() : 0)) * 31, 31, this.enabled);
        String str = this.onClickLabel;
        int hashCode2 = (m + (str != null ? str.hashCode() : 0)) * 31;
        Role role = this.role;
        int hashCode3 = (this.onClick.hashCode() + ((hashCode2 + (role != null ? Integer.hashCode(role.value) : 0)) * 31)) * 31;
        String str2 = this.onLongClickLabel;
        int hashCode4 = (hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Function0 function0 = this.onLongClick;
        int hashCode5 = (hashCode4 + (function0 != null ? function0.hashCode() : 0)) * 31;
        Function0 function02 = this.onDoubleClick;
        return Boolean.hashCode(this.hapticFeedbackEnabled) + ((hashCode5 + (function02 != null ? function02.hashCode() : 0)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        boolean z;
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode;
        CombinedClickableNodeImpl combinedClickableNodeImpl = (CombinedClickableNodeImpl) node;
        combinedClickableNodeImpl.hapticFeedbackEnabled = this.hapticFeedbackEnabled;
        String str = combinedClickableNodeImpl.onLongClickLabel;
        String str2 = this.onLongClickLabel;
        if (!Intrinsics.areEqual(str, str2)) {
            combinedClickableNodeImpl.onLongClickLabel = str2;
            SemanticsModifierNodeKt.invalidateSemantics(combinedClickableNodeImpl);
        }
        boolean z2 = combinedClickableNodeImpl.onLongClick == null;
        Function0 function0 = this.onLongClick;
        if (z2 != (function0 == null)) {
            combinedClickableNodeImpl.disposeInteractions();
            SemanticsModifierNodeKt.invalidateSemantics(combinedClickableNodeImpl);
            z = true;
        } else {
            z = false;
        }
        combinedClickableNodeImpl.onLongClick = function0;
        boolean z3 = combinedClickableNodeImpl.onDoubleClick == null;
        Function0 function02 = this.onDoubleClick;
        if (z3 != (function02 == null)) {
            z = true;
        }
        combinedClickableNodeImpl.onDoubleClick = function02;
        boolean z4 = combinedClickableNodeImpl.enabled;
        boolean z5 = this.enabled;
        boolean z6 = z4 != z5 ? true : z;
        combinedClickableNodeImpl.m17updateCommonQzZPfjk(this.interactionSource, this.indicationNodeFactory, z5, this.onClickLabel, this.role, this.onClick);
        if (!z6 || (suspendingPointerInputModifierNode = combinedClickableNodeImpl.pointerInputNode) == null) {
            return;
        }
        ((SuspendingPointerInputModifierNodeImpl) suspendingPointerInputModifierNode).resetPointerInputHandler();
    }
}
