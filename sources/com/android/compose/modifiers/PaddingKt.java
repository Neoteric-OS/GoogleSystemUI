package com.android.compose.modifiers;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PaddingKt {
    public static final Function1 PaddingUnspecified = new Function1() { // from class: com.android.compose.modifiers.PaddingKt$PaddingUnspecified$1
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return 0;
        }
    };

    public static Modifier padding$default(Modifier modifier, Function1 function1, Function1 function12, Function1 function13, int i) {
        Function1 function14 = PaddingUnspecified;
        if ((i & 2) != 0) {
            function1 = function14;
        }
        if ((i & 4) != 0) {
            function12 = function14;
        }
        if ((i & 8) != 0) {
            function13 = function14;
        }
        Function1 function15 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new PaddingModifier(function14, function1, function12, function13));
    }
}
