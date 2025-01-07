package androidx.compose.ui.graphics.drawscope;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Stroke extends DrawStyle {
    public final int cap;
    public final int join;
    public final float miter;
    public final float width;

    public Stroke(float f, float f2, int i, int i2, int i3) {
        f2 = (i3 & 2) != 0 ? 4.0f : f2;
        i = (i3 & 4) != 0 ? 0 : i;
        i2 = (i3 & 8) != 0 ? 0 : i2;
        this.width = f;
        this.miter = f2;
        this.cap = i;
        this.join = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Stroke)) {
            return false;
        }
        Stroke stroke = (Stroke) obj;
        return this.width == stroke.width && this.miter == stroke.miter && StrokeCap.m392equalsimpl0(this.cap, stroke.cap) && StrokeJoin.m394equalsimpl0(this.join, stroke.join) && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final int hashCode() {
        return KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.join, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.cap, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.width) * 31, this.miter, 31), 31), 31);
    }

    public final String toString() {
        return "Stroke(width=" + this.width + ", miter=" + this.miter + ", cap=" + ((Object) StrokeCap.m393toStringimpl(this.cap)) + ", join=" + ((Object) StrokeJoin.m395toStringimpl(this.join)) + ", pathEffect=null)";
    }
}
