package com.android.systemui.screenshot.appclips;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternalTaskInfo {
    public final PackageManager packageManager;
    public final int taskId;
    public final Lazy topActivityAppIcon$delegate;
    public final Lazy topActivityAppName$delegate;
    public final ActivityInfo topActivityInfo;
    public final String topActivityPackageName;
    public final int userId;

    public InternalTaskInfo(ActivityInfo activityInfo, int i, int i2, PackageManager packageManager) {
        this.topActivityInfo = activityInfo;
        this.taskId = i;
        this.userId = i2;
        this.packageManager = packageManager;
        String str = activityInfo.name;
        this.topActivityPackageName = activityInfo.packageName;
        this.topActivityAppName$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.screenshot.appclips.InternalTaskInfo$topActivityAppName$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                InternalTaskInfo internalTaskInfo = InternalTaskInfo.this;
                return internalTaskInfo.topActivityInfo.loadLabel(internalTaskInfo.packageManager).toString();
            }
        });
        this.topActivityAppIcon$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.screenshot.appclips.InternalTaskInfo$topActivityAppIcon$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                InternalTaskInfo internalTaskInfo = InternalTaskInfo.this;
                return internalTaskInfo.topActivityInfo.loadIcon(internalTaskInfo.packageManager);
            }
        });
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InternalTaskInfo)) {
            return false;
        }
        InternalTaskInfo internalTaskInfo = (InternalTaskInfo) obj;
        return Intrinsics.areEqual(this.topActivityInfo, internalTaskInfo.topActivityInfo) && this.taskId == internalTaskInfo.taskId && this.userId == internalTaskInfo.userId && Intrinsics.areEqual(this.packageManager, internalTaskInfo.packageManager);
    }

    public final int hashCode() {
        return this.packageManager.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.userId, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.taskId, this.topActivityInfo.hashCode() * 31, 31), 31);
    }

    public final String toString() {
        return "InternalTaskInfo(topActivityInfo=" + this.topActivityInfo + ", taskId=" + this.taskId + ", userId=" + this.userId + ", packageManager=" + this.packageManager + ")";
    }
}
