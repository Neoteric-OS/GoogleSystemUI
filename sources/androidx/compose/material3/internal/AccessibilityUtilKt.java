package androidx.compose.material3.internal;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AccessibilityUtilKt {
    public static final float HorizontalSemanticsBoundsPadding;
    public static final Modifier IncreaseHorizontalSemanticsBounds;
    public static final float VerticalSemanticsBoundsPadding;

    static {
        float f = 10;
        HorizontalSemanticsBoundsPadding = f;
        VerticalSemanticsBoundsPadding = f;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        IncreaseHorizontalSemanticsBounds = PaddingKt.m100paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(companion, new Function3() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$IncreaseHorizontalSemanticsBounds$1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                MeasureResult layout$1;
                MeasureScope measureScope = (MeasureScope) obj;
                long j = ((Constraints) obj3).value;
                final int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(AccessibilityUtilKt.HorizontalSemanticsBoundsPadding);
                int i = mo45roundToPx0680j_4 * 2;
                final Placeable mo479measureBRTryo0 = ((Measurable) obj2).mo479measureBRTryo0(ConstraintsKt.m666offsetNN6EwU(i, 0, j));
                layout$1 = measureScope.layout$1(mo479measureBRTryo0.width - i, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$IncreaseHorizontalSemanticsBounds$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        ((Placeable.PlacementScope) obj4).place(Placeable.this, -mo45roundToPx0680j_4, 0, 0.0f);
                        return Unit.INSTANCE;
                    }
                });
                return layout$1;
            }
        }), true, new Function1() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$IncreaseHorizontalSemanticsBounds$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }), f, 0.0f, 2);
        PaddingKt.m100paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(companion, new Function3() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$IncreaseVerticalSemanticsBounds$1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                MeasureResult layout$1;
                MeasureScope measureScope = (MeasureScope) obj;
                long j = ((Constraints) obj3).value;
                final int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(AccessibilityUtilKt.VerticalSemanticsBoundsPadding);
                int i = mo45roundToPx0680j_4 * 2;
                final Placeable mo479measureBRTryo0 = ((Measurable) obj2).mo479measureBRTryo0(ConstraintsKt.m666offsetNN6EwU(0, i, j));
                layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height - i, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$IncreaseVerticalSemanticsBounds$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        ((Placeable.PlacementScope) obj4).place(Placeable.this, 0, -mo45roundToPx0680j_4, 0.0f);
                        return Unit.INSTANCE;
                    }
                });
                return layout$1;
            }
        }), true, new Function1() { // from class: androidx.compose.material3.internal.AccessibilityUtilKt$IncreaseVerticalSemanticsBounds$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }), 0.0f, f, 1);
    }
}
