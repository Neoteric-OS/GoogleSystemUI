package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LayoutUtilsKt {
    /* renamed from: finalConstraints-tfFHcEY, reason: not valid java name */
    public static final long m177finalConstraintstfFHcEY(long j, boolean z, int i, float f) {
        int m655getMaxWidthimpl = ((z || TextOverflow.m647equalsimpl0(i, 2) || TextOverflow.m647equalsimpl0(i, 4) || TextOverflow.m647equalsimpl0(i, 5)) && Constraints.m651getHasBoundedWidthimpl(j)) ? Constraints.m655getMaxWidthimpl(j) : Integer.MAX_VALUE;
        if (Constraints.m657getMinWidthimpl(j) != m655getMaxWidthimpl) {
            m655getMaxWidthimpl = RangesKt.coerceIn(TextDelegateKt.ceilToIntPx(f), Constraints.m657getMinWidthimpl(j), m655getMaxWidthimpl);
        }
        return Constraints.Companion.m660fitPrioritizingWidthZbe2FdA(0, m655getMaxWidthimpl, 0, Constraints.m654getMaxHeightimpl(j));
    }
}
