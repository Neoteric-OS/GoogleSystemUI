package com.google.ux.material.libmonet.dynamiccolor;

import com.android.app.viewcapture.data.ViewNode;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class MaterialDynamicColors$$ExternalSyntheticLambda9 implements Function {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (this.$r8$classId) {
            case 0:
                return dynamicScheme.secondaryPalette;
            case 1:
                return Double.valueOf(MaterialDynamicColors.isMonochrome(dynamicScheme) ? 25.0d : 30.0d);
            case 2:
                return dynamicScheme.primaryPalette;
            case 3:
                return Double.valueOf(dynamicScheme.primaryPalette.keyColor.tone);
            case 4:
                return dynamicScheme.tertiaryPalette;
            case 5:
                return Double.valueOf(dynamicScheme.tertiaryPalette.keyColor.tone);
            case 6:
                return dynamicScheme.tertiaryPalette;
            case 7:
                return dynamicScheme.neutralVariantPalette;
            case 8:
                return Double.valueOf(dynamicScheme.isDark ? 80.0d : 30.0d);
            case 9:
                return dynamicScheme.errorPalette;
            case 10:
                return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return dynamicScheme.tertiaryPalette;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return Double.valueOf(MaterialDynamicColors.isMonochrome(dynamicScheme) ? 40.0d : 90.0d);
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return dynamicScheme.primaryPalette;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                boolean isMonochrome = MaterialDynamicColors.isMonochrome(dynamicScheme);
                boolean z = dynamicScheme.isDark;
                if (isMonochrome) {
                    return Double.valueOf(z ? 100.0d : 0.0d);
                }
                return Double.valueOf(z ? 80.0d : 40.0d);
            default:
                return dynamicScheme.neutralPalette;
        }
    }
}
