package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LayoutElement extends ModifierNodeElement {
    public final Function3 measure;

    public LayoutElement(Function3 function3) {
        this.measure = function3;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        LayoutModifierImpl layoutModifierImpl = new LayoutModifierImpl();
        layoutModifierImpl.measureBlock = this.measure;
        return layoutModifierImpl;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LayoutElement) && Intrinsics.areEqual(this.measure, ((LayoutElement) obj).measure);
    }

    public final int hashCode() {
        return this.measure.hashCode();
    }

    public final String toString() {
        return "LayoutElement(measure=" + this.measure + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((LayoutModifierImpl) node).measureBlock = this.measure;
    }
}
