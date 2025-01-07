package androidx.compose.ui.platform;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowInfoImpl implements WindowInfo {
    public static final MutableState GlobalKeyboardModifiers = SnapshotStateKt.mutableStateOf$default(new PointerKeyboardModifiers(0));
    public final MutableState _isWindowFocused = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);

    public final boolean isWindowFocused() {
        return ((Boolean) ((SnapshotMutableStateImpl) this._isWindowFocused).getValue()).booleanValue();
    }
}
