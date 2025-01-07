package com.android.compose.modifiers;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SizeKt {
    public static final Function1 SizeUnspecified = new Function1() { // from class: com.android.compose.modifiers.SizeKt$SizeUnspecified$1
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return 0;
        }
    };

    public static final Modifier height(Modifier modifier, Function1 function1) {
        Function1 function12 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeModifier(null, function1, null, function1, 5));
    }
}
