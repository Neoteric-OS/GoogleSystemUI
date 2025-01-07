package com.google.ux.material.libmonet.dynamiccolor;

import com.android.app.viewcapture.data.ViewNode;
import com.google.ux.material.libmonet.utils.MathUtils;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class MaterialDynamicColors$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        double d;
        double d2;
        double lerp;
        double lerp2;
        switch (this.$r8$classId) {
            case 0:
                return ((DynamicScheme) obj).neutralPalette;
            case 1:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 90.0d : 10.0d);
            case 2:
                return ((DynamicScheme) obj).primaryPalette;
            case 3:
                return ((DynamicScheme) obj).secondaryPalette;
            case 4:
                return Double.valueOf(MaterialDynamicColors.isMonochrome((DynamicScheme) obj) ? 80.0d : 90.0d);
            case 5:
                return ((DynamicScheme) obj).neutralPalette;
            case 6:
                DynamicScheme dynamicScheme = (DynamicScheme) obj;
                boolean z = dynamicScheme.isDark;
                double d3 = dynamicScheme.contrastLevel;
                if (z) {
                    d = 12.0d;
                    if (d3 > -1.0d) {
                        if (d3 < 0.0d) {
                            d = MathUtils.lerp(12.0d, 12.0d, (d3 - (-1.0d)) / 1.0d);
                        } else if (d3 < 0.5d) {
                            d = MathUtils.lerp(12.0d, 16.0d, (d3 - 0.0d) / 0.5d);
                        } else {
                            d2 = 20.0d;
                            if (d3 < 1.0d) {
                                d = MathUtils.lerp(16.0d, 20.0d, (d3 - 0.5d) / 0.5d);
                            }
                            d = d2;
                        }
                    }
                    return Double.valueOf(d);
                }
                d = 94.0d;
                if (d3 > -1.0d) {
                    if (d3 < 0.0d) {
                        lerp = MathUtils.lerp(94.0d, 94.0d, (d3 - (-1.0d)) / 1.0d);
                    } else if (d3 < 0.5d) {
                        lerp = MathUtils.lerp(94.0d, 92.0d, (d3 - 0.0d) / 0.5d);
                    } else {
                        d2 = 90.0d;
                        if (d3 < 1.0d) {
                            lerp = MathUtils.lerp(92.0d, 90.0d, (d3 - 0.5d) / 0.5d);
                        }
                        d = d2;
                    }
                    d = lerp;
                }
                return Double.valueOf(d);
            case 7:
                return ((DynamicScheme) obj).neutralVariantPalette;
            case 8:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 80.0d : 30.0d);
            case 9:
                return ((DynamicScheme) obj).primaryPalette;
            case 10:
                return Double.valueOf(MaterialDynamicColors.isMonochrome((DynamicScheme) obj) ? 30.0d : 80.0d);
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return ((DynamicScheme) obj).neutralVariantPalette;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return Double.valueOf(((DynamicScheme) obj).neutralVariantPalette.keyColor.tone);
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return ((DynamicScheme) obj).neutralPalette;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 100.0d : 0.0d);
            case 15:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 0.2d : 0.12d);
            case 16:
                return ((DynamicScheme) obj).errorPalette;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 80.0d : 40.0d);
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                return ((DynamicScheme) obj).tertiaryPalette;
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                DynamicScheme dynamicScheme2 = (DynamicScheme) obj;
                boolean isMonochrome = MaterialDynamicColors.isMonochrome(dynamicScheme2);
                boolean z2 = dynamicScheme2.isDark;
                if (isMonochrome) {
                    return Double.valueOf(z2 ? 90.0d : 25.0d);
                }
                return Double.valueOf(z2 ? 80.0d : 40.0d);
            case 20:
                return ((DynamicScheme) obj).neutralPalette;
            case 21:
                DynamicScheme dynamicScheme3 = (DynamicScheme) obj;
                if (dynamicScheme3.isDark) {
                    lerp2 = 6.0d;
                } else {
                    double d4 = dynamicScheme3.contrastLevel;
                    lerp2 = d4 <= -1.0d ? 87.0d : d4 < 0.0d ? MathUtils.lerp(87.0d, 87.0d, (d4 - (-1.0d)) / 1.0d) : d4 < 0.5d ? MathUtils.lerp(87.0d, 80.0d, (d4 - 0.0d) / 0.5d) : d4 < 1.0d ? MathUtils.lerp(80.0d, 75.0d, (d4 - 0.5d) / 0.5d) : 75.0d;
                }
                return Double.valueOf(lerp2);
            case 22:
                return ((DynamicScheme) obj).tertiaryPalette;
            case 23:
                return ((DynamicScheme) obj).neutralPalette;
            case 24:
                return ((DynamicScheme) obj).errorPalette;
            case 25:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 20.0d : 100.0d);
            case 26:
                return ((DynamicScheme) obj).neutralVariantPalette;
            case 27:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 30.0d : 80.0d);
            case 28:
                return ((DynamicScheme) obj).primaryPalette;
            default:
                return ((DynamicScheme) obj).secondaryPalette;
        }
    }
}
