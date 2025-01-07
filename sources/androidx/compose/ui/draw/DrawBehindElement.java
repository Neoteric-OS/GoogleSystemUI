package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DrawBehindElement extends ModifierNodeElement {
    public final Function1 onDraw;

    public DrawBehindElement(Function1 function1) {
        this.onDraw = function1;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        DrawBackgroundModifier drawBackgroundModifier = new DrawBackgroundModifier();
        drawBackgroundModifier.onDraw = this.onDraw;
        return drawBackgroundModifier;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DrawBehindElement) && Intrinsics.areEqual(this.onDraw, ((DrawBehindElement) obj).onDraw);
    }

    public final int hashCode() {
        return this.onDraw.hashCode();
    }

    public final String toString() {
        return "DrawBehindElement(onDraw=" + this.onDraw + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((DrawBackgroundModifier) node).onDraw = this.onDraw;
    }
}
