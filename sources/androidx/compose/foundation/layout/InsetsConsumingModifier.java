package androidx.compose.foundation.layout;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.modifier.ModifierLocalReadScope;
import androidx.compose.ui.modifier.ProvidableModifierLocal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
abstract class InsetsConsumingModifier implements ModifierLocalConsumer, ModifierLocalProvider {
    public final MutableState consumedInsets$delegate = SnapshotStateKt.mutableStateOf$default(new FixedIntInsets());

    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public final ProvidableModifierLocal getKey() {
        return WindowInsetsPaddingKt.ModifierLocalConsumedWindowInsets;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public final WindowInsets getValue$1() {
        return (WindowInsets) ((SnapshotMutableStateImpl) this.consumedInsets$delegate).getValue();
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalConsumer
    public final void onModifierLocalsUpdated(ModifierLocalReadScope modifierLocalReadScope) {
        ((SnapshotMutableStateImpl) this.consumedInsets$delegate).setValue(new UnionInsets(((UnionInsetsConsumingModifier) this).insets, (WindowInsets) modifierLocalReadScope.getCurrent(WindowInsetsPaddingKt.ModifierLocalConsumedWindowInsets)));
    }
}
