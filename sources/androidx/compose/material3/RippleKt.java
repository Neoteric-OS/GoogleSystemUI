package androidx.compose.material3;

import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RippleKt {
    public static final RippleNodeFactory DefaultBoundedRipple;
    public static final RippleNodeFactory DefaultUnboundedRipple;
    public static final DynamicProvidableCompositionLocal LocalRippleConfiguration = new DynamicProvidableCompositionLocal(new Function0() { // from class: androidx.compose.material3.RippleKt$LocalRippleConfiguration$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new RippleConfiguration();
        }
    });

    static {
        long j = Color.Unspecified;
        DefaultBoundedRipple = new RippleNodeFactory(Float.NaN, j, true);
        DefaultUnboundedRipple = new RippleNodeFactory(Float.NaN, j, false);
    }

    /* renamed from: ripple-H2RKhps$default, reason: not valid java name */
    public static IndicationNodeFactory m221rippleH2RKhps$default(float f, boolean z, int i) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            f = Float.NaN;
        }
        long j = Color.Unspecified;
        return (Dp.m668equalsimpl0(f, Float.NaN) && Color.m363equalsimpl0(j, j)) ? z ? DefaultBoundedRipple : DefaultUnboundedRipple : new RippleNodeFactory(f, j, z);
    }
}
