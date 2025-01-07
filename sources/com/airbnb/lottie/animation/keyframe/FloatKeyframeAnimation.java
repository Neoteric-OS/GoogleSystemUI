package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FloatKeyframeAnimation extends KeyframeAnimation {
    public final float getFloatValue(Keyframe keyframe, float f) {
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        LottieValueCallback lottieValueCallback = this.valueCallback;
        Object obj = keyframe.startValue;
        if (lottieValueCallback != null) {
            Float f2 = (Float) keyframe.endValue;
            float linearCurrentKeyframeProgress = getLinearCurrentKeyframeProgress();
            float f3 = this.progress;
            Float f4 = (Float) lottieValueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), (Float) obj, f2, f, linearCurrentKeyframeProgress, f3);
            if (f4 != null) {
                return f4.floatValue();
            }
        }
        if (keyframe.startValueFloat == -3987645.8f) {
            keyframe.startValueFloat = ((Float) obj).floatValue();
        }
        float f5 = keyframe.startValueFloat;
        if (keyframe.endValueFloat == -3987645.8f) {
            keyframe.endValueFloat = ((Float) keyframe.endValue).floatValue();
        }
        return MiscUtils.lerp(f5, keyframe.endValueFloat, f);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public final Object getValue(Keyframe keyframe, float f) {
        return Float.valueOf(getFloatValue(keyframe, f));
    }

    public final float getFloatValue() {
        return getFloatValue(this.keyframesWrapper.getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }
}
