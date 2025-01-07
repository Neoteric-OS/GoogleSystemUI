package androidx.compose.foundation.text.handwriting;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class StylusHandwritingKt {
    public static final float HandwritingBoundsHorizontalOffset;
    public static final float HandwritingBoundsVerticalOffset;

    static {
        float f = 0;
        HandwritingBoundsVerticalOffset = f;
        HandwritingBoundsHorizontalOffset = f;
    }

    public static final Modifier stylusHandwriting(Function0 function0, boolean z) {
        return z ? PaddingKt.m99paddingVpY3zN4(new StylusHandwritingElementWithNegativePadding(function0), HandwritingBoundsHorizontalOffset, HandwritingBoundsVerticalOffset) : Modifier.Companion.$$INSTANCE;
    }
}
