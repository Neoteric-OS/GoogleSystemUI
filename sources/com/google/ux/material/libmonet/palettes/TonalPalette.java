package com.google.ux.material.libmonet.palettes;

import com.google.ux.material.libmonet.hct.Hct;
import com.google.ux.material.libmonet.palettes.TonalPalette;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TonalPalette {
    public final Map cache = new HashMap();
    public final double chroma;
    public final double hue;
    public final Hct keyColor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeyColor {
        public final Map chromaCache = new HashMap();
        public final double hue;
        public final double requestedChroma;

        public KeyColor(double d, double d2) {
            this.hue = d;
            this.requestedChroma = d2;
        }

        public final double maxChroma(int i) {
            return ((Double) ((HashMap) this.chromaCache).computeIfAbsent(Integer.valueOf(i), new Function() { // from class: com.google.ux.material.libmonet.palettes.TonalPalette$KeyColor$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    TonalPalette.KeyColor keyColor = TonalPalette.KeyColor.this;
                    keyColor.getClass();
                    return Double.valueOf(Hct.from(keyColor.hue, 200.0d, ((Integer) obj).intValue()).chroma);
                }
            })).doubleValue();
        }
    }

    public TonalPalette(double d, double d2, Hct hct) {
        this.hue = d;
        this.chroma = d2;
        this.keyColor = hct;
    }

    public static TonalPalette fromHueAndChroma(double d, double d2) {
        Hct from;
        KeyColor keyColor = new KeyColor(d, d2);
        int i = 100;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                from = Hct.from(keyColor.hue, keyColor.requestedChroma, i2);
                break;
            }
            int i3 = (i2 + i) / 2;
            int i4 = i3 + 1;
            boolean z = keyColor.maxChroma(i3) < keyColor.maxChroma(i4);
            if (keyColor.maxChroma(i3) >= keyColor.requestedChroma - 0.01d) {
                if (Math.abs(i2 - 50) < Math.abs(i - 50)) {
                    i = i3;
                } else {
                    if (i2 == i3) {
                        from = Hct.from(keyColor.hue, keyColor.requestedChroma, i2);
                        break;
                    }
                    i2 = i3;
                }
            } else if (z) {
                i2 = i4;
            } else {
                i = i3;
            }
        }
        return new TonalPalette(d, d2, from);
    }
}
