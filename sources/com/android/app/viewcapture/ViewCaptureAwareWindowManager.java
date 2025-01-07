package com.android.app.viewcapture;

import android.graphics.Region;
import android.view.Display;
import android.view.KeyboardShortcutGroup;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewCaptureAwareWindowManager implements WindowManager {
    public final Lazy lazyViewCapture;
    public final Map viewCaptureCloseableMap;
    public final WindowManager windowManager;

    public ViewCaptureAwareWindowManager(WindowManager windowManager, Lazy lazy) {
        this.windowManager = windowManager;
        new LinkedHashMap();
    }

    @Override // android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        this.windowManager.addView(view, layoutParams);
    }

    public final KeyboardShortcutGroup getApplicationLaunchKeyboardShortcuts(int i) {
        return this.windowManager.getApplicationLaunchKeyboardShortcuts(i);
    }

    public final Region getCurrentImeTouchRegion() {
        return this.windowManager.getCurrentImeTouchRegion();
    }

    @Override // android.view.WindowManager
    public final Display getDefaultDisplay() {
        return this.windowManager.getDefaultDisplay();
    }

    @Override // android.view.ViewManager
    public final void removeView(View view) {
        this.windowManager.removeView(view);
    }

    @Override // android.view.WindowManager
    public final void removeViewImmediate(View view) {
        this.windowManager.removeViewImmediate(view);
    }

    public final void requestAppKeyboardShortcuts(WindowManager.KeyboardShortcutsReceiver keyboardShortcutsReceiver, int i) {
        this.windowManager.requestAppKeyboardShortcuts(keyboardShortcutsReceiver, i);
    }

    @Override // android.view.ViewManager
    public final void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
        this.windowManager.updateViewLayout(view, layoutParams);
    }
}
