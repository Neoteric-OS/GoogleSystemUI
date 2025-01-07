package com.android.systemui.navigationbar.views;

import android.view.Display;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class NavigationBar$$ExternalSyntheticLambda10 implements View.OnLongClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NavigationBar f$0;

    public /* synthetic */ NavigationBar$$ExternalSyntheticLambda10(NavigationBar navigationBar, int i) {
        this.$r8$classId = i;
        this.f$0 = navigationBar;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int i;
        int i2 = this.$r8$classId;
        NavigationBar navigationBar = this.f$0;
        switch (i2) {
            case 0:
                navigationBar.getClass();
                Display display = view.getDisplay();
                AccessibilityManager accessibilityManager = navigationBar.mAccessibilityManager;
                if (display != null) {
                    i = display.getDisplayId();
                } else {
                    navigationBar.mDisplayTracker.getClass();
                    i = 0;
                }
                accessibilityManager.notifyAccessibilityButtonLongClicked(i);
                return true;
            case 1:
                return navigationBar.onImeSwitcherLongClick(view);
            case 2:
                return navigationBar.onLongPressNavigationButtons(view, R.id.recent_apps);
            case 3:
                return navigationBar.onLongPressNavigationButtons(view, R.id.home);
            default:
                return navigationBar.onHomeLongClick(view);
        }
    }
}
