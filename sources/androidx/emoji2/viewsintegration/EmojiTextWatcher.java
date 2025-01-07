package androidx.emoji2.viewsintegration;

import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmojiTextWatcher implements TextWatcher {
    public final EditText mEditText;
    public boolean mEnabled = true;
    public InitCallbackImpl mInitCallback;
    public int mLastEditLength;
    public int mLastEditPosition;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InitCallbackImpl extends EmojiCompat.InitCallback implements Runnable {
        public final Reference mViewRef;

        public InitCallbackImpl(EditText editText) {
            this.mViewRef = new WeakReference(editText);
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public final void onInitialized() {
            Handler handler;
            EditText editText = (EditText) this.mViewRef.get();
            if (editText == null || (handler = editText.getHandler()) == null) {
                return;
            }
            handler.post(this);
        }

        @Override // java.lang.Runnable
        public final void run() {
            EmojiTextWatcher.processTextOnEnablingEvent((EditText) this.mViewRef.get(), 1);
        }
    }

    public EmojiTextWatcher(EditText editText) {
        this.mEditText = editText;
    }

    public static void processTextOnEnablingEvent(EditText editText, int i) {
        int length;
        if (i == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            EmojiCompat emojiCompat = EmojiCompat.get();
            if (editableText == null) {
                length = 0;
            } else {
                emojiCompat.getClass();
                length = editableText.length();
            }
            emojiCompat.process(0, length, 0, editableText);
            if (selectionStart >= 0 && selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionStart, selectionEnd);
            } else if (selectionStart >= 0) {
                Selection.setSelection(editableText, selectionStart);
            } else if (selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionEnd);
            }
        }
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (!this.mEditText.isInEditMode() && this.mEnabled && EmojiCompat.isConfigured()) {
            int i = this.mLastEditPosition;
            int i2 = this.mLastEditLength;
            if (i2 > 0) {
                int loadState = EmojiCompat.get().getLoadState();
                if (loadState != 0) {
                    if (loadState == 1) {
                        EmojiCompat.get().process(i, i2 + i, 0, editable);
                        return;
                    } else if (loadState != 3) {
                        return;
                    }
                }
                EmojiCompat emojiCompat = EmojiCompat.get();
                if (this.mInitCallback == null) {
                    this.mInitCallback = new InitCallbackImpl(this.mEditText);
                }
                emojiCompat.registerInitCallback(this.mInitCallback);
            }
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mLastEditPosition = i;
        this.mLastEditLength = i3;
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
