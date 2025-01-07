package androidx.compose.material3;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class RippleNodeFactory implements IndicationNodeFactory {
    public final boolean bounded;
    public final long color;
    public final float radius;

    public RippleNodeFactory(float f, long j, boolean z) {
        this.bounded = z;
        this.radius = f;
        this.color = j;
    }

    @Override // androidx.compose.foundation.IndicationNodeFactory
    public final DelegatableNode create(InteractionSource interactionSource) {
        ColorProducer colorProducer = new ColorProducer() { // from class: androidx.compose.material3.RippleNodeFactory$create$colorProducer$1
            @Override // androidx.compose.ui.graphics.ColorProducer
            /* renamed from: invoke-0d7_KjU */
            public final long mo206invoke0d7_KjU() {
                return RippleNodeFactory.this.color;
            }
        };
        return new DelegatingThemeAwareRippleNode(interactionSource, this.bounded, this.radius, colorProducer);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RippleNodeFactory)) {
            return false;
        }
        RippleNodeFactory rippleNodeFactory = (RippleNodeFactory) obj;
        if (this.bounded == rippleNodeFactory.bounded && Dp.m668equalsimpl0(this.radius, rippleNodeFactory.radius) && Intrinsics.areEqual((Object) null, (Object) null)) {
            return Color.m363equalsimpl0(this.color, rippleNodeFactory.color);
        }
        return false;
    }

    @Override // androidx.compose.foundation.IndicationNodeFactory
    public final int hashCode() {
        int m = FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.bounded) * 31, this.radius, 961);
        int i = Color.$r8$clinit;
        return Long.hashCode(this.color) + m;
    }
}
