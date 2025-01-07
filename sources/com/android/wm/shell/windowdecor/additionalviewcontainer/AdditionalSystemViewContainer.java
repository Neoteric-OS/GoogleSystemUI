package com.android.wm.shell.windowdecor.additionalviewcontainer;

import android.view.View;
import android.view.WindowManager;
import com.android.wm.shell.windowdecor.WindowManagerWrapper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AdditionalSystemViewContainer extends AdditionalViewContainer {
    public final View view;
    public final WindowManagerWrapper windowManagerWrapper;

    public AdditionalSystemViewContainer(WindowManagerWrapper windowManagerWrapper, int i, int i2, int i3, int i4, int i5, View view) {
        this.windowManagerWrapper = windowManagerWrapper;
        this.view = view;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i4, i5, i2, i3, 2041, 262152, -2);
        layoutParams.setTitle("Additional view container of Task=" + i);
        layoutParams.gravity = 51;
        layoutParams.setTrustedOverlay();
        windowManagerWrapper.windowManager.addView(view, layoutParams);
    }
}
