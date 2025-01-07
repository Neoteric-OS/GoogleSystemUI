package com.google.ux.material.libmonet.dynamiccolor;

import com.android.app.viewcapture.data.ViewNode;
import com.google.ux.material.libmonet.dislike.DislikeAnalyzer;
import com.google.ux.material.libmonet.hct.Hct;
import com.google.ux.material.libmonet.palettes.TonalPalette;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class MaterialDynamicColors$$ExternalSyntheticLambda4 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MaterialDynamicColors f$0;

    public /* synthetic */ MaterialDynamicColors$$ExternalSyntheticLambda4(MaterialDynamicColors materialDynamicColors, int i) {
        this.$r8$classId = i;
        this.f$0 = materialDynamicColors;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return this.f$0.primary();
            case 1:
                return new DynamicColor("background", new MaterialDynamicColors$$ExternalSyntheticLambda5(2), new MaterialDynamicColors$$ExternalSyntheticLambda5(3), true, null, null, null, null);
            case 2:
                MaterialDynamicColors materialDynamicColors = this.f$0;
                DynamicScheme dynamicScheme = (DynamicScheme) obj;
                if (materialDynamicColors.isFidelity(dynamicScheme)) {
                    return Double.valueOf(DynamicColor.foregroundTone(((Double) materialDynamicColors.primaryContainer().tone.apply(dynamicScheme)).doubleValue(), 4.5d));
                }
                boolean isMonochrome = MaterialDynamicColors.isMonochrome(dynamicScheme);
                boolean z = dynamicScheme.isDark;
                if (isMonochrome) {
                    return Double.valueOf(z ? 0.0d : 100.0d);
                }
                return Double.valueOf(z ? 90.0d : 30.0d);
            case 3:
                return this.f$0.primaryContainer();
            case 4:
                MaterialDynamicColors materialDynamicColors2 = this.f$0;
                return new ToneDeltaPair(materialDynamicColors2.secondaryFixed(), materialDynamicColors2.secondaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
            case 5:
                return this.f$0.secondaryFixedDim();
            case 6:
                MaterialDynamicColors materialDynamicColors3 = this.f$0;
                return new ToneDeltaPair(materialDynamicColors3.primaryFixed(), materialDynamicColors3.primaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
            case 7:
                return this.f$0.secondaryFixed();
            case 8:
                MaterialDynamicColors materialDynamicColors4 = this.f$0;
                return new ToneDeltaPair(materialDynamicColors4.errorContainer(), materialDynamicColors4.error(), 10.0d, TonePolarity.NEARER, false);
            case 9:
                MaterialDynamicColors materialDynamicColors5 = this.f$0;
                return new ToneDeltaPair(materialDynamicColors5.tertiaryContainer(), materialDynamicColors5.tertiary(), 10.0d, TonePolarity.NEARER, false);
            case 10:
                MaterialDynamicColors materialDynamicColors6 = this.f$0;
                DynamicScheme dynamicScheme2 = (DynamicScheme) obj;
                materialDynamicColors6.getClass();
                boolean isMonochrome2 = MaterialDynamicColors.isMonochrome(dynamicScheme2);
                boolean z2 = dynamicScheme2.isDark;
                if (isMonochrome2) {
                    return Double.valueOf(z2 ? 60.0d : 49.0d);
                }
                if (!materialDynamicColors6.isFidelity(dynamicScheme2)) {
                    return Double.valueOf(z2 ? 30.0d : 90.0d);
                }
                double d = dynamicScheme2.sourceColorHct.tone;
                TonalPalette tonalPalette = dynamicScheme2.tertiaryPalette;
                return Double.valueOf(DislikeAnalyzer.fixIfDisliked(Hct.from(tonalPalette.hue, tonalPalette.chroma, d)).tone);
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                MaterialDynamicColors materialDynamicColors7 = this.f$0;
                return new ToneDeltaPair(materialDynamicColors7.tertiaryContainer(), materialDynamicColors7.tertiary(), 10.0d, TonePolarity.NEARER, false);
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return this.f$0.error();
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                DynamicScheme dynamicScheme3 = (DynamicScheme) obj;
                if (this.f$0.isFidelity(dynamicScheme3)) {
                    return Double.valueOf(dynamicScheme3.sourceColorHct.tone);
                }
                boolean isMonochrome3 = MaterialDynamicColors.isMonochrome(dynamicScheme3);
                boolean z3 = dynamicScheme3.isDark;
                if (isMonochrome3) {
                    return Double.valueOf(z3 ? 85.0d : 25.0d);
                }
                return Double.valueOf(z3 ? 30.0d : 90.0d);
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                MaterialDynamicColors materialDynamicColors8 = this.f$0;
                return new ToneDeltaPair(materialDynamicColors8.primaryContainer(), materialDynamicColors8.primary(), 10.0d, TonePolarity.NEARER, false);
            case 15:
                MaterialDynamicColors materialDynamicColors9 = this.f$0;
                DynamicScheme dynamicScheme4 = (DynamicScheme) obj;
                boolean isMonochrome4 = MaterialDynamicColors.isMonochrome(dynamicScheme4);
                boolean z4 = dynamicScheme4.isDark;
                if (isMonochrome4) {
                    return Double.valueOf(z4 ? 90.0d : 10.0d);
                }
                if (materialDynamicColors9.isFidelity(dynamicScheme4)) {
                    return Double.valueOf(DynamicColor.foregroundTone(((Double) materialDynamicColors9.secondaryContainer().tone.apply(dynamicScheme4)).doubleValue(), 4.5d));
                }
                return Double.valueOf(z4 ? 90.0d : 30.0d);
            case 16:
                return this.f$0.secondaryContainer();
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                MaterialDynamicColors materialDynamicColors10 = this.f$0;
                return new ToneDeltaPair(materialDynamicColors10.secondaryFixed(), materialDynamicColors10.secondaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                return this.f$0.tertiaryFixedDim();
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                return this.f$0.tertiaryFixed();
            case 20:
                return this.f$0.errorContainer();
            case 21:
                return this.f$0.secondary();
            case 22:
                return MaterialDynamicColors.highestSurface((DynamicScheme) obj);
            case 23:
                return this.f$0.primaryFixedDim();
            case 24:
                return this.f$0.primaryFixed();
            case 25:
                MaterialDynamicColors materialDynamicColors11 = this.f$0;
                DynamicScheme dynamicScheme5 = (DynamicScheme) obj;
                materialDynamicColors11.getClass();
                double d2 = dynamicScheme5.isDark ? 30.0d : 90.0d;
                boolean isMonochrome5 = MaterialDynamicColors.isMonochrome(dynamicScheme5);
                boolean z5 = dynamicScheme5.isDark;
                if (isMonochrome5) {
                    return Double.valueOf(z5 ? 30.0d : 85.0d);
                }
                if (!materialDynamicColors11.isFidelity(dynamicScheme5)) {
                    return Double.valueOf(d2);
                }
                TonalPalette tonalPalette2 = dynamicScheme5.secondaryPalette;
                double d3 = tonalPalette2.hue;
                double d4 = tonalPalette2.chroma;
                Hct from = Hct.from(d3, d4, d2);
                double d5 = from.chroma;
                if (d5 < d4) {
                    double d6 = d5;
                    while (from.chroma < d4) {
                        d2 += !z5 ? -1.0d : 1.0d;
                        Hct from2 = Hct.from(d3, d4, d2);
                        double d7 = from2.chroma;
                        if (d6 <= d7 && Math.abs(d7 - d4) >= 0.4d) {
                            if (Math.abs(from2.chroma - d4) < Math.abs(from.chroma - d4)) {
                                from = from2;
                            }
                            d6 = Math.max(d6, from2.chroma);
                        }
                    }
                }
                return Double.valueOf(d2);
            case 26:
                this.f$0.getClass();
                return MaterialDynamicColors.highestSurface((DynamicScheme) obj);
            case 27:
                MaterialDynamicColors materialDynamicColors12 = this.f$0;
                return new ToneDeltaPair(materialDynamicColors12.secondaryContainer(), materialDynamicColors12.secondary(), 10.0d, TonePolarity.NEARER, false);
            case 28:
                return this.f$0.tertiaryFixedDim();
            default:
                return this.f$0.tertiaryFixed();
        }
    }
}
