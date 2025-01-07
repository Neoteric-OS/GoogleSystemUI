package com.android.systemui.monet;

import com.android.app.viewcapture.data.ViewNode;
import com.google.ux.material.libmonet.dynamiccolor.DynamicScheme;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CustomDynamicColors$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (this.$r8$classId) {
            case 0:
                return dynamicScheme.secondaryPalette;
            case 1:
                return dynamicScheme.primaryPalette;
            case 2:
                return Double.valueOf(dynamicScheme.isDark ? 60.0d : 50.0d);
            case 3:
                return dynamicScheme.primaryPalette;
            case 4:
                return Double.valueOf(dynamicScheme.isDark ? 90.0d : 40.0d);
            case 5:
                return dynamicScheme.tertiaryPalette;
            case 6:
                return Double.valueOf(dynamicScheme.isDark ? 90.0d : 59.0d);
            case 7:
                return Double.valueOf(dynamicScheme.isDark ? 60.0d : 30.0d);
            case 8:
                return dynamicScheme.primaryPalette;
            case 9:
                return Double.valueOf(90.0d);
            case 10:
                return dynamicScheme.primaryPalette;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return Double.valueOf(30.0d);
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return dynamicScheme.primaryPalette;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return Double.valueOf(dynamicScheme.isDark ? 20.0d : 95.0d);
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                return dynamicScheme.secondaryPalette;
            case 15:
                return Double.valueOf(dynamicScheme.isDark ? 98.0d : 70.0d);
            case 16:
                return dynamicScheme.primaryPalette;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                return dynamicScheme.neutralVariantPalette;
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                return dynamicScheme.neutralPalette;
            case 20:
                return Double.valueOf(4.0d);
            case 21:
                return dynamicScheme.primaryPalette;
            case 22:
                return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
            case 23:
                return dynamicScheme.neutralVariantPalette;
            case 24:
                return Double.valueOf(80.0d);
            case 25:
                return dynamicScheme.tertiaryPalette;
            case 26:
                return dynamicScheme.neutralPalette;
            case 27:
                return Double.valueOf(20.0d);
            case 28:
                return dynamicScheme.primaryPalette;
            default:
                return Double.valueOf(0.0d);
        }
    }
}
