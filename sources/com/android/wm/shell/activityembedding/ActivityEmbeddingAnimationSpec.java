package com.android.wm.shell.activityembedding;

import android.R;
import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.window.TransitionInfo;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.internal.policy.TransitionAnimation;
import com.android.wm.shell.shared.TransitionUtil;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActivityEmbeddingAnimationSpec {
    public final Interpolator mFastOutExtraSlowInInterpolator;
    public final LinearInterpolator mLinearInterpolator = new LinearInterpolator();
    public final TransitionAnimation mTransitionAnimation;
    public float mTransitionAnimationScaleSetting;

    public ActivityEmbeddingAnimationSpec(Context context) {
        this.mTransitionAnimation = new TransitionAnimation(context, false, "ActivityEmbeddingAnimSpec");
        this.mFastOutExtraSlowInInterpolator = AnimationUtils.loadInterpolator(context, R.interpolator.fast_out_extra_slow_in);
    }

    public final Animation loadCustomAnimationFromOptions(TransitionInfo.AnimationOptions animationOptions, int i) {
        int i2;
        if (animationOptions == null || animationOptions.getType() != 1) {
            return null;
        }
        if (TransitionUtil.isOpeningType(i)) {
            i2 = animationOptions.getEnterResId();
        } else if (TransitionUtil.isClosingType(i)) {
            i2 = animationOptions.getExitResId();
        } else if (i == 6) {
            i2 = animationOptions.getChangeResId();
        } else {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("Unknown transit type:", "ActivityEmbeddingAnimSpec", i);
            i2 = -1;
        }
        if (i2 == -1) {
            return null;
        }
        Animation loadDefaultAnimationRes = this.mTransitionAnimation.loadDefaultAnimationRes(i2);
        return loadDefaultAnimationRes != null ? loadDefaultAnimationRes : new AlphaAnimation(1.0f, 1.0f);
    }
}
