package androidx.compose.ui.text.platform;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.emoji2.text.EmojiCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DefaultImpl implements EmojiCompatStatusDelegate {
    public State loadState;

    public final State getFontLoadState() {
        EmojiCompat emojiCompat = EmojiCompat.get();
        if (emojiCompat.getLoadState() == 1) {
            return new ImmutableBool(true);
        }
        final MutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
        emojiCompat.registerInitCallback(new EmojiCompat.InitCallback() { // from class: androidx.compose.ui.text.platform.DefaultImpl$getFontLoadState$initCallback$1
            @Override // androidx.emoji2.text.EmojiCompat.InitCallback
            public final void onFailed() {
                this.loadState = EmojiCompatStatus_androidKt.Falsey;
            }

            @Override // androidx.emoji2.text.EmojiCompat.InitCallback
            public final void onInitialized() {
                ((SnapshotMutableStateImpl) MutableState.this).setValue(Boolean.TRUE);
                this.loadState = new ImmutableBool(true);
            }
        });
        return mutableStateOf$default;
    }
}
