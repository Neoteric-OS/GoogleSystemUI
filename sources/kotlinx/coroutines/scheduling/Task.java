package kotlinx.coroutines.scheduling;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Task implements Runnable {
    public long submissionTime;
    public TaskContextImpl taskContext;

    public Task(long j, TaskContextImpl taskContextImpl) {
        this.submissionTime = j;
        this.taskContext = taskContextImpl;
    }
}
