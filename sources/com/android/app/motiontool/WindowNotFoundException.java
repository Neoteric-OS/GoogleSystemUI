package com.android.app.motiontool;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowNotFoundException extends Exception {
    private final String windowId;

    public WindowNotFoundException(String str) {
        this.windowId = str;
    }

    public final String getWindowId() {
        return this.windowId;
    }
}
