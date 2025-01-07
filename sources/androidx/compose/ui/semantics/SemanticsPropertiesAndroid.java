package androidx.compose.ui.semantics;

import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SemanticsPropertiesAndroid {
    public static final SemanticsPropertyKey TestTagsAsResourceId = new SemanticsPropertyKey("TestTagsAsResourceId", false, new Function2() { // from class: androidx.compose.ui.semantics.SemanticsPropertiesAndroid$TestTagsAsResourceId$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            ((Boolean) obj2).booleanValue();
            return bool;
        }
    });
}
