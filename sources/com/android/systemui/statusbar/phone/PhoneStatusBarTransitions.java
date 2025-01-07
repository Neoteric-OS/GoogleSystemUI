package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import com.android.systemui.shared.statusbar.phone.BarTransitions;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhoneStatusBarTransitions extends BarTransitions {
    public final View mBattery;
    public Animator mCurrentAnimation;
    public final float mIconAlphaWhenOpaque;
    public boolean mIsHeadsUp;
    public final View mStartSide;
    public final View mStatusIcons;

    public PhoneStatusBarTransitions(PhoneStatusBarView phoneStatusBarView, View view) {
        super(view, R.drawable.status_background);
        this.mIconAlphaWhenOpaque = phoneStatusBarView.getContext().getResources().getFraction(R.dimen.status_bar_icon_drawing_alpha, 1, 1);
        this.mStartSide = phoneStatusBarView.findViewById(R.id.status_bar_start_side_except_heads_up);
        this.mStatusIcons = phoneStatusBarView.findViewById(R.id.statusIcons);
        this.mBattery = phoneStatusBarView.findViewById(R.id.battery);
        applyModeBackground(this.mMode, false);
        applyMode(this.mMode, false);
    }

    public final void applyMode(int i, boolean z) {
        if (this.mStartSide == null) {
            return;
        }
        float iconAlphaBasedOnOpacity = this.mIsHeadsUp ? getIconAlphaBasedOnOpacity(i) : isLightsOut(i) ? 0.0f : getIconAlphaBasedOnOpacity(i);
        float iconAlphaBasedOnOpacity2 = isLightsOut(i) ? 0.0f : getIconAlphaBasedOnOpacity(i);
        float iconAlphaBasedOnOpacity3 = isLightsOut(i) ? 0.5f : getIconAlphaBasedOnOpacity(i);
        Animator animator = this.mCurrentAnimation;
        if (animator != null) {
            animator.cancel();
        }
        if (!z) {
            this.mStartSide.setAlpha(iconAlphaBasedOnOpacity);
            this.mStatusIcons.setAlpha(iconAlphaBasedOnOpacity2);
            this.mBattery.setAlpha(iconAlphaBasedOnOpacity3);
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        View view = this.mStartSide;
        float[] fArr = {view.getAlpha(), iconAlphaBasedOnOpacity};
        View view2 = this.mStatusIcons;
        float[] fArr2 = {view2.getAlpha(), iconAlphaBasedOnOpacity2};
        View view3 = this.mBattery;
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", fArr), ObjectAnimator.ofFloat(view2, "alpha", fArr2), ObjectAnimator.ofFloat(view3, "alpha", view3.getAlpha(), iconAlphaBasedOnOpacity3));
        if (isLightsOut(i)) {
            animatorSet.setDuration(1500L);
        }
        animatorSet.start();
        this.mCurrentAnimation = animatorSet;
    }

    public final float getIconAlphaBasedOnOpacity(int i) {
        if (i == 1 || i == 2 || i == 0 || i == 6) {
            return 1.0f;
        }
        return this.mIconAlphaWhenOpaque;
    }

    public final void onTransition(int i, int i2, boolean z) {
        applyModeBackground(i2, z);
        applyMode(i2, z);
    }
}
