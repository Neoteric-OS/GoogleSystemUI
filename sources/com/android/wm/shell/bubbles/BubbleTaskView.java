package com.android.wm.shell.bubbles;

import android.content.ComponentName;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.taskview.TaskView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleTaskView {
    public ComponentName componentName;
    public TaskView.Listener delegateListener;
    public boolean isCreated;
    public final BubbleTaskView$listener$1 listener;
    public int taskId = -1;
    public final TaskView taskView;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.bubbles.BubbleTaskView$listener$1, com.android.wm.shell.taskview.TaskView$Listener] */
    public BubbleTaskView(TaskView taskView, ShellExecutor shellExecutor) {
        this.taskView = taskView;
        ?? r0 = new TaskView.Listener() { // from class: com.android.wm.shell.bubbles.BubbleTaskView$listener$1
            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onBackPressedOnTaskRoot(int i) {
                TaskView.Listener listener = BubbleTaskView.this.delegateListener;
                if (listener != null) {
                    listener.onBackPressedOnTaskRoot(i);
                }
            }

            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onInitialized() {
                TaskView.Listener listener = BubbleTaskView.this.delegateListener;
                if (listener != null) {
                    listener.onInitialized();
                }
            }

            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onReleased() {
                TaskView.Listener listener = BubbleTaskView.this.delegateListener;
                if (listener != null) {
                    listener.onReleased();
                }
            }

            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onTaskCreated(int i, ComponentName componentName) {
                BubbleTaskView bubbleTaskView = BubbleTaskView.this;
                TaskView.Listener listener = bubbleTaskView.delegateListener;
                if (listener != null) {
                    listener.onTaskCreated(i, componentName);
                }
                bubbleTaskView.taskId = i;
                bubbleTaskView.isCreated = true;
                bubbleTaskView.componentName = componentName;
            }

            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onTaskRemovalStarted(int i) {
                TaskView.Listener listener = BubbleTaskView.this.delegateListener;
                if (listener != null) {
                    listener.onTaskRemovalStarted(i);
                }
            }

            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onTaskVisibilityChanged(int i, boolean z) {
                TaskView.Listener listener = BubbleTaskView.this.delegateListener;
                if (listener != null) {
                    listener.onTaskVisibilityChanged(i, z);
                }
            }
        };
        this.listener = r0;
        taskView.setListener(shellExecutor, r0);
    }

    public final TaskView.Listener getListener() {
        return this.listener;
    }
}
