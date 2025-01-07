package androidx.compose.ui.input;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InputModeManagerImpl implements InputModeManager {
    public final MutableState inputMode$delegate;

    public InputModeManagerImpl(int i) {
        this.inputMode$delegate = SnapshotStateKt.mutableStateOf$default(new InputMode(i));
    }
}
