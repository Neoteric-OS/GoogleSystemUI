package com.android.systemui.util;

import android.app.ActivityThread;
import android.os.Process;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InitializationChecker {
    public final boolean instrumentationTest;

    public InitializationChecker(boolean z) {
        this.instrumentationTest = z;
    }

    public final boolean initializeComponents() {
        return !this.instrumentationTest && Process.myUserHandle().isSystem() && Intrinsics.areEqual(ActivityThread.currentProcessName(), ActivityThread.currentPackageName());
    }
}
