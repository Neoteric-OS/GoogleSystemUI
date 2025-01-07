package com.google.ux.material.libmonet.dynamiccolor;

import com.android.app.viewcapture.data.ViewNode;
import com.google.ux.material.libmonet.utils.MathUtils;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class MaterialDynamicColors$$ExternalSyntheticLambda5 implements Function {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        double d;
        double d2;
        double lerp;
        double d3;
        double d4;
        double lerp2;
        switch (this.$r8$classId) {
            case 0:
                return ((DynamicScheme) obj).primaryPalette;
            case 1:
                return ((DynamicScheme) obj).secondaryPalette;
            case 2:
                return ((DynamicScheme) obj).neutralPalette;
            case 3:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 6.0d : 98.0d);
            case 4:
                return ((DynamicScheme) obj).tertiaryPalette;
            case 5:
                return Double.valueOf(MaterialDynamicColors.isMonochrome((DynamicScheme) obj) ? 100.0d : 10.0d);
            case 6:
                return ((DynamicScheme) obj).neutralVariantPalette;
            case 7:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 30.0d : 80.0d);
            case 8:
                return ((DynamicScheme) obj).neutralPalette;
            case 9:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 10.0d : 90.0d);
            case 10:
                return ((DynamicScheme) obj).primaryPalette;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return Double.valueOf(MaterialDynamicColors.isMonochrome((DynamicScheme) obj) ? 40.0d : 90.0d);
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return ((DynamicScheme) obj).tertiaryPalette;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return Double.valueOf(MaterialDynamicColors.isMonochrome((DynamicScheme) obj) ? 30.0d : 80.0d);
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                return ((DynamicScheme) obj).neutralVariantPalette;
            case 15:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 30.0d : 90.0d);
            case 16:
                return ((DynamicScheme) obj).neutralPalette;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                DynamicScheme dynamicScheme = (DynamicScheme) obj;
                boolean z = dynamicScheme.isDark;
                double d5 = dynamicScheme.contrastLevel;
                if (z) {
                    d = 22.0d;
                    if (d5 > -1.0d) {
                        if (d5 < 0.0d) {
                            d = MathUtils.lerp(22.0d, 22.0d, (d5 - (-1.0d)) / 1.0d);
                        } else if (d5 < 0.5d) {
                            d = MathUtils.lerp(22.0d, 26.0d, (d5 - 0.0d) / 0.5d);
                        } else {
                            d2 = 30.0d;
                            if (d5 < 1.0d) {
                                d = MathUtils.lerp(26.0d, 30.0d, (d5 - 0.5d) / 0.5d);
                            }
                            d = d2;
                        }
                    }
                    return Double.valueOf(d);
                }
                d = 90.0d;
                if (d5 > -1.0d) {
                    if (d5 < 0.0d) {
                        lerp = MathUtils.lerp(90.0d, 90.0d, (d5 - (-1.0d)) / 1.0d);
                    } else if (d5 < 0.5d) {
                        lerp = MathUtils.lerp(90.0d, 84.0d, (d5 - 0.0d) / 0.5d);
                    } else {
                        d2 = 80.0d;
                        if (d5 < 1.0d) {
                            lerp = MathUtils.lerp(84.0d, 80.0d, (d5 - 0.5d) / 0.5d);
                        }
                        d = d2;
                    }
                    d = lerp;
                }
                return Double.valueOf(d);
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                return ((DynamicScheme) obj).tertiaryPalette;
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                DynamicScheme dynamicScheme2 = (DynamicScheme) obj;
                boolean isMonochrome = MaterialDynamicColors.isMonochrome(dynamicScheme2);
                boolean z2 = dynamicScheme2.isDark;
                if (isMonochrome) {
                    return Double.valueOf(z2 ? 10.0d : 90.0d);
                }
                return Double.valueOf(z2 ? 20.0d : 100.0d);
            case 20:
                return ((DynamicScheme) obj).neutralPalette;
            case 21:
                DynamicScheme dynamicScheme3 = (DynamicScheme) obj;
                boolean z3 = dynamicScheme3.isDark;
                double d6 = dynamicScheme3.contrastLevel;
                if (z3) {
                    d3 = 17.0d;
                    if (d6 > -1.0d) {
                        if (d6 < 0.0d) {
                            d3 = MathUtils.lerp(17.0d, 17.0d, (d6 - (-1.0d)) / 1.0d);
                        } else if (d6 < 0.5d) {
                            d3 = MathUtils.lerp(17.0d, 21.0d, (d6 - 0.0d) / 0.5d);
                        } else {
                            d4 = 25.0d;
                            if (d6 < 1.0d) {
                                d3 = MathUtils.lerp(21.0d, 25.0d, (d6 - 0.5d) / 0.5d);
                            }
                            d3 = d4;
                        }
                    }
                    return Double.valueOf(d3);
                }
                d3 = 92.0d;
                if (d6 > -1.0d) {
                    if (d6 < 0.0d) {
                        lerp2 = MathUtils.lerp(92.0d, 92.0d, (d6 - (-1.0d)) / 1.0d);
                    } else if (d6 < 0.5d) {
                        lerp2 = MathUtils.lerp(92.0d, 88.0d, (d6 - 0.0d) / 0.5d);
                    } else {
                        d4 = 85.0d;
                        if (d6 < 1.0d) {
                            lerp2 = MathUtils.lerp(88.0d, 85.0d, (d6 - 0.5d) / 0.5d);
                        }
                        d3 = d4;
                    }
                    d3 = lerp2;
                }
                return Double.valueOf(d3);
            case 22:
                return Double.valueOf(MaterialDynamicColors.isMonochrome((DynamicScheme) obj) ? 90.0d : 30.0d);
            case 23:
                return ((DynamicScheme) obj).secondaryPalette;
            case 24:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 80.0d : 40.0d);
            case 25:
                return ((DynamicScheme) obj).primaryPalette;
            case 26:
                return Double.valueOf(((DynamicScheme) obj).isDark ? 30.0d : 90.0d);
            case 27:
                return ((DynamicScheme) obj).neutralPalette;
            case 28:
                return Double.valueOf(((DynamicScheme) obj).neutralPalette.keyColor.tone);
            default:
                return ((DynamicScheme) obj).secondaryPalette;
        }
    }
}
