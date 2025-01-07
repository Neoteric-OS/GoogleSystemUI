package androidx.compose.foundation;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutatorMutex {
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

    public static final void access$tryMutateOrCancel(MutatorMutex mutatorMutex, Mutator mutator) {
        Mutator mutator2;
        do {
            mutator2 = (Mutator) mutatorMutex.currentMutator.get();
            if (mutator2 != null && mutator.priority.compareTo(mutator2.priority) < 0) {
                throw new CancellationException("Current mutation had a higher priority");
            }
        } while (!mutatorMutex.currentMutator.compareAndSet(mutator2, mutator));
        if (mutator2 != null) {
            mutator2.job.cancel(new MutationInterruptedException());
        }
    }

    public static Object mutate$default(MutatorMutex mutatorMutex, Function1 function1, SuspendLambda suspendLambda) {
        MutatePriority mutatePriority = MutatePriority.Default;
        mutatorMutex.getClass();
        return CoroutineScopeKt.coroutineScope(suspendLambda, new MutatorMutex$mutate$2(mutatePriority, mutatorMutex, function1, null));
    }

    public final Object mutateWith(Object obj, MutatePriority mutatePriority, Function2 function2, SuspendLambda suspendLambda) {
        return CoroutineScopeKt.coroutineScope(suspendLambda, new MutatorMutex$mutateWith$2(mutatePriority, this, function2, obj, null));
    }
}
