package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PathKeyframeAnimation extends KeyframeAnimation {
    public final PathMeasure pathMeasure;
    public PathKeyframe pathMeasureKeyframe;
    public final PointF point;
    public final float[] pos;
    public final float[] tangent;

    public PathKeyframeAnimation(List list) {
        super(list);
        this.point = new PointF();
        this.pos = new float[2];
        this.tangent = new float[2];
        this.pathMeasure = new PathMeasure();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public final Object getValue(Keyframe keyframe, float f) {
        PathKeyframe pathKeyframe = (PathKeyframe) keyframe;
        Path path = pathKeyframe.path;
        if (path == null) {
            return (PointF) keyframe.startValue;
        }
        LottieValueCallback lottieValueCallback = this.valueCallback;
        if (lottieValueCallback != null) {
            PointF pointF = (PointF) lottieValueCallback.getValueInternal(pathKeyframe.startFrame, pathKeyframe.endFrame.floatValue(), (PointF) pathKeyframe.startValue, (PointF) pathKeyframe.endValue, getLinearCurrentKeyframeProgress(), f, this.progress);
            if (pointF != null) {
                return pointF;
            }
        }
        if (this.pathMeasureKeyframe != pathKeyframe) {
            this.pathMeasure.setPath(path, false);
            this.pathMeasureKeyframe = pathKeyframe;
        }
        float length = this.pathMeasure.getLength();
        float f2 = f * length;
        PathMeasure pathMeasure = this.pathMeasure;
        float[] fArr = this.pos;
        float[] fArr2 = this.tangent;
        pathMeasure.getPosTan(f2, fArr, fArr2);
        this.point.set(fArr[0], fArr[1]);
        if (f2 < 0.0f) {
            this.point.offset(fArr2[0] * f2, fArr2[1] * f2);
        } else if (f2 > length) {
            float f3 = f2 - length;
            this.point.offset(fArr2[0] * f3, fArr2[1] * f3);
        }
        return this.point;
    }
}
