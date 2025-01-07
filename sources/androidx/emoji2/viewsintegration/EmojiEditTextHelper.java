package androidx.emoji2.viewsintegration;

import android.widget.EditText;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmojiEditTextHelper {
    public final EditText mEditText;
    public final EmojiTextWatcher mTextWatcher;

    public EmojiEditTextHelper(EditText editText) {
        this.mEditText = editText;
        EmojiTextWatcher emojiTextWatcher = new EmojiTextWatcher(editText);
        this.mTextWatcher = emojiTextWatcher;
        editText.addTextChangedListener(emojiTextWatcher);
        if (EmojiEditableFactory.sInstance == null) {
            synchronized (EmojiEditableFactory.INSTANCE_LOCK) {
                try {
                    if (EmojiEditableFactory.sInstance == null) {
                        EmojiEditableFactory emojiEditableFactory = new EmojiEditableFactory();
                        try {
                            EmojiEditableFactory.sWatcherClass = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, EmojiEditableFactory.class.getClassLoader());
                        } catch (Throwable unused) {
                        }
                        EmojiEditableFactory.sInstance = emojiEditableFactory;
                    }
                } finally {
                }
            }
        }
        editText.setEditableFactory(EmojiEditableFactory.sInstance);
    }
}
