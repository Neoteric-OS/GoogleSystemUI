package com.google.ux.material.libmonet.temperature;

import com.google.ux.material.libmonet.hct.Hct;
import com.google.ux.material.libmonet.utils.ColorUtils;
import com.google.ux.material.libmonet.utils.MathUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TemperatureCache {
    public final Hct input;
    public List precomputedHctsByHue;
    public List precomputedHctsByTemp;
    public Map precomputedTempsByHct;

    public TemperatureCache(Hct hct) {
        this.input = hct;
    }

    public final List getHctsByHue() {
        List list = this.precomputedHctsByHue;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (double d = 0.0d; d <= 360.0d; d += 1.0d) {
            Hct hct = this.input;
            arrayList.add(Hct.from(d, hct.chroma, hct.tone));
        }
        List unmodifiableList = Collections.unmodifiableList(arrayList);
        this.precomputedHctsByHue = unmodifiableList;
        return unmodifiableList;
    }

    public final List getHctsByTemp() {
        List list = this.precomputedHctsByTemp;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList(getHctsByHue());
        arrayList.add(this.input);
        Collections.sort(arrayList, Comparator.comparing(new Function() { // from class: com.google.ux.material.libmonet.temperature.TemperatureCache$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (Double) TemperatureCache.this.getTempsByHct().get((Hct) obj);
            }
        }, new TemperatureCache$$ExternalSyntheticLambda1()));
        this.precomputedHctsByTemp = arrayList;
        return arrayList;
    }

    public final double getRelativeTemperature(Hct hct) {
        double doubleValue = ((Double) getTempsByHct().get((Hct) ((ArrayList) getHctsByTemp()).get(((ArrayList) getHctsByTemp()).size() - 1))).doubleValue() - ((Double) getTempsByHct().get((Hct) ((ArrayList) getHctsByTemp()).get(0))).doubleValue();
        double doubleValue2 = ((Double) getTempsByHct().get(hct)).doubleValue() - ((Double) getTempsByHct().get((Hct) ((ArrayList) getHctsByTemp()).get(0))).doubleValue();
        if (doubleValue == 0.0d) {
            return 0.5d;
        }
        return doubleValue2 / doubleValue;
    }

    public final Map getTempsByHct() {
        Map map = this.precomputedTempsByHct;
        if (map != null) {
            return map;
        }
        ArrayList<Hct> arrayList = new ArrayList(getHctsByHue());
        arrayList.add(this.input);
        HashMap hashMap = new HashMap();
        for (Hct hct : arrayList) {
            int i = hct.argb;
            double linearized = ColorUtils.linearized((i >> 16) & 255);
            double linearized2 = ColorUtils.linearized((i >> 8) & 255);
            double linearized3 = ColorUtils.linearized(i & 255);
            double[][] dArr = ColorUtils.SRGB_TO_XYZ;
            double[] dArr2 = dArr[0];
            double d = (dArr2[2] * linearized3) + (dArr2[1] * linearized2) + (dArr2[0] * linearized);
            double[] dArr3 = dArr[1];
            double d2 = (dArr3[2] * linearized3) + (dArr3[1] * linearized2) + (dArr3[0] * linearized);
            double[] dArr4 = dArr[2];
            double d3 = (dArr4[2] * linearized3) + (dArr4[1] * linearized2) + (dArr4[0] * linearized);
            double[] dArr5 = ColorUtils.WHITE_POINT_D65;
            double d4 = d / dArr5[0];
            double d5 = d2 / dArr5[1];
            double d6 = d3 / dArr5[2];
            double labF = ColorUtils.labF(d4);
            double labF2 = ColorUtils.labF(d5);
            double[] dArr6 = {(116.0d * labF2) - 16.0d, (labF - labF2) * 500.0d, (labF2 - ColorUtils.labF(d6)) * 200.0d};
            hashMap.put(hct, Double.valueOf((Math.cos(Math.toRadians(MathUtils.sanitizeDegreesDouble(MathUtils.sanitizeDegreesDouble(Math.toDegrees(Math.atan2(dArr6[2], dArr6[1]))) - 50.0d))) * (Math.pow(Math.hypot(dArr6[1], dArr6[2]), 1.07d) * 0.02d)) - 0.5d));
        }
        this.precomputedTempsByHct = hashMap;
        return hashMap;
    }
}
