package com.android.systemui.mediaprojection.taskswitcher.data.repository;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ActivityTaskManagerTasksRepository$findRunningTaskFromWindowContainerToken$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ActivityTaskManagerTasksRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityTaskManagerTasksRepository$findRunningTaskFromWindowContainerToken$1(ActivityTaskManagerTasksRepository activityTaskManagerTasksRepository, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = activityTaskManagerTasksRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.findRunningTaskFromWindowContainerToken(null, this);
    }
}
