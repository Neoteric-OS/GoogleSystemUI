package com.android.systemui.settings.brightness;

import com.android.systemui.statusbar.policy.BrightnessMirrorController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BrightnessMirrorHandler {
    public BrightnessController brightnessController;
    public final BrightnessMirrorHandler$brightnessMirrorListener$1 brightnessMirrorListener = new BrightnessMirrorHandler$brightnessMirrorListener$1(this);
    public BrightnessMirrorController mirrorController;

    public BrightnessMirrorHandler(BrightnessController brightnessController) {
        this.brightnessController = brightnessController;
    }

    public final void updateBrightnessMirror() {
        BrightnessController brightnessController = this.brightnessController;
        BrightnessMirrorController brightnessMirrorController = this.mirrorController;
        BrightnessSliderController brightnessSliderController = brightnessController.mControl;
        brightnessSliderController.mMirrorController = brightnessMirrorController;
        if (brightnessMirrorController != null) {
            brightnessSliderController.setMirror(brightnessMirrorController.mToggleSliderController);
        } else {
            brightnessSliderController.setMirror(null);
        }
    }
}
