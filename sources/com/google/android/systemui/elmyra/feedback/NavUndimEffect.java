package com.google.android.systemui.elmyra.feedback;

import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NavUndimEffect implements FeedbackEffect {
    public final NavigationBarControllerImpl mNavBarController;

    public NavUndimEffect(NavigationBarControllerImpl navigationBarControllerImpl) {
        this.mNavBarController = navigationBarControllerImpl;
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onProgress(int i, float f) {
        this.mNavBarController.touchAutoDim(0);
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onRelease() {
        this.mNavBarController.touchAutoDim(0);
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        this.mNavBarController.touchAutoDim(0);
    }
}
