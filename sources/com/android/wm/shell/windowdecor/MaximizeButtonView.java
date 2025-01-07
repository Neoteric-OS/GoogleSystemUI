package com.android.wm.shell.windowdecor;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.window.flags.DesktopModeFlags;
import androidx.core.content.ContextCompat;
import com.android.wm.shell.R;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaximizeButtonView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean hoverDisabled;
    public final AnimatorSet hoverProgressAnimatorSet;
    public final ImageButton maximizeWindow;
    public DesktopModeWindowDecoration$$ExternalSyntheticLambda3 onHoverAnimationFinishedListener;
    public final ProgressBar progressBar;

    public MaximizeButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.hoverProgressAnimatorSet = new AnimatorSet();
        LayoutInflater.from(context).inflate(R.layout.maximize_menu_button, (ViewGroup) this, true);
        this.progressBar = (ProgressBar) requireViewById(R.id.progress_bar);
        this.maximizeWindow = (ImageButton) requireViewById(R.id.maximize_window);
    }

    public final void cancelHoverAnimation() {
        Iterator<T> it = this.hoverProgressAnimatorSet.getChildAnimations().iterator();
        while (it.hasNext()) {
            ((Animator) it.next()).removeAllListeners();
        }
        this.hoverProgressAnimatorSet.cancel();
        this.progressBar.setVisibility(4);
    }

    public final void setAnimationTints(boolean z, ColorStateList colorStateList, Integer num, RippleDrawable rippleDrawable) {
        if (DesktopModeFlags.ENABLE_THEMED_APP_HEADERS.isTrue()) {
            if (colorStateList == null) {
                throw new IllegalArgumentException("Icon foreground color must be non-null");
            }
            if (num == null) {
                throw new IllegalArgumentException("Base foreground color must be non-null");
            }
            if (rippleDrawable == null) {
                throw new IllegalArgumentException("Ripple drawable must be non-null");
            }
            this.maximizeWindow.setImageTintList(colorStateList);
            this.maximizeWindow.setBackground(rippleDrawable);
            this.progressBar.setProgressTintList(ColorStateList.valueOf(num.intValue()).withAlpha(38));
            this.progressBar.setProgressBackgroundTintList(ColorStateList.valueOf(0));
            return;
        }
        if (z) {
            this.progressBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.desktop_mode_maximize_menu_progress_dark)));
            Drawable background = this.maximizeWindow.getBackground();
            if (background != null) {
                background.setTintList(ContextCompat.getColorStateList(R.color.desktop_mode_caption_button_color_selector_dark, getContext()));
                return;
            }
            return;
        }
        this.progressBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.desktop_mode_maximize_menu_progress_light)));
        Drawable background2 = this.maximizeWindow.getBackground();
        if (background2 != null) {
            background2.setTintList(ContextCompat.getColorStateList(R.color.desktop_mode_caption_button_color_selector_light, getContext()));
        }
    }
}
