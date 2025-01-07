package androidx.appcompat.widget;

import android.graphics.drawable.Drawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppCompatCheckedTextViewHelper {
    public final boolean mHasCheckMarkTint = false;
    public final boolean mHasCheckMarkTintMode = false;
    public boolean mSkipNextApply;
    public final AppCompatCheckedTextView mView;

    public AppCompatCheckedTextViewHelper(AppCompatCheckedTextView appCompatCheckedTextView) {
        this.mView = appCompatCheckedTextView;
    }

    public final void applyCheckMarkTint() {
        AppCompatCheckedTextView appCompatCheckedTextView = this.mView;
        Drawable checkMarkDrawable = appCompatCheckedTextView.getCheckMarkDrawable();
        if (checkMarkDrawable != null) {
            if (this.mHasCheckMarkTint || this.mHasCheckMarkTintMode) {
                Drawable mutate = checkMarkDrawable.mutate();
                if (this.mHasCheckMarkTint) {
                    mutate.setTintList(null);
                }
                if (this.mHasCheckMarkTintMode) {
                    mutate.setTintMode(null);
                }
                if (mutate.isStateful()) {
                    mutate.setState(appCompatCheckedTextView.getDrawableState());
                }
                appCompatCheckedTextView.setCheckMarkDrawable(mutate);
            }
        }
    }
}
