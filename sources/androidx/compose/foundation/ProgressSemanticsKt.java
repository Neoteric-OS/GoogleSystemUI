package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.ClosedFloatRange;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ProgressSemanticsKt {
    public static final Modifier progressSemantics(Modifier modifier, final float f, final ClosedFloatRange closedFloatRange, final int i) {
        return SemanticsModifierKt.semantics(modifier, true, new Function1() { // from class: androidx.compose.foundation.ProgressSemanticsKt$progressSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                Float valueOf = Float.valueOf(f);
                ClosedFloatingPointRange closedFloatingPointRange = closedFloatRange;
                ClosedFloatRange closedFloatRange2 = (ClosedFloatRange) closedFloatingPointRange;
                if (closedFloatRange2.isEmpty()) {
                    throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedFloatingPointRange + '.');
                }
                float f2 = closedFloatRange2._start;
                if (!closedFloatRange2.lessThanOrEquals(valueOf, Float.valueOf(f2)) || closedFloatRange2.lessThanOrEquals(Float.valueOf(f2), valueOf)) {
                    float f3 = closedFloatRange2._endInclusive;
                    if (closedFloatRange2.lessThanOrEquals(Float.valueOf(f3), valueOf) && !closedFloatRange2.lessThanOrEquals(valueOf, Float.valueOf(f3))) {
                        valueOf = Float.valueOf(f3);
                    }
                } else {
                    valueOf = Float.valueOf(f2);
                }
                ProgressBarRangeInfo progressBarRangeInfo = new ProgressBarRangeInfo(valueOf.floatValue(), closedFloatRange, i);
                KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.ProgressBarRangeInfo;
                KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[1];
                semanticsPropertyKey.setValue(semanticsPropertyReceiver, progressBarRangeInfo);
                return Unit.INSTANCE;
            }
        });
    }
}
