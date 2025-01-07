package com.android.systemui.controls.controller;

import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsProviderLifecycleManager$ServiceMethod$run$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ControlsProviderLifecycleManager this$0;

    public /* synthetic */ ControlsProviderLifecycleManager$ServiceMethod$run$1(ControlsProviderLifecycleManager controlsProviderLifecycleManager, int i) {
        this.$r8$classId = i;
        this.this$0 = controlsProviderLifecycleManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.unbindAndCleanup("couldn't call through binder");
                break;
            case 1:
                this.this$0.unbindAndCleanup("package updated");
                ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0;
                ((ExecutorImpl) controlsProviderLifecycleManager.executor).execute(new ControlsProviderLifecycleManager$bindService$1(controlsProviderLifecycleManager, true, controlsProviderLifecycleManager.lastForPanel));
                break;
            case 2:
                ControlsProviderLifecycleManager controlsProviderLifecycleManager2 = this.this$0;
                if (controlsProviderLifecycleManager2.requiresBound) {
                    ((ExecutorImpl) controlsProviderLifecycleManager2.executor).execute(new ControlsProviderLifecycleManager$ServiceMethod$run$1(controlsProviderLifecycleManager2, 1));
                    break;
                }
                break;
            case 3:
                this.this$0.unbindAndCleanup("binder died");
                break;
            default:
                this.this$0.unbindAndCleanup("null binding");
                break;
        }
    }
}
