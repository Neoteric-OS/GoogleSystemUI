package androidx.compose.material3.internal;

import androidx.compose.foundation.MutatePriority;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InternalMutatorMutex {
    public final AtomicReference currentMutator = new AtomicReference(null);
    public final MutexImpl mutex = MutexKt.Mutex$default();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Mutator {
        public final Job job;
        public final MutatePriority priority;

        public Mutator(MutatePriority mutatePriority, Job job) {
            this.priority = mutatePriority;
            this.job = job;
        }
    }
}
