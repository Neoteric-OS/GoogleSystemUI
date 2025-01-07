package androidx.compose.animation;

import androidx.compose.animation.core.TweenSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Slide {
    public final TweenSpec animationSpec;
    public final Lambda slideOffset;

    /* JADX WARN: Multi-variable type inference failed */
    public Slide(TweenSpec tweenSpec, Function1 function1) {
        this.slideOffset = (Lambda) function1;
        this.animationSpec = tweenSpec;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Slide)) {
            return false;
        }
        Slide slide = (Slide) obj;
        return this.slideOffset.equals(slide.slideOffset) && this.animationSpec.equals(slide.animationSpec);
    }

    public final int hashCode() {
        return this.animationSpec.hashCode() + (this.slideOffset.hashCode() * 31);
    }

    public final String toString() {
        return "Slide(slideOffset=" + this.slideOffset + ", animationSpec=" + this.animationSpec + ')';
    }
}
