package com.android.systemui.navigationbar.gestural.domain;

import android.content.ComponentName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TaskInfo {
    public final ComponentName topActivity;
    public final int topActivityType;

    public TaskInfo(int i, ComponentName componentName) {
        this.topActivity = componentName;
        this.topActivityType = i;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TaskInfo) {
            TaskInfo taskInfo = (TaskInfo) obj;
            if (taskInfo.topActivityType == this.topActivityType && Intrinsics.areEqual(taskInfo.topActivity, this.topActivity)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        ComponentName componentName = this.topActivity;
        return Integer.hashCode(this.topActivityType) + ((componentName == null ? 0 : componentName.hashCode()) * 31);
    }

    public final String toString() {
        return "TaskInfo(topActivity=" + this.topActivity + ", topActivityType=" + this.topActivityType + ")";
    }
}
