package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.emoji2.viewsintegration.EmojiTextViewHelper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppCompatEmojiTextHelper {
    public final EmojiTextViewHelper mEmojiTextViewHelper;
    public final TextView mView;

    public AppCompatEmojiTextHelper(TextView textView) {
        this.mView = textView;
        this.mEmojiTextViewHelper = new EmojiTextViewHelper(textView);
    }

    public final InputFilter[] getFilters(InputFilter[] inputFilterArr) {
        return this.mEmojiTextViewHelper.mHelper.getFilters(inputFilterArr);
    }

    public final void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
        try {
            boolean z = obtainStyledAttributes.hasValue(14) ? obtainStyledAttributes.getBoolean(14, true) : true;
            obtainStyledAttributes.recycle();
            this.mEmojiTextViewHelper.mHelper.setEnabled(z);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void setAllCaps(boolean z) {
        this.mEmojiTextViewHelper.mHelper.setAllCaps(z);
    }
}
