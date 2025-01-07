package com.android.systemui.monet;

import com.android.app.viewcapture.data.ViewNode;
import com.google.ux.material.libmonet.dynamiccolor.DynamicColor;
import com.google.ux.material.libmonet.dynamiccolor.MaterialDynamicColors;
import com.google.ux.material.libmonet.dynamiccolor.ToneDeltaPair;
import com.google.ux.material.libmonet.dynamiccolor.TonePolarity;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CustomDynamicColors$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CustomDynamicColors f$0;

    public /* synthetic */ CustomDynamicColors$$ExternalSyntheticLambda2(CustomDynamicColors customDynamicColors, int i) {
        this.$r8$classId = i;
        this.f$0 = customDynamicColors;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.getClass();
                return CustomDynamicColors.widgetBackground();
            case 1:
                MaterialDynamicColors materialDynamicColors = this.f$0.mMdc;
                return MaterialDynamicColors.surfaceContainerLow();
            case 2:
                CustomDynamicColors customDynamicColors = this.f$0;
                return new ToneDeltaPair(customDynamicColors.brandC(), customDynamicColors.brandD(), 10.0d, TonePolarity.NEARER, false);
            case 3:
                this.f$0.getClass();
                return CustomDynamicColors.widgetBackground();
            case 4:
                MaterialDynamicColors materialDynamicColors2 = this.f$0.mMdc;
                return MaterialDynamicColors.surfaceContainerLow();
            case 5:
                CustomDynamicColors customDynamicColors2 = this.f$0;
                return new ToneDeltaPair(customDynamicColors2.brandD(), customDynamicColors2.brandA(), 10.0d, TonePolarity.NEARER, false);
            case 6:
                this.f$0.getClass();
                return CustomDynamicColors.underSurface();
            case 7:
                CustomDynamicColors customDynamicColors3 = this.f$0;
                return new ToneDeltaPair(customDynamicColors3.shadeActive(), customDynamicColors3.shadeInactive(), 30.0d, TonePolarity.LIGHTER, false);
            case 8:
                return this.f$0.shadeActive();
            case 9:
                CustomDynamicColors customDynamicColors4 = this.f$0;
                return new ToneDeltaPair(customDynamicColors4.onShadeActiveVariant(), customDynamicColors4.onShadeActive(), 20.0d, TonePolarity.NEARER, false);
            case 10:
                MaterialDynamicColors materialDynamicColors3 = this.f$0.mMdc;
                return MaterialDynamicColors.surfaceContainerLow();
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                CustomDynamicColors customDynamicColors5 = this.f$0;
                return new ToneDeltaPair(customDynamicColors5.brandB(), customDynamicColors5.brandC(), 10.0d, TonePolarity.NEARER, false);
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                MaterialDynamicColors materialDynamicColors4 = this.f$0.mMdc;
                return MaterialDynamicColors.surfaceContainerLow();
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                CustomDynamicColors customDynamicColors6 = this.f$0;
                return new ToneDeltaPair(customDynamicColors6.brandA(), customDynamicColors6.brandB(), 10.0d, TonePolarity.NEARER, false);
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                CustomDynamicColors customDynamicColors7 = this.f$0;
                return new ToneDeltaPair(customDynamicColors7.clockHour(), customDynamicColors7.clockMinute(), 10.0d, TonePolarity.DARKER, false);
            case 15:
                return this.f$0.shadeInactive();
            case 16:
                CustomDynamicColors customDynamicColors8 = this.f$0;
                return new ToneDeltaPair(customDynamicColors8.onShadeInactive(), customDynamicColors8.onShadeInactiveVariant(), 10.0d, TonePolarity.NEARER, false);
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                this.f$0.getClass();
                return CustomDynamicColors.underSurface();
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                return new DynamicColor("theme_app", new CustomDynamicColors$$ExternalSyntheticLambda5(1), new CustomDynamicColors$$ExternalSyntheticLambda5(2), true, null, null, null, null);
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                return this.f$0.shadeInactive();
            case 20:
                CustomDynamicColors customDynamicColors9 = this.f$0;
                return new ToneDeltaPair(customDynamicColors9.onShadeInactiveVariant(), customDynamicColors9.onShadeInactive(), 10.0d, TonePolarity.NEARER, false);
            case 21:
                this.f$0.getClass();
                return CustomDynamicColors.underSurface();
            case 22:
                CustomDynamicColors customDynamicColors10 = this.f$0;
                return new ToneDeltaPair(customDynamicColors10.shadeInactive(), customDynamicColors10.shadeDisabled(), 15.0d, TonePolarity.LIGHTER, false);
            case 23:
                return CustomDynamicColors.widgetBackground();
            case 24:
                return this.f$0.shadeActive();
            case 25:
                CustomDynamicColors customDynamicColors11 = this.f$0;
                return new ToneDeltaPair(customDynamicColors11.onShadeActive(), customDynamicColors11.onShadeActiveVariant(), 20.0d, TonePolarity.NEARER, false);
            case 26:
                this.f$0.getClass();
                return CustomDynamicColors.themeAppRing();
            default:
                return new ToneDeltaPair(this.f$0.themeNotif(), CustomDynamicColors.themeAppRing(), 10.0d, TonePolarity.NEARER, false);
        }
    }
}
