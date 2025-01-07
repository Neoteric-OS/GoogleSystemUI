package androidx.appcompat.widget;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ToolbarWidgetWrapper {
    public ActionMenuPresenter mActionMenuPresenter;
    public View mCustomView;
    public int mDefaultNavigationContentDescription;
    public Drawable mDefaultNavigationIcon;
    public int mDisplayOpts;
    public CharSequence mHomeDescription;
    public Drawable mIcon;
    public Drawable mLogo;
    public boolean mMenuPrepared;
    public Drawable mNavIcon;
    public CharSequence mSubtitle;
    public CharSequence mTitle;
    public boolean mTitleSet;
    public Toolbar mToolbar;
    public Window.Callback mWindowCallback;

    public final void setDisplayOptions(int i) {
        View view;
        int i2 = this.mDisplayOpts ^ i;
        this.mDisplayOpts = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    updateHomeAccessibility();
                }
                int i3 = this.mDisplayOpts & 4;
                Toolbar toolbar = this.mToolbar;
                if (i3 != 0) {
                    Drawable drawable = this.mNavIcon;
                    if (drawable == null) {
                        drawable = this.mDefaultNavigationIcon;
                    }
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon(null);
                }
            }
            if ((i2 & 3) != 0) {
                updateToolbarLogo();
            }
            int i4 = i2 & 8;
            Toolbar toolbar2 = this.mToolbar;
            if (i4 != 0) {
                if ((i & 8) != 0) {
                    toolbar2.setTitle(this.mTitle);
                    toolbar2.setSubtitle(this.mSubtitle);
                } else {
                    toolbar2.setTitle(null);
                    toolbar2.setSubtitle(null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.mCustomView) == null) {
                return;
            }
            if ((i & 16) != 0) {
                toolbar2.addView(view);
            } else {
                toolbar2.removeView(view);
            }
        }
    }

    public final void updateHomeAccessibility() {
        if ((this.mDisplayOpts & 4) != 0) {
            boolean isEmpty = TextUtils.isEmpty(this.mHomeDescription);
            Toolbar toolbar = this.mToolbar;
            if (!isEmpty) {
                toolbar.setNavigationContentDescription(this.mHomeDescription);
            } else {
                int i = this.mDefaultNavigationContentDescription;
                toolbar.setNavigationContentDescription(i != 0 ? toolbar.getContext().getText(i) : null);
            }
        }
    }

    public final void updateToolbarLogo() {
        Drawable drawable;
        int i = this.mDisplayOpts;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) != 0) {
            drawable = this.mLogo;
            if (drawable == null) {
                drawable = this.mIcon;
            }
        } else {
            drawable = this.mIcon;
        }
        this.mToolbar.setLogo(drawable);
    }
}
