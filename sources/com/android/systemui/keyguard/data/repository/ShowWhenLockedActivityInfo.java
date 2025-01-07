package com.android.systemui.keyguard.data.repository;

import android.app.ActivityManager;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShowWhenLockedActivityInfo {
    public final boolean isOnTop;
    public final ActivityManager.RunningTaskInfo taskInfo;

    public ShowWhenLockedActivityInfo(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z) {
        this.isOnTop = z;
        this.taskInfo = runningTaskInfo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShowWhenLockedActivityInfo)) {
            return false;
        }
        ShowWhenLockedActivityInfo showWhenLockedActivityInfo = (ShowWhenLockedActivityInfo) obj;
        return this.isOnTop == showWhenLockedActivityInfo.isOnTop && Intrinsics.areEqual(this.taskInfo, showWhenLockedActivityInfo.taskInfo);
    }

    public final int hashCode() {
        int hashCode = Boolean.hashCode(this.isOnTop) * 31;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.taskInfo;
        return hashCode + (runningTaskInfo == null ? 0 : runningTaskInfo.hashCode());
    }

    public final String toString() {
        return "ShowWhenLockedActivityInfo(isOnTop=" + this.isOnTop + ", taskInfo=" + this.taskInfo + ")";
    }
}
