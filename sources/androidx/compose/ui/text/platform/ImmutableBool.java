package androidx.compose.ui.text.platform;

import androidx.compose.runtime.State;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ImmutableBool implements State {
    public final boolean value;

    public ImmutableBool(boolean z) {
        this.value = z;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return Boolean.valueOf(this.value);
    }
}
