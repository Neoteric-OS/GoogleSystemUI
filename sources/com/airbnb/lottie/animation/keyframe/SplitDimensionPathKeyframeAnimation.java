package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation {
    public final PointF point;
    public final PointF pointWithCallbackValues;
    public final FloatKeyframeAnimation xAnimation;
    public LottieValueCallback xValueCallback;
    public final FloatKeyframeAnimation yAnimation;
    public LottieValueCallback yValueCallback;

    public SplitDimensionPathKeyframeAnimation(FloatKeyframeAnimation floatKeyframeAnimation, FloatKeyframeAnimation floatKeyframeAnimation2) {
        super(Collections.emptyList());
        this.point = new PointF();
        this.pointWithCallbackValues = new PointF();
        this.xAnimation = floatKeyframeAnimation;
        this.yAnimation = floatKeyframeAnimation2;
        setProgress(this.progress);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public final /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f) {
        return getValue(f);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public final void setProgress(float f) {
        FloatKeyframeAnimation floatKeyframeAnimation = this.xAnimation;
        floatKeyframeAnimation.setProgress(f);
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.yAnimation;
        floatKeyframeAnimation2.setProgress(f);
        this.point.set(((Float) floatKeyframeAnimation.getValue()).floatValue(), ((Float) floatKeyframeAnimation2.getValue()).floatValue());
        for (int i = 0; i < ((ArrayList) this.listeners).size(); i++) {
            ((BaseKeyframeAnimation.AnimationListener) ((ArrayList) this.listeners).get(i)).onValueChanged();
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public final Object getValue() {
        return getValue(0.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.PointF getValue(float r13) {
        /*
            r12 = this;
            com.airbnb.lottie.value.LottieValueCallback r0 = r12.xValueCallback
            r1 = 0
            if (r0 == 0) goto L35
            com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation r0 = r12.xAnimation
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation$KeyframesWrapper r2 = r0.keyframesWrapper
            com.airbnb.lottie.value.Keyframe r2 = r2.getCurrentKeyframe()
            if (r2 == 0) goto L35
            float r10 = r0.getInterpolatedCurrentKeyframeProgress()
            java.lang.Float r0 = r2.endFrame
            com.airbnb.lottie.value.LottieValueCallback r3 = r12.xValueCallback
            float r4 = r2.startFrame
            if (r0 != 0) goto L1d
            r5 = r4
            goto L22
        L1d:
            float r0 = r0.floatValue()
            r5 = r0
        L22:
            java.lang.Object r0 = r2.startValue
            r6 = r0
            java.lang.Float r6 = (java.lang.Float) r6
            java.lang.Object r0 = r2.endValue
            r7 = r0
            java.lang.Float r7 = (java.lang.Float) r7
            r8 = r13
            r9 = r13
            java.lang.Object r0 = r3.getValueInternal(r4, r5, r6, r7, r8, r9, r10)
            java.lang.Float r0 = (java.lang.Float) r0
            goto L36
        L35:
            r0 = r1
        L36:
            com.airbnb.lottie.value.LottieValueCallback r2 = r12.yValueCallback
            if (r2 == 0) goto L6a
            com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation r2 = r12.yAnimation
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation$KeyframesWrapper r3 = r2.keyframesWrapper
            com.airbnb.lottie.value.Keyframe r3 = r3.getCurrentKeyframe()
            if (r3 == 0) goto L6a
            float r11 = r2.getInterpolatedCurrentKeyframeProgress()
            java.lang.Float r1 = r3.endFrame
            com.airbnb.lottie.value.LottieValueCallback r4 = r12.yValueCallback
            float r5 = r3.startFrame
            if (r1 != 0) goto L52
            r6 = r5
            goto L57
        L52:
            float r1 = r1.floatValue()
            r6 = r1
        L57:
            java.lang.Object r1 = r3.startValue
            r7 = r1
            java.lang.Float r7 = (java.lang.Float) r7
            java.lang.Object r1 = r3.endValue
            r8 = r1
            java.lang.Float r8 = (java.lang.Float) r8
            r9 = r13
            r10 = r13
            java.lang.Object r13 = r4.getValueInternal(r5, r6, r7, r8, r9, r10, r11)
            r1 = r13
            java.lang.Float r1 = (java.lang.Float) r1
        L6a:
            r13 = 0
            if (r0 != 0) goto L77
            android.graphics.PointF r0 = r12.pointWithCallbackValues
            android.graphics.PointF r2 = r12.point
            float r2 = r2.x
            r0.set(r2, r13)
            goto L80
        L77:
            android.graphics.PointF r2 = r12.pointWithCallbackValues
            float r0 = r0.floatValue()
            r2.set(r0, r13)
        L80:
            if (r1 != 0) goto L8e
            android.graphics.PointF r13 = r12.pointWithCallbackValues
            float r0 = r13.x
            android.graphics.PointF r1 = r12.point
            float r1 = r1.y
            r13.set(r0, r1)
            goto L99
        L8e:
            android.graphics.PointF r13 = r12.pointWithCallbackValues
            float r0 = r13.x
            float r1 = r1.floatValue()
            r13.set(r0, r1)
        L99:
            android.graphics.PointF r12 = r12.pointWithCallbackValues
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.keyframe.SplitDimensionPathKeyframeAnimation.getValue(float):android.graphics.PointF");
    }
}
