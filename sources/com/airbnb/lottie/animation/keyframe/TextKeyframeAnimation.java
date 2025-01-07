package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextKeyframeAnimation extends KeyframeAnimation {
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public final Object getValue(Keyframe keyframe, float f) {
        Object obj;
        LottieValueCallback lottieValueCallback = this.valueCallback;
        Object obj2 = keyframe.startValue;
        if (lottieValueCallback == null) {
            return (f != 1.0f || (obj = keyframe.endValue) == null) ? (DocumentData) obj2 : (DocumentData) obj;
        }
        Float f2 = keyframe.endFrame;
        float floatValue = f2 == null ? Float.MAX_VALUE : f2.floatValue();
        DocumentData documentData = (DocumentData) obj2;
        Object obj3 = keyframe.endValue;
        return (DocumentData) lottieValueCallback.getValueInternal(keyframe.startFrame, floatValue, documentData, obj3 == null ? documentData : (DocumentData) obj3, f, getInterpolatedCurrentKeyframeProgress(), this.progress);
    }
}
