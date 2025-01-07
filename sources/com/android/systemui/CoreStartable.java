package com.android.systemui;

import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface CoreStartable extends Dumpable {
    default boolean isDumpCritical() {
        return true;
    }

    void start();

    default void onBootCompleted() {
    }

    @Override // com.android.systemui.Dumpable
    default void dump(PrintWriter printWriter, String[] strArr) {
    }
}
