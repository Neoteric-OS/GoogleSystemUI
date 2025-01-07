package com.android.systemui.mediaprojection.appselector.data;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.wm.shell.shared.split.SplitBounds;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RecentTask {
    public final ComponentName baseIntentComponent;
    public final Integer colorBackground;
    public final int displayId;
    public final boolean isForegroundTask;
    public final SplitBounds splitBounds;
    public final int taskId;
    public final ComponentName topActivityComponent;
    public final int userId;
    public final UserType userType;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UserType {
        public static final /* synthetic */ UserType[] $VALUES;
        public static final UserType CLONED;
        public static final UserType PRIVATE;
        public static final UserType STANDARD;
        public static final UserType WORK;

        static {
            UserType userType = new UserType("STANDARD", 0);
            STANDARD = userType;
            UserType userType2 = new UserType("WORK", 1);
            WORK = userType2;
            UserType userType3 = new UserType("PRIVATE", 2);
            PRIVATE = userType3;
            UserType userType4 = new UserType("CLONED", 3);
            CLONED = userType4;
            UserType[] userTypeArr = {userType, userType2, userType3, userType4};
            $VALUES = userTypeArr;
            EnumEntriesKt.enumEntries(userTypeArr);
        }

        public static UserType valueOf(String str) {
            return (UserType) Enum.valueOf(UserType.class, str);
        }

        public static UserType[] values() {
            return (UserType[]) $VALUES.clone();
        }
    }

    public RecentTask(ActivityManager.RecentTaskInfo recentTaskInfo, boolean z, UserType userType, SplitBounds splitBounds) {
        int i = recentTaskInfo.taskId;
        int i2 = recentTaskInfo.displayId;
        int i3 = recentTaskInfo.userId;
        ComponentName componentName = recentTaskInfo.topActivity;
        Intent intent = recentTaskInfo.baseIntent;
        ComponentName component = intent != null ? intent.getComponent() : null;
        ActivityManager.TaskDescription taskDescription = recentTaskInfo.taskDescription;
        Integer valueOf = taskDescription != null ? Integer.valueOf(taskDescription.getBackgroundColor()) : null;
        this.taskId = i;
        this.displayId = i2;
        this.userId = i3;
        this.topActivityComponent = componentName;
        this.baseIntentComponent = component;
        this.colorBackground = valueOf;
        this.isForegroundTask = z;
        this.userType = userType;
        this.splitBounds = splitBounds;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RecentTask)) {
            return false;
        }
        RecentTask recentTask = (RecentTask) obj;
        return this.taskId == recentTask.taskId && this.displayId == recentTask.displayId && this.userId == recentTask.userId && Intrinsics.areEqual(this.topActivityComponent, recentTask.topActivityComponent) && Intrinsics.areEqual(this.baseIntentComponent, recentTask.baseIntentComponent) && Intrinsics.areEqual(this.colorBackground, recentTask.colorBackground) && this.isForegroundTask == recentTask.isForegroundTask && this.userType == recentTask.userType && Intrinsics.areEqual(this.splitBounds, recentTask.splitBounds);
    }

    public final int hashCode() {
        int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.userId, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.displayId, Integer.hashCode(this.taskId) * 31, 31), 31);
        ComponentName componentName = this.topActivityComponent;
        int hashCode = (m + (componentName == null ? 0 : componentName.hashCode())) * 31;
        ComponentName componentName2 = this.baseIntentComponent;
        int hashCode2 = (hashCode + (componentName2 == null ? 0 : componentName2.hashCode())) * 31;
        Integer num = this.colorBackground;
        int hashCode3 = (this.userType.hashCode() + TransitionData$$ExternalSyntheticOutline0.m((hashCode2 + (num == null ? 0 : num.hashCode())) * 31, 31, this.isForegroundTask)) * 31;
        SplitBounds splitBounds = this.splitBounds;
        return hashCode3 + (splitBounds != null ? splitBounds.hashCode() : 0);
    }

    public final String toString() {
        return "RecentTask(taskId=" + this.taskId + ", displayId=" + this.displayId + ", userId=" + this.userId + ", topActivityComponent=" + this.topActivityComponent + ", baseIntentComponent=" + this.baseIntentComponent + ", colorBackground=" + this.colorBackground + ", isForegroundTask=" + this.isForegroundTask + ", userType=" + this.userType + ", splitBounds=" + this.splitBounds + ")";
    }
}
