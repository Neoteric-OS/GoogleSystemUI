package com.android.systemui.compose.modifiers;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid;
import androidx.compose.ui.semantics.SemanticsProperties_androidKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SysuiTestTagKt {
    public static final Modifier sysuiResTag(Modifier modifier, String str) {
        return TestTagKt.testTag(SemanticsModifierKt.semantics(modifier, false, new Function1() { // from class: com.android.systemui.compose.modifiers.SysuiTestTagKt$sysuiResTag$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KProperty[] kPropertyArr = SemanticsProperties_androidKt.$$delegatedProperties;
                SemanticsPropertyKey semanticsPropertyKey = SemanticsPropertiesAndroid.TestTagsAsResourceId;
                KProperty kProperty = SemanticsProperties_androidKt.$$delegatedProperties[0];
                semanticsPropertyKey.setValue((SemanticsPropertyReceiver) obj, Boolean.TRUE);
                return Unit.INSTANCE;
            }
        }), "com.android.systemui:id/".concat(str));
    }
}
