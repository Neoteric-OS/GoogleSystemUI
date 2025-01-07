package androidx.compose.ui.graphics.colorspace;

import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class Rgb$$ExternalSyntheticLambda0 implements DoubleFunction {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Rgb f$0;

    public /* synthetic */ Rgb$$ExternalSyntheticLambda0(Rgb rgb, int i) {
        this.$r8$classId = i;
        this.f$0 = rgb;
    }

    @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
    public final double invoke(double d) {
        switch (this.$r8$classId) {
            case 0:
                return RangesKt.coerceIn(this.f$0.oetfOrig.invoke(d), r8.min, r8.max);
            default:
                return this.f$0.eotfOrig.invoke(RangesKt.coerceIn(d, r8.min, r8.max));
        }
    }
}
