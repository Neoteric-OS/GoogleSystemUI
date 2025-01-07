package com.google.android.systemui.columbus.legacy.actions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class UserAction extends Action {
    public boolean availableOnLockscreen() {
        return this instanceof LaunchApp;
    }

    public boolean availableOnScreenOff() {
        return this instanceof LaunchOpa;
    }
}
