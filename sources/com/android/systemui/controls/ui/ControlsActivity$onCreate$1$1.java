package com.android.systemui.controls.ui;

import android.view.View;
import android.view.WindowInsets;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsActivity$onCreate$1$1 implements View.OnApplyWindowInsetsListener {
    public static final ControlsActivity$onCreate$1$1 INSTANCE = new ControlsActivity$onCreate$1$1();

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), windowInsets.getInsets(WindowInsets.Type.systemBars()).bottom);
        return WindowInsets.CONSUMED;
    }
}
