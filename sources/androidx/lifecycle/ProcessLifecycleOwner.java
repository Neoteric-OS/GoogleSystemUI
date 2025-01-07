package androidx.lifecycle;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ProcessLifecycleOwner implements LifecycleOwner {
    public static final ProcessLifecycleOwner newInstance = new ProcessLifecycleOwner();
    public Handler handler;
    public int resumedCounter;
    public int startedCounter;
    public boolean pauseSent = true;
    public boolean stopSent = true;
    public final LifecycleRegistry registry = new LifecycleRegistry(this);
    public final ProcessLifecycleOwner$$ExternalSyntheticLambda0 delayedPauseRunnable = new Runnable() { // from class: androidx.lifecycle.ProcessLifecycleOwner$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.this;
            int i = processLifecycleOwner.resumedCounter;
            LifecycleRegistry lifecycleRegistry = processLifecycleOwner.registry;
            if (i == 0) {
                processLifecycleOwner.pauseSent = true;
                lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
            }
            if (processLifecycleOwner.startedCounter == 0 && processLifecycleOwner.pauseSent) {
                lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
                processLifecycleOwner.stopSent = true;
            }
        }
    };

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.registry;
    }
}
