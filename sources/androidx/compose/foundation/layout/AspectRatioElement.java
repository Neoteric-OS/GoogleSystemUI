package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.internal.InlineClassHelperKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AspectRatioElement extends ModifierNodeElement {
    public final float aspectRatio;

    public AspectRatioElement(float f) {
        this.aspectRatio = f;
        if (f > 0.0f) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("aspectRatio " + f + " must be > 0");
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        AspectRatioNode aspectRatioNode = new AspectRatioNode();
        aspectRatioNode.aspectRatio = this.aspectRatio;
        return aspectRatioNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        AspectRatioElement aspectRatioElement = obj instanceof AspectRatioElement ? (AspectRatioElement) obj : null;
        if (aspectRatioElement != null && this.aspectRatio == aspectRatioElement.aspectRatio) {
            ((AspectRatioElement) obj).getClass();
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + (Float.hashCode(this.aspectRatio) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((AspectRatioNode) node).aspectRatio = this.aspectRatio;
    }
}
