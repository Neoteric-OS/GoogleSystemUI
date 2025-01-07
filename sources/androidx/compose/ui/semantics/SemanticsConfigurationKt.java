package androidx.compose.ui.semantics;

import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SemanticsConfigurationKt {
    public static final Object getOrNull(SemanticsConfiguration semanticsConfiguration, SemanticsPropertyKey semanticsPropertyKey) {
        SemanticsConfigurationKt$getOrNull$1 semanticsConfigurationKt$getOrNull$1 = new Function0() { // from class: androidx.compose.ui.semantics.SemanticsConfigurationKt$getOrNull$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return null;
            }
        };
        Object obj = semanticsConfiguration.props.get(semanticsPropertyKey);
        return obj == null ? semanticsConfigurationKt$getOrNull$1.invoke() : obj;
    }
}
