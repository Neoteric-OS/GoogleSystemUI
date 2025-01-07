package com.android.systemui.statusbar.phone;

import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.util.function.TriConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ScrimController$$ExternalSyntheticLambda5 implements TriConsumer {
    public final /* synthetic */ LightBarController f$0;

    public /* synthetic */ ScrimController$$ExternalSyntheticLambda5(LightBarController lightBarController) {
        this.f$0 = lightBarController;
    }

    public final void accept(Object obj, Object obj2, Object obj3) {
        LightBarController lightBarController = this.f$0;
        ScrimState scrimState = (ScrimState) obj;
        float floatValue = ((Float) obj2).floatValue();
        ColorExtractor.GradientColors gradientColors = (ColorExtractor.GradientColors) obj3;
        boolean z = lightBarController.mBouncerVisible;
        boolean z2 = lightBarController.mForceDarkForScrim;
        boolean z3 = lightBarController.mForceLightForScrim;
        boolean z4 = scrimState == ScrimState.BOUNCER || scrimState == ScrimState.BOUNCER_SCRIMMED;
        lightBarController.mBouncerVisible = z4;
        boolean z5 = z4 || floatValue >= 0.1f;
        boolean supportsDarkText = gradientColors.supportsDarkText();
        boolean z6 = z5 && !supportsDarkText;
        lightBarController.mForceDarkForScrim = z6;
        boolean z7 = z5 && supportsDarkText;
        lightBarController.mForceLightForScrim = z7;
        if (lightBarController.mBouncerVisible != z) {
            lightBarController.reevaluate();
            return;
        }
        if (lightBarController.mHasLightNavigationBar) {
            if (z6 != z2) {
                lightBarController.reevaluate();
            }
        } else if (z7 != z3) {
            lightBarController.reevaluate();
        }
    }
}
