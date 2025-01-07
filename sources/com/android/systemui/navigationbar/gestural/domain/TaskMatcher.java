package com.android.systemui.navigationbar.gestural.domain;

import android.content.ComponentName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface TaskMatcher {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TopActivityComponent implements TaskMatcher {
        public final ComponentName component;

        public TopActivityComponent(ComponentName componentName) {
            this.component = componentName;
        }

        @Override // com.android.systemui.navigationbar.gestural.domain.TaskMatcher
        public final boolean matches(TaskInfo taskInfo) {
            return Intrinsics.areEqual(this.component, taskInfo.topActivity);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TopActivityType implements TaskMatcher {
        @Override // com.android.systemui.navigationbar.gestural.domain.TaskMatcher
        public final boolean matches(TaskInfo taskInfo) {
            return taskInfo.topActivity != null && taskInfo.topActivityType == 5;
        }
    }

    boolean matches(TaskInfo taskInfo);
}
