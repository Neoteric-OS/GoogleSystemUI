package com.android.systemui.demomode;

import android.os.Bundle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DemoModeCommandReceiver {
    void dispatchDemoCommand(Bundle bundle, String str);

    default void onDemoModeFinished() {
    }

    default void onDemoModeStarted() {
    }
}
