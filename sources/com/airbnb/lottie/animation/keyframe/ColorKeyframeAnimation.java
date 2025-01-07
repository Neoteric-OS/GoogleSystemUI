package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ColorKeyframeAnimation extends KeyframeAnimation {
    public final int getIntValue(Keyframe keyframe, float f) {
        Float f2;
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        LottieValueCallback lottieValueCallback = this.valueCallback;
        Object obj = keyframe.startValue;
        if (lottieValueCallback != null && (f2 = keyframe.endFrame) != null) {
            Integer num = (Integer) lottieValueCallback.getValueInternal(keyframe.startFrame, f2.floatValue(), (Integer) obj, (Integer) keyframe.endValue, f, getLinearCurrentKeyframeProgress(), this.progress);
            if (num != null) {
                return num.intValue();
            }
        }
        return GammaEvaluator.evaluate(((Integer) obj).intValue(), MiscUtils.clamp(f, 0.0f, 1.0f), ((Integer) keyframe.endValue).intValue());
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public final Object getValue(Keyframe keyframe, float f) {
        return Integer.valueOf(getIntValue(keyframe, f));
    }
}
