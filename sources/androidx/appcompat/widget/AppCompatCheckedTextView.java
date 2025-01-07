package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import androidx.appcompat.content.res.AppCompatResources;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppCompatCheckedTextView extends CheckedTextView {
    public AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
    public final AppCompatBackgroundHelper mBackgroundTintHelper;
    public final AppCompatCheckedTextViewHelper mCheckedHelper;
    public final AppCompatTextHelper mTextHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:12:0x008d A[Catch: all -> 0x0067, TryCatch #1 {all -> 0x0067, blocks: (B:3:0x0048, B:6:0x0053, B:9:0x005b, B:10:0x0084, B:12:0x008d, B:13:0x0094, B:15:0x009d, B:25:0x0069, B:27:0x0071, B:29:0x0079), top: B:2:0x0048 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x009d A[Catch: all -> 0x0067, TRY_LEAVE, TryCatch #1 {all -> 0x0067, blocks: (B:3:0x0048, B:6:0x0053, B:9:0x005b, B:10:0x0084, B:12:0x008d, B:13:0x0094, B:15:0x009d, B:25:0x0069, B:27:0x0071, B:29:0x0079), top: B:2:0x0048 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AppCompatCheckedTextView(android.content.Context r11, android.util.AttributeSet r12) {
        /*
            r10 = this;
            r0 = 0
            androidx.appcompat.widget.TintContextWrapper.wrap(r11)
            r8 = 2130968821(0x7f0400f5, float:1.7546306E38)
            r10.<init>(r11, r12, r8)
            android.content.Context r11 = r10.getContext()
            androidx.appcompat.widget.ThemeUtils.checkAppCompatTheme(r11, r10)
            androidx.appcompat.widget.AppCompatTextHelper r11 = new androidx.appcompat.widget.AppCompatTextHelper
            r11.<init>(r10)
            r10.mTextHelper = r11
            r11.loadFromAttributes(r12, r8)
            r11.applyCompoundDrawablesTints()
            androidx.appcompat.widget.AppCompatBackgroundHelper r11 = new androidx.appcompat.widget.AppCompatBackgroundHelper
            r11.<init>(r10)
            r10.mBackgroundTintHelper = r11
            r11.loadFromAttributes(r12, r8)
            androidx.appcompat.widget.AppCompatCheckedTextViewHelper r11 = new androidx.appcompat.widget.AppCompatCheckedTextViewHelper
            r11.<init>(r10)
            r10.mCheckedHelper = r11
            android.content.Context r1 = r10.getContext()
            int[] r3 = androidx.appcompat.R$styleable.CheckedTextView
            androidx.appcompat.widget.TintTypedArray r9 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r1, r12, r3, r8)
            android.content.Context r2 = r10.getContext()
            android.content.res.TypedArray r5 = r9.mWrapped
            java.util.WeakHashMap r1 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            r7 = 0
            r1 = r10
            r4 = r12
            r6 = r8
            androidx.core.view.ViewCompat.Api29Impl.saveAttributeDataForStyleable(r1, r2, r3, r4, r5, r6, r7)
            android.content.res.TypedArray r1 = r9.mWrapped     // Catch: java.lang.Throwable -> L67
            r2 = 1
            boolean r1 = r1.hasValue(r2)     // Catch: java.lang.Throwable -> L67
            androidx.appcompat.widget.AppCompatCheckedTextView r11 = r11.mView
            if (r1 == 0) goto L69
            android.content.res.TypedArray r1 = r9.mWrapped     // Catch: java.lang.Throwable -> L67
            int r1 = r1.getResourceId(r2, r0)     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L69
            android.content.Context r2 = r10.getContext()     // Catch: java.lang.Throwable -> L67 android.content.res.Resources.NotFoundException -> L69
            android.graphics.drawable.Drawable r1 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r1, r2)     // Catch: java.lang.Throwable -> L67 android.content.res.Resources.NotFoundException -> L69
            r10.setCheckMarkDrawable(r1)     // Catch: java.lang.Throwable -> L67 android.content.res.Resources.NotFoundException -> L69
            goto L84
        L67:
            r10 = move-exception
            goto Lc0
        L69:
            android.content.res.TypedArray r1 = r9.mWrapped     // Catch: java.lang.Throwable -> L67
            boolean r1 = r1.hasValue(r0)     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L84
            android.content.res.TypedArray r1 = r9.mWrapped     // Catch: java.lang.Throwable -> L67
            int r0 = r1.getResourceId(r0, r0)     // Catch: java.lang.Throwable -> L67
            if (r0 == 0) goto L84
            android.content.Context r1 = r11.getContext()     // Catch: java.lang.Throwable -> L67
            android.graphics.drawable.Drawable r0 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r0, r1)     // Catch: java.lang.Throwable -> L67
            r11.setCheckMarkDrawable(r0)     // Catch: java.lang.Throwable -> L67
        L84:
            android.content.res.TypedArray r0 = r9.mWrapped     // Catch: java.lang.Throwable -> L67
            r1 = 2
            boolean r0 = r0.hasValue(r1)     // Catch: java.lang.Throwable -> L67
            if (r0 == 0) goto L94
            android.content.res.ColorStateList r0 = r9.getColorStateList(r1)     // Catch: java.lang.Throwable -> L67
            r11.setCheckMarkTintList(r0)     // Catch: java.lang.Throwable -> L67
        L94:
            android.content.res.TypedArray r0 = r9.mWrapped     // Catch: java.lang.Throwable -> L67
            r1 = 3
            boolean r0 = r0.hasValue(r1)     // Catch: java.lang.Throwable -> L67
            if (r0 == 0) goto Lac
            android.content.res.TypedArray r0 = r9.mWrapped     // Catch: java.lang.Throwable -> L67
            r2 = -1
            int r0 = r0.getInt(r1, r2)     // Catch: java.lang.Throwable -> L67
            r1 = 0
            android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r0, r1)     // Catch: java.lang.Throwable -> L67
            r11.setCheckMarkTintMode(r0)     // Catch: java.lang.Throwable -> L67
        Lac:
            r9.recycle()
            androidx.appcompat.widget.AppCompatEmojiTextHelper r11 = r10.mAppCompatEmojiTextHelper
            if (r11 != 0) goto Lba
            androidx.appcompat.widget.AppCompatEmojiTextHelper r11 = new androidx.appcompat.widget.AppCompatEmojiTextHelper
            r11.<init>(r10)
            r10.mAppCompatEmojiTextHelper = r11
        Lba:
            androidx.appcompat.widget.AppCompatEmojiTextHelper r10 = r10.mAppCompatEmojiTextHelper
            r10.loadFromAttributes(r12, r8)
            return
        Lc0:
            r9.recycle()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCheckedTextView.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.mCheckedHelper;
        if (appCompatCheckedTextViewHelper != null) {
            appCompatCheckedTextViewHelper.applyCheckMarkTint();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AppCompatHintHelper.onCreateInputConnection(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    @Override // android.widget.TextView
    public final void setAllCaps(boolean z) {
        super.setAllCaps(z);
        if (this.mAppCompatEmojiTextHelper == null) {
            this.mAppCompatEmojiTextHelper = new AppCompatEmojiTextHelper(this);
        }
        this.mAppCompatEmojiTextHelper.setAllCaps(z);
    }

    @Override // android.view.View
    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundDrawable();
        }
    }

    @Override // android.view.View
    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundResource(i);
        }
    }

    @Override // android.widget.CheckedTextView
    public final void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.mCheckedHelper;
        if (appCompatCheckedTextViewHelper != null) {
            if (appCompatCheckedTextViewHelper.mSkipNextApply) {
                appCompatCheckedTextViewHelper.mSkipNextApply = false;
            } else {
                appCompatCheckedTextViewHelper.mSkipNextApply = true;
                appCompatCheckedTextViewHelper.applyCheckMarkTint();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetTextAppearance(i, context);
        }
    }

    @Override // android.widget.CheckedTextView
    public final void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(AppCompatResources.getDrawable(i, getContext()));
    }
}
