package com.android.wm.shell.common.pip;

import android.content.Context;
import android.view.WindowManager;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface PipMenuController {
    static WindowManager.LayoutParams getPipMenuLayoutParams(int i, int i2, Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i, i2, 2038, 545521680, -3);
        layoutParams.privateFlags |= 536870912;
        layoutParams.setTitle("PipMenuView");
        layoutParams.accessibilityTitle = context.getResources().getString(R.string.pip_menu_accessibility_title);
        return layoutParams;
    }
}
