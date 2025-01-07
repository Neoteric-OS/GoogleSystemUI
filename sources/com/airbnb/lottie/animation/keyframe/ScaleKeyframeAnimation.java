package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScaleKeyframeAnimation extends KeyframeAnimation {
    public final ScaleXY scaleXY;

    public ScaleKeyframeAnimation(List list) {
        super(list);
        this.scaleXY = new ScaleXY();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public final Object getValue(Keyframe keyframe, float f) {
        Object obj;
        Object obj2 = keyframe.startValue;
        if (obj2 == null || (obj = keyframe.endValue) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        ScaleXY scaleXY = (ScaleXY) obj2;
        ScaleXY scaleXY2 = (ScaleXY) obj;
        LottieValueCallback lottieValueCallback = this.valueCallback;
        if (lottieValueCallback != null) {
            ScaleXY scaleXY3 = (ScaleXY) lottieValueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), scaleXY, scaleXY2, f, getLinearCurrentKeyframeProgress(), this.progress);
            if (scaleXY3 != null) {
                return scaleXY3;
            }
        }
        float lerp = MiscUtils.lerp(scaleXY.scaleX, scaleXY2.scaleX, f);
        float lerp2 = MiscUtils.lerp(scaleXY.scaleY, scaleXY2.scaleY, f);
        ScaleXY scaleXY4 = this.scaleXY;
        scaleXY4.scaleX = lerp;
        scaleXY4.scaleY = lerp2;
        return scaleXY4;
    }
}
