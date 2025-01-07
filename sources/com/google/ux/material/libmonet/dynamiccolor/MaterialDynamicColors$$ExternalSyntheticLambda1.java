package com.google.ux.material.libmonet.dynamiccolor;

import com.android.app.viewcapture.data.ViewNode;
import com.google.ux.material.libmonet.utils.MathUtils;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class MaterialDynamicColors$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        double d;
        double d2;
        double d3;
        double d4;
        double lerp;
        switch (this.$r8$classId) {
            case 0:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 10.0d : 90.0d);
            case 1:
                DynamicScheme dynamicScheme = (DynamicScheme) obj;
                if (dynamicScheme.isDark) {
                    double d5 = dynamicScheme.contrastLevel;
                    d = 4.0d;
                    if (d5 > -1.0d) {
                        d = d5 < 0.0d ? MathUtils.lerp(4.0d, 4.0d, (d5 - (-1.0d)) / 1.0d) : d5 < 0.5d ? MathUtils.lerp(4.0d, 2.0d, (d5 - 0.0d) / 0.5d) : d5 < 1.0d ? MathUtils.lerp(2.0d, 0.0d, (d5 - 0.5d) / 0.5d) : 0.0d;
                    }
                } else {
                    d = 100.0d;
                }
                return Double.valueOf(d);
            case 2:
                return ((DynamicScheme) obj).secondaryPalette;
            case 3:
                return Double.valueOf(MaterialDynamicColors.isMonochrome((DynamicScheme) obj) ? 70.0d : 80.0d);
            case 4:
                return ((DynamicScheme) obj).neutralVariantPalette;
            case 5:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 60.0d : 50.0d);
            case 6:
                return ((DynamicScheme) obj).neutralPalette;
            case 7:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 10.0d : 90.0d);
            case 8:
                return ((DynamicScheme) obj).neutralPalette;
            case 9:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 6.0d : 98.0d);
            case 10:
                return ((DynamicScheme) obj).tertiaryPalette;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return Double.valueOf(MaterialDynamicColors.isMonochrome((DynamicScheme) obj) ? 90.0d : 30.0d);
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return ((DynamicScheme) obj).secondaryPalette;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return Double.valueOf(((DynamicScheme) obj).secondaryPalette.keyColor.tone);
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                return ((DynamicScheme) obj).errorPalette;
            case 15:
                DynamicScheme dynamicScheme2 = (DynamicScheme) obj;
                boolean isMonochrome = MaterialDynamicColors.isMonochrome(dynamicScheme2);
                boolean z = dynamicScheme2.isDark;
                if (isMonochrome) {
                    return Double.valueOf(z ? 90.0d : 10.0d);
                }
                return Double.valueOf(z ? 90.0d : 30.0d);
            case 16:
                return ((DynamicScheme) obj).neutralPalette;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                DynamicScheme dynamicScheme3 = (DynamicScheme) obj;
                if (dynamicScheme3.isDark) {
                    double d6 = dynamicScheme3.contrastLevel;
                    d2 = 24.0d;
                    if (d6 > -1.0d) {
                        d2 = d6 < 0.0d ? MathUtils.lerp(24.0d, 24.0d, (d6 - (-1.0d)) / 1.0d) : d6 < 0.5d ? MathUtils.lerp(24.0d, 29.0d, (d6 - 0.0d) / 0.5d) : d6 < 1.0d ? MathUtils.lerp(29.0d, 34.0d, (d6 - 0.5d) / 0.5d) : 34.0d;
                    }
                } else {
                    d2 = 98.0d;
                }
                return Double.valueOf(d2);
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                return ((DynamicScheme) obj).neutralPalette;
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                DynamicScheme dynamicScheme4 = (DynamicScheme) obj;
                boolean z2 = dynamicScheme4.isDark;
                double d7 = dynamicScheme4.contrastLevel;
                if (z2) {
                    d3 = 10.0d;
                    if (d7 > -1.0d) {
                        if (d7 < 0.0d) {
                            d3 = MathUtils.lerp(10.0d, 10.0d, (d7 - (-1.0d)) / 1.0d);
                        } else if (d7 < 0.5d) {
                            d3 = MathUtils.lerp(10.0d, 11.0d, (d7 - 0.0d) / 0.5d);
                        } else {
                            d4 = 12.0d;
                            if (d7 < 1.0d) {
                                d3 = MathUtils.lerp(11.0d, 12.0d, (d7 - 0.5d) / 0.5d);
                            }
                            d3 = d4;
                        }
                    }
                    return Double.valueOf(d3);
                }
                d3 = 96.0d;
                if (d7 > -1.0d) {
                    if (d7 < 0.0d) {
                        lerp = MathUtils.lerp(96.0d, 96.0d, (d7 - (-1.0d)) / 1.0d);
                    } else if (d7 < 0.5d) {
                        lerp = MathUtils.lerp(96.0d, 96.0d, (d7 - 0.0d) / 0.5d);
                    } else {
                        d4 = 95.0d;
                        if (d7 < 1.0d) {
                            lerp = MathUtils.lerp(96.0d, 95.0d, (d7 - 0.5d) / 0.5d);
                        }
                        d3 = d4;
                    }
                    d3 = lerp;
                }
                return Double.valueOf(d3);
            case 20:
                return ((DynamicScheme) obj).secondaryPalette;
            case 21:
                return ((DynamicScheme) obj).primaryPalette;
            case 22:
                DynamicScheme dynamicScheme5 = (DynamicScheme) obj;
                boolean isMonochrome2 = MaterialDynamicColors.isMonochrome(dynamicScheme5);
                boolean z3 = dynamicScheme5.isDark;
                if (isMonochrome2) {
                    return Double.valueOf(z3 ? 10.0d : 100.0d);
                }
                return Double.valueOf(z3 ? 20.0d : 100.0d);
            case 23:
                return ((DynamicScheme) obj).neutralPalette;
            case 24:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 10.0d : 90.0d);
            case 25:
                return ((DynamicScheme) obj).neutralPalette;
            case 26:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 90.0d : 10.0d);
            case 27:
                return ((DynamicScheme) obj).primaryPalette;
            case 28:
                return Double.valueOf(MaterialDynamicColors.isMonochrome((DynamicScheme) obj) ? 100.0d : 10.0d);
            default:
                DynamicScheme dynamicScheme6 = (DynamicScheme) obj;
                boolean isMonochrome3 = MaterialDynamicColors.isMonochrome(dynamicScheme6);
                boolean z4 = dynamicScheme6.isDark;
                if (isMonochrome3) {
                    return Double.valueOf(z4 ? 10.0d : 90.0d);
                }
                return Double.valueOf(z4 ? 20.0d : 100.0d);
        }
    }
}
