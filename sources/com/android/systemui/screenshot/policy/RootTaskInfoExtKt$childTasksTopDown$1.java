package com.android.systemui.screenshot.policy;

import android.app.ActivityTaskManager;
import com.android.systemui.screenshot.data.model.ChildTaskModel;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class RootTaskInfoExtKt$childTasksTopDown$1 extends Lambda implements Function1 {
    final /* synthetic */ ActivityTaskManager.RootTaskInfo $this_childTasksTopDown;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RootTaskInfoExtKt$childTasksTopDown$1(ActivityTaskManager.RootTaskInfo rootTaskInfo) {
        super(1);
        this.$this_childTasksTopDown = rootTaskInfo;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int intValue = ((Number) obj).intValue();
        ActivityTaskManager.RootTaskInfo rootTaskInfo = this.$this_childTasksTopDown;
        return new ChildTaskModel(rootTaskInfo.childTaskIds[intValue], rootTaskInfo.childTaskNames[intValue], rootTaskInfo.childTaskBounds[intValue], rootTaskInfo.childTaskUserIds[intValue]);
    }
}
