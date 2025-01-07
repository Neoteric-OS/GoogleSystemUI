package com.android.systemui.keyguard.ui.view;

import android.view.LayoutInflater;
import android.view.WindowManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SideFpsProgressBar {
    public final LayoutInflater layoutInflater;
    public final WindowManager.LayoutParams overlayViewParams;
    public final ViewCaptureAwareWindowManager windowManager;

    public SideFpsProgressBar(LayoutInflater layoutInflater, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2024, 16777496, -2);
        layoutParams.setTitle("SideFpsProgressBar");
        layoutParams.setFitInsetsTypes(0);
        layoutParams.gravity = 51;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.privateFlags = 536870976;
    }
}
