package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GradientColorParser implements ValueParser {
    public int colorPoints;

    @Override // com.airbnb.lottie.parser.ValueParser
    public final Object parse(JsonReader jsonReader, float f) {
        int i;
        int i2;
        int argb;
        int argb2;
        float lerp;
        ArrayList arrayList = new ArrayList();
        int i3 = 1;
        boolean z = jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.beginArray();
        }
        while (jsonReader.hasNext()) {
            arrayList.add(Float.valueOf((float) jsonReader.nextDouble()));
        }
        int i4 = 2;
        int i5 = 4;
        if (arrayList.size() == 4 && ((Float) arrayList.get(0)).floatValue() == 1.0f) {
            arrayList.set(0, Float.valueOf(0.0f));
            arrayList.add(Float.valueOf(1.0f));
            arrayList.add((Float) arrayList.get(1));
            arrayList.add((Float) arrayList.get(2));
            arrayList.add((Float) arrayList.get(3));
            this.colorPoints = 2;
        }
        if (z) {
            jsonReader.endArray();
        }
        if (this.colorPoints == -1) {
            this.colorPoints = arrayList.size() / 4;
        }
        int i6 = this.colorPoints;
        float[] fArr = new float[i6];
        int[] iArr = new int[i6];
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            i = this.colorPoints * i5;
            if (i7 >= i) {
                break;
            }
            int i10 = i7 / 4;
            double floatValue = ((Float) arrayList.get(i7)).floatValue();
            int i11 = i7 % 4;
            if (i11 == 0) {
                if (i10 > 0) {
                    float f2 = (float) floatValue;
                    if (fArr[i10 - 1] >= f2) {
                        fArr[i10] = f2 + 0.01f;
                    }
                }
                fArr[i10] = (float) floatValue;
            } else if (i11 == i3) {
                i8 = (int) (floatValue * 255.0d);
            } else if (i11 == 2) {
                i9 = (int) (floatValue * 255.0d);
            } else if (i11 == 3) {
                iArr[i10] = Color.argb(255, i8, i9, (int) (floatValue * 255.0d));
            }
            i7++;
            i3 = 1;
            i5 = 4;
        }
        GradientColor gradientColor = new GradientColor(fArr, iArr);
        if (arrayList.size() <= i) {
            return gradientColor;
        }
        int size = (arrayList.size() - i) / 2;
        float[] fArr2 = new float[size];
        float[] fArr3 = new float[size];
        int i12 = 0;
        while (i < arrayList.size()) {
            if (i % 2 == 0) {
                fArr2[i12] = ((Float) arrayList.get(i)).floatValue();
            } else {
                fArr3[i12] = ((Float) arrayList.get(i)).floatValue();
                i12++;
            }
            i++;
        }
        float[] fArr4 = gradientColor.positions;
        if (fArr4.length == 0) {
            fArr4 = fArr2;
        } else if (size != 0) {
            int length = fArr4.length + size;
            float[] fArr5 = new float[length];
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            for (int i16 = 0; i16 < length; i16++) {
                float f3 = i14 < fArr4.length ? fArr4[i14] : Float.NaN;
                float f4 = i15 < size ? fArr2[i15] : Float.NaN;
                if (Float.isNaN(f4) || f3 < f4) {
                    fArr5[i16] = f3;
                    i14++;
                } else if (Float.isNaN(f3) || f4 < f3) {
                    fArr5[i16] = f4;
                    i15++;
                } else {
                    fArr5[i16] = f3;
                    i14++;
                    i15++;
                    i13++;
                }
            }
            fArr4 = i13 == 0 ? fArr5 : Arrays.copyOf(fArr5, length - i13);
        }
        int length2 = fArr4.length;
        int[] iArr2 = new int[length2];
        int i17 = 0;
        while (i17 < length2) {
            float f5 = fArr4[i17];
            int binarySearch = Arrays.binarySearch(fArr, f5);
            int binarySearch2 = Arrays.binarySearch(fArr2, f5);
            int[] iArr3 = gradientColor.colors;
            if (binarySearch < 0 || binarySearch2 > 0) {
                if (binarySearch2 < 0) {
                    binarySearch2 = -(binarySearch2 + 1);
                }
                float f6 = fArr3[binarySearch2];
                if (iArr3.length >= 2 && f5 != fArr[0]) {
                    for (int i18 = 1; i18 < i6; i18++) {
                        float f7 = fArr[i18];
                        if (f7 >= f5 || i18 == i6 - 1) {
                            if (i18 != i6 - 1 || f5 < f7) {
                                int i19 = i18 - 1;
                                float f8 = fArr[i19];
                                int evaluate = GammaEvaluator.evaluate(iArr3[i19], (f5 - f8) / (f7 - f8), iArr3[i18]);
                                argb = Color.argb((int) (f6 * 255.0f), Color.red(evaluate), Color.green(evaluate), Color.blue(evaluate));
                            } else {
                                argb = Color.argb((int) (f6 * 255.0f), Color.red(iArr3[i18]), Color.green(iArr3[i18]), Color.blue(iArr3[i18]));
                            }
                            i2 = argb;
                        }
                    }
                    throw new IllegalArgumentException("Unreachable code.");
                }
                i2 = iArr3[0];
                iArr2[i17] = i2;
            } else {
                int i20 = iArr3[binarySearch];
                if (size >= i4 && f5 > fArr2[0]) {
                    for (int i21 = 1; i21 < size; i21++) {
                        float f9 = fArr2[i21];
                        if (f9 >= f5 || i21 == size - 1) {
                            if (f9 <= f5) {
                                lerp = fArr3[i21];
                            } else {
                                int i22 = i21 - 1;
                                float f10 = fArr2[i22];
                                lerp = MiscUtils.lerp(fArr3[i22], fArr3[i21], (f5 - f10) / (f9 - f10));
                            }
                            argb2 = Color.argb((int) (lerp * 255.0f), Color.red(i20), Color.green(i20), Color.blue(i20));
                        }
                    }
                    throw new IllegalArgumentException("Unreachable code.");
                }
                argb2 = Color.argb((int) (fArr3[0] * 255.0f), Color.red(i20), Color.green(i20), Color.blue(i20));
                iArr2[i17] = argb2;
            }
            i17++;
            i4 = 2;
        }
        return new GradientColor(fArr4, iArr2);
    }
}
