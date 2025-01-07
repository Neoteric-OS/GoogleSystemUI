package com.airbnb.lottie.animation.keyframe;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GradientColorKeyframeAnimation extends KeyframeAnimation {
    public final GradientColor gradientColor;

    public GradientColorKeyframeAnimation(List list) {
        super(list);
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            GradientColor gradientColor = (GradientColor) ((Keyframe) list.get(i2)).startValue;
            if (gradientColor != null) {
                i = Math.max(i, gradientColor.colors.length);
            }
        }
        this.gradientColor = new GradientColor(new float[i], new int[i]);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public final Object getValue(Keyframe keyframe, float f) {
        int[] iArr;
        float[] fArr;
        GradientColor gradientColor = (GradientColor) keyframe.startValue;
        GradientColor gradientColor2 = (GradientColor) keyframe.endValue;
        GradientColor gradientColor3 = this.gradientColor;
        gradientColor3.getClass();
        if (gradientColor.equals(gradientColor2)) {
            gradientColor3.copyFrom(gradientColor);
        } else if (f <= 0.0f) {
            gradientColor3.copyFrom(gradientColor);
        } else if (f >= 1.0f) {
            gradientColor3.copyFrom(gradientColor2);
        } else {
            int[] iArr2 = gradientColor.colors;
            int length = iArr2.length;
            int[] iArr3 = gradientColor2.colors;
            if (length != iArr3.length) {
                StringBuilder sb = new StringBuilder("Cannot interpolate between gradients. Lengths vary (");
                sb.append(iArr2.length);
                sb.append(" vs ");
                throw new IllegalArgumentException(PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, iArr3.length, ")"));
            }
            int i = 0;
            while (true) {
                int length2 = iArr2.length;
                iArr = gradientColor3.colors;
                fArr = gradientColor3.positions;
                if (i >= length2) {
                    break;
                }
                fArr[i] = MiscUtils.lerp(gradientColor.positions[i], gradientColor2.positions[i], f);
                iArr[i] = GammaEvaluator.evaluate(iArr2[i], f, iArr3[i]);
                i++;
            }
            for (int length3 = iArr2.length; length3 < fArr.length; length3++) {
                fArr[length3] = fArr[iArr2.length - 1];
                iArr[length3] = iArr[iArr2.length - 1];
            }
        }
        return gradientColor3;
    }
}
