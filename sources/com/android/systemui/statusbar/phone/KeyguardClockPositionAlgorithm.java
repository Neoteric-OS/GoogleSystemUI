package com.android.systemui.statusbar.phone;

import android.util.MathUtils;
import android.view.animation.PathInterpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.doze.util.BurnInHelperKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardClockPositionAlgorithm {
    public boolean mBypassEnabled;
    public float mClockBottom;
    public int mContainerTopPadding;
    public float mCurrentBurnInOffsetY;
    public int mCutoutTopInset;
    public float mDarkAmount;
    public boolean mIsClockTopAligned;
    public boolean mIsSplitShade;
    public int mKeyguardStatusHeight;
    public int mMaxBurnInPreventionOffsetX;
    public int mMaxBurnInPreventionOffsetYClock;
    public int mMinTopMargin;
    public float mOverStretchAmount;
    public float mPanelExpansion;
    public float mQsExpansion;
    public int mSplitShadeTargetTopMargin;
    public int mSplitShadeTopNotificationsMargin;
    public int mStatusViewBottomMargin;
    public float mUdfpsTop;
    public int mUnlockedStackScrollerPadding;
    public int mUserSwitchHeight;
    public int mUserSwitchPreferredY;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Result {
        public float clockAlpha;
        public int clockX;
        public int stackScrollerPadding;
        public int stackScrollerPaddingExpanded;
        public int userSwitchY;
    }

    public final int getClockY(float f, float f2) {
        float lerp = MathUtils.lerp((-this.mKeyguardStatusHeight) / 3.0f, this.mIsSplitShade ? this.mSplitShadeTargetTopMargin : this.mMinTopMargin, ((PathInterpolator) Interpolators.FAST_OUT_LINEAR_IN).getInterpolation(f));
        int i = this.mMaxBurnInPreventionOffsetYClock;
        float f3 = lerp - i;
        float f4 = this.mCutoutTopInset;
        float f5 = f3 < f4 ? f4 - f3 : 0.0f;
        float f6 = this.mUdfpsTop;
        if (f6 > -1.0f && !this.mIsClockTopAligned) {
            float f7 = this.mClockBottom;
            if (f6 < f7) {
                int i2 = ((int) (lerp - f4)) / 2;
                if (i >= i2) {
                    i = i2;
                }
                f5 = -i;
            } else {
                float f8 = lerp - f4;
                float f9 = f6 - f7;
                int i3 = ((int) (f9 + f8)) / 2;
                if (i >= i3) {
                    i = i3;
                }
                f5 = (f9 - f8) / 2.0f;
            }
        }
        float burnInOffset = BurnInHelperKt.getBurnInOffset(i * 2, false) - i;
        float f10 = lerp + burnInOffset + f5;
        this.mCurrentBurnInOffsetY = MathUtils.lerp(0.0f, burnInOffset, f2);
        return (int) (MathUtils.lerp(lerp, f10, f2) + this.mOverStretchAmount);
    }
}
