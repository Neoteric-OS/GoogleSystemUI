package com.google.ux.material.libmonet.dynamiccolor;

import com.google.ux.material.libmonet.contrast.Contrast;
import com.google.ux.material.libmonet.hct.Hct;
import com.google.ux.material.libmonet.palettes.TonalPalette;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DynamicColor {
    public final Function background;
    public final ContrastCurve contrastCurve;
    public final HashMap hctCache;
    public final boolean isBackground;
    public final String name;
    public final MaterialDynamicColors$$ExternalSyntheticLambda0 opacity;
    public final Function palette;
    public final Function secondBackground;
    public final Function tone;
    public final Function toneDeltaPair;

    public DynamicColor(String str, Function function, Function function2, boolean z, Function function3, Function function4, ContrastCurve contrastCurve, Function function5) {
        this.hctCache = new HashMap();
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve;
        this.toneDeltaPair = function5;
        this.opacity = null;
    }

    public static double foregroundTone(double d, double d2) {
        double lighter = Contrast.lighter(d, d2);
        if (lighter < 0.0d) {
            lighter = 100.0d;
        }
        double max = Math.max(0.0d, Contrast.darker(d, d2));
        double ratioOfTones = Contrast.ratioOfTones(lighter, d);
        double ratioOfTones2 = Contrast.ratioOfTones(max, d);
        if (Math.round(d) < 60) {
            return (ratioOfTones >= d2 || ratioOfTones >= ratioOfTones2 || ((Math.abs(ratioOfTones - ratioOfTones2) > 0.1d ? 1 : (Math.abs(ratioOfTones - ratioOfTones2) == 0.1d ? 0 : -1)) < 0 && (ratioOfTones > d2 ? 1 : (ratioOfTones == d2 ? 0 : -1)) < 0 && (ratioOfTones2 > d2 ? 1 : (ratioOfTones2 == d2 ? 0 : -1)) < 0)) ? lighter : max;
        }
        return (ratioOfTones2 >= d2 || ratioOfTones2 >= ratioOfTones) ? max : lighter;
    }

    public static DynamicColor fromPalette(String str, Function function, Function function2) {
        return new DynamicColor(str, function, function2, false, null, null, null, null);
    }

    public final int getArgb(DynamicScheme dynamicScheme) {
        Hct hct = (Hct) this.hctCache.get(dynamicScheme);
        if (hct == null) {
            double tone = getTone(dynamicScheme);
            TonalPalette tonalPalette = (TonalPalette) this.palette.apply(dynamicScheme);
            hct = Hct.from(tonalPalette.hue, tonalPalette.chroma, tone);
            if (this.hctCache.size() > 4) {
                this.hctCache.clear();
            }
            this.hctCache.put(dynamicScheme, hct);
        }
        int i = hct.argb;
        MaterialDynamicColors$$ExternalSyntheticLambda0 materialDynamicColors$$ExternalSyntheticLambda0 = this.opacity;
        if (materialDynamicColors$$ExternalSyntheticLambda0 == null) {
            return i;
        }
        int round = (int) Math.round(((Double) materialDynamicColors$$ExternalSyntheticLambda0.apply(dynamicScheme)).doubleValue() * 255.0d);
        if (round < 0) {
            round = 0;
        } else if (round > 255) {
            round = 255;
        }
        return (round << 24) | (16777215 & i);
    }

    public final double getTone(DynamicScheme dynamicScheme) {
        double d;
        double min;
        double d2 = dynamicScheme.contrastLevel;
        boolean z = d2 < 0.0d;
        Function function = this.toneDeltaPair;
        if (function == null) {
            double doubleValue = ((Double) this.tone.apply(dynamicScheme)).doubleValue();
            Function function2 = this.background;
            if (function2 == null) {
                return doubleValue;
            }
            double tone = ((DynamicColor) function2.apply(dynamicScheme)).getTone(dynamicScheme);
            double d3 = this.contrastCurve.get(d2);
            if (Contrast.ratioOfTones(tone, doubleValue) < d3) {
                doubleValue = foregroundTone(tone, d3);
            }
            if (z) {
                doubleValue = foregroundTone(tone, d3);
            }
            if (this.isBackground && 50.0d <= doubleValue && doubleValue < 60.0d) {
                doubleValue = Contrast.ratioOfTones(49.0d, tone) >= d3 ? 49.0d : 60.0d;
            }
            if (this.secondBackground == null) {
                return doubleValue;
            }
            double tone2 = ((DynamicColor) this.background.apply(dynamicScheme)).getTone(dynamicScheme);
            double tone3 = ((DynamicColor) this.secondBackground.apply(dynamicScheme)).getTone(dynamicScheme);
            double max = Math.max(tone2, tone3);
            double min2 = Math.min(tone2, tone3);
            if (Contrast.ratioOfTones(max, doubleValue) >= d3 && Contrast.ratioOfTones(min2, doubleValue) >= d3) {
                return doubleValue;
            }
            double lighter = Contrast.lighter(max, d3);
            double darker = Contrast.darker(min2, d3);
            ArrayList arrayList = new ArrayList();
            if (lighter != -1.0d) {
                arrayList.add(Double.valueOf(lighter));
            }
            if (darker != -1.0d) {
                arrayList.add(Double.valueOf(darker));
            }
            if (Math.round(tone2) < 60 || Math.round(tone3) < 60) {
                if (lighter == -1.0d) {
                    return 100.0d;
                }
                return lighter;
            }
            if (arrayList.size() == 1) {
                return ((Double) arrayList.get(0)).doubleValue();
            }
            if (darker == -1.0d) {
                return 0.0d;
            }
            return darker;
        }
        ToneDeltaPair toneDeltaPair = (ToneDeltaPair) function.apply(dynamicScheme);
        DynamicColor dynamicColor = toneDeltaPair.roleA;
        double tone4 = ((DynamicColor) this.background.apply(dynamicScheme)).getTone(dynamicScheme);
        TonePolarity tonePolarity = TonePolarity.NEARER;
        TonePolarity tonePolarity2 = toneDeltaPair.polarity;
        boolean z2 = dynamicScheme.isDark;
        boolean z3 = tonePolarity2 == tonePolarity || (tonePolarity2 == TonePolarity.LIGHTER && !z2) || (tonePolarity2 == TonePolarity.DARKER && z2);
        DynamicColor dynamicColor2 = toneDeltaPair.roleB;
        DynamicColor dynamicColor3 = z3 ? dynamicColor : dynamicColor2;
        if (z3) {
            dynamicColor = dynamicColor2;
        }
        boolean equals = this.name.equals(dynamicColor3.name);
        double d4 = z2 ? 1.0d : -1.0d;
        double d5 = dynamicColor3.contrastCurve.get(d2);
        double d6 = dynamicColor.contrastCurve.get(d2);
        double doubleValue2 = ((Double) dynamicColor3.tone.apply(dynamicScheme)).doubleValue();
        if (Contrast.ratioOfTones(tone4, doubleValue2) < d5) {
            doubleValue2 = foregroundTone(tone4, d5);
        }
        double doubleValue3 = ((Double) dynamicColor.tone.apply(dynamicScheme)).doubleValue();
        if (Contrast.ratioOfTones(tone4, doubleValue3) < d6) {
            doubleValue3 = foregroundTone(tone4, d6);
        }
        if (z) {
            doubleValue2 = foregroundTone(tone4, d5);
            doubleValue3 = foregroundTone(tone4, d6);
        }
        double d7 = (doubleValue3 - doubleValue2) * d4;
        double d8 = toneDeltaPair.delta;
        if (d7 < d8) {
            double d9 = d8 * d4;
            double d10 = doubleValue2 + d9;
            doubleValue3 = d10 < 0.0d ? 0.0d : d10 > 100.0d ? 100.0d : d10;
            if ((doubleValue3 - doubleValue2) * d4 < d8) {
                double d11 = doubleValue3 - d9;
                if (d11 < 0.0d) {
                    doubleValue2 = 0.0d;
                } else {
                    doubleValue2 = 100.0d;
                    if (d11 <= 100.0d) {
                        doubleValue2 = d11;
                    }
                }
            }
        }
        if (50.0d > doubleValue2 || doubleValue2 >= 60.0d) {
            if (50.0d > doubleValue3 || doubleValue3 >= 60.0d) {
                d = doubleValue3;
            } else if (!toneDeltaPair.stayTogether) {
                d = d4 > 0.0d ? 60.0d : 49.0d;
            } else if (d4 > 0.0d) {
                d = Math.max(doubleValue3, (d8 * d4) + 60.0d);
                doubleValue2 = 60.0d;
            } else {
                min = Math.min(doubleValue3, (d8 * d4) + 49.0d);
                d = min;
                doubleValue2 = 49.0d;
            }
        } else if (d4 > 0.0d) {
            d = Math.max(doubleValue3, (d8 * d4) + 60.0d);
            doubleValue2 = 60.0d;
        } else {
            min = Math.min(doubleValue3, (d8 * d4) + 49.0d);
            d = min;
            doubleValue2 = 49.0d;
        }
        return equals ? doubleValue2 : d;
    }

    public DynamicColor(MaterialDynamicColors$$ExternalSyntheticLambda0 materialDynamicColors$$ExternalSyntheticLambda0, MaterialDynamicColors$$ExternalSyntheticLambda0 materialDynamicColors$$ExternalSyntheticLambda02, MaterialDynamicColors$$ExternalSyntheticLambda0 materialDynamicColors$$ExternalSyntheticLambda03) {
        this.hctCache = new HashMap();
        this.name = "control_highlight";
        this.palette = materialDynamicColors$$ExternalSyntheticLambda0;
        this.tone = materialDynamicColors$$ExternalSyntheticLambda02;
        this.isBackground = false;
        this.background = null;
        this.secondBackground = null;
        this.contrastCurve = null;
        this.toneDeltaPair = null;
        this.opacity = materialDynamicColors$$ExternalSyntheticLambda03;
    }
}
