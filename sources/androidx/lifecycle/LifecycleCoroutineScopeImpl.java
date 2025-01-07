package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LifecycleCoroutineScopeImpl implements LifecycleEventObserver, CoroutineScope {
    public final CoroutineContext coroutineContext;
    public final Lifecycle lifecycle;

    public LifecycleCoroutineScopeImpl(Lifecycle lifecycle, CoroutineContext coroutineContext) {
        Job job;
        this.lifecycle = lifecycle;
        this.coroutineContext = coroutineContext;
        if (((LifecycleRegistry) lifecycle).state != Lifecycle.State.DESTROYED || (job = (Job) coroutineContext.get(Job.Key.$$INSTANCE)) == null) {
            return;
        }
        job.cancel(null);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Lifecycle lifecycle = this.lifecycle;
        if (((LifecycleRegistry) lifecycle).state.compareTo(Lifecycle.State.DESTROYED) <= 0) {
            lifecycle.removeObserver(this);
            Job job = (Job) this.coroutineContext.get(Job.Key.$$INSTANCE);
            if (job != null) {
                job.cancel(null);
            }
        }
    }
}
