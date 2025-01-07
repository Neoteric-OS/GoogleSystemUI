package androidx.compose.ui.text.platform;

import androidx.emoji2.text.EmojiCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmojiCompatStatus implements EmojiCompatStatusDelegate {
    public static final EmojiCompatStatus INSTANCE = null;
    public static final EmojiCompatStatusDelegate delegate;

    static {
        DefaultImpl defaultImpl = new DefaultImpl();
        defaultImpl.loadState = EmojiCompat.isConfigured() ? defaultImpl.getFontLoadState() : null;
        delegate = defaultImpl;
    }
}
