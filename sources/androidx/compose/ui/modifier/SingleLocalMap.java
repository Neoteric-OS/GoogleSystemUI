package androidx.compose.ui.modifier;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.internal.InlineClassHelperKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SingleLocalMap extends ModifierLocalMap {
    public final ModifierLocal key;
    public final MutableState value$delegate = SnapshotStateKt.mutableStateOf$default(null);

    public SingleLocalMap(ModifierLocal modifierLocal) {
        this.key = modifierLocal;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public final boolean contains$ui_release(ModifierLocal modifierLocal) {
        return modifierLocal == this.key;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public final Object get$ui_release(ProvidableModifierLocal providableModifierLocal) {
        if (providableModifierLocal != this.key) {
            InlineClassHelperKt.throwIllegalStateException("Check failed.");
        }
        Object value = ((SnapshotMutableStateImpl) this.value$delegate).getValue();
        if (value == null) {
            return null;
        }
        return value;
    }
}
