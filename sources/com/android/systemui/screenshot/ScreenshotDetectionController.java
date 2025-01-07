package com.android.systemui.screenshot;

import android.content.pm.PackageManager;
import android.view.IWindowManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotDetectionController {
    public final PackageManager packageManager;
    public final IWindowManager windowManager;

    public ScreenshotDetectionController(IWindowManager iWindowManager, PackageManager packageManager) {
        this.windowManager = iWindowManager;
        this.packageManager = packageManager;
    }
}
