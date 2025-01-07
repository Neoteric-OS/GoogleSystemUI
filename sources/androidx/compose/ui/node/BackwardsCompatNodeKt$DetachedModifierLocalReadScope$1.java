package androidx.compose.ui.node;

import androidx.compose.ui.modifier.ModifierLocalReadScope;
import androidx.compose.ui.modifier.ProvidableModifierLocal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackwardsCompatNodeKt$DetachedModifierLocalReadScope$1 implements ModifierLocalReadScope {
    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.ui.modifier.ModifierLocalReadScope
    public final Object getCurrent(ProvidableModifierLocal providableModifierLocal) {
        return providableModifierLocal.defaultFactory.invoke();
    }
}
