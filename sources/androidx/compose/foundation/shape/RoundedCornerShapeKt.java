package androidx.compose.foundation.shape;

import com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelper$Dimensions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RoundedCornerShapeKt {
    public static final RoundedCornerShape CircleShape;

    static {
        CornerSize CornerSize = CornerSizeKt.CornerSize(50);
        CircleShape = new RoundedCornerShape(CornerSize, CornerSize, CornerSize, CornerSize);
    }

    public static final RoundedCornerShape RoundedCornerShape(float f, float f2, float f3, float f4) {
        return new RoundedCornerShape(new PxCornerSize(f), new PxCornerSize(f2), new PxCornerSize(f3), new PxCornerSize(f4));
    }

    /* renamed from: RoundedCornerShape-0680j_4, reason: not valid java name */
    public static final RoundedCornerShape m148RoundedCornerShape0680j_4(float f) {
        DpCornerSize dpCornerSize = new DpCornerSize(f);
        return new RoundedCornerShape(dpCornerSize, dpCornerSize, dpCornerSize, dpCornerSize);
    }

    /* renamed from: RoundedCornerShape-a9UjIt4, reason: not valid java name */
    public static final RoundedCornerShape m149RoundedCornerShapea9UjIt4(float f, float f2, float f3, float f4) {
        return new RoundedCornerShape(new DpCornerSize(f), new DpCornerSize(f2), new DpCornerSize(f3), new DpCornerSize(f4));
    }

    /* renamed from: RoundedCornerShape-a9UjIt4$default, reason: not valid java name */
    public static RoundedCornerShape m150RoundedCornerShapea9UjIt4$default(int i, float f, float f2) {
        float f3 = ShortcutHelper$Dimensions.SinglePaneCategoryCornerRadius;
        if ((i & 1) != 0) {
            f = 0;
        }
        if ((i & 2) != 0) {
            f2 = 0;
        }
        float f4 = (i & 4) != 0 ? 0 : f3;
        if ((i & 8) != 0) {
            f3 = 0;
        }
        return m149RoundedCornerShapea9UjIt4(f, f2, f4, f3);
    }
}
