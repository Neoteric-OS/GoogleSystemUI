package com.android.systemui.statusbar;

import android.view.View;
import android.view.WindowInsets;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class KeyboardShortcutListSearch$$ExternalSyntheticLambda11 implements View.OnApplyWindowInsetsListener {
    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        int i = windowInsets.getInsets(WindowInsets.Type.navigationBars()).bottom;
        View findViewById = view.findViewById(R.id.keyboard_shortcuts_container);
        findViewById.setPadding(findViewById.getPaddingLeft(), findViewById.getPaddingTop(), findViewById.getPaddingRight(), i);
        return WindowInsets.CONSUMED;
    }
}
