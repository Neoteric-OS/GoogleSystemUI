package com.android.systemui.unfold;

import android.os.SystemProperties;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class UnfoldTransitionModuleKt {
    public static final boolean ENABLE_FOLD_TASK_ANIMATIONS = SystemProperties.getBoolean("persist.unfold.enable_fold_tasks_animation", false);
}
