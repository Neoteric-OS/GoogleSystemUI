package com.airbnb.lottie.value;

import com.airbnb.lottie.SimpleColorFilter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class LottieValueCallback {
    public final LottieFrameInfo frameInfo;
    public final SimpleColorFilter value;

    public LottieValueCallback() {
        this.frameInfo = new LottieFrameInfo();
        this.value = null;
    }

    public Object getValue(LottieFrameInfo lottieFrameInfo) {
        return this.value;
    }

    public final Object getValueInternal(float f, float f2, Object obj, Object obj2, float f3, float f4, float f5) {
        LottieFrameInfo lottieFrameInfo = this.frameInfo;
        lottieFrameInfo.startFrame = f;
        lottieFrameInfo.endFrame = f2;
        lottieFrameInfo.startValue = obj;
        lottieFrameInfo.endValue = obj2;
        lottieFrameInfo.linearKeyframeProgress = f3;
        lottieFrameInfo.interpolatedKeyframeProgress = f4;
        lottieFrameInfo.overallProgress = f5;
        return getValue(lottieFrameInfo);
    }

    public LottieValueCallback(SimpleColorFilter simpleColorFilter) {
        this.frameInfo = new LottieFrameInfo();
        this.value = simpleColorFilter;
    }
}
