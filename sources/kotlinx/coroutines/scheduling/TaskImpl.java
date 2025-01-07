package kotlinx.coroutines.scheduling;

import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import kotlinx.coroutines.DebugStringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TaskImpl extends Task {
    public final Runnable block;

    public TaskImpl(Runnable runnable, long j, TaskContextImpl taskContextImpl) {
        super(j, taskContextImpl);
        this.block = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.block.run();
        } finally {
            this.taskContext.getClass();
        }
    }

    public final String toString() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this.block);
        String hexAddress = DebugStringsKt.getHexAddress(this.block);
        long j = this.submissionTime;
        TaskContextImpl taskContextImpl = this.taskContext;
        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Task[", classSimpleName, "@", hexAddress, ", ");
        m.append(j);
        m.append(", ");
        m.append(taskContextImpl);
        m.append("]");
        return m.toString();
    }
}
