package androidx.compose.ui.modifier;

import androidx.compose.ui.internal.InlineClassHelperKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackwardsCompatLocalMap extends ModifierLocalMap {
    public ModifierLocalProvider element;

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public final boolean contains$ui_release(ModifierLocal modifierLocal) {
        return modifierLocal == this.element.getKey();
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public final Object get$ui_release(ProvidableModifierLocal providableModifierLocal) {
        if (providableModifierLocal != this.element.getKey()) {
            InlineClassHelperKt.throwIllegalStateException("Check failed.");
        }
        return this.element.getValue$1();
    }
}
