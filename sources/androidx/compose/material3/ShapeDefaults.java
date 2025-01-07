package androidx.compose.material3;

import androidx.compose.foundation.shape.CornerSize;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.tokens.ShapeTokens;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ShapeDefaults {
    public static final RoundedCornerShape ExtraSmall = ShapeTokens.CornerExtraSmall;
    public static final RoundedCornerShape Small = ShapeTokens.CornerSmall;
    public static final RoundedCornerShape Medium = ShapeTokens.CornerMedium;
    public static final RoundedCornerShape Large = ShapeTokens.CornerLarge;
    public static final RoundedCornerShape LargeIncreased = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(20);
    public static final RoundedCornerShape ExtraLarge = ShapeTokens.CornerExtraLarge;
    public static final RoundedCornerShape ExtraLargeIncreased = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(32);
    public static final RoundedCornerShape ExtraExtraLarge = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(48);
    public static final CornerSize CornerNone = CornerSizeKt.m147CornerSize0680j_4(0);

    static {
        CornerSizeKt.CornerSize(100);
    }
}
