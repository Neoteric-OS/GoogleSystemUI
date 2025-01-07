package com.android.systemui.shade;

import androidx.constraintlayout.widget.ConstraintSet;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CombinedShadeHeadersConstraintManagerKt {
    public static final Function1 plus(final Function1 function1, final Function1 function12) {
        return function1 == null ? function12 : function12 == null ? function1 : new Function1() { // from class: com.android.systemui.shade.CombinedShadeHeadersConstraintManagerKt$plus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ConstraintSet constraintSet = (ConstraintSet) obj;
                Function1.this.invoke(constraintSet);
                function12.invoke(constraintSet);
                return Unit.INSTANCE;
            }
        };
    }
}
