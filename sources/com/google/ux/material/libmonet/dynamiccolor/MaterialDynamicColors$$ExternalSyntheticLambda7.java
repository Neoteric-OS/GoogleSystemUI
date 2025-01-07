package com.google.ux.material.libmonet.dynamiccolor;

import com.android.app.viewcapture.data.ViewNode;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class MaterialDynamicColors$$ExternalSyntheticLambda7 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MaterialDynamicColors f$0;

    public /* synthetic */ MaterialDynamicColors$$ExternalSyntheticLambda7(MaterialDynamicColors materialDynamicColors, int i) {
        this.$r8$classId = i;
        this.f$0 = materialDynamicColors;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        int i = this.$r8$classId;
        MaterialDynamicColors materialDynamicColors = this.f$0;
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (i) {
            case 0:
                return materialDynamicColors.primaryFixedDim();
            case 1:
                return new ToneDeltaPair(materialDynamicColors.primaryFixed(), materialDynamicColors.primaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
            case 2:
                return new ToneDeltaPair(materialDynamicColors.tertiaryFixed(), materialDynamicColors.tertiaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
            case 3:
                return materialDynamicColors.tertiary();
            case 4:
                return new ToneDeltaPair(materialDynamicColors.secondaryContainer(), materialDynamicColors.secondary(), 10.0d, TonePolarity.NEARER, false);
            case 5:
                return materialDynamicColors.secondaryFixedDim();
            case 6:
                return materialDynamicColors.secondaryFixed();
            case 7:
                boolean isMonochrome = MaterialDynamicColors.isMonochrome(dynamicScheme);
                boolean z = dynamicScheme.isDark;
                if (isMonochrome) {
                    return Double.valueOf(z ? 0.0d : 100.0d);
                }
                if (materialDynamicColors.isFidelity(dynamicScheme)) {
                    return Double.valueOf(DynamicColor.foregroundTone(((Double) materialDynamicColors.tertiaryContainer().tone.apply(dynamicScheme)).doubleValue(), 4.5d));
                }
                return Double.valueOf(z ? 90.0d : 30.0d);
            case 8:
                return materialDynamicColors.tertiaryContainer();
            case 9:
                return materialDynamicColors.primaryFixed();
            case 10:
                return new ToneDeltaPair(materialDynamicColors.errorContainer(), materialDynamicColors.error(), 10.0d, TonePolarity.NEARER, false);
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return new ToneDeltaPair(materialDynamicColors.tertiaryFixed(), materialDynamicColors.tertiaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
            default:
                return new ToneDeltaPair(materialDynamicColors.primaryContainer(), materialDynamicColors.primary(), 10.0d, TonePolarity.NEARER, false);
        }
    }
}
