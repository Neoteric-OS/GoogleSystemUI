package com.android.systemui.navigationbar.views.buttons;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextualButton extends ButtonDispatcher {
    public final int mIconResId;
    public final Context mLightContext;

    public ContextualButton(int i, int i2, Context context) {
        super(i);
        this.mLightContext = context;
        this.mIconResId = i2;
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonDispatcher
    public final void setVisibility(int i) {
        super.setVisibility(i);
        KeyButtonDrawable keyButtonDrawable = this.mImageDrawable;
        if (i == 0 || keyButtonDrawable == null || !keyButtonDrawable.mState.mSupportsAnimation) {
            return;
        }
        AnimatedVectorDrawable animatedVectorDrawable = keyButtonDrawable.mAnimatedDrawable;
        if (animatedVectorDrawable != null) {
            animatedVectorDrawable.clearAnimationCallbacks();
        }
        AnimatedVectorDrawable animatedVectorDrawable2 = keyButtonDrawable.mAnimatedDrawable;
        if (animatedVectorDrawable2 != null) {
            animatedVectorDrawable2.reset();
        }
    }
}
