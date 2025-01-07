package com.airbnb.lottie.model.content;

import com.airbnb.lottie.utils.GammaEvaluator;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GradientColor {
    public final int[] colors;
    public final float[] positions;

    public GradientColor(float[] fArr, int[] iArr) {
        this.positions = fArr;
        this.colors = iArr;
    }

    public final void copyFrom(GradientColor gradientColor) {
        int i = 0;
        while (true) {
            int[] iArr = gradientColor.colors;
            if (i >= iArr.length) {
                return;
            }
            this.positions[i] = gradientColor.positions[i];
            this.colors[i] = iArr[i];
            i++;
        }
    }

    public final GradientColor copyWithPositions(float[] fArr) {
        int evaluate;
        int[] iArr = new int[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            float f = fArr[i];
            float[] fArr2 = this.positions;
            int binarySearch = Arrays.binarySearch(fArr2, f);
            int[] iArr2 = this.colors;
            if (binarySearch >= 0) {
                evaluate = iArr2[binarySearch];
            } else {
                int i2 = -(binarySearch + 1);
                if (i2 == 0) {
                    evaluate = iArr2[0];
                } else if (i2 == iArr2.length - 1) {
                    evaluate = iArr2[iArr2.length - 1];
                } else {
                    int i3 = i2 - 1;
                    float f2 = fArr2[i3];
                    evaluate = GammaEvaluator.evaluate(iArr2[i3], (f - f2) / (fArr2[i2] - f2), iArr2[i2]);
                }
            }
            iArr[i] = evaluate;
        }
        return new GradientColor(fArr, iArr);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GradientColor.class != obj.getClass()) {
            return false;
        }
        GradientColor gradientColor = (GradientColor) obj;
        return Arrays.equals(this.positions, gradientColor.positions) && Arrays.equals(this.colors, gradientColor.colors);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.colors) + (Arrays.hashCode(this.positions) * 31);
    }
}
