package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.appcompat.R$styleable;
import androidx.collection.ArraySet;
import androidx.collection.ArraySet.ElementIterator;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.viewsintegration.EmojiEditTextHelper;
import androidx.emoji2.viewsintegration.EmojiInputConnection;
import androidx.emoji2.viewsintegration.EmojiKeyListener;
import androidx.emoji2.viewsintegration.EmojiTextWatcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppCompatEmojiEditTextHelper {
    public final EmojiEditTextHelper mEmojiEditTextHelper;
    public final EditText mView;

    public AppCompatEmojiEditTextHelper(EditText editText) {
        this.mView = editText;
        this.mEmojiEditTextHelper = new EmojiEditTextHelper(editText);
    }

    public final KeyListener getKeyListener(KeyListener keyListener) {
        if (keyListener instanceof NumberKeyListener) {
            return keyListener;
        }
        this.mEmojiEditTextHelper.getClass();
        if (keyListener instanceof EmojiKeyListener) {
            return keyListener;
        }
        if (keyListener == null) {
            return null;
        }
        return keyListener instanceof NumberKeyListener ? keyListener : new EmojiKeyListener(keyListener);
    }

    public final void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
        try {
            boolean z = obtainStyledAttributes.hasValue(14) ? obtainStyledAttributes.getBoolean(14, true) : true;
            obtainStyledAttributes.recycle();
            EmojiTextWatcher emojiTextWatcher = this.mEmojiEditTextHelper.mTextWatcher;
            if (emojiTextWatcher.mEnabled != z) {
                if (emojiTextWatcher.mInitCallback != null) {
                    EmojiCompat emojiCompat = EmojiCompat.get();
                    EmojiTextWatcher.InitCallbackImpl initCallbackImpl = emojiTextWatcher.mInitCallback;
                    emojiCompat.getClass();
                    ArraySet arraySet = emojiCompat.mInitCallbacks;
                    Preconditions.checkNotNull(initCallbackImpl, "initCallback cannot be null");
                    ((ReentrantReadWriteLock) emojiCompat.mInitLock).writeLock().lock();
                    try {
                        ArrayList arrayList = new ArrayList();
                        arraySet.getClass();
                        ArraySet.ElementIterator elementIterator = arraySet.new ElementIterator();
                        while (elementIterator.hasNext()) {
                            EmojiCompat.InitWithExecutor initWithExecutor = (EmojiCompat.InitWithExecutor) elementIterator.next();
                            if (initWithExecutor.mInitCallback == initCallbackImpl) {
                                arrayList.add(initWithExecutor);
                            }
                        }
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            arraySet.remove((EmojiCompat.InitWithExecutor) it.next());
                        }
                        ((ReentrantReadWriteLock) emojiCompat.mInitLock).writeLock().unlock();
                    } catch (Throwable th) {
                        ((ReentrantReadWriteLock) emojiCompat.mInitLock).writeLock().unlock();
                        throw th;
                    }
                }
                emojiTextWatcher.mEnabled = z;
                if (z) {
                    EmojiTextWatcher.processTextOnEnablingEvent(emojiTextWatcher.mEditText, EmojiCompat.get().getLoadState());
                }
            }
        } catch (Throwable th2) {
            obtainStyledAttributes.recycle();
            throw th2;
        }
    }

    public final InputConnection onCreateInputConnection(InputConnection inputConnection, EditorInfo editorInfo) {
        EmojiEditTextHelper emojiEditTextHelper = this.mEmojiEditTextHelper;
        emojiEditTextHelper.getClass();
        if (inputConnection == null) {
            return null;
        }
        return inputConnection instanceof EmojiInputConnection ? inputConnection : new EmojiInputConnection(emojiEditTextHelper.mEditText, inputConnection, editorInfo);
    }
}
