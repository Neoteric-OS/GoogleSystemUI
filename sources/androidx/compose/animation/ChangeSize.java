package androidx.compose.animation;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.BiasAlignment;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChangeSize {
    public final BiasAlignment alignment;
    public final FiniteAnimationSpec animationSpec;
    public final boolean clip;
    public final Function1 size;

    public ChangeSize(BiasAlignment biasAlignment, Function1 function1, FiniteAnimationSpec finiteAnimationSpec, boolean z) {
        this.alignment = biasAlignment;
        this.size = function1;
        this.animationSpec = finiteAnimationSpec;
        this.clip = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChangeSize)) {
            return false;
        }
        ChangeSize changeSize = (ChangeSize) obj;
        return this.alignment.equals(changeSize.alignment) && Intrinsics.areEqual(this.size, changeSize.size) && Intrinsics.areEqual(this.animationSpec, changeSize.animationSpec) && this.clip == changeSize.clip;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.clip) + ((this.animationSpec.hashCode() + ChangeSize$$ExternalSyntheticOutline0.m(this.size, this.alignment.hashCode() * 31, 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ChangeSize(alignment=");
        sb.append(this.alignment);
        sb.append(", size=");
        sb.append(this.size);
        sb.append(", animationSpec=");
        sb.append(this.animationSpec);
        sb.append(", clip=");
        return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.clip, ')');
    }
}
