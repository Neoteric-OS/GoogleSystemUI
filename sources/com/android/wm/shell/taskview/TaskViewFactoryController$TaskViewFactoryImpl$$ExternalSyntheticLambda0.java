package com.android.wm.shell.taskview;

import android.content.Context;
import com.android.wm.shell.taskview.TaskViewFactoryController;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TaskViewFactoryController$TaskViewFactoryImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TaskViewFactoryController.TaskViewFactoryImpl f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ Executor f$2;
    public final /* synthetic */ Consumer f$3;

    public /* synthetic */ TaskViewFactoryController$TaskViewFactoryImpl$$ExternalSyntheticLambda0(TaskViewFactoryController.TaskViewFactoryImpl taskViewFactoryImpl, Context context, Executor executor, Consumer consumer) {
        this.f$0 = taskViewFactoryImpl;
        this.f$1 = context;
        this.f$2 = executor;
        this.f$3 = consumer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TaskViewFactoryController.TaskViewFactoryImpl taskViewFactoryImpl = this.f$0;
        Context context = this.f$1;
        Executor executor = this.f$2;
        final Consumer consumer = this.f$3;
        TaskViewFactoryController taskViewFactoryController = TaskViewFactoryController.this;
        taskViewFactoryController.getClass();
        final TaskView taskView = new TaskView(context, new TaskViewTaskController(context, taskViewFactoryController.mTaskOrganizer, taskViewFactoryController.mTaskViewTransitions, taskViewFactoryController.mSyncQueue));
        executor.execute(new Runnable() { // from class: com.android.wm.shell.taskview.TaskViewFactoryController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                consumer.accept(taskView);
            }
        });
    }
}
