package com.android.systemui.monet;

import com.google.ux.material.libmonet.dynamiccolor.DynamicScheme;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CustomDynamicColors$$ExternalSyntheticLambda5 implements Function {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (this.$r8$classId) {
            case 0:
                return Double.valueOf(dynamicScheme.isDark ? 90.0d : 80.0d);
            case 1:
                return dynamicScheme.primaryPalette;
            case 2:
                return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
            case 3:
                return dynamicScheme.neutralVariantPalette;
            case 4:
                return Double.valueOf(dynamicScheme.isDark ? 35.0d : 80.0d);
            case 5:
                return dynamicScheme.primaryPalette;
            case 6:
                return Double.valueOf(dynamicScheme.isDark ? 80.0d : 55.0d);
            case 7:
                return dynamicScheme.primaryPalette;
            case 8:
                return Double.valueOf(10.0d);
            case 9:
                return dynamicScheme.primaryPalette;
            default:
                return Double.valueOf(70.0d);
        }
    }
}
