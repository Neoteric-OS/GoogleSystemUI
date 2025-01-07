package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.parser.moshi.JsonUtf8Reader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnimatableValueParser {
    public static AnimatableColorValue parseColor(JsonUtf8Reader jsonUtf8Reader, LottieComposition lottieComposition) {
        return new AnimatableColorValue(KeyframesParser.parse(jsonUtf8Reader, lottieComposition, 1.0f, ColorParser.INSTANCE, false));
    }

    public static AnimatableFloatValue parseFloat(JsonReader jsonReader, LottieComposition lottieComposition, boolean z) {
        return new AnimatableFloatValue(KeyframesParser.parse(jsonReader, lottieComposition, z ? Utils.dpScale() : 1.0f, FloatParser.INSTANCE, false));
    }

    public static AnimatableGradientColorValue parseGradientColor(JsonUtf8Reader jsonUtf8Reader, LottieComposition lottieComposition, int i) {
        GradientColorParser gradientColorParser = new GradientColorParser();
        gradientColorParser.colorPoints = i;
        List parse = KeyframesParser.parse(jsonUtf8Reader, lottieComposition, 1.0f, gradientColorParser, false);
        int i2 = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) parse;
            if (i2 >= arrayList.size()) {
                return new AnimatableGradientColorValue(parse);
            }
            Keyframe keyframe = (Keyframe) arrayList.get(i2);
            GradientColor gradientColor = (GradientColor) keyframe.startValue;
            GradientColor gradientColor2 = (GradientColor) keyframe.endValue;
            if (gradientColor != null && gradientColor2 != null) {
                float[] fArr = gradientColor.positions;
                int length = fArr.length;
                float[] fArr2 = gradientColor2.positions;
                if (length != fArr2.length) {
                    int length2 = fArr.length + fArr2.length;
                    float[] fArr3 = new float[length2];
                    System.arraycopy(fArr, 0, fArr3, 0, fArr.length);
                    System.arraycopy(fArr2, 0, fArr3, fArr.length, fArr2.length);
                    Arrays.sort(fArr3);
                    float f = Float.NaN;
                    int i3 = 0;
                    for (int i4 = 0; i4 < length2; i4++) {
                        float f2 = fArr3[i4];
                        if (f2 != f) {
                            fArr3[i3] = f2;
                            i3++;
                            f = fArr3[i4];
                        }
                    }
                    float[] copyOfRange = Arrays.copyOfRange(fArr3, 0, i3);
                    keyframe = new Keyframe(gradientColor.copyWithPositions(copyOfRange), gradientColor2.copyWithPositions(copyOfRange));
                }
            }
            parse.set(i2, keyframe);
            i2++;
        }
    }

    public static AnimatableIntegerValue parseInteger(JsonUtf8Reader jsonUtf8Reader, LottieComposition lottieComposition) {
        return new AnimatableIntegerValue(KeyframesParser.parse(jsonUtf8Reader, lottieComposition, 1.0f, IntegerParser.INSTANCE, false));
    }

    public static AnimatablePointValue parsePoint(JsonUtf8Reader jsonUtf8Reader, LottieComposition lottieComposition) {
        return new AnimatablePointValue(KeyframesParser.parse(jsonUtf8Reader, lottieComposition, Utils.dpScale(), PointFParser.INSTANCE, true));
    }
}
